package com.vero.testapp.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vero.testapp.adapter.RepoItemAdapter
import com.vero.testapp.model.RepoItem

@BindingAdapter("adapter")
fun bindAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}

@BindingAdapter("adapterRepoItemList")
fun bindAdapterRepoItemList(view: RecyclerView, repoItemList: List<RepoItem>?) {
    repoItemList?.let {
        (view.adapter as? RepoItemAdapter)?.updateRepoItemList(it)
    }
}