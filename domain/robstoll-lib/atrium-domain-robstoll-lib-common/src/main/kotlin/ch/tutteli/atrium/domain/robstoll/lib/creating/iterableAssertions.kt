package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.fixedClaimGroup
import ch.tutteli.atrium.assertions.builders.invisibleGroup
import ch.tutteli.atrium.core.falseProvider
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.domain.robstoll.lib.assertions.LazyThreadUnsafeAssertionGroup
import ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.allCreatedAssertionsHold
import ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.builders.IterableContainsBuilder
import ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.createExplanatoryAssertionGroup
import ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.createHasElementAssertion
import ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.searchbehaviours.NoOpSearchBehaviourImpl
import ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.searchbehaviours.NotSearchBehaviourImpl
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.translations.DescriptionBasic
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import ch.tutteli.atrium.translations.DescriptionIterableAssertion.ALL
import ch.tutteli.atrium.translations.DescriptionIterableAssertion.INDEX
import ch.tutteli.atrium.translations.DescriptionIterableAssertion.WARNING_MISMATCHES
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
        val list = assertionContainer.maybeSubject.fold({ emptyList<E>() }) { it.toList() }
        val hasElementAssertion = createHasElementAssertion(list)

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

fun <E : Any> _hasNext(expect: Expect<Iterable<E>>): Assertion =
    ExpectImpl.builder.createDescriptive(expect,
        DescriptionBasic.HAS, RawString.create(DescriptionIterableAssertion.NEXT_ELEMENT)) { it.iterator().hasNext() }

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
