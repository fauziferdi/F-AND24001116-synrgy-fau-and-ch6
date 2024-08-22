package com.example.fauzi_chalange_chapter6.data.datasource.local.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        PlayerEntity::class,
    ],
    version = 1,
)
abstract class RoomDatabase : RoomDatabase() {

    companion object {
        const val NAME = "room_database"
    }

    abstract fun playerDao(): PlayerDao
}