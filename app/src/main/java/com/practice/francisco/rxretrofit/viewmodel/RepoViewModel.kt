package com.practice.francisco.rxretrofit.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.practice.francisco.rxretrofit.model.Repo
import com.practice.francisco.rxretrofit.network.GithubApiClient
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RepoViewModel:ViewModel() {

    val compositeDisposablete = CompositeDisposable()
    val repoLiveData = MutableLiveData<ArrayList<Repo>>()

    fun getMyStarsRepos(username:String){
        val repoDisposable = GithubApiClient.getGithubService().getStarredRepos(username)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                    it-> repoLiveData.value = it
                //repoAdapter.addRepos(it)
            }
        compositeDisposablete.add(repoDisposable)
    }

    fun getLiveData():LiveData<ArrayList<Repo>> = repoLiveData

    override fun onCleared() {
        super.onCleared()
        compositeDisposablete.clear()
    }
}