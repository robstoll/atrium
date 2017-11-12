package ch.tutteli.atrium.assertions.throwable.thrown

import ch.tutteli.atrium.assertions.any.narrow.IAnyNarrow
import ch.tutteli.atrium.assertions.throwable.thrown.IThrowableThrown.IAbsentThrowableMessageProvider
import ch.tutteli.atrium.assertions.throwable.thrown.builders.ThrowableThrownBuilder
import ch.tutteli.atrium.assertions.throwable.thrown.creators.ThrowableThrownAssertionCreator
import ch.tutteli.atrium.reporting.IRawString

/**
 * Defines the contract for sophisticated a [Throwable] was thrown assertions.
 *
 * An assertion starts with a [ThrowableThrownBuilder] and is typically built up by an [IAbsentThrowableMessageProvider]
 * and an [IAnyNarrow.IDownCastFailureHandler] which are passed to [ThrowableThrownAssertionCreator] which finally
 * builds the assertion.
 */
interface IThrowableThrown {
    /**
     * Provides a message which can be used in reporting to represent the case that no [Throwable] at all was thrown.
     */
    interface IAbsentThrowableMessageProvider {
        /**
         * The message can be used in reporting to represent the case that no [Throwable] at all was thrown.
         */
        val message: IRawString
    }
}
