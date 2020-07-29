package ch.tutteli.atrium.logic.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.core.trueProvider
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.changers.ChangedSubjectPostStep
import ch.tutteli.atrium.logic.*
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.translations.DescriptionAnyAssertion.*
import kotlin.reflect.KClass

class DefaultAnyAssertions : AnyAssertions {
    override fun <T> toBe(container: AssertionContainer<T>, expected: T): Assertion =
        container.createDescriptiveAssertion(TO_BE, expected) { it == expected }

    override fun <T> notToBe(container: AssertionContainer<T>, expected: T): Assertion =
        container.createDescriptiveAssertion(NOT_TO_BE, expected) { it != expected }

    override fun <T> isSameAs(container: AssertionContainer<T>, expected: T): Assertion =
        container.createDescriptiveAssertion(IS_SAME, expected) { it === expected }

    override fun <T> isNotSameAs(container: AssertionContainer<T>, expected: T): Assertion =
        container.createDescriptiveAssertion(IS_NOT_SAME, expected) { it !== expected }

    override fun <T> toBeNull(container: AssertionContainer<T>): Assertion =
        container.createDescriptiveAssertion(TO_BE, Text.NULL) { it == null }

    override fun <T : Any> toBeNullIfNullGivenElse(
        container: AssertionContainer<T?>,
        type: KClass<T>,
        assertionCreatorOrNull: (Expect<T>.() -> Unit)?
    ): Assertion =
        if (assertionCreatorOrNull == null) container.toBeNull()
        else notToBeNull(container, type, assertionCreatorOrNull)

    private fun <T : Any> notToBeNull(
        container: AssertionContainer<T?>,
        type: KClass<T>,
        assertionCreator: Expect<T>.() -> Unit
    ) = container.notToBeNull(type).collect(assertionCreator)


    override fun <T : Any> notToBeNull(
        container: AssertionContainer<T?>,
        subType: KClass<T>
    ): ChangedSubjectPostStep<T?, T> = container.isA(subType)

    override fun <T, TSub : Any> isA(
        container: AssertionContainer<T>,
        subType: KClass<TSub>
    ): ChangedSubjectPostStep<T, TSub> =
        container.changeSubject.reportBuilder()
            .downCastTo(subType)
            .build()

    override fun <T> isNotIn(container: AssertionContainer<T>, expected: Iterable<T>): Assertion {
        val assertions = expected.map { value ->
            assertionBuilder.representationOnly
                .withTest(container) { it != value }
                .withRepresentation(value)
                .build()
        }
        return assertionBuilder.list
            .withDescriptionAndEmptyRepresentation(IS_NONE_OF)
            .withAssertions(assertions)
            .build()
    }

}
