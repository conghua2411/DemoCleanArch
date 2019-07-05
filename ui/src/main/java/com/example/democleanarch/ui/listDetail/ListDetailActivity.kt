package com.example.democleanarch.ui.listDetail

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.democleanarch.BR
import com.example.democleanarch.R
import com.example.democleanarch.databinding.ActivityListDetailBinding
import com.example.democleanarch.ui.base.BaseActivity
import com.example.presentation.ViewModelFactory
import com.example.presentation.detail.DetailListViewModel
import com.example.presentation.model.UserData
import javax.inject.Inject

class ListDetailActivity : BaseActivity<ActivityListDetailBinding, DetailListViewModel>() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var listDetailAdapter: ListDetailAdapter

    // paging
//    lateinit var userAdapter: UserAdapter

    override fun getBindingVariable(): Int = BR.viewModel

    override fun getLayoutId(): Int = R.layout.activity_list_detail

    override fun getViewModel(): DetailListViewModel =
        ViewModelProviders.of(this, viewModelFactory).get(DetailListViewModel::class.java)

    override fun mappingEvent() {
        getViewModel().getEventViewModel().observe(this,
            Observer {
                when (it) {
                    is DetailListViewModel.ShowToast -> showToast(it.text)
                    is DetailListViewModel.ShowDialogEvent -> showDialog(it.title, it.confirm)
                    is DetailListViewModel.UpdateListUser -> updateListUser(it.list)
                    else -> Log.e("ListDetailActivity", "not support event")
                }
            })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        listDetailAdapter = ListDetailAdapter(mutableListOf())

        listDetailAdapter.setItemListener(object : ListDetailAdapter.ItemListenner {
            override fun onLongClick(userData: UserData) {
                getViewModel().deleteUser(userData)
            }
        })

        listDetailAdapter.setFunc {
            getViewModel().deleteUser(it)
        }

        // paging
//        userAdapter = UserAdapter(mutableListOf())

        getViewDataBinding().rvShow.layoutManager = LinearLayoutManager(applicationContext)

        getViewDataBinding().rvShow.adapter = listDetailAdapter

        // paging
//        getViewDataBinding().rvShow.adapter = userAdapter
    }

    private fun showToast(text: String) {
        Log.d("race", "toast")
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    private fun showDialog(text: String, confirm: String) {
        AlertDialog.Builder(this)
            .setTitle(text)
            .setPositiveButton(confirm, null)
            .create().show()
    }

    private fun updateListUser(list: List<UserData>) {
        Log.d("race", "updateListUser : ${list.size}")
        listDetailAdapter.addAll(list)
    }
}
