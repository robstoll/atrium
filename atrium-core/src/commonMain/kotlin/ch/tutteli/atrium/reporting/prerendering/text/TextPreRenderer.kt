package ch.tutteli.atrium.reporting.prerendering.text

import ch.tutteli.atrium.reporting.reportables.Reportable

//TODO 1.3.0 in case StyledString could also be used for other output formats, then we could abstract this to PreRenderer
interface TextPreRenderer {
    fun canTransform(reportable: Reportable): Boolean
    fun transform(reportable: Reportable, controlObject: TextPreRenderControlObject): List<OutputNode>
}
