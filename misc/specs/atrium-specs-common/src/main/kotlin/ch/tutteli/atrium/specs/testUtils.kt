@file:Suppress("NOTHING_TO_INLINE")

package ch.tutteli.atrium.specs

import ch.tutteli.atrium.core.polyfills.format
import ch.tutteli.atrium.creating.Expect
import kotlin.reflect.KFunction1
import kotlin.reflect.KFunction2
import kotlin.reflect.KFunction3

typealias Fun<T> = Pair<String, T>

inline val Fun<out Any?>.name get(): String = this.first
inline val <T> Fun<T>.lambda get(): T = this.second
inline fun <T> Fun<T>.adjustName(f: (String) -> String): Fun<T> = f(name) to lambda

typealias Feature0<T, R> = Fun<Expect<T>.() -> Expect<R>>
typealias Feature1<T, A1, R> = Fun<Expect<T>.(A1) -> Expect<R>>
typealias Feature2<T, A1, A2, R> = Fun<Expect<T>.(A1, A2) -> Expect<R>>

typealias Fun0<T> = Feature0<T, T>
typealias Fun1<T, A1> = Feature1<T, A1, T>
typealias Fun2<T, A1, A2> = Feature2<T, A1, A2, T>

inline operator fun <T, R> Feature0<T, R>.invoke(expect: Expect<T>): Expect<R> = this.second(expect)
inline operator fun <T, A1, R> Feature1<T, A1, R>.invoke(expect: Expect<T>, a1: A1): Expect<R> = this.second(expect, a1)
inline operator fun <T, A1, A2, R> Feature2<T, A1, A2, R>.invoke(expect: Expect<T>, a1: A1, a2: A2): Expect<R> =
    this.second(expect, a1, a2)

inline fun <T, R> Feature0<T, R>.forSubjectLess(): Pair<String, Expect<T>.() -> Unit> =
    this.name to expectLambda { this@forSubjectLess(this) }

inline fun <T, A1, R> Feature1<T, A1, R>.forSubjectLess(a1: A1): Pair<String, Expect<T>.() -> Unit> =
    this.name to expectLambda { this@forSubjectLess(this, a1) }

inline fun <T, A1, A2, R> Feature2<T, A1, A2, R>.forSubjectLess(a1: A1, a2: A2): Pair<String, Expect<T>.() -> Unit> =
    this.name to expectLambda { this@forSubjectLess(this, a1, a2) }


fun <T> Triple<String, Expect<T>.() -> Unit, Pair<T, T>>.adjustName(f: (String) -> String): Triple<String, Expect<T>.() -> Unit, Pair<T, T>> =
    Triple(f(first), second, third)

inline fun <T, R> Feature0<T, R>.forChecking(
    holdingSubject: T,
    failingSubject: T,
    crossinline subAssert: Expect<R>.() -> Unit
): Triple<String, Expect<T>.() -> Unit, Pair<T, T>> =
    checkingTriple(this.name, { this@forChecking(this).subAssert() }, holdingSubject, failingSubject)

inline fun <T, A1, R> Feature1<T, A1, R>.forChecking(
    a1: A1,
    holdingSubject: T,
    failingSubject: T,
    noinline subAssert: (Expect<R>.() -> Unit)? = null
): Triple<String, Expect<T>.() -> Unit, Pair<T, T>> =
    checkingTriple(this.name, {
        val assertionContainer = this@forChecking(this, a1)
        if (subAssert != null) assertionContainer.subAssert()
    }, holdingSubject, failingSubject)

inline fun <T, A1, A2, R> Feature2<T, A1, A2, R>.forChecking(
    a1: A1,
    a2: A2,
    holdingSubject: T,
    failingSubject: T,
    noinline subAssert: (Expect<R>.() -> Unit)?
): Triple<String, Expect<T>.() -> Unit, Pair<T, T>> =
    checkingTriple(this.name, {
        val assertionContainer = this@forChecking(this, a1, a2)
        if (subAssert != null) assertionContainer.subAssert()
    }, holdingSubject, failingSubject)


inline fun <T> Fun0<T>.forChecking(
    holdingSubject: T,
    failingSubject: T
): Triple<String, Expect<T>.() -> Unit, Pair<T, T>> =
    checkingTriple(this.name, { this@forChecking(this) }, holdingSubject, failingSubject)

inline fun <T, A1> Fun1<T, A1>.forChecking(
    a1: A1,
    holdingSubject: T,
    failingSubject: T
): Triple<String, Expect<T>.() -> Unit, Pair<T, T>> =
    checkingTriple(this.name, { this@forChecking(this, a1) }, holdingSubject, failingSubject)

inline fun <T, A1, A2> Fun2<T, A1, A2>.forChecking(
    a1: A1,
    a2: A2,
    holdingSubject: T,
    failingSubject: T
): Triple<String, Expect<T>.() -> Unit, Pair<T, T>> =
    checkingTriple(this.name, { this@forChecking(this, a1, a2) }, holdingSubject, failingSubject)


//TODO rename, we only introduced it so that it is easier to migrate specs from JVM to common
fun String.Companion.format(string: String, arg: Any, vararg otherArgs: Any): String = string.format(arg, *otherArgs)

expect val lineSeperator: String

//@formatter:off
inline fun <T, R> feature0(f: KFunction1<Expect<T>, Expect<R>>, suffix: String = ""): Feature0<T, R> = f.name + suffix to f
inline fun <T, A1, R> feature1(f: KFunction2<Expect<T>, A1, Expect<R>>, suffix: String = ""): Feature1<T, A1, R> = f.name + suffix to f
inline fun <T, A1, A2, R> feature2(f: KFunction3<Expect<T>, A1, A2, Expect<R>>, suffix: String = ""): Feature2<T, A1, A2, R> = f.name + suffix to f

inline fun <T> fun0(f: KFunction1<Expect<T>, Expect<T>>, suffix: String = ""): Fun0<T> = f.name + suffix to f
inline fun <T, A1> fun1(f: KFunction2<Expect<T>, A1, Expect<T>>, suffix: String = ""): Fun1<T, A1> = f.name + suffix to f
inline fun <T, A1, A2> fun2(f: KFunction3<Expect<T>, A1, A2, Expect<T>>, suffix: String = ""): Fun2<T, A1, A2> = f.name + suffix to f
//@formatter:on

fun <T> notImplemented(): T = throw NotImplementedError()
