package com.michaelbentz.axiom.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.michaelbentz.axiom.repository.QrCodeRepository
import com.michaelbentz.axiom.usecase.CreateQrCodeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QrCodeViewModel @Inject constructor(
    qrCodeRepository: QrCodeRepository,
    private val createQrCodeUseCase: CreateQrCodeUseCase
) : ViewModel() {
    val latestQrCode = qrCodeRepository.getLatest()

    fun createQrCode(data: String) = viewModelScope.launch {
        createQrCodeUseCase(data)
    }
}