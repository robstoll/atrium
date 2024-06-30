package ch.tutteli.atrium.reporting.prerendering.text

import ch.tutteli.atrium.reporting.reportables.Reportable
import ch.tutteli.atrium.reporting.theming.text.StyledString

// TODO 1.3.0 KDOC
interface TextDesignationPreRenderer {
    fun canTransform(description: Reportable): Boolean
    fun transform(
        description: Reportable,
        representation: Any,
        prefixDescriptionColumns: List<StyledString>,
        suffixDescriptionColumns: List<StyledString>,
        startMergeAtColumn: Int,
        children: List<OutputNode>,
        controlObject: TextPreRenderControlObject,
    ): List<OutputNode>
}
