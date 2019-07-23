@file:Suppress("OVERRIDE_BY_INLINE", "NOTHING_TO_INLINE")
package ch.tutteli.atrium.domain.builders.creating

import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.OptionalAssertions
import ch.tutteli.atrium.domain.creating.optionalAssertions
import java.util.*

/**
 * Delegates inter alia to the implementation of [OptionalAssertions].
 * In detail, it implements [OptionalAssertions] by delegating to [optionalAssertions]
 * which in turn delegates to the implementation via [loadSingleService].
 */
object OptionalAssertionsBuilder : OptionalAssertions {

    override inline fun <T> isEmpty(assertionContainer: Expect<Optional<T>>)
        = optionalAssertions.isEmpty(assertionContainer)

}
