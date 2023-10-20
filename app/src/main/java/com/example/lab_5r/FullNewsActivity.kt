package com.example.lab_5r

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class FullNewsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_full_news)

        val title = intent.getStringExtra("title")
        val description = intent.getStringExtra("description")
        val content = intent.getStringExtra("content")

        val titleTextView: TextView = findViewById(R.id.titleTextView)
        val descriptionTextView: TextView = findViewById(R.id.descriptionTextView)
        val contentTextView: TextView = findViewById(R.id.contentTextView)



        titleTextView.text = title
        if (!content.isNullOrEmpty()) {
            contentTextView.text = content
        } else {
            contentTextView.text = "Нету"
        }

        descriptionTextView.text = description
    }
}

