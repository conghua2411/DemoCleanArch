package com.example.democleanarch.ui.listDetail

import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.democleanarch.databinding.ItemUserBinding
import com.example.presentation.model.UserData

class ListDetailAdapter(private val listData: MutableList<UserData>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var itemListenner: ItemListenner? = null

    private var func: ((UserData)->Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val binding = ItemUserBinding.inflate(layoutInflater, parent, false)

        return UserViewHolder(binding)
    }

    override fun getItemCount(): Int = listData.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as UserViewHolder).onBind(listData[position])
    }

    fun setItemListener(itemListenner: ItemListenner) {
        this.itemListenner = itemListenner
    }

    fun setFunc(func: (UserData)->Unit) {
        this.func = func
    }

    fun addAll(list: List<UserData>) {
        listData.clear()
        listData.addAll(list)
        notifyDataSetChanged()
    }

    fun clear() {
        listData.clear()
        notifyDataSetChanged()
    }

    interface ItemListenner {
        fun onLongClick(userData: UserData)
    }

    inner class UserViewHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(userData: UserData) {
            binding.tvName.text = userData.name
            binding.tvEmail.text = userData.email

            binding.root.setOnLongClickListener {
//                itemListenner?.onLongClick(userData)
                func?.invoke(userData)
                true
            }
        }
    }
}