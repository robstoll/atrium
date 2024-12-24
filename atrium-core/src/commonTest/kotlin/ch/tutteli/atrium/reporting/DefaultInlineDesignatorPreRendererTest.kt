package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.api.infix.en_GB.feature
import ch.tutteli.atrium.api.infix.en_GB.of
import ch.tutteli.atrium.api.infix.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.api.verbs.internal.expectGrouped
import ch.tutteli.atrium.api.verbs.internal.testFactory
import ch.tutteli.atrium.creating.ExperimentalComponentFactoryContainer
import ch.tutteli.atrium.creating.build
import ch.tutteli.atrium.creating.impl.DefaultComponentFactoryContainer
import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.reporting.prerendering.text.impl.DefaultInlineDesignatorPreRenderer
import ch.tutteli.atrium.reporting.reportables.InlineElement
import ch.tutteli.atrium.reporting.reportables.Reportable
import ch.tutteli.atrium.reporting.reportables.TextElement
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionAnyProof
import ch.tutteli.atrium.testfactories.TestFactory
import kotlin.test.Test

class DefaultInlineDesignatorPreRendererTest {

    @TestFactory
    fun canTransform_InlineElement_true() = testFactory {
        val preRenderer = buildDefaultInlineDesignatorPreRenderer()
        val inlineElements = listOf(
            Text("bla"),
            Reportable.inlineGroup(listOf(Text("bli"))),
            DescriptionAnyProof.TO_EQUAL,
            object : InlineElement {},
            object : TextElement {
                override val string: String = ""
            }
        )
        inlineElements.forEach {
            it("inline element ${it::class.simpleName}") {
                expect(preRenderer) feature of(DefaultInlineDesignatorPreRenderer::canTransform, it) toEqual true
            }
        }
    }

    @Test
    fun canTransform_notInlineElement_false() {
        val preRenderer = buildDefaultInlineDesignatorPreRenderer()
        val simpleProof = Proof.simple(Text("bla"), "rep") { false }
        val nonInlineElements = listOf(
            simpleProof,
            Proof.group(Text("bla"), "rep", children = listOf(simpleProof)),
            Reportable.debugGroup(Text("bla"), listOf(simpleProof)),
            Reportable.failureExplanationGroup(Text("bli"), listOf(simpleProof)),
            Reportable.group(Text("bla"), "rep", children = listOf(simpleProof)),
            Reportable.informationGroup(Text("info"), listOf(simpleProof)),
            Reportable.proofExplanation(simpleProof),
            Reportable.usageHintGroup(listOf(simpleProof)),
        )
        //TODO 1.3.0 switch to testFactory
        expectGrouped {
            nonInlineElements.forEach {
                expect(preRenderer) feature of(DefaultInlineDesignatorPreRenderer::canTransform, it) toEqual false
            }
        }
    }

    @OptIn(ExperimentalComponentFactoryContainer::class)
    private fun buildDefaultInlineDesignatorPreRenderer() =
        DefaultInlineDesignatorPreRenderer(DefaultComponentFactoryContainer.build())
}
