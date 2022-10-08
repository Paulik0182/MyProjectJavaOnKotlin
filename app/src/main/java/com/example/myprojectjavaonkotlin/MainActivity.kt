package com.example.myprojectjavaonkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private lateinit var javaButton: Button
    private lateinit var kotlinButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        javaButton = findViewById(R.id.button_java)
        javaButton.setOnClickListener {
            val intent = Intent(this@MainActivity, JavaActivity::class.java)
            startActivity(intent)
        }

        kotlinButton = findViewById(R.id.button_kotlin)
        kotlinButton.setOnClickListener(View.OnClickListener { view: View? ->
            val intent = Intent(this@MainActivity, KotlinActivity::class.java)
            startActivity(intent)
        })
    }
}