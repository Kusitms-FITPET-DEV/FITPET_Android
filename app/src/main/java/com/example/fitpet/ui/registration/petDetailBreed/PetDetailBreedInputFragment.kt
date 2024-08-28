package com.example.fitpet.ui.registration.petDetailBreed

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fitpet.R

class PetDetailBreedInputFragment : Fragment() {

    companion object {
        fun newInstance() = PetDetailBreedInputFragment()
    }

    private val viewModel: PetDetailBreedInputViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_pet_detail_breed_input, container, false)
    }
}