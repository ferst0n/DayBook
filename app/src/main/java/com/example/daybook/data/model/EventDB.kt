package com.example.daybook.data.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import org.bson.types.ObjectId


@RealmClass
open class EventDB(
        @PrimaryKey
        var _id: ObjectId = ObjectId(),
        var date_start:String = "",
        var date_finish:String = "",
        var name:String = "",
        var description:String?= "") : RealmObject()






