package ch.tutteli.atrium.creating.proofs.basic.contains.creators.impl

import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.assertions.builders.withExplanatoryAssertion
import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.creating.proofs.ProofGroup
import ch.tutteli.atrium.creating.proofs.basic.contains.ToContain
import ch.tutteli.atrium.creating.proofs.basic.contains.checkers.AtLeastChecker
import ch.tutteli.atrium.creating.proofs.builders.buildProof
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.reportables.Description


/**
 * Represents the base class for [ToContain.Creator]s, providing a template to fulfill its job.
 *
 * @param SubjectT The type of the subject of this expectation.
 * @param SubjectMultiConsumableT The type of the subject of this expectation after making it multiple times consumable.
 * @param SearchCriteriaT The type of the search criteria.
 * @param CheckerT The type of the checkers in use (typically a sub interface of [ToContain.Checker]).
 *
 * @property checkers The [ToContain.Checker]s which shall be applied to the search result.
 *
 * @constructor Represents the base class for [ToContain.Creator]s, providing a template to fulfill its job.
 * @param checkers The [ToContain.Checker]s which shall be applied to the search result.
 */
//TODO 1.3.0 abstract class really the right way? would it be better to just use function composition?
abstract class ToContainProofCreator<SubjectT : Any, SubjectMultiConsumableT : Any, in SearchCriteriaT, CheckerT : ToContain.Checker>(
    protected val searchBehaviour: ToContain.SearchBehaviour,
    private val checkers: List<CheckerT>
) : ToContain.Creator<SubjectT, SearchCriteriaT> {


    /**
     * Provides the [Description] for `to contain`.
     */
    protected abstract val descriptionToContain: Description

    /**
     * Provides the [Description] for when an item is not found in a `toContain.atLeast(1)` check.
     */
    //TODO 1.3.0 can be removed, right? as we no longer need to show this "hint"
    protected abstract val descriptionNotFound: Description

    /**
     * Provides the [Description] for `and N such elements were found` when an item is not found in a
     * `toContain.atLeast|atMost|...`  check.
     */
    //TODO 1.3.0 can be removed, right? as we no longer need to show this "hint"
    @Suppress("DEPRECATION")
    protected abstract val descriptionNumberOfElementsFound: ch.tutteli.atrium.reporting.translating.Translatable

    final override fun createProofGroup(
        container: ProofContainer<SubjectT>,
        searchCriteria: List<SearchCriteriaT>
    ): ProofGroup {
        val multiConsumableContainer = makeSubjectMultipleTimesConsumable(container)
        val proofs = searchCriteria.map {
            //TODO 1.3.0 introduce something like LazyThreadUnsafeProofGroup again?
            searchAndCreateProof(multiConsumableContainer, it, this::featureFactory)
        }
        val description = searchBehaviour.decorateDescription(descriptionToContain)
        val inAnyOrderAssertion = Proof.group(description, Text.EMPTY, proofs)
        return decorateInAnyOrderAssertion(inAnyOrderAssertion, multiConsumableContainer)
    }

    /**
     * Override in a subclass if you want to decorate the assertion.
     */
    protected open fun decorateInAnyOrderAssertion(
        inAnyOrderAssertion: ProofGroup,
        multiConsumableContainer: ProofContainer<SubjectMultiConsumableT>
    ): ProofGroup = inAnyOrderAssertion

    /**
     * Make the underlying subject multiple times consumable.
     */
    protected abstract fun makeSubjectMultipleTimesConsumable(container: ProofContainer<SubjectT>): ProofContainer<SubjectMultiConsumableT>

    /**
     * Searches for something fulfilling the given [searchCriterion] in the subject of this expectation associated with
     * the given [multiConsumableContainer] and should pass on the number of occurrences to the given
     * [featureFactory] which creates feature assertions based on the [checkers], which in turn can be used to create
     * a resulting [ProofGroup] representing the assertion for a search criteria as a whole.
     *
     * @param multiConsumableContainer Provides the subject of this expectation for which the assertion is created.
     * @param searchCriterion A search criterion.
     * @param featureFactory The feature factory which should be called, passing the number of occurrences (matching
     *   the given [searchCriterion]) including a translation for `number of occurrences`.
     *
     * @return The newly created [ProofGroup].
     */
    protected abstract fun searchAndCreateProof(
        multiConsumableContainer: ProofContainer<SubjectMultiConsumableT>,
        searchCriterion: SearchCriteriaT,
        featureFactory: (container: ProofContainer<SubjectMultiConsumableT>, numberOfOccurrences: Int, description: Description) -> Proof
    ): Proof

    private fun featureFactory(
        multiConsumableContainer: ProofContainer<SubjectMultiConsumableT>,
        count: Int,
        numberOfOccurrences: Description
    ): Proof {
        val proofs = checkers.map { it.createProof(count) }
        val checker = checkers.firstOrNull()
        return if (checkers.size == 1 && checker is AtLeastChecker && checker.times == 1) {
            //TODO 1.3.0 change return type to Either<Boolean, Proof> and return Left in this branch as we don't want
            // to show those hints anymore
            if (checker.createProof(count).holds()) {
                //TODO 1.3.0 replace with Proof and remove suppression
                @Suppress("DEPRECATION")
                assertionBuilder.explanatoryGroup
                    .withDefaultType
                    .withExplanatoryAssertion(
                        ch.tutteli.atrium.reporting.translating.TranslatableWithArgs(
                            descriptionNumberOfElementsFound,
                            count.toString()
                        )
                    )
                    .build()
            } else {
                assertionBuilder.explanatoryGroup
                    .withDefaultType
                    .withExplanatoryAssertion(descriptionNotFound)
                    .failing
                    .build()
            }
        } else {
            multiConsumableContainer.buildProof {
                //TODO 1.3.0 still use feature for it?
                feature(numberOfOccurrences, Text(count.toString())) {
                    addAll(proofs)
                }
            }
        }
    }
}
