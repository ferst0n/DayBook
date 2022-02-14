package com.example.daybook.presentation.creatingEvent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.daybook.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreatingEventViewModel @Inject constructor( private val repository: Repository) : ViewModel() {




    fun addNote(eventName: String, eventDescription: String, date_start: String, date_finish:String) {

        viewModelScope.launch(){

            repository.addEvent(eventName,eventDescription,date_start,date_finish)
        }

    }

//    fun updateNote(id: String, noteTitle: String, noteDesc: String) {
//        val target = realm.where(EventDB::class.java)
//            .equalTo("id", id)
//            .findFirst()
//
//        realm.executeTransaction {
//            target?.name = noteTitle
//            target?.description = noteDesc
//            realm.insertOrUpdate(target)
//        }
//    }
//
//    fun deleteNote(id: ObjectId) {
//
//        viewModelScope.launch(Dispatchers.Main){
//            realm.executeTransactionAwait(Dispatchers.IO){ transactionRealm: Realm ->
//
//                val notes = transactionRealm.where(EventDB::class.java)
//                    .equalTo("_id", id)
//                    .findFirst()
//
//                notes!!.deleteFromRealm()
//            }}
//
//    }
//
//    fun deleteAllNotes() {
//
//        viewModelScope.launch(Dispatchers.Main){
//            realm.executeTransactionAwait(Dispatchers.IO){ transactionRealm: Realm ->
//
//                transactionRealm.delete(EventDB::class.java)
//            }
//
//        }
//
//    }
}