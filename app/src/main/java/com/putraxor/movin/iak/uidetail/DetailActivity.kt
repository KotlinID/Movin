package com.putraxor.movin.iak.uimovie

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.TabLayout
import android.support.v4.widget.SwipeRefreshLayout
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.putraxor.movin.R
import com.putraxor.movin.iak.MovinConfig
import com.putraxor.movin.iak.base.BaseLifecycleActivity
import com.putraxor.movin.iak.data.movie.Movie
import com.putraxor.movin.iak.data.movie.MovieLocalDS
import com.putraxor.movin.iak.data.review.Review
import com.putraxor.movin.iak.data.trailer.Trailer
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.doAsync

class DetailActivity : BaseLifecycleActivity<DetailVM>(),
        SwipeRefreshLayout.OnRefreshListener {

    override val vmClass = DetailVM::class.java

    private val detailAdapter = DetailAdapter()
    private val reviewAdapter = ReviewAdapter()
    private val trailerAdapter = TrailerAdapter()

    val movieDS = MovieLocalDS()
    var movie: Movie? = null

    private var currentFilter: Long = 0
    private var currentPosition: Int = OVERVIEW

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        setContentView(R.layout.activity_detail)
        rvMovies.setHasFixedSize(true)
        val animateAdapter = AlphaInAnimationAdapter(detailAdapter)
        animateAdapter.setDuration(1000)
        rvMovies.adapter = animateAdapter
        swipeRefresh.setOnRefreshListener(this)
        swipeRefresh.setColorSchemeResources(
                R.color.colorPrimary,
                R.color.colorPrimaryDark,
                R.color.colorPrimaryDarker)
        intent?.let {
            val movieId = intent.getLongExtra("extra", 0)
            //Toast.makeText(this, "Terima extra movieId = $movieId", Toast.LENGTH_SHORT).show()
            currentFilter = movieId
            viewModel.setFilter(movieId)
        }
        toolbar.title = "Movie Detail"
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setupTablayout()
        observeLiveData()
    }

    /**
     * Inflate option menu
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item?.let {
            if (item.itemId == R.id.favorite_menu) {
                when (item.title) {
                    "Add to Favorite" -> {
                        doAsync {
                            if (movie != null) {
                                val m = movie as Movie
                                m.favorite = 1
                                movieDS.saveMovie(m)
                            }
                        }
                        Toast.makeText(this, "Adding to favorite", Toast.LENGTH_SHORT).show()

                    }
                    "Remove from Favorite" -> Toast.makeText(this, "Removing from favorite", Toast.LENGTH_SHORT).show()
                }
            } else if (item.itemId == android.R.id.home) {
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * Observer live data
     */
    private fun observeLiveData() {
        viewModel.isLoadingLiveData.observe(this, Observer<Boolean> {
            it?.let { swipeRefresh.isRefreshing = it }
        })
        viewModel.detailRepoLD.observe(this, Observer<List<Movie>> {
            it?.let {
                it[0].let {
                    supportActionBar?.title = it.originalTitle
                    detailParalax.setImageURI("${MovinConfig.IMG_BASE_URL}${it.posterPath}")
                }
                movie = it[0]
                detailAdapter.dataSource = it
            }
        })

        viewModel.reviewRepoLD.observe(this, Observer<List<Review>> {
            it?.let {
                reviewAdapter.dataSource = it
            }
        })

        viewModel.trailerRepoLD.observe(this, Observer<List<Trailer>> {
            it?.let {
                trailerAdapter.dataSource = it
            }
        })

        viewModel.throwableLiveData.observe(this, Observer<Throwable> {
            it?.let { Snackbar.make(rvMovies, "Can not dispatch live data", Snackbar.LENGTH_LONG).show() }
        })
    }

    override fun onRefresh() {
        viewModel.setFilter(currentFilter)
    }

    fun changeAdapter() {
        when (currentPosition) {
            OVERVIEW -> rvMovies.adapter = detailAdapter
            REVIEWS -> rvMovies.adapter = reviewAdapter
            TRAILERS -> rvMovies.adapter = trailerAdapter
        }
    }


    private fun setupTablayout() {
        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {}

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position ?: 0) {
                    0 -> currentPosition = OVERVIEW
                    1 -> currentPosition = REVIEWS
                    2 -> currentPosition = TRAILERS
                }
                changeAdapter()
                onRefresh()
            }
        })
    }

    companion object {
        val OVERVIEW = 0
        val REVIEWS = 1
        val TRAILERS = 2
    }
}
