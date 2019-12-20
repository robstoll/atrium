@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.domain.robstoll.lib.creating.any.typetransformation.failurehandlers

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.reporting.translating.Translatable
import kotlin.reflect.KClass

@Deprecated("Use SubjectChanger.FailureHandler.createThrowableThrownFailureHandler; will be removed with 1.0.0")
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
            "Use ch.tutteli.atrium.domain.creating.changers.utils.propertiesOfThrowable",
            ReplaceWith("ch.tutteli.atrium.domain.creating.changers.utils.propertiesOfThrowable(throwable, maxStackTrace)")
        )
        fun propertiesOfException(throwable: Throwable, maxStackTrace: Int): AssertionGroup =
            ch.tutteli.atrium.domain.creating.changers.utils.propertiesOfThrowable(throwable, maxStackTrace)
    }
}

@Deprecated(
    "Use ch.tutteli.atrium.domain.creating.changers.utils.createAdditionalHints; will be removed with 1.0.0",
    ReplaceWith("ch.tutteli.atrium.domain.creating.changers.utils.createAdditionalHints(throwable, maxStackTrace)")
)
fun createAdditionalHints(throwable: Throwable, maxStackTrace: Int): List<Assertion> =
    ch.tutteli.atrium.domain.creating.changers.utils.createAdditionalHints(throwable, maxStackTrace)
