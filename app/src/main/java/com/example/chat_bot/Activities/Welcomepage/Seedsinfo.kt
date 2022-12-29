package com.example.chat_bot.Activities.Welcomepage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.chat_bot.R

class Seedsinfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seedsinfo)

        val backbtn = findViewById<ImageView>(R.id.Backbutton)

        backbtn.setOnClickListener{
            finish()
        }
    }
}