package com.athimue.domain.usecase

interface SuspendWithInputUseCase<Input, Output> {
    suspend fun invoke(input: Input): Output
}