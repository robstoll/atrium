package ch.tutteli.atrium.domain.creating.charsequence.contains

import ch.tutteli.atrium.domain.creating.charsequence.contains.impl.CharSequenceContainsBuilderNoOpSearchBehaviourDomainImpl
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour


/**
 * Access to the domain level of Atrium where this [CharSequenceContains.Builder] is passed along,
 * scoping it to the domain of subjects which have a type extending [CharSequence]
 * and a [NoOpSearchBehaviour].
 */
val <T : CharSequence> CharSequenceContains.Builder<T, NoOpSearchBehaviour>._domain
    get(): CharSequenceContainsBuilderNoOpSearchBehaviourDomain<T> =
        CharSequenceContainsBuilderNoOpSearchBehaviourDomainImpl(this)

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
