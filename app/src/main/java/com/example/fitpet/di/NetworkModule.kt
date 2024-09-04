package com.example.fitpet.di

import com.example.fitpet.data.FitPetDataStore
import com.example.fitpet.data.repository.AuthRepository
import com.example.fitpet.model.request.ReissueTokenRequest
import com.example.fitpet.util.Config.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val AUTHORIZATION = "Authorization"

    @Singleton
    @Provides
    fun provideOkHttpClient(
        dataStore: FitPetDataStore,
        authRepository: AuthRepository
    ): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        val headerInterceptor = Interceptor{ chain ->
            val tokenFlow = dataStore.accessToken
            val token = runBlocking { tokenFlow.first() }
            val request = chain.request().newBuilder()

            token?.let {
                request.addHeader(AUTHORIZATION, "Bearer $it")
            }

            chain.proceed(request.build())
        }

        val tokenAuthenticator = Authenticator { route, response ->
            val refreshToken = runBlocking { dataStore.refreshToken.first() }
            refreshToken?.let {
                val newTokens = runBlocking {
                    authRepository.reissueToken(ReissueTokenRequest(refreshToken)).firstOrNull()?.getOrNull()
                }

                newTokens?.let { token ->
                    runBlocking {
                        dataStore.saveAccessToken(token.accessToken)
                        dataStore.saveRefreshToken(token.refreshToken)
                    }
                    response.request.newBuilder()
                        .header("Authorization", "Bearer ${token.accessToken}")
                        .build()
                }
            }
        }

        return OkHttpClient.Builder()
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(headerInterceptor)
            .authenticator(tokenAuthenticator)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}