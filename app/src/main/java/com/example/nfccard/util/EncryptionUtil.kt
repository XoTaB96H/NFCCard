package com.example.nfccard.util

import android.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

object EncryptionUtil {

    private const val ALGORITHM = "AES"
    private const val KEY = "your-secret-key" // Замените на ваш ключ

    fun encrypt(data: String): String {
        val cipher = Cipher.getInstance(ALGORITHM)
        val keySpec = SecretKeySpec(KEY.toByteArray(), ALGORITHM)
        cipher.init(Cipher.ENCRYPT_MODE, keySpec)
        val encrypted = cipher.doFinal(data.toByteArray())
        return Base64.encodeToString(encrypted, Base64.DEFAULT)
    }

    fun decrypt(data: String): String {
        val cipher = Cipher.getInstance(ALGORITHM)
        val keySpec = SecretKeySpec(KEY.toByteArray(), ALGORITHM)
        cipher.init(Cipher.DECRYPT_MODE, keySpec)
        val decrypted = cipher.doFinal(Base64.decode(data, Base64.DEFAULT))
        return String(decrypted)
    }
}
