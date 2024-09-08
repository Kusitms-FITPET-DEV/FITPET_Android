package com.example.fitpet.ui.mypet.insurance.petEdit

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.graphics.Color
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.fitpet.R
import com.example.fitpet.databinding.BottomSheetMyPetBinding
import com.example.fitpet.ui.mypet.MypetMainViewModel
import com.example.fitpet.ui.mypet.insurance.petEdit.adapter.MyPetRVA
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class PetBottomSheetFragment (): BottomSheetDialogFragment() {

    private val viewModel: PetBottomSheetViewModel by viewModels(ownerProducer = {requireParentFragment()})
    private val myPetMainViewModel: MypetMainViewModel by activityViewModels()

    private var _binding: BottomSheetMyPetBinding? = null
    val binding: BottomSheetMyPetBinding
        get() = requireNotNull(_binding as BottomSheetMyPetBinding)

    private var _myPetRVA: MyPetRVA? = null
    private val myPetRVA
        get() = requireNotNull(_myPetRVA)

//    private val myPetRVA by lazy {
//        MyPetRVA(
//            onEditClicked = { mypet ->
//                viewModel.updateEditPetId(mypet.petId)
//                myPetMainViewModel.updatePetId(mypet.petId)
//                myPetMainViewModel.emitEditedPet()
//                if (isAdded) {
//                    dismiss()
//                }
//            },
//            onClicked = { mypet ->
//                viewModel.updatePetInfo(mypet)
//                myPetMainViewModel.updatePetId(mypet.petId)
//                myPetMainViewModel.emitClickedPet()
////                dismiss()
//                if (isAdded) {
//                    dismiss()
//                }
//            }
//        )
//    }

    override fun getTheme(): Int {
        return R.style.BottomSheetDialogTheme
    }

    override fun onStart() {
        super.onStart()

        val behavior = BottomSheetBehavior.from(requireView().parent as View)
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = BottomSheetMyPetBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return binding.root
    }

    protected fun LifecycleOwner.launchWhenStarted(viewLifecycleOwner: LifecycleOwner, block: suspend CoroutineScope.() -> Unit) {
        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED, block)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel

        initState()

        _myPetRVA  = MyPetRVA(
            onEditClicked = { mypet ->
                viewModel.updateEditPetId(mypet.petId)
                myPetMainViewModel.updatePetId(mypet.petId)
                myPetMainViewModel.emitEditedPet()
                if (isAdded) {
                    dismiss()
                }
            },
            onClicked = { mypet ->
                viewModel.updatePetInfo(mypet)
                myPetMainViewModel.updatePetId(mypet.petId)
                myPetMainViewModel.emitClickedPet()
//                dismiss()
                if (isAdded) {
                    dismiss()
                }
            }
        )
        binding.rvBottomMyPet.adapter = myPetRVA
        viewModel.loadPetData()
        binding.btnAddPet.setOnClickListener{ myPetMainViewModel.emitEditedMainPet()}
    }

//    private val myPetRVA by lazy {
//        MyPetRVA(
//            onEditClicked = { mypet ->
//                viewModel.updateEditPetId(mypet.petId)
//                myPetMainViewModel.updatePetId(mypet.petId)
//                myPetMainViewModel.emitEditedPet()
//                if (isAdded) {
//                    dismiss()
//                }
//            },
//            onClicked = { mypet ->
//                viewModel.updatePetInfo(mypet)
//                myPetMainViewModel.updatePetId(mypet.petId)
//                myPetMainViewModel.emitClickedPet()
////                dismiss()
//                if (isAdded) {
//                    dismiss()
//                }
//            }
//        )
//    }

    private fun initState(){
        launchWhenStarted(viewLifecycleOwner) {
            launch {
                viewModel.eventFlow.collect { event ->
                    handleEvent(event as PetBottomSheetEvent)
                }
            }
        }
    }

    private fun handleEvent(event: PetBottomSheetEvent) {
        when(event) {
            PetBottomSheetEvent.FetchPetData -> fetchPetData()
        }
    }

    private fun fetchPetData() {
        myPetRVA.submitList(viewModel.uiState.petData.value?.petList)
        }
}