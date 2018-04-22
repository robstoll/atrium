package ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.creators

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.DefaultListAssertionGroupType
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.builders.assertions.builders.fixHoldsGroup
import ch.tutteli.atrium.domain.robstoll.lib.assertions.LazyThreadUnsafeAssertionGroup
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.reporting.translating.Untranslatable
import ch.tutteli.atrium.translations.DescriptionAnyAssertion
import ch.tutteli.atrium.translations.DescriptionIterableAssertion.*

internal fun <E : Any> createExplanatoryAssertions(
    assertionCreator: (AssertionPlant<E>.() -> Unit)?,
    list: List<E?>
): List<Assertion> {
    return when {
        list.isNotEmpty() ->
            collectIterableAssertionsForExplanationWithFirst(assertionCreator, list.firstOrNull { it != null })
        else ->
            collectIterableAssertionsForExplanation(assertionCreator, null)
    }
}

internal fun <E : Any> collectIterableAssertionsForExplanationWithFirst(
    assertionCreator: (AssertionPlant<E>.() -> Unit)?,
    first: E?
): List<Assertion> {
    return if (first != null) {
        collectIterableAssertionsForExplanation(assertionCreator, first)
    } else {
        collectIterableAssertionsForExplanation(CANNOT_EVALUATE_SUBJECT_ONLY_NULL, assertionCreator, null)
    }
}

internal fun <E : Any> collectIterableAssertionsForExplanation(
    assertionCreator: (AssertionPlant<E>.() -> Unit)?,
    subject: E?
) = collectIterableAssertionsForExplanation(CANNOT_EVALUATE_SUBJECT_EMPTY_ITERABLE, assertionCreator, subject)

internal fun <E : Any> collectIterableAssertionsForExplanation(
    description: Translatable,
    assertionCreator: (AssertionPlant<E>.() -> Unit)?,
    subject: E?
) = AssertImpl.collector
    .forExplanation
    .throwIfNoAssertionIsCollected
    .collect(description, assertionCreator, subject)

internal fun createEntryAssertion(explanatoryAssertions: List<Assertion>, found: Boolean): AssertionGroup {
    return AssertImpl.builder.fixHoldsGroup.create(
        AN_ENTRY_WHICH,
        RawString.EMPTY,
        found,
        DefaultListAssertionGroupType,
        AssertImpl.builder.explanatoryGroup.withDefault.create(explanatoryAssertions)
    )
}

internal fun <E : Any> allCreatedAssertionsHold(
    subject: E?,
    assertionCreator: (AssertionPlant<E>.() -> Unit)?
): Boolean = when (subject) {
    null -> assertionCreator == null
    else -> assertionCreator != null &&
        coreFactory.newCheckingPlant(subject)
            .addAssertionsCreatedBy(assertionCreator)
            .allAssertionsHold()
}

fun <E> createSizeFeatureAssertionForInOrderOnly(
    expectedSize: Int,
    iterableAsList: List<E?>,
    itr: Iterator<E?>
): AssertionGroup {
    val additionalEntries = mutableListOf<E?>()
    val actualSize = iterableAsList.size
    while (itr.hasNext()) {
        additionalEntries.add(itr.next())
    }
    val featureAssertions = mutableListOf<Assertion>()
    featureAssertions.add(AssertImpl.builder.descriptive.create(
        DescriptionAnyAssertion.TO_BE, RawString.create(expectedSize.toString()), { actualSize == expectedSize }
    ))
    if (actualSize > expectedSize) {
        featureAssertions.add(LazyThreadUnsafeAssertionGroup {
            val assertions = additionalEntries.mapIndexed { index, it ->
                val description = TranslatableWithArgs(ENTRY_WITH_INDEX, expectedSize + index)
                AssertImpl.builder.descriptive.create(description, it ?: RawString.NULL, true)
            }
            with(AssertImpl.builder) {
                explanatoryGroup.withWarning.create(
                    list(WARNING_ADDITIONAL_ENTRIES, RawString.EMPTY)
                        .create(assertions)
                )
            }
        })
    }
    return AssertImpl.builder
        .feature(Untranslatable(additionalEntries::size.name), RawString.create(actualSize.toString()))
        .create(featureAssertions)
}
