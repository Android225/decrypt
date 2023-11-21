package com.example.decrypt

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import javax.crypto.SecretKey

class MainActivity : AppCompatActivity() {

    // Объявление переменных для взаимодействия с элементами интерфейса
    private lateinit var inputEditText: EditText
    private lateinit var resultTextView: TextView

    // Экземпляр класса для криптографических операций
    private val cryptoManager = CryptoManager()

    // Переменная для хранения секретного ключа шифрования
    private lateinit var secretKey: SecretKey

    // Переменная для хранения зашифрованных данных
    private var encryptedData: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Связывание переменных с элементами пользовательского интерфейса
        inputEditText = findViewById(R.id.inputEditText)
        resultTextView = findViewById(R.id.resultTextView)

        // Генерация секретного ключа для шифрования/дешифрования
        secretKey = cryptoManager.generateKey()

        // Обработчик нажатия на кнопку "Зашифровать"
        findViewById<Button>(R.id.encryptButton).setOnClickListener {
            val inputData = inputEditText.text.toString()
            encryptedData = cryptoManager.encrypt(inputData, secretKey)
            resultTextView.text = "Encrypted: $encryptedData"
        }

        // Обработчик нажатия на кнопку "Дешифровать"
        findViewById<Button>(R.id.decryptButton).setOnClickListener {
            encryptedData?.let {
                val decryptedData = cryptoManager.decrypt(it, secretKey)
                resultTextView.text = "Decrypted: $decryptedData"
            }
        }
    }
}