package com.example.myroomcoroutinse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.myroomcorotiunes.Database.viewModel.NoteViewModel
import com.example.myroomcorotiunes.model.Note

class UpdateNoteActivity : AppCompatActivity() {

    private val noteViewModel: NoteViewModel by lazy {
        ViewModelProvider(this,
            NoteViewModel.NoteViewModelFactory(this.application))[NoteViewModel::class.java]
    }
    lateinit var tv_title : TextView
    lateinit var tv_desc : TextView
    lateinit var ed_title : EditText
    lateinit var ed_desc : EditText
    lateinit var btn_add : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_note)



        tv_title =findViewById(R.id.textView2)
        tv_desc  =findViewById(R.id.textView)
        ed_title =findViewById(R.id.edtNotetitle)
        ed_desc  =findViewById(R.id.edtNotedesc)
        btn_add  =findViewById(R.id.btn_add)

        val note = intent.getSerializableExtra("UPDATE_NOTE") as Note
        ed_title.setText(note.title)
        ed_desc.setText(note.description)


        btn_add.setOnClickListener {
            note.title=ed_title.text.toString()
            note.description=ed_desc.text.toString()
            noteViewModel.updateNote(note)
            finish()

        }

    }
}