package com.example.contactslistview.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.contactslistview.databinding.ActivityMainBinding
import com.example.contactslistview.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity() {

    private  lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            super.onBackPressed()
        }

        val name = intent.getStringExtra("name")

        binding.userName.text = name
    }
}