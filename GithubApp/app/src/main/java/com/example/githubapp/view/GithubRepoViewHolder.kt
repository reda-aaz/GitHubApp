package com.example.githubapp.view

import androidx.recyclerview.widget.RecyclerView
import com.example.githubapp.databinding.RepoItemBinding
import com.example.githubapp.model.data.GithubRepo
import com.example.githubapp.viewmodel.ItemViewModel

class GithubRepoViewHolder(private val binding: RepoItemBinding) :  RecyclerView.ViewHolder(binding.root) {
    private val itemViewModel = ItemViewModel()

    fun bindItem(githubRepo: GithubRepo) {
        itemViewModel.bind(githubRepo)
        binding.viewModel = itemViewModel
    }
}