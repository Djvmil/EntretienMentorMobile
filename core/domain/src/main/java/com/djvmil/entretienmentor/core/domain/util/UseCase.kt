package com.djvmil.entretienmentor.core.domain.util

interface UseCase<Input, Output> {
  suspend operator fun invoke(input: Input): Output
}
