package com.vero.testapp.repository

import com.vero.testapp.network.RepoItemService
import javax.inject.Inject

class RepoItemRepository @Inject constructor(
    private val repoItemService: RepoItemService
) {
    fun fetchRepoItem(searchText: String, per_page: Int, page: Int)
            = repoItemService.fetchRepoItemList(searchText, per_page, page)
}