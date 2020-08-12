package com.example.githubapp.model

import com.example.githubapp.model.data.GithubRepo
import io.reactivex.Single

interface Repository {
    fun getGithubRepo(): Single<List<GithubRepo>>
}