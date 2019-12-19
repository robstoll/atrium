@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.MaybeSubject
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import ch.tutteli.atrium.translations.DescriptionIterableAssertion.CANNOT_EVALUATE_SUBJECT_EMPTY_ITERABLE
import ch.tutteli.atrium.translations.DescriptionIterableAssertion.CANNOT_EVALUATE_SUBJECT_ONLY_NULL
import ch.tutteli.kbox.ifWithinBound

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("Will be removed with 1.0.0")
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

@Suppress("DeprecatedCallableAddReplaceWith", "DEPRECATION")
@Deprecated("Will be removed with 1.0.0")
internal fun <E : Any> collectIterableAssertionsForExplanationWithFirst(
    assertionCreator: (AssertionPlant<E>.() -> Unit)?,
    first: E?
): List<Assertion> {
    return if (first != null) {
        collectIterableAssertionsForExplanation(assertionCreator, MaybeSubject.Present(first))
    } else {
        collectIterableAssertionsForExplanation(
            CANNOT_EVALUATE_SUBJECT_ONLY_NULL,
            assertionCreator,
            MaybeSubject.Absent
        )
    }
}

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("Will be removed with 1.0.0")
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


@Suppress("DeprecatedCallableAddReplaceWith", "DEPRECATION")
@Deprecated("Will be removed with 1.0.0")
internal fun <E : Any> allCreatedAssertionsHold(
    subject: E?,
    assertionCreator: (AssertionPlant<E>.() -> Unit)?
): Boolean = when (subject) {
    null -> assertionCreator == null
    else -> assertionCreator != null &&
        coreFactory.newCheckingPlant {
            //TODO remove if https://youtrack.jetbrains.com/issue/KT-24917 is fixed
            @Suppress("USELESS_CAST")
            subject as E
        }
            .addAssertionsCreatedBy(assertionCreator)
            .allAssertionsHold()
}

@Suppress("DeprecatedCallableAddReplaceWith", "DEPRECATION")
@Deprecated("Will be removed with 1.0.0")
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
        assertionBuilder.feature
            .withDescriptionAndRepresentation(description, entryRepresentation)
            .withAssertion(createEntryFeatureAssertion { found })
            .build()
    }
}

@Suppress("DeprecatedCallableAddReplaceWith", "DEPRECATION")
@Deprecated("Will be removed with 1.0.0")
internal fun createEntryAssertion(explanatoryAssertions: List<Assertion>, found: Boolean): AssertionGroup {
    val explanatoryGroup = assertionBuilder.explanatoryGroup
        .withDefaultType
        .withAssertions(explanatoryAssertions)
        .build()
    return createEntryAssertion(explanatoryGroup, found)
}
