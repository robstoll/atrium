package ch.tutteli.atrium.logic.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.assertions.builders.fixedClaimGroup
import ch.tutteli.atrium.assertions.builders.invisibleGroup
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.falseProvider
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.IterableLikeAssertions
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic.assertions.impl.LazyThreadUnsafeAssertionGroup
import ch.tutteli.atrium.logic.createDescriptiveAssertion
import ch.tutteli.atrium.logic.creating.iterable.contains.IterableLikeContains
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.impl.NoOpSearchBehaviourImpl
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.impl.NotSearchBehaviourImpl
import ch.tutteli.atrium.logic.creating.iterable.contains.steps.NotCheckerStep
import ch.tutteli.atrium.logic.creating.iterable.contains.steps.impl.EntryPointStepImpl
import ch.tutteli.atrium.logic.creating.iterable.contains.steps.notCheckerStep
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder
import ch.tutteli.atrium.logic.extractFeature
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.translations.DescriptionBasic
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import ch.tutteli.atrium.translations.DescriptionIterableAssertion.NEXT_ELEMENT
import ch.tutteli.kbox.WithIndex
import ch.tutteli.kbox.mapWithIndex

class DefaultIterableLikeAssertions : IterableLikeAssertions {
    override fun <T : Any, E> containsBuilder(
        container: AssertionContainer<T>,
        converter: (T) -> Iterable<E>
    ): IterableLikeContains.EntryPointStep<E, T, NoOpSearchBehaviour> =
        EntryPointStepImpl(container, converter, NoOpSearchBehaviourImpl())

    override fun <T : Any, E> containsNotBuilder(
        container: AssertionContainer<T>,
        converter: (T) -> Iterable<E>
    ): NotCheckerStep<E, T, NotSearchBehaviour> =
        EntryPointStepImpl(container, converter, NotSearchBehaviourImpl())._logic.notCheckerStep()

    override fun <T : Any, E> hasNext(container: AssertionContainer<T>, converter: (T) -> Iterable<E>): Assertion =
        container.createDescriptiveAssertion(DescriptionBasic.HAS, NEXT_ELEMENT) { hasNext(it, converter) }

    private fun <E, T : Any> hasNext(it: T, converter: (T) -> Iterable<E>) =
        converter(it).iterator().hasNext()

    override fun <T : Any, E> hasNotNext(container: AssertionContainer<T>, converter: (T) -> Iterable<E>): Assertion =
        container.createDescriptiveAssertion(DescriptionBasic.HAS_NOT, NEXT_ELEMENT) { !hasNext(it, converter) }

    override fun <T : Any, E : Comparable<E>> min(
        container: AssertionContainer<T>,
        converter: (T) -> Iterable<E>
    ): FeatureExtractorBuilder.ExecutionStep<T, E> = collect(container, converter, "min", Iterable<E>::min)

    override fun <T : Any, E : Comparable<E>> max(
        container: AssertionContainer<T>,
        converter: (T) -> Iterable<E>
    ): FeatureExtractorBuilder.ExecutionStep<T, E> = collect(container, converter, "max", Iterable<E>::max)

    private fun <T : Any, E : Comparable<E>> collect(
        container: AssertionContainer<T>,
        converter: (T) -> Iterable<E>,
        method: String,
        collect: Iterable<E>.() -> E?
    ): FeatureExtractorBuilder.ExecutionStep<T, E> =
        container.extractFeature
            .methodCall(method)
            .withRepresentationForFailure(DescriptionIterableAssertion.NO_ELEMENTS)
            .withFeatureExtraction {
                val iterable = converter(it)
                Option.someIf(iterable.iterator().hasNext()) {
                    iterable.collect() ?: throw IllegalStateException(
                        "Iterable does not hasNext() even though checked before! Concurrent access?"
                    )
                }
            }
            .withoutOptions()
            .build()

    override fun <T : Any, E : Any> all(
        container: AssertionContainer<T>,
        converter: (T) -> Iterable<E?>,
        assertionCreatorOrNull: (Expect<E>.() -> Unit)?
    ): Assertion = LazyThreadUnsafeAssertionGroup {
        val list = transformToList(container, converter)

        val assertions = ArrayList<Assertion>(2)
        assertions.add(createExplanatoryAssertionGroup(assertionCreatorOrNull, list))

        val mismatches = createIndexAssertions(list) { (_, element) ->
            !allCreatedAssertionsHold(element, assertionCreatorOrNull)
        }
        assertions.add(
            assertionBuilder.explanatoryGroup
                .withWarningType
                .withAssertion(
                    assertionBuilder.list
                        .withDescriptionAndEmptyRepresentation(DescriptionIterableAssertion.WARNING_MISMATCHES)
                        .withAssertions(mismatches)
                        .build()
                )
                .build()
        )

        createHasElementPlusFixedClaimGroup(
            list,
            DescriptionIterableAssertion.ALL,
            Text.EMPTY,
            mismatches.isEmpty(),
            assertions
        )
    }

    private fun <T : Any, E> transformToList(container: AssertionContainer<T>, converter: (T) -> Iterable<E>): List<E> =
        container.maybeSubject.fold({ emptyList() }) { subject ->
            val iterable = converter(subject)
            when (iterable) {
                is List<E> -> iterable
                else -> iterable.toList()
            }
        }

    private fun <E> createIndexAssertions(
        list: List<E>,
        predicate: (WithIndex<E>) -> Boolean
    ) = list
        .asSequence()
        .mapWithIndex()
        .filter { predicate(it) }
        .map { (index, element) ->
            assertionBuilder.createDescriptive(
                TranslatableWithArgs(DescriptionIterableAssertion.INDEX, index),
                element,
                falseProvider
            )
        }
        .toList()

    private fun <E> createHasElementPlusFixedClaimGroup(
        list: List<E>,
        description: Translatable,
        representation: Any,
        claim: Boolean,
        assertions: List<Assertion>
    ) = assertionBuilder.invisibleGroup
        .withAssertions(
            createHasElementAssertion(list.iterator()),
            assertionBuilder.fixedClaimGroup
                .withListType
                .withClaim(claim)
                .withDescriptionAndRepresentation(description, representation)
                .withAssertions(assertions)
                .build()
        )
        .build()

    override fun <T : Any, E> containsNoDuplicates(
        container: AssertionContainer<T>,
        converter: (T) -> Iterable<E>
    ): Assertion = LazyThreadUnsafeAssertionGroup {
        val list = transformToList(container, converter)

        val duplicates = createIndexAssertions(list) { (_, element) ->
            list.count { e -> e == element } > 1
        }
        createHasElementPlusFixedClaimGroup(
            list,
            DescriptionBasic.HAS, DescriptionIterableAssertion.DUPLICATE_ELEMENTS,
            duplicates.isEmpty(),
            duplicates
        )
    }
}
