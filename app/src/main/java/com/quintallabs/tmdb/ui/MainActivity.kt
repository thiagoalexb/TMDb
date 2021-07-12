package com.quintallabs.tmdb.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.quintallabs.domain.usecases.getmoviedetail.GetMovieDetail
import com.quintallabs.domain.usecases.getmovies.GetMovies
import com.quintallabs.tmdb.R
import com.quintallabs.tmdb.utils.network.NetworkListener
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val broadcastReceiver by lazy {
        NetworkListener.create({
            Navigation.findNavController(this, R.id.nav_host_fragment)
                .navigate(R.id.upcomingMoviesFragment);
        }, {
            Navigation.findNavController(this, R.id.nav_host_fragment)
                .navigate(R.id.offlineFragment);
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        NetworkListener.register(this, broadcastReceiver)
    }

    override fun onPause() {
        super.onPause()

        NetworkListener.unregister(this, broadcastReceiver)
    }
}