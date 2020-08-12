package com.example.githubapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubapp.R
import com.example.githubapp.databinding.ActivityMainBinding
import com.example.githubapp.inject.Injection
import com.example.githubapp.viewmodel.GithubRepoViewModel
import com.example.githubapp.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: GithubRepoViewModel
    val injection = Injection()
    private var binding: ActivityMainBinding? = null
    var wordsAdapter: GithubRepoAdapter = GithubRepoAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding?.repos?.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.HORIZONTAL, false)

        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(injection.provideUserRepo())
        ).get(GithubRepoViewModel::class.java)

        binding?.let {
            it.viewModel = viewModel
        }
        viewModel.initialState()

        viewModel.stateLiveData.observe(this, Observer { list ->
            wordsAdapter.update(list)
        })

        setRecyclerViewLinearLayout()
        viewModel.getGithubRepo()
    }

    private fun setRecyclerViewLinearLayout() {
        repos.layoutManager = LinearLayoutManager(this)
        repos.adapter = wordsAdapter
    }
}