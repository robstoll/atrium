package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.reporting.translating.ITranslatable
import ch.tutteli.atrium.reporting.translating.ITranslator

/**
 * Formats an [IAssertion] and its [Message]s, putting each message on its own line.
 *
 * Currently the following [IAssertion] types are supported:
 * - [IAssertionGroup] with the following types:
 *   - [RootAssertionGroupType]
 *   - [FeatureAssertionGroupType]
 * - [IAssertion]
 *
 * and the following [Message] types:
 * - [IOneMessageAssertion]
 *
 * @property assertionFormatterController The [IAssertionFormatterController] used to steer the control flow of
 *           the reporting process.
 * @property objectFormatter Used to format objects such as [Message.representation].
 * @property translator Used to translate [ITranslatable]s such as [Message.description].
 *
 * @constructor
 * @param assertionFormatterController The [IAssertionFormatterController] used to steer the control flow of
 *        the reporting process.
 * @param objectFormatter Used to format objects such as [Message.representation].
 * @param translator Used to translate [ITranslatable]s such as [Message.description].
 */
internal class SameLineAssertionFormatter(
    private val assertionFormatterController: IAssertionFormatterController,
    private val objectFormatter: IObjectFormatter,
    private val translator: ITranslator
) : IAssertionFormatter {

    override fun canFormat(assertion: IAssertion): Boolean {
        // two fallback are implemented one for IAssertionGroup (fallback to formatGroupNameDefault)
        // and the other one for any kind of IAssertion (fallback to fallbackFormat)
        return true
    }

    override fun format(assertion: IAssertion, methodObject: AssertionFormatterMethodObject) = when (assertion) {
        is IAssertionGroup -> IAssertionFormatter.notIntendedForAssertionGroups()
        is IOneMessageAssertion -> appendMessage(assertion.message, methodObject)
        else -> fallbackFormat(assertion, methodObject)
    }

    private fun appendMessage(message: Message, methodObject: AssertionFormatterMethodObject) {
        if (methodObject.messageFilter(message)) {
            methodObject.sb.appendPair(translator.translate(message.description), message.representation)
        }
    }

    private fun StringBuilder.appendPair(left: String, right: Any?)
        = append(left).append(": ").append(objectFormatter.format(right))

    private fun fallbackFormat(assertion: IAssertion, methodObject: AssertionFormatterMethodObject) {
        methodObject.sb.appendPair(
            "Unsupported type ${assertion::class.java.name}, can only report whether it holds",
            assertion.holds())
    }

    override fun formatGroup(assertionGroup: IAssertionGroup, methodObject: AssertionFormatterMethodObject, formatAssertions: ((IAssertion) -> Unit) -> Unit) {
        val newMethodObject = when (assertionGroup.type) {
            is FeatureAssertionGroupType -> formatFeatureGroupName(assertionGroup, methodObject)
            else -> formatGroupNameDefault(assertionGroup, methodObject)
        }
        formatAssertions {
            newMethodObject.sb.appendln()
            indent(newMethodObject)
            assertionFormatterController.format(it, newMethodObject)
        }
    }

    fun formatFeatureGroupName(featureAssertionGroup: IAssertionGroup, methodObject: AssertionFormatterMethodObject): AssertionFormatterMethodObject {
        val arrow = "-> "
        val arrowLength = arrow.length
        methodObject.sb.appendPair(arrow + translator.translate(featureAssertionGroup.name), featureAssertionGroup.subject)
        return AssertionFormatterMethodObject(
            methodObject.sb,
            methodObject.indentLevel + arrowLength,
            methodObject.assertionFilter,
            methodObject.messageFilter)
    }

    private fun formatGroupNameDefault(rootAssertionGroup: IAssertionGroup, methodObject: AssertionFormatterMethodObject): AssertionFormatterMethodObject {
        methodObject.sb.appendPair(translator.translate(rootAssertionGroup.name), rootAssertionGroup.subject)
        return methodObject
    }

    private fun indent(methodObject: AssertionFormatterMethodObject) {
        for (i in 0 until methodObject.indentLevel) {
            methodObject.sb.append(' ')
        }
    }

}


