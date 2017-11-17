package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.assertions.DescriptionCharSequenceAssertion.*
import ch.tutteli.atrium.assertions.charsequence.contains.builders.CharSequenceContainsBuilder
import ch.tutteli.atrium.assertions.charsequence.contains.builders.CharSequenceContainsCheckerBuilder
import ch.tutteli.atrium.assertions.charsequence.contains.creators.CharSequenceContainsAssertionCreator
import ch.tutteli.atrium.assertions.charsequence.contains.creators.CharSequenceContainsAssertionCreator.ISearcher
import ch.tutteli.atrium.assertions.charsequence.contains.decorators.CharSequenceContainsIgnoringCaseDecorator
import ch.tutteli.atrium.assertions.charsequence.contains.decorators.CharSequenceContainsNoOpDecorator
import ch.tutteli.atrium.assertions.charsequence.contains.searchers.CharSequenceContainsIgnoringCaseIndexSearcher
import ch.tutteli.atrium.assertions.charsequence.contains.searchers.CharSequenceContainsIgnoringCaseRegexSearcher
import ch.tutteli.atrium.assertions.charsequence.contains.searchers.CharSequenceContainsIndexSearcher
import ch.tutteli.atrium.assertions.charsequence.contains.searchers.CharSequenceContainsRegexSearcher
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.translating.ITranslatable
import ch.tutteli.atrium.reporting.translating.TranslatableRawString

fun <T : CharSequence> _containsBuilder(plant: IAssertionPlant<T>)
    = CharSequenceContainsBuilder(plant, CharSequenceContainsNoOpDecorator)

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


fun <T : CharSequence> _containsValues(
    checker: CharSequenceContainsCheckerBuilder<T, CharSequenceContainsNoOpDecorator>,
    expected: Any,
    otherExpected: Array<out Any>
): IAssertionGroup
    = createAssertionGroup(checker, CharSequenceContainsIndexSearcher(), expected, otherExpected)

fun <T : CharSequence> _containsValuesIgnoringCase(
    checker: CharSequenceContainsCheckerBuilder<T, CharSequenceContainsIgnoringCaseDecorator>,
    expected: Any,
    otherExpected: Array<out Any>
): IAssertionGroup
    = createAssertionGroup(checker, CharSequenceContainsIgnoringCaseIndexSearcher(), expected, otherExpected)

fun <T : CharSequence> _containsRegex(
    checker: CharSequenceContainsCheckerBuilder<T, CharSequenceContainsNoOpDecorator>,
    expected: Any,
    otherExpected: Array<out Any>
): IAssertionGroup
    = createAssertionGroup(checker, CharSequenceContainsRegexSearcher(), expected, otherExpected)

fun <T : CharSequence> _containsRegexIgnoringCase(
    checker: CharSequenceContainsCheckerBuilder<T, CharSequenceContainsIgnoringCaseDecorator>,
    expected: Any,
    otherExpected: Array<out Any>
): IAssertionGroup
    = createAssertionGroup(checker, CharSequenceContainsIgnoringCaseRegexSearcher(), expected, otherExpected)

private fun <T : CharSequence, D : CharSequenceContainsAssertionCreator.IDecorator> createAssertionGroup(
    checker: CharSequenceContainsCheckerBuilder<T, D>,
    searcher: ISearcher<D>,
    expected: Any,
    otherExpected: Array<out Any>
): IAssertionGroup {
    return CharSequenceContainsAssertionCreator<T, D>(checker.containsBuilder.decorator, searcher, checker.checkers)
        .create(checker.containsBuilder.plant, expected, *otherExpected)
}
