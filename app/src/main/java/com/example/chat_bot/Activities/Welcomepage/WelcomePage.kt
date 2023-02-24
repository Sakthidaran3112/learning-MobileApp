package com.example.chat_bot.Activities.Welcomepage

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import com.example.chat_bot.Activities.IntroductionActivity
import com.example.chat_bot.Activities.Login
import com.example.chat_bot.R
import com.example.chat_bot.databinding.ActivityWelcomepageBinding
import com.example.chat_bot.utils.Language
import com.example.chat_bot.utils.SessionManager
import com.yariksoffice.lingver.Lingver

class WelcomePage : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomepageBinding
    private lateinit var alreadyRegisteredUserButton: Button
    private lateinit var getStartedButton: Button
    private lateinit var infoTextView: TextView
    lateinit var session: SessionManager
    private lateinit var user_language: String


    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(android.R.style.Theme_Light_NoTitleBar_Fullscreen)
        super.onCreate(savedInstanceState)
        binding = setContentView(this, R.layout.activity_welcomepage)
        supportActionBar?.hide()

        setupViews()

        user_language = ""


        val languages = resources.getStringArray(R.array.Languages)
        val arrayAdapter = ArrayAdapter(this,R.layout.lang_dropdown,languages)

        binding.languageText.setAdapter(arrayAdapter)

    }



    private fun setupViews() {
        setupAlreadyRegisteredUserButton()
        setupGetStartedButton()
        setupInfoTextViewButton()
    }

    private fun setupAlreadyRegisteredUserButton() {
        alreadyRegisteredUserButton = findViewById(R.id.Already_user_button)

        alreadyRegisteredUserButton.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
        }
    }

    private fun setupGetStartedButton() {
        getStartedButton = findViewById(R.id.Get_started_button)

        getStartedButton.setOnClickListener {
            startActivity(Intent(this, IntroductionActivity::class.java))
        }
    }

    private fun setupInfoTextViewButton() {
        infoTextView = findViewById(R.id.infoTextView)

        infoTextView.setOnClickListener {
            startActivity(Intent(this, Seedsinfo::class.java))
        }
    }



    private fun lang(lang: String, adapter: ArrayAdapter<String>) {


        when (lang) {
            "German" -> {

                Lingver.getInstance().setLocale(this, "de")
                recreate()
                adapter.notifyDataSetChanged()


            }
            "Spanish" -> {

                Lingver.getInstance().setLocale(this, "es")
                recreate()
                adapter.notifyDataSetChanged()

            }
            "English" -> {

                Lingver.getInstance().setLocale(this, "en")
                val eng = Lingver.getInstance().getLanguage()
                session.savelanguagePref(eng)
                recreate()
                adapter.notifyDataSetChanged()

            }
            "Greek" -> {

                Lingver.getInstance().setLocale(this, "el")
                val eng = Lingver.getInstance().getLanguage()
                session.savelanguagePref(eng)
                recreate()
                adapter.notifyDataSetChanged()


            }
            else -> session.savelanguagePref(Lingver.getInstance().getLanguage()).toString()
        }
        adapter.notifyDataSetChanged()

    }
    override fun onResume() {
        super.onResume()
    }

}





