@file:Suppress(
    // TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE"
)

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import java.time.LocalDateTime
import java.util.*

/**
 * Collection of assertion functions and builders which are applicable to subjects with an [Optional] type.
 */
interface OptionalAssertions {
    fun <T : Optional<*>> isEmpty(container: AssertionContainer<T>): Assertion
    fun <E, T : Optional<E>> isPresent(container: AssertionContainer<T>): ExtractedFeaturePostStep<T, E>
}

