package ch.tutteli.assertk

class ExceptionThrownAssertion<T : Throwable>(throwable: Throwable?, expectedType: Class<T>) : IAssertion {
    private val lazyMessages: List<Message> by lazy {
        when (throwable) {
            null -> listOf(Message("exception was", null, false))
            else -> listOf(Message("exception was", throwable.javaClass, expectedType.isAssignableFrom(throwable.javaClass)))
        }
    }

    override fun messages() = lazyMessages
}
