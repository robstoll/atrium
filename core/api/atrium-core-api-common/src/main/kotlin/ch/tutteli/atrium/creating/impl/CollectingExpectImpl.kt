package ch.tutteli.atrium.creating.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.builders.*
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.*

@ExperimentalNewExpectTypes
internal class CollectingExpectImpl<T>(maybeSubject: Option<T>) : BaseExpectImpl<T>(maybeSubject), CollectingExpect<T> {
    private val assertions = mutableListOf<Assertion>()

    override fun getAssertions(): List<Assertion> = assertions.toList()

    override fun addAssertion(assertion: Assertion): Expect<T> {
        assertions.add(assertion)
        return this
    }

    override fun addAssertionsCreatedBy(assertionCreator: Expect<T>.() -> Unit): CollectingExpect<T> {
        // in case we run into performance problems, the code below is certainly not ideal
        val allAssertions = mutableListOf<Assertion>()
        allAssertions.addAll(getAssertions())
        assertions.clear()

        val newAssertions = collectAssertions(assertionCreator)

        assertions.clear()

        if (newAssertions.isNotEmpty()) {
            allAssertions.addAll(newAssertions)
        } else {
            allAssertions.add(assertionBuilder.descriptive
                .failing
                .withFailureHint {
                    assertionBuilder.explanatoryGroup
                        .withDefaultType
                        .withAssertions(
                            assertionBuilder.explanatory.withExplanation(ErrorMessages.FORGOT_DO_DEFINE_ASSERTION)
                                .build(),
                            assertionBuilder.explanatory.withExplanation(ErrorMessages.HINT_AT_LEAST_ONE_ASSERTION_DEFINED)
                                .build()
                        )
                        .build()
                }
                .showForAnyFailure
                .withDescriptionAndRepresentation(ErrorMessages.AT_LEAST_ONE_ASSERTION_DEFINED, false)
                .build()
            )
        }
        allAssertions.forEach { addAssertion(it) }
        return this
    }

    private fun collectAssertions(assertionCreator: Expect<T>.() -> Unit) =
        //TODO remove try-catch with 0.16.0 should no longer be needed once PlantHasNoSubjectException is removed
        try {
            this.assertionCreator()
            val collectedAssertions = getAssertions()

            //TODO remove with 0.16.0
            // Required as we support mixing Expect with Assert.
            // And since assertions can be lazily computed we have to provoke their creation here,
            // so that a potential PlantHasNoSubjectException is thrown. It's fine to provoke the computation
            // because we require the assertions for the explanation anyway.
            expandAssertionGroups(collectedAssertions)

            collectedAssertions
        } catch (@Suppress("DEPRECATION") e: PlantHasNoSubjectException) {
            @Suppress("DEPRECATION")
            listOf(
                assertionBuilder.explanatoryGroup
                    .withWarningType
                    .withExplanatoryAssertion(ErrorMessages.SUBJECT_ACCESSED_TOO_EARLY)
                    .build()
            )
        }

    //TODO remove with 0.16.0
    private tailrec fun expandAssertionGroups(assertions: List<Assertion>) {
        if (assertions.isEmpty()) return

        expandAssertionGroups(
            assertions
                .asSequence()
                .filterIsInstance<AssertionGroup>()
                .flatMap { it.assertions.asSequence() }
                .toList()
        )
    }

}
