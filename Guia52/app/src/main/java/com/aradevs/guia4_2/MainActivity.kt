package com.aradevs.guia4_2

import android.app.ListActivity
import android.app.LoaderManager
import android.content.CursorLoader
import android.content.Intent
import android.content.Loader
import android.database.Cursor
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : ListActivity(), LoaderManager.LoaderCallbacks<Cursor?> {
    private var adaptador: StudentsAdapter? = null
    lateinit var fab: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fab = findViewById(R.id.add_fab)
        adaptador = StudentsAdapter(this)
        listAdapter = adaptador
        loaderManager.initLoader(0, null, this)

        fab.setOnClickListener {
            val intent = Intent(this, AddItemsToDatabaseActivity::class.java)
            startActivity(intent)
        }
    }


    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor?> {
        return CursorLoader(
            this,
            StudentsContract.CONTENT_URI,
            null, null, null, null)
    }

    override fun onLoadFinished(p0: android.content.Loader<Cursor?>?, p1: Cursor?) {
        adaptador!!.swapCursor(p1)
    }

    override fun onLoaderReset(p0: android.content.Loader<Cursor?>?) {
        adaptador!!.swapCursor(null)
    }
}