package ch.tutteli.atrium.domain.creating.changers.utils

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.ExplanatoryAssertionGroupType
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.core.polyfills.fullName
import ch.tutteli.atrium.core.polyfills.stackBacktrace
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.translations.DescriptionThrowableAssertion

/**
 * Returns an [AssertionGroup] with an [ExplanatoryAssertionGroupType] containing properties
 * of the given [throwable] where [maxStackTrace] are shown.
 */
fun propertiesOfThrowable(
    throwable: Throwable,
    maxStackTrace: Int,
    explanation: Assertion = createExplanation(throwable)
): AssertionGroup {
    return assertionBuilder.explanatoryGroup
        .withDefaultType
        .withAssertions(
            explanation,
            createHints(throwable, maxStackTrace, secondStackFrameOfParent = null)
        )
        .build()
}

private fun createExplanation(throwable: Throwable) =
    assertionBuilder.explanatory
        .withExplanation(
            DescriptionThrowableAssertion.OCCURRED_EXCEPTION_PROPERTIES,
            throwable::class.simpleName ?: throwable::class.fullName
        )
        .build()

private fun createHints(
    throwable: Throwable,
    maxStackTrace: Int,
    secondStackFrameOfParent: String?
): Assertion {
    val assertions = mutableListOf(
        createMessageHint(throwable),
        createStackTraceHint(
            throwable,
            maxStackTrace,
            secondStackFrameOfParent
        )
    )
    @Suppress("DEPRECATION" /* TODO remove annotation with 1.0.0 */)
    assertions.addAll(createAdditionalHints(throwable, maxStackTrace))
    createCauseHint(throwable, maxStackTrace)?.let { assertions.add(it) }

    return assertionBuilder.explanatoryGroup
        .withDefaultType
        .withAssertions(assertions.toList())
        .build()
}

private fun createMessageHint(throwable: Throwable) =
    assertionBuilder.descriptive
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
            assertionBuilder.explanatory.withExplanation(RawString.create(it)).build()
        }
        .let {
            if (stackTrace.size > maxStackTrace) {
                it.plus(assertionBuilder.explanatory.withExplanation(RawString.create("...")).build())
            } else {
                it
            }
        }.toList()

    return assertionBuilder.list
        .withDescriptionAndEmptyRepresentation(DescriptionThrowableAssertion.OCCURRED_EXCEPTION_STACKTRACE)
        .withAssertions(assertions)
        .build()
}


private fun createCauseHint(throwable: Throwable, maxStackTrace: Int): AssertionGroup? =
    throwable.cause?.let { cause ->
        createChildHint(
            throwable,
            cause,
            DescriptionThrowableAssertion.OCCURRED_EXCEPTION_CAUSE,
            maxStackTrace
        )
    }

/**
 * Creates a hint for a given [child] of the given [throwable] using the given [childDescription]
 * and displaying [maxStackTrace].
 */
internal fun createChildHint(
    throwable: Throwable,
    child: Throwable,
    childDescription: Translatable,
    maxStackTrace: Int
): AssertionGroup {
    val secondStackTrace = if (throwable.stackBacktrace.size > 1) throwable.stackBacktrace[1] else null
    return assertionBuilder.list
        .withDescriptionAndRepresentation(childDescription, child)
        .withAssertion(
            createHints(
                child,
                maxStackTrace,
                secondStackTrace
            )
        )
        .build()
}


/**
 * Hook for platform specific additional hints.
 */
@Deprecated("not really deprecated but will be made internal with 1.0.0")
expect fun createAdditionalHints(throwable: Throwable, maxStackTrace: Int): List<Assertion>
