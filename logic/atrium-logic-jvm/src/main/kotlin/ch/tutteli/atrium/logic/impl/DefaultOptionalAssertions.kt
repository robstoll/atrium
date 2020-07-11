@file:Suppress(
    // TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE"
)

package ch.tutteli.atrium.logic.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.logic.OptionalAssertions
import ch.tutteli.atrium.logic.createDescriptiveAssertion
import ch.tutteli.atrium.logic.extractFeature
import ch.tutteli.atrium.translations.DescriptionBasic.IS
import ch.tutteli.atrium.translations.DescriptionOptionalAssertion.*
import java.util.*

class DefaultOptionalAssertions : OptionalAssertions {

    override fun <T : Optional<*>> isEmpty(container: AssertionContainer<T>): Assertion =
        container.createDescriptiveAssertion(IS, EMPTY) { !it.isPresent }

    override fun <E, T : Optional<E>> isPresent(container: AssertionContainer<T>): ExtractedFeaturePostStep<T, E> =
        container.extractFeature
            .withDescription(GET)
            .withRepresentationForFailure(IS_NOT_PRESENT)
            .withFeatureExtraction {
                Option.someIf(it.isPresent) { it.get() }
            }
            .withoutOptions()
            .build()
}

