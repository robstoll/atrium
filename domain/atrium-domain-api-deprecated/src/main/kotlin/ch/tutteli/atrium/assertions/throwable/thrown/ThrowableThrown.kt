@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0*/)
package ch.tutteli.atrium.assertions.throwable.thrown

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.assertions.throwable.thrown.ThrowableThrown.AbsentThrowableMessageProvider
import ch.tutteli.atrium.assertions.throwable.thrown.ThrowableThrown.Creator
import ch.tutteli.atrium.assertions.throwable.thrown.builders.ThrowableThrownBuilder
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Translatable
import kotlin.reflect.KClass

/**
 * Defines the *deprecated* contract for sophisticated a [Throwable] was thrown assertions.
 *
 * An assertion starts with a [ThrowableThrownBuilder] and is typically built up by an [AbsentThrowableMessageProvider]
 * and a [Creator] which finally builds the assertion.
 */
@Deprecated(
    "use the interface from package domain.creating; will be removed with 1.0.0",
    ReplaceWith("ch.tutteli.atrium.domain.creating.throwable.thrown.ThrowableThrown")
)
interface ThrowableThrown {
    /**
     * Provides a message which can be used in reporting to represent the case that no [Throwable] at all was thrown.
     */
    @Deprecated(
        "use the interface from package domain.creating; will be removed with 1.0.0",
        ReplaceWith("ch.tutteli.atrium.domain.creating.throwable.thrown.ThrowableThrown.AbsentThrowableMessageProvider")
    )
    interface AbsentThrowableMessageProvider {
        /**
         * The message can be used in reporting to represent the case that no [Throwable] was thrown at all.
         */
        val message: RawString
    }

    /**
     * Represents the final step of a sophisticated a [Throwable] was thrown assertion builder which creates
     * the [AssertionGroup] as such.
     *
     * @param TExpected The type of the [Throwable] which is expected to be thrown.
     */
    @Deprecated(
        "use the interface from package domain.creating; will be removed with 1.0.0",
        ReplaceWith("ch.tutteli.atrium.domain.creating.throwable.thrown.ThrowableThrown.Creator")
    )
    interface Creator<TExpected : Throwable> {
        /**
         * Executes the [act][ThrowableThrownBuilder.act] lambda of the given [throwableThrownBuilder], catches any
         * thrown [Throwable], creates based on it a corresponding [Assertion] representing the sophisticated
         * assertion and also checks whether it holds or not.
         *
         * @param throwableThrownBuilder The [ThrowableThrownBuilder] containing inter alia the
         *   [act][ThrowableThrownBuilder.act] lambda.
         * @param description The [description][DescriptiveAssertion.description] of the resulting [DescriptiveAssertion].
         * @param expectedType The expected type of the [Throwable] used for casting and probably in reporting.
         * @param assertionCreator The assertion creator which defines subsequent assertions for the [Throwable] in
         *   case it was thrown as expected and is of the expected type [TExpected].
         */
        fun executeActAndCreateAssertion(
            throwableThrownBuilder: ThrowableThrownBuilder,
            description: Translatable,
            expectedType: KClass<TExpected>,
            assertionCreator: AssertionPlant<TExpected>.() -> Unit
        )
    }
}
