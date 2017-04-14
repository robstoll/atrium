package ch.tutteli.atrium

import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.RawString

/**
 * Makes the assertion that [IAssertionPlant.subject] contains the [expected] [CharSequence].
 *
 * @return This plant to support a fluent-style API.
 * @throws AssertionError Might throw an [AssertionError] if the made assertion does not hold.
 */
fun <T : CharSequence> IAssertionPlant<T>.contains(expected: CharSequence)
    = createAndAddAssertion("contains", expected, { subject.contains(expected) })

/**
 * Makes the assertion that [IAssertionPlant.subject] contains the [expected] [CharSequence]
 * and [otherExpected] [CharSequence]s (if defined).
 *
 * @return This plant to support a fluent-style API.
 * @throws AssertionError Might throw an [AssertionError] if the made assertion does not hold.
 */
fun <T : CharSequence> IAssertionPlant<T>.contains(expected: CharSequence, vararg otherExpected: CharSequence): IAssertionPlant<T> {
    val plant = contains(expected)
    otherExpected.forEach { contains(it) }
    return plant
}

/**
 * Makes the assertion that [IAssertionPlant.subject] contains [expected]'s [toString] representation.
 *
 * @return This plant to support a fluent-style API.
 * @throws AssertionError Might throw an [AssertionError] if the made assertion does not hold.
 */
fun <T : CharSequence> IAssertionPlant<T>.contains(expected: Any, vararg otherExpected: Any): IAssertionPlant<T> {
    val plant = contains(expected.toString())
    otherExpected.forEach { contains(it.toString()) }
    return plant
}

/**
 * Makes the assertion that [IAssertionPlant.subject] starts with [expected].
 *
 * @return This plant to support a fluent-style API.
 * @throws AssertionError Might throw an [AssertionError] if the made assertion does not hold.
 */
fun <T : CharSequence> IAssertionPlant<T>.startsWith(expected: CharSequence)
    = createAndAddAssertion("starts with", expected, { subject.startsWith(expected) })

/**
 * Makes the assertion that [IAssertionPlant.subject] starts with [expected].
 *
 * @return This plant to support a fluent-style API.
 * @throws AssertionError Might throw an [AssertionError] if the made assertion does not hold.
 */
fun <T : CharSequence> IAssertionPlant<T>.endsWith(expected: CharSequence)
    = createAndAddAssertion("ends with", expected, { subject.endsWith(expected) })

/**
 * Makes the assertion that [IAssertionPlant.subject] [CharSequence.isEmpty].
 *
 * @return This plant to support a fluent-style API.
 * @throws AssertionError Might throw an [AssertionError] if the made assertion does not hold.
 */
fun <T : CharSequence> IAssertionPlant<T>.isEmpty()
    = createAndAddAssertion("is", RawString("empty"), { subject.isEmpty() })
