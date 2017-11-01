package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.assertions.DescriptionAnyAssertion.*
import ch.tutteli.atrium.creating.IAssertionPlant

fun <T : Any> _toBe(plant: IAssertionPlant<T>, expected: T): IAssertion
    = BasicAssertion(TO_BE, expected, { plant.subject == expected })

fun <T : Any> _notToBe(plant: IAssertionPlant<T>, expected: T): IAssertion
    = BasicAssertion(NOT_TO_BE, expected, { plant.subject != expected })

fun <T : Any> _isSame(plant: IAssertionPlant<T>, expected: T): IAssertion
    = BasicAssertion(IS_SAME, expected, { plant.subject === expected })

fun <T : Any> _isNotSame(plant: IAssertionPlant<T>, expected: T): IAssertion
    = BasicAssertion(IS_NOT_SAME, expected, { plant.subject !== expected })
