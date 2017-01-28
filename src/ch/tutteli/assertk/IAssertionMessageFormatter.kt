package ch.tutteli.assertk

interface IAssertionMessageFormatter {
    fun format(messages: List<Pair<String, String>>): String
}
