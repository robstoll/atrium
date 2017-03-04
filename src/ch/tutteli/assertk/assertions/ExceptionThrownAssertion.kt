package ch.tutteli.assertk.assertions

import ch.tutteli.assertk.assertions.Message
import ch.tutteli.assertk.assertions.IOneMessageAssertion
import ch.tutteli.assertk.reporting.RawString

class ExceptionThrownAssertion<T : Throwable>(throwable: Throwable?, expectedType: Class<T>) : IOneMessageAssertion {
    override val message by lazy {
        when (throwable) {
            null -> Message("exception was", RawString.NULL, false)
            else -> Message("exception was", throwable::class.java, expectedType.isAssignableFrom(throwable::class.java))
        }
    }
}
