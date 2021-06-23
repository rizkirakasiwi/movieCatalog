package com.dicoding.moviecatalog.data.dummy

import com.dicoding.moviecatalog.data.Discover
import com.dicoding.moviecatalog.data.Result
import com.dicoding.moviecatalog.data.ResultData

object DiscoverDummy {
    fun getDiscover(): ResultData<Discover?> {
        return ResultData(
            true,
            null,
            Discover(
                page = 1,
                results = listOf(
                    Result(
                        adult = false,
                        backdrop_path = "/8ChCpCYxh9YXusmHwcE9YzP0TSG.jpg",
                        genre_ids = listOf(
                            35,
                            80
                        ),
                        id = 337404,
                        original_language = "en",
                        original_title = "Cruella",
                        overview = "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella.",
                        popularity = 3842.377,
                        poster_path = "/rTh4K5uw9HypmpGslcKd4QfHl93.jpg",
                        release_date = "2021-05-26",
                        title = "Cruella",
                        video = false,
                        vote_average = 8.6,
                        vote_count = 2892
                    )
                ),
                total_pages = 500,
                total_results = 100000,
                status_message = null,
                status_code = null
            )
        )
    }
}