package com.android.myrxjava.ui

import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.myrxjava.R
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SimpleTaskActivity : AppCompatActivity() {

    var disposable: CompositeDisposable? = CompositeDisposable()
    var value = 0

    /**
     * Creating data source for testing
     * this will emit data after 4 sec
     */

    private val serverDownloadObservable = Observable.create { emitter: ObservableEmitter<Int> ->
            SystemClock.sleep(4000)
            emitter.onNext(126)
            emitter.onComplete()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_simple)
        val view = findViewById<View>(R.id.button)

        view.setOnClickListener { v: View ->
            v.isEnabled = false // disables the button until execution has finished
            val subscribe = serverDownloadObservable.observeOn(
                AndroidSchedulers.mainThread()
            ).subscribeOn(Schedulers.io())
                .subscribe { integer: Int ->
                    updateTheUserInterface(integer) // this methods updates the ui
                    v.isEnabled = true // enables it again
                }
            disposable!!.add(subscribe)
        }

    }

    /**
     * Update the UI once getting the result
     */
    private fun updateTheUserInterface(integer: Int) {
        val view  = findViewById<View>(R.id.resultView) as TextView
        view.text = integer.toString()
    }

    /**
     * Destroy the subscribers
     */

    override fun onStop() {
        super.onStop()
        if (disposable != null && !disposable!!.isDisposed) {
            disposable!!.dispose()
        }
    }

    fun onClick(view: View?) {
        Toast.makeText(this, "Still active " + value++, Toast.LENGTH_SHORT).show()
    }

}