package com.example.myroomcoroutinse.Database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.myroomcorotiunes.Database.Dao.NoteDao
import com.example.myroomcorotiunes.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun getNoteDao():NoteDao

    companion object{

        @Volatile
        private var instance: NoteDatabase? = null

        fun getInstance(con:Context):NoteDatabase{
            if (instance==null){
                instance= Room.databaseBuilder(con,NoteDatabase::class.java,"NoteDatabaes")

                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            Log.d("MoviesDatabase", "populating with data...")
                            GlobalScope.launch(Dispatchers.IO) { rePopulateDb(instance) }
                        }
                    })

                    .build()

            }
            return instance!!
        }
    }
}