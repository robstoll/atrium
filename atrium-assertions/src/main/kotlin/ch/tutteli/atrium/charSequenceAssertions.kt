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
 * Makes the assertion that [IAssertionPlant.subject] does not contain the [expected] [CharSequence].
 *
 * @return This plant to support a fluent-style API.
 * @throws AssertionError Might throw an [AssertionError] if the made assertion does not hold.
 */
fun <T : CharSequence> IAssertionPlant<T>.containsNot(expected: CharSequence)
    = createAndAddAssertion("does not contain", expected, { !subject.contains(expected) })


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
 * Makes the assertion that [IAssertionPlant.subject] does not contain the [expected] [CharSequence]
 * and neither one of the [otherExpected] [CharSequence]s (if defined).
 *
 * @return This plant to support a fluent-style API.
 * @throws AssertionError Might throw an [AssertionError] if the made assertion does not hold.
 */
fun <T : CharSequence> IAssertionPlant<T>.containsNot(expected: CharSequence, vararg otherExpected: CharSequence): IAssertionPlant<T> {
    val plant = containsNot(expected)
    otherExpected.forEach { containsNot(it) }
    return plant
}


/**
 * Makes the assertion that [IAssertionPlant.subject] contains [expected]'s [toString] representation
 * and the [toString] representation of the [otherExpected] (if defined).
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
 * Makes the assertion that [IAssertionPlant.subject] does not contain [expected]'s [toString] representation
 * and neither one of the [otherExpected]'s [toString] representation (if defined).
 *
 * @return This plant to support a fluent-style API.
 * @throws AssertionError Might throw an [AssertionError] if the made assertion does not hold.
 */
fun <T : CharSequence> IAssertionPlant<T>.containsNot(expected: Any, vararg otherExpected: Any): IAssertionPlant<T> {
    val plant = containsNot(expected.toString())
    otherExpected.forEach { containsNot(it.toString()) }
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
 * Makes the assertion that [IAssertionPlant.subject] [CharSequence].[kotlin.text.isEmpty].
 *
 * @return This plant to support a fluent-style API.
 * @throws AssertionError Might throw an [AssertionError] if the made assertion does not hold.
 */
fun <T : CharSequence> IAssertionPlant<T>.isEmpty()
    = createAndAddAssertion("is", RawString("empty"), { subject.isEmpty() })

/**
 * Makes the assertion that [IAssertionPlant.subject] [CharSequence].[kotlin.text.isNotEmpty].
 *
 * @return This plant to support a fluent-style API.
 * @throws AssertionError Might throw an [AssertionError] if the made assertion does not hold.
 */
fun <T : CharSequence> IAssertionPlant<T>.isNotEmpty()
    = createAndAddAssertion("is not", RawString("empty"), { subject.isNotEmpty() })
