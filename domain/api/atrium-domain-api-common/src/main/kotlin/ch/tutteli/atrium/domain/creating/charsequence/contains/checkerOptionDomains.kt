package ch.tutteli.atrium.domain.creating.charsequence.contains

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.domain.creating.charsequence.contains.impl.CharSequenceContainsCheckerIgnoringCaseSearchBehaviourDomainImpl
import ch.tutteli.atrium.domain.creating.charsequence.contains.impl.CharSequenceContainsCheckerNoOpSearchBehaviourDomainImpl
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour

/**
 * Access to the domain level of Atrium where this [CharSequenceContains.CheckerOption] is passed along,
 * scoping it to the domain of subjects whose type extends [CharSequence]
 * and a [NoOpSearchBehaviour].
 */
val <T : CharSequence> CharSequenceContains.CheckerOption<T, NoOpSearchBehaviour>._domain
    get() : CharSequenceContainsCheckerNoOpSearchBehaviourDomain<T> =
        CharSequenceContainsCheckerNoOpSearchBehaviourDomainImpl(this)

/**
 * Access to the domain level of Atrium where this [CharSequenceContains.CheckerOption] is passed along,
 * scoping it to the domain of subjects whose type extends [CharSequence]
 * and a [IgnoringCaseSearchBehaviour].
 */
val <T : CharSequence> CharSequenceContains.CheckerOption<T, IgnoringCaseSearchBehaviour>._domain
    get() : CharSequenceContainsCheckerIgnoringCaseSearchBehaviourDomain<T> =
        CharSequenceContainsCheckerIgnoringCaseSearchBehaviourDomainImpl(this)




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
 */
interface CharSequenceContainsCheckerNoOpSearchBehaviourDomain<T : CharSequence> :
    CharSequenceContainsCheckerDomain<T, NoOpSearchBehaviour> {

    fun values(expected: List<Any>): AssertionGroup

    /**
     * @since 0.9.0
     */
    fun regex(expected: List<Regex>): AssertionGroup
}


/**
 * Helper function, maps the given [expected] regex which are [String]s to [Regex] and delegates to
 * [CharSequenceContainsCheckerNoOpSearchBehaviourDomain.regex].
 */
// cannot be in the interface as we have a JVM name clash otherwise and
// @JvmName on the other hand cannot be applied in an interface
fun <T : CharSequence> CharSequenceContainsCheckerNoOpSearchBehaviourDomain<T>.regex(
    expected: List<String>
): AssertionGroup = regex(expected.map { it.toRegex() })

/**
 * Represents the domains of [CharSequenceContains.CheckerOption] with an [IgnoringCaseSearchBehaviour].
 */
interface CharSequenceContainsCheckerIgnoringCaseSearchBehaviourDomain<T : CharSequence> :
    CharSequenceContainsCheckerDomain<T, IgnoringCaseSearchBehaviour> {

    fun values(expected: List<Any>): AssertionGroup
    fun regex(expected: List<String>): AssertionGroup
}
