package com.example.myprojectjavaonkotlin.ui.settings

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.myprojectjavaonkotlin.R
import com.example.myprojectjavaonkotlin.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentSettingsBinding.bind(view)

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

        @JvmStatic
        fun newInstance() =
            SettingsFragment().apply {
//                    putString(KEY, "key")
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}