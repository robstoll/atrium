//TODO remove file with 1.0.0
@file:Suppress("DEPRECATION", "OVERRIDE_BY_INLINE", "NOTHING_TO_INLINE")

package ch.tutteli.atrium.domain.builders.kotlin_1_3.creating

import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.domain.kotlin_1_3.creating.ResultAssertions
import ch.tutteli.atrium.domain.kotlin_1_3.creating.resultAssertions
import kotlin.reflect.KClass

/**
 * Delegates inter alia to the implementation of [ResultAssertions].
 * In detail, it implements [ResultAssertions] by delegating to [resultAssertions]
 * which in turn delegates to the implementation via [loadSingleService].
 */
@Deprecated("Use _logic instead; will be removed with 1.0.0")
object ResultAssertionsBuilder : ResultAssertions {

    override inline fun <E, T : Result<E>> isSuccess(expect: Expect<T>): ExtractedFeaturePostStep<T, E> =
        resultAssertions.isSuccess(expect)

    override fun <TExpected : Throwable> isFailure(expect: Expect<out Result<*>>, expectedType: KClass<TExpected>) =
        resultAssertions.isFailure(expect, expectedType)
}
