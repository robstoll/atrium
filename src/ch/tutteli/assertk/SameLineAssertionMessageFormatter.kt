package ch.tutteli.assertk

class SameLineAssertionMessageFormatter(private val objectFormatter: IObjectFormatter) : IAssertionMessageFormatter {

    override fun format(sb: StringBuilder, assertion: IAssertion, assertionFilter: (IAssertion) -> Boolean, messageFilter: (Message) -> Boolean) {
        format(assertion, MethodObject(0, sb, assertionFilter, messageFilter))
    }

    private fun format(assertion: IAssertion, methodObject: MethodObject) {
        if (methodObject.assertionFilter(assertion)) {
            when (assertion) {
                is IAssertionGroup -> formatGroup(assertion, methodObject)
                is IOneMessageAssertion -> appendMessage(assertion.message, methodObject)
                is IMultiMessageAssertion -> appendMessages(assertion.messages, methodObject)
                else -> basicFormat(assertion, methodObject)
            }
        }
    }

    private fun formatGroup(assertionGroup: IAssertionGroup, methodObject: MethodObject) {
        methodObject.sb
            .appendPair(assertionGroup.name, assertionGroup.subject)
            .appendln()
        assertionGroup.assertions
            .filter(methodObject.assertionFilter)
            .appendToStringBuilder(methodObject.sb, SEPARATOR) { it, _ ->
                format(it, methodObject.newWithIncrementedGroupLevel())
            }
    }

    private fun appendMessages(messages: List<Message>, methodObject: MethodObject) {
        messages
            .filter(methodObject.messageFilter)
            .appendToStringBuilder(methodObject.sb, SEPARATOR) { it, sb ->
                sb.appendPair(it.description, it.representation)
            }
    }

    private fun appendMessage(message: Message, methodObject: MethodObject) {
        if (methodObject.messageFilter(message)) {
            methodObject.sb.appendPair(message.description, message.representation)
        }
    }

    private fun basicFormat(assertion: IAssertion, methodObject: MethodObject) {
        methodObject.sb.append("Unsupported ${assertion::class.java.simpleName} type, can only report whether it holds: ")
            .append(assertion.holds())
    }

    private fun StringBuilder.appendPair(left: String, right: Any?)
        = append(left).append(": ").append(objectFormatter.format(right))

    companion object {
        val SEPARATOR: String = System.getProperty("line.separator")!!
    }

    private class MethodObject(
        val groupLevel: Int,
        val sb: StringBuilder,
        val assertionFilter: (IAssertion) -> Boolean,
        val messageFilter: (Message) -> Boolean) {

        fun newWithIncrementedGroupLevel(): MethodObject {
            return MethodObject(groupLevel + 1, sb, assertionFilter, messageFilter)
        }
    }

}
