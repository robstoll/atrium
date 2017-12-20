package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.assertions.DescriptionCharSequenceAssertion.*
import ch.tutteli.atrium.assertions.charsequence.contains.ICharSequenceContains
import ch.tutteli.atrium.assertions.charsequence.contains.ICharSequenceContains.ISearcher
import ch.tutteli.atrium.assertions.charsequence.contains.builders.CharSequenceContainsBuilder
import ch.tutteli.atrium.assertions.charsequence.contains.builders.CharSequenceContainsCheckerBuilder
import ch.tutteli.atrium.assertions.charsequence.contains.creators.CharSequenceContainsAssertionCreator
import ch.tutteli.atrium.assertions.charsequence.contains.searchbehaviours.CharSequenceContainsIgnoringCaseSearchBehaviour
import ch.tutteli.atrium.assertions.charsequence.contains.searchbehaviours.CharSequenceContainsNoOpSearchBehaviour
import ch.tutteli.atrium.assertions.charsequence.contains.searchers.CharSequenceContainsIgnoringCaseIndexSearcher
import ch.tutteli.atrium.assertions.charsequence.contains.searchers.CharSequenceContainsIgnoringCaseRegexSearcher
import ch.tutteli.atrium.assertions.charsequence.contains.searchers.CharSequenceContainsIndexSearcher
import ch.tutteli.atrium.assertions.charsequence.contains.searchers.CharSequenceContainsRegexSearcher
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.translating.ITranslatable
import ch.tutteli.atrium.reporting.translating.TranslatableRawString

fun <T : CharSequence> _containsBuilder(plant: IAssertionPlant<T>)
    = CharSequenceContainsBuilder(plant, CharSequenceContainsNoOpSearchBehaviour)

fun <T : CharSequence> _containsNot(plant: IAssertionPlant<T>, expected: Any, otherExpected: Array<out Any>): IAssertion {
    val assertions = mutableListOf<IAssertion>()
    arrayOf(expected, *otherExpected).forEach {
        assertions.add(LazyThreadUnsafeBasicAssertion {
            val expectedString = it.toString()
            BasicAssertion(CONTAINS_NOT, expectedString, { !plant.subject.contains(expectedString) })
        })
    }
    return InvisibleAssertionGroup(assertions)
}

fun <T : CharSequence> _containsNotDefaultTranslationOf(plant: IAssertionPlant<T>, expected: ITranslatable, otherExpected: Array<out ITranslatable>): IAssertion
    = _containsNot(plant, expected.getDefault(), mapDefaultTranslations(otherExpected))

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
    checker: CharSequenceContainsCheckerBuilder<T, CharSequenceContainsNoOpSearchBehaviour>,
    expected: Any,
    otherExpected: Array<out Any>
): IAssertionGroup
    = checkOnlyAllowedTypeAndCreateAssertionGroup(checker, CharSequenceContainsIndexSearcher(), expected, otherExpected)

fun <T : CharSequence> _containsValuesIgnoringCase(
    checker: CharSequenceContainsCheckerBuilder<T, CharSequenceContainsIgnoringCaseSearchBehaviour>,
    expected: Any,
    otherExpected: Array<out Any>
): IAssertionGroup
    = checkOnlyAllowedTypeAndCreateAssertionGroup(checker, CharSequenceContainsIgnoringCaseIndexSearcher(), expected, otherExpected)

private fun <T : CharSequence, S : ICharSequenceContains.ISearchBehaviour> checkOnlyAllowedTypeAndCreateAssertionGroup(
    checker: CharSequenceContainsCheckerBuilder<T, S>,
    searcher: ISearcher<S>,
    expected: Any,
    otherExpected: Array<out Any>
): IAssertionGroup {
    listOf(expected, *otherExpected).forEach {
        require(it is CharSequence || it is Number || it is Char) {
            "Only CharSequence, Number and Char are allowed, `$it` given.\nWe provide an API with Any for convenience (so that you can mix String and Int for instance)."
        }
    }
    return createAssertionGroup(checker, searcher, expected, otherExpected)
}

fun <T : CharSequence> _containsDefaultTranslationOf(
    checker: CharSequenceContainsCheckerBuilder<T, CharSequenceContainsNoOpSearchBehaviour>,
    expected: ITranslatable,
    otherExpected: Array<out ITranslatable>
): IAssertionGroup
    = _containsValues(checker, expected.getDefault(), mapDefaultTranslations(otherExpected))

fun <T : CharSequence> _containsDefaultTranslationOfIgnoringCase(
    checker: CharSequenceContainsCheckerBuilder<T, CharSequenceContainsIgnoringCaseSearchBehaviour>,
    expected: ITranslatable,
    otherExpected: Array<out ITranslatable>
): IAssertionGroup
    = _containsValuesIgnoringCase(checker, expected.getDefault(), mapDefaultTranslations(otherExpected))

private fun mapDefaultTranslations(otherExpected: Array<out ITranslatable>) =
    otherExpected.map { it.getDefault() }.toTypedArray()


fun <T : CharSequence> _containsRegex(
    checker: CharSequenceContainsCheckerBuilder<T, CharSequenceContainsNoOpSearchBehaviour>,
    expected: String,
    otherExpected: Array<out String>
): IAssertionGroup
    = createAssertionGroup(checker, CharSequenceContainsRegexSearcher(), expected, otherExpected)

fun <T : CharSequence> _containsRegexIgnoringCase(
    checker: CharSequenceContainsCheckerBuilder<T, CharSequenceContainsIgnoringCaseSearchBehaviour>,
    expected: String,
    otherExpected: Array<out String>
): IAssertionGroup
    = createAssertionGroup(checker, CharSequenceContainsIgnoringCaseRegexSearcher(), expected, otherExpected)


private fun <T : CharSequence, S : ICharSequenceContains.ISearchBehaviour> createAssertionGroup(
    checker: CharSequenceContainsCheckerBuilder<T, S>,
    searcher: ISearcher<S>,
    expected: Any,
    otherExpected: Array<out Any>
): IAssertionGroup {
    return CharSequenceContainsAssertionCreator<T, S>(checker.containsBuilder.searchBehaviour, searcher, checker.checkers)
        .createAssertionGroup(checker.containsBuilder.plant, expected, otherExpected)
}
