@file:Suppress("OVERRIDE_BY_INLINE", "NOTHING_TO_INLINE")
package ch.tutteli.atrium.creating

import ch.tutteli.atrium.creating.charsequence.contains.builders.CharSequenceContainsBuilder
import ch.tutteli.atrium.creating.charsequence.contains.builders.CharSequenceContainsCheckerBuilder
import ch.tutteli.atrium.creating.charsequence.contains.creators.CharSequenceContainsAssertions
import ch.tutteli.atrium.creating.charsequence.contains.creators.ICharSequenceContainsAssertions
import ch.tutteli.atrium.creating.charsequence.contains.searchbehaviours.CharSequenceContainsIgnoringCaseSearchBehaviour
import ch.tutteli.atrium.creating.charsequence.contains.searchbehaviours.CharSequenceContainsNoOpSearchBehaviour
import ch.tutteli.atrium.creating.charsequence.contains.searchbehaviours.CharSequenceContainsSearchBehaviours
import ch.tutteli.atrium.creating.charsequence.contains.searchbehaviours.ICharSequenceContainsSearchBehaviours
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

    inline val contains get() = CharSequenceContainsAssertionsBuilder
}


object CharSequenceContainsAssertionsBuilder: ICharSequenceContainsAssertions {

    override inline fun <T : CharSequence> values(checkerBuilder: CharSequenceContainsCheckerBuilder<T, CharSequenceContainsNoOpSearchBehaviour>, expected: Any, otherExpected: Array<out Any>)
        = CharSequenceContainsAssertions.values(checkerBuilder, expected, otherExpected)

    override inline fun <T : CharSequence> valuesIgnoringCase(checkerBuilder: CharSequenceContainsCheckerBuilder<T, CharSequenceContainsIgnoringCaseSearchBehaviour>, expected: Any, otherExpected: Array<out Any>)
        = CharSequenceContainsAssertions.valuesIgnoringCase(checkerBuilder, expected, otherExpected)

    override inline fun <T : CharSequence> defaultTranslationOf(checkerBuilder: CharSequenceContainsCheckerBuilder<T, CharSequenceContainsNoOpSearchBehaviour>, expected: Translatable, otherExpected: Array<out Translatable>)
        = CharSequenceContainsAssertions.defaultTranslationOf(checkerBuilder, expected, otherExpected)

    override inline fun <T : CharSequence> defaultTranslationOfIgnoringCase(checkerBuilder: CharSequenceContainsCheckerBuilder<T, CharSequenceContainsIgnoringCaseSearchBehaviour>, expected: Translatable, otherExpected: Array<out Translatable>)
        = CharSequenceContainsAssertions.defaultTranslationOfIgnoringCase(checkerBuilder, expected, otherExpected)

    override inline fun <T : CharSequence> regex(checkerBuilder: CharSequenceContainsCheckerBuilder<T, CharSequenceContainsNoOpSearchBehaviour>, expected: String, otherExpected: Array<out String>)
        = CharSequenceContainsAssertions.regex(checkerBuilder, expected, otherExpected)

    override inline fun <T : CharSequence> regexIgnoringCase(checkerBuilder: CharSequenceContainsCheckerBuilder<T, CharSequenceContainsIgnoringCaseSearchBehaviour>, expected: String, otherExpected: Array<out String>)
        = CharSequenceContainsAssertions.regexIgnoringCase(checkerBuilder, expected, otherExpected)

    inline val searchBehaviours get() = CharSequenceContainsSearchBehavioursBuilder
}

object CharSequenceContainsSearchBehavioursBuilder: ICharSequenceContainsSearchBehaviours{

    override inline fun <T : CharSequence> ignoringCase(containsBuilder: CharSequenceContainsBuilder<T, CharSequenceContainsNoOpSearchBehaviour>): CharSequenceContainsBuilder<T, CharSequenceContainsIgnoringCaseSearchBehaviour>
        = CharSequenceContainsSearchBehaviours.ignoringCase(containsBuilder)
}
