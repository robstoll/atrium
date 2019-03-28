package ch.tutteli.atrium.domain.robstoll.lib.creating.any.typetransformation.failurehandlers

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.core.polyfills.fullName
import ch.tutteli.atrium.core.polyfills.stackBacktrace
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.translations.DescriptionThrowableAssertion
import ch.tutteli.atrium.translations.DescriptionThrowableAssertion.OCCURRED_EXCEPTION_PROPERTIES
import kotlin.reflect.KClass

class ThrowableThrownFailureHandler<out TExpected : Throwable>(
    private val throwable: Throwable?,
    private val expectedType: KClass<TExpected>
) : ExplanatoryFailureHandlerBase<Throwable, TExpected>() {

    override fun createFailingAssertion(description: Translatable, representation: Any): Assertion {
        val propertiesOfOtherException = {
            //cannot/shouldn't be null since we checked it in showHint (or it needs to be checked on the outside)
            propertiesOfException(throwable!!, maxStackTrace = 7)
        }
        return AssertImpl.any.typeTransformation.failureHandlers.newExplanatoryWithHint<Any, TExpected>(
            showHint = { throwable != null && !expectedType.isInstance(throwable) },
            failureHintFactory = propertiesOfOtherException
        ).createFailingAssertion(description, representation)
    }

    companion object {
        fun propertiesOfException(throwable: Throwable, maxStackTrace: Int): AssertionGroup {
            return AssertImpl.builder.explanatoryGroup
                .withDefaultType
                .withAssertions(
                    AssertImpl.builder.explanatory
                        .withDescription(
                            TranslatableWithArgs(
                                OCCURRED_EXCEPTION_PROPERTIES,
                                throwable::class.simpleName ?: throwable::class.fullName
                            )
                        )
                        .build(),
                    createHints(throwable, maxStackTrace, secondStackFrameOfParent = null)
                )
                .build()
        }


        private fun createHints(throwable: Throwable, maxStackTrace: Int, secondStackFrameOfParent: String?): Assertion {
            val assertions = mutableListOf(
                createMessageHint(throwable),
                createStackTraceHint(throwable, maxStackTrace, secondStackFrameOfParent)
            )
            assertions.addAll(createAdditionalHints(throwable, maxStackTrace))
            createCauseHint(throwable, maxStackTrace)?.let { assertions.add(it) }

            return AssertImpl.builder.explanatoryGroup
                .withDefaultType
                .withAssertions(assertions.toList())
                .build()
        }

        private fun createMessageHint(throwable: Throwable) = AssertImpl.builder.descriptive
            .holding
            .withDescriptionAndRepresentation(
                DescriptionThrowableAssertion.OCCURRED_EXCEPTION_MESSAGE,
                throwable.message
            )
            .build()

        private fun createStackTraceHint(
            throwable: Throwable,
            maxStackTrace: Int,
            secondStackFrameOfParent: String?
        ): Assertion {
            val stackTrace = if (secondStackFrameOfParent != null) {
                throwable.stackBacktrace.takeWhile { it != secondStackFrameOfParent }
            } else {
                throwable.stackBacktrace
            }
            val assertions = stackTrace.asSequence()
                .take(maxStackTrace)
                .map {
                    AssertImpl.builder.explanatory.withDescription(RawString.create(it)).build()
                }
                .let {
                    if (stackTrace.size > maxStackTrace) {
                        it.plus(AssertImpl.builder.explanatory.withDescription(RawString.create("...")).build())
                    } else {
                        it
                    }
                }.toList()

            return AssertImpl.builder.list
                .withDescriptionAndEmptyRepresentation(DescriptionThrowableAssertion.OCCURRED_EXCEPTION_STACKTRACE)
                .withAssertions(assertions)
                .build()
        }


        private fun createCauseHint(throwable: Throwable, maxStackTrace: Int): AssertionGroup?
            = throwable.cause?.let { cause ->
                createChildHint(throwable, cause, DescriptionThrowableAssertion.OCCURRED_EXCEPTION_CAUSE, maxStackTrace)
            }

        fun createChildHint(
            throwable: Throwable,
            child: Throwable,
            childDescription: Translatable,
            maxStackTrace: Int
        ): AssertionGroup{
            val secondStackTrace = if (throwable.stackBacktrace.size > 1) throwable.stackBacktrace[1] else null
            return AssertImpl.builder.list
                .withDescriptionAndRepresentation(childDescription, child)
                .withAssertion(createHints(child, maxStackTrace, secondStackTrace))
                .build()
        }

    }
}

expect fun createAdditionalHints(throwable: Throwable, maxStackTrace: Int): List<Assertion>
