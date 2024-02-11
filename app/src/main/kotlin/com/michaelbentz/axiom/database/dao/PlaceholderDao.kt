package com.michaelbentz.axiom.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.michaelbentz.axiom.database.entity.PlaceholderEntity

@Dao
interface PlaceholderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(placeholderEntity: PlaceholderEntity): Long

    @Query("SELECT * FROM placeholder WHERE id == :id")
    fun getById(id: Long): PlaceholderEntity?
}