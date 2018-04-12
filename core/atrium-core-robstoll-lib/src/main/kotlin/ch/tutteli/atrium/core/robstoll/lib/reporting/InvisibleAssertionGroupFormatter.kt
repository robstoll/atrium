package ch.tutteli.atrium.core.robstoll.lib.reporting

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.InvisibleAssertionGroupType
import ch.tutteli.atrium.reporting.AssertionFormatter
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.reporting.AssertionFormatterParameterObject

/**
 * Represents an [AssertionFormatter] which formats [AssertionGroup]s with an [InvisibleAssertionGroupType] or rather
 * does not format them but only passes on the given [AssertionFormatterParameterObject] as
 * child-[AssertionFormatterParameterObject] for the formatting of the [AssertionGroup.assertions].
 *
 * It does not include a group header in its result or in other words, skips the first part of formatting an
 * [AssertionGroup] as defined in [AssertionFormatter.formatGroup].
 *
 * @constructor Represents an [AssertionFormatter] which formats [AssertionGroup]s with an
 *   [InvisibleAssertionGroupType] by neglecting [AssertionGroup.name] and [AssertionGroup.representation] and
 *   passes on the given [AssertionFormatterParameterObject] which is used to format
 *   [AssertionGroup.assertions].
 * @param assertionFormatterController The controller to which this formatter gives back the control
 *   when it comes to format children of an [AssertionGroup].
 */
class InvisibleAssertionGroupFormatter(
    assertionFormatterController: AssertionFormatterController
) : NoSpecialChildFormattingSingleAssertionGroupTypeFormatter<InvisibleAssertionGroupType>(InvisibleAssertionGroupType::class.java, assertionFormatterController) {

    override fun formatGroupHeaderAndGetChildParameterObject(assertionGroup: AssertionGroup, parameterObject: AssertionFormatterParameterObject)
        = parameterObject
}
