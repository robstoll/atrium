package ch.tutteli.atrium

import ch.tutteli.atrium.creating.IAssertionPlant

/**
 * Makes the assertion that [IAssertionPlant.subject] is smaller than [expected].
 *
 * @return This plant to support a fluent-style API.
 */
fun IAssertionPlant<Int>.isSmallerThan(expected: Int)
    = createAndAddAssertion("is smaller than", expected, { subject < expected })

/**
 * Makes the assertion that [IAssertionPlant.subject] is smaller than or equals [expected].
 *
 * @return This plant to support a fluent-style API.
 * @throws AssertionError Might throw an [AssertionError] if the made assertion does not hold.
 */
fun IAssertionPlant<Int>.isSmallerOrEquals(expected: Int)
    = createAndAddAssertion("is smaller or equals", expected, { subject <= expected })

/**
 * Makes the assertion that [IAssertionPlant.subject] is greater than [expected].
 *
 * @return This plant to support a fluent-style API.
 * @throws AssertionError Might throw an [AssertionError] if the made assertion does not hold.
 */
fun IAssertionPlant<Int>.isGreaterThan(expected: Int)
    = createAndAddAssertion("is greater than", expected, { subject > expected })

/**
 * Makes the assertion that [IAssertionPlant.subject] is greater than or equals [expected].
 *
 * @return This plant to support a fluent-style API.
 * @throws AssertionError Might throw an [AssertionError] if the made assertion does not hold.
 */
fun IAssertionPlant<Int>.isGreaterOrEquals(expected: Int)
    = createAndAddAssertion("is greater or equals", expected, { subject >= expected })
