package com.example.chat_bot.Ac

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.compose.ui.text.capitalize
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.chat_bot.Activities.HomePage.HomeActivity
import com.example.chat_bot.Activities.Welcomepage.WelcomePage
import com.example.chat_bot.Activities.acivity.downloadQuizActivity
import com.example.chat_bot.Activities.acivity.quiz_home
import com.example.chat_bot.R
import com.example.chat_bot.Room.Dao.SeedsDao
import com.example.chat_bot.Room.SeedsDatabase
import com.example.chat_bot.databinding.FragmentDashboardBinding
import com.example.chat_bot.utils.AppMode
import com.example.chat_bot.utils.Language
import com.example.chat_bot.utils.SessionManager
import com.yariksoffice.lingver.Lingver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*


class DashboardFragment : Fragment() {

    private lateinit var bind: FragmentDashboardBinding
    lateinit var session: SessionManager
    var appmode: AppMode? = null
    lateinit var lang: Language
    var m_androidId: String? = null
    private val USER = "M-f8f2e818-808f-"
    lateinit var userename: String
    var pref_material_language: String = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        session = SessionManager(this.requireContext())
        var user = session.getUserDetails()

        userename = user.get("name").toString()


        bind = FragmentDashboardBinding.inflate(layoutInflater, container, false)

//        bind.materialLanguage.visibility = View.VISIBLE
        showUserProfile()
//
//
        bind.signoutTv.setOnClickListener { handleClicks() }


        return bind.root


    }


    @SuppressLint("HardwareIds")
    private fun getDevID() {
        m_androidId =
            Settings.Secure.getString(requireContext().contentResolver, Settings.Secure.ANDROID_ID)
        m_androidId = "$m_androidId/" + UUID.randomUUID().toString()
        //Toast.makeText(this, m_androidId.toString(), Toast.LENGTH_SHORT).show()
        Log.d("dev_id", "Device ID: $m_androidId")
    }


    @SuppressLint("SetTextI18n")
    private fun showUserProfile() {

        val user = session.getUserDetails()
        val name = user["name"]

        Log.d("DashboardFragment", "name: $name")

        bind.profileUsername.text = getString(R.string.Hello) + " " + name?.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.getDefault()
            ) else {
                it.toString()
            }
        } + ","

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        bind.AccessCodeHere.setOnClickListener {
            alertbox_accessCode()
        }

        bind.profileCard.setOnClickListener {
            alertbox_profile()
        }

        bind.SettingsCardview.setOnClickListener {
            val intent =
                Intent(this.context, com.example.chat_bot.Activities.acivity.Settings::class.java)
            startActivity(intent)
        }

//        bind.complexTV.setOnClickListener {  loadHome()}
//
//        bind.contactTv.setOnClickListener { open_Contact_dialog() }
//
        bind.downloadIcon.setOnClickListener {
            showDownload()
        }
