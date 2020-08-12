package com.example.githubapp.view

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.githubapp.R
import com.example.githubapp.common.CustomAssertions.Companion.hasItemCount
import com.example.githubapp.common.CustomMatchers.Companion.withRecyclerView
import com.example.githubapp.resources.EspressoIdlingResource
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java, false, false)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getIdlingResource())
    }

    @Test
    fun onLaunchCheckAmountInputIsDisplayed() {
        // launch activity
        ActivityScenario.launch(MainActivity::class.java)

        // check there are 10 items
        Espresso
            .onView(withId(R.id.repos))
            .check(hasItemCount(30))

        // Check item at position 0 has "The greatest [video game]/[card game]/[tv show] of all time"
        Espresso
            .onView(withRecyclerView(R.id.repos)
            ?.atPosition(0))
            .check(ViewAssertions
                    .matches(ViewMatchers
                        .hasDescendant(ViewMatchers
                            .withText("yajl-objc"))))
    }
}