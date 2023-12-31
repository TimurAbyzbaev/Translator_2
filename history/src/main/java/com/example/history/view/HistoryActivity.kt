package com.example.history.view

import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.core.BaseActivity
import com.example.core.description.DescriptionActivity
import com.example.history.viewmodel.HistoryInteractor
import com.example.history.viewmodel.HistoryViewModel
import com.example.historyscreen.databinding.ActivityHistoryBinding
import com.example.model.AppState
import com.example.model.data.DataModel
import com.example.repository.convertMeaningsToSingleString
import com.example.utils.ui.viewBinding
import org.koin.android.ext.android.inject

class HistoryActivity : BaseActivity<AppState, HistoryInteractor>() {
    private val binding by viewBinding(ActivityHistoryBinding::inflate)
    override lateinit var model: HistoryViewModel
    private val adapter: HistoryAdapter by lazy { HistoryAdapter(::onItemClick) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        iniViewModel()
        initViews()
        subscribeToNetworkChange(binding.root)
    }

    private fun onItemClick(data: DataModel) {
        startActivity(
            DescriptionActivity.getIntent(
                this@HistoryActivity,
                data.text,
                convertMeaningsToSingleString(data.meanings),
                data.meanings[0].imageUrl
            )
        )
    }

    override fun onResume() {
        super.onResume()
        model.getData("", false)
    }

    override fun setDataToAdapter(data: List<DataModel>) {
        adapter.setData(data)
    }

    private fun iniViewModel() {
        if (binding.historyActivityRecyclerview.adapter != null) {
            throw IllegalStateException("The ViewModel should be initialised first")
        }
        val viewModel: HistoryViewModel by inject()
        model = viewModel
        model.subscribe().observe(this@HistoryActivity, Observer<AppState> { renderData(it) })
    }

    private fun initViews() {
        binding.historyActivityRecyclerview.adapter = adapter
    }
}