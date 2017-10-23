package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.IAssertionGroup
import ch.tutteli.atrium.assertions.IAssertionGroupType

/**
 * Represents an [IAssertionFormatter] which formats [IAssertionGroup]s with type [T] by
 * putting each assertion on an own line prefixed with a bullet point.
 *
 * Its usage is intended for text output (e.g. to the console).
 *
 * @constructor Represents an [IAssertionFormatter] which formats [IAssertionGroup]s with type [T] by
 *              putting each assertion on an own line prefixed with a bullet point.
 * @param bulletPoint The bullet point (might also be more than one character) which shall be used.
 * @param assertionFormatterController The controller to which this formatter gives back the control
 *        when it comes to format children of an [IAssertionGroup].
 * @param assertionPairFormatter The formatter used to format assertion pairs.
 * @param extraIndent Adds the number of [extraIndent] as spaces in front of the given [bulletPoint].
 * @param clazz The [IAssertionGroupType] which the concrete sub class [canFormat][IAssertionFormatter.canFormat].
 */
abstract class TextListBasedAssertionGroupFormatter<in T : IAssertionGroupType>(
    bulletPoint: String,
    assertionFormatterController: IAssertionFormatterController,
    private val assertionPairFormatter: IAssertionPairFormatter,
    clazz: Class<T>,
    extraIndent: Int = 0
) : SingleAssertionGroupTypeFormatter<T>(clazz) {
    private val formatter = TextPrefixBasedAssertionGroupFormatter(" ".repeat(extraIndent) + "$bulletPoint ", assertionFormatterController)

    override fun formatSpecificGroup(assertionGroup: IAssertionGroup, methodObject: AssertionFormatterMethodObject, formatAssertions: ((IAssertion) -> Unit) -> Unit) {
        formatter.formatWithGroupName(assertionPairFormatter, assertionGroup, methodObject, formatAssertions)
    }
}
