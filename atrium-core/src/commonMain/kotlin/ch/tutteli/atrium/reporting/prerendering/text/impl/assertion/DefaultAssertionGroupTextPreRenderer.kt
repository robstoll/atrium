//TODO 2.0.0 remove file
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.reporting.prerendering.text.impl.assertion

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.filters.ReportableFilter
import ch.tutteli.atrium.reporting.prerendering.text.*
import ch.tutteli.atrium.reporting.reportables.Icon
import ch.tutteli.atrium.reporting.reportables.Reportable
import ch.tutteli.atrium.reporting.theming.text.TextIconStyler

@Deprecated("Switch from Assertion to Proof, was introduced to ease the migration to Proof, will be removed with 2.0.0 at the latest")
class DefaultAssertionGroupTextPreRenderer(private val iconStyler: TextIconStyler) : TextPreRenderer {
    override fun canTransform(reportable: Reportable): Boolean = reportable is AssertionGroup

    override fun transform(reportable: Reportable, controlObject: TextPreRenderControlObject): List<OutputNode> =
        (reportable as AssertionGroup).let { assertionGroup ->
            when (assertionGroup.type) {
                is RootAssertionGroupType -> controlObject.transformGroup(reportable, controlObject) { child ->
                    val newControlObject = determineChildControlObject(controlObject, child, Icon.ROOT_BULLET_POINT)
                    controlObject.transformChildIncludingIndentationAndPrefix(child, newControlObject)
                }.let {
                    // usually a group has a prefix icon (or an own prefix) in which case, for root we get an empty_string as
                    // icon but we don't want to show it as column, instead this first column shall be merged with the columns
                    // needed for the description
                    val first = it.first()
                    (sequenceOf(first.copy(mergeColumns = first.mergeColumns + 1)) + it.asSequence()
                        .drop(1)).toList()
                }

                is InvisibleAssertionGroupType ->
                    controlObject.transformChild(Proof.invisibleGroup(assertionGroup.assertions), controlObject)

                is FeatureAssertionGroupType -> controlObject.transformSubProofGroup(
                    reportable,
                    controlObject,
                    prefixDescriptionColumns = listOf(iconStyler.style(Icon.FEATURE)),
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

                is ListAssertionGroupType ->
                    if (controlObject.explainsProof && assertionGroup.representation != Text.EMPTY) {
                        // if we are in an ExplanatoryAssertionGroup, and the listGroup has a representation,
                        // then we want to show it in reporting
                        controlObject.transformSubProofGroup(reportable, controlObject.copy(explainsProof = false)) { child ->
                            val newControlObject = determineChildControlObject(
                                controlObject,
                                child,
                                Icon.LIST_BULLET_POINT,
                                additionalIndent = 1
                            )
                            controlObject.transformChildIncludingIndentationAndPrefix(child, newControlObject)
                        }
                    } else {
                        controlObject.transformSubProofGroup(reportable, controlObject, Icon.LIST_BULLET_POINT)
                    }

                is SummaryAssertionGroupType -> controlObject.transformSubProofGroup(
                    reportable,
                    controlObject
                ) { child ->
                    val newControlObject = determineChildControlObject(
                        controlObject,
                        child,
                        Icon.LIST_BULLET_POINT,
                        additionalIndent = 1,
                    ).copy(reportableFilter = ReportableFilter.includeAll())
                    controlObject.transformChildIncludingIndentationAndPrefix(child, newControlObject)
                }

                is GroupingAssertionGroupType ->
                    controlObject.transformSubProofGroup(reportable, controlObject, Icon.GROUPING_BULLET_POINT)

                else -> throw UnsupportedOperationException("Unsupported assertionGroupType ${assertionGroup.type}")
            }
        }
}
