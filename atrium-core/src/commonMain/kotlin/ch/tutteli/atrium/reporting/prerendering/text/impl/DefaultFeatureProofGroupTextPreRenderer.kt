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
            // we use an empty string as additional colum because we want to indent the children by 2 and still
            // span the description over the indent + prefix
            prefixDescriptionColumns = listOf(iconStyler.style(Icon.FEATURE), StyledString.EMPTY_STRING),
            suffixDescriptionColumns = listOf(),
            startMergeAtColumn = 1 // because we have a prefix from the parent group
        ) { child ->
            val newControlObject = determineChildControlObject(
                controlObject,
                child,
                Icon.LIST_BULLET_POINT,
                // indent by two because we want that the children are after Icon.FEATURE
                // (1 additional indent for the x prefix of the feature group
                additionalIndent = 2
            )
            controlObject.transformChildIncludingIndentationAndPrefix(child, newControlObject)
        }
}
