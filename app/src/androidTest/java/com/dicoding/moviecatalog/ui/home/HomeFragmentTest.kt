package com.dicoding.moviecatalog.ui.home

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import com.dicoding.moviecatalog.EspressoIdlingResource
import com.dicoding.moviecatalog.MainActivity
import com.dicoding.moviecatalog.R
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matchers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class HomeFragmentTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    lateinit var instrumentationContext: Context


    @Before
    fun setup(){
        hiltRule.inject()
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
        instrumentationContext = InstrumentationRegistry.getInstrumentation().context
    }

    @After
    fun tearDown(){
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun trendingListViewTest(){
        val scenarioRule = launchActivity<MainActivity>()

        onView(withId(R.id.home_fragment)).check(matches(isDisplayed()))

        onView(allOf(withParent(withId(R.id.appbar_home)), withId(R.id.rv_banner_popular)))
            .check(matches(isDisplayed()))

        onView(allOf(withParent(withId(R.id.appbar_home)), withId(R.id.rv_banner_popular)))
            .perform(scrollToPosition<BannerViewHolder>(4))
    }

    @Test
    fun popularMovie(){
        val scenarioRule = launchActivity<MainActivity>()
        onView(withId(R.id.home_fragment)).check(matches(isDisplayed()))

        onView(
            allOf(
                withId(R.id.rv_home_layout),
                isDescendantOfA(
                    withId(R.id.rv_home)
                )
            )
        ).check(matches(isDisplayed()))

        onView(
            allOf(
                withId(R.id.rv_home_layout),
                isDescendantOfA(
                    withId(R.id.rv_home)
                )
            )
        ).perform(scrollToPosition<RecyclerView.ViewHolder>(10))
    }
}