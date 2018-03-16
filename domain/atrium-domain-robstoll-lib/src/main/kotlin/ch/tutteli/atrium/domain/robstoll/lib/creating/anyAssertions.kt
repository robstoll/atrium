package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.assertions.builders.AssertionBuilder
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.translations.DescriptionAnyAssertion.*

fun <T : Any> _toBe(plant: AssertionPlant<T>, expected: T)
    = AssertionBuilder.descriptive.create(TO_BE, expected, { plant.subject == expected })

fun <T : Any> _notToBe(plant: AssertionPlant<T>, expected: T)
    = AssertionBuilder.descriptive.create(NOT_TO_BE, expected, { plant.subject != expected })

fun <T : Any> _isSame(plant: AssertionPlant<T>, expected: T)
    = AssertionBuilder.descriptive.create(IS_SAME, expected, { plant.subject === expected })

fun <T : Any> _isNotSame(plant: AssertionPlant<T>, expected: T)
    = AssertionBuilder.descriptive.create(IS_NOT_SAME, expected, { plant.subject !== expected })

fun <T : Any?> _isNull(plant: AssertionPlantNullable<T>)
    = AssertionBuilder.descriptive.create(TO_BE, RawString.NULL, { plant.subject == null })
