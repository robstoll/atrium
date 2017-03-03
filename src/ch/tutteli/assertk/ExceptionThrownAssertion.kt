package ch.tutteli.assertk

class ExceptionThrownAssertion<T : Throwable>(throwable: Throwable?, expectedType: Class<T>) : IOneMessageAssertion {
    override val message by lazy {
        when (throwable) {
            null -> Message("exception was", RawString.NULL, false)
            else -> Message("exception was", throwable::class.java, expectedType.isAssignableFrom(throwable::class.java))
        }
    }
}
