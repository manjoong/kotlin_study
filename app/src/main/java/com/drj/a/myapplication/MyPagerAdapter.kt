package com.drj.a.myapplication

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by a on 2020. 1. 5..
 */

class MyPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
    private val items = ArrayList<Fragment>()

    override fun getItem(position: Int): Fragment {
        return items[position]
    }

    override fun getCount(): Int {
        return  items.size
    }

    fun  updateFragments(items : List<Fragment>){
        this.items.addAll(items)
    }

}