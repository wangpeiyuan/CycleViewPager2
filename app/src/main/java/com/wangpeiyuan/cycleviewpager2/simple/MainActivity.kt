package com.wangpeiyuan.cycleviewpager2.simple

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.wangpeiyuan.cycleviewpager2.CycleViewPager2
import com.wangpeiyuan.cycleviewpager2.CycleViewPager2Helper
import com.wangpeiyuan.cycleviewpager2.adapter.CyclePagerAdapter
import com.wangpeiyuan.cycleviewpager2.indicator.DotsIndicator
import com.wangpeiyuan.cycleviewpager2.util.Logger

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    private val resList = listOf(R.drawable.one, R.drawable.two, R.drawable.three, R.drawable.four)
    private lateinit var banner: CycleViewPager2
    private var items = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Logger.setIsDebug(true)
        banner = findViewById(R.id.banner)

        initData()

        val adapter = MyCyclePagerAdapter()

        findViewById<Button>(R.id.btn_add).setOnClickListener {
            items.add(resList.random())
            adapter.notifyDataSetChanged()
        }
        findViewById<Button>(R.id.btn_remove).setOnClickListener {
            if (items.isNotEmpty()) {
                val index = items.size - 1
                items.removeAt(index)
                adapter.notifyDataSetChanged()
            }
        }

        val nextItemVisiblePx = resources.getDimension(R.dimen.viewpager_next_item_visible)
        val currentItemHorizontalMarginPx =
            resources.getDimension(R.dimen.viewpager_current_item_horizontal_margin)
        val dotsRadius = resources.getDimension(R.dimen.dots_radius)
        val dotsPadding = resources.getDimension(R.dimen.dots_padding)
        val dotsBottomMargin = resources.getDimension(R.dimen.dots_bottom_margin)

        CycleViewPager2Helper(banner)
            .setAdapter(adapter)
            .setMultiplePagerScaleInTransformer(
                nextItemVisiblePx.toInt(),
                currentItemHorizontalMarginPx.toInt(),
                0.1f
            )
            .setDotsIndicator(
                dotsRadius,
                Color.RED,
                Color.WHITE,
                dotsPadding,
                0,
                dotsBottomMargin.toInt(),
                0,
                DotsIndicator.Direction.CENTER
            )
            .setAutoTurning(3000L)
            .build()
    }

    private fun initData() {
        items.add(resList[0])
        items.add(resList[2])
        items.add(resList[3])
    }

    private inner class MyCyclePagerAdapter :
        CyclePagerAdapter<PagerViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
            Log.d(TAG, "onCreateViewHolder")
            return PagerViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.fragment_pager, parent, false)
            )
        }

        override fun getRealItemCount(): Int = items.size

        override fun onRealBindViewHolder(holder: PagerViewHolder, position: Int) {
            Log.d(TAG, "onRealBindViewHolder $position")
            holder.ivPager.setImageResource(items[position])
            holder.tvTitle.text = position.toString()
        }

    }

    private inner class PagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivPager: ImageView = itemView.findViewById(R.id.iv_pager)
        var tvTitle: TextView = itemView.findViewById(R.id.tv_title)
    }
}
