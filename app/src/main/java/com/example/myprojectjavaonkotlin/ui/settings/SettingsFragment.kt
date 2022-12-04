package com.example.myprojectjavaonkotlin.ui.settings

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.myprojectjavaonkotlin.R
import com.example.myprojectjavaonkotlin.databinding.FragmentSettingsBinding
import com.example.myprojectjavaonkotlin.ui.utils.snack

private const val IS_ADULT_KEY = "IS_ADULT_KEY"
private const val SAVE_SETTINGS_KEY = "SAVE_SETTINGS_KEY"

class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    private var isDataSetAdult: Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentSettingsBinding.bind(view)

        setAdultContent()

        showListOfMovies()

        setContacts()
    }

    private fun setAdultContent() {
        binding.switchContent.setOnCheckedChangeListener { buttonContent, isChecked ->
            isDataSetAdult = !isDataSetAdult
            if (isChecked) {
                Toast.makeText(requireContext(), "Взрослый контент", Toast.LENGTH_SHORT).show()
                saveListOfMovies()
            } else {
                Toast.makeText(requireContext(), "Выключено", Toast.LENGTH_SHORT).show()
            }
        }
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

    // проверяем разрешения чтения контактов
    private fun checkPermission() {
        context?.let {
            when {
                // Внимательно импортировать Manifest (в библиотеках android)
                ContextCompat.checkSelfPermission(it, Manifest.permission.READ_CONTACTS) ==
                        PackageManager.PERMISSION_DENIED -> {
                    // Доступ к контактам
                    view?.snack("Список контактов получен")
                    getController().openContacts()
                }

                // пояснение перед запросом разрешения (не обязательно. показывается в окне запроса)
                shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS) -> {
                    AlertDialog.Builder(it)
                        .setTitle("Доступ к контактам")
                        .setMessage("Для чтения контактов и демонстрации")
                        .setPositiveButton("Предоставить доступ") { _, _ ->
                            // Запрашиваем разрешение
                            requestPermissionLauncher.launch(Manifest.permission.READ_CONTACTS)
                        }
                        .setNegativeButton("Отказать в доступе") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                        .show()
                }
                else -> {
                    // если ничего не произошло, то запрашиваем разрешения
                    requestPermissionLauncher.launch(Manifest.permission.READ_CONTACTS)

                }
            }
        }
    }

    private val requestPermissionLauncher: ActivityResultLauncher<String> =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                // пермиссия выдана
                view?.snack("Список контактов получен")
            } else {
                // не выдана
                context?.let {
                    AlertDialog.Builder(it)
                        .setTitle("Доступ к контактам")
                        .setMessage("Уведомление")
                        .setNegativeButton("Закрыть") { dialog, _ ->
                            dialog.dismiss()
                        }.show()
                }
            }
        }

    private fun setContacts() {
        binding.setContactsButton.setOnClickListener {
//            checkPermission()
            getController().openContacts()
        }
    }

    interface Controller {
        fun openContacts()
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