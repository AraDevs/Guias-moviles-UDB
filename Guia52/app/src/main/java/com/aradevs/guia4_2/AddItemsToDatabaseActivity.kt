package com.aradevs.guia4_2

import android.content.ContentValues
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class AddItemsToDatabaseActivity : AppCompatActivity() {
    lateinit var name: EditText
    lateinit var lastName: EditText
    lateinit var id: EditText
    lateinit var add: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_items_to_database)
        name = findViewById(R.id.name)
        lastName = findViewById(R.id.lastname)
        id = findViewById(R.id.id)
        add = findViewById(R.id.add_btn)

        add.setOnClickListener {
            if (name.text.toString().trim().isNotEmpty() && lastName.text.toString().trim()
                    .isNotEmpty() && id.text.toString().trim().isNotEmpty()
            ) {
                val values = ContentValues()
                values.put(StudentsContract.Columnas.NOMBRE, name.text.toString())
                values.put(StudentsContract.Columnas.APELLIDO, lastName.text.toString())
                values.put(StudentsContract.Columnas.CARNET, id.text.toString())
                applicationContext.contentResolver.insert(StudentsContract.CONTENT_URI, values)
                finish()
            }

        }
    }
}