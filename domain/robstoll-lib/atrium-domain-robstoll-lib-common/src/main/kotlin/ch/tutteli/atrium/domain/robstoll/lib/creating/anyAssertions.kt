package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.api.cc.en_GB.toBe
import ch.tutteli.atrium.api.fluent.en_GB.toBe
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.polyfills.cast
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.robstoll.lib.creating.any.typetransformation.creators._downCast
import ch.tutteli.atrium.domain.robstoll.lib.creating.any.typetransformation.failurehandlers.ExplanatoryFailureHandler
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.translations.DescriptionAnyAssertion.*
import ch.tutteli.atrium.translations.DescriptionTypeTransformationAssertion
import kotlin.reflect.KClass

fun <T> _toBe(subjectProvider: SubjectProvider<T>, expected: T) =
    ExpectImpl.builder.createDescriptive(subjectProvider, TO_BE, expected) { it == expected }

fun <T> _notToBe(subjectProvider: SubjectProvider<T>, expected: T) =
    ExpectImpl.builder.createDescriptive(subjectProvider, NOT_TO_BE, expected) { it != expected }

fun <T> _isSame(subjectProvider: SubjectProvider<T>, expected: T) =
    ExpectImpl.builder.createDescriptive(subjectProvider, IS_SAME, expected) { it === expected }

fun <T> _isNotSame(subjectProvider: SubjectProvider<T>, expected: T) =
    ExpectImpl.builder.createDescriptive(subjectProvider, IS_NOT_SAME, expected) { it !== expected }


fun <T : Any?> _toBeNull(subjectProvider: SubjectProvider<T>) =
    ExpectImpl.builder.createDescriptive(subjectProvider, TO_BE, RawString.NULL) { it == null }

fun <T : Any> _notToBeNull(
    assertionContainer: Expect<T?>,
    type: KClass<T>,
    assertionCreator: Expect<T>.() -> Unit
): Assertion = ExpectImpl.collector.collect(assertionContainer) {
    ExpectImpl.changeSubject.reportBuilder(this)
        .withDescriptionAndRepresentation(DescriptionTypeTransformationAssertion.IS_A, type)
        .withCheck { type.isInstance(it) }
        .withTransformation { type.cast(it) }
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

fun <T : Any> _toBeNullIfNullGivenElse(
    assertionContainer: Expect<T?>,
    type: KClass<T>,
    assertionCreatorOrNull: (Expect<T>.() -> Unit)?
): Assertion =
    if (assertionCreatorOrNull == null) ExpectImpl.any.toBeNull(assertionContainer)
    else ExpectImpl.any.notToBeNull(assertionContainer, type, assertionCreatorOrNull)


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
