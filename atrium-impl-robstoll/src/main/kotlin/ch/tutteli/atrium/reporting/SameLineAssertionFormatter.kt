package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.reporting.translating.Untranslatable

/**
 * Formats an [IAssertion], putting each message on its own line.
 *
 * Currently the following [IAssertion] types are supported:
 * - [IAssertionGroup] with the following types:
 *   - [RootAssertionGroupType]
 *   - [FeatureAssertionGroupType]
 * - [IBasicAssertion]
 * - [IAssertion]
 *
 * @property assertionFormatterController The [IAssertionFormatterController] used to steer the control flow of
 *           the reporting process.

 * @constructor
 * @param assertionFormatterController The [IAssertionFormatterController] used to steer the control flow of
 *        the reporting process.

 */
internal class SameLineAssertionFormatter(
    private val assertionFormatterController: IAssertionFormatterController,
    private val assertionPairFormatter: IAssertionPairFormatter
) : IAssertionFormatter {

    override fun canFormat(assertion: IAssertion): Boolean {
        // two fallback are implemented one for IAssertionGroup (fallback to formatGroupNameDefault)
        // and the other one for any kind of IAssertion (fallback to formatFallback)
        return true
    }

    override fun format(assertion: IAssertion, methodObject: AssertionFormatterMethodObject) = when (assertion) {
        is IAssertionGroup -> IAssertionFormatter.throwNotIntendedForAssertionGroups()
        is IBasicAssertion -> appendBasicAssertion(assertion, methodObject)
        else -> formatFallback(assertion, methodObject)
    }

    private fun appendBasicAssertion(basicAssertion: IBasicAssertion, methodObject: AssertionFormatterMethodObject) {
        assertionPairFormatter.format(methodObject, basicAssertion.description, basicAssertion.expected)
    }

    private fun formatFallback(assertion: IAssertion, methodObject: AssertionFormatterMethodObject) {
        val translatable = Untranslatable("Unsupported type ${assertion::class.java.name}, can only report whether it holds")
        assertionPairFormatter.format(methodObject, translatable, assertion.holds())
    }

    override fun formatGroup(assertionGroup: IAssertionGroup, methodObject: AssertionFormatterMethodObject, formatAssertions: ((IAssertion) -> Unit) -> Unit) {
        val newMethodObject = when (assertionGroup.type) {
            is FeatureAssertionGroupType -> formatFeatureGroupName(assertionGroup, methodObject)
            else -> formatGroupNameDefault(assertionGroup, methodObject)
        }
        formatAssertions {
            newMethodObject.sb.appendln()
            newMethodObject.indent()
            assertionFormatterController.format(it, newMethodObject)
        }
    }

    fun formatFeatureGroupName(featureAssertionGroup: IAssertionGroup, methodObject: AssertionFormatterMethodObject): AssertionFormatterMethodObject {
        val arrow = "-> "
        val arrowLength = arrow.length
        val translatable = TranslatableWithArgs(Untranslatable("$arrow%s"), featureAssertionGroup.name)
        assertionPairFormatter.format(methodObject, translatable, featureAssertionGroup.subject)
        return AssertionFormatterMethodObject(
            methodObject.sb,
            methodObject.indentLevel + arrowLength,
            methodObject.assertionFilter)
    }

    private fun formatGroupNameDefault(rootAssertionGroup: IAssertionGroup, methodObject: AssertionFormatterMethodObject): AssertionFormatterMethodObject {
        assertionPairFormatter.format(methodObject, rootAssertionGroup.name, rootAssertionGroup.subject)
        return methodObject
    }

}


