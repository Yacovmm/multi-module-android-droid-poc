package com.example.pokemonmuiltimodulearch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.example.pokemonmuiltimodulearch.databinding.ActivityMainBinding
import com.example.pokemonmuiltimodulearch.di.ScreenNavigation
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

interface Teste {
    fun getSupportFragmentManagerTeste(): FragmentManager
}

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), Teste  {

    lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var screenNavigation: ScreenNavigation

    override fun getSupportFragmentManagerTeste(): FragmentManager {
        return supportFragmentManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        fragNavController = FragNavController(supportFragmentManager, R.id.nav_host_fragment)
//        fragNavController.apply {
//            transactionListener = this@BaseActivity
//
//            defaultTransactionOptions = FragNavTransactionOptions.newBuilder().customAnimations(
//                R.anim.slide_in_from_right, R.anim.slide_out_to_left,
//                R.anim.slide_in_from_left, R.anim.slide_out_to_right
//            ).build()
//
//            fragmentHideStrategy = FragNavController.DETACH
//        }
//
//        fragNavController.rootFragments = rootFragments()
//        fragNavController.initialize(FragNavController.TAB1, savedInstanceState)
//

        screenNavigation.init(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        screenNavigation.onSaveInstanceState(outState)
    }

    override fun onBackPressed() {
        if (!screenNavigation.navigateBack()) {
            super.onBackPressed()
        }
    }
}