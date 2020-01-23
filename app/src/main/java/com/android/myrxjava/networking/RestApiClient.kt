package com.android.myrxjava.networking

import android.os.SystemClock
import com.android.myrxjava.model.AlbumModel
import java.util.*
import kotlin.collections.ArrayList

class RestApiClient {

    fun getFavoriteBooks(): List<String> {
        SystemClock.sleep(5000) // "Simulate" the delay of network.
        return createBooks()
    }

    fun getFavoriteBooksWithException(): List<String> {
        SystemClock.sleep(5000) // "Simulate" the delay of network.
        throw RuntimeException("Failed to load")
    }

    private fun createBooks(): List<String> {
        
        val books : MutableList<String> = ArrayList()

        books.add("Tamil 1")
        books.add("Tamil 2")
        books.add("Tamil 3")
        books.add("Tamil 4")
        books.add("Tamil 5")
        books.add("Tamil 6")
        books.add("Tamil 7")
        books.add("Tamil 8")
        books.add("Tamil 9")
        books.add("Tamil 10")
        
        return books
        
    }

//    fun getAlbums() : ArrayList<AlbumModel> {
//
//    }

}