//TODO 2.0.0 remove file
@file:Suppress("DEPRECATION")
package ch.tutteli.atrium.reporting.prerendering.text.impl.assertion

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.reporting.filters.ReportableFilter
import ch.tutteli.atrium.reporting.prerendering.text.OutputNode
import ch.tutteli.atrium.reporting.prerendering.text.TextPreRenderControlObject
import ch.tutteli.atrium.reporting.prerendering.text.TextPreRenderer
import ch.tutteli.atrium.reporting.reportables.Icon
import ch.tutteli.atrium.reporting.reportables.Reportable

@Deprecated("Switch from Assertion to Proof, was introduced to ease the migration to Proof, will be removed with 2.0.0 at the latest")
class DefaultExplanatoryAssertionGroupTextPreRenderer : TextPreRenderer {
    override fun canTransform(reportable: Reportable): Boolean =
        reportable is AssertionGroup && reportable.type is ExplanatoryAssertionGroupType

    override fun transform(
        reportable: Reportable,
        controlObject: TextPreRenderControlObject
    ): List<OutputNode> = (reportable as AssertionGroup).let { assertion ->
        val (icon, additionalIndent) = when (val type = assertion.type) {
            is DefaultExplanatoryAssertionGroupType -> Icon.PROOF_EXPLANATION_BULLET_POINT to 1
            is WarningAssertionGroupType -> Icon.BANGBANG to 1
            is InformationAssertionGroupType -> Icon.INFORMATION_SOURCE to if (type.withIndent) 1 else 0
            is HintAssertionGroupType -> Icon.BULB to 1
            else -> throw UnsupportedOperationException("Unsupported assertionGroupType $type")
        }

        listOf(
            OutputNode(
                columns = emptyList(),
                children = assertion.assertions.asSequence().flatMap { child ->
                    val newControlObject = controlObject.copy(
                        prefix = icon,
                        indentLevel = controlObject.indentLevel + additionalIndent,
                        reportableFilter = ReportableFilter.includeAll(),
                        explainsProof = true
                    )
                    controlObject.transformChildIncludingIndentationAndPrefix(child, newControlObject)
                }.toList(),
                definesOwnLevel = true,
                usesOwnPrefix = true
            )
        )
    }
}
