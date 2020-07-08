//TODO remove file with 1.0.0
@file:Suppress("DEPRECATION")
package ch.tutteli.atrium.domain.kotlin_1_3.creating

import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.changers.ChangedSubjectPostStep
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import kotlin.reflect.KClass

/**
 * The access point to an implementation of [ResultAssertions].
 *
 * It loads the implementation lazily via [loadSingleService].
 */
val resultAssertions by lazy { loadSingleService(ResultAssertions::class) }

/**
 * Defines the minimum set of assertion functions and builders applicable to [Result],
 * which an implementation of the domain of Atrium has to provide.
 */
@Deprecated(
    "Use ResultAssertions from atrium-logic; will be removed with 1.0.0",
    ReplaceWith("ch.tutteli.atrium.logic.kotlin_1_3.ResultAssertions")
)
interface ResultAssertions {
    fun <E, T : Result<E>> isSuccess(expect: Expect<T>): ExtractedFeaturePostStep<T, E>

    fun <TExpected : Throwable> isFailure(
        expect: Expect<out Result<*>>,
        expectedType: KClass<TExpected>
    ): ChangedSubjectPostStep<Throwable?, TExpected>
}
