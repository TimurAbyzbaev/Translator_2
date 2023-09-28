package com.example.translator_2.presentation.view.history

import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.translator_2.databinding.ActivityHistoryBinding
import com.example.translator_2.model.AppState
import com.example.translator_2.model.data.DataModel
import com.example.translator_2.presentation.view.base.BaseActivity
import com.example.translator_2.presentation.viewmodels.history.HistoryInteractor
import com.example.translator_2.presentation.viewmodels.history.HistoryViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryActivity : BaseActivity<AppState, HistoryInteractor>() {
    private lateinit var binding: ActivityHistoryBinding
    override lateinit var model: HistoryViewModel
    private val adapter: HistoryAdapter by lazy { HistoryAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        iniViewModel()
        initViews()
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
        val viewModel: HistoryViewModel by viewModel()
        model = viewModel
        model.subscribe().observe(this@HistoryActivity, Observer<AppState> { renderData(it) })
    }

    private fun initViews() {
        binding.historyActivityRecyclerview.adapter = adapter
    }
}