package ch.tutteli.atrium.assertions.iterable.contains.creators

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.creating.AssertionCollector
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Translatable


internal fun <E : Any> createExplanatoryAssertions(assertionCreator: (AssertionPlant<E>.() -> Unit)?, list: List<E?>) = when {
    list.isNotEmpty() -> collectIterableAssertionsForExplanationWithFirst(assertionCreator, list.firstOrNull { it != null })
    else -> collectIterableAssertionsForExplanation(assertionCreator, null)
}

internal fun <E : Any> collectIterableAssertionsForExplanationWithFirst(assertionCreator: (AssertionPlant<E>.() -> Unit)?, first: E?): List<Assertion> {
    return if (first != null) {
        collectIterableAssertionsForExplanation(assertionCreator, first)
    } else {
        collectIterableAssertionsForExplanation(
            DescriptionIterableAssertion.CANNOT_EVALUATE_SUBJECT_ONLY_NULL,
            assertionCreator,
            null)
    }
}

internal fun <E : Any> collectIterableAssertionsForExplanation(assertionCreator: (AssertionPlant<E>.() -> Unit)?, subject: E?)
    = collectIterableAssertionsForExplanation(
    DescriptionIterableAssertion.CANNOT_EVALUATE_SUBJECT_EMPTY_ITERABLE,
    assertionCreator,
    subject)

internal fun <E : Any> collectIterableAssertionsForExplanation(description: Translatable, assertionCreator: (AssertionPlant<E>.() -> Unit)?, subject: E?)
    = AssertionCollector
    .throwIfNoAssertionIsCollected
    .collectAssertionsForExplanation(description, assertionCreator, subject)

internal fun createEntryAssertion(explanatoryAssertions: List<Assertion>, found: Boolean)
    = FixHoldsAssertionGroup(
    DefaultListAssertionGroupType,
    DescriptionIterableAssertion.AN_ENTRY_WHICH,
    RawString.EMPTY,
    listOf(AssertionGroupBuilder.explanatory.withDefault.create(explanatoryAssertions)),
    found)

internal fun <E : Any> allCreatedAssertionsHold(subject: E?, assertionCreator: (AssertionPlant<E>.() -> Unit)?): Boolean = when (subject) {
    null -> assertionCreator == null
    else -> assertionCreator != null &&
        AtriumFactory.newCheckingPlant(subject)
            .addAssertionsCreatedBy(assertionCreator)
            .allAssertionsHold()
}
