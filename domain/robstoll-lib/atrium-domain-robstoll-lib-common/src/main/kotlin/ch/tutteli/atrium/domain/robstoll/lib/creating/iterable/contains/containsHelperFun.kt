package ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains

import ch.tutteli.atrium.api.cc.en_GB.property
import ch.tutteli.atrium.api.cc.en_GB.toBe
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.MaybeSubject
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.builders.assertions.builders.fixedClaimGroup
import ch.tutteli.atrium.domain.robstoll.lib.assertions.LazyThreadUnsafeAssertionGroup
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.translations.DescriptionBasic
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import ch.tutteli.atrium.translations.DescriptionIterableAssertion.*
import ch.tutteli.kbox.ifWithinBound
import ch.tutteli.kbox.mapRemainingWithCounter

internal fun <E : Any> createExplanatoryAssertions(
    assertionCreator: (AssertionPlant<E>.() -> Unit)?,
    list: List<E?>
): List<Assertion> {
    return when {
        list.isNotEmpty() ->
            collectIterableAssertionsForExplanationWithFirst(assertionCreator, list.firstOrNull { it != null })
        else ->
            collectIterableAssertionsForExplanation(assertionCreator, MaybeSubject.Absent)
    }
}

internal fun <E : Any> collectIterableAssertionsForExplanationWithFirst(
    assertionCreator: (AssertionPlant<E>.() -> Unit)?,
    first: E?
): List<Assertion> {
    return if (first != null) {
        collectIterableAssertionsForExplanation(assertionCreator, MaybeSubject.Present(first))
    } else {
        collectIterableAssertionsForExplanation(CANNOT_EVALUATE_SUBJECT_ONLY_NULL, assertionCreator, MaybeSubject.Absent)
    }
}

internal fun <E : Any> collectIterableAssertionsForExplanation(
    assertionCreator: (AssertionPlant<E>.() -> Unit)?,
    maybeSubject: MaybeSubject<E>
) = collectIterableAssertionsForExplanation(CANNOT_EVALUATE_SUBJECT_EMPTY_ITERABLE, assertionCreator, maybeSubject)

internal fun <E : Any> collectIterableAssertionsForExplanation(
    warningCannotEvaluate: Translatable,
    assertionCreator: (AssertionPlant<E>.() -> Unit)?,
    maybeSubject: MaybeSubject<E>
) = AssertImpl.collector
    .forExplanation
    .throwIfNoAssertionIsCollected
    .collect(warningCannotEvaluate, maybeSubject, assertionCreator)

internal fun createEntryAssertion(explanatoryAssertions: List<Assertion>, found: Boolean): AssertionGroup {
    val explanatoryGroup = AssertImpl.builder.explanatoryGroup
        .withDefaultType
        .withAssertions(explanatoryAssertions)
        .build()
    return AssertImpl.builder.fixedClaimGroup
        .withListType
        .withClaim(found)
        .withDescriptionAndEmptyRepresentation(AN_ENTRY_WHICH)
        .withAssertion(explanatoryGroup)
        .build()
}

@Suppress(
    "USELESS_CAST"
    //TODO remove if https://youtrack.jetbrains.com/issue/KT-24917 is fixed
)
internal fun <E : Any> allCreatedAssertionsHold(
    subject: E?,
    assertionCreator: (AssertionPlant<E>.() -> Unit)?
): Boolean = when (subject) {
    null -> assertionCreator == null
    else -> assertionCreator != null &&
        coreFactory.newCheckingPlant { subject as E }
            .addAssertionsCreatedBy(assertionCreator)
            .allAssertionsHold()
}

internal fun <E, SC> createEntryAssertionTemplate(
    subjectProvider: () -> List<E>,
    index: Int,
    searchCriterion: SC,
    entryWithIndex: DescriptionIterableAssertion,
    matches: (E, SC) -> Boolean
): ((() -> Boolean) -> Assertion) -> AssertionGroup {
    return { createEntryFeatureAssertion ->
        val list = subjectProvider()
        val (found, entryRepresentation) = list.ifWithinBound(index, {
            val entry = list[index]
            Pair(matches(entry, searchCriterion), entry ?: RawString.NULL)
        }, {
            Pair(false, RawString.create(DescriptionIterableAssertion.SIZE_EXCEEDED))
        })
        val description = TranslatableWithArgs(entryWithIndex, index)
        AssertImpl.builder.feature
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
    return AssertImpl.collector.collect({ iterableAsList }) {
        property(Collection<*>::size) {
            toBe(expectedSize)
            if (iterableAsList.size > expectedSize) {
                addAssertion(LazyThreadUnsafeAssertionGroup {
                    val assertions = itr.mapRemainingWithCounter { counter, it ->
                        val description = TranslatableWithArgs(ENTRY_WITH_INDEX, expectedSize + counter)
                        AssertImpl.builder.descriptive
                            .holding
                            .withDescriptionAndRepresentation(description, it)
                            .build()
                    }

                    AssertImpl.builder.explanatoryGroup
                        .withWarningType
                        .withAssertion(
                            AssertImpl.builder.list
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

internal fun <E> createHasElementAssertion(iterable: Iterable<E>): AssertionGroup {
    val hasElement = iterable.iterator().hasNext()
    return AssertImpl.builder.feature
        .withDescriptionAndRepresentation(HAS_ELEMENT, RawString.create(hasElement.toString()))
        .withAssertion(
            AssertImpl.builder.createDescriptive(DescriptionBasic.IS, RawString.create(true.toString())) { hasElement }
        )
        .build()
}
