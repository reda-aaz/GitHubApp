package com.example.githubapp.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubapp.model.Repository
import com.example.githubapp.model.data.GithubRepo
import com.example.githubapp.resources.EspressoIdlingResource
import io.reactivex.disposables.CompositeDisposable
import java.net.UnknownHostException

class GithubRepoViewModel(private val repository: Repository): ViewModel() {
    private val disposable = CompositeDisposable()

    private val progressBarVisibilityMutableLiveData = MutableLiveData<Int>()
    val progressBarVisibilityLiveData: LiveData<Int>
        get() = progressBarVisibilityMutableLiveData

    private val stateMutableLiveData = MutableLiveData<List<GithubRepo>>()
    val stateLiveData: LiveData<List<GithubRepo>>
        get() = stateMutableLiveData

    private val listVisibilityMutableLiveData = MutableLiveData<Int>()
    val listVisibilityLiveData: LiveData<Int>
        get() = listVisibilityMutableLiveData
    private val errorVisibilityMutableLiveData = MutableLiveData<Int>()
    val errorVisibilityLiveData: LiveData<Int>
        get() = errorVisibilityMutableLiveData
    private val errorMessageMutableLiveData = MutableLiveData<String>()
    val errorMessagLiveData: LiveData<String>
        get() = errorMessageMutableLiveData

    private var loaded = false

    fun getGithubRepo() {
        displayLoading()
        EspressoIdlingResource.increment() // stops Espresso tests from going forward
        disposable.add(
            repository
                .getGithubRepo()
                .subscribe({
                    loaded = true
                    if (it.isEmpty()) {
                        displayMessage("No Data Retrieved")
                    } else {
                        stateMutableLiveData.value = it
                        displayRepos()
                    }
                    EspressoIdlingResource.decrement() // Tells Espresso test to resume
                }, {
                    loaded = true
                    //errors
                    val errorString = when (it) {
                        is UnknownHostException -> "No Internet Connection"
                        else -> it.localizedMessage
                    }
                    displayMessage(errorString)
                    EspressoIdlingResource.decrement() // Tells Espresso test to resume
                })
        )
    }

    fun initialState() {
        progressBarVisibilityMutableLiveData.value = View.GONE
        errorVisibilityMutableLiveData.value = View.GONE
    }

    private fun displayRepos() {
        // set correct visible element
        listVisibilityMutableLiveData.value = View.VISIBLE
        initialState()
    }

    private fun displayLoading() {
        // set correct visible element
        progressBarVisibilityMutableLiveData.value = View.VISIBLE
        listVisibilityMutableLiveData.value = View.GONE
        errorVisibilityMutableLiveData.value = View.GONE
    }

    private fun displayMessage(message: String) {
        // set correct visible element
        progressBarVisibilityMutableLiveData.value = View.GONE
        listVisibilityMutableLiveData.value = View.GONE
        errorVisibilityMutableLiveData.value = View.VISIBLE
        //set message
        errorMessageMutableLiveData.value = message
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}