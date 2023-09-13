package com.athimue.domain.usecase

interface UseCase<Input, Output> {
    fun invoke(input: Input): Output
}