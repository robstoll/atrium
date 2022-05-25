package ch.tutteli.atrium.logic.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
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
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.translations.DescriptionBasic.NOT_TO_HAVE
import ch.tutteli.atrium.translations.DescriptionBasic.TO_HAVE
import ch.tutteli.atrium.translations.DescriptionIterableLikeExpectation.*
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
        container.createDescriptiveAssertion(TO_HAVE, A_NEXT_ELEMENT) { hasNext(it, converter) }

    private fun <E, T : IterableLike> hasNext(it: T, converter: (T) -> Iterable<E>) =
        converter(it).iterator().hasNext()

    override fun <T : IterableLike, E> hasNotNext(
        container: AssertionContainer<T>,
        converter: (T) -> Iterable<E>
    ): Assertion =
        container.createDescriptiveAssertion(NOT_TO_HAVE, A_NEXT_ELEMENT) { !hasNext(it, converter) }

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
            .withRepresentationForFailure(NO_ELEMENTS)
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
    ): Assertion = allWithMismatchClauseAndDecorator(
        container,
        converter,
        assertionCreatorOrNull,
        ALL_ELEMENTS,
        mismatchIf = { holds -> !holds },
        decorateAssertion = ::decorateAssertionWithHasNext
    )

    override fun <T : IterableLike, E: Any> hasNotNextOrAll(
        container: AssertionContainer<T>,
        converter: (T) -> Iterable<E?>,
        assertionCreatorOrNull: (Expect<E>.() -> Unit)?
    ): Assertion = allWithMismatchClauseAndDecorator(
        container,
        converter,
        assertionCreatorOrNull,
        NOT_TO_HAVE_ELEMENTS_OR_ALL,
        mismatchIf = { holds -> !holds }
    ) { group, _ -> group }

    override fun <T : IterableLike, E : Any> hasNotNextOrNone(
        container: AssertionContainer<T>,
        converter: (T) -> Iterable<E?>,
        assertionCreatorOrNull: (Expect<E>.() -> Unit)?
    ): Assertion = allWithMismatchClauseAndDecorator(
        container,
        converter,
        assertionCreatorOrNull,
        NOT_TO_HAVE_ELEMENTS_OR_NONE,
        mismatchIf = { holds -> holds }
    ) { group, _ -> group }

    private fun <E : Any, T : IterableLike> allWithMismatchClauseAndDecorator(
        container: AssertionContainer<T>,
        converter: (T) -> Iterable<E?>,
        assertionCreatorOrNull: (Expect<E>.() -> Unit)?,
        description: Translatable,
        mismatchIf: (Boolean) -> Boolean,
        decorateAssertion: (AssertionGroup, AssertionContainer<List<E?>>) -> AssertionGroup
    ): LazyThreadUnsafeAssertionGroup = LazyThreadUnsafeAssertionGroup {
        val listAssertionContainer = turnSubjectToList(container, converter)
        val list = listAssertionContainer.maybeSubject.getOrElse { emptyList() }

        val assertions = ArrayList<Assertion>(2)
        assertions.add(createExplanatoryAssertionGroup(container, assertionCreatorOrNull))
        val mismatches = createIndexAssertions(list) { (_, element) ->
            mismatchIf(allCreatedAssertionsHold(container, element, assertionCreatorOrNull))
        }
        if (mismatches.isNotEmpty()) assertions.add(createExplanatoryGroupForMismatches(mismatches))

        decorateAssertion(
            // we need to use a partiallyFixedClaimGroup here and cannot use `.list` because an assertion has to fail
            // if the subject is absent (checked in SubjectLessSpec). Since we use an emptyList in case the subject is
            // absent and in contrast to toHaveElementsAnd... (which decorates this AssertionGroup with a toHaveNext)
            // notToHaveElementsOr... functions don't fail in case of an empty list.
            // Hence, we need the additional claim
            assertionBuilder.partiallyFixedClaimGroup
                .withListType
                .withClaim(listAssertionContainer.maybeSubject.isDefined())
                .withDescriptionAndEmptyRepresentation(description)
                .withAssertions(assertions)
                .build(),
            listAssertionContainer
        )
    }

    override fun <T : IterableLike, E : IterableLike> hasNotNextOrAny(
        container: AssertionContainer<T>,
        converter: (T) -> Iterable<E?>,
        assertionCreatorOrNull: (Expect<E>.() -> Unit)?
    ): Assertion = LazyThreadUnsafeAssertionGroup {
        val listAssertionContainer = turnSubjectToList(container, converter)
        val list = listAssertionContainer.maybeSubject.getOrElse { emptyList() }

        val assertions = ArrayList<Assertion>(2)
        assertions.add(createExplanatoryAssertionGroup(container, assertionCreatorOrNull))
        if (list.isNotEmpty() && !list.any { element ->
                allCreatedAssertionsHold(container, element, assertionCreatorOrNull)
            }) {
            assertions.add(
                assertionBuilder.explanatoryGroup
                    .withDefaultType
                    .withExplanatoryAssertion(NEITHER_EMPTY_NOR_ELEMENT_FOUND)
                    .failing
                    .build()
            )
        }
        // we need to use a partiallyFixedClaimGroup here and cannot use `.list` because an assertion has to fail
        // if the subject is absent (checked in SubjectLessSpec). Since we use an emptyList in case the subject is
        // absent and in contrast to toHaveElementsAndAny we don't fail in case of an empty list,
        // we need the additional claim
        assertionBuilder.partiallyFixedClaimGroup
            .withListType
            .withClaim(listAssertionContainer.maybeSubject.isDefined())
            .withDescriptionAndEmptyRepresentation(NOT_TO_HAVE_ELEMENTS_OR_ANY)
            .withAssertions(assertions)
            .build()
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
                                    DUPLICATED_BY,
                                    indices.joinToString(", ")
                                )
                            )
                            .build()
                    }
                    .showForAnyFailure
                    .withDescriptionAndRepresentation(
                        TranslatableWithArgs(INDEX, index),
                        element
                    )
                    .build()
            }

        decorateAssertionWithHasNext(
            createAssertionGroupFromListOfAssertions(
                NOT_TO_HAVE,
                DUPLICATE_ELEMENTS,
                duplicates
            ),
            listAssertionContainer
        )
    }
}
