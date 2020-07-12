package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NotSearchBehaviour

/**
 * The access point to an implementation of [CharSequenceAssertions].
 *
 * It loads the implementation lazily via [loadSingleService].
 */
//TODO 0.14.0 deprecate
val charSequenceAssertions by lazy { loadSingleService(CharSequenceAssertions::class) }


/**
 * Defines the minimum set of assertion functions and builders applicable to [CharSequence],
 * which an implementation of the domain of Atrium has to provide.
 */
//TODO 0.14.0 deprecate
interface CharSequenceAssertions {
    fun <T : CharSequence> containsBuilder(subjectProvider: SubjectProvider<T>): CharSequenceContains.Builder<T, NoOpSearchBehaviour>
    fun <T : CharSequence> containsNotBuilder(subjectProvider: SubjectProvider<T>): CharSequenceContains.Builder<T, NotSearchBehaviour>

    fun startsWith(subjectProvider: SubjectProvider<CharSequence>, expected: CharSequence): Assertion
    fun startsNotWith(subjectProvider: SubjectProvider<CharSequence>, expected: CharSequence): Assertion
    fun endsWith(subjectProvider: SubjectProvider<CharSequence>, expected: CharSequence): Assertion
    fun endsNotWith(subjectProvider: SubjectProvider<CharSequence>, expected: CharSequence): Assertion
    fun isEmpty(subjectProvider: SubjectProvider<CharSequence>): Assertion
    fun isNotEmpty(subjectProvider: SubjectProvider<CharSequence>): Assertion
    fun isNotBlank(subjectProvider: SubjectProvider<CharSequence>): Assertion

    fun <T : CharSequence> matches(expect: Expect<T>, expected: Regex): Assertion
    fun <T : CharSequence> mismatches(expect: Expect<T>, expected: Regex): Assertion
}
