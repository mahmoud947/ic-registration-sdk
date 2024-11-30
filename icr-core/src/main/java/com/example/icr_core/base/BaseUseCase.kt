package com.example.icr_core.base

interface BaseUseCase {
    operator fun invoke()
}

interface BaseIOUseCase<IN, OUT> {
    operator fun invoke(input: IN): OUT
}

interface BaseIUseCase<IN> {
    operator fun invoke(input: IN)
}

interface BaseOUseCase<OUT> {
    operator fun invoke(): OUT
}
