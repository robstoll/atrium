package ch.tutteli.atrium.logic.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.*
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.getOrElse
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.*
import ch.tutteli.atrium.logic.assertions.impl.LazyThreadUnsafeAssertionGroup
import ch.tutteli.atrium.logic.creating.iterable.contains.IterableLikeContains
import ch.tutteli.atrium.logic.creating.iterable.contains.creators.impl.turnSubjectToList
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.impl.NoOpSearchBehaviourImpl
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.impl.NotSearchBehaviourImpl
import ch.tutteli.atrium.logic.creating.iterable.contains.steps.NotCheckerStep
import ch.tutteli.atrium.logic.creating.iterable.contains.steps.impl.EntryPointStepImpl
import ch.tutteli.atrium.logic.creating.iterable.contains.steps.notCheckerStep
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.translations.DescriptionBasic
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import ch.tutteli.atrium.translations.DescriptionIterableAssertion.NEXT_ELEMENT
import ch.tutteli.kbox.mapWithIndex

class DefaultIterableLikeAssertions : IterableLikeAssertions {
    override fun <T : IterableLike, E> builderContainsInIterableLike(
        container: AssertionContainer<T>,
        converter: (T) -> Iterable<E>
    ): IterableLikeContains.EntryPointStep<E, T, NoOpSearchBehaviour> =
        EntryPointStepImpl(container, converter, NoOpSearchBehaviourImpl())

    override fun <T : IterableLike, E> builderContainsNotInIterableLike(
        container: AssertionContainer<T>,
        converter: (T) -> Iterable<E>
    ): NotCheckerStep<E, T, NotSearchBehaviour> =
        EntryPointStepImpl(container, converter, NotSearchBehaviourImpl())._logic.notCheckerStep()

    override fun <T : IterableLike, E> hasNext(
        container: AssertionContainer<T>,
        converter: (T) -> Iterable<E>
    ): Assertion =
        container.createDescriptiveAssertion(DescriptionBasic.HAS, NEXT_ELEMENT) { hasNext(it, converter) }

    private fun <E, T : IterableLike> hasNext(it: T, converter: (T) -> Iterable<E>) =
        converter(it).iterator().hasNext()

    override fun <T : IterableLike, E> hasNotNext(
        container: AssertionContainer<T>,
        converter: (T) -> Iterable<E>
    ): Assertion =
        container.createDescriptiveAssertion(DescriptionBasic.HAS_NOT, NEXT_ELEMENT) { !hasNext(it, converter) }

    override fun <T : IterableLike, E : Comparable<E>> min(
        container: AssertionContainer<T>,
        converter: (T) -> Iterable<E>
    ): FeatureExtractorBuilder.ExecutionStep<T, E> = collect(container, converter, "min", Iterable<E>::min)

    override fun <T : IterableLike, E : Comparable<E>> max(
        container: AssertionContainer<T>,
        converter: (T) -> Iterable<E>
    ): FeatureExtractorBuilder.ExecutionStep<T, E> = collect(container, converter, "max", Iterable<E>::max)

    private fun <T : IterableLike, E : Comparable<E>> collect(
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

    override fun <T : IterableLike, E : IterableLike> all(
        container: AssertionContainer<T>,
        converter: (T) -> Iterable<E?>,
        assertionCreatorOrNull: (Expect<E>.() -> Unit)?
    ): Assertion = LazyThreadUnsafeAssertionGroup {
        val listAssertionContainer = turnSubjectToList(container, converter)
        val list = listAssertionContainer.maybeSubject.getOrElse { emptyList() }

        val assertions = ArrayList<Assertion>(2)
        assertions.add(createExplanatoryAssertionGroup(container, assertionCreatorOrNull))
        val mismatches = createIndexAssertions(list) { (_, element) ->
            !allCreatedAssertionsHold(container, element, assertionCreatorOrNull)
        }
        if (mismatches.isNotEmpty()) assertions.add(createExplanatoryGroupForMismatches(mismatches))

        decorateAssertionWithHasNext(
            assertionBuilder.list
                .withDescriptionAndEmptyRepresentation(DescriptionIterableAssertion.ALL)
                .withAssertions(assertions)
                .build(),
            listAssertionContainer
        )
    }

    override fun <T : IterableLike, E> containsNoDuplicates(
        container: AssertionContainer<T>,
        converter: (T) -> Iterable<E>
    ): Assertion = LazyThreadUnsafeAssertionGroup {
        val listAssertionContainer = turnSubjectToList(container, converter)
        val list = listAssertionContainer.maybeSubject.getOrElse { emptyList() }

        val lookupHashMap = HashMap<E, Int>()
        val duplicateIndices = HashMap<Int, Pair<E, MutableList<Int>>>()

        list.asSequence()
            .mapWithIndex()
            .forEach { (index, element) ->
                lookupHashMap[element]?.let {
                    duplicateIndices.getOrPut(it) {
                        Pair(element, mutableListOf<Int>())
                    }.second.add(index)
                } ?: let {
                    lookupHashMap[element] = index
                }
            }

        val duplicates = duplicateIndices
            .map { (index, pair) ->
                val (element, indices) = pair
                assertionBuilder.descriptive
                    .failing
                    .withHelpOnFailure {
                        assertionBuilder.explanatoryGroup
                            .withDefaultType
                            .withExplanatoryAssertion(
                                TranslatableWithArgs(
                                    DescriptionIterableAssertion.DUPLICATED_BY,
                                    indices.joinToString(", ")
                                )
                            )
                            .build()
                    }
                    .showForAnyFailure
                    .withDescriptionAndRepresentation(
                        TranslatableWithArgs(DescriptionIterableAssertion.INDEX, index),
                        element
                    )
                    .build()
            }

        decorateAssertionWithHasNext(
            createAssertionGroupFromListOfAssertions(
                DescriptionBasic.HAS_NOT,
                DescriptionIterableAssertion.DUPLICATE_ELEMENTS,
                duplicates
            ),
            listAssertionContainer
        )
    }
}
