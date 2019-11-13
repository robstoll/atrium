package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.fixedClaimGroup
import ch.tutteli.atrium.assertions.builders.invisibleGroup
import ch.tutteli.atrium.core.falseProvider
import ch.tutteli.atrium.core.getOrElse
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.domain.robstoll.lib.assertions.LazyThreadUnsafeAssertionGroup
import ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.allCreatedAssertionsHold
import ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.builders.IterableContainsBuilder
import ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.createExplanatoryAssertionGroup
import ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.createHasElementAssertion
import ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.creators.turnSubjectToList
import ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.searchbehaviours.NoOpSearchBehaviourImpl
import ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.searchbehaviours.NotSearchBehaviourImpl
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.translations.DescriptionBasic
import ch.tutteli.atrium.translations.DescriptionIterableAssertion.*
import ch.tutteli.kbox.mapWithIndex

fun <E, T : Iterable<E>> _containsBuilder(subjectProvider: SubjectProvider<T>): IterableContains.Builder<E, T, NoOpSearchBehaviour> =
    IterableContainsBuilder(subjectProvider, NoOpSearchBehaviourImpl())

fun <E, T : Iterable<E>> _containsNotBuilder(subjectProvider: SubjectProvider<T>): IterableContains.Builder<E, T, NotSearchBehaviour> =
    IterableContainsBuilder(subjectProvider, NotSearchBehaviourImpl())

fun <E : Any, T : Iterable<E?>> _iterableAll(
    assertionContainer: Expect<T>,
    assertionCreatorOrNull: (Expect<E>.() -> Unit)?
): Assertion {
    return LazyThreadUnsafeAssertionGroup {
        val list = turnSubjectToList(assertionContainer).maybeSubject.getOrElse { emptyList() }
        val hasElementAssertion = createHasElementAssertion(list.iterator())

        val assertions = ArrayList<Assertion>(2)
        assertions.add(createExplanatoryAssertionGroup(assertionCreatorOrNull, list))

        val mismatches = createMismatchAssertions(list, assertionCreatorOrNull)
        assertions.add(
            ExpectImpl.builder.explanatoryGroup
                .withWarningType
                .withAssertion(
                    ExpectImpl.builder.list
                        .withDescriptionAndEmptyRepresentation(WARNING_MISMATCHES)
                        .withAssertions(mismatches)
                        .build()
                )
                .build()
        )

        ExpectImpl.builder.invisibleGroup
            .withAssertions(
                hasElementAssertion,
                ExpectImpl.builder.fixedClaimGroup
                    .withListType
                    .withClaim(mismatches.isEmpty())
                    .withDescriptionAndEmptyRepresentation(ALL)
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
            ExpectImpl.builder.createDescriptive(TranslatableWithArgs(INDEX, index), element, falseProvider)
        }
        .toList()
}

fun <E, T : Iterable<E>> _hasNext(assertionContainer: Expect<T>): Assertion =
    ExpectImpl.builder.createDescriptive(assertionContainer, DescriptionBasic.HAS, RawString.create(NEXT_ELEMENT)) {
        it.iterator().hasNext()
    }

fun <E, T : Iterable<E>> _hasNotNext(assertionContainer: Expect<T>): Assertion =
    ExpectImpl.builder.createDescriptive(assertionContainer, DescriptionBasic.HAS_NOT, RawString.create(NEXT_ELEMENT)) {
        !it.iterator().hasNext()
    }

fun <E : Comparable<E>, T : Iterable<E>> _min(assertionContainer: Expect<T>): ExtractedFeaturePostStep<T, E> =
    collect(assertionContainer, "min", Iterable<E>::min)

fun <E : Comparable<E>, T : Iterable<E>> _max(assertionContainer: Expect<T>): ExtractedFeaturePostStep<T, E> =
    collect(assertionContainer, "max", Iterable<E>::max)

private fun <E : Comparable<E>, T : Iterable<E>> collect(
    assertionContainer: Expect<T>,
    method: String,
    collect: T.() -> E?
): ExtractedFeaturePostStep<T, E> {
    return ExpectImpl.feature.extractor(assertionContainer)
        .methodCall(method)
        .withRepresentationForFailure(NO_ELEMENTS)
        .withCheck { it.iterator().hasNext() }
        .withFeatureExtraction {
            it.collect() ?: throw IllegalStateException(
                "Iterable does not haveNext() even though checked before! Concurrent access?"
            )
        }.build()
}
