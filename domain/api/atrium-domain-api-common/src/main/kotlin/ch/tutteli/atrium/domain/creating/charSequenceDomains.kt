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
 *
 * @since 0.9.0
 */
val <T : CharSequence> Expect<T>._domain: CharSequenceDomain<T>
    get() = CharSequenceDomainImpl(CharSequenceOnlyDomainImpl(this), AnyDomainImpl(this))


/**
 * Access to the domain level of Atrium where this [CharSequenceContains.Builder] is passed along,
 * scoping it to the domain of subjects which have a type extending [CharSequence]
 * and a [NoOpSearchBehaviour].
 *
 * @since 0.9.0
 */
val <T : CharSequence> CharSequenceContains.Builder<T, NoOpSearchBehaviour>._domain
    get(): CharSequenceContainsBuilderNoOpSearchBehaviourDomain<T> =
        CharSequenceContainsBuilderNoOpSearchBehaviourDomainImpl(this)

/**
 * Access to the domain level of Atrium where this [CharSequenceContains.CheckerOption] is passed along,
 * scoping it to the domain of subjects which have a type extending [CharSequence]
 * and a [NoOpSearchBehaviour].
 *
 * @since 0.9.0
 */
val <T : CharSequence> CharSequenceContains.CheckerOption<T, NoOpSearchBehaviour>._domain
    get() : CharSequenceContainsCheckerNoOpSearchBehaviourDomain<T> =
        CharSequenceContainsCheckerNoOpSearchBehaviourDomainImpl(this)

/**
 * Access to the domain level of Atrium where this [CharSequenceContains.CheckerOption] is passed along,
 * scoping it to the domain of subjects which have a type extending [CharSequence]
 * and a [IgnoringCaseSearchBehaviour].
 *
 * @since 0.9.0
 */
val <T : CharSequence> CharSequenceContains.CheckerOption<T, IgnoringCaseSearchBehaviour>._domain
    get() : CharSequenceContainsCheckerIgnoringCaseSearchBehaviourDomain<T> =
        CharSequenceContainsCheckerIgnoringCaseSearchBehaviourDomainImpl(this)


/**
 * Defines the minimum set of assertion functions and builders applicable to types extending [CharSequence],
 * which an implementation of the domain of Atrium has to provide.
 *
 * @since 0.9.0
 */
interface CharSequenceDomain<T : CharSequence> : CharSequenceOnlyDomain<T>, AnyDomain<T>

/**
 * Defines the minimum set of assertion functions and builders applicable to types extending [CharSequence]
 * excluding the assertion functions which are defined on domains of  super types
 * (e.g. the functions of the [AnyDomain]), which an implementation of the domain of Atrium has to provide.
 *
 * @since 0.9.0
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
    fun matches(expected: Regex): Assertion
    fun mismatches(expected: Regex): Assertion
}


/**
 * Represents the base interface for domains of [CharSequenceContains.Builder].
 *
 * @since 0.9.0
 */
interface CharSequenceContainsBuilderDomain<T : CharSequence, S : CharSequenceContains.SearchBehaviour> {
    val builder: CharSequenceContains.Builder<T, S>
}

/**
 * Represents the domains of [CharSequenceContains.Builder] with a [NoOpSearchBehaviour].
 *
 * @since 0.9.0
 */
interface CharSequenceContainsBuilderNoOpSearchBehaviourDomain<T : CharSequence> :
    CharSequenceContainsBuilderDomain<T, NoOpSearchBehaviour> {

    val ignoringCase: CharSequenceContains.Builder<T, IgnoringCaseSearchBehaviour>
}


/**
 * Represents the base interface for domains of [CharSequenceContains.CheckerOption].
 *
 * @since 0.9.0
 */
interface CharSequenceContainsCheckerDomain<T : CharSequence, S : CharSequenceContains.SearchBehaviour> {
    val checkerOption: CharSequenceContains.CheckerOption<T, S>
}

/**
 * Represents the domains of [CharSequenceContains.CheckerOption] with a [NoOpSearchBehaviour].
 *
 * @since 0.9.0
 */
interface CharSequenceContainsCheckerNoOpSearchBehaviourDomain<T : CharSequence> :
    CharSequenceContainsCheckerDomain<T, NoOpSearchBehaviour> {

    fun values(expected: List<Any>): AssertionGroup
    fun regex(expected: List<Regex>): AssertionGroup
}


/**
 * Helper function, maps the given [expected] regex which are [String]s to [Regex] and delegates to
 * [CharSequenceContainsCheckerNoOpSearchBehaviourDomain.regex].
 *
 * @since 0.9.0
 */
// cannot be in the interface as we have a JVM name clash otherwise and
// @JvmName on the other hand cannot be applied in an interface
fun <T : CharSequence> CharSequenceContainsCheckerNoOpSearchBehaviourDomain<T>.regex(
    expected: List<String>
): AssertionGroup = regex(expected.map { it.toRegex() })

/**
 * Represents the domains of [CharSequenceContains.CheckerOption] with an [IgnoringCaseSearchBehaviour].
 *
 * @since 0.9.0
 */
interface CharSequenceContainsCheckerIgnoringCaseSearchBehaviourDomain<T : CharSequence> :
    CharSequenceContainsCheckerDomain<T, IgnoringCaseSearchBehaviour> {

    fun values(expected: List<Any>): AssertionGroup
    fun regex(expected: List<String>): AssertionGroup
}
