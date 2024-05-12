package com.djvmil.entretienmentor.core.domain.util

interface UseCaseNoInput<Output> {
  suspend operator fun invoke(): Output
}
