package com.athimue.domain.usecase.definition

interface SuspendWithInputUseCase<Input, Output> {
    suspend fun invoke(input: Input): Output
}