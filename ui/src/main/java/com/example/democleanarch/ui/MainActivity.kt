package com.example.democleanarch.ui

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.democleanarch.BR
import com.example.democleanarch.R
import com.example.democleanarch.databinding.ActivityMainBinding
import com.example.democleanarch.ui.base.BaseActivity
import com.example.democleanarch.ui.listDetail.ListDetailActivity
import com.example.presentation.ViewModelFactory
import com.example.presentation.main.MainViewModel
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun getBindingVariable(): Int = BR.viewModel

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun getViewModel(): MainViewModel =
        ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)

    override fun mappingEvent() {
        getViewModel().getEventViewModel().observe(this,
            Observer {
                when (it) {
                    is MainViewModel.ShowDialogEvent -> showDialogWarn(it.title, it.confirm)
                    is MainViewModel.ShowToast -> showToastMsg(it.text)
                    is MainViewModel.GotoDetailScreen -> gotoDetailScreen()
                    else -> Log.d("MappingEvent", "Unknown event")
                }
            })
    }

    private fun gotoDetailScreen() {
        Intent(this, ListDetailActivity::class.java).let {
            startActivity(it)
        }
    }

    private fun showToastMsg(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    private fun showDialogWarn(title: String, confirm: String) {
        Log.d("showDialogWarn", "title: $title - confirm : $confirm")
    }
}
