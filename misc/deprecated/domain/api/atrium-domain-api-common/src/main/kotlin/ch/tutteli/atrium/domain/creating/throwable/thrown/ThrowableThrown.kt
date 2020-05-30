@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
package ch.tutteli.atrium.domain.creating.throwable.thrown

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.creating.throwable.thrown.ThrowableThrown.*
import ch.tutteli.atrium.domain.creating.throwable.thrown.creators.ThrowableThrownAssertions
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.reporting.translating.Translatable
import kotlin.reflect.KClass

/**
 * Defines the contract for sophisticated a [Throwable] was thrown [Assertion] builders.
 * as well as the minimum set of assertions an implementation of the domain of Atrium has to provide.
 *
 * The building process is typically started by the creation of a [Builder],
 * would allow to define an [AbsentThrowableMessageProvider] as well as an
 * [ch.tutteli.atrium.domain.creating.any.typetransformation.AnyTypeTransformation.FailureHandler]
 * (currently all [ThrowableThrownAssertions] specify it implicitly) and
 * is finalized by one of the [ThrowableThrownAssertions] which usually use a [Creator].
 */
@Deprecated("Use Expect instead; will be removed with 1.0.0")
interface ThrowableThrown {
    /**
     * The entry point of the [Throwable] `thrown` contract.
     */
    @Deprecated("Use Expect instead; will be removed with 1.0.0")
    interface Builder {
        val assertionVerb: Translatable
        val act: () -> Unit
        val reporter: Reporter
    }

    /**
     * Provides a message which can be used in reporting to represent the case that no [Throwable] at all was thrown.
     */
    @Deprecated("Will be removed with 1.0.0")
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
    @Deprecated("Will be removed with 1.0.0")
    interface Creator<TExpected : Throwable> {

        /**
         * Executes the [act][ThrowableThrown.Builder.act] lambda of the given [throwableThrownBuilder] and catches
         * thrown [Throwable]s (if there are any) and correspondingly creates an assertion where it is expected that
         * nothing is thrown..
         *
         * @param throwableThrownBuilder The [ThrowableThrown.Builder] containing inter alia the
         *   [act][ThrowableThrown.Builder.act] lambda.
         */
        @Deprecated("Will be removed with 1.0.0")
        fun executeActAssertNothingThrown(throwableThrownBuilder: Builder)

        /**
         * Executes the [act][ThrowableThrown.Builder.act] lambda of the given [throwableThrownBuilder], catches any
         * thrown [Throwable], creates based on it a corresponding [Assertion] representing the sophisticated
         * assertion and also checks whether it holds or not.
         *
         * @param throwableThrownBuilder The [ThrowableThrown.Builder] containing inter alia the
         *   [act][ThrowableThrown.Builder.act] lambda.
         * @param description The [description][DescriptiveAssertion.description] of the resulting [DescriptiveAssertion].
         * @param expectedType The expected type of the [Throwable] used for casting and probably in reporting.
         * @param assertionCreator The assertion creator which defines subsequent assertions for the [Throwable] in
         *   case it was thrown as expected and is of the expected type [TExpected].
         */
        @Deprecated("Will be removed with 1.0.0")
        fun executeActAndCreateAssertion(
            throwableThrownBuilder: Builder,
            description: Translatable,
            expectedType: KClass<TExpected>,
            assertionCreator: AssertionPlant<TExpected>.() -> Unit
        )
    }
}
