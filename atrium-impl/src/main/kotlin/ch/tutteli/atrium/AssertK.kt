@file:JvmName("AssertK")

package ch.tutteli.atrium

import ch.tutteli.atrium.assertions.IsAAssertion
import ch.tutteli.atrium.assertions.IsNotNullAssertion
import ch.tutteli.atrium.checking.FeatureAssertionChecker
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.reporting.RawString
import kotlin.reflect.KProperty0

// ---------------------------------------------------------------------------------
// Narrowing/feature assertions ----------------------------------------------------
// ---------------------------------------------------------------------------------

inline fun <reified T : Any> IAssertionPlantNullable<T?>.isNotNull()
    = DownCastBuilder.create(commonFields, IsNotNullAssertion(subject))
    .cast()

inline fun <reified T : Any> IAssertionPlantNullable<T?>.isNotNull(noinline createAssertions: IAssertionPlant<T>.() -> Unit)
    = DownCastBuilder.create(commonFields, IsNotNullAssertion(subject))
    .withLazyAssertions(createAssertions)
    .cast()

/**
 * Creates an `is a` assertion, which holds if the [IAssertionPlant.subject] is the same or a subtype of the expected type [TSub].
 */
inline fun <reified TSub : Any> IAssertionPlant<Any>.isA(): IAssertionPlant<TSub>
    = DownCastBuilder.create<TSub, Any>(commonFields, IsAAssertion(subject, TSub::class.java))
    .cast()

/**
 * Creates an `is a` assertion, which holds if the [IAssertionPlant.subject] is the same or a subtype of the expected type [TSub].
 */
inline fun <reified TSub : Any> IAssertionPlant<Any>.isA(noinline createAssertions: IAssertionPlant<TSub>.() -> Unit): IAssertionPlant<TSub>
    = DownCastBuilder.create<TSub, Any>(commonFields, IsAAssertion(subject, TSub::class.java))
    .withLazyAssertions(createAssertions)
    .cast()

/**
 * Allows to define assertions which are immediately evaluated (see [AssertionPlantFactory.newCheckImmediately]) for a specific [feature].
 */
fun <T : Any, TFeature : Any> IAssertionPlant<T>.its(feature: KProperty0<TFeature>): IAssertionPlant<TFeature>
    = AssertionPlantFactory.newCheckImmediately(createCommonFieldsForFeatureFactory(feature))

/**
 * Allows to define assertions which are lazily evaluated (see [AssertionPlantFactory.newCheckLazily]) for a specific [feature].
 */
fun <T : Any, TFeature : Any> IAssertionPlant<T>.its(feature: KProperty0<TFeature>, createAssertions: IAssertionPlant<TFeature>.() -> Unit): IAssertionPlant<TFeature> {
    val featurePlant = AssertionPlantFactory.newCheckLazily(createCommonFieldsForFeatureFactory(feature))
    return AssertionPlantFactory.createAssertionsAndCheckThem(featurePlant, createAssertions)
}

/**
 * Allows to define assertions for a specific [feature] which is nullable (see [AssertionPlantFactory.newNullable]).
 */
fun <T : Any, TFeature : Any?> IAssertionPlant<T>.its(feature: KProperty0<TFeature>): IAssertionPlantNullable<TFeature>
    = AssertionPlantFactory.newNullable(createCommonFieldsForFeatureFactory(feature))

private fun <T : Any, TFeature : Any?> IAssertionPlant<T>.createCommonFieldsForFeatureFactory(feature: KProperty0<TFeature>)
    = IAssertionPlantWithCommonFields.CommonFields(feature.name, feature.get(), FeatureAssertionChecker(this))

/**
 * Creates an [isNotNull] assertion for the [Throwable.message] of a [Throwable] and
 * allows to define further assertions for the message, which are then immediately evaluated (see [AssertionPlantFactory.newCheckImmediately]).
 */
val <T : Throwable> IAssertionPlant<T>.message: IAssertionPlant<String> get() = its(subject::message).isNotNull()

/**
 * Creates an [isNotNull] assertion for the [Throwable.message] of a [Throwable] and
 * allows to define further assertions for the message, which are then lazily evaluated (see [AssertionPlantFactory.newCheckLazily]).
 */
fun <T : Throwable> IAssertionPlant<T>.message(createAssertions: IAssertionPlant<String>.() -> Unit): IAssertionPlant<String>
    = its(subject::message).isNotNull(createAssertions)


// ---------------------------------------------------------------------------------
// Assertions ----------------------------------------------------------------------
// ---------------------------------------------------------------------------------

fun <T : Any> IAssertionPlant<T>.genericCheck(feature: KProperty0<Boolean>)
    = createAndAddAssertion("generic check ${feature.name}", true, { feature.get() })

fun <T : Any> IAssertionPlant<T>.toBe(expected: T)
    = createAndAddAssertion("to be", expected, { subject == expected })

fun IAssertionPlant<Int>.isSmallerThan(expected: Int)
    = createAndAddAssertion("is smaller than", expected, { subject < expected })

fun IAssertionPlant<Int>.isSmallerOrEquals(expected: Int)
    = createAndAddAssertion("is smaller or equals", expected, { subject <= expected })

fun IAssertionPlant<Int>.isGreaterThan(expected: Int)
    = createAndAddAssertion("is greater than", expected, { subject > expected })

fun IAssertionPlant<Int>.isGreaterOrEquals(expected: Int)
    = createAndAddAssertion("is greater or equals", expected, { subject >= expected })

fun <T : CharSequence> IAssertionPlant<T>.contains(expected: CharSequence)
    = createAndAddAssertion("contains", expected, { subject.contains(expected) })

fun <T : CharSequence> IAssertionPlant<T>.contains(expected: CharSequence, vararg otherExpected: CharSequence): IAssertionPlant<T> {
    val plant = contains(expected)
    otherExpected.forEach { contains(it) }
    return plant
}

fun <T : CharSequence> IAssertionPlant<T>.contains(expected: Any, vararg otherExpected: Any): IAssertionPlant<T> {
    val plant = contains(expected.toString())
    otherExpected.forEach { contains(it.toString()) }
    return plant
}

fun <T : CharSequence> IAssertionPlant<T>.startsWith(expected: CharSequence)
    = createAndAddAssertion("starts with", expected, { subject.startsWith(expected) })

fun <T : CharSequence> IAssertionPlant<T>.endsWith(expected: CharSequence)
    = createAndAddAssertion("ends with", expected, { subject.endsWith(expected) })

fun <T : CharSequence> IAssertionPlant<T>.isEmpty()
    = createAndAddAssertion("is", RawString("empty"), { subject.isEmpty() })

fun <T : Collection<*>> IAssertionPlant<T>.hasSize(size: Int)
    = createAndAddAssertion("has size", size, { subject.size == size })
