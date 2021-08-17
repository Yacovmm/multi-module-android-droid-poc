package com.example.pokemonmuiltimodulearch.di

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.pokemonmuiltimodulearch.R
import com.example.pokemonmuiltimodulearch.Teste
import com.ncapdevi.fragnav.FragNavController
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import features.feature_a.FeatureAFirstFragment
import features.feature_a.FeatureASecondFragment
import features.feature_a_public.navigation.IFeatureANavigation
import javax.inject.Inject


@Module
@InstallIn(ActivityComponent::class)
class NavigationModule {



    @Provides
    fun provideTeste(activity: Activity): Teste {
        return activity as Teste
    }

    @Provides
    @ActivityScoped
    fun provideFragNavController(teste: Teste): FragNavController {
        return FragNavController(teste.getSupportFragmentManagerTeste(), R.id.nav_host_fragment)
    }

    @Provides
    @ActivityScoped
    fun provideIFeatureANavigator(fragNavController: FragNavController): IFeatureANavigation {
        return ScreenNavigation(fragNavController)
    }

}


class ScreenNavigation @Inject constructor(
    private val mFragNavController: FragNavController
): IFeatureANavigation {

    fun init(savedInstanceState: Bundle?) {
        mFragNavController.rootFragmentListener = mRootFragmentListener
        mFragNavController.initialize(FragNavController.TAB1, savedInstanceState)
    }

    fun onSaveInstanceState(saveInstanceState: Bundle?) {
        mFragNavController.onSaveInstanceState(saveInstanceState)
    }


    override fun navigateToFeatureB(param: String) {
        mFragNavController.pushFragment(FeatureASecondFragment.newInstance(param2 = param))
    }

    fun navigateBack(): Boolean {
        return if (mFragNavController.isRootFragment) {
            false
        } else {
            mFragNavController.popFragment()
            true
        }
    }

    private val mRootFragmentListener: FragNavController.RootFragmentListener = object :
        FragNavController.RootFragmentListener {

        override val numberOfRootFragments: Int
            get() = 1

        override fun getRootFragment(index: Int): Fragment {
            return when (index) {
                FragNavController.TAB1 -> FeatureAFirstFragment.newInstance();
                else -> throw IllegalStateException("unsupported tab index: " + index)
            }
        }
    };

}