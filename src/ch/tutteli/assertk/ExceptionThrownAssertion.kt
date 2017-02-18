package ch.tutteli.assertk

class ExceptionThrownAssertion<T : Throwable>(private val throwable: Throwable?, private val expectedType: Class<T>) : IAssertion {


    override fun messages() = when (throwable) {
        null -> listOf(Message("exception was", null, false))
        else -> listOf(Message("exception was", throwable.javaClass, expectedType.isAssignableFrom(throwable.javaClass)))
    }
}
