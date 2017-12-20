package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.IAssertionGroup
import ch.tutteli.atrium.assertions.IAssertionGroupType

/**
 * Represents an [AssertionFormatter] which formats [IAssertionGroup]s with type [T] by using the given
 * [assertionPairFormatter] to format the group header and using the given `bulletPoint` (see constructor) to prefix
 * the [IAssertionGroup.assertions].
 *
 * Its usage is intended for text output (e.g. to the console).
 *
 * @constructor Represents an [AssertionFormatter] which formats [IAssertionGroup]s with type [T] by
 *              putting each assertion on an own line prefixed with a bullet point.
 * @param bulletPoint The bullet point (might also be more than one character) which shall be used.
 * @param assertionFormatterController The controller to which this formatter gives back the control
 *        when it comes to format children of an [IAssertionGroup].
 * @param assertionPairFormatter The formatter used to format assertion pairs.
 * @param clazz The [IAssertionGroupType] which the concrete sub class [canFormat][AssertionFormatter.canFormat].
 */
abstract class TextListBasedAssertionGroupFormatter<in T : IAssertionGroupType>(
    bulletPoint: String,
    assertionFormatterController: AssertionFormatterController,
    private val assertionPairFormatter: AssertionPairFormatter,
    clazz: Class<T>
) : NoSpecialChildFormattingSingleAssertionGroupTypeFormatter<T>(clazz, assertionFormatterController) {
    private val formatter = TextPrefixBasedAssertionGroupFormatter(bulletPoint)

    override fun formatGroupHeaderAndGetChildMethodObject(assertionGroup: IAssertionGroup, methodObject: AssertionFormatterMethodObject)
        = formatter.formatWithGroupName(assertionPairFormatter, assertionGroup, methodObject)

}
