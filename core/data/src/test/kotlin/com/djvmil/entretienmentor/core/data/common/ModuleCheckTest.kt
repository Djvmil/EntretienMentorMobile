package com.djvmil.entretienmentor.core.data.common

import com.djvmil.entretienmentor.core.data.di.dataModule
import org.junit.After
import org.junit.Before
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class ModuleCheckTest {
  @Before
  fun setUp() {
    loadKoinModules(dataModule)
  }

  @After
  fun tearDown() {
    unloadKoinModules(dataModule)
  }
}
