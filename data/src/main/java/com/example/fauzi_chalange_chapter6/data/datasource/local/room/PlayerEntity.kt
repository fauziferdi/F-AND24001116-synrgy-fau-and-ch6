package com.example.fauzi_chalange_chapter6.data.datasource.local.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "player")

data class PlayerEntity(
    @PrimaryKey(autoGenerate = true)

    @ColumnInfo(name = "id")
    val id: Int? = null,

    @ColumnInfo(name = "image")
    val image: String,

    @ColumnInfo(name = "nama")
    val nama: String,


)