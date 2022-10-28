package com.example.myprojectjavaonkotlin.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import com.example.myprojectjavaonkotlin.R
import com.example.myprojectjavaonkotlin.domain.KotlinCounterEntity

private const val SAVE_KOTLIN_COUNTER_KEY = "save_kotlin_counter_key"
private const val TAG = "@@@ KOTLIN"

class KotlinActivity : AppCompatActivity() {

    private lateinit var minusButton: Button
    private lateinit var plusButton: Button
    private lateinit var textViewCounter: TextView
    private lateinit var textView: TextView

    private lateinit var counter: KotlinCounterEntity

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_counter)

        initViews()

        textView.text = "KOTLIN"

        counter = if (savedInstanceState != null && savedInstanceState.containsKey("SAVE_JAVA_COUNTER_KEY")) {
            savedInstanceState.getParcelable("SAVE_JAVA_COUNTER_KEY")!!
        } else {
            KotlinCounterEntity("counter", 0)
        }

        counterUpdate()

        minusButton.setOnClickListener {
            counter.decrement()
            counterUpdate()
        }

        plusButton.setOnClickListener {
            counter.increment()
            counterUpdate()
        }
    }

    private fun initViews() {
        minusButton = findViewById(R.id.button_minus)
        plusButton = findViewById(R.id.button_plus)
        textViewCounter = findViewById(R.id.text_view_counter)
        textView = findViewById(R.id.text_view)
    }

    private fun counterUpdate() {
        textViewCounter.text = counter.counter.toString()
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        outState.putInt(SAVE_KOTLIN_COUNTER_KEY, counter.counter)

        Log.d(
            TAG,
            "onSaveInstanceState() called with: outState = $outState, outPersistentState = $outPersistentState"
        )
        super.onSaveInstanceState(outState, outPersistentState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        if (savedInstanceState.containsKey(SAVE_KOTLIN_COUNTER_KEY)) {
            counter = savedInstanceState.getParcelable(SAVE_KOTLIN_COUNTER_KEY)!!
        }
        counterUpdate()

        Log.d(TAG, "onRestoreInstanceState() called with: savedInstanceState = $savedInstanceState")
        super.onRestoreInstanceState(savedInstanceState)
    }

    companion object {
        fun launch(context: Context) {
            val intent = Intent(context, KotlinActivity::class.java)
            context.startActivity(intent)
        }
    }
}