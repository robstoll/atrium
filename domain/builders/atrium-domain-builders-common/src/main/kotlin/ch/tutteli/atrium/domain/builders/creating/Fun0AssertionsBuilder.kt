@file:Suppress("OVERRIDE_BY_INLINE", "NOTHING_TO_INLINE")

package ch.tutteli.atrium.domain.builders.creating

import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.Fun0Assertions
import ch.tutteli.atrium.domain.creating.fun0Assertions
import kotlin.reflect.KClass

/**
 * Delegates inter alia to the implementation of [Fun0Assertions].
 * In detail, it implements [Fun0Assertions] by delegating to [fun0Assertions]
 * which in turn delegates to the implementation via [loadSingleService].
 */
object Fun0AssertionsBuilder : Fun0Assertions {

    override inline fun <TExpected : Throwable> isThrowing(
        assertionContainer: Expect<out () -> Any?>,
        expectedType: KClass<TExpected>
    ) = fun0Assertions.isThrowing(assertionContainer, expectedType)

    override inline fun <R, T : () -> R> isNotThrowing(assertionContainer: Expect<T>) =
        fun0Assertions.isNotThrowing(assertionContainer)
}
