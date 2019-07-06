package ch.tutteli.atrium.domain.robstoll.lib.creating.collectors

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.builders.invisibleGroup
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.BaseAssertionPlant
import ch.tutteli.atrium.creating.BaseCollectingAssertionPlant
import ch.tutteli.atrium.creating.CollectingAssertionContainer
import ch.tutteli.atrium.domain.builders.AssertImpl

fun <T> _collect(
    maybeSubject: Option<T>,
    assertionCreator: CollectingAssertionContainer<T>.() -> Unit
): AssertionGroup {
    val collectingPlant = AssertImpl.coreFactory.newCollectingAssertionContainer(maybeSubject)
    assertionCreator(collectingPlant)
    val collectedAssertions = collectingPlant.getAssertions()
    check(collectedAssertions.isNotEmpty()) {
        "There was not any assertion created. Did you forget to add it to the assertion container?"
    }
    return AssertImpl.builder.invisibleGroup.withAssertions(collectedAssertions).build()
}


fun <T, A : BaseAssertionPlant<T, A>, C : BaseCollectingAssertionPlant<T, A, C>> _collectAssertions(
    subjectProvider: () -> T,
    collectingPlantFactory: (() -> T) -> C,
    subPlantAndAssertionCreator: C.() -> Unit
): AssertionGroup {
    val collectingPlant = collectingPlantFactory(subjectProvider)
    subPlantAndAssertionCreator(collectingPlant)
    val collectedAssertions = collectingPlant.getAssertions()
    check(collectedAssertions.isNotEmpty()) {
        "There was not any assertion created. Did you forget to add it to the plant?"
    }
    return AssertImpl.builder.invisibleGroup.withAssertions(collectedAssertions).build()
}
