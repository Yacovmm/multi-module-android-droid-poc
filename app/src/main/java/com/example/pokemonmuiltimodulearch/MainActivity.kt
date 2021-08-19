package com.example.pokemonmuiltimodulearch

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.example.pokemonmuiltimodulearch.databinding.ActivityMainBinding
import com.example.pokemonmuiltimodulearch.di.ScreenNavigation
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

interface IFragmentManagerInterface {
    fun getSupportFragmentManagerHelper(): FragmentManager
}

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), IFragmentManagerInterface {

    lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var screenNavigation: ScreenNavigation

    override fun getSupportFragmentManagerHelper(): FragmentManager {
        return supportFragmentManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setupScreenNavigatin(savedInstanceState)

        supportActionBar?.setDisplayHomeAsUpEnabled(screenNavigation.isRootFragment().not())

        setContentView(binding.root)
    }

    private fun setupScreenNavigatin(savedInstanceState: Bundle?) {
        screenNavigation.init(savedInstanceState)
        screenNavigation.transactionCB = { fragment, transactionType ->
            supportActionBar?.setDisplayHomeAsUpEnabled(screenNavigation.isRootFragment().not())
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        screenNavigation.onSaveInstanceState(outState)
    }

    override fun onBackPressed() {
        if (screenNavigation.navigateBack().not()) {
            super.onBackPressed()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return true
    }
}
