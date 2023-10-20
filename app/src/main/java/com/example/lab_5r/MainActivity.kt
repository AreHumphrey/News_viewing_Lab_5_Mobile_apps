package com.example.lab_5r

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val newsApiClient = NewsApiClient()
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        newsAdapter = NewsAdapter(emptyList(), { article ->
            val intent = Intent(this, FullNewsActivity::class.java)
            intent.putExtra("title", article.title)
            intent.putExtra("description", article.description)
            intent.putExtra("content", article.content)

            startActivity(intent)
        }, { article ->
        })


        recyclerView.adapter = newsAdapter

        getTopHeadlines()
    }

    private fun getTopHeadlines() {
        newsApiClient.getTopHeadlines().enqueue(object : Callback<NewsResponse> {
            override fun onResponse(
                call: Call<NewsResponse>,
                response: Response<NewsResponse>
            ) {
                if (response.isSuccessful) {
                    val newsResponse = response.body()
                    newsResponse?.articles?.let {
                        newsAdapter.articles = it
                        newsAdapter.notifyDataSetChanged()
                    }
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "Ошибка при получении новостей",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Toast.makeText(
                    this@MainActivity,
                    "Ошибка при получении новостей: ${t.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}