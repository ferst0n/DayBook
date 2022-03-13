package com.example.daybook.data.localDataSource

import com.example.daybook.presentation.Utils.getDate
import com.example.daybook.presentation.Utils.getTime
import com.example.daybook.data.model.EventDB
import com.example.daybook.domain.entity.Event
import io.realm.Realm
import io.realm.kotlin.executeTransactionAwait
import kotlinx.coroutines.*
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val realm: Realm) {

    fun getAllEvents(): List<Event> {

        var note: ArrayList<Event> = ArrayList()
        val notes = realm.where(EventDB::class.java).findAll()
        notes.map {
            note.addAll(listOf(
                Event(it._id.toString(), getDate(it.date_start),
                getDate(it.date_finish),getTime(it.date_start), getTime(it.date_finish),
                it.name,it.description)
            ))
        }
        return note
    }

    suspend fun addEvent(event: EventDB){
        realm.executeTransactionAwait(Dispatchers.IO){ transactionRealm: Realm ->
            transactionRealm.insert(event)
        }
    }

}