//TODO remove file with 1.0.0
@file:Suppress("DEPRECATION")
package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.changers.ChangedSubjectPostStep
import kotlin.reflect.KClass

/**
 * The access point to an implementation of [Fun0Assertions].
 *
 * It loads the implementation lazily via [loadSingleService].
 */
val fun0Assertions by lazy { loadSingleService(Fun0Assertions::class) }


/**
 * Defines the minimum set of assertion functions and builders applicable to lambdas with arity 0
 * (i.e. a lambda with 0 arguments or in other words `() -> R`),
 * which an implementation of the domain of Atrium has to provide.
 */
@Deprecated(
    "Use Fun0Assertions from atrium-logic; will be removed with 1.0.0",
    ReplaceWith("ch.tutteli.atrium.logic.Fun0Assertions")
)
interface Fun0Assertions {

    fun <TExpected : Throwable> isThrowing(
        expect: Expect<out () -> Any?>,
        expectedType: KClass<TExpected>
    ): ChangedSubjectPostStep<*, TExpected>

    fun <R, T : () -> R> isNotThrowing(expect: Expect<T>): ChangedSubjectPostStep<*, R>
}
