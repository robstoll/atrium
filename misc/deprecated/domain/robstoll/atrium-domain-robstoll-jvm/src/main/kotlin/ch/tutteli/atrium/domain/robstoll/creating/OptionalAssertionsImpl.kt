//TODO remove file with 1.0.0
@file:Suppress(
    "DEPRECATION",
    /* TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE"
)

package ch.tutteli.atrium.domain.robstoll.creating

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.OptionalAssertions
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.domain.robstoll.lib.creating._isEmpty
import ch.tutteli.atrium.domain.robstoll.lib.creating._isPresent
import java.util.*

class OptionalAssertionsImpl : OptionalAssertions {

    override fun <T : Optional<*>> isEmpty(expect: Expect<T>) = _isEmpty(expect)
    override fun <E, T : Optional<E>> isPresent(expect: Expect<T>): ExtractedFeaturePostStep<T, E> = _isPresent(expect)
}
