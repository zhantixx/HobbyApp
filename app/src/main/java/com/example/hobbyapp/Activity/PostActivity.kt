package com.example.hobbyapp.Activity

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.hobbyapp.databinding.ActivityPostBinding
import com.example.hobbyapp.R
import com.google.android.material.bottomnavigation.LabelVisibilityMode

class PostActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPostBinding
    private var selectedImageUri: Uri? = null
    private val PICK_IMAGE_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSelectImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            intent.type = "image/*"
            startActivityForResult(intent, PICK_IMAGE_REQUEST)
        }

        binding.buttonPublish.setOnClickListener {
            val title = binding.editTitle.text.toString()
            val description = binding.editDescription.text.toString()
            val location = binding.editLocation.text.toString()
            val date = binding.editDate.text.toString()
            val category = binding.editCategory.text.toString()

            if (title.isBlank() || description.isBlank() || location.isBlank()
                || date.isBlank() || category.isBlank()
            ) {
                Toast.makeText(this, "Barlyq alanǵa mǝlimet engizińiz", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Post sǝtti jariyalandy!", Toast.LENGTH_SHORT).show()
                finish()
            }
        }

        // Bottom navigation
        binding.bottomNavigation.labelVisibilityMode = LabelVisibilityMode.LABEL_VISIBILITY_UNLABELED
        binding.bottomNavigation.selectedItemId = R.id.nav_edit
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    true
                }
                R.id.nav_edit -> true
                R.id.nav_chat -> {
                    startActivity(Intent(this, ChatActivity::class.java))
                    true
                }
                R.id.nav_profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    true
                }
                R.id.nav_friends -> {
                    startActivity(Intent(this, FriendsActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            selectedImageUri = data?.data
            selectedImageUri?.let { uri ->
                try {
                    contentResolver.takePersistableUriPermission(
                        uri,
                        Intent.FLAG_GRANT_READ_URI_PERMISSION
                    )
                    binding.imagePreview.setImageURI(uri)
                } catch (e: SecurityException) {
                    Toast.makeText(this, "Kóru úshin rukhsat joq", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}