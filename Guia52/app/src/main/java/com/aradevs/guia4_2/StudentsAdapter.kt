package com.aradevs.guia4_2

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CursorAdapter
import android.widget.TextView


class StudentsAdapter(context: Context?) :
    CursorAdapter(context, null, 0) {
    @SuppressLint("Range")
    override fun bindView(view: View, context: Context, cursor: Cursor) {
        val carnet = view.findViewById<View>(R.id.carnet_text) as TextView
        carnet.text = cursor.getString(
            cursor.getColumnIndex(StudentsContract.Columnas.CARNET))
        val nombre = view.findViewById<View>(R.id.nombre_text) as TextView
        nombre.text = cursor.getString(
            cursor.getColumnIndex(StudentsContract.Columnas.NOMBRE))
        val apellido = view.findViewById<View>(R.id.apellido_text) as TextView
        apellido.text = cursor.getString(
            cursor.getColumnIndex(StudentsContract.Columnas.APELLIDO))
    }

    override fun newView(context: Context, cursor: Cursor, parent: ViewGroup): View {
        val inflater = LayoutInflater.from(parent.context)
        return inflater.inflate(R.layout.item, parent, false)
    }
}