package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.reporting.translating.Untranslatable

/**
 * Formats an [Assertion] for text output (e.g. to the console) by using the given [assertionPairFormatter]
 * to format the group header for [AssertionGroup]s and uses the bullet point defined for
 * [RootAssertionGroupType] as prefix for the [AssertionGroup.assertions].
 *
 * The [assertionPairFormatter] is also used to format [DescriptiveAssertion]s.
 *
 * Currently the following [Assertion] types are supported:
 * - [AssertionGroup] of type [RootAssertionGroupType]
 * - [DescriptiveAssertion]
 * - [ExplanatoryAssertion]
 *
 * In addition it defines a fallback for unknown [AssertionGroupType]s as well as for unknown [Assertion] types.
 *
 * @property assertionFormatterController The [AssertionFormatterController] used to steer the control flow of
 *   the reporting process.
 * @property assertionPairFormatter The formatter used to format assertion pairs (e.g. [DescriptiveAssertion.description]
 *   and [DescriptiveAssertion.expected])
 *
 * @constructor Formats an [Assertion] for text output (e.g. for the console) where it uses a given
 *   [assertionPairFormatter] which defines how an assertion pair (e.g. [DescriptiveAssertion.description]
 *   and [DescriptiveAssertion.expected]) is formatted.
 * @param bulletPoints The formatter uses the bullet point defined for [RootAssertionGroupType]
 *   (`"◆ "` if absent) as prefix of the child-[AssertionFormatterMethodObject].
 * @param assertionFormatterController The [AssertionFormatterController] used to steer the control flow of
 *   the reporting process.
 * @param assertionPairFormatter The formatter which is used to format assertion pairs
 *   (e.g. [DescriptiveAssertion.description] and [DescriptiveAssertion.expected])
 */
class TextFallbackAssertionFormatter(
    bulletPoints: Map<Class<out BulletPointIdentifier>, String>,
    private val assertionFormatterController: AssertionFormatterController,
    private val assertionPairFormatter: AssertionPairFormatter,
    private val objectFormatter: ObjectFormatter
) : AssertionFormatter {
    private val rootPrefix = bulletPoints[RootAssertionGroupType::class.java] ?: "◆ "
    private val formatter = TextPrefixBasedAssertionGroupFormatter(rootPrefix)

    override fun canFormat(assertion: Assertion): Boolean {
        // two fallback are implemented one for IAssertionGroup (uses always formatGroup)
        // and the other one for any kind of IAssertion (fallback to formatFallback)
        return true
    }

    override fun formatNonGroup(assertion: Assertion, methodObject: AssertionFormatterMethodObject) {
        methodObject.appendLnIndentAndPrefix()
        when (assertion) {
            is DescriptiveAssertion -> appendDescriptiveAssertion(assertion, methodObject)
            is ExplanatoryAssertion -> appendExplanatoryAssertion(assertion, methodObject)
            else -> formatFallback(assertion, methodObject)
        }
    }

    private fun appendDescriptiveAssertion(basicAssertion: DescriptiveAssertion, methodObject: AssertionFormatterMethodObject) {
        assertionPairFormatter.format(methodObject, basicAssertion.description, basicAssertion.expected)
    }

    private fun appendExplanatoryAssertion(assertion: ExplanatoryAssertion, methodObject: AssertionFormatterMethodObject) {
        methodObject.sb.append(objectFormatter.format(assertion.explanation))
    }

    private fun formatFallback(assertion: Assertion, methodObject: AssertionFormatterMethodObject) {
        val translatable = Untranslatable("Unsupported type ${assertion::class.java.name}, can only report whether it holds")
        assertionPairFormatter.format(methodObject, translatable, assertion.holds())
    }

    override fun formatGroup(assertionGroup: AssertionGroup, methodObject: AssertionFormatterMethodObject, formatAssertions: (AssertionFormatterMethodObject, (Assertion) -> Unit) -> Unit) {
        val childMethodObject = formatGroupHeaderAndGetChildMethodObject(assertionGroup, methodObject)
        formatAssertions(childMethodObject) {
            assertionFormatterController.format(it, childMethodObject)
        }
    }

    private fun formatGroupHeaderAndGetChildMethodObject(
        assertionGroup: AssertionGroup, methodObject: AssertionFormatterMethodObject
    ) = when (assertionGroup.type) {
        is RootAssertionGroupType -> formatter.formatAfterAppendLnEtc(assertionPairFormatter, assertionGroup, methodObject)
        else -> formatter.formatWithGroupName(assertionPairFormatter, assertionGroup, methodObject)
    }

}
