package com.putraxor.movin.iak.uiintro

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.putraxor.movin.R
import com.putraxor.movin.iak.data.movie.MovieRepository
import com.putraxor.movin.iak.uimovie.MovieActivity
import kotlinx.android.synthetic.main.activity_intro.*

/**
 * Created by putraxor on 04/06/17.
 */

class IntroActivity : AppCompatActivity() {

    val TAG: String = this::class.java.name

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        setContentView(R.layout.activity_intro)
        startAnimation(patrickLeft, 350)
        startAnimation(patrickCenter, 540)
        startAnimation(patrickRight, 255)
        startAnimation(prepareLabel, 1000)
        startPopcornAnimation()
        loadFirstData()
    }


    /**
     * Animate any view with infinite fade-in animation
     */
    private fun startAnimation(view: View, duration: Long) {
        val anim = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        anim.duration = duration
        view.startAnimation(anim)
    }

    private fun startPopcornAnimation() {
        val anim = AnimationUtils.loadAnimation(this, R.anim.fade_in_zoom)
        popcornCircle.startAnimation(anim)
    }

    private fun loadFirstData() {
        val repository = MovieRepository()
        repository.getPopular()
                .subscribe { data, error ->
                    Log.d(TAG, "Subscribed result data=$data and error=$error")
                    if (data != null) {
                        startActivity(Intent(this, MovieActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this, "Application error ${error.message}", Toast.LENGTH_SHORT).show()
                        Log.e(TAG, "Application error ${error.message}", error)
                    }
                }
    }
}
