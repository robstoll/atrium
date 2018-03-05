@file:Suppress("OVERRIDE_BY_INLINE", "NOTHING_TO_INLINE")
package ch.tutteli.atrium.creating

import ch.tutteli.atrium.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.creating.charsequence.contains.creators.CharSequenceContainsAssertions
import ch.tutteli.atrium.creating.charsequence.contains.creators.ICharSequenceContainsAssertions
import ch.tutteli.atrium.creating.charsequence.contains.searchbehaviours.ISearchBehaviourFactory
import ch.tutteli.atrium.creating.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour
import ch.tutteli.atrium.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.creating.charsequence.contains.searchbehaviours.SearchBehaviourFactory
import ch.tutteli.atrium.reporting.translating.Translatable

object CharSequenceAssertionsBuilder : ICharSequenceAssertions {

    override inline fun <T : CharSequence> containsBuilder(plant: AssertionPlant<T>)
        = CharSequenceAssertions.containsBuilder(plant)

    override inline fun <T : CharSequence> containsNotBuilder(plant: AssertionPlant<T>)
        = CharSequenceAssertions.containsNotBuilder(plant)

    override inline fun <T : CharSequence> startsWith(plant: AssertionPlant<T>, expected: CharSequence)
        = CharSequenceAssertions.startsWith(plant, expected)

    override inline fun <T : CharSequence> startsNotWith(plant: AssertionPlant<T>, expected: CharSequence)
        = CharSequenceAssertions.startsNotWith(plant, expected)

    override inline fun <T : CharSequence> endsWith(plant: AssertionPlant<T>, expected: CharSequence)
        = CharSequenceAssertions.endsWith(plant, expected)

    override inline fun <T : CharSequence> endsNotWith(plant: AssertionPlant<T>, expected: CharSequence)
        = CharSequenceAssertions.endsNotWith(plant, expected)

    override inline fun <T : CharSequence> isEmpty(plant: AssertionPlant<T>)
        = CharSequenceAssertions.isEmpty(plant)

    override inline fun <T : CharSequence> isNotEmpty(plant: AssertionPlant<T>)
        = CharSequenceAssertions.isNotEmpty(plant)

    /**
     * Delegates to [CharSequenceContainsAssertions].
     */
    inline val contains get() = CharSequenceContainsAssertionsBuilder
}


object CharSequenceContainsAssertionsBuilder: ICharSequenceContainsAssertions {

    override inline fun <T : CharSequence> values(checkerOption: CharSequenceContains.CheckerOption<T, NoOpSearchBehaviour>, expected: Any, otherExpected: Array<out Any>)
        = CharSequenceContainsAssertions.values(checkerOption, expected, otherExpected)

    override inline fun <T : CharSequence> valuesIgnoringCase(checkerOption: CharSequenceContains.CheckerOption<T, IgnoringCaseSearchBehaviour>, expected: Any, otherExpected: Array<out Any>)
        = CharSequenceContainsAssertions.valuesIgnoringCase(checkerOption, expected, otherExpected)

    override inline fun <T : CharSequence> defaultTranslationOf(checkerOption: CharSequenceContains.CheckerOption<T, NoOpSearchBehaviour>, expected: Translatable, otherExpected: Array<out Translatable>)
        = CharSequenceContainsAssertions.defaultTranslationOf(checkerOption, expected, otherExpected)

    override inline fun <T : CharSequence> defaultTranslationOfIgnoringCase(checkerOption: CharSequenceContains.CheckerOption<T, IgnoringCaseSearchBehaviour>, expected: Translatable, otherExpected: Array<out Translatable>)
        = CharSequenceContainsAssertions.defaultTranslationOfIgnoringCase(checkerOption, expected, otherExpected)

    override inline fun <T : CharSequence> regex(checkerOption: CharSequenceContains.CheckerOption<T, NoOpSearchBehaviour>, expected: String, otherExpected: Array<out String>)
        = CharSequenceContainsAssertions.regex(checkerOption, expected, otherExpected)

    override inline fun <T : CharSequence> regexIgnoringCase(checkerOption: CharSequenceContains.CheckerOption<T, IgnoringCaseSearchBehaviour>, expected: String, otherExpected: Array<out String>)
        = CharSequenceContainsAssertions.regexIgnoringCase(checkerOption, expected, otherExpected)

    /**
     * Delegates to [SearchBehaviourFactory].
     */
    inline val searchBehaviours get() = SearchBehaviourFactoryBuilder
}


object SearchBehaviourFactoryBuilder : ISearchBehaviourFactory {

    override inline fun <T : CharSequence> ignoringCase(containsBuilder: CharSequenceContains.Builder<T, NoOpSearchBehaviour>): CharSequenceContains.Builder<T, IgnoringCaseSearchBehaviour>
        = SearchBehaviourFactory.ignoringCase(containsBuilder)
}
