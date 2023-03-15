package com.example.chat_bot.Activities.acivity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.chat_bot.R
import com.example.chat_bot.databinding.ProfileCardBinding
import com.example.chat_bot.utils.SessionManager

private lateinit var binding: ProfileCardBinding
lateinit var session: SessionManager
lateinit var username: String

class profile_card : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_card)

        session = SessionManager(applicationContext)
        var user = session.getUserDetails()
        username = user.get("name").toString()
        showUserProfile()
    }

    @SuppressLint("SetTextI18n")
    private fun showUserProfile() {

        val user = session.getUserDetails()


        val age = user["age"]
        println(age)
        val name = user["name"]
        val grade = user["grade"]

        val materialLang = user["KEY_materialLang"]

        Log.d("SettingsFragment", "age: $age")
        Log.d("SettingsFragment", "name: $name")
        Log.d("SettingsFragment", "grade: $grade")
        Log.d("SettingsFragment", "materialLang: $materialLang")


        binding.profilematerialLang.text = materialLang
        binding.profileUserage.text = age
        binding.profileUsername.text = name
        binding.profilegrade.text= grade



    }

}