//      //  handle_clicks()
//        onBackPressed()


    }

    private fun open_Contact_dialog() {
        val builder = AlertDialog.Builder(context, R.style.CustomAlertDialog)
            .create()

        val view = LayoutInflater.from(context).inflate(R.layout.contact_us_dialog, null)

        builder.setView(view)
        val btnclose = view.findViewById<Button>(R.id.close_contact_dialog)

        btnclose.setOnClickListener {

            builder.dismiss()
        }
        builder.show()
    }

    private fun showDownload() {
        val intent = Intent(context, downloadQuizActivity::class.java).apply {

        }
        startActivity(intent)
    }

    private fun loadHome() {
        val intent = Intent(context, quiz_home::class.java).apply {


        }
        startActivity(intent)
    }


    private fun handleClicks() {
        session.logoutUser()
        (context as Activity).finish()

    }

    fun alertbox_profile() {

        val builder = AlertDialog.Builder(context, R.style.PauseDialog)
            .create()
        val view = LayoutInflater.from(context).inflate(R.layout.profile_card, null)

        val button = view.findViewById<Button>(R.id.profile_closebtn)
        builder.setView(view)
        button.setOnClickListener {
            builder.dismiss()
        }

        val user = session.getUserDetails()


        val age = user["age"]
        println(age)
        val name = user["name"]
        val grade = user["grade"]

        val materialLang = user["KEY_materialLang"]

        Log.d("DashboardFragment", "age: $age")
        Log.d("DashboardFragment", "name: $name")
        Log.d("DashboardFragment", "grade: $grade")
        Log.d("DashboardFragment", "materialLang: $materialLang")

        val profilemateriallang = view.findViewById<TextView>(R.id.profilematerialLang)
        val profileuserage = view.findViewById<TextView>(R.id.profileUserage)
        val profilegrade = view.findViewById<TextView>(R.id.profilegrade)
        val profileusername = view.findViewById<TextView>(R.id.profilecardUsername)


        profileusername.text = name?.capitalize()
        profilemateriallang.text = materialLang
        profileuserage.text = age
        profilegrade.text = grade



        builder.setCanceledOnTouchOutside(false)
        builder.show()

    }

    fun alertbox_accessCode() {

        val builder = AlertDialog.Builder(context, R.style.PauseDialog)
            .create()
        val view = LayoutInflater.from(context).inflate(R.layout.aceess_code_dialog, null)

        val et = view.findViewById<EditText>(R.id.code_et)

        val button = view.findViewById<Button>(R.id.code_return_to_chat)
        builder.setView(view)
        button.setOnClickListener {
//            val intent = Intent(context, HomeActivity::class.java)
//            startActivity(intent)
            val code = et.text.trim()
            if (code.isNotEmpty()) {
                Log.d("SettingsFragment", "Access Code: ${code.toString()}")
                Toast.makeText(context, "access code saved!!!", Toast.LENGTH_SHORT).show()
                session.savePrivateMaterialsAccessCode(code.toString())
            }

            builder.dismiss()

        }
        builder.setCanceledOnTouchOutside(false)
        builder.show()
    }



    fun alertbox_materiallanguage() {
        // Toast.makeText(context, "wow", Toast.LENGTH_SHORT).show()
        val builder = AlertDialog.Builder(context, R.style.CustomAlertDialog)
            .create()

        val view = LayoutInflater.from(context).inflate(R.layout.material_language_dialog, null)

        builder.setView(view)
        val btnGerman = view.findViewById<Button>(R.id.btn_german)

        btnGerman.setOnClickListener {
            session.save_materialLangPref("German")
            pref_material_language = "German"
            UpdateMaterialLang(pref_material_language)
            (context as Activity).recreate()

            builder.dismiss()
        }

        val btnSpanish = view.findViewById<Button>(R.id.btn_spanish)
        btnSpanish.setOnClickListener {
            session.save_materialLangPref("Spanish")
            pref_material_language = "Spanish"
            UpdateMaterialLang(pref_material_language)
            (context as Activity).recreate()
            // CoroutineScope(Dispatchers.IO).launch { UpdateMaterialLang() }
            builder.dismiss()
        }

        val btnGreek = view.findViewById<Button>(R.id.btn_greek)
        btnGreek.setOnClickListener {
            session.save_materialLangPref("Greek").toString()
            pref_material_language = "Greek"
            UpdateMaterialLang(pref_material_language)
            (context as Activity).recreate()
            //  CoroutineScope(Dispatchers.IO).launch { UpdateMaterialLang() }
            builder.dismiss()
        }

        val btnEnglish = view.findViewById<Button>(R.id.btn_english)
        btnEnglish.setOnClickListener {
            session.save_materialLangPref("English")
            pref_material_language = "English"
            UpdateMaterialLang(pref_material_language)
            (context as Activity).recreate()
            //  CoroutineScope(Dispatchers.IO).launch { UpdateMaterialLang() }
            builder.dismiss()
        }


        val button = view.findViewById<Button>(R.id.code_return_to_chat)

        button.setOnClickListener {
            val intent = Intent(context, HomeActivity::class.java)
            context?.startActivity(intent)
            (context as Activity).finish()


        }
        builder.setCanceledOnTouchOutside(false)
        builder.show()
    }


    private fun UpdateMaterialLang(language: String) {
        val dao: SeedsDao = SeedsDatabase.getInstance(context as Activity).seedsDao
        lifecycleScope.launch(Dispatchers.IO) {

            dao.updatedMaterialLanguage(language, userename.toString())

        }
    }







//    fun onBackpressed(){
//        val intent = Intent(context, HomeActivity::class.java)
//        startActivity(intent)
//    }

//    fun onBackPressed() {
//        val count = childFragmentManager.backStackEntryCount
//        if (count == 0) {
//            super.onBackPressed()
//            //additional code
//        } else {
//            childFragmentManager.popBackStack()
//        }
//    }

//    fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            val intent = Intent(context, HomeActivity::class.java)
//            startActivity(intent)
//        }
//    }

//   override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            val intent = Intent(requireContext(), HomeActivity::class.java)
//            startActivity(intent)
//            return true
//        }
//        return true
//    }
//
//}

//    private fun Fragment.onBackPressed() {
//        val count = childFragmentManager.backStackEntryCount
//        if (count == 0) {
//            return onBackPressed()
//        //additional code
//        } else {
//            childFragmentManager.popBackStack()
//        }
//    }
}


