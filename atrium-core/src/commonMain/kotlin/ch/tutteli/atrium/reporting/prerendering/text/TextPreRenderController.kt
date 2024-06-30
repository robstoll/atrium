package ch.tutteli.atrium.reporting.prerendering.text

import ch.tutteli.atrium.assertions.ExplanatoryAssertion
import ch.tutteli.atrium.creating.proofs.InvisibleProofGroup
import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.reporting.reportables.Icon
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.reportables.Reportable
import ch.tutteli.atrium.reporting.reportables.ReportableGroup
import ch.tutteli.atrium.reporting.reportables.ReportableGroupWithDescription
import ch.tutteli.atrium.reporting.reportables.ReportableWithDesignation
import ch.tutteli.atrium.reporting.theming.text.StyledString
import ch.tutteli.kbox.takeIf

// TODO 1.3.0 KDOC
interface TextPreRenderController {
    // TODO 1.3.0 do we ever use the indentNewline?
    fun transformRoot(reportable: Reportable, indentNewLine: Boolean = true): List<OutputNode>

    fun transformChildIncludingIndentationAndPrefix(
        child: Reportable,
        controlObject: TextPreRenderControlObject
    ): List<OutputNode>

    fun transformChild(child: Reportable, controlObject: TextPreRenderControlObject): List<OutputNode>

    fun transformGroup(
        description: Reportable,
        representation: Any,
        controlObject: TextPreRenderControlObject,
        children: List<OutputNode>,
        prefixDescriptionColumns: List<StyledString> = emptyList(),
        suffixDescriptionColumns: List<StyledString> = emptyList(),
        startMergeAtColumn: Int = 0,
    ): List<OutputNode>
}

// TODO 1.3.0 KDOC
fun TextPreRenderController.transformGroup(
    reportableGroupWithDescription: ReportableGroupWithDescription,
    controlObject: TextPreRenderControlObject,
    prefixDescriptionColumns: List<StyledString> = emptyList(),
    suffixDescriptionColumns: List<StyledString> = emptyList(),
    startMergeAtColumn: Int = 0,
    childTransformer: (child: Reportable) -> List<OutputNode>,
) = transformGroup(
    reportableGroupWithDescription.description,
    Text.EMPTY,
    controlObject,
    reportableGroupWithDescription.children.flatMap(childTransformer),
    prefixDescriptionColumns,
    suffixDescriptionColumns,
    startMergeAtColumn
)

// TODO 1.3.0 KDOC
fun <T> TextPreRenderController.transformSubProofGroup(
    reportableGroupWithDesignation: T,
    controlObject: TextPreRenderControlObject,
    icon: Icon
) where T : ReportableGroup, T : ReportableWithDesignation =
    transformSubProofGroup(reportableGroupWithDesignation, controlObject) { child ->
        val newControlObject = determineChildControlObject(controlObject, child, icon, additionalIndent = 1)
        controlObject.transformChildIncludingIndentationAndPrefix(child, newControlObject)
    }

// TODO 1.3.0 KDOC
fun <T> TextPreRenderController.transformSubProofGroup(
    reportableGroupWithDesignation: T,
    controlObject: TextPreRenderControlObject,
    prefixDescriptionColumns: List<StyledString> = listOf(StyledString.EMPTY_STRING),
    suffixDescriptionColumns: List<StyledString> = emptyList(),
    startMergeAtColumn: Int = 1,
    childTransformer: (child: Reportable) -> List<OutputNode>,
) where T : ReportableGroup, T : ReportableWithDesignation =
    transformGroup(
        reportableGroupWithDesignation,
        controlObject,
        prefixDescriptionColumns,
        suffixDescriptionColumns,
        startMergeAtColumn,
        childTransformer
    )

// TODO 1.3.0 KDOC
fun <T> TextPreRenderController.transformGroup(
    reportableGroupWithDesignation: T,
    controlObject: TextPreRenderControlObject,
    prefixDescriptionColumns: List<StyledString> = emptyList(),
    suffixDescriptionColumns: List<StyledString> = emptyList(),
    startMergeAtColumn: Int = 0,
    childTransformer: (child: Reportable) -> List<OutputNode>,
) where T : ReportableGroup, T : ReportableWithDesignation = transformGroup(
    reportableGroupWithDesignation.description,
    reportableGroupWithDesignation.representation,
    controlObject,
    reportableGroupWithDesignation.children.flatMap(childTransformer),
    prefixDescriptionColumns,
    suffixDescriptionColumns,
    startMergeAtColumn
)

// TODO 1.3.0 KDOC
fun TextPreRenderController.transformGroup(
    reportableWithDesignation: ReportableWithDesignation,
    controlObject: TextPreRenderControlObject,
    prefixDescriptionColumns: List<StyledString> = emptyList(),
    suffixDescriptionColumns: List<StyledString> = emptyList(),
    startMergeAtColumn: Int = 0,
) = transformGroup(
    reportableWithDesignation.description,
    reportableWithDesignation.representation,
    controlObject,
    children = emptyList(),
    prefixDescriptionColumns,
    suffixDescriptionColumns,
    startMergeAtColumn
)

// TODO 1.3.0 KDOC
fun determineChildControlObject(
    controlObject: TextPreRenderControlObject,
    child: Reportable,
    childPrefix: Icon,
    additionalIndent: Int = 0
): TextPreRenderControlObject {
    val indentLevel = controlObject.indentLevel + additionalIndent

    // we don't use failure/success icons in case we explain a proof
    val newControlObject = takeIf(controlObject.explainsProof.not()) {
        when (child) {
            //TODO remove with 2.0.0 latest
            is ExplanatoryAssertion -> null

            is Proof -> {
                @Suppress("DEPRECATION")
                // TODO 1.3.0 what about InvisibleFixedClaimGroup? In case we keep it we need to address it here as well
                // I guess. create a test in CreateReportTest (similar to invisibleGroup_...)
                if (child is InvisibleProofGroup ||
                    //TODO remove with 2.0.0 latest and with it the above @Suppress
                    child is ch.tutteli.atrium.assertions.AssertionGroup && child.type is ch.tutteli.atrium.assertions.InvisibleAssertionGroupType
                ) {
                    null
                } else
                    if (child.holds()) {
                        controlObject.copy(prefix = Icon.SUCCESS, indentLevel = indentLevel)
                    } else {
                        controlObject.copy(prefix = Icon.FAILURE, indentLevel = indentLevel)
                    }
            }

            else -> null
        }
    }
    return newControlObject ?: controlObject.copy(prefix = childPrefix, indentLevel = indentLevel)
}
