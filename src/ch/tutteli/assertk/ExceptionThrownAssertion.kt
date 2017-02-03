package ch.tutteli.assertk

class ExceptionThrownAssertion<T : Throwable>(private val throwable: Throwable?, private val expectedType: Class<T>) : IAssertion {
    override fun holds(): Boolean {
        return throwable != null && expectedType.isAssignableFrom(throwable.javaClass)
    }

    override fun logMessages(): List<Pair<String, String>> {
        val errorPair = if (throwable != null) {
            "but was" to AssertionFactory.objectFormatter.format(throwable.javaClass)
        } else {
            "but no exception was thrown" to ""
        }
        return listOf(errorPair)
    }
}
