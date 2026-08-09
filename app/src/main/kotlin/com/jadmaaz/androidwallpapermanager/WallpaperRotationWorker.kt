package com.jadmaaz.androidwallpapermanager

import android.app.WallpaperManager
import android.content.Context
import android.net.Uri
import androidx.documentfile.provider.DocumentFile
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class WallpaperRotationWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        val prefs = applicationContext.getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val wallpaperManager = WallpaperManager.getInstance(applicationContext)

        prefs.getString("home_dir_uri", null)?.let { uriString ->
            setRandomWallpaperFrom(uriString, wallpaperManager, WallpaperManager.FLAG_SYSTEM)
        }
        prefs.getString("lock_dir_uri", null)?.let { uriString ->
            setRandomWallpaperFrom(uriString, wallpaperManager, WallpaperManager.FLAG_LOCK)
        }
        return Result.success()
    }

    private fun setRandomWallpaperFrom(
        treeUriString: String,
        wallpaperManager: WallpaperManager,
        which: Int
    ) {
        val treeUri = Uri.parse(treeUriString)
        val dir = DocumentFile.fromTreeUri(applicationContext, treeUri) ?: return
        val images = dir.listFiles().filter { it.type?.startsWith("image/") == true }
        if (images.isEmpty()) return

        val chosen = images.random()
        applicationContext.contentResolver.openInputStream(chosen.uri)?.use { stream ->
            wallpaperManager.setStream(stream, null, true, which)
        }
    }
}
