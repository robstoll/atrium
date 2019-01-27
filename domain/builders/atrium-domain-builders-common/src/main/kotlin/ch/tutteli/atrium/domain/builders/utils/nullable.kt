@file:Suppress("NOTHING_TO_INLINE")

package ch.tutteli.atrium.domain.builders.utils

import kotlin.reflect.*
import kotlin.jvm.JvmName


/**
 * Turns the given type into a nullable type.
 *
 * Intended to be used in conjunction with [platform types](https://kotlinlang.org/docs/reference/java-interop.html#notation-for-platform-types)
 * such as `String!` or in other words, when you deal with Java and you want to turn a platform type into a nullable type.
 *
 * Basically it is a replacement for cast but without introducing one explicitly (we only give an additional hint to the
 * compiler that we expect a nullable type and not a non-nullable one). For instance, instead of writing
 * `getPersons() as List<String>?` you can write `nullable(getPersons())`
 */
inline fun <T> nullable(t: T): T? = t

/**
 * Turns the given function reference into a function reference with a nullable return type.
 *
 * Intended to be used in conjunction with [platform types](https://kotlinlang.org/docs/reference/java-interop.html#notation-for-platform-types)
 * such as `String!` or in other words, when you deal with Java and you want to turn the return type (which is a platform
 * type) of your function into a nullable type.
 */
inline fun <T> nullable(t: KFunction0<T>): KFunction0<T?> = t

/**
 * Turns an [Iterable] into an iterable with a nullable entry type.
 *
 * Intended to be used in conjunction with [platform types](https://kotlinlang.org/docs/reference/java-interop.html#notation-for-platform-types)
 * such as `Iterable<String!>` or in other words, when you deal with Java and you want to turn a platform type into a nullable type.
 *
 * Basically it is a replacement for cast but without introducing one explicitly (we only give an additional hint to the
 * compiler that we expect a nullable type and not a non-nullable one). For instance, instead of writing
 * `getPersons() as List<String?>` you can write `nullableContainer(getPersons())`
 */
inline fun <T> nullableContainer(iterable: Iterable<T>): Iterable<T?> = iterable

/**
 * Turns an [Array] into an array with a nullable entry type.
 *
 * Intended to be used in conjunction with [platform types](https://kotlinlang.org/docs/reference/java-interop.html#notation-for-platform-types)
 * such as `Array<String!>` or in other words, when you deal with Java and you want to turn a platform type into a nullable type.
 *
 * Basically it is a replacement for cast but without introducing one explicitly (we only give an additional hint to the
 * compiler that we expect a nullable type and not a non-nullable one). For instance, instead of writing
 * `getPersons() as Array<String?>` you can write `nullableContainer(getPersons())`
 */
inline fun <T> nullableContainer(arr: Array<out T>): Array<out T?> = arr


/**
 * Turns a [Map] into a map with a nullable key type.
 *
 * Intended to be used in conjunction with [platform types](https://kotlinlang.org/docs/reference/java-interop.html#notation-for-platform-types)
 * such as `Map<Int!, `String!`>! or in other words, when you deal with Java and you want to turn a platform type into a
 * nullable type.
 *
 * Basically it is a replacement for cast but without introducing one explicitly (we only give an additional hint to the
 * compiler that we expect a nullable type and not a non-nullable one). For instance, instead of writing
 * `getPersons() as Map<String?, Person>` you can write `nullableKeyMap(getPersons())`
 */
inline fun <K, V: Any> nullableKeyMap(map: Map<out K, V>): Map<out K?, V> = map

/**
 * Turns a [Map] into a map with a nullable value type.
 *
 * Intended to be used in conjunction with [platform types](https://kotlinlang.org/docs/reference/java-interop.html#notation-for-platform-types)
 * such as `Map<Int!, `String!`>! or in other words, when you deal with Java and you want to turn a platform type into a
 * nullable type.
 *
 * Basically it is a replacement for cast but without introducing one explicitly (we only give an additional hint to the
 * compiler that we expect a nullable type and not a non-nullable one). For instance, instead of writing
 * `getPersons() as Map<String, Person?>` you can write `nullableValueMap(getPersons())`
 */
inline fun <K: Any, V> nullableValueMap(map: Map<K, V>): Map<K, V?> = map

/**
 * Turns a [Map] into a map with a nullable key and a nullable value type.
 *
 * Intended to be used in conjunction with [platform types](https://kotlinlang.org/docs/reference/java-interop.html#notation-for-platform-types)
 * such as `Map<Int!, `String!`>! or in other words, when you deal with Java and you want to turn a platform type into a
 * nullable type.
 *
 * Basically it is a replacement for cast but without introducing one explicitly (we only give an additional hint to the
 * compiler that we expect a nullable type and not a non-nullable one). For instance, instead of writing
 * `getPersons() as Map<String?, Person?>` you can write `nullableKeyValueMap(getPersons())`
 */
inline fun <K, V> nullableKeyValueMap(map: Map<out K, V>): Map<out K?, V?> = map

/**
 * Turns the given function reference into a function reference with a nullable return type.
 *
 * Intended to be used in conjunction with [platform types](https://kotlinlang.org/docs/reference/java-interop.html#notation-for-platform-types)
 * such as `String!` or in other words, when you deal with Java and you want to turn the return type (which is a platform
 * type) of your function into a nullable type.
 */
@JvmName("nullable1")
inline fun <T1, R> nullable(t: KFunction1<T1, R>): KFunction1<T1, R?> = t

/**
 * Turns the given function reference into a function reference with a nullable return type.
 *
 * Intended to be used in conjunction with [platform types](https://kotlinlang.org/docs/reference/java-interop.html#notation-for-platform-types)
 * such as `String!` or in other words, when you deal with Java and you want to turn the return type (which is a platform
 * type) of your function into a nullable type.
 */
@JvmName("nullable2")
inline fun <T1, T2, R> nullable(t: KFunction2<T1, T2, R>): KFunction2<T1, T2, R?> = t

/**
 * Turns the given function reference into a function reference with a nullable return type.
 *
 * Intended to be used in conjunction with [platform types](https://kotlinlang.org/docs/reference/java-interop.html#notation-for-platform-types)
 * such as `String!` or in other words, when you deal with Java and you want to turn the return type (which is a platform
 * type) of your function into a nullable type.
 */
@JvmName("nullable3")
inline fun <T1, T2, T3, R> nullable(t: KFunction3<T1, T2, T3, R>): KFunction3<T1, T2, T3, R?> = t

/**
 * Turns the given function reference into a function reference with a nullable return type.
 *
 * Intended to be used in conjunction with [platform types](https://kotlinlang.org/docs/reference/java-interop.html#notation-for-platform-types)
 * such as `String!` or in other words, when you deal with Java and you want to turn the return type (which is a platform
 * type) of your function into a nullable type.
 */
@JvmName("nullable4")
inline fun <T1, T2, T3, T4, R> nullable(t: KFunction4<T1, T2, T3, T4, R>): KFunction4<T1, T2, T3, T4, R?> = t

/**
 * Turns the given function reference into a function reference with a nullable return type.
 *
 * Intended to be used in conjunction with [platform types](https://kotlinlang.org/docs/reference/java-interop.html#notation-for-platform-types)
 * such as `String!` or in other words, when you deal with Java and you want to turn the return type (which is a platform
 * type) of your function into a nullable type.
 */
@JvmName("nullable5")
inline fun <T1, T2, T3, T4, T5, R> nullable(t: KFunction5<T1, T2, T3, T4, T5, R>): KFunction5<T1, T2, T3, T4, T5, R?> = t
