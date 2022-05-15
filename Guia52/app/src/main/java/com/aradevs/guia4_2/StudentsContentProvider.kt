package com.aradevs.guia4_2

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.database.Cursor
import android.database.SQLException
import android.net.Uri
import android.text.TextUtils
import android.util.Log


class StudentsContentProvider() : ContentProvider() {
    /**
     * Instancia del administrado de BD
     */
    private var databaseHelper: DatabaseHelper? = null
    override fun onCreate(): Boolean {
        // Inicializando gestor BD
        Log.i("BASE", "CRENADO BASE")
        databaseHelper = DatabaseHelper(
            context,
            DATABASE_NAME,
            null,
            DATABASE_VERSION
        )
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?,
    ): Cursor? {


        // Obtener base de datos
        val db = databaseHelper!!.writableDatabase
        // Comparar Uri
        val match = StudentsContract.uriMatcher!!.match(uri)
        val c: Cursor
        when (match) {
            StudentsContract.ALLROWS -> {
                // Consultando todos los registros
                c = db.query(StudentsContract.STUDENTS, projection,
                    selection, selectionArgs,
                    null, null, sortOrder)
                c.setNotificationUri(
                    context!!.contentResolver,
                    StudentsContract.CONTENT_URI)
            }
            StudentsContract.SINGLE_ROW -> {
                // Consultando un solo registro basado en el Id del Uri
                val id = ContentUris.parseId(uri)
                c = db.query(StudentsContract.STUDENTS, projection,
                    StudentsContract.Columnas._ID + " = " + id,
                    selectionArgs, null, null, sortOrder)
                c.setNotificationUri(
                    context!!.contentResolver,
                    StudentsContract.CONTENT_URI)
            }
            else -> throw IllegalArgumentException("URI no soportada: $uri")
        }
        return c
    }

    override fun getType(uri: Uri): String? {
        when (StudentsContract.uriMatcher!!.match(uri)) {
            StudentsContract.ALLROWS -> return StudentsContract.MULTIPLE_MIME
            StudentsContract.SINGLE_ROW -> return StudentsContract.SINGLE_MIME
            else -> throw IllegalArgumentException("Tipo de students desconocida: $uri")
        }
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        // Validar la uri
        if (StudentsContract.uriMatcher!!.match(uri) != StudentsContract.ALLROWS) {
            throw IllegalArgumentException("URI desconocida : $uri")
        }
        val contentValues: ContentValues
        if (values != null) {
            contentValues = ContentValues(values)
        } else {
            contentValues = ContentValues()
        }

        // Si es necesario, verifica los valores
        if (databaseHelper == null) {
            databaseHelper = DatabaseHelper(
                context,
                DATABASE_NAME,
                null,
                DATABASE_VERSION
            )
        }

        // Inserción de nueva fila
        val db = databaseHelper!!.writableDatabase
        val rowId = db.insert(StudentsContract.STUDENTS,
            null, contentValues)
        if (rowId > 0) {
            val uri_actividad = ContentUris.withAppendedId(
                StudentsContract.CONTENT_URI, rowId)
            context!!.contentResolver.notifyChange(uri_actividad, null)
            return uri_actividad
        }
        throw SQLException("Falla al insertar fila en : $uri")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        val db = databaseHelper!!.writableDatabase
        val match = StudentsContract.uriMatcher!!.match(uri)
        val affected: Int
        when (match) {
            StudentsContract.ALLROWS -> affected = db.delete(StudentsContract.STUDENTS,
                selection,
                selectionArgs)
            StudentsContract.SINGLE_ROW -> {
                val id = ContentUris.parseId(uri)
                affected = db.delete(StudentsContract.STUDENTS,
                    StudentsContract.Columnas._ID + "=" + id
                            + if (!TextUtils.isEmpty(selection)) " AND ($selection)" else "",
                    selectionArgs)
                // Notificar cambio asociado a la uri
                context!!.contentResolver.notifyChange(uri, null)
            }
            else -> throw IllegalArgumentException("Elemento actividad desconocido: " +
                    uri)
        }
        return affected
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<String>?,
    ): Int {
        val db = databaseHelper!!.writableDatabase
        val affected: Int
        when (StudentsContract.uriMatcher!!.match(uri)) {
            StudentsContract.ALLROWS -> affected = db.update(StudentsContract.STUDENTS, values,
                selection, selectionArgs)
            StudentsContract.SINGLE_ROW -> {
                val id = uri.pathSegments[1]
                affected = db.update(StudentsContract.STUDENTS, values,
                    (StudentsContract.Columnas._ID + "=" + id
                            + (if (!TextUtils.isEmpty(selection)) " AND ($selection)" else "")),
                    selectionArgs)
            }
            else -> throw IllegalArgumentException("URI desconocida: $uri")
        }
        context!!.contentResolver.notifyChange(uri, null)
        return affected
    }

    companion object {
        /**
         * Nombre de la base de datos
         */
        private val DATABASE_NAME = "students.db"

        /**
         * Versión actual de la base de datos
         */
        private val DATABASE_VERSION = 1
    }
}