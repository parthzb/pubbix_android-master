package com.pubbix.base

import android.content.Context
import android.view.View
import com.pubbix.di.Injector
import com.pubbix.feature.home.MainActivity
import com.wayfair.brickkit.BrickFragment
import javax.inject.Inject

abstract class BaseFragment<P : Any> : BrickFragment() {

    @Inject
    protected lateinit var presenter: P

    override fun onAttach(context: Context?) {
        Injector.inject(this)
        super.onAttach(context)
    }

    override fun onResume() {
        super.onResume()
        if (activity is MainActivity) {
            val currentActivity = activity as MainActivity
            if (shouldShowBottomNavigation()) {
                currentActivity.setBottomNavigationVisibility(View.VISIBLE)
            } else {
                currentActivity.setBottomNavigationVisibility(View.GONE)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!activity!!.isChangingConfigurations) {
            Injector.clearComponent(this)
        }
    }

    protected abstract fun shouldShowBottomNavigation(): Boolean
}
