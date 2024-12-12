@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.logic.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.assertions.builders.partiallyFixedClaimGroup
import ch.tutteli.atrium.assertions.builders.withExplanatoryAssertion
import ch.tutteli.atrium.assertions.builders.withHelpOnFailure
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.getOrElse
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.IterableLikeAssertions
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic.assertions.impl.LazyThreadUnsafeAssertionGroup
import ch.tutteli.atrium.logic.createDescriptiveAssertion
import ch.tutteli.atrium.logic.creating.iterable.contains.IterableLikeContains
import ch.tutteli.atrium.logic.creating.iterable.contains.creators.impl.mapSubjectToList
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.impl.NoOpSearchBehaviourImpl
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.impl.NotSearchBehaviourImpl
import ch.tutteli.atrium.logic.creating.iterable.contains.steps.NotCheckerStep
import ch.tutteli.atrium.logic.creating.iterable.contains.steps.impl.EntryPointStepImpl
import ch.tutteli.atrium.logic.creating.iterable.contains.steps.notCheckerStep
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike
import ch.tutteli.atrium.logic.extractFeature
import ch.tutteli.atrium.translations.DescriptionBasic.NOT_TO_HAVE
import ch.tutteli.atrium.translations.DescriptionBasic.TO_HAVE
import ch.tutteli.atrium.translations.DescriptionIterableLikeExpectation.*

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
    ): FeatureExtractorBuilder.ExecutionStep<T, E> = collect(container, converter, "min", Iterable<E>::minOrNull)

    override fun <T : IterableLike, E : Comparable<E>> max(
        container: AssertionContainer<T>,
        converter: (T) -> Iterable<E>
    ): FeatureExtractorBuilder.ExecutionStep<T, E> = collect(container, converter, "max", Iterable<E>::maxOrNull)

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

    override fun <T : IterableLike, E : Any> hasNotNextOrAll(
        container: AssertionContainer<T>,
        converter: (T) -> Iterable<E?>,
        assertionCreatorOrNull: (Expect<E>.() -> Unit)?
    ): Assertion = allWithMismatchClauseAndDecorator(
        container,
        converter,
        assertionCreatorOrNull,
        NOT_TO_HAVE_ELEMENTS_OR_ALL,
        mismatchIf = { holds -> !holds },
        decorateAssertion = { assertionGroup, _ ->
            // no decoration necessary, return assertionGroup as is
            assertionGroup
        }
    )

    override fun <T : IterableLike, E : Any> hasNotNextOrNone(
        container: AssertionContainer<T>,
        converter: (T) -> Iterable<E?>,
        assertionCreatorOrNull: (Expect<E>.() -> Unit)?
    ): Assertion = allWithMismatchClauseAndDecorator(
        container,
        converter,
        assertionCreatorOrNull,
        NOT_TO_HAVE_ELEMENTS_OR_NONE,
        mismatchIf = { holds -> holds },
        decorateAssertion = { assertionGroup, _ ->
            // no decoration necessary, return assertionGroup as is
            assertionGroup
        }
    )

    //TODO 1.3.0 remove suppress again, use InlineElement instead
    @Suppress("DEPRECATION")
    private fun <E : Any, T : IterableLike> allWithMismatchClauseAndDecorator(
        container: AssertionContainer<T>,
        converter: (T) -> Iterable<E?>,
        assertionCreatorOrNull: (Expect<E>.() -> Unit)?,
        description: ch.tutteli.atrium.reporting.translating.Translatable,
        mismatchIf: (Boolean) -> Boolean,
        decorateAssertion: (AssertionGroup, AssertionContainer<List<E?>>) -> AssertionGroup
    ): LazyThreadUnsafeAssertionGroup = LazyThreadUnsafeAssertionGroup {
        val listAssertionContainer = container.mapSubjectToList(converter)
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
        val listAssertionContainer = container.mapSubjectToList(converter)
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
        val listAssertionContainer = container.mapSubjectToList(converter)
        val list = listAssertionContainer.maybeSubject.getOrElse { emptyList() }

        val lookupHashMap = HashMap<E, Int>()
        val duplicateIndices = HashMap<Int, Pair<E, MutableList<Int>>>()

        list.forEachIndexed { index, element ->
            lookupHashMap[element]?.let {
                duplicateIndices.getOrPut(it) {
                    Pair(element, mutableListOf())
                }.second.add(index)
            } ?: let {
                lookupHashMap[element] = index
            }
        }

        //TODO 1.3.0 replace with Representable and remove suppression
        @Suppress("DEPRECATION")
        val duplicates = duplicateIndices
            .map { (index, pair) ->
                val (element, indices) = pair
                assertionBuilder.descriptive
                    .failing
                    .withHelpOnFailure {
                        assertionBuilder.explanatoryGroup
                            .withDefaultType
                            .withExplanatoryAssertion(
                                ch.tutteli.atrium.reporting.translating.TranslatableWithArgs(
                                    DUPLICATED_BY,
                                    indices.joinToString(", ")
                                )
                            )
                            .build()
                    }
                    .showForAnyFailure
                    .withDescriptionAndRepresentation(
                        ch.tutteli.atrium.reporting.translating.TranslatableWithArgs(INDEX, index),
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

    override fun <T : IterableLike, E> last(
        container: AssertionContainer<T>,
        converter: (T) -> Iterable<E>
    ): FeatureExtractorBuilder.ExecutionStep<T, E> =
        container.extractFeature
            .methodCall("last")
            .withRepresentationForFailure(NO_ELEMENTS)
            .withFeatureExtraction {
                val iterable = converter(it)
                Option.someIf(iterable.iterator().hasNext()) {
                    iterable.last()
                }
            }
            .withoutOptions()
            .build()
}
