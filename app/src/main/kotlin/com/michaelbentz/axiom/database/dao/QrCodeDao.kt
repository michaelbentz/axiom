package com.michaelbentz.axiom.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.michaelbentz.axiom.database.entity.QrCodeEntity

@Dao
interface QrCodeDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(qrCodeEntity: QrCodeEntity): Long?

    @Query("SELECT * FROM qr_code ORDER BY millis DESC LIMIT 1")
    fun getLatest(): LiveData<QrCodeEntity?>
}