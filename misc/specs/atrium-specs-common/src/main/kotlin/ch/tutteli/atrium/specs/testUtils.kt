@file:Suppress("NOTHING_TO_INLINE")

package ch.tutteli.atrium.specs

import ch.tutteli.atrium.core.polyfills.format
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.utils.expectLambda
import ch.tutteli.atrium.translations.DescriptionAnyExpectation
import ch.tutteli.atrium.translations.DescriptionBasic
import kotlin.jvm.JvmName
import kotlin.reflect.*

typealias SpecPair<T> = Pair<String, T>

inline val SpecPair<out Any?>.name get(): String = this.first
inline val <T> SpecPair<T>.lambda get(): T = this.second
inline fun <T> SpecPair<T>.withFeatureSuffix(): SpecPair<T> = "$name (feature)" to lambda
inline fun <T> SpecPair<T>.withNullableSuffix(): SpecPair<T> = "$name nullable" to lambda
inline fun <T> SpecPair<T>.adjustName(f: (String) -> String): SpecPair<T> = f(name) to lambda

typealias Feature0<T, R> = SpecPair<Expect<T>.() -> Expect<R>>
typealias Feature1<T, A1, R> = SpecPair<Expect<T>.(A1) -> Expect<R>>
typealias Feature2<T, A1, A2, R> = SpecPair<Expect<T>.(A1, A2) -> Expect<R>>
typealias Feature3<T, A1, A2, A3, R> = SpecPair<Expect<T>.(A1, A2, A3) -> Expect<R>>
typealias Feature4<T, A1, A2, A3, A4, R> = SpecPair<Expect<T>.(A1, A2, A3, A4) -> Expect<R>>
typealias Feature5<T, A1, A2, A3, A4, A5, R> = SpecPair<Expect<T>.(A1, A2, A3, A4, A5) -> Expect<R>>

typealias Fun0<T> = Feature0<T, T>
typealias Fun1<T, A1> = Feature1<T, A1, T>
typealias Fun2<T, A1, A2> = Feature2<T, A1, A2, T>
typealias Fun3<T, A1, A2, A3> = Feature3<T, A1, A2, A3, T>
typealias Fun4<T, A1, A2, A3, A4> = Feature4<T, A1, A2, A3, A4, T>
typealias Fun5<T, A1, A2, A3, A4, A5> = Feature5<T, A1, A2, A3, A4, A5, T>

inline operator fun <T, R> Feature0<T, R>.invoke(expect: Expect<T>): Expect<R> = this.second(expect)
inline operator fun <T, A1, R> Feature1<T, A1, R>.invoke(expect: Expect<T>, a1: A1): Expect<R> = this.second(expect, a1)
inline operator fun <T, A1, A2, R> Feature2<T, A1, A2, R>.invoke(expect: Expect<T>, a1: A1, a2: A2): Expect<R> =
    this.second(expect, a1, a2)

inline operator fun <T, A1, A2, A3, R> Feature3<T, A1, A2, A3, R>.invoke(
    expect: Expect<T>, a1: A1, a2: A2, a3: A3
): Expect<R> = this.second(expect, a1, a2, a3)

inline operator fun <T, A1, A2, A3, A4, R> Feature4<T, A1, A2, A3, A4, R>.invoke(
    expect: Expect<T>, a1: A1, a2: A2, a3: A3, a4: A4
): Expect<R> = this.second(expect, a1, a2, a3, a4)

inline operator fun <T, A1, A2, A3, A4, A5, R> Feature5<T, A1, A2, A3, A4, A5, R>.invoke(
    expect: Expect<T>, a1: A1, a2: A2, a3: A3, a4: A4, a5: A5
): Expect<R> = this.second(expect, a1, a2, a3, a4, a5)


inline fun <T, R> Feature0<T, R>.forSubjectLess(): Pair<String, Expect<T>.() -> Unit> =
    this.name to expectLambda { this@forSubjectLess.invoke(this) }

inline fun <T, A1, R> Feature1<T, A1, R>.forSubjectLess(a1: A1): Pair<String, Expect<T>.() -> Unit> =
    this.name to expectLambda { this@forSubjectLess.invoke(this, a1) }

inline fun <T, A1, A2, R> Feature2<T, A1, A2, R>.forSubjectLess(a1: A1, a2: A2): Pair<String, Expect<T>.() -> Unit> =
    this.name to expectLambda { this@forSubjectLess.invoke(this, a1, a2) }

inline fun <T, A1, A2, A3, R> Feature3<T, A1, A2, A3, R>.forSubjectLess(
    a1: A1, a2: A2, a3: A3
): Pair<String, Expect<T>.() -> Unit> = this.name to expectLambda { this@forSubjectLess.invoke(this, a1, a2, a3) }

