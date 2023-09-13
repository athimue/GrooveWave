package com.athimue.domain.usecase

interface SuspendOneInputUseCase<Input, Output> {
    suspend fun invoke(input: Input): Output
}