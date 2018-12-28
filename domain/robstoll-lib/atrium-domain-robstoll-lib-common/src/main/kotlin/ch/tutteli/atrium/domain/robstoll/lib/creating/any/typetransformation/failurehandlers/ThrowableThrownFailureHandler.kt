package ch.tutteli.atrium.domain.robstoll.lib.creating.any.typetransformation.failurehandlers

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.core.polyfills.fullName
import ch.tutteli.atrium.core.polyfills.stackBacktrace
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.translations.DescriptionThrowableAssertion
import kotlin.reflect.KClass

class ThrowableThrownFailureHandler<out TExpected : Throwable>(
    private val throwable: Throwable?,
    private val expectedType: KClass<TExpected>
) : ExplanatoryFailureHandlerBase<Throwable, TExpected>() {

    override fun createFailingAssertion(description: Translatable, representation: Any): Assertion {
        val propertiesOfOtherException = {
            throwable!! //cannot be null since we checked it in showHint (see below)
            AssertImpl.builder.explanatoryGroup
                .withDefaultType
                .withAssertions(
                    AssertImpl.builder.explanatory
                        .withDescription(TranslatableWithArgs(
                            DescriptionThrowableAssertion.OCCURRED_EXCEPTION_PROPERTIES,
                            throwable::class.simpleName ?: throwable::class.fullName
                        ))
                        .build(),
                    AssertImpl.builder.explanatoryGroup
                        .withDefaultType
                        .withAssertions(createMessageHint(throwable), createStackTraceHint(throwable))
                        .build()
                )
                .build()
        }
        return AssertImpl.any.typeTransformation.failureHandlers.newExplanatoryWithHint<Any, TExpected>(
            showHint = { throwable != null && !expectedType.isInstance(throwable) },
            failureHintFactory = propertiesOfOtherException
        ).createFailingAssertion(description, representation)
    }

    private fun createMessageHint(throwable: Throwable): DescriptiveAssertion {
        return AssertImpl.builder.descriptive
            .holding
            .withDescriptionAndNullableRepresentation(
                DescriptionThrowableAssertion.OCCURRED_EXCEPTION_MESSAGE, throwable.message
            )
            .build()
    }

    private fun createStackTraceHint(throwable: Throwable): DescriptiveAssertion {
        val stackTrace = throwable.stackBacktrace
        val stackTraceAsString = stackTrace.asSequence().take(MAX_NUM_OF_STACK).joinToString("\n") +
            if (stackTrace.size > MAX_NUM_OF_STACK) "\n..." else ""

        return AssertImpl.builder.descriptive
            .holding
            .withDescriptionAndNullableRepresentation(
                DescriptionThrowableAssertion.OCCURRED_EXCEPTION_STACKTRACE, RawString.create(stackTraceAsString)
            )
            .build()
    }

    companion object {
        private const val MAX_NUM_OF_STACK = 7
    }
}
