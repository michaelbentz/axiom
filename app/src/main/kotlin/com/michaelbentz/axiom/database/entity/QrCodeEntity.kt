package com.michaelbentz.axiom.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "qr_code")
class QrCodeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val millis: Long,
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    val bytes: ByteArray
)