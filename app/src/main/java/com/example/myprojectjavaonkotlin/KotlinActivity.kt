package com.example.myprojectjavaonkotlin

import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.os.Bundle
import android.view.View
import android.widget.Button

class KotlinActivity : AppCompatActivity() {

    private lateinit var minusButton: Button
    private lateinit var plusButton: Button
    private lateinit var textViewCounter: TextView

    private var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_counter)

        minusButton = findViewById(R.id.button_minus)
        plusButton = findViewById(R.id.button_plus)
        textViewCounter = findViewById(R.id.text_view_counter)

        counterUpdate(counter)

        minusButton.setOnClickListener {
            counterUpdate(--counter)
        }

        plusButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                counterUpdate(++counter)
            }
        })
    }

    private fun counterUpdate(counter: Int) {
        textViewCounter.text = counter.toString()
    }
}