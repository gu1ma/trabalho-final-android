package com.example.opiniaodetudo

import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.opiniaodetudo.model.Review
import com.example.opiniaodetudo.repository.ReviewRepository

class ListActivity : AppCompatActivity(){

    private lateinit var reviews : MutableList<Review>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_review_layout)

        reviews = ReviewRepository(this@ListActivity.applicationContext)
            .listAll().toMutableList()

        this.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val listView = findViewById<ListView>(R.id.list_recyclerview)
        initList(listView)
        configureOnLongClickListener(listView)

    }

    private fun delete(item: Review) {
        object: AsyncTask<Unit, Void, Unit>(){
            override fun doInBackground(vararg params: Unit?) {
                ReviewRepository(this@ListActivity.applicationContext).delete(item)
                reviews.remove(item)
            }

            override fun onPostExecute(result: Unit?) {
                val listView = findViewById<ListView>(R.id.list_recyclerview)
                val adapter = listView.adapter as ArrayAdapter<Review>
                adapter.notifyDataSetChanged()
            }

        }.execute()
    }

    private fun initList(listView: ListView){
        object: AsyncTask<Void, Void, ArrayAdapter<Review>>(){
            override fun doInBackground(vararg params: Void?): ArrayAdapter<Review> {
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

    fun configureOnLongClickListener(listView: ListView?) {
        listView?.setOnItemLongClickListener { parent, view, position, id ->
            val popupMenu = PopupMenu(this@ListActivity, view)
            popupMenu.inflate(R.menu.list_review_item_menu)

            popupMenu.setOnMenuItemClickListener {
                when(it.itemId) {
                    R.id.item_list_delete ->
                        delete(reviews[position])
                }
                true
            }

            popupMenu.show()
            true //retorno indicando que o clique foi consumido
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}