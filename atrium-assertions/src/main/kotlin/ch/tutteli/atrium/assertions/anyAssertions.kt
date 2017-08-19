package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.assertions.DescriptionAnyAssertion.*
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.translating.ISimpleTranslatable

fun <T : Any> _toBe(plant: IAssertionPlant<T>, expected: T): IAssertion
    = BasicAssertion(TO_BE, expected, { plant.subject == expected })

fun <T : Any> _notToBe(plant: IAssertionPlant<T>, expected: T): IAssertion
    = BasicAssertion(NOT_TO_BE, expected, { plant.subject != expected })

fun <T : Any> _isSame(plant: IAssertionPlant<T>, expected: T): IAssertion
    = BasicAssertion(IS_SAME, expected, { plant.subject === expected })

fun <T : Any> _isNotSame(plant: IAssertionPlant<T>, expected: T): IAssertion
    = BasicAssertion(IS_NOT_SAME, expected, { plant.subject !== expected })

/**
 * Contains the [IBasicAssertion.description]s of the assertion functions which are applicable to [Any].
 */
enum class DescriptionAnyAssertion(override val value: String) : ISimpleTranslatable {
    TO_BE("to be"),
    NOT_TO_BE("not to be"),
    IS_SAME("is the same as"),
    IS_NOT_SAME("is not the same as"),
}
