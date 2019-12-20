package ch.tutteli.atrium.domain.creating.charsequence.contains.impl

import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContainsBuilderNoOpSearchBehaviourDomain
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.searchBehaviourFactory

internal class CharSequenceContainsBuilderNoOpSearchBehaviourDomainImpl<T : CharSequence>(
    override val builder: CharSequenceContains.Builder<T, NoOpSearchBehaviour>
) : CharSequenceContainsBuilderNoOpSearchBehaviourDomain<T> {
    override val ignoringCase: CharSequenceContains.Builder<T, IgnoringCaseSearchBehaviour>
        get() = searchBehaviourFactory.ignoringCase(builder)
}
