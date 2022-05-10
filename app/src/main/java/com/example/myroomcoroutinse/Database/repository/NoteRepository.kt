package com.example.myroomcorotiunes.Database.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.myroomcorotiunes.Database.Dao.NoteDao

import com.example.myroomcorotiunes.model.Note
import com.example.myroomcoroutinse.Database.NoteDatabase

class NoteRepository(app:Application) {
    //data warehouse for LiveData
    private var noteDao: NoteDao
    private var allNotes: LiveData<List<Note>>



    init {
        val database = NoteDatabase.getInstance(app)
        noteDao = database.getNoteDao()
        allNotes = noteDao.getAllNote()
    }

    suspend fun insertNote(note:Note) = noteDao.insert_note(note)
    suspend fun updateNote(note:Note) = noteDao.update_note(note)
    suspend fun deleteNote(note:Note) = noteDao.delete_note(note)

    fun getAllNote():LiveData<List<Note>> = noteDao.getAllNote()


    fun searchDatabase(searchQuery: String): LiveData<List<Note>> {
        return noteDao.searchDatabase(searchQuery)
    }




}