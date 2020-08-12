package com.example.githubapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubapp.model.data.GithubRepo

class ItemViewModel : ViewModel() {
    val description = MutableLiveData<String>()
    val name = MutableLiveData<String>()

    fun bind(githubRepo: GithubRepo) {
        description.value = githubRepo.description
        name.value = githubRepo.name
    }
}