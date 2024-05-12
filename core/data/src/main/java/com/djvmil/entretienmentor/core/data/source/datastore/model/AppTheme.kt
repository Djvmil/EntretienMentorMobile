package com.djvmil.entretienmentor.core.data.source.datastore.model

enum class AppTheme {
  FollowSystem,
  Light,
  Dark;

  companion object {
    fun default(): AppTheme {
      return FollowSystem
    }
  }
}
