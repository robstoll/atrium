package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.creating.Expect

typealias Fun<T> = Pair<String, T>

inline val Fun<out Any?>.name get(): String = this.first
inline val <T> Fun<T>.lambda get(): T = this.second

typealias Fun0<T> = Pair<String, Expect<T>.() -> Expect<T>>
typealias Fun1<T, A1> = Pair<String, Expect<T>.(A1) -> Expect<T>>

operator fun <T> Fun0<T>.invoke(expect: Expect<T>) : Expect<T> = this.second(expect)
operator fun <T, A1> Fun1<T, A1>.invoke(expect: Expect<T>, a: A1) : Expect<T> = this.second(expect, a)
