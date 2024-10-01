package com.example.nfccard.nfc

import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.nfc.NfcAdapter


object NFCUtil {

    fun isNfcEnabled(context: Context): Boolean {
        val nfcAdapter = NfcAdapter.getDefaultAdapter(context)
        return nfcAdapter != null && nfcAdapter.isEnabled
    }

    fun enableNfc(context: Context) {
        context.startActivity(Intent(Settings.ACTION_NFC_SETTINGS).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        })
    }

    fun generateUniqueNfcId(): String {
        return java.util.UUID.randomUUID().toString()
    }
}
