package com.practice.francisco.rxretrofit.network

import com.practice.francisco.rxretrofit.model.Repo
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import kotlin.collections.ArrayList

interface GithubService {
    @GET("users/{user}/starred")
    fun getStarredRepos(@Path("user")userName:String):Observable<ArrayList<Repo>>
}