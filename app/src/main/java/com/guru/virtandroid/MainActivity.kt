package com.guru.virtandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.*
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    val viewModel:SharedViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = PageAdapter(this)
        val viewpager = findViewById<ViewPager2>(R.id.pager)
        viewpager.adapter = adapter
        val tab = findViewById<TabLayout>(R.id.tab_layout)
        TabLayoutMediator(tab, viewpager) { tabLay, pos ->
            if (pos == 0)
                tabLay.text = "String Operation"
            else
                tabLay.text = "Email list"
        }.attach()


    }
}

class PageAdapter(fm: FragmentActivity) : FragmentStateAdapter(fm) {
    private val stringOpFragment: StringOpFragment = StringOpFragment()
    private val emailListFragment: EmailListFragment = EmailListFragment()
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return if (position == 0)
            stringOpFragment
        else
            emailListFragment
    }


}