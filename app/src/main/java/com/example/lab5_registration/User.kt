package com.example.lab5_registration
import java.io.Serializable
data class User (
    val name: String,
    val email: String,
    val phone: String
): Serializable