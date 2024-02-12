package com.michaelbentz.axiom.usecase

import com.michaelbentz.axiom.repository.QrCodeRepository
import javax.inject.Inject

class CreateQrCodeUseCase @Inject constructor(
    private val qrCodeRepository: QrCodeRepository
) {
    operator fun invoke(data: String) {
        qrCodeRepository.create(data)
    }
}