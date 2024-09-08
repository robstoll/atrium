package ch.tutteli.atrium.reporting.prerendering.text.impl

import ch.tutteli.atrium.creating.proofs.InvisibleProofGroup
import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.reporting.reportables.Icon
import ch.tutteli.atrium.reporting.prerendering.text.TextPreRenderControlObject
import ch.tutteli.atrium.reporting.prerendering.text.determineChildControlObject
import ch.tutteli.atrium.reporting.prerendering.text.OutputNode
import ch.tutteli.atrium.reporting.prerendering.text.TypedTextPreRenderer
import ch.tutteli.atrium.reporting.theming.text.TextIconStyler

internal class DefaultInvisibleProofGroupTextPreRenderer(
    private val iconStyler: TextIconStyler
) : TypedTextPreRenderer<InvisibleProofGroup>(InvisibleProofGroup::class) {
    override fun transformIt(
        reportable: InvisibleProofGroup,
        controlObject: TextPreRenderControlObject
    ): List<OutputNode> =
        reportable.children.flatMap { child ->
            val newControlObject =
                determineChildControlObject(controlObject, child, childPrefix = controlObject.prefix)

            controlObject.transformChild(child, newControlObject).map {
                //TODO 1.3.0 adjusting children after transformation is fishy, maybe better use the approach we took with ExplanatoryAssertionGroup, i.e. create an OutputNode without columns
                // this way we keep the grouping we introduced with usageGroup also here
                // What if the child had defined his own prefix as well, no we override it again, of course, we could check that
                // here instead of blindly set usesOwnPrefix -- not sure yet what is better, an OutputNode with empty columns introduces other complexities
                if (child is Proof) it.copy(
                    columns = listOf(iconStyler.style(if (child.holds()) Icon.SUCCESS else Icon.FAILURE)) + it.columns,
                    usesOwnPrefix = true
                ) else it
            }
        }
}
