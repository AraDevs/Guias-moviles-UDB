package com.aradevs.guia4_2

import android.content.UriMatcher
import android.net.Uri
import android.provider.BaseColumns


object StudentsContract {
    /**
     * Representación de la tabla a consultar
     */
    const val STUDENTS = "students"

    /**
     * Código para URIs de multiples registros
     */
    const val ALLROWS = 1

    /**
     * Código para URIS de un solo registro
     */
    const val SINGLE_ROW = 2

    /**
     * Autoridad del Content Provider
     */
    const val AUTHORITY = "com.aradevs.guia4_2.content.provider"

    /**
     * URI de contenido principal
     */
    val CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + STUDENTS)
    const val SINGLE_MIME = "vnd.android.cursor.item/vnd." + AUTHORITY + STUDENTS
    const val MULTIPLE_MIME = "vnd.android.cursor.dir/vnd." + AUTHORITY + STUDENTS

    /**
     * Comparador de URIs de contenido
     */
    var uriMatcher: UriMatcher? = null

    /**
     * Estructura de la tabla
     */
    object Columnas : BaseColumns {
        const val _ID = "_id"
        const val NOMBRE = "nombre"
        const val APELLIDO = "apellido"
        const val CARNET = "carnet"
    }

    // Asignación de URIs
    init {
        uriMatcher = UriMatcher(UriMatcher.NO_MATCH)
        uriMatcher?.addURI(AUTHORITY, STUDENTS, ALLROWS)
        uriMatcher?.addURI(AUTHORITY, STUDENTS + "/#", SINGLE_ROW)
    }
}