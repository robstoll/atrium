package ch.tutteli.atrium.creating.proofs.impl

import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.creating.proofs.CharSequenceProofs
import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.creating.proofs.charsequence.contains.CharSequenceToContain
import ch.tutteli.atrium.creating.proofs.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.creating.proofs.charsequence.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.creating.proofs.charsequence.contains.steps.NotCheckerStep

class DefaultCharSequenceProofs : CharSequenceProofs {
    override fun <T : CharSequence> toContainBuilder(container: ProofContainer<T>): CharSequenceToContain.EntryPointStep<T, NoOpSearchBehaviour> {
        TODO("Not yet implemented")
    }

    override fun <T : CharSequence> notToContainBuilder(container: ProofContainer<T>): NotCheckerStep<T, NotSearchBehaviour> {
        TODO("Not yet implemented")
    }

    override fun <T : CharSequence> toStartWith(
        container: ProofContainer<T>,
        expected: CharSequence
    ): Proof {
        TODO("Not yet implemented")
    }

    override fun <T : CharSequence> notToStartWith(
        container: ProofContainer<T>,
        expected: CharSequence
    ): Proof {
        TODO("Not yet implemented")
    }

    override fun <T : CharSequence> toEndWith(
        container: ProofContainer<T>,
        expected: CharSequence
    ): Proof {
        TODO("Not yet implemented")
    }

    override fun <T : CharSequence> notToEndWith(
        container: ProofContainer<T>,
        expected: CharSequence
    ): Proof {
        TODO("Not yet implemented")
    }

    override fun <T : CharSequence> toBeEmpty(container: ProofContainer<T>): Proof {
        TODO("Not yet implemented")
    }

    override fun <T : CharSequence> notToBeEmpty(container: ProofContainer<T>): Proof {
        TODO("Not yet implemented")
    }

    override fun <T : CharSequence> notToBeBlank(container: ProofContainer<T>): Proof {
        TODO("Not yet implemented")
    }

    override fun <T : CharSequence> toMatch(
        container: ProofContainer<T>,
        expected: Regex
    ): Proof {
        TODO("Not yet implemented")
    }

    override fun <T : CharSequence> notToMatch(
        container: ProofContainer<T>,
        expected: Regex
    ): Proof {
        TODO("Not yet implemented")
    }

}
