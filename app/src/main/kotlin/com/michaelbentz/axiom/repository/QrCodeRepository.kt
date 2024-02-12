package com.michaelbentz.axiom.repository

import androidx.lifecycle.liveData
import com.michaelbentz.axiom.database.dao.QrCodeDao
import com.michaelbentz.axiom.database.entity.QrCodeEntity
import com.michaelbentz.axiom.service.QrCodeService
import com.michaelbentz.axiom.util.ResponseBodyCallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QrCodeRepository @Inject constructor(
    private val qrCodeService: QrCodeService,
    private val qrCodeDao: QrCodeDao
) {
    fun create(data: String) = CoroutineScope(Dispatchers.IO).launch {
        val call = qrCodeService.createQrCode(data)
        call.enqueue(ResponseBodyCallback {
            qrCodeDao.insert(
                QrCodeEntity(
                    bytes = it.bytes(),
                    millis = System.currentTimeMillis()
                )
            )
        })
    }

    fun getLatest() = liveData {
        emitSource(qrCodeDao.getLatest())
    }
}