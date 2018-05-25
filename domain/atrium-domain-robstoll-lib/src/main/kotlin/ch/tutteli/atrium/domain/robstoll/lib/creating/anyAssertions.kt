package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.translations.DescriptionAnyAssertion.*

fun <T : Any> _toBe(plant: AssertionPlant<T>, expected: T)
    = AssertImpl.builder.descriptive.withTest({ plant.subject == expected }).create(TO_BE, expected)

fun <T : Any> _notToBe(plant: AssertionPlant<T>, expected: T)
    = AssertImpl.builder.descriptive.withTest({ plant.subject != expected }).create(NOT_TO_BE, expected)

fun <T : Any> _isSame(plant: AssertionPlant<T>, expected: T)
    = AssertImpl.builder.descriptive.withTest({ plant.subject === expected }).create(IS_SAME, expected)

fun <T : Any> _isNotSame(plant: AssertionPlant<T>, expected: T)
    = AssertImpl.builder.descriptive.withTest({ plant.subject !== expected }).create(IS_NOT_SAME, expected)

fun <T : Any?> _isNull(plant: AssertionPlantNullable<T>)
    = AssertImpl.builder.descriptive.withTest({ plant.subject == null }).create(TO_BE, RawString.NULL)
