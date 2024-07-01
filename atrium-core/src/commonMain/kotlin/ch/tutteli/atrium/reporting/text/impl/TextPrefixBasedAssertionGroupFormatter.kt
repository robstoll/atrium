//TODO remove file with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.reporting.text.impl

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.reporting.AssertionFormatterParameterObject
import ch.tutteli.atrium.reporting.AssertionPairFormatter

@Deprecated("Switch to Proof based reporting, will be removed with 2.0.0 at the latest")
class TextPrefixBasedAssertionGroupFormatter(
    private val prefix: String
) {
    fun formatWithGroupName(
        assertionPairFormatter: AssertionPairFormatter,
        assertionGroup: AssertionGroup,
        parameterObject: AssertionFormatterParameterObject
    ): AssertionFormatterParameterObject {
        parameterObject.appendLnIndentAndPrefix()
        return formatAfterAppendLnEtc(assertionPairFormatter, assertionGroup, parameterObject)
    }

    fun formatAfterAppendLnEtc(
        assertionPairFormatter: AssertionPairFormatter,
        assertionGroup: AssertionGroup,
        parameterObject: AssertionFormatterParameterObject
    ): AssertionFormatterParameterObject {
        val newParameterObject = parameterObject.createChildWithNewPrefix(prefix)
        assertionPairFormatter.formatGroupHeader(parameterObject, assertionGroup, newParameterObject)
        return newParameterObject
    }

}
