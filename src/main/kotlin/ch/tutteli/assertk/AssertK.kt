@file:JvmName("AssertK")

package ch.tutteli.assertk

import ch.tutteli.assertk.assertions.IsAAssertion
import ch.tutteli.assertk.assertions.IsNotNullAssertion
import ch.tutteli.assertk.checking.FeatureAssertionChecker
import ch.tutteli.assertk.creating.*
import ch.tutteli.assertk.reporting.RawString
import kotlin.reflect.KProperty0

fun <T : Any, TFeature : Any> IAssertionPlant<T>.and(feature: KProperty0<TFeature>): IAssertionPlant<TFeature>
    = AssertionPlantFactory.newCheckImmediately(createCommonFieldsForFeatureFactory(feature))

fun <T : Any, TFeature : Any> IAssertionPlant<T>.and(feature: KProperty0<TFeature>, createAssertions: IAssertionPlant<TFeature>.() -> Unit): IAssertionPlant<TFeature> {
    val featurePlant = AssertionPlantFactory.newCheckLazily(createCommonFieldsForFeatureFactory(feature))
    return AssertionPlantFactory.createAssertionsAndCheckThem(featurePlant, createAssertions)
}

fun <T : Any, TFeature : Any?> IAssertionPlant<T>.and(feature: KProperty0<TFeature>): IAssertionPlantNullable<TFeature>
    = AssertionPlantFactory.newNullable(createCommonFieldsForFeatureFactory(feature))

private fun <T : Any, TFeature : Any?> IAssertionPlant<T>.createCommonFieldsForFeatureFactory(feature: KProperty0<TFeature>)
    = IAssertionPlantWithCommonFields.CommonFields(feature.name, feature.get(), FeatureAssertionChecker(this))


inline fun <reified T : Any> IAssertionPlantNullable<T?>.isNotNull()
    = DownCastFluent.create(commonFields, IsNotNullAssertion(subject))
    .cast()

inline fun <reified T : Any> IAssertionPlantNullable<T?>.isNotNull(noinline createAssertions: IAssertionPlant<T>.() -> Unit)
    = DownCastFluent.create(commonFields, IsNotNullAssertion(subject))
    .withLazyAssertions(createAssertions)
    .cast()

inline fun <reified TSub : Any> IAssertionPlant<Any>.isA(): IAssertionPlant<TSub>
    = DownCastFluent.create<TSub, Any>(commonFields, IsAAssertion(subject, TSub::class.java))
    .cast()

inline fun <reified TSub : Any> IAssertionPlant<Any>.isA(noinline createAssertions: IAssertionPlant<TSub>.() -> Unit): IAssertionPlant<TSub>
    = DownCastFluent.create<TSub, Any>(commonFields, IsAAssertion(subject, TSub::class.java))
    .withLazyAssertions(createAssertions)
    .cast()

// ---------------------------------------------------------------------------------
// Assertions ----------------------------------------------------------------------
// ---------------------------------------------------------------------------------

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

val <T : Throwable> IAssertionPlant<T>.message: IAssertionPlant<String> get() = and(subject::message).isNotNull()
fun <T : Throwable> IAssertionPlant<T>.message(createAssertions: IAssertionPlant<String>.() -> Unit): IAssertionPlant<String> = and(subject::message).isNotNull(createAssertions)

fun <T : Collection<*>> IAssertionPlant<T>.hasSize(size: Int)
    = createAndAddAssertion("has size", size, { subject.size == size })
