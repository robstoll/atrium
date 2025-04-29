package ch.tutteli.atrium.reporting.prerendering.text.impl

import ch.tutteli.atrium.creating.proofs.FeatureProofGroup
import ch.tutteli.atrium.reporting.prerendering.text.*
import ch.tutteli.atrium.reporting.prerendering.text.TypedTextPreRenderer
import ch.tutteli.atrium.reporting.reportables.Icon
import ch.tutteli.atrium.reporting.theming.text.StyledString
import ch.tutteli.atrium.reporting.theming.text.TextIconStyler

internal class DefaultFeatureProofGroupTextPreRenderer(
    private val iconStyler: TextIconStyler
) : TypedTextPreRenderer<FeatureProofGroup>(FeatureProofGroup::class) {

    override fun transformIt(
        reportable: FeatureProofGroup,
        controlObject: TextPreRenderControlObject
    ): List<OutputNode> =
        controlObject.transformSubProofGroup(
            reportable,
            controlObject,
            prefixDescriptionColumns = listOf(iconStyler.style(Icon.FEATURE)),
            suffixDescriptionColumns = listOf(),
        ) { child ->
            val newControlObject = determineChildControlObject(
                controlObject,
                child,
                Icon.LIST_BULLET_POINT,
                // indent by two, one for the Icon.FAILING_GROUP Icon (at the time of writing 🚩) and one for the
                // Icon.FEATURE (we want that the children are after Icon.FEATURE)
                additionalIndent = 2
            )
            controlObject.transformChildIncludingIndentationAndPrefix(child, newControlObject)
        }
}
