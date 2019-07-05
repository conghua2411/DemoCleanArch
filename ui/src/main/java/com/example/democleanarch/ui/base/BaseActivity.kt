package com.example.democleanarch.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.presentation.base.BaseViewModel
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity<B : ViewDataBinding, V : BaseViewModel> : DaggerAppCompatActivity() {
    var mBinding: B? = null
    var mViewModel: V? = null

    abstract fun getBindingVariable(): Int

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun getViewModel(): V

    fun getViewDataBinding(): B = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()

        mappingEvent()
    }

    abstract fun mappingEvent()

    private fun performDataBinding() {
        mBinding = DataBindingUtil.setContentView(this, getLayoutId())
        mViewModel = if (mViewModel == null) getViewModel() else mViewModel
        mBinding?.setVariable(getBindingVariable(), mViewModel)
        mBinding?.executePendingBindings()
    }

}
