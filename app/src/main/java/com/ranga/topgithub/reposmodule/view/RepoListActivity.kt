package com.ranga.topgithub.reposmodule.view

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ranga.topgithub.R
import com.ranga.topgithub.data.model.Result
import com.ranga.topgithub.reposmodule.view.adapter.RepoListAdapter
import com.ranga.topgithub.reposmodule.viewmodel.RepoListViewModel
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_repo_list.*
import kotlinx.android.synthetic.main.tool_bar.*
import javax.inject.Inject

class RepoListActivity : DaggerAppCompatActivity() {

    companion object{
        private val TAG: String = RepoListActivity::class.java.simpleName
    }
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by lazy{ viewModelFactory.create(RepoListViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_list)
        super.setSupportActionBar(toolBar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        setUpRecyclerView()
        setUpSwipeRefresh()
        setupRepoList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        return  super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.sortByName -> {
                Log.d(TAG, "sortByName") //future scope
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onResume() {
        super.onResume()
        fetchRepos(false)
    }

    private fun setUpRecyclerView() = with(rvTopRepos) {
        adapter =
            RepoListAdapter(mutableListOf())
        addItemDecoration(
            androidx.recyclerview.widget.DividerItemDecoration(
                context,
                androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
            ).apply {
                val drawable = androidx.core.content.ContextCompat.getDrawable(context, R.drawable.devider_decorator)
                if (drawable != null) setDrawable(drawable)
            })
    }

    private fun setUpSwipeRefresh() {
        swipeRefresh.setOnRefreshListener {
            fetchRepos(true)
        }
    }

    private fun fetchRepos(forceRefresh: Boolean){
        viewModel.fetchRepoList(forceRefresh)
    }

    private fun setupRepoList(){
        viewModel.getRepoList().observe(this, Observer{ result->
            when(result){
                is Result.Success -> (rvTopRepos.adapter as RepoListAdapter)
                    .setItems(result.data)
                is Result.Error -> Log.d("Error", result.toString())
            }
        })
    }

}
