package ch.tutteli.atrium.logic.creating.transformers


import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.ExplanatoryAssertionGroupType
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.core.polyfills.fullName
import ch.tutteli.atrium.core.polyfills.stackBacktrace
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.ExperimentalComponentFactoryContainer
import ch.tutteli.atrium.creating.build
import ch.tutteli.atrium.logic.creating.transformers.impl.createAdditionalHints
import ch.tutteli.atrium.reporting.AtriumErrorAdjuster
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.translations.DescriptionThrowableExpectation.*

//TODO 1.3.0 deprecate
/**
 * Returns an [AssertionGroup] with an [ExplanatoryAssertionGroupType] containing properties
 * of the given [throwable].
 *
 * @since 1.3.0
 */
@OptIn(ExperimentalComponentFactoryContainer::class)
fun propertiesOfThrowable(
    throwable: Throwable,
    container: AssertionContainer<*>,
    explanation: Assertion = createExplanation(throwable)
): AssertionGroup {
    container.components.build<AtriumErrorAdjuster>().adjust(throwable)
    return assertionBuilder.explanatoryGroup
        .withInformationType(withIndent = false)
        .withAssertions(
            explanation,
            createHints(throwable, secondStackFrameOfParent = null)
        )
        .build()
}

private fun createExplanation(throwable: Throwable) =
    assertionBuilder.explanatory
        .withExplanation(
            OCCURRED_EXCEPTION_PROPERTIES,
            throwable::class.simpleName ?: throwable::class.fullName
        )
        .build()


private fun createHints(
    throwable: Throwable,
    secondStackFrameOfParent: String?
): Assertion {
    val assertions = mutableListOf(
        createMessageHint(throwable),
        createStackTraceHint(throwable, secondStackFrameOfParent)
    )
    assertions.addAll(createAdditionalHints(throwable))
    createCauseHint(throwable)?.let { assertions.add(it) }

    return assertionBuilder.explanatoryGroup
        .withDefaultType
        .withAssertions(assertions)
        .build()
}

private fun createMessageHint(throwable: Throwable) =
    assertionBuilder.descriptive
        .holding
        .withDescriptionAndRepresentation(
            OCCURRED_EXCEPTION_MESSAGE,
            throwable.message
        )
        .build()

private fun createStackTraceHint(
    throwable: Throwable,
    secondStackFrameOfParent: String?
): Assertion {
    val stackTrace = if (secondStackFrameOfParent != null) {
        throwable.stackBacktrace.takeWhile { it != secondStackFrameOfParent }
    } else {
        throwable.stackBacktrace
    }
    val assertions = stackTrace.map {
        assertionBuilder.explanatory.withExplanation(Text(it)).build()
    }

    return assertionBuilder.list
        .withDescriptionAndEmptyRepresentation(OCCURRED_EXCEPTION_STACKTRACE)
        .withAssertions(assertions)
        .build()
}


private fun createCauseHint(throwable: Throwable): AssertionGroup? =
    throwable.cause?.let { cause -> createChildHint(throwable, cause, OCCURRED_EXCEPTION_CAUSE) }

/**
 * Creates a hint for a given [child] of the given [throwable] using the given [childDescription].
 */
internal fun createChildHint(
    throwable: Throwable,
    child: Throwable,
    childDescription: Translatable
): AssertionGroup {
    val secondStackTrace = if (throwable.stackBacktrace.size > 1) throwable.stackBacktrace[1] else null
    return assertionBuilder.list
        .withDescriptionAndRepresentation(childDescription, child)
        .withAssertion(createHints(child, secondStackTrace))
        .build()
}
