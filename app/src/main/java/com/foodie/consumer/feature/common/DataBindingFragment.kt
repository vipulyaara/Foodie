package com.foodie.consumer.feature.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.foodie.data.config.di.kodeinInstance
import com.foodie.data.data.Logger
import org.kodein.di.generic.instance

/**
 * @author Vipul Kumar; dated 22/10/18.
 */
abstract class DataBindingFragment<Binding : ViewDataBinding>(
    private val layoutRes: Int
) : BaseFragment() {

    protected val logger: Logger by kodeinInstance.instance()
    protected lateinit var binding: Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil
            .inflate(inflater, layoutRes, container, false)
        binding.setLifecycleOwner(this)
        return binding.root
    }
}
