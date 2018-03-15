package ch.tutteli.atrium.core.robstoll.lib.reporting

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.reporting.AssertionFormatterParameterObject
import ch.tutteli.atrium.reporting.AssertionPairFormatter

class TextPrefixBasedAssertionGroupFormatter(
    private val prefix: String
) {
    fun formatWithGroupName(assertionPairFormatter: AssertionPairFormatter, assertionGroup: AssertionGroup, parameterObject: AssertionFormatterParameterObject): AssertionFormatterParameterObject {
        parameterObject.appendLnIndentAndPrefix()
        return formatAfterAppendLnEtc(assertionPairFormatter, assertionGroup, parameterObject)
    }

    fun formatAfterAppendLnEtc(assertionPairFormatter: AssertionPairFormatter, assertionGroup: AssertionGroup, parameterObject: AssertionFormatterParameterObject): AssertionFormatterParameterObject {
        val newParameterObject = parameterObject.createChildWithNewPrefix(prefix)
        assertionPairFormatter.formatGroupHeader(parameterObject, assertionGroup, newParameterObject)
        return newParameterObject
    }

}
