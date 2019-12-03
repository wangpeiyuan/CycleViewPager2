package com.wangpeiyuan.cycleviewpager2.simple

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

/**
 * Created by wangpeiyuan on 2019-12-02.
 */
class PagerFragment(var resId: Int, val pos: Int) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pager, container, false)
        view.findViewById<ImageView>(R.id.iv_pager).setImageResource(resId)
        view.findViewById<TextView>(R.id.tv_title).text = pos.toString()
        return view
    }
}