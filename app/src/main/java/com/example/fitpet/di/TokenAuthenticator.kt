package com.example.fitpet.di

import com.example.fitpet.data.FitPetDataStore
import com.example.fitpet.data.repository.TokenProvider
import dagger.Lazy
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class TokenAuthenticator @Inject constructor(
    private val tokenProvider: Lazy<TokenProvider>,
    private val dataStore: FitPetDataStore
): Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        val refreshToken = runBlocking { dataStore.refreshToken.firstOrNull() }

        return refreshToken?.let {
            val newTokens = runBlocking {
                tokenProvider.get().refreshTokens(refreshToken)
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
}