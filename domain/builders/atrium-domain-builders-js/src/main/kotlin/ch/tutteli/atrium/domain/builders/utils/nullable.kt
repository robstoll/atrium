@file:Suppress("NOTHING_TO_INLINE")

package ch.tutteli.atrium.domain.builders.utils

import kotlin.reflect.*

// Further nullable related functions are defined in domain-builders-common

/**
 * Turns the given function reference into a function reference with a nullable return type.
 *
 * Intended to be used in conjunction with [platform types](https://kotlinlang.org/docs/reference/java-interop.html#notation-for-platform-types)
 * such as `String!` or in other words, when you deal with Java and you want to turn the return type (which is a platform
 * type) of your function into a nullable type.
 */
inline fun <T1, R> nullable(t: KFunction1<T1, R>): KFunction1<T1, R?> = t

/**
 * Turns the given function reference into a function reference with a nullable return type.
 *
 * Intended to be used in conjunction with [platform types](https://kotlinlang.org/docs/reference/java-interop.html#notation-for-platform-types)
 * such as `String!` or in other words, when you deal with Java and you want to turn the return type (which is a platform
 * type) of your function into a nullable type.
 */
inline fun <T1, T2, R> nullable(t: KFunction2<T1, T2, R>):  KFunction2<T1, T2,  R?> = t

/**
 * Turns the given function reference into a function reference with a nullable return type.
 *
 * Intended to be used in conjunction with [platform types](https://kotlinlang.org/docs/reference/java-interop.html#notation-for-platform-types)
 * such as `String!` or in other words, when you deal with Java and you want to turn the return type (which is a platform
 * type) of your function into a nullable type.
 */
inline fun <T1, T2, T3, R> nullable(t: KFunction3<T1, T2, T3, R>):  KFunction3<T1, T2, T3, R?> = t

/**
 * Turns the given function reference into a function reference with a nullable return type.
 *
 * Intended to be used in conjunction with [platform types](https://kotlinlang.org/docs/reference/java-interop.html#notation-for-platform-types)
 * such as `String!` or in other words, when you deal with Java and you want to turn the return type (which is a platform
 * type) of your function into a nullable type.
 */
inline fun <T1, T2, T3, T4, R> nullable(t: KFunction4<T1, T2, T3, T4, R>): KFunction4<T1, T2, T3, T4, R?> = t

/**
 * Turns the given function reference into a function reference with a nullable return type.
 *
 * Intended to be used in conjunction with [platform types](https://kotlinlang.org/docs/reference/java-interop.html#notation-for-platform-types)
 * such as `String!` or in other words, when you deal with Java and you want to turn the return type (which is a platform
 * type) of your function into a nullable type.
 */
inline fun <T1, T2, T3, T4, T5, R> nullable(t: KFunction5<T1, T2, T3, T4, T5, R>): KFunction5<T1, T2, T3, T4, T5, R?> = t
