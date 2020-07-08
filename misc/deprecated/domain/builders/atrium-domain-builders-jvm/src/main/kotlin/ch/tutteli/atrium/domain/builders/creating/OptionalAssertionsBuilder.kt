//TODO remove file with 1.0.0
@file:Suppress(
    "DEPRECATION",
    "OVERRIDE_BY_INLINE",
    "NOTHING_TO_INLINE",
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE" /* TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */
)

package ch.tutteli.atrium.domain.builders.creating

import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.OptionalAssertions
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.domain.creating.optionalAssertions
import java.util.*

/**
 * Delegates inter alia to the implementation of [OptionalAssertions].
 * In detail, it implements [OptionalAssertions] by delegating to [optionalAssertions]
 * which in turn delegates to the implementation via [loadSingleService].
 */
object OptionalAssertionsBuilder : OptionalAssertions {

    override inline fun <T : Optional<*>> isEmpty(expect: Expect<T>) =
        optionalAssertions.isEmpty(expect)

    override inline fun <E, T : Optional<E>> isPresent(expect: Expect<T>): ExtractedFeaturePostStep<T, E> =
        optionalAssertions.isPresent(expect)
}
