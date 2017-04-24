package ch.tutteli.atrium

import ch.tutteli.atrium.DescriptionCharSequenceAssertion.*
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.translating.ISimpleTranslatable
import ch.tutteli.atrium.reporting.translating.ITranslatable
import ch.tutteli.atrium.reporting.RawString

/**
 * Makes the assertion that [IAssertionPlant.subject] contains the [expected] [CharSequence].
 *
 * @return This plant to support a fluent-style API.
 * @throws AssertionError Might throw an [AssertionError] if the made assertion does not hold.
 */
fun <T : CharSequence> IAssertionPlant<T>.contains(expected: CharSequence)
    = createAndAddAssertion(CONTAINS, expected, { subject.contains(expected) })

/**
 * Makes the assertion that [IAssertionPlant.subject] does not contain the [expected] [CharSequence].
 *
 * @return This plant to support a fluent-style API.
 * @throws AssertionError Might throw an [AssertionError] if the made assertion does not hold.
 */
fun <T : CharSequence> IAssertionPlant<T>.containsNot(expected: CharSequence)
    = createAndAddAssertion(CONTAINS_NOT, expected, { !subject.contains(expected) })


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
 * Makes the assertion that [IAssertionPlant.subject] contains [expected]'s [getDefault][ITranslatable.getDefault] representation
 * and the [getDefault][ITranslatable.getDefault] representation of the [otherExpected] (if defined).
 *
 * @return This plant to support a fluent-style API.
 * @throws AssertionError Might throw an [AssertionError] if the made assertion does not hold.
 */
fun <T : CharSequence> IAssertionPlant<T>.contains(expected: ITranslatable, vararg otherExpected: ITranslatable): IAssertionPlant<T> {
    val plant = contains(expected.getDefault())
    otherExpected.forEach { contains(it.getDefault()) }
    return plant
}

/**
 * Makes the assertion that [IAssertionPlant.subject] contains [expected]'s [getDefault][ITranslatable.getDefault] representation
 * and the [getDefault][ITranslatable.getDefault] representation of the [otherExpected] (if defined).
 *
 * @return This plant to support a fluent-style API.
 * @throws AssertionError Might throw an [AssertionError] if the made assertion does not hold.
 */
fun <T : CharSequence> IAssertionPlant<T>.containsNot(expected: ITranslatable, vararg otherExpected: ITranslatable): IAssertionPlant<T> {
    val plant = containsNot(expected.getDefault())
    otherExpected.forEach { containsNot(it.getDefault()) }
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
    = createAndAddAssertion(STARTS_WITH, expected, { subject.startsWith(expected) })

/**
 * Makes the assertion that [IAssertionPlant.subject] does not start with [expected].
 *
 * @return This plant to support a fluent-style API.
 * @throws AssertionError Might throw an [AssertionError] if the made assertion does not hold.
 */
fun <T : CharSequence> IAssertionPlant<T>.startsNotWith(expected: CharSequence)
    = createAndAddAssertion(STARTS_NOT_WITH, expected, { !subject.startsWith(expected) })


/**
 * Makes the assertion that [IAssertionPlant.subject] ends with [expected].
 *
 * @return This plant to support a fluent-style API.
 * @throws AssertionError Might throw an [AssertionError] if the made assertion does not hold.
 */
fun <T : CharSequence> IAssertionPlant<T>.endsWith(expected: CharSequence)
    = createAndAddAssertion(ENDS_WITH, expected, { subject.endsWith(expected) })

/**
 * Makes the assertion that [IAssertionPlant.subject] does not end with [expected].
 *
 * @return This plant to support a fluent-style API.
 * @throws AssertionError Might throw an [AssertionError] if the made assertion does not hold.
 */
fun <T : CharSequence> IAssertionPlant<T>.endsNotWith(expected: CharSequence)
    = createAndAddAssertion(ENDS_NOT_WITH, expected, { !subject.endsWith(expected) })


/**
 * Makes the assertion that [IAssertionPlant.subject] [CharSequence].[kotlin.text.isEmpty].
 *
 * @return This plant to support a fluent-style API.
 * @throws AssertionError Might throw an [AssertionError] if the made assertion does not hold.
 */
fun <T : CharSequence> IAssertionPlant<T>.isEmpty()
    = createAndAddAssertion(IS_EMPTY, RawString("empty"), { subject.isEmpty() })

/**
 * Makes the assertion that [IAssertionPlant.subject] [CharSequence].[kotlin.text.isNotEmpty].
 *
 * @return This plant to support a fluent-style API.
 * @throws AssertionError Might throw an [AssertionError] if the made assertion does not hold.
 */
fun <T : CharSequence> IAssertionPlant<T>.isNotEmpty()
    = createAndAddAssertion(IS_NOT_EMPTY, RawString("empty"), { subject.isNotEmpty() })

enum class DescriptionCharSequenceAssertion(override val value: String) : ISimpleTranslatable {
    CONTAINS("contains"),
    CONTAINS_NOT("does not contain"),
    STARTS_WITH("starts with"),
    STARTS_NOT_WITH("does not start with"),
    ENDS_WITH("ends with"),
    ENDS_NOT_WITH("does not end with"),
    IS_EMPTY("is"),
    IS_NOT_EMPTY("is not"),
    ;
}
