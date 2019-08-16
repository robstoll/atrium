@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
@file:JvmMultifileClass
@file:JvmName("AnyAssertionsKt")

package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.api.cc.en_GB.toBe
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.robstoll.lib.creating.any.typetransformation.creators._downCast
import ch.tutteli.atrium.domain.robstoll.lib.creating.any.typetransformation.failurehandlers.ExplanatoryFailureHandler
import ch.tutteli.atrium.translations.DescriptionTypeTransformationAssertion
import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName
import kotlin.reflect.KClass

fun <T : Any> _isNullable(
    plant: AssertionPlantNullable<T?>,
    type: KClass<T>,
    expectedOrNull: T?
): Assertion =
    if (expectedOrNull == null) AssertImpl.any.toBeNull(plant)
    else AssertImpl.any.isNotNullBut(plant, type, expectedOrNull)

fun <T : Any> _isNotNull(
    plant: AssertionPlantNullable<T?>,
    type: KClass<T>,
    assertionCreator: AssertionPlant<T>.() -> Unit
): Assertion = AssertImpl.collector.collectNullable(plant) {
    _downCast(DescriptionTypeTransformationAssertion.IS_A, type, this, assertionCreator, ExplanatoryFailureHandler())
}

fun <T : Any> _isNotNullBut(
    plant: AssertionPlantNullable<T?>,
    type: KClass<T>,
    expected: T
): Assertion {
    return AssertImpl.any.isNotNull(plant, type) { toBe(expected) }
}

fun <T : Any> _isNullIfNullGivenElse(
    plant: AssertionPlantNullable<T?>,
    type: KClass<T>,
    assertionCreatorOrNull: (AssertionPlant<T>.() -> Unit)?
): Assertion =
    if (assertionCreatorOrNull == null) AssertImpl.any.toBeNull(plant)
    else AssertImpl.any.isNotNull(plant, type, assertionCreatorOrNull)
