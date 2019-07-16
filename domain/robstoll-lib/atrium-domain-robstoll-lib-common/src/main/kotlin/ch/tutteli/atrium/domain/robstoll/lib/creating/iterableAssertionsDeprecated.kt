package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.builders.invisibleGroup
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.builders.assertions.builders.fixedClaimGroup
import ch.tutteli.atrium.domain.robstoll.lib.assertions.LazyThreadUnsafeAssertionGroup
import ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.allCreatedAssertionsHold
import ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.createExplanatoryAssertions
import ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.createHasElementAssertion
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.translations.DescriptionIterableAssertion.*
import ch.tutteli.kbox.mapWithIndex

fun <E : Any> _iterableAll(
    plant: AssertionPlant<Iterable<E?>>,
    assertionCreator: (AssertionPlant<E>.() -> Unit)?
): Assertion {
    return LazyThreadUnsafeAssertionGroup {
        val list = plant.maybeSubject.fold({ emptyList<E>() }) { it.toList() }
        val hasElementAssertion = createHasElementAssertion(list)

        val assertions = ArrayList<Assertion>(2)
        assertions.add(createExplanatoryAssertionGroup(assertionCreator, list))

        val mismatches = createMismatchAssertions(list, assertionCreator)
        assertions.add(AssertImpl.builder.explanatoryGroup
            .withWarningType
            .withAssertion(AssertImpl.builder.list
                .withDescriptionAndEmptyRepresentation(WARNING_MISMATCHES)
                .withAssertions(mismatches)
                .build()
            )
            .build()
        )

        AssertImpl.builder.invisibleGroup
            .withAssertions(
                hasElementAssertion,
                AssertImpl.builder.fixedClaimGroup
                    .withListType
                    .withClaim(mismatches.isEmpty())
                    .withDescriptionAndEmptyRepresentation(ALL)
                    .withAssertions(assertions)
                    .build()
            )
            .build()
    }
}

@Suppress("DEPRECATION")
private fun <E : Any> createMismatchAssertions(
    list: List<E?>,
    assertionCreator: (AssertionPlant<E>.() -> Unit)?
): List<Assertion> {
    return list
        .asSequence()
        .mapWithIndex()
        .filter { (_, element) -> !allCreatedAssertionsHold(element, assertionCreator) }
        .map { (index, element) ->
            AssertImpl.builder.createDescriptive(TranslatableWithArgs(INDEX, index), element) {
                allCreatedAssertionsHold(element, assertionCreator)
            }
        }
        .toList()
}

private fun <E : Any> createExplanatoryAssertionGroup(
    assertionCreator: (AssertionPlant<E>.() -> Unit)?,
    list: List<E?>
): AssertionGroup {
    @Suppress("DEPRECATION")
    val explanatoryAssertions = createExplanatoryAssertions(assertionCreator, list)
    return AssertImpl.builder.explanatoryGroup
        .withDefaultType
        .withAssertions(explanatoryAssertions)
        .build()
}
