package com.example.githubapp.viewmodel

import android.view.View
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.githubapp.common.mock
import com.example.githubapp.common.whenever
import com.example.githubapp.model.Repository
import com.example.githubapp.model.data.GithubRepo
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import java.net.UnknownHostException

class GithubRepoViewModelTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    val repository = mock<Repository>()
    val observerState = mock<Observer<Int>>()
    val observerList = mock<Observer<List<GithubRepo>>>()

    val viewmodel by lazy { GithubRepoViewModel(repository) }

    @Before
    fun initTest() {
        Mockito.reset(repository, observerState)
    }

    @Test
    fun `hasInternet_getGithubRepo_getGithubRepoList`() {
        val response = arrayListOf(GithubRepo(1, "description", "name"))
        whenever(repository.getGithubRepo())
            .thenReturn(Single.just(response))

        viewmodel.listVisibilityLiveData.observeForever(observerState)
        viewmodel.stateLiveData.observeForever(observerList)

        viewmodel.getGithubRepo()
        assertEquals(viewmodel.listVisibilityLiveData.value, View.VISIBLE)
        assertEquals(viewmodel.stateLiveData.value?.size, 1)
    }

    @Test
    fun `noInternet_getDefinitionsUnknownHostException_getErrorNoInternetConnection`() {
        whenever(repository.getGithubRepo())
            .thenReturn(Single.error(UnknownHostException()))

        viewmodel.listVisibilityLiveData.observeForever(observerState)
        viewmodel.progressBarVisibilityLiveData.observeForever(observerState)
        viewmodel.errorVisibilityLiveData.observeForever(observerState)
        viewmodel.stateLiveData.observeForever(observerList)

        viewmodel.getGithubRepo()

        assertEquals(viewmodel.listVisibilityLiveData.value, View.GONE)
        assertEquals(viewmodel.progressBarVisibilityLiveData.value, View.GONE)
        assertEquals(viewmodel.errorVisibilityLiveData.value, View.VISIBLE)
        assertEquals(viewmodel.errorMessagLiveData.value,"No Internet Connection")
        assertEquals(viewmodel.stateLiveData.value, null)
    }

}