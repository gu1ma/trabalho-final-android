package com.example.opiniaodetudo

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.opiniaodetudo.repository.ReviewRepository


class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == R.id.menu_list_reviews){
            startActivity(Intent(this, ListActivity::class.java))
            return true
        }
        return false
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonSave = findViewById<Button>(R.id.button)
        val inputNome = findViewById<EditText>(R.id.editTextNome)
        val inputOpiniao = findViewById<EditText>(R.id.editTextOpiniao)


        buttonSave.setOnClickListener {
            val name = inputNome.text
            val review = inputOpiniao.text
            Toast.makeText(this, "Nome:$name - Opinião:$review", Toast.LENGTH_LONG).show();

            ReviewRepository(this).save(name.toString(), review.toString())

            //startActivity(Intent(this, ListActivity::class.java))

            // começo do setOnClickListener
            object: AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg params: Void?) {
                    val repository = ReviewRepository(this@MainActivity.applicationContext)
                    repository.save(name.toString(), review.toString())
                    startActivity(Intent(this@MainActivity, ListActivity::class.java))
                }
            }.execute()
        }
    }

    //teste commit testando 2



}

