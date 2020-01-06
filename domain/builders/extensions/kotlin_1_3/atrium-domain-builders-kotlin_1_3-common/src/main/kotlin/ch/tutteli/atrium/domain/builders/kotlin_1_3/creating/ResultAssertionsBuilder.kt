@file:Suppress("OVERRIDE_BY_INLINE", "NOTHING_TO_INLINE")

package ch.tutteli.atrium.domain.builders.kotlin_1_3.creating

import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.changers.ChangedSubjectPostStep
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.domain.creating.changers.PostFinalStep
import ch.tutteli.atrium.domain.kotlin_1_3.creating.resultAssertions
import ch.tutteli.atrium.domain.kotlin_1_3.creating.ResultAssertions
import kotlin.reflect.KClass

/**
 * Delegates inter alia to the implementation of [ResultAssertions].
 * In detail, it implements [ResultAssertions] by delegating to [resultAssertions]
 * which in turn delegates to the implementation via [loadSingleService].
 */
object ResultAssertionsBuilder : ResultAssertions {

    override inline fun <E, T : Result<E>> isSuccess(
        assertionContainer: Expect<T>
    ) :  ExtractedFeaturePostStep<T, E> = resultAssertions.isSuccess(assertionContainer)

    override fun <TExpected : Throwable> isFailure(
        assertionContainer: Expect<out Result<Any?>>,
        expectedType: KClass<TExpected>
    ) = resultAssertions.isFailure(assertionContainer, expectedType)
}
