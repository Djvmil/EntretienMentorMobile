package com.djvmil.entretienmentor

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.work.WorkerParameters
import com.djvmil.entretienmentor.di.ProvideAppModules.getAppModules
import org.junit.Test
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.dsl.koinApplication
import org.koin.test.verify.verify

class EMentorAppModuleCheck {

    @OptIn(KoinExperimentalAPI::class)
    @Test
    fun checkKoinModule() {
/*
       getAppModules().verify(
           extraTypes = listOf(
               Context::class,
               SavedStateHandle::class,
               WorkerParameters::class
           )
       )
*/
    //Arrange //Act //Assert
       //Given //When //Then

       koinApplication {
           getAppModules()
       }
   }
}