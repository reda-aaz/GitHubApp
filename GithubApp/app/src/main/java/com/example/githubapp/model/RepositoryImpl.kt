package com.example.githubapp.model

import com.example.githubapp.model.data.GithubRepo
import com.example.githubapp.model.remote.RestService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

open class RepositoryImpl(private val restService: RestService) : Repository {

    override fun getGithubRepo(): Single<List<GithubRepo>> {
        return restService
            .getGithubRepo()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}