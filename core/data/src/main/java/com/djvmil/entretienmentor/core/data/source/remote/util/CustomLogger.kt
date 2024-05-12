package com.djvmil.entretienmentor.core.data.source.remote.util

import android.util.Log
import io.ktor.client.plugins.logging.Logger

class CustomHttpLogger : Logger {
  override fun log(message: String) {
    Log.d("Ktor", message)
  }
}
