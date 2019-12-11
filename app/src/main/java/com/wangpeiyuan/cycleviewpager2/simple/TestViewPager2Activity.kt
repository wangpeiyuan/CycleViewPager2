package com.wangpeiyuan.cycleviewpager2.simple

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

/**
 * Created by wangpeiyuan on 2019-12-11.
 */
class TestViewPager2Activity : AppCompatActivity() {

    private val resList = listOf(R.drawable.one, R.drawable.two, R.drawable.three, R.drawable.four)
    private lateinit var viewpager2: ViewPager2
    private var items = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_viewpager2)
        initData()
        viewpager2 = findViewById(R.id.viewpager2)
        viewpager2.adapter = SimapleAdapter(this)
    }

    private fun initData() {
        items.add(resList[0])
        items.add(resList[1])
        items.add(resList[2])
        items.add(resList[3])
    }

    inner class SimapleAdapter(fragmentActivity: FragmentActivity) :
        FragmentStateAdapter(fragmentActivity) {
        override fun getItemCount(): Int = items.size

        override fun createFragment(position: Int): Fragment {
            return PagerFragment(items[position], position)
        }

    }
}