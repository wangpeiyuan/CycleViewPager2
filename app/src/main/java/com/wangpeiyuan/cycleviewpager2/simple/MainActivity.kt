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
import androidx.viewpager2.widget.ViewPager2
import com.wangpeiyuan.cycleviewpager2.CycleViewPager2
import com.wangpeiyuan.cycleviewpager2.CycleViewPager2Helper
import com.wangpeiyuan.cycleviewpager2.adapter.CyclePagerAdapter
import com.wangpeiyuan.cycleviewpager2.indicator.DotsIndicator
import com.wangpeiyuan.cycleviewpager2.util.Logger

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    private val resList = listOf(R.drawable.one, R.drawable.two, R.drawable.three, R.drawable.four)
    private lateinit var banner: CycleViewPager2
    private var bannerItems = mutableListOf<Int>()

    private lateinit var verticalCycleViewPager: CycleViewPager2
    private var textList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Logger.setIsDebug(true)
        banner = findViewById(R.id.banner)
        verticalCycleViewPager = findViewById(R.id.vertical_cycleViewPager)
        setBannerData()
        setTextData()
    }

    private fun setBannerData() {
        initData()

        val adapter = MyCyclePagerAdapter()

        findViewById<Button>(R.id.btn_add).setOnClickListener {
            bannerItems.add(resList.random())
            adapter.notifyDataSetChanged()
        }
        findViewById<Button>(R.id.btn_remove).setOnClickListener {
            if (bannerItems.isNotEmpty()) {
                val index = bannerItems.size - 1
                bannerItems.removeAt(index)
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
        bannerItems.add(resList[0])
        bannerItems.add(resList[1])
        bannerItems.add(resList[2])
        bannerItems.add(resList[3])
    }

    private fun setTextData() {
        textList.add(resources.getString(R.string.text_scroll_01))
        textList.add(resources.getString(R.string.text_scroll_02))
        textList.add(resources.getString(R.string.text_scroll_03))

        val adapter = TextCyclePagerAdapter()
        CycleViewPager2Helper(verticalCycleViewPager)
            .setOrientation(ViewPager2.ORIENTATION_VERTICAL)
            .setAdapter(adapter)
            .setAutoTurning(2000L)
            .build()
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

        override fun getRealItemCount(): Int = bannerItems.size

        override fun onBindRealViewHolder(holder: PagerViewHolder, position: Int) {
            Log.d(TAG, "onBindRealViewHolder $position")
            holder.ivPager.setImageResource(bannerItems[position])
            holder.tvTitle.text = position.toString()
        }

    }

    private inner class PagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivPager: ImageView = itemView.findViewById(R.id.iv_pager)
        var tvTitle: TextView = itemView.findViewById(R.id.tv_title)
    }

    private inner class TextCyclePagerAdapter :
        CyclePagerAdapter<TextViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextViewHolder {
            return TextViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_vertical_cycleviewpager, parent, false)
            )
        }

        override fun getRealItemCount(): Int = textList.size

        override fun onBindRealViewHolder(holder: TextViewHolder, position: Int) {
            holder.tvItemContent.text = textList[position]
        }

    }

    private inner class TextViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvItemContent: TextView = itemView.findViewById(R.id.tv_item_content)
    }
}
