@file:Suppress("ObjectPropertyName")

package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.domain.creating.impl.*

/**
 * Access to the domain level of Atrium where this [Expect] is passed along,
 * scoping it to the domain of subjects which have a type extending [CharSequence].
 */
val <T : CharSequence> Expect<T>._domain: CharSequenceDomain<T>
    get() = CharSequenceDomainImpl(CharSequenceOnlyDomainImpl(this), AnyDomainImpl(this))

/**
 * Defines the minimum set of assertion functions and builders applicable to types extending [CharSequence],
 * which an implementation of the domain of Atrium has to provide.
 */
interface CharSequenceDomain<T : CharSequence> : CharSequenceOnlyDomain<T>, AnyDomain<T>

/**
 * Defines the minimum set of assertion functions and builders applicable to types extending [CharSequence]
 * excluding the assertion functions which are defined on domains of super types
 * (e.g. the functions of the [AnyDomain]), which an implementation of the domain of Atrium has to provide.
 */
interface CharSequenceOnlyDomain<T : CharSequence> : ExpectDomain<T> {
    val containsBuilder: CharSequenceContains.Builder<T, NoOpSearchBehaviour>
    val containsNotBuilder: CharSequenceContains.Builder<T, NotSearchBehaviour>

    fun startsWith(expected: CharSequence): Assertion
    fun startsNotWith(expected: CharSequence): Assertion
    fun endsWith(expected: CharSequence): Assertion
    fun endsNotWith(expected: CharSequence): Assertion
    fun isEmpty(): Assertion
    fun isNotEmpty(): Assertion
    fun isNotBlank(): Assertion

    /**
     * @since 0.9.0
     */
    fun matches(expected: Regex): Assertion

    /**
     * @since 0.9.0
     */
    fun mismatches(expected: Regex): Assertion
}


