package ch.tutteli.atrium.creating.proofs.impl

import ch.tutteli.atrium._core
import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.creating.proofs.CharSequenceProofs
import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.creating.proofs.builders.buildSimpleProof
import ch.tutteli.atrium.creating.proofs.charsequence.contains.CharSequenceToContain
import ch.tutteli.atrium.creating.proofs.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.creating.proofs.charsequence.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.creating.proofs.charsequence.contains.searchbehaviours.impl.NoOpSearchBehaviourImpl
import ch.tutteli.atrium.creating.proofs.charsequence.contains.searchbehaviours.impl.NotSearchBehaviourImpl
import ch.tutteli.atrium.creating.proofs.charsequence.contains.steps.NotCheckerStep
import ch.tutteli.atrium.creating.proofs.charsequence.contains.steps.impl.EntryPointStepImpl
import ch.tutteli.atrium.creating.proofs.charsequence.contains.steps.notCheckerStep
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionBasic.NOT_TO_BE
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionBasic.TO_BE
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionCharSequenceProof.*

class DefaultCharSequenceProofs : CharSequenceProofs {
    override fun <T : CharSequence> toContainBuilder(
        container: ProofContainer<T>
    ): CharSequenceToContain.EntryPointStep<T, NoOpSearchBehaviour> =
        EntryPointStepImpl(container, NoOpSearchBehaviourImpl())

    override fun <T : CharSequence> notToContainBuilder(
        container: ProofContainer<T>
    ): NotCheckerStep<T, NotSearchBehaviour> =
        EntryPointStepImpl(container, NotSearchBehaviourImpl())._core.notCheckerStep()

    override fun <T : CharSequence> toStartWith(container: ProofContainer<T>, expected: CharSequence): Proof =
        container.buildSimpleProof(TO_START_WITH, expected) { it.startsWith(expected) }

    override fun <T : CharSequence> notToStartWith(container: ProofContainer<T>, expected: CharSequence): Proof =
        container.buildSimpleProof(NOT_TO_START_WITH, expected) { !it.startsWith(expected) }

    override fun <T : CharSequence> toEndWith(container: ProofContainer<T>, expected: CharSequence): Proof =
        container.buildSimpleProof(TO_END_WITH, expected) { it.endsWith(expected) }

    override fun <T : CharSequence> notToEndWith(container: ProofContainer<T>, expected: CharSequence): Proof =
        container.buildSimpleProof(NOT_TO_END_WITH, expected) { !it.endsWith(expected) }

    override fun <T : CharSequence> toBeEmpty(container: ProofContainer<T>): Proof =
        container.buildSimpleProof(TO_BE, EMPTY) { it.isEmpty() }

    override fun <T : CharSequence> notToBeEmpty(container: ProofContainer<T>): Proof =
        container.buildSimpleProof(NOT_TO_BE, EMPTY) { it.isNotEmpty() }

    override fun <T : CharSequence> notToBeBlank(container: ProofContainer<T>): Proof =
        container.buildSimpleProof(NOT_TO_BE, BLANK) { it.isNotBlank() }

    override fun <T : CharSequence> toMatch(container: ProofContainer<T>, expected: Regex): Proof =
        container.buildSimpleProof(TO_MATCH, expected) { it.matches(expected) }

    override fun <T : CharSequence> notToMatch(container: ProofContainer<T>, expected: Regex): Proof =
        container.buildSimpleProof(NOT_TO_MATCH, expected) { !it.matches(expected) }
}
