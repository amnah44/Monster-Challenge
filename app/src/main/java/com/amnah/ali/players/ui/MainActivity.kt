package com.amnah.ali.players.ui

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.amnah.ali.players.R
import com.amnah.ali.players.data.DataManager
import com.amnah.ali.players.databinding.ActivityMainBinding
import com.amnah.ali.players.ui.adapter.MainAdapter
import com.amnah.ali.players.util.CsvParse
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var itemsAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(requireNotNull(binding.root))

        init()
    }

    //to show icon of chat in action bar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar,menu)
        return true
    }

    //make initial to view in activity main layout (the parent of recycler)
    private fun init() {
        parsCsvFile()
        itemsAdapter = MainAdapter(this,DataManager.playerList)

        val layout = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvItem.apply {
            layoutManager = layout
            adapter = itemsAdapter
        }
    }
    //parse csv file to get all values
    private fun parsCsvFile() {
        val inputStream: InputStream = assets.open("players.csv")
        val buffer = BufferedReader(InputStreamReader(inputStream))
        val parser = CsvParse()
        buffer.forEachLine { it ->
            val currentPlayer = parser.parse(it)
            DataManager.addPlayer(currentPlayer)
        }
    }
}