inline fun <T, A1, A2, A3, A4, R> Feature4<T, A1, A2, A3, A4, R>.forSubjectLess(
    a1: A1, a2: A2, a3: A3, a4: A4
): Pair<String, Expect<T>.() -> Unit> = this.name to expectLambda { this@forSubjectLess.invoke(this, a1, a2, a3, a4) }

inline fun <T, A1, A2, A3, A4, A5, R> Feature5<T, A1, A2, A3, A4, A5, R>.forSubjectLess(
    a1: A1, a2: A2, a3: A3, a4: A4, a5: A5
): Pair<String, Expect<T>.() -> Unit> =
    this.name to expectLambda { this@forSubjectLess.invoke(this, a1, a2, a3, a4, a5) }


inline fun <T, R> Fun1<T, Expect<R>.() -> Unit>.forAssertionCreatorSpec(
    containsNot: String,
    noinline subAssert: (Expect<R>.() -> Unit)?
): Triple<String, String, Pair<Expect<T>.() -> Expect<T>, Expect<T>.() -> Expect<T>>> =
    assertionCreatorSpecTriple(
        this.name,
        containsNot,
        { this@forAssertionCreatorSpec(this, subAssert ?: throw IllegalArgumentException("pass a lambda")) },
        { this@forAssertionCreatorSpec(this) {} }
    )

inline fun <T, A1, R> Fun2<T, A1, Expect<R>.() -> Unit>.forAssertionCreatorSpec(
    containsNot: String,
    a1: A1,
    noinline subAssert: (Expect<R>.() -> Unit)?
): Triple<String, String, Pair<Expect<T>.() -> Expect<T>, Expect<T>.() -> Expect<T>>> =
    assertionCreatorSpecTriple(
        this.name,
        containsNot,
        { this@forAssertionCreatorSpec(this, a1, subAssert ?: throw IllegalArgumentException("pass a lambda")) },
        { this@forAssertionCreatorSpec(this, a1) {} }
    )

inline fun <T, R> Fun2<T, Expect<R>.() -> Unit, Array<out Expect<R>.() -> Unit>>.forAssertionCreatorSpec(
    containsNot1: String,
    containsNot2: String,
    noinline subAssert: Expect<R>.() -> Unit,
    subAsserts: Array<out Expect<R>.() -> Unit>
): Array<Triple<String, String, Pair<Expect<T>.() -> Expect<T>, Expect<T>.() -> Expect<T>>>> =
    arrayOf(
        assertionCreatorSpecTriple(
            this.name + " - first empty",
            containsNot1,
            { this@forAssertionCreatorSpec(this, subAssert, subAsserts) },
            { this@forAssertionCreatorSpec(this, {}, subAsserts) }
        ),
        assertionCreatorSpecTriple(
            this.name + " - second empty",
            containsNot2,
            { this@forAssertionCreatorSpec(this, subAssert, subAsserts) },
            { this@forAssertionCreatorSpec(this, subAssert, arrayOf(expectLambda<R> {}) + subAsserts.drop(1)) }
        )
    )

inline fun <T, R, A1> Fun3<T, Expect<R>.() -> Unit, Array<out Expect<R>.() -> Unit>, A1>.forAssertionCreatorSpec(
    containsNot1: String,
    containsNot2: String,
    noinline subAssert: Expect<R>.() -> Unit,
    subAsserts: Array<out Expect<R>.() -> Unit>,
    a1: A1
): Array<Triple<String, String, Pair<Expect<T>.() -> Expect<T>, Expect<T>.() -> Expect<T>>>> =
    arrayOf(
        assertionCreatorSpecTriple(
            this.name + " - first empty",
            containsNot1,
            { this@forAssertionCreatorSpec(this, subAssert, subAsserts, a1) },
            { this@forAssertionCreatorSpec(this, {}, subAsserts, a1) }
        ),
        assertionCreatorSpecTriple(
            this.name + " - second empty",
            containsNot2,
            { this@forAssertionCreatorSpec(this, subAssert, subAsserts, a1) },
            { this@forAssertionCreatorSpec(this, subAssert, arrayOf(expectLambda<R> {}) + subAsserts.drop(1), a1) }
        )
    )


fun <T, R> unifySignatures(
    f0: Feature0<T, R>,
    f1: Fun1<T, Expect<R>.() -> Unit>
): List<Triple<String, Expect<T>.(Expect<R>.() -> Unit) -> Expect<T>, Boolean>> =
    listOf(
        Triple(f0.name, f0.withSubAssertion(), false),
        Triple(f1.name, f1.lambda, true)
    )

@JvmName("unifySignatures1")
fun <T, A1, R> unifySignatures(
    f0: Feature1<T, A1, R>,
    f1: Fun2<T, A1, Expect<R>.() -> Unit>
): List<Triple<String, Expect<T>.(A1, Expect<R>.() -> Unit) -> Expect<T>, Boolean>> =
    listOf(
        Triple(f0.name, f0.withSubAssertion(), false),
        Triple(f1.name, f1.lambda, true)
    )

