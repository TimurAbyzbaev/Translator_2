package com.example.translator_2.presentation.view.main


import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import com.example.core.description.DescriptionActivity
import com.example.history.view.HistoryActivity
import com.example.model.AppState
import com.example.model.data.DataModel
import com.example.repository.convertDataModelToHistoryEntity
import com.example.repository.convertMeaningsToSingleString
import com.example.translator_2.R
import com.example.translator_2.databinding.ActivityMainBinding
import com.example.translator_2.presentation.viewmodels.main.MainInteractor
import com.example.translator_2.presentation.viewmodels.main.MainViewModel
import com.example.utils.ui.viewBinding
import org.koin.android.ext.android.inject


private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG = "74a54328-5d62-46bf-ab6b-cbf5fgt0-092395"

class MainActivity : com.example.core.BaseActivity<AppState, MainInteractor>() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    override lateinit var model: MainViewModel
    private val adapter: MainAdapter by lazy { MainAdapter(::onItemClick) }

    private val fabClickListener: View.OnClickListener =
        View.OnClickListener {
            val searchDialogFragment = SearchDialogFragment.newInstance()
            searchDialogFragment.setOnSearchClickListener(onSearchClickListener)
            searchDialogFragment.show(supportFragmentManager, BOTTOM_SHEET_FRAGMENT_DIALOG_TAG)
        }

    private fun onItemClick(data: DataModel) {
        model.saveInDB(convertDataModelToHistoryEntity(data))
        startActivity(
            DescriptionActivity.getIntent(
                this@MainActivity,
                data.text,
                convertMeaningsToSingleString(data.meanings),
                data.meanings[0].imageUrl
            )
        )
    }

    private val onSearchClickListener: SearchDialogFragment.OnSearchClickListener =
        object : SearchDialogFragment.OnSearchClickListener {
            override fun onClick(searchWord: String) {
                if (isNetworkAvailable) {
                    model.getData(searchWord, isNetworkAvailable)
                } else {
                    showNoInternetConnectionDialog()
                }
            }
        }

    private val onHistorySearchClickListener: SearchDialogFragment.OnSearchClickListener =
        object : SearchDialogFragment.OnSearchClickListener {
            override fun onClick(searchWord: String) {
                model.getData(searchWord, false)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        iniViewModel()
        initViews()
        subscribeToNetworkChange(binding.root)
    }

    override fun setDataToAdapter(data: List<DataModel>) {
        adapter.setData(data)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.history_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_history -> {
                startActivity(Intent(this, HistoryActivity::class.java))
                true
            }
            R.id.menu_history_search -> {
                val searchDialogFragment = SearchDialogFragment.newInstance()
                searchDialogFragment.setOnSearchClickListener(onHistorySearchClickListener)
                searchDialogFragment.show(supportFragmentManager, BOTTOM_SHEET_FRAGMENT_DIALOG_TAG)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun iniViewModel() {
        if (binding.mainActivityRecyclerview.adapter != null) {
            throw IllegalStateException("The ViewModel should be initialised first")
        }

        val viewModel: MainViewModel by inject()
        model = viewModel
        model.subscribe().observe(this@MainActivity, Observer<AppState> { renderData(it) })
    }

    private fun initViews() {
        binding.searchFab.setOnClickListener(fabClickListener)
        binding.mainActivityRecyclerview.adapter = adapter
    }
}