package com.example.fitpet.ui.mypet.life.hospitalRecord

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fitpet.R

class HospitalRecordFragment : Fragment() {

    companion object {
        fun newInstance() = HospitalRecordFragment()
    }

    private val viewModel: HospitalRecordViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_hospital_record, container, false)
    }
}