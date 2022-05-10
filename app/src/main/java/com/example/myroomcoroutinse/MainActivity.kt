package com.example.myroomcoroutinse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myroomcorotiunes.Database.viewModel.NoteViewModel
import com.example.myroomcorotiunes.model.Note
import com.example.myroomcoroutinse.adapter.NoteAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    lateinit var recycler_view : RecyclerView
    lateinit var button_add_note : FloatingActionButton
    lateinit var adapter: NoteAdapter
    private val noteViewModel: NoteViewModel by lazy {
        ViewModelProvider(this,NoteViewModel.NoteViewModelFactory(this.application))[NoteViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initControlls()
        initEvents()

    }

    private fun initEvents() {
        button_add_note=findViewById(R.id.button_add_note)
        button_add_note.setOnClickListener {
            val intent = Intent(this, AddNoteActivity::class.java)
            startActivity(intent)

        }
    }

    private fun initControlls() {
        adapter = NoteAdapter(this@MainActivity,onItemClick,onItemDelete)
        recycler_view = findViewById(R.id.recycler_view)
        recycler_view.setHasFixedSize(true)
        recycler_view.layoutManager=LinearLayoutManager(this)
        recycler_view.adapter=adapter

        noteViewModel.getAllNote().observe(this, Observer {
            adapter.setNotes(it)
        })
    }

    private val onItemClick:(Note)->Unit={
        var intent = Intent(this, UpdateNoteActivity::class.java)
        intent.putExtra("UPDATE_NOTE",it)
        startActivity(intent)
    }

    private val onItemDelete:(Note)->Unit={
        noteViewModel.deleteNote(it)
    }

    private fun searchDatabase(query: String) {
        val searchQuery = "%$query%"


//        adapter = NoteAdapter(this@MainActivity,onItemClick,onItemDelete)

        noteViewModel.searchDatabase(searchQuery).observe(this, Observer {

            adapter.setNotes(it)
        })


    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

//        val search = menu?.findItem(R.id.menu_search)
//        val searchView = search?.actionView as? SearchView
//        searchView?.isSubmitButtonEnabled = true
//        searchView?.setOnQueryTextListener(this)
        val search = menu?.findItem(R.id.menu_search)
        val searchView = search?.actionView as androidx.appcompat.widget.SearchView

        searchView.setOnQueryTextListener(object: androidx.appcompat.widget.
        SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    searchDatabase(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    searchDatabase(newText)
                }
                return true
            }

        })
        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if(query != null){
            searchDatabase(query)
        }
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        if(query != null){
            searchDatabase(query)
        }
        return true
    }





}