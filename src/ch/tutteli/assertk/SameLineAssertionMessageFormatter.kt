package ch.tutteli.assertk

class SameLineAssertionMessageFormatter : IAssertionMessageFormatter {

    override fun format(messages: List<Pair<String, String>>) =
        messages.joinToString(SEPARATOR) { it, sb ->
            sb.append(it.first).append(':').append(' ').append(it.second)
        }

    companion object {
        val SEPARATOR: String = System.getProperty("line.separator")!!
    }

}
