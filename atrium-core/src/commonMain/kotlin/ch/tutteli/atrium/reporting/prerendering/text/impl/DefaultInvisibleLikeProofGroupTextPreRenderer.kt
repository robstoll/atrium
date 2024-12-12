package ch.tutteli.atrium.reporting.prerendering.text.impl

import ch.tutteli.atrium.creating.proofs.InvisibleFixedClaimGroup
import ch.tutteli.atrium.creating.proofs.InvisibleProofGroup
import ch.tutteli.atrium.creating.proofs.ProofGroup
import ch.tutteli.atrium.reporting.prerendering.text.*
import ch.tutteli.atrium.reporting.reportables.Reportable

internal class DefaultInvisibleLikeProofGroupTextPreRenderer : TextPreRenderer {


    override fun canTransform(reportable: Reportable): Boolean =
        reportable is InvisibleProofGroup ||
            reportable is InvisibleFixedClaimGroup ||
            @Suppress("DEPRECATION")
            run {
                reportable is ch.tutteli.atrium.assertions.InvisibleAssertionGroup
            }

    override fun transform(
        reportable: Reportable,
        controlObject: TextPreRenderControlObject
    ): List<OutputNode> =
        OutputNode.singleWithoutColumnsNotOwnLevel(
            children = (reportable as ProofGroup).children.flatMap { child ->
                val newControlObject = determineChildControlObject(controlObject, child, controlObject.prefix)
                controlObject.transformChildIncludingIndentationAndPrefix(child, newControlObject)
            }
        )

}

