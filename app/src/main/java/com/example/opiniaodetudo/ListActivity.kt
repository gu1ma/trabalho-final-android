package com.example.opiniaodetudo

import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.opiniaodetudo.model.Review
import com.example.opiniaodetudo.repository.ReviewRepository

class ListActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_review_layout)

        this.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val listView = findViewById<ListView>(R.id.list_recyclerview)
        initList(listView)

//        val listView = findViewById<ListView>(R.id.list_recyclerview)
//        val reviews = ReviewRepository(this).listAll()
//
//
//        val adapter = object : ArrayAdapter<Review>(this, -1, reviews ){
//            override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
//                val itemView = layoutInflater.inflate(R.layout.review_list_item_layout, null)
//                val item = reviews[position]
//                val textViewName = itemView.findViewById<TextView>(R.id.item_name)
//                val textViewReview = itemView.findViewById<TextView>(R.id.item_review)
//                textViewName.text = item.name
//                textViewReview.text = item.review
//                return itemView
//            }
//        }
//        listView.adapter = adapter

//        val listView = findViewById<ListView>(R.id.list_recyclerview)
//        this.supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        initList(listView)
    }

    private fun initList(listView: ListView){
        object: AsyncTask<Void, Void, ArrayAdapter<Review>>(){
            override fun doInBackground(vararg params: Void?): ArrayAdapter<Review> {
                val reviews = ReviewRepository(this@ListActivity.applicationContext).listAll()

                return object: ArrayAdapter<Review>(this@ListActivity, -1, reviews) {
                    override fun getView(
                        position: Int,
                        convertView: View?,
                        parent: ViewGroup
                    ): View {
                        val itemView = layoutInflater.inflate(R.layout.review_list_item_layout, null)
                        val item = reviews[position]
                        val textViewName = itemView
                            .findViewById<TextView>(R.id.item_name)
                        val textViewReview = itemView
                            .findViewById<TextView>(R.id.item_review)
                        textViewName.text = item.name
                        textViewReview.text = item.review
                        return itemView
                    }
                }
            }

            override fun onPostExecute(adapter: ArrayAdapter<Review>?) {
                listView.adapter = adapter
            }
        }.execute()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}