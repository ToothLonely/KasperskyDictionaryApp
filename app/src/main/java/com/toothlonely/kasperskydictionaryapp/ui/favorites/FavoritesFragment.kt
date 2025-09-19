package com.toothlonely.kasperskydictionaryapp.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.toothlonely.kasperskydictionaryapp.databinding.FragmentFavoritesBinding
import kotlinx.coroutines.launch

class FavoritesFragment : Fragment() {
    private var _favoritesFragmentBinding: FragmentFavoritesBinding? = null
    private val favoritesFragmentBinding
        get() = _favoritesFragmentBinding ?: throw IllegalStateException(
            "Binding for FavoritesFragmentBinding mustn't be null"
        )

    private val viewModel: FavoritesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.viewModelScope.launch {
            viewModel.initFavorites()
        }
        _favoritesFragmentBinding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return favoritesFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {

        val favoritesAdapter =
            FavoritesListAdapter(viewModel.favoritesLiveData.value?.favoritesDataSet ?: emptyList())

        favoritesAdapter.setOnClickDeleteListener(object :
            FavoritesListAdapter.OnDeleteClickListener {
            override fun onClickDelete(id: Int) {
                with(viewModel) {
                    viewModelScope.launch {
                        deleteFromFavorites(id)
                    }
                }
            }
        })

        favoritesFragmentBinding.rvFavorites.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = favoritesAdapter
        }

        viewModel.favoritesLiveData.observe(viewLifecycleOwner, Observer {
            favoritesAdapter.setNewSet(it.favoritesDataSet)
            with(favoritesFragmentBinding) {

                rvFavorites.visibility =
                    if (it.isFavoritesListVisible) View.VISIBLE else View.GONE

                tvEmptyFavorites.visibility =
                    if (it.isFavoritesListVisible) View.GONE else View.VISIBLE

                btnBackToMain.setOnClickListener {
                    viewModel.openMainFragment(this@FavoritesFragment)
                }
            }
        })
    }
}