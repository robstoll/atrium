package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.api.cc.en_GB.toBe
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.polyfills.cast
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.robstoll.lib.creating.any.typetransformation.creators._downCast
import ch.tutteli.atrium.domain.robstoll.lib.creating.any.typetransformation.failurehandlers.ExplanatoryFailureHandler
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.translations.DescriptionAnyAssertion.*
import ch.tutteli.atrium.translations.DescriptionTypeTransformationAssertion
import kotlin.reflect.KClass

fun <T : Any> _toBe(plant: SubjectProvider<T>, expected: T) =
    ExpectImpl.builder.createDescriptive(TO_BE, expected) { plant.subject == expected }

fun <T : Any?> _toBeNull(plant: SubjectProvider<T>) =
    ExpectImpl.builder.createDescriptive(TO_BE, RawString.NULL) { plant.subject == null }

fun <T : Any> _notToBeNull(
    assertionContainer: Expect<T?>,
    type: KClass<T>,
    assertionCreator: Expect<T>.() -> Unit
): Assertion = ExpectImpl.collector.collect(assertionContainer) {
    ExpectImpl.changeSubject.reportBuilder(this)
        .withDescriptionAndRepresentation(DescriptionTypeTransformationAssertion.IS_A, type)
        .withCheck { type.isInstance(it) }
        //TODO #88 I think it would make more sense if the subject is a parameter here as well
        .withSubjectProvider { type.cast(this.subject) }
        .withSubAssertions(assertionCreator)
        .build()
}

fun <T : Any> _toBeNullable(
    assertionContainer: Expect<T?>,
    type: KClass<T>,
    expectedOrNull: T?
): Assertion = when (expectedOrNull) {
    null -> ExpectImpl.any.toBeNull(assertionContainer)
    else -> ExpectImpl.any.notToBeNull(assertionContainer, type) { toBe(expectedOrNull) }
}



fun <T : Any> _notToBe(plant: AssertionPlant<T>, expected: T) =
    AssertImpl.builder.createDescriptive(NOT_TO_BE, expected) { plant.subject != expected }

fun <T : Any> _isSame(plant: AssertionPlant<T>, expected: T) =
    AssertImpl.builder.createDescriptive(IS_SAME, expected) { plant.subject === expected }

fun <T : Any> _isNotSame(plant: AssertionPlant<T>, expected: T) =
    AssertImpl.builder.createDescriptive(IS_NOT_SAME, expected) { plant.subject !== expected }

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
