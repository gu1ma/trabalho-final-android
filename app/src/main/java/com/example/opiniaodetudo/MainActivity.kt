package com.example.opiniaodetudo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.opiniaodetudo.fragments.FormFragment


class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, FormFragment())
            .commit()
        configureAutoHiddenKeyboard()
    }

    fun configureAutoHiddenKeyboard() {

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu_second, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == R.id.go_to_list_menu_item){
            startActivity(Intent(this, ListActivity::class.java))
            return true
        }

        if(item?.itemId == R.id.about_menu_item) {
            startActivity(Intent(this, AboutActivity::class.java))
            return true
        }
        return false
    }

}

