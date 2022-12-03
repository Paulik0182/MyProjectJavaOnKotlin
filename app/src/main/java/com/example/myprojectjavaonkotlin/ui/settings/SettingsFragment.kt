package com.example.myprojectjavaonkotlin.ui.settings

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.myprojectjavaonkotlin.R
import com.example.myprojectjavaonkotlin.databinding.FragmentSettingsBinding

private const val IS_ADULT_KEY = "IS_ADULT_KEY"
private const val SAVE_SETTINGS_KEY = "SAVE_SETTINGS_KEY"

class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    private var isDataSetAdult: Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentSettingsBinding.bind(view)

        binding.switchContent.setOnCheckedChangeListener { buttonContent, isChecked ->
            isDataSetAdult = !isDataSetAdult
            if (isChecked) {
                Toast.makeText(requireContext(), "Взрослый контент", Toast.LENGTH_SHORT).show()
                saveListOfMovies()
            } else {
                Toast.makeText(requireContext(), "Выключено", Toast.LENGTH_SHORT).show()
            }
        }
        showListOfMovies()
    }

    private fun showListOfMovies() {
        isDataSetAdult =
            requireActivity().getSharedPreferences(SAVE_SETTINGS_KEY, Context.MODE_PRIVATE)
                .getBoolean(IS_ADULT_KEY, true)
        binding.switchContent.isChecked = isDataSetAdult
    }

    private fun saveListOfMovies() {
        requireActivity().getSharedPreferences(SAVE_SETTINGS_KEY, Context.MODE_PRIVATE).edit()
            .putBoolean(IS_ADULT_KEY, isDataSetAdult)
            .apply()
    }

    interface Controller {
        // TODO
    }

    private fun getController(): Controller = activity as Controller

    override fun onAttach(context: Context) {
        super.onAttach(context)
        getController()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}