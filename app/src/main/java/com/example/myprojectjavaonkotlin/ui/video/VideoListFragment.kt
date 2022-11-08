package com.example.myprojectjavaonkotlin.ui.video

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myprojectjavaonkotlin.App
import com.example.myprojectjavaonkotlin.MyReceiver
import com.example.myprojectjavaonkotlin.MyService
import com.example.myprojectjavaonkotlin.databinding.FragmentVideoListBinding
import com.example.myprojectjavaonkotlin.domain.entity.MovieDto
import com.example.myprojectjavaonkotlin.domain.interactor.CollectionInteractor
import java.util.*

private const val FRAGMENT_UUID_KEY = "FRAGMENT_UUID_KEY"

class VideoListFragment : Fragment() {

    private val app: App get() = requireActivity().application as App

    private val collectionVideoRepo: CollectionInteractor by lazy {
        app.collectionInteractor
    }

    private var _binding: FragmentVideoListBinding? = null
    private val binding get() = _binding!!

    /**
     * поздняя инициализация ViewModel, положили в него repo
     * в связи с тем что ViewModel при каждом повороте пересоздается, если необходимо
     * сохранять экран, необходимо ViewModel сохранить вне данного класса
     */
//    private val viewModel: VideoListViewModel by lazy { extractViewModel() }
//    private val viewModel: VideoListViewModel = ViewModelProvider(
//        this,
//        VideoListViewModel.Factory(
//            collectionVideoRepo
//        )
//    )[VideoListViewModel::class.java]

    private val viewModel: VideoListViewModel by viewModels {
        VideoListViewModel.Factory(collectionVideoRepo)
    }

//    private fun extractViewModel(): VideoListViewModel {
//        val presenter = app.rotationFreeStorage[fragmentUid] as VideoListViewModel?
//            ?: VideoListViewModel(collectionVideoRepo)
//        app.rotationFreeStorage[fragmentUid] = presenter
//        return presenter
//    }

    private lateinit var adapter: CollectionVideoAdapter

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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVideoListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

        viewModel.inProgressLiveData.observe(viewLifecycleOwner) { inProgress ->
            binding.collectionVideoRecyclerView.isVisible = !inProgress
            binding.progressTaskBar.isVisible = inProgress
        }

        viewModel.videoListLiveData.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }

        viewModel.selectedVideoLiveData.observe(viewLifecycleOwner) {
            getController().openDetailsVideo(it)
        }

        onService()

        onReceiver()
    }

    private fun onService() {
        context?.let {
            it.startService(Intent(it, MyService::class.java).apply {
                putExtra(
                    "",
                    ""
                )
            })
        }
    }

    private fun onReceiver() {
        context?.let {
            it.sendBroadcast(Intent(it, MyReceiver::class.java).apply {
                putExtra(
                    "RootFragment",
                    "Запущен фрагмент Видео"
                )
            })
        }
    }

    private fun initView() {
        binding.collectionVideoRecyclerView.layoutManager = LinearLayoutManager(context)
        adapter = CollectionVideoAdapter(
            data = emptyList(),
            onVideoClickListener = {
                viewModel.onVideoClick(it)
            }
        )
        binding.collectionVideoRecyclerView.adapter = adapter
    }

    interface Controller {
        fun openDetailsVideo(movieDto: MovieDto)
    }

    private fun getController(): Controller = activity as Controller

    override fun onAttach(context: Context) {
        super.onAttach(context)
        getController()
    }
}