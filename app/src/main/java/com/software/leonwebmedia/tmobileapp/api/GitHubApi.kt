package com.software.leonwebmedia.tmobileapp.api

import com.software.leonwebmedia.tmobileapp.model.ObjectResults
import com.software.leonwebmedia.tmobileapp.model.ObjectUser
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApi {
    @GET("/orgs/octokit/repos")
    fun getRepos() : Call<List<ObjectUser>>

    @GET("users/{login}/repos")
    fun getRepoDetails(@Path("login") login : String) : Call<List<ObjectUser>>

    @GET("/users/{username}")
    fun getUsers(@Path("username") usename : String) : Call<ObjectUser>

}