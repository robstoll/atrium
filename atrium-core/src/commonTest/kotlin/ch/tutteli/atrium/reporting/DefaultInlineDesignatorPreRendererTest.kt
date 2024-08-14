package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.api.infix.en_GB.feature
import ch.tutteli.atrium.api.infix.en_GB.of
import ch.tutteli.atrium.api.infix.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.api.verbs.internal.expectGrouped
import ch.tutteli.atrium.creating.ExperimentalComponentFactoryContainer
import ch.tutteli.atrium.creating.build
import ch.tutteli.atrium.creating.impl.DefaultComponentFactoryContainer
import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.reporting.reportables.Reportable
import kotlin.test.Test

class DefaultInlineDesignatorPreRendererTest {

    @Test
    fun canTransform_InlineElement_true() {
        val preRenderer = DefaultInlineDesignatorPreRenderer()
        val inlineElements = listOf(
            Text("bla"),
            Reportable.inlineGroup(listOf(Text("bli")))
        )
        //TODO 1.4.0 switch to testFactory
        expectGrouped {
            inlineElements.forEach {
                expect(preRenderer) feature of(DefaultInlineDesignatorPreRenderer::canTransform, it) toEqual true
            }
        }
    }

    @Test
    fun canTransform_notInlineElement_false() {
        val preRenderer = DefaultInlineDesignatorPreRenderer()
        val simpleProof = Proof.simple(Text("bla"), "rep") { false }
        val nonInlineElements = listOf(
            simpleProof,
            Proof.group(Text("bla"), "rep", children = listOf(simpleProof)),
            Reportable.debugGroup(Text("bla"), emptyList()),
            Reportable.group(Text("bla"), "rep", children = emptyList()),
            Reportable.proofExplanation(simpleProof),
            Reportable.usageHintGroup(emptyList())
        )
        //TODO 1.4.0 switch to testFactory
        expectGrouped {
            nonInlineElements.forEach {
                expect(preRenderer) feature of(DefaultInlineDesignatorPreRenderer::canTransform, it) toEqual false
            }
        }
    }

    @OptIn(ExperimentalComponentFactoryContainer::class)
    @Suppress("TestFunctionName")
    private fun DefaultInlineDesignatorPreRenderer() =
        DefaultInlineDesignatorPreRenderer(
            DefaultComponentFactoryContainer.build()
        )
}
