package com.example.myprojectjavaonkotlin.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myprojectjavaonkotlin.R
import com.example.myprojectjavaonkotlin.ui.video.VideoListFragment

class RootActivity : AppCompatActivity(),
    VideoListFragment.Controller {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_root)

        if (savedInstanceState == null)//проверяем какой запуск первый или нет (например, после поворота экрана)
            supportFragmentManager
                .beginTransaction()
                .add(R.id.container_layout, VideoListFragment())
                .commit()
    }
}