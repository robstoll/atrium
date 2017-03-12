@file:JvmName("AssertK")

package ch.tutteli.assertk

import ch.tutteli.assertk.assertions.OneMessageAssertion
import ch.tutteli.assertk.checking.FeatureAssertionChecker
import ch.tutteli.assertk.checking.IAssertionChecker
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

fun <T : Any> IAssertionFactoryNullable<T?>.isNotNull(createAssertions: IAssertionFactory<T>.() -> Unit)
    = isNotNull(this, { AssertionFactory.newCheckLazilyAtTheEnd(assertionVerb, subject!!, createAssertions) })

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

fun <T : Collection<*>> IAssertionFactory<T>.hasSize(size: Int)
    = createAndAddAssertion("has size", size, { subject.size == size })
