package ch.tutteli.atrium.domain.robstoll.lib.assertions.composers

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.PlantHasNoSubjectException
import ch.tutteli.atrium.domain.builders.assertions.builders.fixHoldsGroup
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.reporting.translating.Translatable

fun _createDescriptiveWithFailureHint(
    description: Translatable,
    representation: Any,
    test: () -> Boolean,
    showHint: () -> Boolean,
    failureHintFactory: () -> Assertion
): Assertion {
    val holds = try {
        test()
    } catch (e: PlantHasNoSubjectException) {
        true //TODO that's a hack, do we have a better solution?
    }
    return if (holds || !showHint()) {
        AssertImpl.builder.descriptive
            .withTest({ holds })
            .create(description, representation)
    } else {
        AssertImpl.builder
            .fixHoldsGroup(description, representation)
            .withListType
            .failing
            .create(failureHintFactory())
    }
}
