package com.aradevs.guia4_2

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.CursorFactory
import android.database.sqlite.SQLiteOpenHelper


/**
 * Clase envoltura para el gestor de Bases de datos
 */
internal class DatabaseHelper(
    context: Context?,
    name: String?,
    factory: CursorFactory?,
    version: Int,
) :
    SQLiteOpenHelper(context, name, factory, version) {
    override fun onCreate(database: SQLiteDatabase) {
        createTable(database) // Crear la tabla "students"
        loadDummyData(database) // Cargar 5 datos de prueba
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Actualizaciones
    }

    /**
     * Crear tabla en la base de datos
     *
     * @param database Instancia de la base de datos
     */
    private fun createTable(database: SQLiteDatabase) {
        val cmd = "CREATE TABLE " + StudentsContract.STUDENTS.toString() + " (" +
                StudentsContract.Columnas._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                StudentsContract.Columnas.NOMBRE + " TEXT, " +
                StudentsContract.Columnas.APELLIDO + " TEXT, " +
                StudentsContract.Columnas.CARNET + " TEXT);"
        database.execSQL(cmd)
    }

    /**
     * Carga datos de ejemplo en la tabla
     * @param database Instancia de la base de datos
     */
    private fun loadDummyData(database: SQLiteDatabase) {
        var values = ContentValues()
        values.put(StudentsContract.Columnas.NOMBRE, "Hugo")
        values.put(StudentsContract.Columnas.APELLIDO, "Valencia")
        values.put(StudentsContract.Columnas.CARNET, "GV050221")
        database.insert(StudentsContract.STUDENTS, null, values)
        values = ContentValues()
        values.put(StudentsContract.Columnas.NOMBRE, "Hugo2")
        values.put(StudentsContract.Columnas.APELLIDO, "Valencia")
        values.put(StudentsContract.Columnas.CARNET, "GV050222")
        database.insert(StudentsContract.STUDENTS, null, values)
        values = ContentValues()
        values.put(StudentsContract.Columnas.NOMBRE, "Hugo3")
        values.put(StudentsContract.Columnas.APELLIDO, "Valencia")
        values.put(StudentsContract.Columnas.CARNET, "GV050223")
        database.insert(StudentsContract.STUDENTS, null, values)
        values = ContentValues()
        values.put(StudentsContract.Columnas.NOMBRE, "Hugo4")
        values.put(StudentsContract.Columnas.APELLIDO, "Valencia")
        values.put(StudentsContract.Columnas.CARNET, "GV050224")
        database.insert(StudentsContract.STUDENTS, null, values)
        values = ContentValues()
        values.put(StudentsContract.Columnas.NOMBRE, "Hugo5")
        values.put(StudentsContract.Columnas.APELLIDO, "Valencia")
        values.put(StudentsContract.Columnas.CARNET, "GV050225")
        database.insert(StudentsContract.STUDENTS, null, values)
    }
}