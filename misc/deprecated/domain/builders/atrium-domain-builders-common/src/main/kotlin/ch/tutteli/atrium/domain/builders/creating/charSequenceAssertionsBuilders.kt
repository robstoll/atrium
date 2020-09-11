//TODO remove file with 1.0.0
@file:Suppress("DEPRECATION", "OVERRIDE_BY_INLINE", "NOTHING_TO_INLINE")

package ch.tutteli.atrium.domain.builders.creating

import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.creating.CharSequenceAssertions
import ch.tutteli.atrium.domain.creating.charSequenceAssertions
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.domain.creating.charsequence.contains.creators.CharSequenceContainsAssertions
import ch.tutteli.atrium.domain.creating.charsequence.contains.creators.charSequenceContainsAssertions
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.SearchBehaviourFactory
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.searchBehaviourFactory
import ch.tutteli.atrium.domain.creating.typeutils.CharSequenceOrNumberOrChar
import ch.tutteli.atrium.reporting.translating.Translatable
import kotlin.jvm.JvmName

/**
 * Delegates inter alia to the implementation of [CharSequenceAssertions].
 * In detail, it implements [CharSequenceAssertions] by delegating to [charSequenceAssertions]
 * which in turn delegates to the implementation via [loadSingleService].
 */
@Deprecated("Use _logic from ch.tutteli.atrium.logic instead; will be removed with 1.0.0")
object CharSequenceAssertionsBuilder : CharSequenceAssertions {

    override inline fun <T : CharSequence> containsBuilder(subjectProvider: SubjectProvider<T>) =
        charSequenceAssertions.containsBuilder(subjectProvider)

    override inline fun <T : CharSequence> containsNotBuilder(subjectProvider: SubjectProvider<T>) =
        charSequenceAssertions.containsNotBuilder(subjectProvider)

    override inline fun startsWith(subjectProvider: SubjectProvider<CharSequence>, expected: CharSequence) =
        charSequenceAssertions.startsWith(subjectProvider, expected)

    override inline fun startsNotWith(subjectProvider: SubjectProvider<CharSequence>, expected: CharSequence) =
        charSequenceAssertions.startsNotWith(subjectProvider, expected)

    override inline fun endsWith(subjectProvider: SubjectProvider<CharSequence>, expected: CharSequence) =
        charSequenceAssertions.endsWith(subjectProvider, expected)

    override inline fun endsNotWith(subjectProvider: SubjectProvider<CharSequence>, expected: CharSequence) =
        charSequenceAssertions.endsNotWith(subjectProvider, expected)

    override inline fun isEmpty(subjectProvider: SubjectProvider<CharSequence>) =
        charSequenceAssertions.isEmpty(subjectProvider)

    override inline fun isNotEmpty(subjectProvider: SubjectProvider<CharSequence>) =
        charSequenceAssertions.isNotEmpty(subjectProvider)

    override inline fun isNotBlank(subjectProvider: SubjectProvider<CharSequence>) =
        charSequenceAssertions.isNotBlank(subjectProvider)

    override inline fun <T : CharSequence> matches(expect: Expect<T>, expected: Regex) =
        charSequenceAssertions.matches(expect, expected)

    override inline fun <T : CharSequence> mismatches(expect: Expect<T>, expected: Regex) =
        charSequenceAssertions.mismatches(expect, expected)

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
@Deprecated("Use _logic from ch.tutteli.atrium.logic instead; will be removed with 1.0.0")
object CharSequenceContainsAssertionsBuilder : CharSequenceContainsAssertions {

    override inline fun <T : CharSequence> values(
        checkerOption: CharSequenceContains.CheckerOption<T, NoOpSearchBehaviour>,
        expected: List<CharSequenceOrNumberOrChar>
    ) = charSequenceContainsAssertions.values(checkerOption, expected)

    override inline fun <T : CharSequence> valuesIgnoringCase(
        checkerOption: CharSequenceContains.CheckerOption<T, IgnoringCaseSearchBehaviour>,
        expected: List<CharSequenceOrNumberOrChar>
    ) = charSequenceContainsAssertions.valuesIgnoringCase(checkerOption, expected)

    @Suppress("OverridingDeprecatedMember")
    override inline fun <T : CharSequence> defaultTranslationOf(
        checkerOption: CharSequenceContains.CheckerOption<T, NoOpSearchBehaviour>,
        expected: List<Translatable>
    ) = charSequenceContainsAssertions.defaultTranslationOf(checkerOption, expected)

    @Suppress("OverridingDeprecatedMember")
    override inline fun <T : CharSequence> defaultTranslationOfIgnoringCase(
        checkerOption: CharSequenceContains.CheckerOption<T, IgnoringCaseSearchBehaviour>,
        expected: List<Translatable>
    ) = charSequenceContainsAssertions.defaultTranslationOfIgnoringCase(checkerOption, expected)

    @JvmName("stringRegex")
    inline fun <T : CharSequence> regex(
        checkerOption: CharSequenceContains.CheckerOption<T, NoOpSearchBehaviour>,
        expected: List<String>
    ) = regex(checkerOption, expected.map { it.toRegex() })

    override inline fun <T : CharSequence> regex(
        checkerOption: CharSequenceContains.CheckerOption<T, NoOpSearchBehaviour>,
        expected: List<Regex>
    ) = charSequenceContainsAssertions.regex(checkerOption, expected)

    override inline fun <T : CharSequence> regexIgnoringCase(
        checkerOption: CharSequenceContains.CheckerOption<T, IgnoringCaseSearchBehaviour>,
        expected: List<String>
    ) = charSequenceContainsAssertions.regexIgnoringCase(checkerOption, expected)

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
@Deprecated("Use _logic from ch.tutteli.atrium.logic instead; will be removed with 1.0.0")
object CharSequenceContainsSearchBehaviourFactoryBuilder : SearchBehaviourFactory {

    override inline fun <T : CharSequence> ignoringCase(
        containsBuilder: CharSequenceContains.Builder<T, NoOpSearchBehaviour>
    ): CharSequenceContains.Builder<T, IgnoringCaseSearchBehaviour> =
        searchBehaviourFactory.ignoringCase(containsBuilder)
}
