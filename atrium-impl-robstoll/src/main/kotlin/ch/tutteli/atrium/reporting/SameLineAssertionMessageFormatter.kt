package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.*
import ch.tutteli.kbox.appendToStringBuilder

/**
 * Formats an [IAssertion] and its [Message]s, putting each message on its own line.
 *
 * Currently the following [IAssertion] types are supported:
 * - [IAssertionGroup]
 * - [IFeatureAssertionGroup]
 * - [IAssertion]
 *
 * and the following [Message] types:
 * - [IOneMessageAssertion]
 * - [IMultiMessageAssertion]
 */
internal class SameLineAssertionMessageFormatter(private val objectFormatter: IObjectFormatter) : IAssertionMessageFormatter {

    override fun format(sb: StringBuilder, assertion: IAssertion, assertionFilter: (IAssertion) -> Boolean, messageFilter: (Message) -> Boolean) {
        format(assertion, MethodObject(0, sb, assertionFilter, messageFilter))
    }

    private fun format(assertion: IAssertion, methodObject: MethodObject) {
        if (methodObject.assertionFilter(assertion)) {
            appendIndent(methodObject)
            when (assertion) {
                is IAssertionGroup -> formatGroup(assertion, methodObject)
                is IFeatureAssertionGroup -> formatFeature(assertion, methodObject)
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
            .appendAssertions(assertionGroup.assertions, methodObject, { methodObject })
    }

    private fun formatFeature(featureAssertionGroup: IFeatureAssertionGroup, methodObject: MethodObject) {
        methodObject.sb
            .appendPair("-> ${featureAssertionGroup.featureName}", featureAssertionGroup.subSubject)
            .appendln()
            .appendAssertions(featureAssertionGroup.assertions, methodObject, methodObject::newWithIncrementedMessageLevel)
    }

    private fun appendMessages(messages: List<Message>, methodObject: MethodObject) {
        messages
            .filter(methodObject.messageFilter)
            .appendToStringBuilder(methodObject.sb, SEPARATOR) { (description, representation), sb ->
                sb.appendPair(description, representation)
            }
    }

    private fun appendMessage(message: Message, methodObject: MethodObject) {
        if (methodObject.messageFilter(message)) {
            methodObject.sb.appendPair(message.description, message.representation)
        }
    }

    private fun basicFormat(assertion: IAssertion, methodObject: MethodObject) {
        methodObject.sb.append("Unsupported type ${assertion::class.java.name}, can only report whether it holds: ")
            .append(assertion.holds())
    }

    private fun appendIndent(methodObject: MethodObject) {
        for (i in 0 until methodObject.messageGroupLevel * NUMBER_OF_INDENT_SPACES) {
            methodObject.sb.append(' ')
        }
    }

    private fun StringBuilder.appendPair(left: String, right: Any?)
        = append(left).append(": ").append(objectFormatter.format(right))

    private fun StringBuilder.appendAssertions(assertions: List<IAssertion>, methodObject: MethodObject, factoryMethod: () -> MethodObject): StringBuilder {
        assertions
            .filter(methodObject.assertionFilter)
            .appendToStringBuilder(methodObject.sb, SEPARATOR) { it, _ ->
                format(it, factoryMethod())
            }
        return this
    }

    companion object {
        val SEPARATOR: String = System.getProperty("line.separator")!!
        internal val NUMBER_OF_INDENT_SPACES = 3
    }

    private class MethodObject(
        val messageGroupLevel: Int,
        val sb: StringBuilder,
        val assertionFilter: (IAssertion) -> Boolean,
        val messageFilter: (Message) -> Boolean) {

        fun newWithIncrementedMessageLevel(): MethodObject {
            return MethodObject(messageGroupLevel + 1, sb, assertionFilter, messageFilter)
        }
    }

}


