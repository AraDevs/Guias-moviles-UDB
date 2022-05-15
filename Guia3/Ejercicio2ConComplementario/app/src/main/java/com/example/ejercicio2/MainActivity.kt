package com.example.ejercicio2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<MaterialButton>(R.id.frame).setOnClickListener {
            val intent = Intent(this, FrameLayoutActivity::class.java)
            startActivity(intent)
        }
        findViewById<MaterialButton>(R.id.grid).setOnClickListener {
            val intent = Intent(this, GridLayoutActivity::class.java)
            startActivity(intent)
        }
        findViewById<MaterialButton>(R.id.linear).setOnClickListener {
            val intent = Intent(this, LinearLayoutActivity::class.java)
            startActivity(intent)
        }
        findViewById<MaterialButton>(R.id.relative).setOnClickListener {
            val intent = Intent(this, RelativeLayoutActivity::class.java)
            startActivity(intent)
        }
        findViewById<MaterialButton>(R.id.table).setOnClickListener {
            val intent = Intent(this, TableLayoutActivity::class.java)
            startActivity(intent)
        }
    }
}