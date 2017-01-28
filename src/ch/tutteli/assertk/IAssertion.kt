package ch.tutteli.assertk

interface IAssertion {
    fun holds(): Boolean
    fun logMessages(): List<Pair<String, String>>
}

