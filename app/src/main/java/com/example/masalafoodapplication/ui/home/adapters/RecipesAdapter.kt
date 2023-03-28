package com.example.masalafoodapplication.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.masalafoodapplication.data.domain.models.Food
import com.example.masalafoodapplication.databinding.ItemFoodBinding
import com.example.masalafoodapplication.ui.base.BaseAdapter2
import com.example.masalafoodapplication.ui.home.HomeInteractionListener
import com.example.masalafoodapplication.util.loadImage
import com.example.masalafoodapplication.util.setTime

class RecipesAdapter(
    items: List<Food>,
    private val listener: HomeInteractionListener,
) : BaseAdapter2<Food, ItemFoodBinding>(items) {
    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): ItemFoodBinding {
        return ItemFoodBinding.inflate(inflater, parent, false)
    }

    override fun bind(binding: ItemFoodBinding, item: Food) {
        binding.apply {
            recipeName.text = item.recipeName
            prepareTime.text = item.timeMinutes.setTime()
            imageRecipe.loadImage(item.imageUrl)
            root.setOnClickListener { listener.onRecipeClicked(item) }
        }
    }
}