package ch.tutteli.atrium.creating.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.*
import ch.tutteli.atrium.core.*
import ch.tutteli.atrium.core.polyfills.fullName
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.creating.collectors.collectAssertions
import ch.tutteli.atrium.creating.collectors.collectForComposition
import ch.tutteli.atrium.creating.collectors.collectForCompositionBasedOnSubject
import ch.tutteli.atrium.reporting.AtriumErrorAdjuster
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.reporting.translating.Untranslatable

@ExperimentalNewExpectTypes
@ExperimentalComponentFactoryContainer
internal class CollectingExpectImpl<T>(
    maybeSubject: Option<T>,
    override val components: ComponentFactoryContainer
) : BaseExpectImpl<T>(maybeSubject), CollectingExpect<T> {
    private val assertions = mutableListOf<Assertion>()

    override fun getAssertions(): List<Assertion> = assertions.toList()

    override fun append(assertion: Assertion): Expect<T> =
        apply { assertions.add(assertion) }

    override fun appendAsGroup(assertionCreator: Expect<T>.() -> Unit): CollectingExpect<T> {
        // in case we run into performance problems, the code below is certainly not ideal
        val allAssertions = mutableListOf<Assertion>()
        allAssertions.addAll(getAssertions())
        assertions.clear()

        performSafeAssertionCreation(assertionCreator)

        val newAssertions = getAssertions()

        assertions.clear()

        if (newAssertions.isNotEmpty()) {
            allAssertions.addAll(newAssertions)
        } else {
            allAssertions.add(assertionBuilder.descriptive
                .failing
                .withHelpOnFailure {
                    assertionBuilder.explanatoryGroup
                        .withDefaultType
                        .withAssertions(
                            assertionBuilder.explanatory.withExplanation(ErrorMessages.FORGOT_DO_DEFINE_EXPECTATION)
                                .build(),
                            assertionBuilder.explanatory.withExplanation(ErrorMessages.HINT_AT_LEAST_ONE_EXPECTATION_DEFINED)
                                .build()
                        )
                        .build()
                }
                .showForAnyFailure
                .withDescriptionAndRepresentation(ErrorMessages.AT_LEAST_ONE_EXPECTATION_DEFINED, false)
                .build()
            )
        }
        allAssertions.forEach { append(it) }
        return this
    }

    //TODO 1.3.0 use FailureExtractor once in core, re-use most of its logic respectively
    private fun performSafeAssertionCreation(assertionCreator: Expect<T>.() -> Unit) {
        val maybeThrowable = try {
            assertionCreator(this)
            None
        } catch (throwable: Throwable) {
            components.build<AtriumErrorAdjuster>().adjust(throwable)
            Some(throwable)
        }

                val (failureHintAssertions, repForFailure) = maybeThrowable.fold(
                    { emptyList<Assertion>() to representationForFailure },
                    { throwable ->
                        listOf(ThrowableThrownFailureHandler.propertiesOfThrowable(throwable)) to
                            // TODO 1.2.0 decide what we do with translations -- this was defined in DescriptionFunLikeExpectation
                            TranslatableWithArgs(Untranslatable("❗❗ threw %s"), throwable::class.fullName)
                    })

                val subAssertions =
                // TODO 1.3.0: factor out in common pattern, should not be the concern of the average expectation
                    // function writer
                    maybeSubject.fold({
                        // already in an explanatory expectation-group, no need to wrap again
                        // TODO 1.3.0 reuse: collectForCompositionBasedOnSubject(None, assertionCreator)
                        CollectingExpect(maybeSubject, components)
                            .appendAsGroup(assertionCreator)
                            .getAssertions()
                    }, { _ ->
                        listOf(
                            assertionBuilder.explanatoryGroup.withDefaultType
                                // we are not passing the subject on purpose but None instead as we now that the extraction of the subject failed
                                .collectAssertions(this, None, assertionCreator)
                                .build()
                        )
                    })

                val featureAssertions = failureHintAssertions + subAssertions
                val fixedClaimGroup = assertionBuilder.fixedClaimGroup
                    .withFeatureType
                    .failing
                    .withDescriptionAndRepresentation(description, repForFailure)
                    .withAssertions(featureAssertions)
                    .build()
                container.maybeSubject.fold({
                    // If the feature extraction fails because the subject is already None, then we don't need/want to
                    // show the fixedClaimGroup in case it is empty because the feature as such will already be shown
                    // via explanatory expectation-group
                    if (featureAssertions.isNotEmpty()) {
                        container.append(fixedClaimGroup)
                    }
                }, {
                    // on the other hand, if the subject is defined, then we need the fixedClaimGroup which inter alia
                    // shows why the extraction went wrong (e.g. index out of bound)
                    container.append(fixedClaimGroup)
                })
                createFeatureExpect(None, listOf())
            },
            { subject ->
                createFeatureExpect(Some(subject), maybeSubAssertions.fold({
                    emptyList()
                }) { assertionCreator ->
                    container.collectForCompositionBasedOnSubject(Some(subject), assertionCreator)
                })
            }
        )
    }

}

fun test(someParams: ...) {
    expect {
        setup() // exceptions thrown are reported
    }.notToThrow {  // expectation group block, evaluation happens in the end
        extractSubject { dsl ->

        }
    }
    expect(testee) {

    }
}
