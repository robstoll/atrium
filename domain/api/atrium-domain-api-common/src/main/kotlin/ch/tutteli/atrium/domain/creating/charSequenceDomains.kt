package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NotSearchBehaviour

/**
 * Defines the minimum set of assertion functions and builders applicable to types extending [CharSequence],
 * which an implementation of the domain of Atrium has to provide.
 */
interface CharSequenceDomain<T : CharSequence> : ExpectDomain<T> {

    val containsBuilder: CharSequenceContains.Builder<T, NoOpSearchBehaviour>
    val containsNotBuilder: CharSequenceContains.Builder<T, NotSearchBehaviour>

    fun startsWith(expected: CharSequence): Assertion
    fun startsNotWith(expected: CharSequence): Assertion
    fun endsWith(expected: CharSequence): Assertion
    fun endsNotWith(expected: CharSequence): Assertion
    fun isEmpty(): Assertion
    fun isNotEmpty(): Assertion
    fun isNotBlank(): Assertion
    fun matches(expected: Regex): Assertion
    fun mismatches(expected: Regex): Assertion
}

interface CharSequenceContainsBuilderDomain<T : CharSequence, S : CharSequenceContains.SearchBehaviour> {
    val builder: CharSequenceContains.Builder<T, S>
}

interface CharSequenceContainsBuilderNoOpSearchBehaviourDomain<T : CharSequence> :
    CharSequenceContainsBuilderDomain<T, NoOpSearchBehaviour> {

    val ignoringCase: CharSequenceContains.Builder<T, IgnoringCaseSearchBehaviour>
}


interface CharSequenceContainsCheckerDomain<T : CharSequence, S : CharSequenceContains.SearchBehaviour> {
    val checker: CharSequenceContains.CheckerOption<T, S>
}

interface CharSequenceContainsCheckerNoOpSearchBehaviourDomain<T : CharSequence> :
    CharSequenceContainsCheckerDomain<T, NoOpSearchBehaviour> {

    fun values(expected: List<Any>): AssertionGroup
    fun regex(expected: List<Regex>): AssertionGroup
}

// cannot be in the interface as we have a JVM name clash otherwise and
// @JvmName on the other hand cannot be applied in an interface
fun <T : CharSequence> CharSequenceContainsCheckerNoOpSearchBehaviourDomain<T>.regex(
    expected: List<String>
): AssertionGroup = regex(expected.map { it.toRegex() })

interface CharSequenceContainsCheckerIgnoringCaseSearchBehaviourDomain<T : CharSequence> :
    CharSequenceContainsCheckerDomain<T, IgnoringCaseSearchBehaviour> {

    fun values(expected: List<Any>): AssertionGroup
    fun regex(expected: List<String>): AssertionGroup
}
