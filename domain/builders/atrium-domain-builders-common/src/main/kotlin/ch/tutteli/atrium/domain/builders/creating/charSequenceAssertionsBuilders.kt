@file:Suppress("OVERRIDE_BY_INLINE", "NOTHING_TO_INLINE")
package ch.tutteli.atrium.domain.builders.creating

import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.creating.CharSequenceAssertions
import ch.tutteli.atrium.domain.creating.charSequenceAssertions
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.domain.creating.charsequence.contains.creators.CharSequenceContainsAssertions
import ch.tutteli.atrium.domain.creating.charsequence.contains.creators.charSequenceContainsAssertions
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.SearchBehaviourFactory
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.searchBehaviourFactory
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Delegates inter alia to the implementation of [CharSequenceAssertions].
 * In detail, it implements [CharSequenceAssertions] by delegating to [charSequenceAssertions]
 * which in turn delegates to the implementation via [loadSingleService].
 */
object CharSequenceAssertionsBuilder : CharSequenceAssertions {

    override inline fun <T : CharSequence> containsBuilder(plant: AssertionPlant<T>)
        = charSequenceAssertions.containsBuilder(plant)

    override inline fun <T : CharSequence> containsNotBuilder(plant: AssertionPlant<T>)
        = charSequenceAssertions.containsNotBuilder(plant)

    override inline fun startsWith(plant: AssertionPlant<CharSequence>, expected: CharSequence)
        = charSequenceAssertions.startsWith(plant, expected)

    override inline fun startsNotWith(plant: AssertionPlant<CharSequence>, expected: CharSequence)
        = charSequenceAssertions.startsNotWith(plant, expected)

    override inline fun endsWith(plant: AssertionPlant<CharSequence>, expected: CharSequence)
        = charSequenceAssertions.endsWith(plant, expected)

    override inline fun endsNotWith(plant: AssertionPlant<CharSequence>, expected: CharSequence)
        = charSequenceAssertions.endsNotWith(plant, expected)

    override inline fun isEmpty(plant: AssertionPlant<CharSequence>)
        = charSequenceAssertions.isEmpty(plant)

    override inline fun isNotEmpty(plant: AssertionPlant<CharSequence>)
        = charSequenceAssertions.isNotEmpty(plant)

    override inline fun isNotBlank(plant: AssertionPlant<CharSequence>)
        = charSequenceAssertions.isNotBlank(plant)

    /**
     * Returns [CharSequenceContainsAssertionsBuilder]
     * which inter alia delegates to the implementation of [CharSequenceContainsAssertions].
     */
    inline val contains get() = CharSequenceContainsAssertionsBuilder
}

/**
 * Delegates inter alia to the implementation of [CharSequenceContainsAssertions].
 * In detail, it implements [CharSequenceContainsAssertions] by delegating to [charSequenceContainsAssertions]
 * which in turn delegates to the implementation via [loadSingleService].
 */
object CharSequenceContainsAssertionsBuilder: CharSequenceContainsAssertions {

    override inline fun <T : CharSequence> values(checkerOption: CharSequenceContains.CheckerOption<T, NoOpSearchBehaviour>, expected: List<Any>)
        = charSequenceContainsAssertions.values(checkerOption, expected)

    override inline fun <T : CharSequence> valuesIgnoringCase(checkerOption: CharSequenceContains.CheckerOption<T, IgnoringCaseSearchBehaviour>, expected: List<Any>)
        = charSequenceContainsAssertions.valuesIgnoringCase(checkerOption, expected)

    override inline fun <T : CharSequence> defaultTranslationOf(checkerOption: CharSequenceContains.CheckerOption<T, NoOpSearchBehaviour>, expected: List<Translatable>)
        = charSequenceContainsAssertions.defaultTranslationOf(checkerOption, expected)

    override inline fun <T : CharSequence> defaultTranslationOfIgnoringCase(checkerOption: CharSequenceContains.CheckerOption<T, IgnoringCaseSearchBehaviour>, expected: List<Translatable>)
        = charSequenceContainsAssertions.defaultTranslationOfIgnoringCase(checkerOption, expected)

    override inline fun <T : CharSequence> regex(checkerOption: CharSequenceContains.CheckerOption<T, NoOpSearchBehaviour>, expected: List<String>)
        = charSequenceContainsAssertions.regex(checkerOption, expected)

    override inline fun <T : CharSequence> regexIgnoringCase(checkerOption: CharSequenceContains.CheckerOption<T, IgnoringCaseSearchBehaviour>, expected: List<String>)
        = charSequenceContainsAssertions.regexIgnoringCase(checkerOption, expected)

    /**
     * Returns [CharSequenceContainsSearchBehaviourFactoryBuilder]
     * which inter alia delegates to the implementation of [SearchBehaviourFactory].
     */
    inline val searchBehaviours get() = CharSequenceContainsSearchBehaviourFactoryBuilder
}

/**
 * Delegates inter alia to the implementation of [SearchBehaviourFactory].
 * In detail, it implements [SearchBehaviourFactory] by delegating to [searchBehaviourFactory]
 * which in turn delegates to the implementation via [loadSingleService].
 */
object CharSequenceContainsSearchBehaviourFactoryBuilder : SearchBehaviourFactory {

    override inline fun <T : CharSequence> ignoringCase(containsBuilder: CharSequenceContains.Builder<T, NoOpSearchBehaviour>): CharSequenceContains.Builder<T, IgnoringCaseSearchBehaviour>
        = searchBehaviourFactory.ignoringCase(containsBuilder)
}
