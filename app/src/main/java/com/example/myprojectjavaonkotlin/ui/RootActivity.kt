package com.example.myprojectjavaonkotlin.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.myprojectjavaonkotlin.MyReceiver
import com.example.myprojectjavaonkotlin.R
import com.example.myprojectjavaonkotlin.databinding.ActivityRootBinding
import com.example.myprojectjavaonkotlin.domain.entity.MovieDto
import com.example.myprojectjavaonkotlin.ui.details.DetailsVideoFragment
import com.example.myprojectjavaonkotlin.ui.video.VideoListFragment

private const val TAG_DETAILS_VIDEO_KEY = "TAG_DETAILS_VIDEO_KEY"

class RootActivity : AppCompatActivity(),
    VideoListFragment.Controller,
    DetailsVideoFragment.Controller {

    private lateinit var binding: ActivityRootBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRootBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null)//проверяем какой запуск первый или нет (например, после поворота экрана)
            supportFragmentManager
                .beginTransaction()
                .add(R.id.container_layout, VideoListFragment())
                .commit()

        onReceiver()
    }

    private fun onReceiver() {
        val intentReceiver = Intent(this, MyReceiver::class.java)
        intentReceiver.putExtra(
            "Action",
            "Запущена активити (Receiver)"
        )
//        intentReceiver.action = "Я Receiver"
        sendBroadcast(intentReceiver)
    }

    private fun openDetailsVideoFragment(videoId: String) {
        val fragment: Fragment = DetailsVideoFragment.newInstance(videoId)
        supportFragmentManager
            .beginTransaction()
            .add(R.id.container_layout, fragment, TAG_DETAILS_VIDEO_KEY)
            .addToBackStack(null)
            .commit()
    }

    override fun openDetailsVideo(movieDto: MovieDto) {
        openDetailsVideoFragment(movieDto.id)
    }
}