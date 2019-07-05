package com.example.democleanarch.ui.listDetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.democleanarch.databinding.ItemUserBinding
import com.example.presentation.model.UserData

class UserAdapter(private val userList: MutableList<UserData>) : PagedListAdapter<UserData, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val itemUserBinding = ItemUserBinding.inflate(layoutInflater, parent, false)

        return UserItemViewHolder(itemUserBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as UserItemViewHolder).bind(userList[position])
    }

    inner class UserItemViewHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: UserData) {
            binding.tvName.text = user.name
            binding.tvEmail.text = user.email
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserData>() {
            override fun areItemsTheSame(oldItem: UserData, newItem: UserData): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: UserData, newItem: UserData): Boolean =
                oldItem.name == newItem.name && oldItem.email == newItem.email
        }
    }
}