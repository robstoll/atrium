package ch.tutteli.assertk

class SameLineAssertionMessageFormatter(private val objectFormatter: IObjectFormatter) : IAssertionMessageFormatter {

    override fun format(sb: StringBuilder, assertion: IAssertion, filter: (Message) -> Boolean) {
        return when (assertion) {
            is IAssertionGroup ->  formatGroup(sb, assertion.name, assertion, filter)
            else -> sb.appendMessages(assertion, filter)
        }
    }

    private fun formatGroup(sb: StringBuilder, groupName: String, assertionGroup: IAssertionGroup, filter: (Message) -> Boolean) {
        sb.appendPair(groupName, assertionGroup.subject)
            .appendln()
            .appendMessages(assertionGroup, filter)
    }

    private fun StringBuilder.appendPair(left: String, right: Any?)
        = append(left).append(": ").append(objectFormatter.format(right))

    private fun StringBuilder.appendMessages(assertion: IAssertion, filter: (Message) -> Boolean) {
        assertion.messages().filter(filter).joinToString(this, SEPARATOR) { it, sb ->
            sb.appendPair(it.description, it.representation)
        }
    }

    companion object {
        val SEPARATOR: String = System.getProperty("line.separator")!!
    }
}
