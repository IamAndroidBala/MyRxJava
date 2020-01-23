package com.android.myrxjava.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.myrxjava.R
import com.android.myrxjava.adapter.AlbumAdapter
import com.android.myrxjava.model.AlbumModel
import kotlinx.android.synthetic.main.activity_album.*

class AlbumListActivity : AppCompatActivity() {

    var mList = ArrayList<AlbumModel>()

    lateinit var albumAdapter: AlbumAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_album)

        albumAdapter = AlbumAdapter(this@AlbumListActivity, mList)
        recyclerAlbum.adapter = albumAdapter

    }

}