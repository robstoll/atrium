package ch.tutteli.atrium

import ch.tutteli.atrium.DescriptionNumberAssertion.*
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.ISimpleTranslatable

/**
 * Makes the assertion that [IAssertionPlant.subject] is smaller than [expected].
 *
 * @return This plant to support a fluent-style API.
 */
fun IAssertionPlant<Int>.isSmallerThan(expected: Int)
    = createAndAddAssertion(IS_SMALLER_THAN, expected, { subject < expected })

/**
 * Makes the assertion that [IAssertionPlant.subject] is smaller than or equals [expected].
 *
 * @return This plant to support a fluent-style API.
 * @throws AssertionError Might throw an [AssertionError] if the made assertion does not hold.
 */
fun IAssertionPlant<Int>.isSmallerOrEquals(expected: Int)
    = createAndAddAssertion(IS_SMALLER_OR_EQUALS, expected, { subject <= expected })

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

enum class DescriptionNumberAssertion(override val value: String) : ISimpleTranslatable {
    IS_SMALLER_THAN("is smaller than"),
    IS_SMALLER_OR_EQUALS("is smaller or equals"),
    IS_GREATER_THAN("is greater than"),
    IS_GREATER_OR_EQUALS("is greater or equals"),
    ;
}
