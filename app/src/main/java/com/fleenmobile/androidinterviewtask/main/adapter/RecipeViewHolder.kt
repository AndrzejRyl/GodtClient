package com.fleenmobile.androidinterviewtask.main.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.fleenmobile.androidinterviewtask.R
import com.fleenmobile.androidinterviewtask.data.Recipe
import com.squareup.picasso.Picasso

typealias OnIngredientsClickedListener = () -> Unit

class RecipeViewHolder(
        private val view: View,
        private val context: Context
) : RecyclerView.ViewHolder(view) {

    @BindView(R.id.recipe_item_photo)
    lateinit var photoImageView: ImageView

    @BindView(R.id.recipe_item_name)
    lateinit var nameTextView: TextView

    @BindView(R.id.recipe_item_description)
    lateinit var descriptionTextView: TextView

    var onIngredientsClickedListener: OnIngredientsClickedListener? = null

    init {
        ButterKnife.bind(this, view)
    }

    fun setRecipe(recipe: Recipe) {
        recipe.photoUrl()?.let {
            Picasso.with(context)
                    .load(it)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(photoImageView)
        } ?: photoImageView.setImageResource(R.drawable.ic_launcher_background)

        nameTextView.text = recipe.title
        descriptionTextView.text = recipe.description
    }

    @OnClick(R.id.recipe_item_ingredients)
    fun onIngredientsClicked() {
        onIngredientsClickedListener?.invoke()
    }
}