package com.example.myprojectjavaonkotlin.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.myprojectjavaonkotlin.R
import com.example.myprojectjavaonkotlin.databinding.ActivityRootBinding
import com.example.myprojectjavaonkotlin.domain.entity.FavoriteMovieDto
import com.example.myprojectjavaonkotlin.ui.contacts.ContactsFragment
import com.example.myprojectjavaonkotlin.ui.details.DetailsVideoFragment
import com.example.myprojectjavaonkotlin.ui.favourites.FavouritesFragment
import com.example.myprojectjavaonkotlin.ui.history.HistoryFragment
import com.example.myprojectjavaonkotlin.ui.map.MapsFragment
import com.example.myprojectjavaonkotlin.ui.settings.SettingsFragment
import com.example.myprojectjavaonkotlin.ui.video.VideoListFragment

private const val TAG_DETAILS_VIDEO_KEY = "TAG_DETAILS_VIDEO_KEY"
private const val TAG_MAIN_CONTAINER_LAYOUT_KEY = "TAG_MAIN_CONTAINER_LAYOUT_KEY"
private const val TAG_CONTACTS_KEY = "TAG_CONTACTS_KEY"
private const val TAG_MAPS_KEY = "TAG_MAPS_KEY"

class RootActivity : AppCompatActivity(),
    VideoListFragment.Controller,
    DetailsVideoFragment.Controller,
    FavouritesFragment.Controller,
    SettingsFragment.Controller,
    HistoryFragment.Controller,
    ContactsFragment.Controller,
    MapsFragment.Controller {

    private lateinit var binding: ActivityRootBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRootBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onBottomNavBar()

        if (savedInstanceState == null) {//проверяем какой запуск первый или нет (например, после поворота экрана)
            binding.bottomNavBar.selectedItemId = R.id.video_list_item
        } else {
            //todo иначе достать из --
        }
        if (binding.fragmentContainerFrameLayout.id == R.id.video_list_item) {
            binding.bottomNavBar.visibility = View.VISIBLE
        }
    }

    private fun onBottomNavBar() {
        binding.bottomNavBar.setOnItemSelectedListener {
            title = it.title
            val fragment = when (it.itemId) {
                R.id.video_list_item -> VideoListFragment()
                R.id.favorite_item -> FavouritesFragment()
                R.id.history_item -> HistoryFragment()
                R.id.settings_item -> SettingsFragment()
                else -> throw IllegalStateException("Такого фрагмента нет")
            }
            swapFragment(fragment)
            return@setOnItemSelectedListener true
        }
    }

    private fun swapFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(
                binding.fragmentContainerFrameLayout.id,
                fragment,
                TAG_MAIN_CONTAINER_LAYOUT_KEY
            ).commit()
    }

    private fun openDetailsVideoFragment(videoId: String) {
        val fragment: Fragment = DetailsVideoFragment.newInstance(videoId)
        supportFragmentManager
            .beginTransaction()
            .add(binding.fragmentContainerFrameLayout.id, fragment, TAG_DETAILS_VIDEO_KEY)
            .addToBackStack(null)
            .commit()
        binding.bottomNavBar.visibility = View.GONE
    }

    private fun openContactsFragment() {
        val fragment: Fragment = ContactsFragment.newInstance()
        supportFragmentManager
            .beginTransaction()
            .replace(binding.fragmentContainerFrameLayout.id, fragment, TAG_CONTACTS_KEY)
            .addToBackStack(null)
            .commit()
        binding.bottomNavBar.visibility = View.GONE
    }

    private fun openMapsGoogleFragment() {
        val fragment: Fragment = MapsFragment.newInstance()
        supportFragmentManager
            .beginTransaction()
            .replace(binding.fragmentContainerFrameLayout.id, fragment, TAG_MAPS_KEY)
            .addToBackStack(null)
            .commit()
        binding.bottomNavBar.visibility = View.GONE
    }

    override fun openDetailsVideo(favoriteMovieDto: FavoriteMovieDto) {
        openDetailsVideoFragment(favoriteMovieDto.id)
    }

    override fun onBackPressed() {
        binding.bottomNavBar.visibility = View.VISIBLE
        super.onBackPressed()
    }

    override fun openContacts() {
        openContactsFragment()
    }

    override fun openMapsGoogle() {
        openMapsGoogleFragment()
    }
}