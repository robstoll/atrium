package ch.tutteli.atrium.core.robstoll.lib.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.assertions.builders.withFailureHint
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.CollectingAssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.translations.ErrorMessages

class CollectingAssertionContainerImpl<T>(
    maybeSubject: Option<T>
) : MutableListBasedAssertionContainer<T>(maybeSubject), CollectingAssertionContainer<T> {

    override fun getAssertions(): List<Assertion> = getCopyOfAssertions()

    override fun addAssertionsCreatedBy(assertionCreator: Expect<T>.() -> Unit): CollectingAssertionContainer<T> {
        // in case we run into performance problems, the code below is certainly not ideal
        val allAssertions = mutableListOf<Assertion>()
        allAssertions.addAll(getAssertions())
        clearAssertions()
        this.assertionCreator()
        val newAssertions = getAssertions()
        clearAssertions()

        if (newAssertions.isNotEmpty()) {
            allAssertions.addAll(newAssertions)
        } else {
            allAssertions.add(assertionBuilder.descriptive
                .failing
                .withFailureHint {
                    assertionBuilder.explanatoryGroup
                        .withDefaultType
                        .withAssertions(
                            assertionBuilder.explanatory.withDescription(ErrorMessages.FORGOT_DO_DEFINE_ASSERTION).build(),
                            assertionBuilder.explanatory.withDescription(ErrorMessages.HINT_AT_LEAST_ONE_ASSERTION_DEFINED).build()
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
}
