package com.toothlonely.kasperskydictionaryapp.ui.main

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.toothlonely.kasperskydictionaryapp.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment() : Fragment() {

    private var _mainFragmentBinding: FragmentMainBinding? = null
    private val mainFragmentBinding: FragmentMainBinding
        get() = _mainFragmentBinding ?: throw IllegalStateException(
            "Binding for FavoritesFragmentBinding mustn't be null"
        )

    private val viewModel: MainFragmentViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _mainFragmentBinding = FragmentMainBinding.inflate(inflater, container, false)
        return mainFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {

        val historyListAdapter = HistoryListAdapter(
            viewModel.mainFragmentLiveData.value?.historyDataSet ?: emptyList()
        )

        val reversedLayoutManager = LinearLayoutManager(requireContext())
        reversedLayoutManager.reverseLayout = true
        reversedLayoutManager.stackFromEnd = true

        mainFragmentBinding.rvTranslateHistory.apply {
            layoutManager = reversedLayoutManager
            adapter = historyListAdapter
        }

        historyListAdapter.setOnClickDeleteListener(object :
            HistoryListAdapter.OnDeleteClickListener {
            override fun onClickDelete(id: Int) {
                viewModel.deleteWordFromHistory(id)
            }
        })

        viewModel.mainFragmentLiveData.observe(viewLifecycleOwner, Observer {
            historyListAdapter.setNewSet(it.historyDataSet)

            with(mainFragmentBinding) {

                tvTranslate.text = it.translate

                etOriginal.setText(it.originalWord)

                with(rvTranslateHistory) {
                    scrollToPosition(it.historyDataSet.size - 1)
                    visibility = if (it.isHistoryVisible) View.VISIBLE else View.GONE
                }

                tvEmptyHistory.visibility = if (it.isHistoryVisible) View.GONE else View.VISIBLE

                etOriginal.setOnEditorActionListener { et, actionId, event ->
                    if (event.keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN) {
                        viewModel.searchWord(et.text.toString())
                        true
                    } else false
                }

                btnAddFavorites.setOnClickListener {
                    viewModel.addWordInFavorites()
                }

                btnFavorites.setOnClickListener {
                    viewModel.openFavoritesFragment(this@MainFragment)
                }
            }
        })
    }
}