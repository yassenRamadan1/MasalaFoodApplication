package com.example.masalafoodapplication.ui.suggestion

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.masalafoodapplication.data.DataManager
import com.example.masalafoodapplication.data.domain.models.Food
import com.example.masalafoodapplication.databinding.FragmentSuggestionsBinding
import com.example.masalafoodapplication.ui.base.BaseFragment
import com.example.masalafoodapplication.ui.food_detail.FoodDetailFragment
import com.example.masalafoodapplication.ui.suggestion.adapters.SuggestionsAdapter
import com.example.masalafoodapplication.util.Constants
import com.example.masalafoodapplication.util.SuggestionOnClick
import com.example.masalafoodapplication.util.SuggestionsItems

class SuggestionsFragment : BaseFragment<FragmentSuggestionsBinding>(), SuggestionOnClick {
    private var data: List<String>? = null
    private lateinit var adapter: SuggestionsAdapter
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSuggestionsBinding
        get() = FragmentSuggestionsBinding::inflate

    override fun setup() {
        listenToFragmentResult()
    }

    override fun onClicks() {
        binding.sugTbTopAppBar.setOnClickListener {
            onBack()
        }
    }

    private fun bind(data: List<String>): List<SuggestionsItems> {
        val list = mutableListOf<SuggestionsItems>()
        list.add(
            SuggestionsItems(
                Constants.BREAKFAST, DataManager.splitFoodsIntoThreeMeals(Constants.BREAKFAST, data)
            )
        )

        list.add(
            SuggestionsItems(
                Constants.LUNCH, DataManager.splitFoodsIntoThreeMeals(Constants.LUNCH, data)
            )
        )

        list.add(
            SuggestionsItems(
                Constants.DINNER, DataManager.splitFoodsIntoThreeMeals(Constants.DINNER, data)
            )
        )

        return list
    }

    override fun onClickListener(food: Food) {
        newInstance(food.id, Constants.KEY_FOOD_ID)
        parentFragmentManager.popBackStack()
        transitionToWithBackStackReplace(FoodDetailFragment(), Constants.SUGGESTIONS)
    }

    private fun listenToFragmentResult() {
        parentFragmentManager.setFragmentResultListener(
            Constants.SUGGESTION_FILTER,
            this
        ) { _, result ->
            data = result.getStringArrayList(Constants.SUGGESTION_FILTER)
            adapter = data?.let { bind(it) }?.let { SuggestionsAdapter(it, this) }!!
            binding.sugRv.adapter = adapter
        }
    }

}