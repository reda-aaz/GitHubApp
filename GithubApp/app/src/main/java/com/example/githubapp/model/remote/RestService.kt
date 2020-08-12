package com.example.githubapp.model.remote

import com.example.githubapp.model.data.GithubRepo
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface RestService {
    companion object {
        val instance: RestService by lazy {
            //Logging
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            //OkHttp
            val okHttpClient = OkHttpClient.Builder()
                .readTimeout(10000, TimeUnit.MILLISECONDS)
                .writeTimeout(1000, TimeUnit.MILLISECONDS)
                .addInterceptor(loggingInterceptor)
                .build()
            //Retrofit
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/orgs/square/")
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            retrofit.create(RestService::class.java)
        }
    }

    @GET("repos")
    fun getGithubRepo(): Single<List<GithubRepo>>
}