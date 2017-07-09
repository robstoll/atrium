package ch.tutteli.atrium

import ch.tutteli.atrium.DescriptionCharSequenceAssertion.*
import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.builders.CharSequenceContainsBuilder
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.translating.ISimpleTranslatable
import ch.tutteli.atrium.reporting.translating.ITranslatable
import ch.tutteli.atrium.reporting.translating.TranslatableRawString

/**
 * Creates an [CharSequenceContainsBuilder] based on this [IAssertionPlant] which allows to define
 * more sophisticated `contains` assertions.
 *
 * @return The newly created builder
 */
val <T : CharSequence> IAssertionPlant<T>.contains get() = CharSequenceContainsBuilder(this)

/**
 * Makes the assertion that [IAssertionPlant.subject] contains [expected]'s [toString] representation
 * and the [toString] representation of the [otherExpected] (if defined).
 *
 * @return This plant to support a fluent-style API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct
 */
fun <T : CharSequence> IAssertionPlant<T>.contains(expected: Any, vararg otherExpected: Any): IAssertionPlant<T> {
    val assertions = mutableListOf<IAssertion>()
    arrayOf(expected, *otherExpected).forEach {
        assertions.add(LazyThreadUnsafeBasicAssertion {
            val expectedString =  it.toString()
            BasicAssertion(CONTAINS, expectedString, { subject.contains(expectedString) })
        })
    }
    addAssertion(InvisibleAssertionGroup(assertions))
    return this
}

/**
 * Makes the assertion that [IAssertionPlant.subject] does not contain [expected]'s [toString] representation
 * and neither one of the [otherExpected]'s [toString] representation (if defined).
 *
 * @return This plant to support a fluent-style API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct
 */
fun <T : CharSequence> IAssertionPlant<T>.containsNot(expected: Any, vararg otherExpected: Any): IAssertionPlant<T> {
    val assertions = mutableListOf<IAssertion>()
    arrayOf(expected, *otherExpected).forEach {
        assertions.add(LazyThreadUnsafeBasicAssertion {
            val expectedString =  it.toString()
            BasicAssertion(CONTAINS_NOT, expectedString, { !subject.contains(expectedString) })
        })
    }
    addAssertion(InvisibleAssertionGroup(assertions))
    return this
}

/**
 * Makes the assertion that [IAssertionPlant.subject] contains [expected]'s [getDefault][ITranslatable.getDefault]
 * representation and the [getDefault][ITranslatable.getDefault] representations of the [otherExpected] (if defined).
 *
 * @return This plant to support a fluent-style API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct
 */
fun <T : CharSequence> IAssertionPlant<T>.containsDefaultTranslationOf(expected: ITranslatable, vararg otherExpected: ITranslatable): IAssertionPlant<T> {
    val plant = contains(expected.getDefault())
    otherExpected.forEach { contains(it.getDefault()) }
    return plant
}

/**
 * Makes the assertion that [IAssertionPlant.subject] does  not contain [expected]'s
 * [getDefault][ITranslatable.getDefault] representation and neither one of the [otherExpected]'s
 * [getDefault][ITranslatable.getDefault] representation (if defined).
 *
 * @return This plant to support a fluent-style API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct
 */
fun <T : CharSequence> IAssertionPlant<T>.containsNotDefaultTranslationOf(expected: ITranslatable, vararg otherExpected: ITranslatable): IAssertionPlant<T> {
    val plant = containsNot(expected.getDefault())
    otherExpected.forEach { containsNot(it.getDefault()) }
    return plant
}

/**
 * Makes the assertion that [IAssertionPlant.subject] starts with [expected].
 *
 * @return This plant to support a fluent-style API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct
 */
fun <T : CharSequence> IAssertionPlant<T>.startsWith(expected: CharSequence)
    = createAndAddAssertion(STARTS_WITH, expected, { subject.startsWith(expected) })

/**
 * Makes the assertion that [IAssertionPlant.subject] does not start with [expected].
 *
 * @return This plant to support a fluent-style API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct
 */
fun <T : CharSequence> IAssertionPlant<T>.startsNotWith(expected: CharSequence)
    = createAndAddAssertion(STARTS_NOT_WITH, expected, { !subject.startsWith(expected) })


/**
 * Makes the assertion that [IAssertionPlant.subject] ends with [expected].
 *
 * @return This plant to support a fluent-style API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct
 */
fun <T : CharSequence> IAssertionPlant<T>.endsWith(expected: CharSequence)
    = createAndAddAssertion(ENDS_WITH, expected, { subject.endsWith(expected) })

/**
 * Makes the assertion that [IAssertionPlant.subject] does not end with [expected].
 *
 * @return This plant to support a fluent-style API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct
 */
fun <T : CharSequence> IAssertionPlant<T>.endsNotWith(expected: CharSequence)
    = createAndAddAssertion(ENDS_NOT_WITH, expected, { !subject.endsWith(expected) })


/**
 * Makes the assertion that [IAssertionPlant.subject] [CharSequence].[kotlin.text.isEmpty].
 *
 * @return This plant to support a fluent-style API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct
 */
fun <T : CharSequence> IAssertionPlant<T>.isEmpty()
    = createAndAddAssertion(DescriptionBasic.IS, TranslatableRawString(EMPTY), { subject.isEmpty() })

/**
 * Makes the assertion that [IAssertionPlant.subject] [CharSequence].[kotlin.text.isNotEmpty].
 *
 * @return This plant to support a fluent-style API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct
 */
fun <T : CharSequence> IAssertionPlant<T>.isNotEmpty()
    = createAndAddAssertion(DescriptionBasic.IS_NOT, TranslatableRawString(EMPTY), { subject.isNotEmpty() })

/**
 * Contains the [IBasicAssertion.description]s of the assertion functions which are applicable to [CharSequence].
 */
enum class DescriptionCharSequenceAssertion(override val value: String) : ISimpleTranslatable {
    CONTAINS("contains"),
    CONTAINS_NOT("does not contain"),
    EXACTLY_TIME("exactly %d time"),
    EXACTLY_TIMES("exactly %d times"),
    STARTS_WITH("starts with"),
    STARTS_NOT_WITH("does not start with"),
    ENDS_WITH("ends with"),
    ENDS_NOT_WITH("does not end with"),
    EMPTY("empty")
}
