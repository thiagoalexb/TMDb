package com.quintallabs.tmdb.ui

import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.quintallabs.tmdb.R

open class BaseFragment(resLayout: Int) : Fragment(resLayout) {

    fun setLoading(isVisible: Boolean) {

       activity?.let {

           val loadingContainer = it.findViewById<LinearLayout>(R.id.container_loading_linear_layout)

           loadingContainer.isVisible = isVisible
       }
    }
}