package com.example.alcoparty

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.alcoparty.ui.main.SectionsPagerAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout


class MainActivity : AppCompatActivity() {
    val LOG_TAG = "myLogs"
    var titles: Array<CharSequence> = arrayOf("Main", "Таблица рекордов") as Array<CharSequence>;
    override fun onCreate(savedInstanceState: Bundle?) {
        var curAlco = 0;
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        val adapter: ViewPagerAdapter = ViewPagerAdapter(supportFragmentManager, titles, 2);
        viewPager.adapter = adapter;
        val tabLayout: TabLayout = findViewById(R.id.tabs)
        tabLayout.setupWithViewPager(viewPager)
        val viewMain: View = layoutInflater.inflate(R.layout.fragment_main, null)
        val viewTable: View = layoutInflater.inflate(R.layout.fragment_table, null)

        tabLayout.getTabAt(0)?.customView = viewMain
        tabLayout.getTabAt(1)?.customView = viewTable
        // val btnAlco: Button = findViewById(viewMain.buttonAlco);
        //   val textAlco: TextView = findViewById(R.id.textAlco);
        /*  curAlco = textAlco.text.toString().toInt();
          btnAlco.setOnClickListener { view ->
              curAlco += 50;
              textAlco.text = curAlco.toString();
          }*/

    }
}