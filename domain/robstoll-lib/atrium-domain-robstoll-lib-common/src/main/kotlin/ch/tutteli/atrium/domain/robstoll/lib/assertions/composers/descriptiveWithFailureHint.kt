package ch.tutteli.atrium.domain.robstoll.lib.assertions.composers

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.PlantHasNoSubjectException
import ch.tutteli.atrium.assertions.builders.fixedClaimGroup
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.reporting.translating.Translatable

fun _createDescriptiveWithFailureHint(
    description: Translatable,
    representation: Any,
    test: () -> Boolean,
    showHint: () -> Boolean,
    failureHintFactory: () -> Assertion
): Assertion {
    //TODO remove try catch with 1.0.0, should no longer be necessary
    val holds = try {
        test()
    } catch (e: PlantHasNoSubjectException) {
        true
    }
    return if (holds || !showHint()) {
        AssertImpl.builder.createDescriptive(description, representation){ holds }
    } else {
        AssertImpl.builder.fixedClaimGroup
            .withListType
            .failing
            .withDescriptionAndRepresentation(description, representation)
            .withAssertion(failureHintFactory())
            .build()
    }
}
