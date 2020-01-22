package com.android.myrxjava.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.myrxjava.R
import com.android.myrxjava.adapter.MyAdapter
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import java.util.*
import kotlin.collections.ArrayList

class ColorActivity  : AppCompatActivity() {

    var simpleStringAdapter    : MyAdapter?  = null
    private var disposable     : Disposable? = null
    lateinit var colorListView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        configureLayout()
        createObservable()

    }

    /**
     * Creating data source
     */
    private fun createObservable() {
        val listObservable = Observable.just(getColorList())
        disposable = listObservable.subscribe { colors: List<String>? ->
                simpleStringAdapter!!.setResults(colors as ArrayList<String>)
            }
    }

    /**
     * Initialse the UI
     */
    private fun configureLayout() {

        setContentView(R.layout.activity_colors)

        colorListView = findViewById<View>(R.id.color_list) as RecyclerView

        colorListView.layoutManager = LinearLayoutManager(this)
        colorListView.itemAnimator = DefaultItemAnimator()

        simpleStringAdapter     = MyAdapter(this)
        colorListView.adapter = simpleStringAdapter

    }

    private fun getColorList(): List<String> {
        val colors = ArrayList<String>()
        colors.add("red")
        colors.add("green")
        colors.add("blue")
        colors.add("pink")
        colors.add("brown")
        return colors
    }

    /**
     * Destroying the subscriber
     */
    override fun onStop() {
        super.onStop()
        if (disposable != null && !disposable!!.isDisposed) {
            disposable!!.dispose()
        }
    }

}