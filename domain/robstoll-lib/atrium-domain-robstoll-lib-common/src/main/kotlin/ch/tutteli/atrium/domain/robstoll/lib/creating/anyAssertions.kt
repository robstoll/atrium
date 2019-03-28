package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.api.cc.en_GB.toBe
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.robstoll.lib.creating.any.typetransformation.creators._downCast
import ch.tutteli.atrium.domain.robstoll.lib.creating.any.typetransformation.failurehandlers.ExplanatoryFailureHandler
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.translations.DescriptionAnyAssertion.*
import ch.tutteli.atrium.translations.DescriptionTypeTransformationAssertion
import kotlin.reflect.KClass

fun <T : Any> _toBe(plant: AssertionPlant<T>, expected: T)
    = AssertImpl.builder.createDescriptive(TO_BE, expected) { plant.subject == expected }

fun <T : Any> _notToBe(plant: AssertionPlant<T>, expected: T)
    = AssertImpl.builder.createDescriptive(NOT_TO_BE, expected) { plant.subject != expected }

fun <T : Any> _isSame(plant: AssertionPlant<T>, expected: T)
    = AssertImpl.builder.createDescriptive(IS_SAME, expected) { plant.subject === expected }

fun <T : Any> _isNotSame(plant: AssertionPlant<T>, expected: T)
    = AssertImpl.builder.createDescriptive(IS_NOT_SAME, expected) { plant.subject !== expected }

fun <T : Any?> _isNull(plant: AssertionPlantNullable<T>)
    = AssertImpl.builder.createDescriptive(TO_BE, RawString.NULL) { plant.subject == null }

fun <T : Any> _isNullable(
    plant: AssertionPlantNullable<T?>,
    type: KClass<T>,
    expectedOrNull: T?
): Assertion =
    if (expectedOrNull == null) AssertImpl.any.isNull(plant)
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
    return AssertImpl.any.isNotNull(plant, type){ toBe(expected) }
}

fun <T : Any> _isNullIfNullGivenElse(
    plant: AssertionPlantNullable<T?>,
    type: KClass<T>,
    assertionCreatorOrNull: (AssertionPlant<T>.() -> Unit)?
): Assertion =
    if (assertionCreatorOrNull == null) AssertImpl.any.isNull(plant)
    else AssertImpl.any.isNotNull(plant, type, assertionCreatorOrNull)
