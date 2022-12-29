package com.example.chat_bot.Activities

import android.content.Intent
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.lifecycle.MutableLiveData
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.example.chat_bot.R

class IntroductionActivity : AppCompatActivity() {
    private lateinit var skipButton: Button
    private lateinit var letsGoButton: Button
    private lateinit var slideViewPager: ViewPager
    private lateinit var indicatorLayout: LinearLayout
    private lateinit var indicators: Array<TextView?>
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private val currentPage : MutableLiveData<Int> =  MutableLiveData<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_introduction)

        setupListeners()
    }

    private fun setupListeners() {
        setupSkipButton()
        setupLetsGoButton()
        setupSlideViewPager()
        setupIndicatorLayout()
        setupPageListener()
        setUpIndicator(0)
    }

    private fun setupSkipButton() {
        skipButton = findViewById(R.id.skipButton)

        skipButton.setOnClickListener {
            slideViewPager.currentItem = viewPagerAdapter.count - 1
        }
    }

    private fun setupLetsGoButton() {
        letsGoButton = findViewById(R.id.lets_go_button)
        letsGoButton.visibility = GONE
        letsGoButton.setOnClickListener {
            startActivity(Intent(this, Register::class.java))
        }
    }

    private fun setupSlideViewPager() {
        slideViewPager = findViewById(R.id.slideViewPager)
        viewPagerAdapter = ViewPagerAdapter(this)
        slideViewPager.adapter = viewPagerAdapter
        slideViewPager.addOnPageChangeListener(viewListener)
    }

    private fun setupIndicatorLayout() {
        indicatorLayout = findViewById(R.id.indicator_layout)
        indicatorLayout.visibility = VISIBLE
    }

    private fun setupPageListener() {
        currentPage.observe(this) {
            setupViewVisibility(it)
            setUpIndicator(it)
        }
    }

    private fun setupViewVisibility(position: Int) {
        if (position == viewPagerAdapter.count - 1) {
            skipButton.visibility = GONE
            letsGoButton.visibility = VISIBLE
            indicatorLayout.visibility = GONE
        } else {
            skipButton.visibility = VISIBLE
            letsGoButton.visibility = GONE
            indicatorLayout.visibility = VISIBLE
        }
    }

    private fun setUpIndicator(position: Int) {
        indicators = arrayOfNulls(viewPagerAdapter.count)
        indicatorLayout.removeAllViews()

        for (i in indicators.indices) {
            indicators[i] = TextView(this)
            indicators[i]?.text = HtmlCompat.fromHtml("&#8226", HtmlCompat.FROM_HTML_MODE_LEGACY)
            indicators[i]?.textSize = 35f
            indicators[i]?.setTextColor(resources.getColor(R.color.inactive, applicationContext.theme))
            indicatorLayout.addView(indicators[i])
        }

        indicators[position]?.setTextColor(resources.getColor(R.color.active, applicationContext.theme))
    }

    private var viewListener: OnPageChangeListener = object : OnPageChangeListener {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {}

        override fun onPageSelected(position: Int) {
            currentPage.value = position
        }

        override fun onPageScrollStateChanged(state: Int) {}
    }
}