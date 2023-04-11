package com.mahmoudi.patientproject.data.di

import com.mahmoudi.patientproject.data.datasource.PatientsDataSource
import com.mahmoudi.patientproject.data.repository.PatientsRepositoryImpl
import com.mahmoudi.patientproject.domain.repository.patients.PatientsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    @Singleton
    fun provideRepositoryPatients(patientsDataSource: PatientsDataSource): PatientsRepository{
        return PatientsRepositoryImpl(patientsDataSource)
    }
}