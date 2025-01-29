package ch.tutteli.atrium.reporting.prerendering.text

import ch.tutteli.atrium.assertions.ExplanatoryAssertion
import ch.tutteli.atrium.creating.proofs.InvisibleFailingProof
import ch.tutteli.atrium.creating.proofs.InvisibleLikeProofGroup
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
        description: Diagnostic,
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
    diagnosticGroupWithDescription: DiagnosticGroupWithDescription,
    controlObject: TextPreRenderControlObject,
    prefixDescriptionColumns: List<StyledString> = emptyList(),
    suffixDescriptionColumns: List<StyledString> = emptyList(),
    startMergeAtColumn: Int = 0,
    childTransformer: (child: Reportable) -> List<OutputNode>,
) = transformGroup(
    diagnosticGroupWithDescription.description,
    Text.EMPTY,
    controlObject,
    diagnosticGroupWithDescription.children.flatMap(childTransformer),
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
                takeIf(
                    // we don't override the icon for invisible like proof groups
                    child !is InvisibleLikeProofGroup &&
                        @Suppress("DEPRECATION")
                        run {
                            child !is ch.tutteli.atrium.assertions.AssertionGroup ||
                                child.type !is ch.tutteli.atrium.assertions.InvisibleAssertionGroupType
                        }
                ) {
                    val icon = when {
                        child.holds() -> Icon.SUCCESS

                        // we want to use the FAILING_GROUP icon in case it is a ProofGroup, and it contains at least
                        // one child (direct or indirect) which we treat as FAILING_GROUP or FAILURE.
                        // As a general rule, a ProofGroup contains at least one Proof which in turn means we can
                        // always use FAILING_GROUP if the child is a ProofGroup but there is one exception to the rule:
                        // InvisibleFailingProofGroup
                        // It implements a ProofGroup which always fails (holds=false) but:
                        // a) it only contains diagnostics (no proofs)
                        // b) it is invisible -> i.e a ProofGroup whose children of type Proof are all
                        //    InvisibleFailingProofGroup might not contain at least one child which we treat as
                        //    FAILING_GROUP or FAILURE
                        // c) There are diagnostics which contain Proofs themselves, and sometimes we want to treat
                        //    those as FAILURE in reporting, in which case the ProofGroup containing the
                        //    InvisibleFailingProofGroup has at least one child which we treat as FAILURE and in turn
                        //    should use the Icon.FAILING_GROUP
                        child is ProofGroup && child.treeContainsAtLeastOneLeafProof() -> Icon.FAILING_GROUP

                        else -> Icon.FAILURE
                    }
                    controlObject.copy(prefix = icon, indentLevel = indentLevel)
                }
            }

            // we don't override the icon for non-proofs
            else -> null
        }
    }
    return newControlObject ?: controlObject.copy(prefix = childPrefix, indentLevel = indentLevel)
}

/**
 * Indicates whether this or a nested group contains at least one leaf proof.
 *
 * A [ProofGroup] contains at least one [Proof] with the exception of [InvisibleFailingProof]
 *
 * @returns true if this group or a nested group contains a leaf proof, otherwise false
 */
//TODO 1.4.0 Reportables shouldn't be nested too deeply but we could optimise this and turn it into a tailrec function
fun ReportableGroup.treeContainsAtLeastOneLeafProof(): Boolean =
    children.any { child ->
        run {
            child is Proof &&
                // if the child is not a ProofGroup, then it is a leaf proof for sure, we can stop here,
                // this ReportableGroup has a leaf proof
                (child !is ProofGroup
                    // if it is not an invisible like proof group, then we can stop as well, because the child is either
                    // an intermediate proof group or a leaf proof itself.
                    || child !is InvisibleLikeProofGroup
                    // for invisible like proof group we need to check if they contain a proof
                    || child.treeContainsAtLeastOneLeafProof())
        } || run {
            // TODO 1.3.0 this seems buggy, we should not check this on the initial treeContainsAtLeastOneLeafProof call
            child is FailureExplanationGroup && child.treeContainsAtLeastOneLeafProof()
        } || run {
            // if a child is a proof explanation, then we only want to show the flag in case the proof explanation
            // contains a failure explanation, because the failure explanation might have a leave proof as well
            // see CreateReportTest -> proofExplanation_withFailureExplanation for a case where a ProofExplanation
            // contains a FailureExplanation but the FailureExplanation does not contain Proofs => i.e. don't use the
            // flag
            // see CreateReportTest -> proofExplanation_withFailureExplanationAndSubProofs_showsOnlyFailingProofs for
            // a case where a ProofExplanation contains a FailureExplanation and it in turn contains a Proof
            child is ProofExplanation && child.children.any { it is FailureExplanationGroup && it.treeContainsAtLeastOneLeafProof() }
        }
    }

