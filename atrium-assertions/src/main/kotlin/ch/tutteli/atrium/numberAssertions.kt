package ch.tutteli.atrium

import ch.tutteli.atrium.DescriptionNumberAssertion.*
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.translating.IEnTranslatable

/**
 * Makes the assertion that [IAssertionPlant.subject] is less than [expected].
 *
 * @return This plant to support a fluent-style API.
 */
fun IAssertionPlant<Int>.isLessThan(expected: Int)
    = createAndAddAssertion(IS_LESS_THAN, expected, { subject < expected })

/**
 * Makes the assertion that [IAssertionPlant.subject] is less than or equals [expected].
 *
 * @return This plant to support a fluent-style API.
 * @throws AssertionError Might throw an [AssertionError] if the made assertion does not hold.
 */
fun IAssertionPlant<Int>.isLessOrEquals(expected: Int)
    = createAndAddAssertion(IS_LESS_OR_EQUALS, expected, { subject <= expected })

/**
 * Makes the assertion that [IAssertionPlant.subject] is greater than [expected].
 *
 * @return This plant to support a fluent-style API.
 * @throws AssertionError Might throw an [AssertionError] if the made assertion does not hold.
 */
fun IAssertionPlant<Int>.isGreaterThan(expected: Int)
    = createAndAddAssertion(IS_GREATER_THAN, expected, { subject > expected })

/**
 * Makes the assertion that [IAssertionPlant.subject] is greater than or equals [expected].
 *
 * @return This plant to support a fluent-style API.
 * @throws AssertionError Might throw an [AssertionError] if the made assertion does not hold.
 */
fun IAssertionPlant<Int>.isGreaterOrEquals(expected: Int)
    = createAndAddAssertion(IS_GREATER_OR_EQUALS, expected, { subject >= expected })

enum class DescriptionNumberAssertion(override val value: String) : IEnTranslatable {
    IS_LESS_THAN("is less than"),
    IS_LESS_OR_EQUALS("is less or equals"),
    IS_GREATER_THAN("is greater than"),
    IS_GREATER_OR_EQUALS("is greater or equals"),
    ;
}
