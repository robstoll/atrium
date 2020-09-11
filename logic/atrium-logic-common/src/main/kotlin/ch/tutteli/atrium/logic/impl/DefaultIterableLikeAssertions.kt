package ch.tutteli.atrium.logic.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.assertions.builders.fixedClaimGroup
import ch.tutteli.atrium.assertions.builders.invisibleGroup
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.falseProvider
import ch.tutteli.atrium.core.getOrElse
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.logic.*
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.impl.NoOpSearchBehaviourImpl
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.impl.NotSearchBehaviourImpl
import ch.tutteli.atrium.logic.creating.iterable.contains.steps.NotCheckerStep
import ch.tutteli.atrium.logic.creating.iterable.contains.steps.impl.EntryPointStepImpl
import ch.tutteli.atrium.logic.creating.iterable.contains.steps.notCheckerStep
import ch.tutteli.atrium.logic.creating.iterable.contains.IterableLikeContains
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.logic.assertions.impl.LazyThreadUnsafeAssertionGroup
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.translations.DescriptionBasic
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import ch.tutteli.atrium.translations.DescriptionIterableAssertion.NEXT_ELEMENT
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

    override fun <T : Any, E : Any> all(
        container: AssertionContainer<T>,
        converter: (T) -> Iterable<E?>,
        assertionCreatorOrNull: (Expect<E>.() -> Unit)?
    ): Assertion {
        val iterableContainer = container.changeSubject.unreported(converter).toAssertionContainer()
        return LazyThreadUnsafeAssertionGroup {
            val list = turnSubjectToList(iterableContainer).maybeSubject.getOrElse { emptyList() }
            val hasElementAssertion = createHasElementAssertion(list.iterator())

            val assertions = ArrayList<Assertion>(2)
            assertions.add(createExplanatoryAssertionGroup(assertionCreatorOrNull, list))

            val mismatches = createMismatchAssertions(list, assertionCreatorOrNull)
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

            assertionBuilder.invisibleGroup
                .withAssertions(
                    hasElementAssertion,
                    assertionBuilder.fixedClaimGroup
                        .withListType
                        .withClaim(mismatches.isEmpty())
                        .withDescriptionAndEmptyRepresentation(DescriptionIterableAssertion.ALL)
                        .withAssertions(assertions)
                        .build()
                )
                .build()
        }
    }

    private fun <E : Any> createMismatchAssertions(
        list: List<E?>,
        assertionCreator: (Expect<E>.() -> Unit)?
    ): List<Assertion> {
        return list
            .asSequence()
            .mapWithIndex()
            .filter { (_, element) -> !allCreatedAssertionsHold(element, assertionCreator) }
            .map { (index, element) ->
                assertionBuilder.createDescriptive(
                    TranslatableWithArgs(DescriptionIterableAssertion.INDEX, index),
                    element,
                    falseProvider
                )
            }
            .toList()
    }

    override fun <T : Any, E> hasNext(container: AssertionContainer<T>, converter: (T) -> Iterable<E>): Assertion =
        container.createDescriptiveAssertion(DescriptionBasic.HAS, NEXT_ELEMENT) { hasNext(it, converter) }

    private fun <E, T : Any> hasNext(it: T, converter: (T) -> Iterable<E>) =
        converter(it).iterator().hasNext()

    override fun <T : Any, E> hasNotNext(container: AssertionContainer<T>, converter: (T) -> Iterable<E>): Assertion =
        container.createDescriptiveAssertion(DescriptionBasic.HAS_NOT, NEXT_ELEMENT) { !hasNext(it, converter) }

    override fun <T : Any, E : Comparable<E>> min(
        container: AssertionContainer<T>,
        converter: (T) -> Iterable<E>
    ): ExtractedFeaturePostStep<T, E> = collect(container, converter, "min", Iterable<E>::min)

    override fun <T : Any, E : Comparable<E>> max(
        container: AssertionContainer<T>,
        converter: (T) -> Iterable<E>
    ): ExtractedFeaturePostStep<T, E> = collect(container, converter, "max", Iterable<E>::max)

    private fun <T : Any, E : Comparable<E>> collect(
        container: AssertionContainer<T>,
        converter: (T) -> Iterable<E>,
        method: String,
        collect: Iterable<E>.() -> E?
    ): ExtractedFeaturePostStep<T, E> =
        container.extractFeature
            .methodCall(method)
            .withRepresentationForFailure(DescriptionIterableAssertion.NO_ELEMENTS)
            .withFeatureExtraction {
                val iterable = converter(it)
                Option.someIf(iterable.iterator().hasNext()) {
                    iterable.collect() ?: throw IllegalStateException(
                        "Iterable does not haveNext() even though checked before! Concurrent access?"
                    )
                }
            }
            .withoutOptions()
            .build()
}
