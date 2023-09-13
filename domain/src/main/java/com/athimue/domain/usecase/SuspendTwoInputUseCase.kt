package com.athimue.domain.usecase

interface SuspendTwoInputUseCase<FirstInput, SecondInput, Output> {
    suspend fun invoke(firstInput: FirstInput, secondInput: SecondInput): Output
}