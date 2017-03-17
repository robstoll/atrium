package ch.tutteli.assertk.assertions

class ExceptionThrownAssertion<T : Throwable>(throwable: Throwable?, expectedType: Class<T>) : IOneMessageAssertion {
    override val message by lazy {
        Message("is a", expectedType, throwable != null && expectedType.isAssignableFrom(throwable::class.java))
    }
}
