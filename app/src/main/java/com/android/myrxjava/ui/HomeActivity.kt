package com.android.myrxjava.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.android.myrxjava.R
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        btnSimple.setOnClickListener {
            startActivity(
                Intent(
                    applicationContext,
                    SimpleTaskActivity::class.java
                )
            )
        }

        btnBook.setOnClickListener {
            startActivity(
                Intent(
                    applicationContext,
                    BookActivity::class.java
                )
            )
        }

        btnColor.setOnClickListener {
            startActivity(
                Intent(
                    applicationContext,
                    ColorActivity::class.java
                )
            )
        }

        btnAlbum.setOnClickListener {
            startActivity(
                Intent(
                    applicationContext,
                    AlbumListActivity::class.java
                )
            )
        }

    }

}