@JvmName("unifySignatures2")
fun <T, A1, A2, R> unifySignatures(
    f0: Feature2<T, A1, A2, R>,
    f1: Fun3<T, A1, A2, Expect<R>.() -> Unit>
): List<Triple<String, Expect<T>.(A1, A2, Expect<R>.() -> Unit) -> Expect<T>, Boolean>> =
    listOf(
        Triple(f0.name, f0.withSubAssertion(), false),
        Triple(f1.name, f1.lambda, true)
    )

@JvmName("unifySignatures0Feature")
fun <T, R> unifySignatures(
    f0: Feature0<T, R>,
    f1: Feature1<T, Expect<R>.() -> Unit, R>
): List<Triple<String, Expect<T>.(Expect<R>.() -> Unit) -> Expect<R>, Boolean>> {
    val f0WithSubAssertion: Expect<T>.(Expect<R>.() -> Unit) -> Expect<R> =
        { f: Expect<R>.() -> Unit -> (f0.lambda)().apply(f) }
    return listOf(
        Triple(f0.name, f0WithSubAssertion, false),
        Triple(f1.name, f1.lambda, true)
    )
}

@JvmName("unifySignatures1Feature")
fun <T, A1, R> unifySignatures(
    f0: Feature1<T, A1, R>,
    f1: Feature2<T, A1, Expect<R>.() -> Unit, R>
): List<Triple<String, Expect<T>.(A1, Expect<R>.() -> Unit) -> Expect<R>, Boolean>> {
    val f0WithSubAssertion: Expect<T>.(A1, Expect<R>.() -> Unit) -> Expect<R> =
        { a1, f: Expect<R>.() -> Unit -> (f0.lambda)(a1).apply(f) }
    return listOf(
        Triple(f0.name, f0WithSubAssertion, false),
        Triple(f1.name, f1.lambda, true)
    )
}

@JvmName("unifySignaturesSameReturn0")
fun <T> unifySignatures(
    f0: Feature0<T, T>,
    f1: Fun1<T, Expect<T>.() -> Unit>
): List<Triple<String, Expect<T>.(Expect<T>.() -> Unit) -> Expect<T>, Boolean>> =
    listOf(
        Triple(f0.name, f0.withSubAssertion(), false),
        Triple(f1.name, f1.lambda, true)
    )

@JvmName("unifySignaturesSameReturn1")
fun <T, A1> unifySignatures(
    f0: Feature1<T, A1, T>,
    f1: Fun2<T, A1, Expect<T>.() -> Unit>
): List<Triple<String, Expect<T>.(A1, Expect<T>.() -> Unit) -> Expect<T>, Boolean>> =
    listOf(
        Triple(f0.name, f0.withSubAssertion(), false),
        Triple(f1.name, f1.lambda, true)
    )

@JvmName("unifySignaturesSameReturn2")
fun <T, A1, A2> unifySignatures(
    f0: Feature2<T, A1, A2, T>,
    f1: Fun3<T, A1, A2, Expect<T>.() -> Unit>
): List<Triple<String, Expect<T>.(A1, A2, Expect<T>.() -> Unit) -> Expect<T>, Boolean>> =
    listOf(
        Triple(f0.name, f0.withSubAssertion(), false),
        Triple(f1.name, f1.lambda, true)
    )

@Suppress("UNCHECKED_CAST")
fun <F> uncheckedToNonNullable(f: List<F>, fNullable: List<Any>): List<F> = f + (fNullable as List<F>)

@Suppress("UNCHECKED_CAST")
fun <F> uncheckedToNonNullable(f: F, fNullable: Any): List<F> = listOf(f, fNullable as F)


internal inline fun <T, R> Feature0<T, R>.withSubAssertion(): Expect<T>.(Expect<R>.() -> Unit) -> Expect<T> =
    { f: Expect<R>.() -> Unit -> apply { (lambda)().f() } }

@JvmName("withSubAssertion1")
internal inline fun <T, R, A1> Feature1<T, A1, R>.withSubAssertion()
    : Expect<T>.(A1, Expect<R>.() -> Unit) -> Expect<T> =
    { a1, f: Expect<R>.() -> Unit -> apply { (lambda)(a1).f() } }

@JvmName("withSubAssertion2")
internal inline fun <T, R, A1, A2> Feature2<T, A1, A2, R>.withSubAssertion():
    Expect<T>.(A1, A2, Expect<R>.() -> Unit) -> Expect<T> =
    { a1, a2, f: Expect<R>.() -> Unit -> apply { (lambda)(a1, a2).f() } }

