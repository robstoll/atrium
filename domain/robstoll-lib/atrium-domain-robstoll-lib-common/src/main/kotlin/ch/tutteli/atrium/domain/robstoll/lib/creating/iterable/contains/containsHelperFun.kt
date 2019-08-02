package ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains

import ch.tutteli.atrium.api.cc.en_GB.property
import ch.tutteli.atrium.api.cc.en_GB.toBe
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.core.*
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.assertions.builders.fixedClaimGroup
import ch.tutteli.atrium.domain.builders.creating.collectors.collectAssertions
import ch.tutteli.atrium.domain.robstoll.lib.assertions.LazyThreadUnsafeAssertionGroup
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.translations.DescriptionBasic
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import ch.tutteli.atrium.translations.DescriptionIterableAssertion.*
import ch.tutteli.kbox.ifWithinBound
import ch.tutteli.kbox.mapRemainingWithCounter

internal fun <E : Any> createExplanatoryAssertionGroup(
    assertionCreator: (Expect<E>.() -> Unit)?,
    list: List<E?>
): AssertionGroup = createExplanatoryAssertionGroup(assertionCreator) {
    list.asSequence().filterNotNull().map { Some(it) }.firstOrNull() ?: None
}

internal inline fun <E : Any> createExplanatoryAssertionGroup(
    noinline assertionCreator: (Expect<E>.() -> Unit)?,
    firstOrNull: () -> Option<E>
): AssertionGroup {
    return ExpectImpl.builder.explanatoryGroup
        .withDefaultType
        .let {
            if (assertionCreator != null) {
                it.collectAssertions(firstOrNull(), assertionCreator)
            } else {
                it.withAssertion(
                    // it is for an explanatoryGroup where it does not matter if the assertion holds or not, thus trueProvider
                    ExpectImpl.builder.createDescriptive(DescriptionBasic.IS, RawString.NULL, trueProvider)
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
            false to RawString.create(SIZE_EXCEEDED)
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
): AssertionGroup {
    @Suppress("DEPRECATION")
    return ExpectImpl.collector.collect({ iterableAsList }) {
        property(Collection<*>::size) {
            toBe(expectedSize)
            if (iterableAsList.size > expectedSize) {
                addAssertion(LazyThreadUnsafeAssertionGroup {
                    val assertions = itr.mapRemainingWithCounter { counter, it ->
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
                                .withAssertions(assertions)
                                .build()
                        )
                        .build()
                })
            }
        }
    }
}

internal fun createHasElementAssertion(iterable: Iterable<*>): AssertionGroup {
    val hasElement = iterable.iterator().hasNext()
    return ExpectImpl.builder.feature
        .withDescriptionAndRepresentation(HAS_ELEMENT, RawString.create(hasElement.toString()))
        .withAssertion(
            ExpectImpl.builder.createDescriptive(DescriptionBasic.IS, RawString.create(true.toString())) { hasElement }
        )
        .build()
}
