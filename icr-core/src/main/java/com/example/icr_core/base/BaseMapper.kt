package com.example.icr_core.base

fun interface Mapper<in From, out To> {
    fun map(from: From): To
}
fun interface MapperWithArgs<in From, in Args, out To> {
    fun map(from: From, args: Args): To
}


fun <F, T> Mapper<F, T>.mapAll(list: List<F>) = list.map { map(it) }

fun <F, T, A> MapperWithArgs<F, A, T>.mapAll(list: List<F>, args: A) = list.map { map(it, args) }
