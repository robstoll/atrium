package ch.tutteli.atrium.reporting.prerendering.text.impl

import ch.tutteli.atrium.creating.proofs.RootProofGroup
import ch.tutteli.atrium.reporting.prerendering.text.*
import ch.tutteli.atrium.reporting.prerendering.text.TypedTextPreRenderer
import ch.tutteli.atrium.reporting.reportables.Icon

internal class DefaultRootProofGroupTextPreRenderer : TypedTextPreRenderer<RootProofGroup>(RootProofGroup::class) {

    override fun transformIt(reportable: RootProofGroup, controlObject: TextPreRenderControlObject): List<OutputNode> =
        controlObject.transformGroup(reportable, controlObject) { child ->
            val newControlObject = determineChildControlObject(controlObject, child, Icon.ROOT_BULLET_POINT)
            controlObject.transformChildIncludingIndentationAndPrefix(child, newControlObject)
        }.let {
            // usually a group has a prefix icon (or an own prefix) but the root group uses an empty_string as
            // icon. Of course, we don't want to show it as column, instead this first column shall be merged with
            // the columns needed for the description of the root group.
            val first = it.first()
            (sequenceOf(first.copy(mergeColumns = first.mergeColumns + 1)) + it.asSequence().drop(1)).toList()
        }
}
