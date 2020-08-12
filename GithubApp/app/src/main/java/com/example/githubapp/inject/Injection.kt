package com.example.githubapp.inject

import com.example.githubapp.model.Repository
import com.example.githubapp.model.RepositoryImpl
import com.example.githubapp.model.remote.RestService

class Injection {
    private var restService: RestService? = null

    fun provideUserRepo(): Repository {
        return RepositoryImpl(provideRestService())
    }

    private fun provideRestService(): RestService {
        if (restService == null) {
            restService = RestService.instance
        }
        return restService as RestService
    }
}