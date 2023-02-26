package com.example.chat_bot.Activities.Welcomepage

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.widget.ImageView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.chat_bot.R

class Seedsinfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seedsinfo)

        val backbtn = findViewById<ImageView>(R.id.Backbutton)

        backbtn.setOnClickListener{
            val intent = Intent (this, WelcomePage::class.java)
            startActivity(intent)
            finish()
        }


    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            val intent = Intent(this@Seedsinfo, WelcomePage::class.java)
            startActivity(intent)
            finish()
        }
        return super.onKeyDown(keyCode, event)
    }

}