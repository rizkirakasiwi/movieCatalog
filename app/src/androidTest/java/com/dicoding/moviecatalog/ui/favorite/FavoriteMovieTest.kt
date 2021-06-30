package com.dicoding.moviecatalog.ui.favorite

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import com.dicoding.moviecatalog.EspressoIdlingResource
import com.dicoding.moviecatalog.MainActivity
import com.dicoding.moviecatalog.R
import com.dicoding.moviecatalog.data.repository.FavoriteRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class FavoriteMovieTest{
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    private lateinit var instrumentationContext: Context
    
    @Inject
    lateinit var favoriteRepository: FavoriteRepository


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
    fun favoriteMovie(){
        runBlocking{
            val scenarioRule = launchActivity<MainActivity>()
            val check = favoriteRepository.selectMovie()

            launch(Dispatchers.Main){
                check.observeOnce {
                    if (it.isNullOrEmpty()) {
                        onView(
                            allOf(
                                withParent(withId(R.id.appbar_home)), withId(
                                    R.id.rv_banner_popular)
                            )
                        ).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

                        onView(withId(R.id.detail_Fragment))
                            .check(matches(isDisplayed()))
                        onView(withId(R.id.fav_button)).perform(click())
                        pressBack()
                    }
                }
            }


            onView(withId(R.id.fav_button)).check(matches(isDisplayed()))
            
            onView(withId(R.id.fav_button)).perform(click())
            
            onView(withId(R.id.favorite_fragment)).check(matches(isDisplayed()))
            
            onView(withId(R.id.fav_recyclerview))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
            
            val mfav = favoriteRepository.selectMovie()
            onView(withId(R.id.detail_Fragment)).check(matches(isDisplayed()))

            launch(Dispatchers.Main) {
                mfav.observeOnce { fav ->
                    onView(withId(R.id.title_text)).check(matches(isDisplayed()))
                    onView(withId(R.id.title_text)).check(matches(withText(fav.get(0)?.title)))

                    onView(withId(R.id.rating_text)).check(matches(isDisplayed()))
                    onView(withId(R.id.rating_text)).check(matches(withText(fav.get(0)?.vote_average.toString())))

                    onView(withId(R.id.description_text)).check(matches(isDisplayed()))
                    onView(withId(R.id.description_text)).check(matches(withText(fav.get(0)?.overview)))

                    onView(withId(R.id.ratingbar_detail)).check(matches(isDisplayed()))
                }
            }

            
        }
    }


    fun <T> LiveData<T>.observeOnce(observer: (T) -> Unit) {
        observeForever(object: Observer<T> {
            override fun onChanged(value: T) {
                removeObserver(this)
                observer(value)
            }
        })
    }
    
}