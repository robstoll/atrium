package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.IAssertionGroup
import ch.tutteli.atrium.assertions.IExplanatoryAssertionGroupType

/**
 * Represents an [IAssertionFormatter] which formats [IAssertionGroup]s with an [IExplanatoryAssertionGroupType] by
 * putting each assertion on an own line prefixed with a bullet point.
 *
 * Its usage is intended for text output (e.g. to the console).
 *
 * @constructor Represents an [IAssertionFormatter] which formats [IAssertionGroup]s with an [IExplanatoryAssertionGroupType] by
 *              putting each assertion on an own line prefixed with a bullet point.
 * @param assertionFormatterController The controller to which this formatter gives back the control
 *        when it comes to format children of an [IAssertionGroup].
 * @param assertionPairFormatter The formatter used to format assertion pairs.
 */
class TextExplanatoryAssertionGroupFormatter(
    bulletPoint: String,
    assertionFormatterController: IAssertionFormatterController,
    assertionPairFormatter: IAssertionPairFormatter
) : TextListBasedAssertionGroupFormatter<IExplanatoryAssertionGroupType>(
    bulletPoint,
    assertionFormatterController,
    assertionPairFormatter,
    IExplanatoryAssertionGroupType::class.java,
    extraIndent = 1)
