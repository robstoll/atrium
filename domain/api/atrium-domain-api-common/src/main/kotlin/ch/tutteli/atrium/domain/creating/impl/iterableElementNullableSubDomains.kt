package ch.tutteli.atrium.domain.creating.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.assertions.builders.fixedClaimGroup
import ch.tutteli.atrium.assertions.builders.invisibleGroup
import ch.tutteli.atrium.core.*
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.assertions.LazyThreadUnsafeAssertionGroup
import ch.tutteli.atrium.domain.creating.IterableDomain
import ch.tutteli.atrium.domain.creating.IterableElementNullableDomain
import ch.tutteli.atrium.domain.creating.IterableElementNullableSubDomain
import ch.tutteli.atrium.domain.creating.collectors.assertionCollector
import ch.tutteli.atrium.domain.creating.collectors.collectAssertions
import ch.tutteli.atrium.domain.creating.iterable.contains.creators.turnSubjectToList
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.translations.DescriptionBasic
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import ch.tutteli.kbox.mapWithIndex

internal class IterableElementNullableDomainImpl<E : Any, T : Iterable<E?>>(
    iterableElementNullableSubDomain: IterableElementNullableSubDomain<E, T>,
    iterableDomain: IterableDomain<E?, T>
) : IterableElementNullableDomain<E, T>,
    IterableElementNullableSubDomain<E, T> by iterableElementNullableSubDomain,
    IterableDomain<E?, T> by iterableDomain {

    override val expect: Expect<T> = iterableElementNullableSubDomain.expect
}

internal class IterableElementNullableSubDomainImpl<E : Any, T : Iterable<E?>>(
    override val expect: Expect<T>
) : IterableElementNullableSubDomain<E, T> {

    override fun all(assertionCreatorOrNull: (Expect<E>.() -> Unit)?): Assertion {
        return LazyThreadUnsafeAssertionGroup {
            val list =
                turnSubjectToList(expect)
                    .maybeSubject.getOrElse { emptyList() }
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


    private fun createHasElementAssertion(iterator: Iterator<*>): AssertionGroup {
        val hasElement = iterator.hasNext()
        return assertionBuilder.feature
            .withDescriptionAndRepresentation(
                DescriptionIterableAssertion.HAS_ELEMENT,
                RawString.create(hasElement.toString())
            )
            .withAssertion(
                assertionBuilder.createDescriptive(
                    DescriptionBasic.IS,
                    RawString.create(true.toString())
                ) { hasElement }
            )
            .build()
    }

    private fun <E : Any> createExplanatoryAssertionGroup(
        assertionCreatorOrNull: (Expect<E>.() -> Unit)?,
        list: List<E?>
    ): AssertionGroup = createExplanatoryAssertionGroup(assertionCreatorOrNull) {
        list.asSequence().filterNotNull().map { Some(it) }.firstOrNull() ?: None
    }

    private inline fun <E : Any> createExplanatoryAssertionGroup(
        noinline assertionCreatorOrNull: (Expect<E>.() -> Unit)?,
        firstOrNull: () -> Option<E>
    ): AssertionGroup {
        return assertionBuilder.explanatoryGroup
            .withDefaultType
            .let {
                if (assertionCreatorOrNull != null) {
                    it.collectAssertions(firstOrNull(), assertionCreatorOrNull)
                } else {
                    it.withAssertion(
                        // it is for an explanatoryGroup where it does not matter if the assertion holds or not
                        // thus it is OK to use trueProvider
                        assertionBuilder.createDescriptive(
                            DescriptionBasic.IS,
                            RawString.NULL,
                            trueProvider
                        )
                    )
                }
            }
            .build()
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
                    TranslatableWithArgs(
                        DescriptionIterableAssertion.INDEX,
                        index
                    ), element,
                    falseProvider
                )
            }
            .toList()
    }

    private fun <E : Any> allCreatedAssertionsHold(
        subject: E?,
        assertionCreator: (Expect<E>.() -> Unit)?
    ): Boolean = when (subject) {
        null -> assertionCreator == null
        else -> assertionCreator != null && assertionCollector.collect(Some(subject), assertionCreator).holds()
    }
}
