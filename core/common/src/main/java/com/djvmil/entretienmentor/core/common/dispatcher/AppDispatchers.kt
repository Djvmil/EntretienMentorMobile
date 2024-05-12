package com.djvmil.entretienmentor.core.common.dispatcher

import kotlinx.coroutines.CoroutineDispatcher

interface AppDispatchers {
  val io: CoroutineDispatcher
  val main: CoroutineDispatcher
  val default: CoroutineDispatcher
}
