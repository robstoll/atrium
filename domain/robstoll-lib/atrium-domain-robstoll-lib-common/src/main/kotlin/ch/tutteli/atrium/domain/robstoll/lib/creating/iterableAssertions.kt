package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.builders.invisibleGroup
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.builders.assertions.builders.fixedClaimGroup
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.domain.robstoll.lib.assertions.LazyThreadUnsafeAssertionGroup
import ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.builders.IterableContainsBuilder
import ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.allCreatedAssertionsHold
import ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.createExplanatoryAssertions
import ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.createHasElementAssertion
import ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.searchbehaviours.NoOpSearchBehaviourImpl
import ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.searchbehaviours.NotSearchBehaviourImpl
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.translations.DescriptionIterableAssertion.*
import ch.tutteli.kbox.mapWithIndex

fun <E, T : Iterable<E>> _containsBuilder(plant: AssertionPlant<T>): IterableContains.Builder<E, T, NoOpSearchBehaviour> =
    IterableContainsBuilder(plant, NoOpSearchBehaviourImpl())

fun <E, T : Iterable<E>> _containsNotBuilder(plant: AssertionPlant<T>): IterableContains.Builder<E, T, NotSearchBehaviour> =
    IterableContainsBuilder(plant, NotSearchBehaviourImpl())

fun <E : Any> _iterableAll(
    plant: AssertionPlant<Iterable<E?>>,
    assertionCreator: (AssertionPlant<E>.() -> Unit)?
): Assertion {
    return LazyThreadUnsafeAssertionGroup {
        val list = plant.subject.toList()
        val hasElementAssertion = createHasElementAssertion(list)

        val assertions = ArrayList<Assertion>(2)
        assertions.add(createExplanatoryAssertionGroup(assertionCreator, list))

        val mismatches = createMismatchAssertions(plant, assertionCreator)
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

private fun <E : Any, T : Iterable<E?>> createMismatchAssertions(
    plant: AssertionPlant<T>,
    assertionCreator: (AssertionPlant<E>.() -> Unit)?
): List<Assertion> {
    return plant.subject
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
    val explanatoryAssertions = createExplanatoryAssertions(assertionCreator, list)
    return AssertImpl.builder.explanatoryGroup
        .withDefaultType
        .withAssertions(explanatoryAssertions)
        .build()
}
