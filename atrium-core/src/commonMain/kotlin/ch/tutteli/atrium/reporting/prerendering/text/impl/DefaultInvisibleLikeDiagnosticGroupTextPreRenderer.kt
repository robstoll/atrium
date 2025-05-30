package ch.tutteli.atrium.reporting.prerendering.text.impl

import ch.tutteli.atrium.reporting.prerendering.text.OutputNode
import ch.tutteli.atrium.reporting.prerendering.text.TextPreRenderControlObject
import ch.tutteli.atrium.reporting.prerendering.text.TypedTextPreRenderer
import ch.tutteli.atrium.reporting.prerendering.text.determineChildControlObject
import ch.tutteli.atrium.reporting.reportables.InvisibleLikeDiagnosticGroup

internal class DefaultInvisibleLikeDiagnosticGroupTextPreRenderer :
    TypedTextPreRenderer<InvisibleLikeDiagnosticGroup>(InvisibleLikeDiagnosticGroup::class) {

    override fun transformIt(
        reportable: InvisibleLikeDiagnosticGroup,
        controlObject: TextPreRenderControlObject
    ): List<OutputNode> = OutputNode.singleWithoutColumnsNotOwnLevel(
        children = reportable.children.flatMap { child ->
            val newControlObject = determineChildControlObject(controlObject, child, controlObject.prefix)
            controlObject.transformChildIncludingIndentationAndPrefix(child, newControlObject)
        }
    )

}

