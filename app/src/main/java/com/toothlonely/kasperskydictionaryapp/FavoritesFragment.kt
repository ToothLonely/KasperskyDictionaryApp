package com.toothlonely.kasperskydictionaryapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.toothlonely.kasperskydictionaryapp.databinding.FragmentFavoritesBinding

class FavoritesFragment : Fragment() {
    private var _favoritesFragmentBinding: FragmentFavoritesBinding? = null
    private val favoritesFragmentBinding
        get() = _favoritesFragmentBinding ?: throw IllegalStateException(
            "Binding for CategoriesListFragmentBinding mustn't be null"
        )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _favoritesFragmentBinding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return favoritesFragmentBinding.root
    }
}