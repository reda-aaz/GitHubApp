package com.example.githubapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapp.R
import com.example.githubapp.databinding.RepoItemBinding
import com.example.githubapp.model.data.GithubRepo

class GithubRepoAdapter : RecyclerView.Adapter<GithubRepoViewHolder>() {

    private var repos: List<GithubRepo> = mutableListOf()

    override fun getItemCount() = repos.size

    override fun onBindViewHolder(holder: GithubRepoViewHolder, position: Int) {
        holder.bindItem(repos[position])
    }

    fun update(words: List<GithubRepo>) {
        this.repos = words
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubRepoViewHolder {
        val binding: RepoItemBinding = DataBindingUtil.inflate(LayoutInflater.
        from(parent.context), R.layout.repo_item, parent, false)
        return GithubRepoViewHolder(binding)
    }
}