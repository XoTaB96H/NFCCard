package com.example.nfccard.util

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat

object PermissionUtil {

    fun checkNfcPermission(context: Context): Boolean {
        val permission = "android.permission.NFC"
        return ActivityCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
    }

    fun requestNfcPermission() {
        // Реализация запроса разрешения на NFC
    }
}
