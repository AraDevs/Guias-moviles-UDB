package com.aradevs.guia4_1

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.aradevs.guia4_1.databinding.ActivityMainBinding
import com.aradevs.guia4_1.db.PersonEntity
import com.aradevs.room_example.db.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val binding: ActivityMainBinding by viewBinding()
    private var currentPerson: PersonEntity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding.apply {
            addBtn.setOnClickListener {
                if (currentPerson != null) {
                    Log.e("upt","aa")
                    update()
                    return@setOnClickListener
                }
                add()
            }

            getBtn.setOnClickListener {
                get()
            }

            deleteBtn.setOnClickListener {
                delete()
            }

        }
    }

    private fun add() {
        if (validate()) {
            val db = AppDatabase.getDatabase(applicationContext)
            val person = with(binding) {
                PersonEntity(0,
                    name.text.toString(),
                    lastname.text.toString(),
                    age.text.toString().toInt(),
                    phone.text.toString())
            }
            lifecycleScope.launch(Dispatchers.IO) {
                db.getDatabaseDao().savePerson(person)
            }
            cleanFields()
        }
    }

    private fun update() {
        if (validate()) {
            val db = AppDatabase.getDatabase(applicationContext)
            val person = with(binding) {
                PersonEntity(currentPerson?.id?:0,
                    name.text.toString(),
                    lastname.text.toString(),
                    age.text.toString().toInt(),
                    phone.text.toString())
            }
            Log.e("id", person.id.toString())
            lifecycleScope.launch(Dispatchers.IO) {
                db.getDatabaseDao().updatePerson(person)
            }
            cleanFields()
        }
    }

    private fun get() {
        if (binding.id.text.trim().isNotEmpty()) {
            val db = AppDatabase.getDatabase(applicationContext)
            val id = with(binding) {
                id.text.toString().toInt()
            }
            lifecycleScope.launch(Dispatchers.IO) {
                val item = db.getDatabaseDao().getPerson(id)
                if (item == null) {
                    runOnUiThread {
                        cleanFields()
                    }
                    return@launch
                }
                currentPerson = item
                runOnUiThread {
                    with(binding) {
                        this.id.setText(item.id.toString())
                        this.name.setText(item.name)
                        this.lastname.setText(item.lastName)
                        this.age.setText(item.age.toString())
                        this.phone.setText(item.phone)
                    }
                }
            }
        }
    }

    private fun delete() {
        if (currentPerson != null) {
            val db = AppDatabase.getDatabase(applicationContext)
            lifecycleScope.launch(Dispatchers.IO) {
                currentPerson?.let {
                    db.getDatabaseDao().deletePerson(it)
                }
            }
        }
    }

    private fun validate(): Boolean {
        return with(binding) {
            name.text.trim().isNotEmpty() && lastname.text.trim().isNotEmpty() && phone.text.trim()
                .isNotEmpty() && age.text.trim().isNotEmpty()
        }
    }

    private fun cleanFields() {
        binding.apply {
            name.text.clear()
            lastname.text.clear()
            phone.text.clear()
            age.text.clear()
        }
        currentPerson = null
    }
}