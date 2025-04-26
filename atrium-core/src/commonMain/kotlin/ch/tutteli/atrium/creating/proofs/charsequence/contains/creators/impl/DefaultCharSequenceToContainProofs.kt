package ch.tutteli.atrium.creating.proofs.charsequence.contains.creators.impl

import ch.tutteli.atrium.creating.proofs.ProofGroup
import ch.tutteli.atrium.creating.proofs.charsequence.contains.CharSequenceToContain.*
import ch.tutteli.atrium.creating.proofs.charsequence.contains.creators.CharSequenceToContainProofs
import ch.tutteli.atrium.creating.proofs.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour
import ch.tutteli.atrium.creating.proofs.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.creating.proofs.charsequence.contains.searchers.impl.IgnoringCaseIndexSearcher
import ch.tutteli.atrium.creating.proofs.charsequence.contains.searchers.impl.IgnoringCaseRegexSearcher
import ch.tutteli.atrium.creating.proofs.charsequence.contains.searchers.impl.IndexSearcher
import ch.tutteli.atrium.creating.proofs.charsequence.contains.searchers.impl.RegexSearcher
import ch.tutteli.atrium.creating.typeutils.CharSequenceOrNumberOrChar
import ch.tutteli.atrium.reporting.reportables.Description
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionCharSequenceProof

class DefaultCharSequenceToContainProofs : CharSequenceToContainProofs {
    override fun <T : CharSequence> values(
        checkerStepCore: CheckerStepCore<T, NoOpSearchBehaviour>,
        expected: List<CharSequenceOrNumberOrChar>
    ): ProofGroup =
        checkOnlyAllowedTypeNotEmptyStringAndCreateProofGroup(checkerStepCore, IndexSearcher(), expected)

    override fun <T : CharSequence> valuesIgnoringCase(
        checkerStepCore: CheckerStepCore<T, IgnoringCaseSearchBehaviour>,
        expected: List<CharSequenceOrNumberOrChar>
    ): ProofGroup =
        checkOnlyAllowedTypeNotEmptyStringAndCreateProofGroup(checkerStepCore, IgnoringCaseIndexSearcher(), expected)

    private fun <T : CharSequence, S : SearchBehaviour> checkOnlyAllowedTypeNotEmptyStringAndCreateProofGroup(
        checkerStepCore: CheckerStepCore<T, S>,
        searcher: Searcher<S, CharSequenceOrNumberOrChar>,
        expected: List<CharSequenceOrNumberOrChar>
    ): ProofGroup {
        require(expected.isNotEmpty()) {
            "You have to specify at least one search criterion for a CharSequence to contain expectation"
        }
        expected.forEach {
            require(it is CharSequence || it is Number || it is Char) {
                "Only values of type CharSequence, Number and Char are allowed\nGiven: $it\n" +
                    "We provide an API with CharSequenceOrNumberOrChar (typealias for Any) for convenience (so that you can mix String and Int for instance).\n" +
                    "Use toString() if you really want to search for its toString()-representation."
            }
            require(it != "") {
                "Searching for the empty string does not make sense. You probably forgot to specify the search criterion."
            }
            require(it.toString() != "") {
                "Searching for an empty CharSequence does not make sense. You probably forgot to specify the search criterion."
            }
        }
        return createProofGroup(checkerStepCore, searcher, expected, DescriptionCharSequenceProof.VALUE)
    }

    override fun <T : CharSequence> regex(
        checkerStepCore: CheckerStepCore<T, NoOpSearchBehaviour>,
        expected: List<Regex>
    ): ProofGroup = createProofGroup(
        checkerStepCore,
        RegexSearcher(),
        expected,
        DescriptionCharSequenceProof.STRING_MATCHING_REGEX
    )

    override fun <T : CharSequence> regexIgnoringCase(
        checkerStepCore: CheckerStepCore<T, IgnoringCaseSearchBehaviour>,
        expected: List<String>
    ): ProofGroup = createProofGroup(
        checkerStepCore,
        IgnoringCaseRegexSearcher(),
        expected,
        DescriptionCharSequenceProof.STRING_MATCHING_REGEX
    )

    private fun <T : CharSequence, SC : CharSequenceOrNumberOrChar, S : SearchBehaviour> createProofGroup(
        checkerStepCore: CheckerStepCore<T, S>,
        searcher: Searcher<S, SC>,
        expected: List<SC>,
        groupDescription: Description
    ): ProofGroup {
        val creator = CharSequenceToContainProofCreator<T, SC, S>(
            checkerStepCore.entryPointStepCore.searchBehaviour,
            searcher,
            checkerStepCore.checkers,
            groupDescription
        )
        return creator.createProofGroup(checkerStepCore.entryPointStepCore.container, expected)
    }
}
