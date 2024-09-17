package ch.tutteli.atrium.reporting

import ch.tutteli.atrium._core
import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.proofs.InvisibleProofGroup
import ch.tutteli.atrium.creating.proofs.SimpleProof
import ch.tutteli.atrium.creating.proofs.builders.buildProof
import ch.tutteli.atrium.reporting.reportables.ProofExplanation
import kotlin.test.Test

class ProofBuilderTest {
    val core = expect(1)._core

    @Test
    fun createOneProof_does_not_wrap_in_invisible() {
        val subject = core.buildProof {
            simpleProof(Text("a"), 1) { true }
        }
        expect(subject).toBeAnInstanceOf<SimpleProof>()
    }

    @Test
    fun createTwoProof_wraps_into_invisible() {
        val subject = core.buildProof {
            simpleProof(Text("a"), 1) { true }
            simpleProof(Text("b"), 1) { true }
        }
        expect(subject).toBeAnInstanceOf<InvisibleProofGroup> {
            feature { f(it::children) } toContainExactly entries(
                { it.toBeAnInstanceOf<SimpleProof>() },
                { it.toBeAnInstanceOf<SimpleProof>() },
            )
        }
    }

    @Test
    fun createProofAndReportable_wraps_into_invisible() {
        val subject = core.buildProof {
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

    @Test
    fun createInvisibleWithAnInvisibleWithASingleProof_unwraps() {
        val subject = core.buildProof {
            invisibleGroup {
                invisibleGroup {
                    simpleProof(Text("a"), 1) { true }
                }
            }
        }
        expect(subject).toBeAnInstanceOf<SimpleProof>()
    }

    @Test
    fun createInvisibleWithAnInvisibleWithAProofAndAReportable_keepsOnly1Group() {
        val subject = core.buildProof {
            invisibleGroup {
                invisibleGroup {
                    simpleProof(Text("a"), 1) { true }
                    add(Text("bla"))
                }
            }
        }
        expect(subject).toBeAnInstanceOf<InvisibleProofGroup> {
            feature { f(it::children) } toContainExactly entries(
                { it.toBeAnInstanceOf<SimpleProof>() },
                { it.toBeAnInstanceOf<Text>() },
            )
        }
    }

    @Test
    fun createProofExplanationWithInvisibleProofGroupWithSingleChild_unwrapsInvisibleGroup() {
        val subject = core.buildProof {
            simpleProof(Text("a"), 1) { false }
            proofExplanation {
                invisibleGroup {
                    simpleProof(Text("a"), 1) { true }
                }
            }
        }
        expect(subject).toBeAnInstanceOf<InvisibleProofGroup> {
            feature { f(it::children) } toContainExactly entries(
                { it.toBeAnInstanceOf<SimpleProof>() },
                {
                    it.toBeAnInstanceOf<ProofExplanation> {
                        feature { f(it::children) } toContainExactly entries(
                            { it.toBeAnInstanceOf<SimpleProof>() }
                        )
                    }
                },
            )
        }
    }
}
