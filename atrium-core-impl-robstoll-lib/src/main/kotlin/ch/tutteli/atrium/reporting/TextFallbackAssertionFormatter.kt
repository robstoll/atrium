package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.reporting.translating.Untranslatable

/**
 * Formats an [IAssertion] for text output (e.g. to the console) by using the given [assertionPairFormatter]
 * to format the group header for [IAssertionGroup]s and uses the bullet point defined for
 * [RootAssertionGroupType] as prefix for the [IAssertionGroup.assertions].
 *
 * The [assertionPairFormatter] is also used to format [IBasicAssertion]s.
 *
 * Currently the following [IAssertion] types are supported:
 * - [IAssertionGroup] of type [RootAssertionGroupType]
 * - [IBasicAssertion]
 * - [IExplanatoryAssertion]
 *
 * In addition it defines a fallback for unknown [IAssertionGroupType]s as well as for unknown [IAssertion] types.
 *
 * @property assertionFormatterController The [IAssertionFormatterController] used to steer the control flow of
 *           the reporting process.
 * @property assertionPairFormatter The formatter used to format assertion pairs (e.g. [IBasicAssertion.description]
 *           and [IBasicAssertion.expected])
 *
 * @constructor Formats an [IAssertion] for text output (e.g. for the console) where it uses a given
 *              [assertionPairFormatter] which defines how an assertion pair (e.g. [IBasicAssertion.description]
 *              and [IBasicAssertion.expected]) is formatted.
 * @param bulletPoints The formatter uses the bullet point defined for [RootAssertionGroupType]
 *        (`"• "` if absent) as prefix of the child-[AssertionFormatterMethodObject].
 * @param assertionFormatterController The [IAssertionFormatterController] used to steer the control flow of
 *        the reporting process.
 * @param assertionPairFormatter The formatter used to format assertion pairs (e.g. [IBasicAssertion.description]
 *        and [IBasicAssertion.expected])
 */
class TextFallbackAssertionFormatter(
    bulletPoints: Map<Class<out IBulletPointIdentifier>, String>,
    private val assertionFormatterController: IAssertionFormatterController,
    private val assertionPairFormatter: IAssertionPairFormatter,
    private val objectFormatter: IObjectFormatter
) : IAssertionFormatter {
    private val formatter = TextPrefixBasedAssertionGroupFormatter(
        bulletPoints[RootAssertionGroupType::class.java] ?: "• ")

    override fun canFormat(assertion: IAssertion): Boolean {
        // two fallback are implemented one for IAssertionGroup (uses always formatGroup)
        // and the other one for any kind of IAssertion (fallback to formatFallback)
        return true
    }

    override fun formatNonGroup(assertion: IAssertion, methodObject: AssertionFormatterMethodObject) {
        methodObject.appendLnIndentAndPrefix()
        when (assertion) {
            is IBasicAssertion -> appendBasicAssertion(assertion, methodObject)
            is IExplanatoryAssertion -> appendExplanatoryAssertion(assertion, methodObject)
            else -> formatFallback(assertion, methodObject)
        }
    }

    private fun appendBasicAssertion(basicAssertion: IBasicAssertion, methodObject: AssertionFormatterMethodObject) {
        assertionPairFormatter.format(methodObject, basicAssertion.description, basicAssertion.expected)
    }

    private fun appendExplanatoryAssertion(assertion: IExplanatoryAssertion, methodObject: AssertionFormatterMethodObject) {
        methodObject.sb.append(objectFormatter.format(assertion.explanation))
    }

    private fun formatFallback(assertion: IAssertion, methodObject: AssertionFormatterMethodObject) {
        val translatable = Untranslatable("Unsupported type ${assertion::class.java.name}, can only report whether it holds")
        assertionPairFormatter.format(methodObject, translatable, assertion.holds())
    }

    override fun formatGroup(assertionGroup: IAssertionGroup, methodObject: AssertionFormatterMethodObject, formatAssertions: (AssertionFormatterMethodObject, (IAssertion) -> Unit) -> Unit) {
        val childMethodObject = formatGroupHeaderAndGetChildMethodObject(assertionGroup, methodObject)
        formatAssertions(childMethodObject) {
            assertionFormatterController.format(it, childMethodObject)
        }
    }

    private fun formatGroupHeaderAndGetChildMethodObject(
        assertionGroup: IAssertionGroup, methodObject: AssertionFormatterMethodObject
    ) = when (assertionGroup.type) {
        is RootAssertionGroupType -> formatter.formatAfterAppendLnEtc(assertionPairFormatter, assertionGroup, methodObject)
        else -> formatter.formatWithGroupName(assertionPairFormatter, assertionGroup, methodObject)
    }

}


