package com.example.chat_bot.Activities.Welcomepage

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.text.intl.Locale
import androidx.databinding.DataBindingUtil.setContentView
import com.example.chat_bot.Activities.IntroductionActivity
import com.example.chat_bot.Activities.Login
import com.example.chat_bot.Activities.MainActivity
import com.example.chat_bot.R
import com.example.chat_bot.databinding.ActivityWelcomepageBinding
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
        session = SessionManager(applicationContext)
        user_language = ""

    }


    private fun setlang() {
        // access the items of the list
        val languages = resources.getStringArray(R.array.Languages)

        // access the language spinner
        val lang_spinner = binding.langBtnn
        if (lang_spinner != null) {
            val adapter = ArrayAdapter(this,
                R.layout.lang_dropdown, languages)
            lang_spinner.setAdapter(adapter)
            lang_spinner.onItemClickListener =
                AdapterView.OnItemClickListener { parent, view, position, id ->

                    user_language = languages[position]

                    lang(languages[position], adapter)


                }

        }
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
            "Deutsch" -> {

                Lingver.getInstance().setLocale(this, "de")
                recreate()
                adapter.notifyDataSetChanged()


            }
            "Español" -> {

                Lingver.getInstance().setLocale(this, "es")
                recreate()
                adapter.notifyDataSetChanged()

            }
            "English" -> {

                Lingver.getInstance().setLocale(this, "en")
                recreate()
                adapter.notifyDataSetChanged()

            }
            "Ελληνικά" -> {

                Lingver.getInstance().setLocale(this, "el")
                recreate()
                adapter.notifyDataSetChanged()

            }
        }
        adapter.notifyDataSetChanged()

    }

    override fun onResume() {
        super.onResume()
        setlang()
    }


}








