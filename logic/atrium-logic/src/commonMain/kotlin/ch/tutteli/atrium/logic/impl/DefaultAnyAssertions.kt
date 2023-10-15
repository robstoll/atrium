package ch.tutteli.atrium.logic.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.assertions.builders.invisibleGroup
import ch.tutteli.atrium.core.*
import ch.tutteli.atrium.core.polyfills.cast
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.*
import ch.tutteli.atrium.logic.creating.transformers.SubjectChangerBuilder
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.translations.DescriptionAnyExpectation.*
import kotlin.reflect.KClass

class DefaultAnyAssertions : AnyAssertions {
    override fun <T> toBe(container: AssertionContainer<T>, expected: T): Assertion =
        container.createDescriptiveAssertion(TO_EQUAL, expected) { it == expected }

    override fun <T> notToBe(container: AssertionContainer<T?>, expected: T?): Assertion =
        container.createDescriptiveAssertion(NOT_TO_EQUAL, expected) { it != expected }

    override fun <T> isSameAs(container: AssertionContainer<T>, expected: T): Assertion =
        container.createDescriptiveAssertion(TO_BE_THE_INSTANCE, expected) { it === expected }

    override fun <T> isNotSameAs(container: AssertionContainer<T>, expected: T): Assertion =
        container.createDescriptiveAssertion(NOT_TO_BE_THE_INSTANCE, expected) { it !== expected }

    override fun <T : Any> toBeNullIfNullGivenElse(
        container: AssertionContainer<T?>,
        assertionCreatorOrNull: (Expect<T>.() -> Unit)?
    ): Assertion =
        if (assertionCreatorOrNull == null) {
            container.toBe(null)
        } else {
            val collectSubject = container.maybeSubject.flatMap { if (it != null) Some(it) else None }
            val assertion = container.collectBasedOnSubject(collectSubject) {
                _logic.appendAsGroup(assertionCreatorOrNull)
            }
            //TODO 1.3.0 this is a pattern which occurs over and over again, maybe incorporate into collect?
            container.maybeSubject.fold(
                {
                    // already in an explanatory assertion context, no need to wrap it again
                    assertion
                },
                {
                    if (it != null) {
                        assertion
                    } else {
                        assertionBuilder.explanatoryGroup
                            .withDefaultType
                            .withAssertion(assertion)
                            .failing
                            .build()
                    }
                }
            )
        }

    override fun <T : Any> notToBeNullButOfType(
        container: AssertionContainer<T?>,
        subType: KClass<T>
    ): SubjectChangerBuilder.ExecutionStep<T?, T>
     = container.changeSubject.reportBuilder()
        .withDescriptionAndRepresentation(description = NOT_TO_EQUAL_NULL_BUT_BE_OF_TYPE, representation = subType)
        .withTransformation {
            Option.someIf(subType.isInstance(it)) { subType.cast(it) }
        }
        .build()


    @Suppress("BOUNDS_NOT_ALLOWED_IF_BOUNDED_BY_TYPE_PARAMETER")
    override fun <T, TSub> isA(
        container: AssertionContainer<T>,
        subType: KClass<TSub>
    ): SubjectChangerBuilder.ExecutionStep<T, TSub> where TSub : Any, TSub : T =
        container.changeSubject.reportBuilder()
            .downCastTo(subType)
            .build()

    override fun <T> isNotIn(container: AssertionContainer<T>, expected: Iterable<T>): Assertion {
        val assertions = expected.map { value ->
            assertionBuilder.representationOnly
                .withTest(container.toExpect()) { it != value }
                .withRepresentation(value)
                .build()
        }
        return assertionBuilder.list
            .withDescriptionAndEmptyRepresentation(NOT_TO_EQUAL_ONE_IN)
            .withAssertions(assertions)
            .build()
    }

    override fun <T> because(
        container: AssertionContainer<T>,
        reason: String,
        assertionCreator: Expect<T>.() -> Unit
    ): Assertion {
        val assertion = container.collect(assertionCreator)
        return assertionBuilder.invisibleGroup
            .withAssertions(
                assertion,
                assertionBuilder.explanatoryGroup
                    .withInformationType(withIndent = false)
                    .withAssertion(assertionBuilder.createDescriptive(BECAUSE, Text(reason), falseProvider))
                    .build()
            ).build()
    }

    override fun <T> notToBeAnInstanceOf(
        container: AssertionContainer<T>,
        notExpectedTypes: List<KClass<*>>
    ): Assertion = assertionBuilder.invisibleGroup
        .withAssertions(
            notExpectedTypes.map { notExpectedType ->
                container.createDescriptiveAssertion(
                    NOT_TO_BE_AN_INSTANCE_OF,
                    notExpectedType
                ) { !notExpectedType.isInstance(it) }
            }
        ).build()

}
