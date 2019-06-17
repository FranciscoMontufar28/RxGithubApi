package com.practice.francisco.rxretrofit

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.practice.francisco.rxretrofit.adapter.GithubRepoAdapter
import com.practice.francisco.rxretrofit.model.Repo
import com.practice.francisco.rxretrofit.network.GithubApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_my_stars_repos.*

class MyStarsRepos : AppCompatActivity() {

    val repoList = ArrayList<Repo>()
    private lateinit var repoAdapter : GithubRepoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_stars_repos)

        val linearLayoutManager = LinearLayoutManager(applicationContext)
        val divider = DividerItemDecoration(myStarsList.context, DividerItemDecoration.VERTICAL)
        myStarsList.layoutManager = linearLayoutManager
        myStarsList.addItemDecoration(divider)

        repoAdapter = GithubRepoAdapter()

        /*repoList.add(Repo("RxJava","Rx is amazign","English"))
        repoList.add(Repo("RxJava","Rx is amazign","English"))
        repoList.add(Repo("RxJava","Rx is amazign","English"))
        repoList.add(Repo("RxJava","Rx is amazign","English"))
        repoList.add(Repo("RxJava","Rx is amazign","English"))
        repoList.add(Repo("RxJava","Rx is amazign","English"))*/

        //repoAdapter.addRepos(repoList)
        myStarsList.adapter = repoAdapter

        getStarredRepos()
    }

    fun getStarredRepos(){
        GithubApiClient.getGithubService().getStarredRepos("mrabelwahed")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                it->
                    repoAdapter.addRepos(it)
            }
    }
}
