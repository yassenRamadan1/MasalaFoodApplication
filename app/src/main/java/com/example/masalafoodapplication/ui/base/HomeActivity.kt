package com.example.masalafoodapplication.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.masalafoodapplication.R
import com.example.masalafoodapplication.data.DataManager
import com.example.masalafoodapplication.data.DataManagerImpl
import com.example.masalafoodapplication.data.datasource.CsvDataSource
import com.example.masalafoodapplication.data.datasource.utils.CsvParser
import com.example.masalafoodapplication.databinding.ActivityBaseBinding
import com.example.masalafoodapplication.ui.explore.ExploreFragment
import com.example.masalafoodapplication.ui.favourite.FavouriteFragment
import com.example.masalafoodapplication.ui.home.HomeFragment
import com.example.masalafoodapplication.ui.suggestionFilter.SuggestionFilterFragment
import com.example.masalafoodapplication.util.SetFragmentType


class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBaseBinding
    private lateinit var dataManager: DataManager


    override fun onCreate(savedInstanceState: Bundle?) {
        initDataManager(savedInstanceState)
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initSubViews()
        setup()
        onClicks()
    }

    private fun setup() {

    }

    fun getDataManager(): DataManager {
        return dataManager
    }

    private fun onClicks() {
        binding.navBar.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    setFragment(HomeFragment(), SetFragmentType.REPLACE, TAG_HOME_FRAGMENT)
                    true
                }

                R.id.nav_explore -> {
                    setFragment(ExploreFragment(), SetFragmentType.REPLACE, TAG_EXPLORE_FRAGMENT)
                    true
                }

                R.id.nav_make_meal -> {
                    replaceFragment(SuggestionFilterFragment(), TAG_MAKE_MEAL_FRAGMENT)
                    true
                }

                R.id.nav_favourite -> {
                    setFragment(
                        FavouriteFragment(),
                        SetFragmentType.REPLACE,
                        TAG_FAVOURITE_FRAGMENT
                    )
                    true
                }

                else -> false
            }
        }
    }


    private fun initSubViews() {
        setFragment(HomeFragment(), SetFragmentType.REPLACE, TAG_HOME_FRAGMENT)
    }

    private fun setFragment(fragment: Fragment, setFragmentType: SetFragmentType, tag: String) {
        when (setFragmentType) {
            SetFragmentType.ADD -> addFragment(fragment)
            SetFragmentType.REPLACE -> replaceFragment(fragment, tag)
        }
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager.commit {
            add(binding.fragmentContainer.id, fragment)
        }
    }

    private fun replaceFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.commit {
            replace(binding.fragmentContainer.id, fragment, tag)
            setReorderingAllowed(true)
        }
    }

    private fun initDataManager(savedInstanceState: Bundle?) {
        dataManager = when (savedInstanceState) {
            null -> DataManagerImpl(CsvDataSource(CsvParser(), this))
            else -> savedInstanceState.getSerializable(KEY_DATA_MANAGER) as DataManager
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(KEY_DATA_MANAGER, dataManager)
    }

    companion object {
        const val KEY_DATA_MANAGER = "DATA_MANAGER"
        const val TAG_HOME_FRAGMENT = "Home"
        const val TAG_EXPLORE_FRAGMENT = "Explore"
        const val TAG_MAKE_MEAL_FRAGMENT = "MakeMeal"
        const val TAG_FAVOURITE_FRAGMENT = "Favorite"
    }

}

