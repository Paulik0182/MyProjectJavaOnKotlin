package com.example.myprojectjavaonkotlin.ui.video

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myprojectjavaonkotlin.App
import com.example.myprojectjavaonkotlin.R
import com.example.myprojectjavaonkotlin.domain.entity.VideoEntity
import com.example.myprojectjavaonkotlin.domain.repo.VideoRepo

class VideoListFragment : Fragment(R.layout.fragment_video_list) {

    private val app: App by lazy { requireActivity().application as App }

    private lateinit var adapter: VideoListAdapter
    private val listener = { _: VideoEntity ->
        fillView()
    }

    private lateinit var videoRepo: VideoRepo
    private lateinit var recyclerView: RecyclerView
    private lateinit var videoList: MutableList<VideoEntity>


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView(view)

        videoRepo = app.videoRepo

        adapter.setData(videoRepo.getVideo())
        adapter.setOnItemClickListener{
            getController().openDetailsVideo(it)
        }
    }

    private fun fillView(){
        adapter.setData(videoList)
    }

    private fun initView(view: View) {
        recyclerView = view.findViewById(R.id.video_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = VideoListAdapter(emptyList(), listener)
        recyclerView.adapter = adapter
    }

    interface Controller {
        fun openDetailsVideo(videoEntity: VideoEntity)
    }

    private fun getController(): Controller = activity as Controller

    override fun onAttach(context: Context) {
        super.onAttach(context)
        getController()
    }

    companion object {
        @JvmStatic
        fun newInstance() = VideoListFragment()
    }
}