package com.lans.recipein_mobile.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.lans.recipein_mobile.presentation.favorite.folder.FolderFragment
import com.lans.recipein_mobile.presentation.favorite.liked.LikedFragment

class FavoritePagerAdapter(fm: FragmentManager, private val fragmentCount: Int) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val fragmentTitleList = mutableListOf("Disukai", "Folder")

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> LikedFragment()
            1 -> FolderFragment()
            else -> LikedFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return fragmentTitleList[position]
    }

    override fun getCount(): Int = fragmentCount
}