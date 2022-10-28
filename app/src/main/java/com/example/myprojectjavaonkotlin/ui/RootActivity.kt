package com.example.myprojectjavaonkotlin.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.myprojectjavaonkotlin.App
import com.example.myprojectjavaonkotlin.R
import com.example.myprojectjavaonkotlin.domain.entity.VideoEntity
import com.example.myprojectjavaonkotlin.ui.details.DetailsVideoFragment
import com.example.myprojectjavaonkotlin.ui.video.VideoListFragment

private const val TAG_DETAILS_VIDEO_KEY = "TAG_DETAILS_VIDEO_KEY"

class RootActivity : AppCompatActivity(),
    VideoListFragment.Controller,
    DetailsVideoFragment.Controller {

    private lateinit var loadButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_root)

        loadButton = findViewById(R.id.load_button)
        loadButton.setOnClickListener {
            (application as App).movieDtoRepo.getMovies(
                mutableListOf(
                    "action",
                    "comedy",
                    "family",
                    "history"
                )
            ) {
                Toast.makeText(this, "Load ${it.size} films", Toast.LENGTH_SHORT).show()
            }
        }

        if (savedInstanceState == null)//проверяем какой запуск первый или нет (например, после поворота экрана)
            supportFragmentManager
                .beginTransaction()
                .add(R.id.container_layout, VideoListFragment())
                .commit()
    }

    private fun openDetailsVideoFragment(videoId: Long) {
        val fragment: Fragment = DetailsVideoFragment.newInstance(videoId)
        supportFragmentManager
            .beginTransaction()
            .add(R.id.container_layout, fragment, TAG_DETAILS_VIDEO_KEY)
            .addToBackStack(null)
            .commit()
    }

    override fun openDetailsVideo(videoEntity: VideoEntity) {
        openDetailsVideoFragment(videoEntity.id)
    }
}