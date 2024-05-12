package com.djvmil.entretienmentor

import com.djvmil.entretienmentor.di.ProvideAppModules.getAppModules
import org.junit.Test
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.dsl.koinApplication

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

    koinApplication { getAppModules() }
  }
}
