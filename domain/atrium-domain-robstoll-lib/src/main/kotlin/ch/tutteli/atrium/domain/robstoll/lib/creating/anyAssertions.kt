package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.translations.DescriptionAnyAssertion.*

fun <T : Any> _toBe(plant: AssertionPlant<T>, expected: T)
    = AssertImpl.builder.descriptive
        .withTest({ plant.subject == expected })
        .withDescriptionAndRepresentation(TO_BE, expected)
        .build()

fun <T : Any> _notToBe(plant: AssertionPlant<T>, expected: T)
    = AssertImpl.builder.descriptive
        .withTest({ plant.subject != expected })
        .withDescriptionAndRepresentation(NOT_TO_BE, expected)
        .build()

fun <T : Any> _isSame(plant: AssertionPlant<T>, expected: T)
    = AssertImpl.builder.descriptive
        .withTest({ plant.subject === expected })
        .withDescriptionAndRepresentation(IS_SAME, expected)
        .build()

fun <T : Any> _isNotSame(plant: AssertionPlant<T>, expected: T)
    = AssertImpl.builder.descriptive
        .withTest({ plant.subject !== expected })
        .withDescriptionAndRepresentation(IS_NOT_SAME, expected)
        .build()

fun <T : Any?> _isNull(plant: AssertionPlantNullable<T>)
    = AssertImpl.builder.descriptive
        .withTest({ plant.subject == null })
        .withDescriptionAndRepresentation(TO_BE, RawString.NULL)
        .build()
