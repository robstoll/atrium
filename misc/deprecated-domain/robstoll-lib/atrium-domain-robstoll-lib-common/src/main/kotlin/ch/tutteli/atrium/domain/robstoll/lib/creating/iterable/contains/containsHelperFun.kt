package ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains

import ch.tutteli.atrium.api.fluent.en_GB.feature
import ch.tutteli.atrium.api.fluent.en_GB.toBe
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.builders.fixedClaimGroup
import ch.tutteli.atrium.core.*
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.builders.creating.collectors.collectAssertions
import ch.tutteli.atrium.domain.robstoll.lib.assertions.LazyThreadUnsafeAssertionGroup
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.translations.DescriptionBasic
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import ch.tutteli.atrium.translations.DescriptionIterableAssertion.*
import ch.tutteli.kbox.ifWithinBound
import ch.tutteli.kbox.mapRemainingWithCounter

internal fun <E : Any> createExplanatoryAssertionGroup(
    assertionCreatorOrNull: (Expect<E>.() -> Unit)?,
    list: List<E?>
): AssertionGroup = createExplanatoryAssertionGroup(assertionCreatorOrNull) {
    list.asSequence().filterNotNull().map { Some(it) }.firstOrNull() ?: None
}

internal inline fun <E : Any> createExplanatoryAssertionGroup(
    noinline assertionCreatorOrNull: (Expect<E>.() -> Unit)?,
    firstOrNull: () -> Option<E>
): AssertionGroup {
    return ExpectImpl.builder.explanatoryGroup
        .withDefaultType
        .let {
            if (assertionCreatorOrNull != null) {
                it.collectAssertions(firstOrNull(), assertionCreatorOrNull)
            } else {
                it.withAssertion(
                    // it is for an explanatoryGroup where it does not matter if the assertion holds or not
                    // thus it is OK to use trueProvider
                    ExpectImpl.builder.createDescriptive(DescriptionBasic.IS, Text.NULL, trueProvider)
                )
            }
        }
        .build()
}

internal fun createEntryAssertion(explanatoryGroup: AssertionGroup, found: Boolean): AssertionGroup {
    return ExpectImpl.builder.fixedClaimGroup
        .withListType
        .withClaim(found)
        .withDescriptionAndEmptyRepresentation(AN_ENTRY_WHICH)
        .withAssertion(explanatoryGroup)
        .build()
}

internal fun <E : Any> allCreatedAssertionsHold(
    subject: E?,
    assertionCreator: (Expect<E>.() -> Unit)?
): Boolean = when (subject) {
    null -> assertionCreator == null
    else -> assertionCreator != null && ExpectImpl.collector.collect(Some(subject), assertionCreator).holds()
}

internal fun <E, SC> createEntryAssertionTemplate(
    maybeSubject: Option<List<E>>,
    index: Int,
    searchCriterion: SC,
    entryWithIndex: DescriptionIterableAssertion,
    matches: (E, SC) -> Boolean
): ((() -> Boolean) -> Assertion) -> AssertionGroup {
    return { createEntryFeatureAssertion ->
        val list = maybeSubject.getOrElse { emptyList() }
        val (found, entryRepresentation) = list.ifWithinBound(index, {
            val entry = list[index]
            matches(entry, searchCriterion) to entry
        }, {
            false to SIZE_EXCEEDED
        })
        val description = TranslatableWithArgs(entryWithIndex, index)
        ExpectImpl.builder.feature
            .withDescriptionAndRepresentation(description, entryRepresentation)
            .withAssertion(createEntryFeatureAssertion { found })
            .build()
    }
}

internal fun <E> createSizeFeatureAssertionForInOrderOnly(
    expectedSize: Int,
    iterableAsList: List<E?>,
    itr: Iterator<E?>
): Assertion {
    return ExpectImpl.collector.collect(Some(iterableAsList)) {
        feature(Collection<*>::size) {
            toBe(expectedSize)
            if (iterableAsList.size > expectedSize) {
                addAssertion(LazyThreadUnsafeAssertionGroup {
                    val additionalEntries = itr.mapRemainingWithCounter { counter, it ->
                        val description = TranslatableWithArgs(ENTRY_WITH_INDEX, expectedSize + counter)
                        ExpectImpl.builder.descriptive
                            .holding
                            .withDescriptionAndRepresentation(description, it)
                            .build()
                    }

                    ExpectImpl.builder.explanatoryGroup
                        .withWarningType
                        .withAssertion(
                            ExpectImpl.builder.list
                                .withDescriptionAndEmptyRepresentation(WARNING_ADDITIONAL_ENTRIES)
                                .withAssertions(additionalEntries)
                                .build()
                        )
                        .build()
                })
            }
        }
    }
}

internal fun createHasElementAssertion(iterator: Iterator<*>): AssertionGroup {
    val hasElement = iterator.hasNext()
    return ExpectImpl.builder.feature
        .withDescriptionAndRepresentation(HAS_ELEMENT, Text(hasElement.toString()))
        .withAssertion(
            ExpectImpl.builder.createDescriptive(DescriptionBasic.IS, Text(true.toString())) { hasElement }
        )
        .build()
}
