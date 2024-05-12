package com.djvmil.entretienmentor.di

import com.djvmil.entretienmentor.core.data.di.dataModule
import com.djvmil.entretienmentor.core.domain.di.domainModule
import com.djvmil.entretienmentor.feature.di.featureModule
import com.djvmil.entretienmentor.ui.MainActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

object ProvideAppModules {
  fun getAppModules() = module {
    includes(
        dataModule,
        domainModule,
        featureModule,
    )
    viewModelOf(::MainActivityViewModel)
  }
}
