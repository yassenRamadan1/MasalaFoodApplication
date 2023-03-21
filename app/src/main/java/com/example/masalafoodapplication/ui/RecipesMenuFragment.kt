package com.example.masalafoodapplication.ui
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.masalafoodapplication.databinding.FragmentRecipesMenuBinding

class RecipesMenuFragment :BaseFragment<FragmentRecipesMenuBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRecipesMenuBinding
        get() = FragmentRecipesMenuBinding::inflate

    override fun onStart() {
        super.onStart()
        onClicks()
    }

    override fun setup() {
    }

    override fun onClicks() {
    binding.icArrowBack.setOnClickListener{
        Log.d("test","clicked")
    }
    }
}