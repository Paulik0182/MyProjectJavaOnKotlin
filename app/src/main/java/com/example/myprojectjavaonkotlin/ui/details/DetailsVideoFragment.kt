package com.example.myprojectjavaonkotlin.ui.details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.myprojectjavaonkotlin.App
import com.example.myprojectjavaonkotlin.R
import com.example.myprojectjavaonkotlin.databinding.FragmentDetailsVideoBinding
import com.example.myprojectjavaonkotlin.domain.entity.VideoEntity
import com.example.myprojectjavaonkotlin.domain.repo.CollectionVideoRepo
import com.example.myprojectjavaonkotlin.ui.utils.snack
import com.squareup.picasso.Picasso
import java.util.*

private const val DETAILS_VIDEO_KEY = "DETAILS_VIDEO_KEY"
private const val FRAGMENT_UUID_KEY = "FRAGMENT_UUID_KEY"

class DetailsVideoFragment : Fragment() {

    private var _binding: FragmentDetailsVideoBinding? = null
    private val binding get() = _binding!!

    private val app: App by lazy { requireActivity().application as App }

    /**
     * поздняя инициализация ViewModel, положили в него repo
     * в связи с тем что ViewModel при каждом повороте пересоздается, если необходимо
     * сохранять экран, необходимо ViewModel сохранить вне данного класса
     */
    private val viewModel: DetailsViewModel by lazy { extractViewModel() }

    private fun extractViewModel(): DetailsViewModel {
        //достаем id
        val id = requireArguments().getLong(DETAILS_VIDEO_KEY)
        val viewModel = app.rotationFreeStorage[fragmentUid] as DetailsViewModel?
            ?: DetailsViewModel(videoRepo, id)
        app.rotationFreeStorage[fragmentUid] = viewModel
        return viewModel
    }

    private val videoRepo: CollectionVideoRepo by lazy {
        app.collectionVideoRepo
    }

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
        _binding = FragmentDetailsVideoBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.videoLiveData.observe(viewLifecycleOwner) {
            setVideoEntity(it)

            //Snackbar
            view.snack(getString(R.string.name_film) + it.name)
        }
    }

    private fun setVideoEntity(videoEntity: VideoEntity) {
        binding.nameDetailsTextView.text = videoEntity.name
        binding.genreDetailsTextView.text = videoEntity.genre
        binding.yearReleaseDetailsTextView.text = videoEntity.yearRelease
        binding.descriptionDetailsTextView.text = videoEntity.description

        if (videoEntity.imageUrl.isNotBlank()) {
            Picasso.get()
                .load(videoEntity.imageUrl)
                .placeholder(R.drawable.uploading_images)
                .into(binding.coverImageView)
            binding.coverImageView.scaleType =
                ImageView.ScaleType.FIT_CENTER// растягиваем картинку на весь элемент
        }
    }

    interface Controller {
        // TODO
    }

    private fun getController(): Controller = activity as Controller

    override fun onAttach(context: Context) {
        super.onAttach(context)
        getController()
    }

    companion object {
        fun newInstance(videoId: Long) =
            DetailsVideoFragment().apply {
                arguments = Bundle().apply {
                    putLong(DETAILS_VIDEO_KEY, videoId)
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}