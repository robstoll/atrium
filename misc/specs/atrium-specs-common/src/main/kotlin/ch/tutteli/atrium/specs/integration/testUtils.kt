package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.checkingTriple
import ch.tutteli.atrium.specs.expectLambda

typealias Fun<T> = Pair<String, T>

inline val Fun<out Any?>.name get(): String = this.first
inline val <T> Fun<T>.lambda get(): T = this.second

typealias Fun0<T> = Pair<String, Expect<T>.() -> Expect<T>>
typealias Fun1<T, A1> = Pair<String, Expect<T>.(A1) -> Expect<T>>

operator fun <T> Fun0<T>.invoke(expect: Expect<T>): Expect<T> = this.second(expect)
operator fun <T, A1> Fun1<T, A1>.invoke(expect: Expect<T>, a: A1): Expect<T> = this.second(expect, a)

fun <T> Fun0<T>.forSubjectLess(): Pair<String, Expect<T>.() -> Unit> = this.name to expectLambda { this@forSubjectLess(this) }
fun <T, A1> Fun1<T, A1>.forSubjectLess(a: A1): Pair<String, Expect<T>.() -> Unit> =
    this.name to expectLambda { this@forSubjectLess(this, a) }

fun <T> Fun0<T>.forChecking(holdingSubject: T, failingSubject: T): Triple<String, Expect<T>.() -> Unit, Pair<T, T>> =
    checkingTriple(this.name, { this@forChecking(this) }, holdingSubject, failingSubject)

fun <T, A1> Fun1<T, A1>.forChecking(
    a: A1,
    holdingSubject: T,
    failingSubject: T
): Triple<String, Expect<T>.() -> Unit, Pair<T, T>> =
    checkingTriple(this.name, { this@forChecking(this, a) }, holdingSubject, failingSubject)
