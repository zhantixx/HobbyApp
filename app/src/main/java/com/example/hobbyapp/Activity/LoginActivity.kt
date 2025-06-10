package com.example.hobbyapp.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hobbyapp.R
import com.example.hobbyapp.databinding.ActivityHomeBinding
import com.example.hobbyapp.databinding.ActivityLoginBinding
import okhttp3.*
import java.io.IOException

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Инициализация элементов интерфейса
        val loginEditText: EditText = findViewById(R.id.editTextTextPersonName)
        val passwordEditText: EditText = findViewById(R.id.editTextTextPassword)
        val loginButton: ImageView = findViewById(R.id.imgHome)

        // Устанавливаем паддинг для системных баров
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.logTxt.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegistrationActivity::class.java))
        }

        // Обработчик нажатия на кнопку входа
        loginButton.setOnClickListener {
            val login = loginEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Проверяем, что поля не пустые
            if (login.isNotEmpty() && password.isNotEmpty()) {
                loginUser(login, password)
            } else {
                Toast.makeText(this, "Barlıq auqytlar toltyrılmalı", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Функция для отправки данных на сервер
    private fun loginUser(login: String, password: String) {
        val client = OkHttpClient()

        // Создание тела запроса с данными пользователя
        val requestBody = FormBody.Builder()
            .add("login", login)
            .add("password", password)
            .build()


        // Создание запроса с указанием URL сервера
        val request = Request.Builder()
            .url("http://10.0.2.2:8888/hobbyapp/login.php")  // Для эмулятора Android
            .post(requestBody)
            .build()

        // Отправка запроса
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // Обработка ошибки при неудачном подключении
                runOnUiThread {
                    Toast.makeText(this@LoginActivity, "Serverge qosyla almadym", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    // Проверяем ответ от сервера
                    val responseText = response.body?.string()

                    // Если успешный ответ, переходим на главный экран
                    runOnUiThread {
                        if (responseText == "success") {
                            Toast.makeText(this@LoginActivity, "Kiru satti", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                            startActivity(intent)
                            finish()  // Закрываем текущую активность
                        } else {
                            // Если логин или пароль неправильные
                            Toast.makeText(this@LoginActivity, "Login nemese qupia soz qate", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    // Если ошибка на сервере
                    runOnUiThread {
                        Toast.makeText(this@LoginActivity, "Serverden jawap alalmadym", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }
}