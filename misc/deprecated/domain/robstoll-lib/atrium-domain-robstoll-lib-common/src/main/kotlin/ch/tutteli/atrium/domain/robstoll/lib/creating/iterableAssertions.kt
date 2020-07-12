//TODO remove file with 1.0.0
@file:Suppress("DEPRECATION")
package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.assertions.builders.fixedClaimGroup
import ch.tutteli.atrium.assertions.builders.invisibleGroup
import ch.tutteli.atrium.core.Option
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
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.translations.DescriptionBasic
import ch.tutteli.atrium.translations.DescriptionIterableAssertion.*
import ch.tutteli.kbox.mapWithIndex

fun <E, T : Iterable<E>> _containsBuilder(subjectProvider: SubjectProvider<T>): IterableContains.Builder<E, T, NoOpSearchBehaviour> =
    IterableContainsBuilder(subjectProvider, NoOpSearchBehaviourImpl())

fun <E, T : Iterable<E>> _containsNotBuilder(subjectProvider: SubjectProvider<T>): IterableContains.Builder<E, T, NotSearchBehaviour> =
    IterableContainsBuilder(subjectProvider, NotSearchBehaviourImpl())

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("use the function from atrium-logic instead, will be removed with 1.0.0")
fun <E : Any, T : Iterable<E?>> _iterableAll(
    expect: Expect<T>,
    assertionCreatorOrNull: (Expect<E>.() -> Unit)?
): Assertion {
    return LazyThreadUnsafeAssertionGroup {
        val list = turnSubjectToList(expect).maybeSubject.getOrElse { emptyList() }
        val hasElementAssertion = createHasElementAssertion(list.iterator())

        val assertions = ArrayList<Assertion>(2)
        assertions.add(createExplanatoryAssertionGroup(assertionCreatorOrNull, list))

        val mismatches = createMismatchAssertions(list, assertionCreatorOrNull)
        assertions.add(
            assertionBuilder.explanatoryGroup
                .withWarningType
                .withAssertion(
                    assertionBuilder.list
                        .withDescriptionAndEmptyRepresentation(WARNING_MISMATCHES)
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
            assertionBuilder.createDescriptive(TranslatableWithArgs(INDEX, index), element, falseProvider)
        }
        .toList()
}

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("use the function from atrium-logic instead, will be removed with 1.0.0")
fun <E, T : Iterable<E>> _hasNext(expect: Expect<T>): Assertion =
    assertionBuilder.createDescriptive(expect, DescriptionBasic.HAS, NEXT_ELEMENT) {
        it.iterator().hasNext()
    }

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("use the function from atrium-logic instead, will be removed with 1.0.0")
fun <E, T : Iterable<E>> _hasNotNext(expect: Expect<T>): Assertion =
    assertionBuilder.createDescriptive(expect, DescriptionBasic.HAS_NOT, NEXT_ELEMENT) {
        !it.iterator().hasNext()
    }

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("use the function from atrium-logic instead, will be removed with 1.0.0")
fun <E : Comparable<E>, T : Iterable<E>> _min(expect: Expect<T>): ExtractedFeaturePostStep<T, E> =
    collect(expect, "min", Iterable<E>::min)

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("use the function from atrium-logic instead, will be removed with 1.0.0")
fun <E : Comparable<E>, T : Iterable<E>> _max(expect: Expect<T>): ExtractedFeaturePostStep<T, E> =
    collect(expect, "max", Iterable<E>::max)

private fun <E : Comparable<E>, T : Iterable<E>> collect(
    expect: Expect<T>,
    method: String,
    collect: T.() -> E?
): ExtractedFeaturePostStep<T, E> {
    return ExpectImpl.feature.extractor(expect)
        .methodCall(method)
        .withRepresentationForFailure(NO_ELEMENTS)
        .withFeatureExtraction {
            Option.someIf(it.iterator().hasNext()) {
                it.collect() ?: throw IllegalStateException(
                    "Iterable does not haveNext() even though checked before! Concurrent access?"
                )
            }
        }
        .withoutOptions()
        .build()
}
