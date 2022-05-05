package com.example.myroomcoroutinse.Database

import com.example.myroomcorotiunes.Database.Dao.NoteDao
import com.example.myroomcorotiunes.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun rePopulateDb(database: NoteDatabase?) {
    database?.let { db ->
        withContext(Dispatchers.IO) {
            val noteDao: NoteDao = db.getNoteDao()


            noteDao.deleteAll()


            noteDao.insert_note(Note("title 1", "desc 1"))
            noteDao.insert_note(Note("title 2", "desc 2"))
            noteDao.insert_note(Note("title 3", "desc 3"))
            noteDao.insert_note(Note("title 4", "desc 4"))
            noteDao.insert_note(Note("title 5", "desc 4"))
            noteDao.insert_note(Note("title 6", "desc 4"))
            noteDao.insert_note(Note("title 7", "desc 4"))
            noteDao.insert_note(Note("title 8", "desc 4"))

//            val directorOne = Director(fullName = "Adam McKay")
//            val directorTwo = Director(fullName = "Denis Villeneuve", age = 35)
//            val directorThree = Director(fullName = "Morten Tyldum", age = 26)
//            val movieOne = Note(title = "The Big Short", directorId = directorDao.insert(directorOne))
//            val dIdTwo = directorDao.insert(directorTwo)
//            val movieTwo = Movie(title = "Arrival", directorId = dIdTwo)
//            val movieThree = Movie(title = "Blade Runner 2049", directorId = dIdTwo)
//            val movieFour = Movie(title = "Passengers", directorId = directorDao.insert(directorThree))
//            movieDao.insert(movieOne, movieTwo, movieThree, movieFour)
        }
    }
}