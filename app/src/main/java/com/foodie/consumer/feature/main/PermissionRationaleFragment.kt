package com.foodie.consumer.feature.main

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import com.foodie.consumer.R
import com.foodie.consumer.databinding.FragmentPermissionRationaleBinding
import com.foodie.consumer.feature.common.DataBindingDialogFragment

/**
 * @author Vipul Kumar; dated 26/12/18.
 *
 * Rationale to show when user denies the permission.
 */
class PermissionRationaleFragment : DataBindingDialogFragment<FragmentPermissionRationaleBinding>(
    R.layout.fragment_permission_rationale
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isCancelable = false
        binding.tvAsk.setOnClickListener {
            dismiss()
            if (activity is MainActivity) {
                (activity as MainActivity).requestPermissions()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }
}
