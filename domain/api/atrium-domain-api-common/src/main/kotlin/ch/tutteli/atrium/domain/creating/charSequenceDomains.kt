@file:Suppress("ObjectPropertyName")

package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.domain.creating.impl.*

/**
 * Access to the domain level of Atrium where this [Expect] is passed along,
 * scoping it to the domain of subjects whose type extends [CharSequence];
 * i.e. it returns a [CharSequenceDomain] for this [Expect].
 */
val <T : CharSequence> Expect<T>._domain: CharSequenceDomain<T>
    get() = CharSequenceDomainImpl(
        CharSequenceSubDomainImpl(this),
        //TODO simplify once we have expect.config.impl in 0.10.0
        AnyDomainImpl(this, AnyInclNullableDomainImpl(this))
    )

/**
 * Represents the [ExpectDomain] whose type extends [CharSequence];
 * i.e. the subject of the underlying [expect] has such a type.
 */
interface CharSequenceDomain<T : CharSequence> : CharSequenceSubDomain<T>, AnyDomain<T>

/**
 * Represents a sub-[ExpectDomain] whose type extends [CharSequence]
 * -- i.e. the subject of the underlying [expect] has such a type --
 * where it does not include the sub domains of super types of [CharSequence] (e.g. the functions of the [AnyDomain]).
 */
interface CharSequenceSubDomain<T : CharSequence> : ExpectDomain<T> {
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


