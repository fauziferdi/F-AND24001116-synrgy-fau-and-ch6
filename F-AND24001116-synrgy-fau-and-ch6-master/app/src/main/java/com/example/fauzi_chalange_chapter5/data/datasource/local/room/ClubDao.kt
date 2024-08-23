package com.example.fauzi_chalange_chapter5.data.datasource.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ClubDao {
       @Insert(onConflict = OnConflictStrategy.REPLACE)
       suspend fun insertClub(clubEntity: ClubEntity)

       @Delete
       suspend fun deleteClub(clubEntity: ClubEntity)

       @Query("SELECT * FROM club")
       suspend fun selectAllClubs(): List<ClubEntity>

       @Query("SELECT * FROM club WHERE id = :id")
       suspend fun selectClubById(id: Int): ClubEntity?
}