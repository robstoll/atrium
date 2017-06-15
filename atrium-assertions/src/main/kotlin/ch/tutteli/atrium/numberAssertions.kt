package ch.tutteli.atrium

import ch.tutteli.atrium.DescriptionNumberAssertion.*
import ch.tutteli.atrium.assertions.Message
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.translating.ISimpleTranslatable

/**
 * Makes the assertion that [IAssertionPlant.subject] is less than [expected].
 *
 * @return This plant to support a fluent-style API.
 */
fun <T> IAssertionPlant<T>.isLessThan(expected: T) where T: Number, T: Comparable<T>
    = createAndAddAssertion(IS_LESS_THAN, expected, { subject < expected })

/**
 * Makes the assertion that [IAssertionPlant.subject] is less than or equals [expected].
 *
 * @return This plant to support a fluent-style API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct
 */
fun <T> IAssertionPlant<T>.isLessOrEquals(expected: T) where T: kotlin.Number, T: kotlin.Comparable<T>
    = createAndAddAssertion(IS_LESS_OR_EQUALS, expected, { subject <= expected })

/**
 * Makes the assertion that [IAssertionPlant.subject] is greater than [expected].
 *
 * @return This plant to support a fluent-style API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct
 */
fun <T> IAssertionPlant<T>.isGreaterThan(expected: T) where T: kotlin.Number, T: kotlin.Comparable<T>
    = createAndAddAssertion(IS_GREATER_THAN, expected, { subject > expected })

/**
 * Makes the assertion that [IAssertionPlant.subject] is greater than or equals [expected].
 *
 * @return This plant to support a fluent-style API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct
 */
fun <T> IAssertionPlant<T>.isGreaterOrEquals(expected: T) where T: kotlin.Number, T: kotlin.Comparable<T>
    = createAndAddAssertion(IS_GREATER_OR_EQUALS, expected, { subject >= expected })

/**
 * Contains the [Message.description]s of the assertion functions which are applicable to [Number].
 */
enum class DescriptionNumberAssertion(override val value: String) : ISimpleTranslatable {
    IS_LESS_THAN("is less than"),
    IS_LESS_OR_EQUALS("is less or equals"),
    IS_GREATER_THAN("is greater than"),
    IS_GREATER_OR_EQUALS("is greater or equals"),
}
