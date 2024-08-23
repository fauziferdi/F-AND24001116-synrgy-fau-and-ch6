package com.example.fauzi_chalange_chapter5.data.datasource.local.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "club")

data class ClubEntity(
    @PrimaryKey(autoGenerate = true)

    @ColumnInfo(name = "id")
    val id: Int? = null,

    @ColumnInfo(name = "image")
    val image: String,

    @ColumnInfo(name = "nama")
    val nama: String,


)