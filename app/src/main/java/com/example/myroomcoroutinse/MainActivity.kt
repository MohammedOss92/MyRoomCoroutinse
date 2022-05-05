package com.example.myroomcoroutinse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myroomcorotiunes.Database.viewModel.NoteViewModel
import com.example.myroomcorotiunes.model.Note
import com.example.myroomcoroutinse.adapter.NoteAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    lateinit var recycler_view : RecyclerView
    lateinit var button_add_note : FloatingActionButton

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
        val adapter: NoteAdapter = NoteAdapter(this@MainActivity,onItemClick,onItemDelete)
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
}