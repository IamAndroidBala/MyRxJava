package com.android.myrxjava.ui

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.myrxjava.R
import com.android.myrxjava.adapter.MyAdapter
import com.android.myrxjava.networking.RestClient
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class BookActivity : AppCompatActivity() {

    private var progressBar         : ProgressBar? = null
    private var stringAdapter       : MyAdapter? = null
    private var restClient          : RestClient? = null
    private var bookSubscription    : Disposable? = null
    private var booksRecyclerView   : RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        restClient = RestClient(this)

        configureLayout()
        createObservable()

    }

    private fun createObservable() {

        val booksObservable = Observable.fromCallable { restClient!!.favoriteBooks }

        bookSubscription = booksObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe { strings: List<String> ->
                    displayBooks(strings as ArrayList)
                }

    }

    override fun onDestroy() {
        super.onDestroy()
        if (bookSubscription != null && !bookSubscription!!.isDisposed) {
            bookSubscription!!.dispose()
        }
    }

    private fun displayBooks(books: ArrayList<String>) {
        stringAdapter!!.setResults(books)
        progressBar!!.visibility = View.GONE
        booksRecyclerView!!.visibility = View.VISIBLE
    }

    private fun configureLayout() {

        setContentView(R.layout.activity_books)

        progressBar         = findViewById<View>(R.id.loader) as ProgressBar
        booksRecyclerView   = findViewById<View>(R.id.books_list) as RecyclerView

        booksRecyclerView!!.layoutManager = LinearLayoutManager(this)
        booksRecyclerView!!.itemAnimator  = DefaultItemAnimator()

        stringAdapter       = MyAdapter(this)
        booksRecyclerView!!.adapter = stringAdapter

    }

    override fun onStop() {
        super.onStop()
        if (bookSubscription != null && !bookSubscription!!.isDisposed) {
            bookSubscription!!.dispose()
        }
    }

}