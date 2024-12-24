package ch.tutteli.atrium.reporting.prerendering.text

import ch.tutteli.atrium.assertions.ExplanatoryAssertion
import ch.tutteli.atrium.creating.proofs.InvisibleFixedClaimGroup
import ch.tutteli.atrium.creating.proofs.InvisibleProofGroup
import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.creating.proofs.ProofGroup
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.reportables.*
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
    prefixDescriptionColumns: List<StyledString> = listOf(),
    suffixDescriptionColumns: List<StyledString> = emptyList(),
    startMergeAtColumn: Int = 1,
    childTransformer: (child: Reportable) -> List<OutputNode>,
) where T : ReportableGroup, T : ReportableWithDesignation =
    transformGroup(
        reportableGroupWithDesignation,
        controlObject,
        // we add an empty string as additional colum because we want that description of the group spans over
        // two columns, this way children's prefix icon will be in the first column and their description in the second
        // allowing that they are aligned again (otherwise the icon is in the same column as the description)
        prefixDescriptionColumns + listOf(StyledString.EMPTY_STRING),
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
                // we don't override the icon for invisible groups
                // TODO 1.3.0 note sure the logic for InvisibleFixedClaimGroup, is correct. In case we keep it we
                // should create a test in CreateReportTest
                takeIf(
                    child !is InvisibleProofGroup && //child !is InvisibleFixedClaimGroup &&
                        @Suppress("DEPRECATION")
                        run {
                            child !is ch.tutteli.atrium.assertions.AssertionGroup || child.type !is ch.tutteli.atrium.assertions.InvisibleAssertionGroupType
                        }
                ) {
                    if (child.holds()) {
                        controlObject.copy(prefix = Icon.SUCCESS, indentLevel = indentLevel)
                    } else if (
                        // a ProofGroup contains always at least one Proof but it could also be an
                        // InvisibleFixedClaimGroup which is a bit a pseudo proof, in such a case we need to check
                        // if there
                        child is ProofGroup && child.hasAtLeastOneLeaveProof()) {
                        controlObject.copy(prefix = Icon.FAILING_GROUP, indentLevel = indentLevel)
                    } else {
                        controlObject.copy(prefix = Icon.FAILURE, indentLevel = indentLevel)
                    }
                }
            }

            // we don't override the icon for non-proofs
            else -> null
        }
    }
    return newControlObject ?: controlObject.copy(prefix = childPrefix, indentLevel = indentLevel)
}

fun ReportableGroup.hasAtLeastOneLeaveProof(): Boolean =
    children.any { child ->
        run {
            child is Proof &&
                (child !is ProofGroup || (child !is InvisibleProofGroup && child !is InvisibleFixedClaimGroup || child.hasAtLeastOneLeaveProof()))
        } || run {
            child is FailureExplanationGroup && child.hasAtLeastOneLeaveProof()
        } || run {
            // if a child is a proof explanation, then we only want to show the flag in case the proof explanation
            // contains a failure explanation, because the failure explanation might have a leave proof as well
            // see CreateReportTest -> proofExplanation_withFailureExplanation for a case where a ProofExplanation
            // contains a FailureExplanation but the FailureExplanation does not contain Proofs => i.e. don't use the
            // flag
            // see CreateReportTest -> proofExplanation_withFailureExplanationAndSubProofs_showsOnlyFailingProofs for
            // a case where a ProofExplanation contains a FailureExplanation and it in turn contains a Proof
            child is ProofExplanation && child.children.any { it is FailureExplanationGroup && it.hasAtLeastOneLeaveProof() }
        }
    }

