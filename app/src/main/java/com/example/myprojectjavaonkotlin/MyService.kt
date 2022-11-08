package com.example.myprojectjavaonkotlin

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

/**
 * Все методы сервиса работают в главном потоке.
 * Service - бывают два вида. Простые Service и Intent Service(это надстройка).
 * Если сервис запускаем то его обязательно нужно принудительно остановить.
 * Прописать в Manifests -
 * <service
 * android:name=".MyService"
 * android:enabled="true"
 * android:exported="true" />
 */

const val TAG = "MyService"

class MyService : Service() {

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(
            TAG,
            "onStartCommand() called with: intent = $intent, flags = $flags, startId = $startId"
        )
        Thread {
            Thread.sleep(1_000)
            stopSelf()//остановить себя (сервис)
        }.start()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder? {
        Log.d(TAG, "onBind() called with: intent = $intent")
        return null
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d(TAG, "onUnbind() called with: intent = $intent")
        return super.onUnbind(intent)
    }
}