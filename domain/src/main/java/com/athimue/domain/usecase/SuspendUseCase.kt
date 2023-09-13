package com.athimue.domain.usecase

interface SuspendUseCase<Output> {
    suspend fun invoke(): Output
}