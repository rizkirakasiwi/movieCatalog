package com.dicoding.moviecatalog.ui.home

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import com.dicoding.moviecatalog.EspressoIdlingResource
import com.dicoding.moviecatalog.MainActivity
import com.dicoding.moviecatalog.R
import com.dicoding.moviecatalog.data.genre
import com.dicoding.moviecatalog.data.repository.Repository
import com.dicoding.moviecatalog.util.DateHelper
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class HomeFragmentTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    private lateinit var instrumentationContext: Context

    @Inject
    lateinit var repository: Repository


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
    fun swipeMovieAppBar(){
        val scenarioRule = launchActivity<MainActivity>()
        onView(withId(R.id.appbar_home)).check(matches(isDisplayed()))
        onView(withId(R.id.appbar_home)).perform(swipeUp())
    }

    @Test
    fun swipeTvShowAppBar(){
        val scenarioRule = launchActivity<MainActivity>()
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.appbar_home)).check(matches(isDisplayed()))
        onView(withId(R.id.appbar_home)).perform(swipeUp())
    }

    @Test
    fun trendingMovieTest(){
        val scenarioRule = launchActivity<MainActivity>()

        onView(withId(R.id.home_fragment)).check(matches(isDisplayed()))

        onView(allOf(withParent(withId(R.id.appbar_home)), withId(R.id.rv_banner_popular)))
            .check(matches(isDisplayed()))

        onView(allOf(withParent(withId(R.id.appbar_home)), withId(R.id.rv_banner_popular)))
            .perform(scrollToPosition<BannerViewHolder>(4))
    }

    @Test
    fun trendingTvShowTest(){
        val scenarioRule = launchActivity<MainActivity>()

        onView(withText("TV SHOW")).perform(click())

        onView(withId(R.id.home_fragment)).check(matches(isDisplayed()))

        onView(allOf(withParent(withId(R.id.appbar_home)), withId(R.id.rv_banner_popular)))
            .check(matches(isDisplayed()))

        onView(allOf(withParent(withId(R.id.appbar_home)), withId(R.id.rv_banner_popular)))
            .perform(scrollToPosition<BannerViewHolder>(4))
    }
    @Test
    fun popularMovieTest(){
        val scenarioRule = launchActivity<MainActivity>()
        onView(withId(R.id.home_fragment)).check(matches(isDisplayed()))

        onView(
            allOf(
                withId(R.id.rv_home_litem),
                isDescendantOfA(
                    allOf(
                        withRecyclerView(R.id.rv_home).atPosition(0),
                        hasDescendant(
                            withText("Popular Movie")
                        )
                    )
                )
            ),
        ).check(matches(isDisplayed()))


        onView(
            allOf(
                withId(R.id.rv_home_litem),
                isDescendantOfA(
                    allOf(
                        withRecyclerView(R.id.rv_home).atPosition(0),
                        hasDescendant(
                            withText("Popular Movie")
                        )
                    )
                )
            )
        ).perform(scrollToPosition<RecyclerView.ViewHolder>(10))
    }


    @Test
    fun topRateTvShowTest(){
        val scenarioRule = launchActivity<MainActivity>()
        onView(withId(R.id.home_fragment)).check(matches(isDisplayed()))

        onView(withText("TV SHOW")).perform(click())

        Thread.sleep(2000)

        onView(
            withId(R.id.rv_home)
        ).perform(scrollToPosition<RecyclerView.ViewHolder>(1))

        onView(
            allOf(
                withId(R.id.rv_home_litem),
                isDescendantOfA(
                    allOf(
                        withRecyclerView(R.id.rv_home).atPosition(0),
                        hasDescendant(
                            withText("Top Rate Tv Show")
                        )
                    )
                )
            ),
        ).check(matches(isDisplayed()))

        onView(
            allOf(
                withId(R.id.rv_home_litem),
                isDescendantOfA(
                    allOf(
                        withRecyclerView(R.id.rv_home).atPosition(0),
                        hasDescendant(
                            withText("Top Rate Tv Show")
                        )
                    )
                )
            )
        ).perform(scrollToPosition<RecyclerView.ViewHolder>(10))
    }


    @Test
    fun latestMovieTest(){
        val scenarioRule = launchActivity<MainActivity>()
        onView(withId(R.id.home_fragment)).check(matches(isDisplayed()))
        onView(withId(R.id.appbar_home)).perform(swipeUp())

        Thread.sleep(2000)

        onView(
            withId(R.id.rv_home)
        ).perform(scrollToPosition<RecyclerView.ViewHolder>(1))

        onView(
            allOf(
                withId(R.id.rv_home_litem),
                isDescendantOfA(
                    allOf(
                        withRecyclerView(R.id.rv_home).atPosition(1),
                        hasDescendant(
                            withText("Latest Movie")
                        )
                    )
                )
            ),
        ).check(matches(isDisplayed()))

        onView(
            allOf(
                withId(R.id.rv_home_litem),
                isDescendantOfA(
                    allOf(
                        withRecyclerView(R.id.rv_home).atPosition(1),
                        hasDescendant(
                            withText("Latest Movie")
                        )
                    )
                )
            )
        ).perform(scrollToPosition<RecyclerView.ViewHolder>(10))
    }


    @Test
    fun latestTvShowTest(){
        val scenarioRule = launchActivity<MainActivity>()
        onView(withId(R.id.home_fragment)).check(matches(isDisplayed()))

        onView(withText("TV SHOW")).perform(click())

        onView(withId(R.id.appbar_home)).perform(swipeUp())

        Thread.sleep(2000)

        onView(
            withId(R.id.rv_home)
        ).perform(scrollToPosition<RecyclerView.ViewHolder>(1))

        onView(
            allOf(
                withId(R.id.rv_home_litem),
                isDescendantOfA(
                    allOf(
                        withRecyclerView(R.id.rv_home).atPosition(1),
                        hasDescendant(
                            withText("Latest Tv Show")
                        )
                    )
                )
            ),
        ).check(matches(isDisplayed()))

        onView(
            allOf(
                withId(R.id.rv_home_litem),
                isDescendantOfA(
                    allOf(
                        withRecyclerView(R.id.rv_home).atPosition(1),
                        hasDescendant(
                            withText("Latest Tv Show")
                        )
                    )
                )
            )
        ).perform(scrollToPosition<RecyclerView.ViewHolder>(10))
    }

    @Test
    fun tesTrendingMovieDetail(){
        runBlocking{

            val scenarioRule = launchActivity<MainActivity>()

            val trendingMovie = repository.trendingMovie()

            onView(allOf(withParent(withId(R.id.appbar_home)), withId(R.id.rv_banner_popular)))
                .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

            onView(withId(R.id.detail_Fragment)).check(matches(isDisplayed()))

            onView(withId(R.id.title_text)).check(matches(isDisplayed()))
            onView(withId(R.id.title_text)).check(matches(withText(trendingMovie.data?.results?.get(0)?.title)))

            onView(withId(R.id.rating_text)).check(matches(isDisplayed()))
            onView(withId(R.id.rating_text)).check(matches(withText(trendingMovie.data?.results?.get(0)?.vote_average.toString())))

            onView(withId(R.id.description_text)).check(matches(isDisplayed()))
            onView(withId(R.id.description_text)).check(matches(withText(trendingMovie.data?.results?.get(0)?.overview)))

            onView(withId(R.id.ratingbar_detail)).check(matches(isDisplayed()))

            val genre = trendingMovie.data?.results?.get(0)?.genre_ids?.let { getGenre(it).joinToString() }
            if (trendingMovie.data?.results?.get(0)?.release_date != null) {
                val year: String? = DateHelper.formatDate(trendingMovie.data?.results?.get(0)?.release_date)
                onView(withId(R.id.genre_year_text)).check(matches(isDisplayed()))
                onView(withId(R.id.genre_year_text)).check(matches(withText("$genre, $year")))
            }else{
                onView(withId(R.id.genre_year_text)).check(matches(isDisplayed()))
                onView(withId(R.id.genre_year_text)).check(matches(withText(genre)))
            }
        }
    }

    @Test
    fun popularMovieDetail(){
        runBlocking{

            val scenarioRule = launchActivity<MainActivity>()

            val trendingMovie = repository.popularMovie()

            onView(
                allOf(
                    withId(R.id.rv_home_litem),
                    isDescendantOfA(
                        allOf(
                            withRecyclerView(R.id.rv_home).atPosition(0),
                            hasDescendant(
                                withText("Popular Movie")
                            )
                        )
                    )
                )
            ).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

            onView(withId(R.id.detail_Fragment)).check(matches(isDisplayed()))

            onView(withId(R.id.title_text)).check(matches(isDisplayed()))
            onView(withId(R.id.title_text)).check(matches(withText(trendingMovie.data?.results?.get(0)?.title)))

            onView(withId(R.id.rating_text)).check(matches(isDisplayed()))
            onView(withId(R.id.rating_text)).check(matches(withText(trendingMovie.data?.results?.get(0)?.vote_average.toString())))

            onView(withId(R.id.description_text)).check(matches(isDisplayed()))
            onView(withId(R.id.description_text)).check(matches(withText(trendingMovie.data?.results?.get(0)?.overview)))

            onView(withId(R.id.ratingbar_detail)).check(matches(isDisplayed()))

            val genre = trendingMovie.data?.results?.get(0)?.genre_ids?.let { getGenre(it).joinToString() }
            if (trendingMovie.data?.results?.get(0)?.release_date != null) {
                val year: String? = DateHelper.formatDate(trendingMovie.data?.results?.get(0)?.release_date)
                onView(withId(R.id.genre_year_text)).check(matches(isDisplayed()))
                onView(withId(R.id.genre_year_text)).check(matches(withText("$genre, $year")))
            }else{
                onView(withId(R.id.genre_year_text)).check(matches(isDisplayed()))
                onView(withId(R.id.genre_year_text)).check(matches(withText(genre)))
            }
        }
    }

    @Test
    fun topRateTvShowDetail(){
        runBlocking{

            val scenarioRule = launchActivity<MainActivity>()

            val trendingMovie = repository.topRateTvShow()

            onView(withText("TV SHOW")).perform(click())

            Thread.sleep(2000)

            onView(
                allOf(
                    withId(R.id.rv_home_litem),
                    isDescendantOfA(
                        allOf(
                            withRecyclerView(R.id.rv_home).atPosition(0),
                            hasDescendant(
                                withText("Top Rate Tv Show")
                            )
                        )
                    )
                )
            ).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

            onView(withId(R.id.detail_Fragment)).check(matches(isDisplayed()))

            onView(withId(R.id.title_text)).check(matches(isDisplayed()))
            onView(withId(R.id.title_text)).check(matches(withText(trendingMovie.data?.results?.get(0)?.title ?: trendingMovie.data?.results?.get(0)?.name)))

            onView(withId(R.id.rating_text)).check(matches(isDisplayed()))
            onView(withId(R.id.rating_text)).check(matches(withText(trendingMovie.data?.results?.get(0)?.vote_average.toString())))

            onView(withId(R.id.description_text)).check(matches(isDisplayed()))
            onView(withId(R.id.description_text)).check(matches(withText(trendingMovie.data?.results?.get(0)?.overview)))

            onView(withId(R.id.ratingbar_detail)).check(matches(isDisplayed()))

            val genre = trendingMovie.data?.results?.get(0)?.genre_ids?.let { getGenre(it).joinToString() }
            if (trendingMovie.data?.results?.get(0)?.release_date != null) {
                val year: String? = DateHelper.formatDate(trendingMovie.data?.results?.get(0)?.release_date)
                onView(withId(R.id.genre_year_text)).check(matches(isDisplayed()))
                onView(withId(R.id.genre_year_text)).check(matches(withText("$genre, $year")))
            }else{
                onView(withId(R.id.genre_year_text)).check(matches(isDisplayed()))
                onView(withId(R.id.genre_year_text)).check(matches(withText(genre)))
            }
        }
    }

    @Test
    fun latestMovieDetailTest(){
        runBlocking{

            val scenarioRule = launchActivity<MainActivity>()

            val trendingMovie = repository.latestMovie()

            Thread.sleep(2000)

            onView(withId(R.id.appbar_home)).perform(swipeUp())

            onView(
                withId(R.id.rv_home)
            ).perform(scrollToPosition<RecyclerView.ViewHolder>(1))

            onView(
                allOf(
                    withId(R.id.rv_home_litem),
                    isDescendantOfA(
                        allOf(
                            withRecyclerView(R.id.rv_home).atPosition(1),
                            hasDescendant(
                                withText("Latest Movie")
                            )
                        )
                    )
                )
            ).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

            onView(withId(R.id.detail_Fragment)).check(matches(isDisplayed()))



            onView(withId(R.id.title_text)).check(matches(isDisplayed()))
            onView(withId(R.id.title_text)).check(matches(withText(trendingMovie.data?.results?.get(0)?.title ?: trendingMovie.data?.results?.get(0)?.name)))

            onView(withId(R.id.rating_text)).check(matches(isDisplayed()))
            onView(withId(R.id.rating_text)).check(matches(withText(trendingMovie.data?.results?.get(0)?.vote_average.toString())))

            onView(withId(R.id.description_text)).check(matches(isDisplayed()))
            onView(withId(R.id.description_text)).check(matches(withText(trendingMovie.data?.results?.get(0)?.overview)))

            onView(withId(R.id.ratingbar_detail)).check(matches(isDisplayed()))

            val genre = trendingMovie.data?.results?.get(0)?.genre_ids?.let { getGenre(it).joinToString() }
            if (trendingMovie.data?.results?.get(0)?.release_date != null) {
                val year: String? = DateHelper.formatDate(trendingMovie.data?.results?.get(0)?.release_date)
                onView(withId(R.id.genre_year_text)).check(matches(isDisplayed()))
                onView(withId(R.id.genre_year_text)).check(matches(withText("$genre, $year")))
            }else{
                onView(withId(R.id.genre_year_text)).check(matches(isDisplayed()))
                onView(withId(R.id.genre_year_text)).check(matches(withText(genre)))
            }
        }
    }


    @Test
    fun latestTvShowDetailTest(){
        runBlocking{

            val scenarioRule = launchActivity<MainActivity>()

            val trendingMovie = repository.latestTvShow()
            onView(withText("TV SHOW")).perform(click())

            onView(withId(R.id.appbar_home)).perform(swipeUp())

            Thread.sleep(2000)

            onView(
                withId(R.id.rv_home)
            ).perform(scrollToPosition<RecyclerView.ViewHolder>(1))

            onView(
                allOf(
                    withId(R.id.rv_home_litem),
                    isDescendantOfA(
                        allOf(
                            withRecyclerView(R.id.rv_home).atPosition(1),
                            hasDescendant(
                                withText("Latest Tv Show")
                            )
                        )
                    )
                )
            ).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

            onView(withId(R.id.detail_Fragment)).check(matches(isDisplayed()))



            onView(withId(R.id.title_text)).check(matches(isDisplayed()))
            onView(withId(R.id.title_text)).check(matches(withText(trendingMovie.data?.results?.get(0)?.title ?: trendingMovie.data?.results?.get(0)?.name)))

            onView(withId(R.id.rating_text)).check(matches(isDisplayed()))
            onView(withId(R.id.rating_text)).check(matches(withText(trendingMovie.data?.results?.get(0)?.vote_average.toString())))

            onView(withId(R.id.description_text)).check(matches(isDisplayed()))
            onView(withId(R.id.description_text)).check(matches(withText(trendingMovie.data?.results?.get(0)?.overview)))

            onView(withId(R.id.ratingbar_detail)).check(matches(isDisplayed()))

            val genre = trendingMovie.data?.results?.get(0)?.genre_ids?.let { getGenre(it).joinToString() }
            if (trendingMovie.data?.results?.get(0)?.release_date != null) {
                val year: String? = DateHelper.formatDate(trendingMovie.data?.results?.get(0)?.release_date)
                onView(withId(R.id.genre_year_text)).check(matches(isDisplayed()))
                onView(withId(R.id.genre_year_text)).check(matches(withText("$genre, $year")))
            }else{
                onView(withId(R.id.genre_year_text)).check(matches(isDisplayed()))
                onView(withId(R.id.genre_year_text)).check(matches(withText(genre)))
            }
        }
    }

    private fun withRecyclerView(recyclerViewId: Int): RecyclerViewMatcher {
        return RecyclerViewMatcher(recyclerViewId)
    }

    private fun getGenre(value:List<Int>):List<String?>{
        val list = mutableListOf<String?>()
        value.forEach {
            list.add(genre[it])
        }
        return list
    }
}