package com.toothlonely.kasperskydictionaryapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.toothlonely.kasperskydictionaryapp.databinding.FragmentMainBinding

class MainFragment() : Fragment() {

    private var _mainFragmentBinding: FragmentMainBinding? = null
    private val mainFragmentMainBinding: FragmentMainBinding
        get() = _mainFragmentBinding ?: throw IllegalStateException(
            "Binding for CategoriesListFragmentBinding mustn't be null"
        )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _mainFragmentBinding = FragmentMainBinding.inflate(inflater, container, false)
        return mainFragmentMainBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {

        val historyListAdapter = FavoritesListAdapter(STUB.getOriginals())

        mainFragmentMainBinding.rvTranslateHistory.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = historyListAdapter
        }

        mainFragmentMainBinding.btnFavorites.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_favoritesFragment)
        }
    }
}