package com.vero.testapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.vero.testapp.R
import com.vero.testapp.databinding.ItemRepoBinding
import com.vero.testapp.model.RepoItem

class RepoItemAdapter : RecyclerView.Adapter<RepoItemAdapter.RepoViewHolder>() {

    private val items: MutableList<RepoItem> = mutableListOf()

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.binding.apply {
            itemRepo = items[position]
            executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ItemRepoBinding>(inflater, R.layout.item_repo, parent, false)
        return RepoViewHolder(binding)
    }

    fun updateRepoItemList(repoItemList: List<RepoItem>) {
        items.clear()
        items.addAll(repoItemList)
        notifyDataSetChanged()
    }

    class RepoViewHolder(val binding: ItemRepoBinding) : RecyclerView.ViewHolder(binding.root)
}