package ch.tutteli.atrium.creating.proofs.charsequence.contains.creators.impl

import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.creating.changeSubject
import ch.tutteli.atrium.creating.proofs.ProofGroup
import ch.tutteli.atrium.creating.proofs.basic.contains.creators.impl.ToContainObjectsProofCreator
import ch.tutteli.atrium.creating.proofs.charsequence.contains.CharSequenceToContain.*
import ch.tutteli.atrium.creating.toProofContainer
import ch.tutteli.atrium.reporting.reportables.Description
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionCharSequenceProof

/**
 * Represents a creator of sophisticated `contains` assertions for [CharSequence].
 *
 * A sophisticated `contains` assertion is build up by a desired [SearchBehaviour], a [Searcher] and a number of
 * [Checker]s. The [searcher] searches for specified objects -- considering the given [searchBehaviour] -- and
 * then passes on the result to the given [checkers] which in turn create the assertions representing the
 * corresponding check. Those created assertions are then grouped into an [ProofGroup] which represents the
 * sophisticated assertion as a whole.
 *
 * @param SubjectT The type of the subject for which the `contains` assertion is be build.
 * @param SearchCriteriaT The type of the search criteria.
 * @param SearchBehaviourT The search behaviour which should be applied to the input of the search.
 *
 * @constructor Represents a creator for sophisticated `contains` assertions for [CharSequence].
 * @param searchBehaviour The search behaviour.
 * @param searcher The search method which is used to search for given objects.
 * @param checkers The checkers which create assertions based on the search result.
 */
class CharSequenceToContainProofCreator<SubjectT : CharSequence, in SearchCriteriaT : Any, SearchBehaviourT : SearchBehaviour>(
    searchBehaviour: SearchBehaviourT,
    private val searcher: Searcher<SearchBehaviourT, SearchCriteriaT>,
    checkers: List<Checker>,
    override val groupDescription: Description
) : ToContainObjectsProofCreator<SubjectT, String, SearchCriteriaT, SearchBehaviourT, Checker>(
    searchBehaviour,
    checkers
), Creator<SubjectT, SearchCriteriaT> {

    override val descriptionToContain get(): Description = DescriptionCharSequenceProof.TO_CONTAIN

    override val descriptionNumberOfOccurrences get(): Description = DescriptionCharSequenceProof.NUMBER_OF_MATCHES

    override val descriptionNotFound get(): Description = DescriptionCharSequenceProof.NOT_FOUND

    @Suppress("DEPRECATION")
    override val descriptionNumberOfElementsFound
        get(): ch.tutteli.atrium.reporting.translating.Translatable =
            ch.tutteli.atrium.reporting.translating.Untranslatable("and %s matches were found")

    override fun makeSubjectMultipleTimesConsumable(container: ProofContainer<SubjectT>): ProofContainer<String> =
        container.changeSubject.unreported { it.toString() }.toProofContainer()

    override fun search(multiConsumableContainer: ProofContainer<String>, searchCriterion: SearchCriteriaT): Int =
    // if the maybeSubject is None it means we are in an explanation like context in which
        // it should not matter what this number is. Moreover, we check in the atMostChecker that times is >= 0
        multiConsumableContainer.maybeSubject.fold({ -1 }) { searcher.search(it, searchCriterion) }
}
