package com.example.chat_bot.Activities.HomePage
import android.app.Activity
import android.content.Intent
import android.content.res.Resources
import android.net.http.SslCertificate.restoreState
import android.os.Bundle
import android.os.Parcelable
import android.os.PersistableBundle
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.chat_bot.Activities.Welcomepage.WelcomePage
import com.example.chat_bot.R
import com.example.chat_bot.databinding.ActivityHomeBinding
import com.example.chat_bot.utils.SessionManager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.yariksoffice.lingver.Lingver


class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2
    private lateinit var session: SessionManager
    val fragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(android.R.style.Theme_Light_NoTitleBar_Fullscreen)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        session = SessionManager(this)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tabLayout = binding.tabslayout
        viewPager2 = binding.viewpager
        viewPager2.adapter = PagerAdapter(this)
        TabLayoutMediator(tabLayout, viewPager2){ tab, index ->
            tab.text = when(index){
                0 -> {"Chat"}
                1 -> applicationContext.resources.getString(R.string.mainpage_ex_heading)
                2 -> applicationContext.resources.getString(R.string.mainpage_Dashboard_heading)
                else ->{throw Resources.NotFoundException("Position Not Found!!")}
            }

        }.attach()



    }

}