package com.example.chat_bot.Activities.HomePage

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.chat_bot.Ac.DashboardFragment

class PagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount() = 3


    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> {ChatFragment()}
            1 -> {ExerciseFragment()}
            2 -> { DashboardFragment()
            }
            else ->{throw Resources.NotFoundException("Position Not Found!!")}
        }

    }



}