package com.djvmil.entretienmentor.core.data.source.datastore.model

enum class StepsStarting {
  ON_BOARDING,
  ON_AUTH_PAGE,
  ON_GUEST_PAGE,
  ON_HOME_USER;

  companion object {
    fun default(): StepsStarting {
      return ON_BOARDING
    }
  }
}
