package com.ranga.topgithub.reposmodule.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ranga.topgithub.data.model.GitRepo
import com.ranga.topgithub.data.manager.GitReposRepository
import com.ranga.topgithub.data.model.Result
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RepoListViewModel @Inject constructor(
    private val reposRepository: GitReposRepository
): ViewModel() {

    private val reposListLiveData = MutableLiveData<Result<List<GitRepo>>>()

    fun fetchRepoList(forceUpdate: Boolean){
        reposRepository.getRepos(forceUpdate)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{ reposListLiveData.postValue(it)}
    }

    fun getRepoList(): LiveData<Result<List<GitRepo>>> = reposListLiveData
}