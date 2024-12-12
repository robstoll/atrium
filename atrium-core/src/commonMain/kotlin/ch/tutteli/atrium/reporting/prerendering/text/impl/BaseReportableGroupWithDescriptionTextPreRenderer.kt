package ch.tutteli.atrium.reporting.prerendering.text.impl

import ch.tutteli.atrium.reporting.prerendering.text.*
import ch.tutteli.atrium.reporting.reportables.Icon
import ch.tutteli.atrium.reporting.reportables.ReportableGroupWithDescription
import ch.tutteli.atrium.reporting.theming.text.StyledString
import ch.tutteli.atrium.reporting.theming.text.TextIconStyler
import kotlin.reflect.KClass

internal abstract class BaseReportableGroupWithDescriptionTextPreRenderer<R : ReportableGroupWithDescription>(
    kClass: KClass<R>,
    private val iconStyler: TextIconStyler,
    private val groupIcon: Icon,
    private val childIcon: Icon
) : TypedTextPreRenderer<R>(kClass) {
    override fun transformIt(
        reportable: R,
        controlObject: TextPreRenderControlObject
    ): List<OutputNode> =
        controlObject.transformGroup(
            reportable,
            controlObject,
            // we add an empty string as additional colum because we want that description of the group spans over
            // two columns, this way children's prefix icon will be in the first column and their description in the second
            // allowing that they are aligned again (otherwise the icon is in the same column as the description)
            prefixDescriptionColumns = listOf(iconStyler.style(groupIcon), StyledString.EMPTY_STRING),
        ) { child ->
            val newControlObject = determineChildControlObject(
                controlObject, child, childIcon, additionalIndent = 1
            ).let {
                if (it.explainsProof) it.copy(explainsProof = false) else it
            }
            controlObject.transformChildIncludingIndentationAndPrefix(child, newControlObject)
        }.let {
            listOf(it.first().copy(usesOwnPrefix = true)) + it.drop(1)
        }
}
