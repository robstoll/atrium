package ch.tutteli.atrium.creating.proofs.basic.contains.creators.impl

import ch.tutteli.atrium.creating.proofs.basic.contains.ToContain.Checker
import ch.tutteli.atrium.creating.proofs.basic.contains.ToContain.SearchBehaviour
import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.creating.proofs.ProofGroup
import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.creating.proofs.basic.contains.ToContain
import ch.tutteli.atrium.creating.proofs.builders.buildProof
import ch.tutteli.atrium.creating.proofs.impl.createProofGroupFromReportables
import ch.tutteli.atrium.reporting.reportables.Description
import ch.tutteli.atrium.reporting.reportables.Reportable
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionIterableLikeProof

/**
 * Represents the base class for [ToContain.Creator]s which use bare objects as search criteria (matching them
 * with `==`).
 *
 * It provides a template to fulfill the job of creating the sophisticated `contains` [Proof].
 *
 * @param SubjectT The type of the subject of this expectation.
 * @param SubjectMultiConsumableT The type of the subject of this expectation after making it multiple times consumable.
 * @param SearchCriteriaT The type of the search criteria.
 * @param SearchBehaviourT The type of the current [ToContain.SearchBehaviour].
 * @param CheckerT The type of the checkers in use (typically a sub interface of [ToContain.Checker]).
 *
 * @property searchBehaviour The chosen search behaviour.
 *
 * @constructor Represents the base class for [ToContain.Creator]s which use bare objects as search criteria (matching them
 *   with `==`).
 * @param searchBehaviour The chosen search behaviour.
 * @param checkers The [ToContain.Checker]s which shall be applied to the search result.
 */
abstract class ToContainObjectsProofCreator<SubjectT : Any, SubjectMultiConsumableT : Any, in SearchCriteriaT, SearchBehaviourT : SearchBehaviour, CheckerT : Checker>(
    searchBehaviour: SearchBehaviourT,
    checkers: List<CheckerT>
) : ToContainProofCreator<SubjectT, SubjectMultiConsumableT, SearchCriteriaT, CheckerT>(searchBehaviour, checkers) {

    override fun searchAndCreateProof(
        multiConsumableContainer: ProofContainer<SubjectMultiConsumableT>,
        searchCriterion: SearchCriteriaT,
        featureFactory: (ProofContainer<SubjectMultiConsumableT>, Int, Description) -> Proof
    ): Proof {
        val reportables = mutableListOf<Reportable>()
        //TODO 1.3.0 change as soon as Iterable is migrated as well
        if (false){//searchBehaviour is IterableNotSearchBehaviour) {
            val mismatches = mismatchesForNotSearchBehaviour(multiConsumableContainer, searchCriterion)
            if (mismatches.isNotEmpty()) reportables.add(
                multiConsumableContainer.createFailureExplanationForMismatches(mismatches)
            )
        } else {
            val count = search(multiConsumableContainer, searchCriterion)
            val featureProof = featureFactory(multiConsumableContainer, count, descriptionNumberOfOccurrences)
            reportables.add(featureProof)
        }

        return multiConsumableContainer.createProofGroupFromReportables(groupDescription, searchCriterion, reportables)
    }

    /**
     * Provides the [Description] for `number of occurrences`.
     */
    protected abstract val descriptionNumberOfOccurrences: Description

    /**
     * Provides the [Description] for [ch.tutteli.atrium.creating.proofs.ProofGroupWithDesignation.description].
     */
    protected abstract val groupDescription: Description


    /**
     * Searches for something matching the given [searchCriterion] in the subject of the given
     * [multiConsumableContainer] and returns the number of occurrences.
     *
     * @param multiConsumableContainer The provider of the subject of this expectation in which we shall look for something
     *   matching the given [searchCriterion].
     * @param searchCriterion The search criterion used to determine whether something matches or not.
     *
     * @return The number of times the [searchCriterion] matched in the subject of this expectation.
     */
    protected abstract fun search(
        multiConsumableContainer: ProofContainer<SubjectMultiConsumableT>,
        searchCriterion: SearchCriteriaT
    ): Int

    /**
     * Finds the mismatched indices and values when the [searchBehaviour] is `NotSearchBehaviour` in the subject of the
     * given [multiConsumableContainer] and creates a list of assertions about the mismatched indexed values
     *
     * @param multiConsumableContainer The provider of the subject of this expectation in which we shall look for something
     *   not matching the given [searchCriterion].
     * @param searchCriterion The search criterion used to determine whether something matches or not.
     *
     * @return A list of [Proof]s that describe the indexed values that did not match the [searchCriterion]
     */
    protected open fun mismatchesForNotSearchBehaviour(
        multiConsumableContainer: ProofContainer<SubjectMultiConsumableT>,
        searchCriterion: SearchCriteriaT
    ): List<Proof> = emptyList()
}

internal fun ProofContainer<*>.createFailureExplanationForMismatches(
    mismatches: List<Proof>
): Proof = buildProof {
    invisibleFailingProofGroup {
        failureExplanationGroup(DescriptionIterableLikeProof.WARNING_MISMATCHES) {
            addAll(mismatches)
        }
    }
}
