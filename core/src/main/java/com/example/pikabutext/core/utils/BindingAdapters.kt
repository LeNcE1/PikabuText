package com.example.pikabutext.core.utils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.hannesdorfmann.adapterdelegates4.AbsDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

@BindingAdapter("app:value")
fun RecyclerView.setValue(list: List<Any>?) {
    list?.let {
        (adapter as? AsyncListDifferDelegationAdapter<*>)?.run {
            items = list
        }

        (adapter as? AbsDelegationAdapter<*>)?.run {
            items = list
        }
    }
}

@BindingAdapter("app:onRefresh")
fun SwipeRefreshLayout.setOnRefresh(onRefresh: () -> Unit) {
    setOnRefreshListener {
        isRefreshing = false
        onRefresh()
    }
}

@BindingAdapter("app:navigateAction")
fun Toolbar.setNavigateAction(action: () -> Unit) {
    setNavigationOnClickListener {
        action()
    }
}

@BindingAdapter("app:src")
fun ImageView.setSrc(resId: Int) {
    setImageResource(resId)
}

@BindingAdapter("app:textOrGone")
fun TextView.setTextOrGone(text: String?) {
    visibility = if (text.isNullOrEmpty()) {
        setText("")
        View.GONE
    } else {
        setText(text)
        View.VISIBLE
    }
}