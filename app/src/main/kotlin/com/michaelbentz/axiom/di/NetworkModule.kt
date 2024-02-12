package com.michaelbentz.axiom.di

import com.michaelbentz.axiom.BASE_URL_QR_SERVER
import com.michaelbentz.axiom.service.QrCodeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideQrCodeService(): QrCodeService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_QR_SERVER)
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QrCodeService::class.java)
    }
}
