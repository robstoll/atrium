package ch.tutteli.atrium.domain.robstoll.lib.assertions.composers

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.fixedClaimGroup
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.reporting.translating.Translatable

@Deprecated("will be removed with 1.0.0")
fun _createDescriptiveWithFailureHint(
    description: Translatable,
    representation: Any,
    test: () -> Boolean,
    showHint: () -> Boolean,
    failureHintFactory: () -> Assertion
): Assertion {
    val holds = try {
        test()
    } catch (@Suppress("DEPRECATION") e: ch.tutteli.atrium.creating.PlantHasNoSubjectException) {
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
