package ch.tutteli.atrium.assertions

/**
 * An assertion for a throwable, checking that it is not null and of an `expectedType`.
 */
class ExceptionThrownAssertion<T : Throwable>(throwable: Throwable?, expectedType: Class<T>) : IOneMessageAssertion {
    override val message by lazy {
        Message("is a", expectedType, throwable != null && expectedType.isAssignableFrom(throwable::class.java))
    }
}
