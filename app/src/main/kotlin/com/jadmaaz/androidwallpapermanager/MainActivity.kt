package com.jadmaaz.androidwallpapermanager

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.work.*
import java.util.concurrent.TimeUnit

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<android.widget.Button>(R.id.pickHomeButton).setOnClickListener {
            pickDirectory(REQUEST_HOME_DIR)
        }
        findViewById<android.widget.Button>(R.id.pickLockButton).setOnClickListener {
            pickDirectory(REQUEST_LOCK_DIR)
        }

        scheduleRotationWork()
    }

    private fun pickDirectory(requestCode: Int) {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT_TREE)
        startActivityForResult(intent, requestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != RESULT_OK) return
        val uri = data?.data ?: return

        contentResolver.takePersistableUriPermission(
            uri,
            Intent.FLAG_GRANT_READ_URI_PERMISSION
        )

        val key = when (requestCode) {
            REQUEST_HOME_DIR -> "home_dir_uri"
            REQUEST_LOCK_DIR -> "lock_dir_uri"
            else -> return
        }
        getSharedPreferences("prefs", MODE_PRIVATE).edit()
            .putString(key, uri.toString())
            .apply()

        WorkManager.getInstance(this).enqueue(OneTimeWorkRequestBuilder<WallpaperRotationWorker>().build())
    }

    private fun scheduleRotationWork() {
        val request = PeriodicWorkRequestBuilder<WallpaperRotationWorker>(30, TimeUnit.MINUTES)
            .build()
        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "wallpaper_rotation",
            ExistingPeriodicWorkPolicy.KEEP,
            request
        )
    }

    companion object {
        private const val REQUEST_HOME_DIR = 1
        private const val REQUEST_LOCK_DIR = 2
    }
}
