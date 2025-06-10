package com.example.hobbyapp.Activity

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hobbyapp.databinding.ActivityProfileEditBinding
import java.util.Calendar

class ProfileEditActivity : AppCompatActivity() {

    private var selectedImageUri: Uri? = null

    private lateinit var binding: ActivityProfileEditBinding
    private val IMAGE_PICK_CODE = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityProfileEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Полноэкранный режим
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        window.statusBarColor = Color.WHITE

        // Обработка отступов системных панелей
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Кнопка "Назад"
        binding.backButton.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
            finish()
        }

        // Кнопка "Обновить"
        binding.btnUpdate.setOnClickListener {
            saveProfileData()
            startActivity(Intent(this, ProfileActivity::class.java))
            finish()
        }


        // Кнопка для редактирования аватара
        binding.editImg.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, IMAGE_PICK_CODE)
        }
        binding.inputDob.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePicker = DatePickerDialog(this, { _, y, m, d ->
                val selectedDate = String.format("%02d/%02d/%04d", d, m + 1, y)
                binding.inputDob.setText(selectedDate)
            }, year, month, day)

            datePicker.show()
        }
    }

    private fun saveProfileData() {
        val sharedPref = getSharedPreferences("UserProfile", MODE_PRIVATE)
        val editor = sharedPref.edit()

        editor.putString("name", binding.inputName.text.toString())
        editor.putString("dob", binding.inputDob.text.toString())
        editor.putString("email", binding.inputEmail.text.toString())
        editor.putString("phone", binding.inputPhone.text.toString())
        editor.putInt("country_code_position", binding.countryCodeSpinner.selectedItemPosition)

        if (selectedImageUri != null) {
            editor.putString("image_uri", selectedImageUri.toString())
        }

        editor.apply()
    }


    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_PICK_CODE && resultCode == RESULT_OK) {
            selectedImageUri = data?.data
            if (selectedImageUri != null) {
                binding.editImg.setImageURI(selectedImageUri)
            }
        }
    }
}
