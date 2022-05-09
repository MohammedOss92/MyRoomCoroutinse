package com.example.myroomcorotiunes.Database.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myroomcorotiunes.model.Note

@Dao
interface NoteDao {

    @Insert
    suspend fun insert_note (note : Note)

    @Update
    suspend fun update_note (note : Note)

    @Delete
    suspend fun delete_note (note : Note)

    @Query("DELETE FROM note_table")
    suspend fun deleteAll()

    @Query("select * from note_table")
    fun getAllNote() : LiveData<List<Note>>

    @Query("select * from note_table")
    fun getAllNote_notLive() : List<Note>

    @Query("select * from note_table where title_col =:title")
    fun getNoteByTitle (title:String):LiveData<List<Note>>


    @Query("SELECT * FROM note_table WHERE title_col LIKE :searchQuery " +
            "OR description_col LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): LiveData<List<Note>>

}