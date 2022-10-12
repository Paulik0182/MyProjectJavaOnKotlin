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
import java.util.*

private const val FRAGMENT_UUID_KEY = "FRAGMENT_UUID_KEY"

class VideoListFragment : Fragment(R.layout.fragment_video_list) {

    private val app: App by lazy { requireActivity().application as App }

    /**
     * поздняя инициализация ViewModel, положили в него repo
     * в связи с тем что ViewModel при каждом повороте пересоздается, если необходимо
     * сохранять экран, необходимо ViewModel сохранить вне данного класса
     */
    private val viewModel: VideoListViewModel by lazy { extractViewModel() }

    private fun extractViewModel(): VideoListViewModel{
        val presenter = app.rotationFreeStorage[fragmentUid] as VideoListViewModel?
            ?: VideoListViewModel(videoRepo)
        app.rotationFreeStorage[fragmentUid] = presenter
        return presenter
    }

    private lateinit var adapter: VideoListAdapter

    private val videoRepo: VideoRepo by lazy {
        app.videoRepo
    }
    private lateinit var recyclerView: RecyclerView

    //уникальный id (для того чтобы можно было сохранить состояние экрана за пределами класса
    private lateinit var fragmentUid: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //проверка, есть ли это значение, если нет то создаем его
        fragmentUid =
            savedInstanceState?.getString(FRAGMENT_UUID_KEY) ?: UUID.randomUUID().toString()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //при сохроанении положить ID
        outState.putString(FRAGMENT_UUID_KEY, fragmentUid)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView(view)

        viewModel.videoListLiveData.observe(viewLifecycleOwner){
            adapter.setData(it)
        }

        viewModel.selectedVideoLiveData.observe(viewLifecycleOwner){
            getController().openDetailsVideo(it)
        }
    }

    private fun initView(view: View) {
        recyclerView = view.findViewById(R.id.video_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = VideoListAdapter(
            data = emptyList(),
            onDetailVideoListener = {
                viewModel.onVideoClick(it)
            }
        )
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
}