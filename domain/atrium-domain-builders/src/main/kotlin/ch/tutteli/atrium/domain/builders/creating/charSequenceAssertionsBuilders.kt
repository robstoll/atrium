@file:Suppress("OVERRIDE_BY_INLINE", "NOTHING_TO_INLINE")
package ch.tutteli.atrium.domain.builders.creating

import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.CharSequenceAssertions
import ch.tutteli.atrium.creating.charSequenceAssertions
import ch.tutteli.atrium.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.creating.charsequence.contains.creators.CharSequenceContainsAssertions
import ch.tutteli.atrium.creating.charsequence.contains.creators.charSequenceContainsAssertions
import ch.tutteli.atrium.creating.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour
import ch.tutteli.atrium.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.creating.charsequence.contains.searchbehaviours.SearchBehaviourFactory
import ch.tutteli.atrium.creating.charsequence.contains.searchbehaviours.searchBehaviourFactory
import ch.tutteli.atrium.reporting.translating.Translatable

object CharSequenceAssertionsBuilder : CharSequenceAssertions {

    override inline fun <T : CharSequence> containsBuilder(plant: AssertionPlant<T>)
        = charSequenceAssertions.containsBuilder(plant)

    override inline fun <T : CharSequence> containsNotBuilder(plant: AssertionPlant<T>)
        = charSequenceAssertions.containsNotBuilder(plant)

    override inline fun <T : CharSequence> startsWith(plant: AssertionPlant<T>, expected: CharSequence)
        = charSequenceAssertions.startsWith(plant, expected)

    override inline fun <T : CharSequence> startsNotWith(plant: AssertionPlant<T>, expected: CharSequence)
        = charSequenceAssertions.startsNotWith(plant, expected)

    override inline fun <T : CharSequence> endsWith(plant: AssertionPlant<T>, expected: CharSequence)
        = charSequenceAssertions.endsWith(plant, expected)

    override inline fun <T : CharSequence> endsNotWith(plant: AssertionPlant<T>, expected: CharSequence)
        = charSequenceAssertions.endsNotWith(plant, expected)

    override inline fun <T : CharSequence> isEmpty(plant: AssertionPlant<T>)
        = charSequenceAssertions.isEmpty(plant)

    override inline fun <T : CharSequence> isNotEmpty(plant: AssertionPlant<T>)
        = charSequenceAssertions.isNotEmpty(plant)

    /**
     * Delegates to [charSequenceContainsAssertions].
     */
    inline val contains get() = CharSequenceContainsAssertionsBuilder
}


object CharSequenceContainsAssertionsBuilder: CharSequenceContainsAssertions {

    override inline fun <T : CharSequence> values(checkerOption: CharSequenceContains.CheckerOption<T, NoOpSearchBehaviour>, expected: Any, otherExpected: Array<out Any>)
        = charSequenceContainsAssertions.values(checkerOption, expected, otherExpected)

    override inline fun <T : CharSequence> valuesIgnoringCase(checkerOption: CharSequenceContains.CheckerOption<T, IgnoringCaseSearchBehaviour>, expected: Any, otherExpected: Array<out Any>)
        = charSequenceContainsAssertions.valuesIgnoringCase(checkerOption, expected, otherExpected)

    override inline fun <T : CharSequence> defaultTranslationOf(checkerOption: CharSequenceContains.CheckerOption<T, NoOpSearchBehaviour>, expected: Translatable, otherExpected: Array<out Translatable>)
        = charSequenceContainsAssertions.defaultTranslationOf(checkerOption, expected, otherExpected)

    override inline fun <T : CharSequence> defaultTranslationOfIgnoringCase(checkerOption: CharSequenceContains.CheckerOption<T, IgnoringCaseSearchBehaviour>, expected: Translatable, otherExpected: Array<out Translatable>)
        = charSequenceContainsAssertions.defaultTranslationOfIgnoringCase(checkerOption, expected, otherExpected)

    override inline fun <T : CharSequence> regex(checkerOption: CharSequenceContains.CheckerOption<T, NoOpSearchBehaviour>, expected: String, otherExpected: Array<out String>)
        = charSequenceContainsAssertions.regex(checkerOption, expected, otherExpected)

    override inline fun <T : CharSequence> regexIgnoringCase(checkerOption: CharSequenceContains.CheckerOption<T, IgnoringCaseSearchBehaviour>, expected: String, otherExpected: Array<out String>)
        = charSequenceContainsAssertions.regexIgnoringCase(checkerOption, expected, otherExpected)

    /**
     * Delegates to [searchBehaviourFactory].
     */
    inline val searchBehaviours get() = SearchBehaviourFactoryBuilder
}


object SearchBehaviourFactoryBuilder : SearchBehaviourFactory {

    override inline fun <T : CharSequence> ignoringCase(containsBuilder: CharSequenceContains.Builder<T, NoOpSearchBehaviour>): CharSequenceContains.Builder<T, IgnoringCaseSearchBehaviour>
        = searchBehaviourFactory.ignoringCase(containsBuilder)
}
