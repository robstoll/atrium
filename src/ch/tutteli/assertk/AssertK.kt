@file:JvmName("AssertK")

package ch.tutteli.assertk

import ch.tutteli.assertk.assertions.OneMessageAssertion
import ch.tutteli.assertk.checking.FeatureAssertionChecker
import ch.tutteli.assertk.creating.AssertionFactory
import ch.tutteli.assertk.creating.IAssertionFactory
import ch.tutteli.assertk.creating.IAssertionFactoryNullable
import ch.tutteli.assertk.reporting.RawString
import kotlin.reflect.KProperty0

fun <T : Any, TSub : Any> IAssertionFactory<T>.and(feature: KProperty0<TSub>): IAssertionFactory<TSub>
    = AssertionFactory.newCheckImmediately(feature.name, feature.get(), FeatureAssertionChecker(this))

fun <T : Any, TSub : Any> IAssertionFactory<T>.and(feature: KProperty0<TSub>, createAssertions: IAssertionFactory<TSub>.() -> Unit): IAssertionFactory<TSub> {
    val featureFactory = AssertionFactory.newCheckLazily(feature.name, feature.get(), FeatureAssertionChecker(this))
    return AssertionFactory.createAssertionsAndCheckThem(featureFactory, createAssertions)
}

fun <T : Any, TSub : Any?> IAssertionFactory<T>.and(feature: KProperty0<TSub>): IAssertionFactoryNullable<TSub>
    = AssertionFactory.newNullable(feature.name, feature.get(), FeatureAssertionChecker(this))

inline fun <reified T : Any> IAssertionFactoryNullable<T?>.isNotNull()
    = AssertionFactory.downCast(assertionVerb, subject, OneMessageAssertion("is not", RawString.NULL, subject != null), assertionChecker)

inline fun <reified T : Any> IAssertionFactoryNullable<T?>.isNotNull(crossinline createAssertions: IAssertionFactory<T>.() -> Unit)
    = AssertionFactory.downCast(assertionVerb, subject, OneMessageAssertion("is not", RawString.NULL, subject != null), assertionChecker, createAssertions)

// ---------------------------------------------------------------------------------
// Assertions ----------------------------------------------------------------------
// ---------------------------------------------------------------------------------

fun <T : Any> IAssertionFactory<T>.toBe(expected: T)
    = createAndAddAssertion("to be", expected, { subject == expected })

fun IAssertionFactory<Int>.isSmallerThan(expected: Int)
    = createAndAddAssertion("is smaller than", expected, { subject < expected })

fun IAssertionFactory<Int>.isSmallerOrEquals(expected: Int)
    = createAndAddAssertion("is smaller or equals", expected, { subject <= expected })

fun IAssertionFactory<Int>.isGreaterThan(expected: Int)
    = createAndAddAssertion("is greater than", expected, { subject > expected })

fun IAssertionFactory<Int>.isGreaterOrEquals(expected: Int)
    = createAndAddAssertion("is greater or equals", expected, { subject >= expected })

fun <T : CharSequence> IAssertionFactory<T>.contains(expected: CharSequence)
    = createAndAddAssertion("contains", expected, { subject.contains(expected) })

fun <T : CharSequence> IAssertionFactory<T>.contains(expected: CharSequence, vararg otherExpected: CharSequence): IAssertionFactory<T> {
    val factory = contains(expected)
    otherExpected.forEach { contains(it) }
    return factory
}

fun <T : CharSequence> IAssertionFactory<T>.startsWith(expected: CharSequence)
    = createAndAddAssertion("starts with", expected, { subject.startsWith(expected) })

fun <T : CharSequence> IAssertionFactory<T>.endsWith(expected: CharSequence)
    = createAndAddAssertion("ends with", expected, { subject.endsWith(expected) })

fun <T : CharSequence> IAssertionFactory<T>.isEmpty()
    = createAndAddAssertion("is", RawString("empty"), { subject.isEmpty() })

val <T : Throwable> IAssertionFactory<T>.message: IAssertionFactory<String> get() = and(subject::message).isNotNull()
fun <T : Throwable> IAssertionFactory<T>.message(createAssertions: IAssertionFactory<String>.() -> Unit): IAssertionFactory<String> = and(subject::message).isNotNull(createAssertions)

fun <T : Collection<*>> IAssertionFactory<T>.hasSize(size: Int)
    = createAndAddAssertion("has size", size, { subject.size == size })
