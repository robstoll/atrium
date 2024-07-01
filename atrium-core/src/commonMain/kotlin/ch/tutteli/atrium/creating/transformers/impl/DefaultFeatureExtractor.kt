package ch.tutteli.atrium.creating.transformers.impl

import ch.tutteli.atrium.core.*
import ch.tutteli.atrium.core.polyfills.fullName
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.creating.transformers.FeatureExtractor
import ch.tutteli.atrium.creating.transformers.impl.ThrowableThrownFailureHandler.Companion.propertiesOfThrowable
import ch.tutteli.atrium.creating.proofs.buildProof
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.reportables.InlineElement
import ch.tutteli.atrium.reporting.reportables.Reportable
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionFunLikeProof

class DefaultFeatureExtractor : FeatureExtractor {

    @OptIn(ExperimentalNewExpectTypes::class, ExperimentalComponentFactoryContainer::class)
    override fun <SubjectT, FeatureT> extract(
        container: ProofContainer<SubjectT>,
        description: InlineElement,
        representationForFailure: Any,
        featureExtraction: (SubjectT) -> Option<FeatureT>,
        maybeSubExpectationCreatorAndUsageHints: Option<ExpectationCreatorWithUsageHints<FeatureT>>,
        featureExpectOptions: FeatureExpectOptions<FeatureT>
    ): FeatureExpect<SubjectT, FeatureT> {
        val either: Either<Option<Throwable>, FeatureT> = extract(container, featureExtraction)
        return either.fold(
            { maybeThrowable ->
                appendFailureHint(
                    container,
                    description,
                    maybeThrowable,
                    representationForFailure,
                    maybeSubExpectationCreatorAndUsageHints
                )

                // we already append sub-expectations as part of the failure hint, hence we can start the FeatureExpect
                // with an empty list of proofs to append
                Pair(None, emptyList())
            },
            { subject ->
                Pair(
                    Some(subject),
                    maybeSubExpectationCreatorAndUsageHints.fold({
                        emptyList()
                    }) { expectationCreatorWithUsageHints ->
                        container.collectForCompositionBasedOnGivenSubject(
                            Some(subject),
                            expectationCreatorWithUsageHints
                        ).first
                    }
                )
            }
        ).let { (maybeFeature, subProofs) ->
            FeatureExpect(
                container.toExpect(),
                maybeFeature,
                description,
                subProofs,
                featureExpectOptions
            )
        }
    }

    private fun <FeatureT, SubjectT> appendFailureHint(
        container: ProofContainer<SubjectT>,
        description: InlineElement,
        maybeThrowable: Option<Throwable>,
        representationForFailure: Any,
        maybeSubExpectationCreatorAndUsageHints: Option<ExpectationCreatorWithUsageHints<FeatureT>>
    ) {
        val (failureHints, repForFailure) = maybeThrowable.fold(
            {
                // subject was already None, i.e. we were not able to extract a feature, no additional failure hint
                Pair(emptyList(), representationForFailure)
            },
            { throwable ->
                // there was an error during extraction
                Pair(
                    listOf(container.propertiesOfThrowable(throwable)),
                    Reportable.inlineGroup(
                        listOf(DescriptionFunLikeProof.THREW, Text(throwable::class.fullName))
                    )
                )
            }
        )

        val subExpectations = maybeSubExpectationCreatorAndUsageHints.fold({
            // no sub-expectations defined
            emptyList()
        }) { expectationCreatorWithUsageHints ->
            // sub-expectations defined, show them in the failure hint as well to give more context
            container.collectForFailureHint(expectationCreatorWithUsageHints).first
        }
        val featureAssertions = failureHints + subExpectations

        val fixedClaimGroup = container.buildProof {
            fixedClaimGroup(description, repForFailure, holds = false) {
                //TODO 1.3.0 this building process should fail in case we don't define any expectation
                // make sure we have an appropriate test which covers this
                addAll(featureAssertions)
            }
        }
        container.maybeSubject.fold({
            // The subject is already None, i.e. the feature extraction cannot be carried out.
            // If no subExpectations where defined, then we don't need/want to show the fixedClaimGroup because the
            // feature as such will already be shown via explanatory expectation-group at the place where the first
            // extraction/transformation failed.
            if (featureAssertions.isNotEmpty()) {
                container.append(fixedClaimGroup)
            }
        }, {
            // on the other hand, if the subject is defined, then we need the fixedClaimGroup which inter alia
            // shows why the extraction went wrong (e.g. index out of bound)
            container.append(fixedClaimGroup)
        })
    }

    private fun <FeatureT, SubjectT> extract(
        container: ProofContainer<SubjectT>,
        featureExtraction: (SubjectT) -> Option<FeatureT>
    ) = container.maybeSubject.fold({ Left(None) }, { subject ->
        try {
            featureExtraction(subject).fold({ Left(None) }, { Right(it) })
        } catch (throwable: Throwable) {
            Left(Some(throwable))
        }
    })
}