@JvmName("withSubAssertion3")
internal inline fun <T, R, A1, A2, A3> Feature3<T, A1, A2, A3, R>.withSubAssertion():
    Expect<T>.(A1, A2, A3, Expect<R>.() -> Unit) -> Expect<T> =
    { a1, a2, a3, f: Expect<R>.() -> Unit -> apply { (lambda)(a1, a2, a3).f() } }

//@formatter:off
inline fun <T, R> property(f: KProperty1<Expect<T>, Expect<R>>): Feature0<T, R> = (f.name to f).withFeatureSuffix()

//TODO simplify further instead of adjustName { "$it nullable" } once https://youtrack.jetbrains.com/issue/KT-35992 is fixed
//@JvmName("feature0Nullable")
//inline fun <T, R> feature0(f: KFunction1<Expect<T>, Expect<R>>): Feature0<T, R> = "${f.name} (nullable feature)" to f
inline fun <T, R> feature0(f: KFunction1<Expect<T>, Expect<R>>): Feature0<T, R> = (f.name to f).withFeatureSuffix()
inline fun <T, A1, R> feature1(f: KFunction2<Expect<T>, A1, Expect<R>>): Feature1<T, A1, R> = (f.name to f).withFeatureSuffix()
inline fun <T, A1, A2, R> feature2(f: KFunction3<Expect<T>, A1, A2, Expect<R>>): Feature2<T, A1, A2, R> = (f.name to f).withFeatureSuffix()
inline fun <T, A1, A2, A3, R> feature3(f: KFunction4<Expect<T>, A1, A2, A3, Expect<R>>): Feature3<T, A1, A2, A3, R> = (f.name to f).withFeatureSuffix()
inline fun <T, A1, A2, A3, A4, R> feature4(f: KFunction5<Expect<T>, A1, A2, A3, A4, Expect<R>>): Feature4<T, A1, A2, A3, A4, R> = (f.name to f).withFeatureSuffix()

inline fun <T> fun0(f: KFunction1<Expect<T>, Expect<T>>): Fun0<T> = f.name to f
inline fun <T, A1> fun1(f: KFunction2<Expect<T>, A1, Expect<T>>): Fun1<T, A1> = f.name to f
inline fun <T, A1, A2> fun2(f: KFunction3<Expect<T>, A1, A2, Expect<T>>): Fun2<T, A1, A2> = f.name to f
inline fun <T, A1, A2, A3> fun3(f: KFunction4<Expect<T>, A1, A2, A3, Expect<T>>): Fun3<T, A1, A2, A3> = f.name to f
inline fun <T, A1, A2, A3, A4> fun4(f: KFunction5<Expect<T>, A1, A2, A3, A4, Expect<T>>): Fun4<T, A1, A2, A3, A4> = f.name to f
inline fun <T, A1, A2, A3, A4, A5> fun5(f: KFunction6<Expect<T>, A1, A2, A3, A4, A5, Expect<T>>): Fun5<T, A1, A2, A3, A4, A5> = f.name to f
//@formatter:on

fun <T> notImplemented(): T = throw NotImplementedError()

val toEqualDescr = DescriptionAnyExpectation.TO_EQUAL.getDefault()
val toBeDescr = DescriptionBasic.TO_BE.getDefault()
val notToBeDescr = DescriptionBasic.NOT_TO_BE.getDefault()
val toBeAnInstanceOfDescr = DescriptionAnyExpectation.TO_BE_AN_INSTANCE_OF.getDefault()

expect val lineSeparator: String

fun showsSubAssertionIf(hasExtraHint: Boolean): String = if (hasExtraHint) "; shows intended sub assertion" else ""

val emptyIterableLikeTypes = mapOf(
    "Iterable" to listOf<Int>(),
    "Sequence" to sequenceOf<Int>(),
    "Array" to arrayOf<Int>(),
    "CharArray" to charArrayOf(),
    "ByteArray" to byteArrayOf(),
    "ShortArray" to shortArrayOf(),
    "IntArray" to intArrayOf(),
    "LongArray" to longArrayOf(),
    "FloatArray" to floatArrayOf(),
    "DoubleArray" to doubleArrayOf(),
    "BooleanArray" to booleanArrayOf()
)

val iterableLikeTypesWithElement1 = mapOf(
    "Iterable" to listOf(1),
    "Sequence" to sequenceOf(1),
    "Array" to arrayOf(1),
    "CharArray" to charArrayOf('v'),
    "ByteArray" to byteArrayOf(1),
    "ShortArray" to shortArrayOf(1),
    "IntArray" to intArrayOf(1),
    "LongArray" to longArrayOf(1),
    "FloatArray" to floatArrayOf(1.0f),
    "DoubleArray" to doubleArrayOf(1.0),
    "BooleanArray" to booleanArrayOf(false)
)
