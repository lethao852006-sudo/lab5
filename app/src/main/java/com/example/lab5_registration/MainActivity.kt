package com.example.lab5_registration

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.example.lab5_registration.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRealtimeValidation()
        setupButtons()
    }

    // Tự động xóa lỗi khi người dùng gõ phím mới (Lab 5 - Bài 2)[cite: 12, 13]
    private fun setupRealtimeValidation() {
        binding.edtName.doAfterTextChanged { binding.tilName.error = null }
        binding.edtEmail.doAfterTextChanged { binding.tilEmail.error = null }
        binding.edtPhone.doAfterTextChanged { binding.tilPhone.error = null }
    }

    private fun setupButtons() {
        binding.btnRegister.setOnClickListener { view ->
            if (validateForm()) {
                val name = binding.edtName.text.toString().trim()
                val email = binding.edtEmail.text.toString().trim()
                val phone = binding.edtPhone.text.toString().trim()

                val user = User(name, email, phone)
                val intent = Intent(this, ProfileActivity::class.java).apply {
                    putExtra("USER_DATA", user)
                }
                startActivity(intent)
            } else {
                // Thất bại -> Hiển thị Snackbar báo lỗi (Yêu cầu đề bài)[cite: 1, 6]
                Snackbar.make(
                    view,
                    "Vui lòng điền đầy đủ và đúng thông tin các trường!",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }

        binding.btnCancel.setOnClickListener {
            resetForm()
        }
    }

    private fun validateForm(): Boolean {
        var isValid = true
        val name = binding.edtName.text.toString().trim()
        val email = binding.edtEmail.text.toString().trim()
        val phone = binding.edtPhone.text.toString().trim()

        if (name.isEmpty()) {
            binding.tilName.error = "Họ tên không được để trống"
            isValid = false
        }

        if (email.isEmpty()) {
            binding.tilEmail.error = "Email không được để trống"
            isValid = false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.tilEmail.error = "Email không đúng định dạng"
            isValid = false
        }

        val phoneRegex = Regex("^(03|05|07|08|09)\\d{8}$")
        if (phone.isEmpty()) {
            binding.tilPhone.error = "Số điện thoại không được để trống"
            isValid = false
        } else if (!phoneRegex.matches(phone)) {
            binding.tilPhone.error = "Số điện thoại không hợp lệ (cần 10 số)"
            isValid = false
        }

        return isValid
    }

    private fun resetForm() {
        binding.edtName.text?.clear()
        binding.edtEmail.text?.clear()
        binding.edtPhone.text?.clear()

        binding.tilName.error = null
        binding.tilEmail.error = null
        binding.tilPhone.error = null

        binding.edtName.requestFocus()
    }
}