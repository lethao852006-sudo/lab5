package com.example.lab5_registration

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lab5_registration.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = intent.getSerializableExtra("USER_DATA") as? User
        if (user != null) {
            binding.tvInfo.text = "Họ và tên: ${user.name}\nEmail: ${user.email}\nSố điện thoại: ${user.phone}"
        }
    }
}