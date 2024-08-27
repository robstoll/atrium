package ch.tutteli.atrium.reporting

import ch.tutteli.atrium._core
import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.proofs.InvisibleProofGroup
import ch.tutteli.atrium.creating.proofs.SimpleProof
import ch.tutteli.atrium.creating.proofs.buildProof
import kotlin.test.Test

class ProofBuilderTest {

    @Test
    fun createOneProof_does_not_wrap_in_invisible() {
        val subject = expect(1)._core.buildProof {
            simpleProof(Text("a"), 1) { true }
        }
        expect(subject).toBeAnInstanceOf<Text>()
    }

    @Test
    fun createProofAndReportable_wraps_into_invisible() {
        val subject = expect(1)._core.buildProof {
            simpleProof(Text("a"), 1) { true }
            add(Text("bla"))
        }
        expect(subject).toBeAnInstanceOf<InvisibleProofGroup> {
            feature { f(it::children) } toContainExactly entries(
                { it.toBeAnInstanceOf<SimpleProof>() },
                { it.toBeAnInstanceOf<Text>() },
            )
        }
    }
}
