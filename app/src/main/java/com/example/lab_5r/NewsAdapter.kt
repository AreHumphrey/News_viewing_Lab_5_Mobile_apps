package com.example.lab_5r

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NewsAdapter(
    var articles: List<Article>,
    private val onItemClick: (Article) -> Unit,
    private val onItemFullClick: (Article) -> Unit
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {


    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)

        fun bind(article: Article) {
            titleTextView.text = article.title
            descriptionTextView.text = article.description
            itemView.setOnClickListener {
                onItemClick(article)
            }
            itemView.setOnLongClickListener {
                onItemFullClick(article)
                true
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.news_item, parent, false)
        return NewsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = articles[position]
        holder.bind(article)
        holder.itemView.setOnClickListener {
            onItemClick(article)
        }
    }

    override fun getItemCount(): Int {
        return articles.size
    }
}
