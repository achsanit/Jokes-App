package com.achsanit.jokesapp.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import androidx.navigation.findNavController
import com.achsanit.jokesapp.R
import com.achsanit.jokesapp.databinding.ActivityMainBinding
import com.achsanit.jokesapp.ui.home.HomeViewModel
import com.achsanit.jokesapp.utils.*
import kotlinx.coroutines.flow.update
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val homeViewModel: HomeViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.nav_host_main_fragment)

        with(binding) {
            searchView.setupWithSearchBar(searchBar) // setup searchView with searchBar
            searchView.editText.setOnEditorActionListener { tv, _, event ->
                    if (event == null || event.action != KeyEvent.ACTION_DOWN) {
                        false
                    } else {
                        searchBar.setText(searchView.text)
                        homeViewModel.searchJokes(tv.text.toString())
                        homeViewModel.query.update { tv.text.toString() }
                        searchView.hide()

                        if (navController.currentDestination?.id != R.id.searchFragment) {
                            navController.navigate(R.id.action_homeFragment_to_searchFragment)
                        }

                        true
                    }
                }

            // clear searchBar and searchView when icon back clicked
            ivBack.setOnClickListener {
                searchBar.clearText()
                searchView.clearText()
                it.makeGone()
                navController.navigate(R.id.action_searchFragment_to_homeFragment)
            }

            // set for visibility back icon
            collectLatestState(homeViewModel.query) {
                if (it.isNotBlank()) {
                    ivBack.makeVisible()
                } else {
                    ivBack.makeGone()
                }
            }
        }
    }
}