package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.assertions.DescriptionCharSequenceAssertion.*
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.translating.ISimpleTranslatable
import ch.tutteli.atrium.reporting.translating.ITranslatable
import ch.tutteli.atrium.reporting.translating.TranslatableRawString

fun <T : CharSequence> _containsNot(plant: IAssertionPlant<T>, expected: Any, vararg otherExpected: Any): IAssertion {
    val assertions = mutableListOf<IAssertion>()
    arrayOf(expected, *otherExpected).forEach {
        assertions.add(LazyThreadUnsafeBasicAssertion {
            val expectedString = it.toString()
            BasicAssertion(CONTAINS_NOT, expectedString, { !plant.subject.contains(expectedString) })
        })
    }
    return InvisibleAssertionGroup(assertions)
}

fun <T : CharSequence> _containsNotDefaultTranslationOf(plant: IAssertionPlant<T>, expected: ITranslatable, vararg otherExpected: ITranslatable): IAssertion
    = _containsNot(plant, expected.getDefault(), *otherExpected.map { it.getDefault() }.toTypedArray())

fun <T : CharSequence> _startsWith(plant: IAssertionPlant<T>, expected: CharSequence): IAssertion
    = BasicAssertion(STARTS_WITH, expected, { plant.subject.startsWith(expected) })

fun <T : CharSequence> _startsNotWith(plant: IAssertionPlant<T>, expected: CharSequence): IAssertion
    = BasicAssertion(STARTS_NOT_WITH, expected, { !plant.subject.startsWith(expected) })

fun <T : CharSequence> _endsWith(plant: IAssertionPlant<T>, expected: CharSequence): IAssertion
    = BasicAssertion(ENDS_WITH, expected, { plant.subject.endsWith(expected) })

fun <T : CharSequence> _endsNotWith(plant: IAssertionPlant<T>, expected: CharSequence): IAssertion
    = BasicAssertion(ENDS_NOT_WITH, expected, { !plant.subject.endsWith(expected) })

fun <T : CharSequence> _isEmpty(plant: IAssertionPlant<T>): IAssertion
    = BasicAssertion(DescriptionBasic.IS, TranslatableRawString(EMPTY), { plant.subject.isEmpty() })

fun <T : CharSequence> _isNotEmpty(plant: IAssertionPlant<T>): IAssertion
    = BasicAssertion(DescriptionBasic.IS_NOT, TranslatableRawString(EMPTY), { plant.subject.isNotEmpty() })

/**
 * Contains the [IBasicAssertion.description]s of the assertion functions which are applicable to [CharSequence].
 */
enum class DescriptionCharSequenceAssertion(override val value: String) : ISimpleTranslatable {
    AT_LEAST("is at least"),
    AT_MOST("is at most"),
    CONTAINS("contains"),
    CONTAINS_NOT("does not contain"),
    EMPTY("empty"),
    ENDS_WITH("ends with"),
    ENDS_NOT_WITH("does not end with"),
    EXACTLY("is exactly"),
    NUMBER_OF_OCCURRENCES("number of occurrences"),
    STARTS_WITH("starts with"),
    STARTS_NOT_WITH("does not start with"),
}
