package com.lans.recipein_mobile.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.lans.recipein_mobile.presentation.favorite.folder.FolderFragment
import com.lans.recipein_mobile.presentation.favorite.liked.LikedFragment

class FavoritePagerAdapter(fm : FragmentManager, val fragmentCount : Int): FragmentStatePagerAdapter(fm) {

    private val fragmentTitleList = mutableListOf("Disukai", "Folder")

    override fun getItem(position: Int): Fragment {

        when (position) {
            0 -> return LikedFragment()
            1 -> return FolderFragment()
            else -> return LikedFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentTitleList[position]
    }

    override fun getCount(): Int = fragmentCount
}