package com.vero.testapp.ui

import androidx.databinding.ObservableBoolean
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vero.testapp.model.RepoItem
import com.vero.testapp.model.RepoItemResponse
import com.vero.testapp.repository.RepoItemRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers

class MainViewModel @ViewModelInject constructor(
    private val repoItemRepository: RepoItemRepository
) : ViewModel() {

    val searchText = MutableLiveData("")
    val isLoading = ObservableBoolean(false)

    val repoFetchingLiveData: MutableLiveData<List<RepoItem>> = MutableLiveData()

    private lateinit var disposable: Disposable

    fun fetchRepoItem() {
        searchText.value?.let {
            if (it.isNotEmpty()) {
                disposable = Observable.zip(
                    getObservable(it, 1),
                    getObservable(it, 2),
                    BiFunction<RepoItemResponse, RepoItemResponse, Pair<RepoItemResponse, RepoItemResponse>> { list1, list2 ->
                        return@BiFunction Pair(list1, list2)
                    }).doOnSubscribe {
                    isLoading.set(true)
                }.doOnError {
                    isLoading.set(false)
                }.subscribe { pair ->
                    repoFetchingLiveData.postValue(getSortedList(pair))
                    isLoading.set(false)
                }
            }
        }
    }

    private fun getObservable(textSearch: String, page: Int) =
        repoItemRepository.fetchRepoItem(textSearch, PER_PAGE_SIZE, page)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }

    private fun getSortedList(pair: Pair<RepoItemResponse, RepoItemResponse>) =
        mutableListOf<RepoItem>().apply {
            addAll(pair.first.items)
            addAll(pair.second.items)
            sortByDescending { it.stars }
        }

    companion object {
        private const val PER_PAGE_SIZE = 15
    }
}