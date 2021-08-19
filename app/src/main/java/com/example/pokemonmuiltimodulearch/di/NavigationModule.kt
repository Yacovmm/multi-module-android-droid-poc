package com.example.pokemonmuiltimodulearch.di

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.auth_private.presentation.main.AuthMainFragment
import com.example.pokemonmuiltimodulearch.IFragmentManagerInterface
import com.example.pokemonmuiltimodulearch.R
import com.ncapdevi.fragnav.FragNavController
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import features.auth.auth_public.domain.entities.UrlEntity
import features.auth.auth_public.naviagtion.IAuthNavigation
import features.feature_a.presentation.FeatureASecondFragment
import features.feature_a.presentation.FirstScreen.FeatureAFirstFragment
import features.feature_a_public.navigation.IFeatureANavigation
import javax.inject.Inject

@Module()
@InstallIn(ActivityComponent::class)
class NavigationModule {

    @Provides
    fun provideTeste(activity: Activity): IFragmentManagerInterface {
        return activity as IFragmentManagerInterface
    }

    @Provides
    @ActivityScoped
    fun provideFragNavController(fragmentManagerInterface: IFragmentManagerInterface): FragNavController {
        return FragNavController(fragmentManagerInterface.getSupportFragmentManagerHelper(), R.id.nav_host_fragment)
    }

    @Provides
    @ActivityScoped
    fun provideScreenNavigation(fragNavController: FragNavController) =
        ScreenNavigation(fragNavController)

    @Provides
    @ActivityScoped
    fun provideIFeatureANavigator(screenNavigation: ScreenNavigation): IFeatureANavigation {
        return screenNavigation
    }

    @Provides
    @ActivityScoped
    fun provideIAuthNavigator(screenNavigation: ScreenNavigation): IAuthNavigation =
        screenNavigation
}

class ScreenNavigation @Inject constructor(
    private val mFragNavController: FragNavController
) : IFeatureANavigation, IAuthNavigation {

    fun init(savedInstanceState: Bundle?) {
        mFragNavController.rootFragmentListener = mRootFragmentListener
        mFragNavController.transactionListener = mTransactionFragmentListener
        mFragNavController.initialize(FragNavController.TAB1, savedInstanceState)
    }

    fun onSaveInstanceState(saveInstanceState: Bundle?) {
        mFragNavController.onSaveInstanceState(saveInstanceState)
    }

    override fun navigateToFeatureB(param: String) {
        mFragNavController.pushFragment(FeatureASecondFragment.newInstance(param2 = param))
    }

    override fun navigateToFirstScreen(urls: List<UrlEntity>) {
        mFragNavController.replaceFragment(FeatureAFirstFragment.newInstance(urls as ArrayList<UrlEntity>))
    }

    override fun navigateToAuthScreen() {
        mFragNavController.pushFragment(AuthMainFragment.newInstance())
    }

    fun navigateBack(): Boolean {
        return if (mFragNavController.isRootFragment) {
            false
        } else {
            mFragNavController.popFragment()
            true
        }
    }

    fun isRootFragment(): Boolean = mFragNavController.isRootFragment

    private val mRootFragmentListener: FragNavController.RootFragmentListener = object :
        FragNavController.RootFragmentListener {

        override val numberOfRootFragments: Int
            get() = 1

        override fun getRootFragment(index: Int): Fragment {
            return when (index) {
                FragNavController.TAB1 -> AuthMainFragment.newInstance()
                else -> throw IllegalStateException("unsupported tab index: " + index)
            }
        }
    }

    var transactionCB: (
        (fragment: Fragment?, transactionType: FragNavController.TransactionType) -> Unit
    )? = null

    private val mTransactionFragmentListener: FragNavController.TransactionListener = object :
        FragNavController.TransactionListener {
        override fun onFragmentTransaction(
            fragment: Fragment?,
            transactionType: FragNavController.TransactionType
        ) {
            transactionCB?.invoke(fragment, transactionType)
        }

        override fun onTabTransaction(fragment: Fragment?, index: Int) {}
    }
}
