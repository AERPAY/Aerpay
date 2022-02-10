package com.client.aerpaymerchant.network.listeners

import android.view.View
import android.view.ViewTreeObserver
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager

abstract class PaginationScrollListener(val mScrollView : NestedScrollView ,layoutManager: LinearLayoutManager) : ViewTreeObserver.OnScrollChangedListener {
    var layoutManager: LinearLayoutManager = layoutManager
    override fun onScrollChanged() {
        val view = mScrollView.getChildAt(mScrollView.childCount - 1) as View
        val diff: Int = view.bottom - (mScrollView.height + mScrollView
            .scrollY)
        if (diff == 0) {
            if (!isLoading && !isLastPage) {
                    loadMoreItems()
                }
        }

    }


    protected abstract fun loadMoreItems()
    abstract val isLastPage: Boolean
    abstract val isLoading: Boolean

}
