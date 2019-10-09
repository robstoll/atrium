package ch.tutteli.atrium.core.robstoll.lib.reporting

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.core.polyfills.fullName
import ch.tutteli.atrium.reporting.*
import ch.tutteli.atrium.reporting.translating.Untranslatable
import kotlin.reflect.KClass

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
 *   and [DescriptiveAssertion.representation])
 *
 * @constructor Formats an [Assertion] for text output (e.g. for the console) where it uses a given
 *   [assertionPairFormatter] which defines how an assertion pair (e.g. [DescriptiveAssertion.description]
 *   and [DescriptiveAssertion.representation]) is formatted.
 * @param bulletPoints The formatter uses the bullet point defined for [RootAssertionGroupType]
 *   (`"◆ "` if absent) as prefix of the child-[AssertionFormatterParameterObject].
 * @param assertionFormatterController The [AssertionFormatterController] used to steer the control flow of
 *   the reporting process.
 * @param assertionPairFormatter The formatter which is used to format assertion pairs
 *   (e.g. [DescriptiveAssertion.description] and [DescriptiveAssertion.representation])
 */
class TextFallbackAssertionFormatter(
    bulletPoints: Map<KClass<out BulletPointIdentifier>, String>,
    private val assertionFormatterController: AssertionFormatterController,
    private val assertionPairFormatter: AssertionPairFormatter,
    private val objectFormatter: ObjectFormatter
) : AssertionFormatter {
    private val rootPrefix = bulletPoints[RootAssertionGroupType::class] ?: "◆ "
    private val formatter = TextPrefixBasedAssertionGroupFormatter(rootPrefix)

    override fun canFormat(assertion: Assertion): Boolean {
        // two fallback are implemented one for IAssertionGroup (uses always formatGroup)
        // and the other one for any kind of IAssertion (fallback to formatFallback)
        return true
    }

    override fun formatNonGroup(assertion: Assertion, parameterObject: AssertionFormatterParameterObject) {
        parameterObject.appendLnIndentAndPrefix()
        when (assertion) {
            is DescriptiveAssertion -> appendDescriptiveAssertion(assertion, parameterObject)
            is RepresentationOnlyAssertion -> appendRepresentationOnlyAssertion(assertion, parameterObject)
            is ExplanatoryAssertion -> appendExplanatoryAssertion(assertion, parameterObject)
            else -> formatFallback(assertion, parameterObject)
        }
    }

    private fun appendDescriptiveAssertion(
        assertion: DescriptiveAssertion,
        parameterObject: AssertionFormatterParameterObject
    ) {
        assertionPairFormatter.format(parameterObject, assertion.description, assertion.representation)
    }

    private fun appendRepresentationOnlyAssertion(
        assertion: RepresentationOnlyAssertion,
        parameterObject: AssertionFormatterParameterObject
    ) {
        parameterObject.sb.append(objectFormatter.format(assertion.representation))
    }

    private fun appendExplanatoryAssertion(
        assertion: ExplanatoryAssertion,
        parameterObject: AssertionFormatterParameterObject
    ) {
        parameterObject.sb.append(objectFormatter.format(assertion.explanation))
    }

    private fun formatFallback(assertion: Assertion, parameterObject: AssertionFormatterParameterObject) {
        val translatable =
            Untranslatable("Unsupported type ${assertion::class.fullName}, can only report whether it holds")
        assertionPairFormatter.format(parameterObject, translatable, assertion.holds())
    }

    override fun formatGroup(
        assertionGroup: AssertionGroup,
        parameterObject: AssertionFormatterParameterObject,
        formatAssertions: (AssertionFormatterParameterObject, (Assertion) -> Unit) -> Unit
    ) {
        val childParameterObject = formatGroupHeaderAndGetChildParameterObject(assertionGroup, parameterObject)
        formatAssertions(childParameterObject) {
            assertionFormatterController.format(it, childParameterObject)
        }
    }

    private fun formatGroupHeaderAndGetChildParameterObject(
        assertionGroup: AssertionGroup, parameterObject: AssertionFormatterParameterObject
    ) = when (assertionGroup.type) {
        RootAssertionGroupType -> formatter.formatAfterAppendLnEtc(
            assertionPairFormatter, assertionGroup, parameterObject
        )
        else -> formatter.formatWithGroupName(assertionPairFormatter, assertionGroup, parameterObject)
    }

}
