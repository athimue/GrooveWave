package com.athimue.domain.usecase.definition

interface SuspendUseCase<Output> {
    suspend fun invoke(): Output
}