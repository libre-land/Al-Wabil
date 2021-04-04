package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    var adapter: MyAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val nav_view = findViewById<NavigationView>(R.id.nav_view)
        nav_view.setNavigationItemSelectedListener(this)

        val rcView = findViewById<RecyclerView>(R.id.rcView)
        var list = ArrayList<ListItem>()

        list.addAll(fillArrays(resources.getStringArray(R.array.chapter_name),
                resources.getStringArray(R.array.chapter_content)))

        rcView.hasFixedSize()
        rcView.layoutManager = LinearLayoutManager(this)
        adapter = MyAdapter(list, this)
        rcView.adapter = adapter
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.id_chapters -> {
                Toast.makeText(this, "Chapters", Toast.LENGTH_SHORT).show()
                adapter?.updateAdapter(fillArrays(resources.getStringArray(R.array.chapter_name),
                        resources.getStringArray(R.array.chapter_content)))
            }
        }

        return true
    }

    fun fillArrays(titleArray: Array<String>, contentArray: Array<String>): List<ListItem> {
        var listItemArray = ArrayList<ListItem>()

        for (n in 0..titleArray.size - 1) {
            var listItem = ListItem(titleArray[n], contentArray[n])
            listItemArray.add(listItem)
        }
        return listItemArray
    }
}