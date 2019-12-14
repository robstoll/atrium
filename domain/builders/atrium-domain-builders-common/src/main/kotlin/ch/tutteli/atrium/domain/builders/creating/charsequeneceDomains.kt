@file:Suppress("ObjectPropertyName")

package ch.tutteli.atrium.domain.builders.creating

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.creating.impl.CharSequenceContainsBuilderNoOpSearchBehaviourDomainImpl
import ch.tutteli.atrium.domain.builders.creating.impl.CharSequenceContainsCheckerIgnoringCaseSearchBehaviourDomainImpl
import ch.tutteli.atrium.domain.builders.creating.impl.CharSequenceContainsCheckerNoOpSearchBehaviourDomainImpl
import ch.tutteli.atrium.domain.builders.creating.impl.CharSequenceDomainImpl
import ch.tutteli.atrium.domain.creating.CharSequenceContainsBuilderNoOpSearchBehaviourDomain
import ch.tutteli.atrium.domain.creating.CharSequenceContainsCheckerIgnoringCaseSearchBehaviourDomain
import ch.tutteli.atrium.domain.creating.CharSequenceContainsCheckerNoOpSearchBehaviourDomain
import ch.tutteli.atrium.domain.creating.CharSequenceDomain
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour

val <T : CharSequence> Expect<T>._domain: CharSequenceDomain<T> get() = CharSequenceDomainImpl(this)


val <T : CharSequence> CharSequenceContains.Builder<T, NoOpSearchBehaviour>._domain
    get(): CharSequenceContainsBuilderNoOpSearchBehaviourDomain<T> =
        CharSequenceContainsBuilderNoOpSearchBehaviourDomainImpl(this)

val <T : CharSequence> CharSequenceContains.CheckerOption<T, NoOpSearchBehaviour>._domain
    get() : CharSequenceContainsCheckerNoOpSearchBehaviourDomain<T> =
        CharSequenceContainsCheckerNoOpSearchBehaviourDomainImpl(this)

val <T : CharSequence> CharSequenceContains.CheckerOption<T, IgnoringCaseSearchBehaviour>._domain
    get() : CharSequenceContainsCheckerIgnoringCaseSearchBehaviourDomain<T> =
        CharSequenceContainsCheckerIgnoringCaseSearchBehaviourDomainImpl(this)
