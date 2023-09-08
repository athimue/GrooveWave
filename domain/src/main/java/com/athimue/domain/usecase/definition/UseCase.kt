package com.athimue.domain.usecase.definition

interface UseCase<Input, Output> {
    fun invoke(input: Input): Output
}