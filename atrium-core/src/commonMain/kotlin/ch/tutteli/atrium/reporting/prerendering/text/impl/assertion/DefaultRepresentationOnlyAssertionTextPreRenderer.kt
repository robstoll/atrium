//TODO 2.0.0 remove file
@file:Suppress("DEPRECATION")
package ch.tutteli.atrium.reporting.prerendering.text.impl.assertion

import ch.tutteli.atrium.assertions.RepresentationOnlyAssertion
import ch.tutteli.atrium.reporting.text.TextObjFormatter
import ch.tutteli.atrium.reporting.prerendering.text.TextPreRenderControlObject
import ch.tutteli.atrium.reporting.prerendering.text.OutputNode
import ch.tutteli.atrium.reporting.prerendering.text.TypedTextPreRenderer

@Deprecated("Switch from Assertion to Proof, was introduced to ease the migration to Proof, will be removed with 2.0.0 at the latest")
class DefaultRepresentationOnlyAssertionTextPreRenderer(private val objFormatter: TextObjFormatter) :
    TypedTextPreRenderer<RepresentationOnlyAssertion>(RepresentationOnlyAssertion::class) {
    override fun transformIt(
        reportable: RepresentationOnlyAssertion,
        controlObject: TextPreRenderControlObject
    ): List<OutputNode> = listOf(
        OutputNode(
            arrayOf(objFormatter.format(reportable.representation)).asList(),
            children = emptyList(),
            definesOwnLevel = true
        )
    )
}
