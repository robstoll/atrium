@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.domain.robstoll.lib.creating.any.typetransformation.failurehandlers

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.robstoll.lib.creating.throwable.thrown.creators.ThrowableThrownFailureHandler
import ch.tutteli.atrium.reporting.translating.Translatable
import kotlin.reflect.KClass

@Deprecated(
    "Use ThrowableThrownFailureHandler in package throwable.thrown; will be removed with 1.0.0",
    ReplaceWith("ch.tutteli.atrium.domain.robstoll.lib.creating.throwable.thrown.creators.ThrowableThrownFailureHandler")
)
class ThrowableThrownFailureHandler<out TExpected : Throwable>(
    private val throwable: Throwable?,
    private val expectedType: KClass<TExpected>
) : ExplanatoryFailureHandlerBase<Throwable, TExpected>() {

    override fun createFailingAssertion(description: Translatable, representation: Any): Assertion {
        @Suppress("DEPRECATION")
        return AssertImpl.any.typeTransformation.failureHandlers.newExplanatoryWithHint<Any, TExpected>(
            showHint = { throwable != null && !expectedType.isInstance(throwable) },
            failureHintFactory = {
                //cannot/shouldn't be null since we checked it in showHint (or it needs to be checked on the outside)
                propertiesOfException(throwable!!, maxStackTrace = 7)
            }
        ).createFailingAssertion(description, representation)
    }

    companion object {
        @Deprecated(
            "Use throwable.thrown.creators.ThrowableThrownFailureHandler.propertiesOfThrowable",
            ReplaceWith("ch.tutteli.atrium.domain.robstoll.lib.creating.throwable.thrown.creators.ThrowableThrownFailureHandler.propertiesOfThrowable(throwable, maxStackTrace)")
        )
        fun propertiesOfException(throwable: Throwable, maxStackTrace: Int): AssertionGroup =
            ThrowableThrownFailureHandler.propertiesOfThrowable(throwable, maxStackTrace)

        @Deprecated(
            "Use throwable.thrown.creators.ThrowableThrownFailureHandler.createChildHint",
            ReplaceWith("ch.tutteli.atrium.domain.robstoll.lib.creating.throwable.thrown.creators.ThrowableThrownFailureHandler.createChildHint(throwable, child, childDescription, maxStackTrace)")
        )
        fun createChildHint(
            throwable: Throwable,
            child: Throwable,
            childDescription: Translatable,
            maxStackTrace: Int
        ): AssertionGroup = ThrowableThrownFailureHandler.createChildHint(
            throwable, child, childDescription, maxStackTrace
        )
    }
}

@Deprecated(
    "use the function from package throwable.thrown; will be removed with 1.0.0",
    ReplaceWith("ch.tutteli.atrium.domain.robstoll.lib.creating.throwable.thrown.creators.createAdditionalHints(throwable, maxStackTrace)")
)
fun createAdditionalHints(throwable: Throwable, maxStackTrace: Int): List<Assertion> =
    ch.tutteli.atrium.domain.robstoll.lib.creating.throwable.thrown.creators.createAdditionalHints(
        throwable, maxStackTrace
    )
