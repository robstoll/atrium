@file:JvmName("AssertK")

package ch.tutteli.assertk

import ch.tutteli.assertk.assertions.OneMessageAssertion
import ch.tutteli.assertk.checking.FeatureAssertionChecker
import ch.tutteli.assertk.checking.IAssertionChecker
import ch.tutteli.assertk.checking.ThrowingAssertionChecker
import ch.tutteli.assertk.reporting.DetailedObjectFormatter
import ch.tutteli.assertk.reporting.OnlyFailureReporter
import ch.tutteli.assertk.reporting.RawString
import ch.tutteli.assertk.reporting.SameLineAssertionMessageFormatter
import kotlin.reflect.KProperty0

fun <T : Any> assert(subject: T): IAssertionFactory<T>
    = AssertionFactory.newCheckImmediately("assert", subject)

fun <T : Any?> assert(subject: T): IAssertionFactoryNullable<T>
    = AssertionFactory.newNullable("assert", subject)

inline fun <T : Any> assert(subject: T, createAssertions: IAssertionFactory<T>.() -> Unit)
    = createAndCheckAssertions("assert", subject, createAssertions)

fun expect(act: () -> Unit): ThrowableFluent {
    val objectFormatter = DetailedObjectFormatter()
    val assertionMessageFormatter = SameLineAssertionMessageFormatter(objectFormatter)
    val reporter = OnlyFailureReporter(assertionMessageFormatter)
    return ThrowableFluent.create("expect to throw", act, ThrowingAssertionChecker(reporter))
}

/**
 * Use this function to create custom 'assert' functions which lazy evaluate the given assertions.
 */
inline fun <T : Any> createAndCheckAssertions(assertionVerb: String, subject: T, createAssertions: IAssertionFactory<T>.() -> Unit)
    = createAndCheckAssertions(AssertionFactory.new(assertionVerb, subject), createAssertions)

inline fun <T : Any> createAndCheckAssertions(factory: IAssertionFactory<T>, createAssertions: IAssertionFactory<T>.() -> Unit): IAssertionFactory<T> {
    factory.createAssertions()
    factory.checkAssertions()
    return factory
}

fun <T : Any, TSub : Any> IAssertionFactory<T>.and(feature: KProperty0<TSub>): IAssertionFactory<TSub>
    = AssertionFactory.newCheckImmediately(feature.name, feature.get(), FeatureAssertionChecker(this))

fun <T : Any, TSub : Any> IAssertionFactory<T>.and(feature: KProperty0<TSub>, createAssertions: IAssertionFactory<TSub>.() -> Unit): IAssertionFactory<TSub> {
    val featureFactory = AssertionFactory.new(feature.name, feature.get(), FeatureAssertionChecker(this))
    return createAndCheckAssertions(featureFactory, createAssertions)
}

fun <T : Any, TSub : Any?> IAssertionFactory<T>.and(feature: KProperty0<TSub>): IAssertionFactoryNullable<TSub>
    = AssertionFactory.newNullable(feature.name, feature.get(), FeatureAssertionChecker(this))

fun <T : Any> IAssertionFactoryNullable<T?>.isNotNull(createAssertions: IAssertionFactory<T>.() -> Unit)
    = isNotNull(this, { createAndCheckAssertions(assertionVerb, subject!!, createAssertions) })

fun <T : Any> IAssertionFactoryNullable<T?>.isNotNull()
    = isNotNull(this, { AssertionFactory.newCheckImmediately(assertionVerb, subject!!) })

private fun <T : Any> isNotNull(factoryNullable: IAssertionFactoryNullable<T?>, factory: () -> IAssertionFactory<T>): IAssertionFactory<T> {
    if (factoryNullable.subject != null) {
        return factory()
    } else {
        factoryNullable.assertionChecker.fail(factoryNullable.assertionVerb, RawString.NULL, OneMessageAssertion("is not", RawString.NULL, false))
        throw IllegalStateException("calling ${IAssertionChecker::class.java.simpleName}#${IAssertionChecker::fail.name} should throw an exception")
    }
}


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

fun IAssertionFactory<CharSequence>.contains(expected: CharSequence)
    = createAndAddAssertion("contains", expected, { subject.contains(expected) })

fun IAssertionFactory<CharSequence>.contains(expected: CharSequence, vararg otherExpected: CharSequence): IAssertionFactory<CharSequence> {
    val factory = contains(expected)
    otherExpected.forEach { contains(it) }
    return factory
}

fun IAssertionFactory<CharSequence>.startsWith(expected: CharSequence)
    = createAndAddAssertion("starts with", expected, { subject.startsWith(expected) })

fun IAssertionFactory<CharSequence>.endsWith(expected: CharSequence)
    = createAndAddAssertion("ends with", expected, { subject.endsWith(expected) })

fun IAssertionFactory<CharSequence>.isEmpty()
    = createAndAddAssertion("is", RawString("empty"), { subject.isEmpty() })

val IAssertionFactory<Throwable>.message: IAssertionFactory<String> get() = and(subject::message).isNotNull()
fun IAssertionFactory<Throwable>.message(createAssertions: IAssertionFactory<String>.() -> Unit): IAssertionFactory<String> = and(subject::message).isNotNull(createAssertions)
