package com.example.fauzi_chalange_chapter6.data.datasource.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PlayerDao {
       @Insert(onConflict = OnConflictStrategy.REPLACE)
       suspend fun insertPlayer(playerEntity: PlayerEntity)

       @Delete
       suspend fun deletePlayer(playerEntity: PlayerEntity)

       @Query("SELECT * FROM player")
       suspend fun selectAllPlayers(): List<PlayerEntity>

       @Query("SELECT * FROM player WHERE id = :id")
       suspend fun selectPlayerById(id: Int): PlayerEntity?
}