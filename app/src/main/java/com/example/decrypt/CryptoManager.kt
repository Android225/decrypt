package com.example.decrypt

import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import android.util.Base64

class CryptoManager {

    // Генерация ключа для шифрования
    fun generateKey(): SecretKey {
        val keyGenerator = KeyGenerator.getInstance("AES")
        keyGenerator.init(256)
        return keyGenerator.generateKey()
    }

    // Функция для шифрования данных
    fun encrypt(data: String, secretKey: SecretKey): String {
        val cipher = Cipher.getInstance("AES")
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)
        val encryptedData = cipher.doFinal(data.toByteArray(Charsets.UTF_8))
        return Base64.encodeToString(encryptedData, Base64.DEFAULT)
    }

    // Функция для дешифрования данных
    fun decrypt(data: String, secretKey: SecretKey): String {
        val cipher = Cipher.getInstance("AES")
        cipher.init(Cipher.DECRYPT_MODE, secretKey)
        val decodedData = Base64.decode(data, Base64.DEFAULT)
        val decryptedData = cipher.doFinal(decodedData)
        return String(decryptedData, Charsets.UTF_8)
    }
}