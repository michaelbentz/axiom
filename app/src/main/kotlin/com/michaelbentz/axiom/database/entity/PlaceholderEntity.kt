package com.michaelbentz.axiom.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "placeholder")
data class PlaceholderEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Long = 0
)