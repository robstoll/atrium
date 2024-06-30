package ch.tutteli.atrium.reporting.prerendering.text.impl

import ch.tutteli.atrium.reporting.prerendering.text.*
import ch.tutteli.atrium.reporting.reportables.Icon
import ch.tutteli.atrium.reporting.reportables.ReportableGroupWithDescription
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
            prefixDescriptionColumns = listOf(iconStyler.style(groupIcon)),
        ) { child ->
            val newControlObject = determineChildControlObject(
                controlObject, child, childIcon, additionalIndent = 1
            ).let {
                if(it.explainsProof) it.copy(explainsProof = false) else it
            }
            controlObject.transformChildIncludingIndentationAndPrefix(child, newControlObject)
        }.let {
            listOf(it.first().copy(usesOwnPrefix = true)) + it.drop(1)
        }
}
