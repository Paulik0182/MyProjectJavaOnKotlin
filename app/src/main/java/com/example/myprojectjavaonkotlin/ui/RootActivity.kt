package com.example.myprojectjavaonkotlin.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import com.example.myprojectjavaonkotlin.R

class RootActivity : AppCompatActivity() {

    private lateinit var javaButton: Button
    private lateinit var kotlinButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

        javaButton.setOnClickListener {
            val intent = Intent(this@RootActivity, JavaActivity::class.java)
            startActivity(intent)
        }

        kotlinButton.setOnClickListener {
            KotlinActivity.launch(kotlinButton.context)
        }
    }

    private fun initViews() {
        javaButton = findViewById(R.id.button_java)
        kotlinButton = findViewById(R.id.button_kotlin)
    }
}