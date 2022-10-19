package com.example.myprojectjavaonkotlin.ui.details

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.myprojectjavaonkotlin.App
import com.example.myprojectjavaonkotlin.R
import com.example.myprojectjavaonkotlin.domain.entity.VideoEntity
import com.example.myprojectjavaonkotlin.domain.repo.CollectionVideoRepo
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import java.util.*

private const val DETAILS_VIDEO_KEY = "DETAILS_VIDEO_KEY"
private const val FRAGMENT_UUID_KEY = "FRAGMENT_UUID_KEY"

class DetailsVideoFragment : Fragment(R.layout.fragment_details_video) {

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

    private lateinit var nameTv: TextView
    private lateinit var genreTv: TextView
    private lateinit var yearReleaseTv: TextView
    private lateinit var descriptionTv: TextView
    private lateinit var coverIv: ImageView

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

        viewModel.videoLiveData.observe(viewLifecycleOwner) {
            setVideoEntity(it)

            Snackbar.make(
                view,
                getString(R.string.name_film) + it.name,
                Snackbar.ANIMATION_MODE_SLIDE
            ).show()
        }
    }

    private fun initView(view: View) {
        nameTv = view.findViewById(R.id.name_details_text_view)
        genreTv = view.findViewById(R.id.genre_details_text_view)
        yearReleaseTv = view.findViewById(R.id.year_release_details_text_view)
        descriptionTv = view.findViewById(R.id.description_details_text_view)
        coverIv = view.findViewById(R.id.cover_image_view)
    }

    private fun setVideoEntity(videoEntity: VideoEntity) {
        nameTv.text = videoEntity.name
        genreTv.text = videoEntity.genre
        yearReleaseTv.text = videoEntity.yearRelease
        descriptionTv.text = videoEntity.description

        if (videoEntity.imageUrl.isNotBlank()) {
            Picasso.get()
                .load(videoEntity.imageUrl)
                .placeholder(R.drawable.uploading_images)
                .into(coverIv)
            coverIv.scaleType =
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
}