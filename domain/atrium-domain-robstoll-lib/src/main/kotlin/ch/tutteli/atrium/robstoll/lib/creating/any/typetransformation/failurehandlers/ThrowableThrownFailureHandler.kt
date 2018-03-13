package ch.tutteli.atrium.robstoll.lib.creating.any.typetransformation.failurehandlers

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.AssertionBuilder
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.translations.DescriptionThrowableAssertion
import kotlin.reflect.KClass

class ThrowableThrownFailureHandler<out TExpected : Throwable>(
    private val throwable: Throwable?,
    private val expectedType: KClass<TExpected>
) : ExplanatoryFailureHandlerBase<Throwable, TExpected>() {

    override fun createFailingAssertion(description: Translatable, representation: Any): Assertion {
        val messageOfOtherException = {
            AssertionBuilder.explanatoryGroup.withDefault.create(
                AssertionBuilder.descriptive.create(
                    DescriptionThrowableAssertion.OCCURRED_EXCEPTION_MESSAGE,
                    throwable?.localizedMessage ?: RawString.NULL,
                    true
                )
            )
        }
        return ExplanatoryFailureHandlerWithHint<Any, TExpected>(
            showHint = { throwable != null && !expectedType.isInstance(throwable) },
            failureHintFactory = messageOfOtherException
        ).createFailingAssertion(description, representation)
    }
}
