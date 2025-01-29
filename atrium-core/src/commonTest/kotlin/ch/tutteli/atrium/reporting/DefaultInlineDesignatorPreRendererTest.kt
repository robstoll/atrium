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
import ch.tutteli.atrium.reporting.reportables.Diagnostic
import ch.tutteli.atrium.reporting.reportables.InlineElement
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
            Diagnostic.inlineGroup(listOf(Text("bli"))),
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

    @TestFactory
    fun canTransform_notInlineElement_false() = testFactory {
        val preRenderer = buildDefaultInlineDesignatorPreRenderer()
        val simpleProof = Proof.simple(Text("bla"), "rep") { false }
        val column = Diagnostic.column(Text("bla"), HorizontalAlignment.LEFT)
        val nonInlineElements = listOf(
            Diagnostic.debugGroup(Text("bla"), listOf(simpleProof)),
            Diagnostic.failureExplanationGroup(Text("bli"), listOf(simpleProof)),
            Diagnostic.group(Text("bla"), "rep", children = listOf(simpleProof)),
            Diagnostic.informationGroup(Text("info"), listOf(simpleProof)),
            Diagnostic.proofExplanation(simpleProof),
            Diagnostic.usageHintGroup(listOf(simpleProof)),
            Diagnostic.row(null, includingBorder = false, listOf(column)),
            column,
        )
        nonInlineElements.forEach {
            it("${it::class.simpleName}") {
                expect(preRenderer) feature of(DefaultInlineDesignatorPreRenderer::canTransform, it) toEqual false
            }
        }
    }


    @OptIn(ExperimentalComponentFactoryContainer::class)
    private fun buildDefaultInlineDesignatorPreRenderer() =
        DefaultInlineDesignatorPreRenderer(DefaultComponentFactoryContainer.build())
}
