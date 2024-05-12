package com.djvmil.entretienmentor

import android.app.Application
import com.djvmil.entretienmentor.di.ProvideAppModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AppEMentor : Application() {
  override fun onCreate() {
    super.onCreate()

    startKoin {
      androidLogger()
      androidContext(this@AppEMentor)
      modules(ProvideAppModules.getAppModules())
    }
  }
}
