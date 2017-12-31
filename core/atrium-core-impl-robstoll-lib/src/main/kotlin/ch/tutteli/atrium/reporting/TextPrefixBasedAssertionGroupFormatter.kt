package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.AssertionGroup

class TextPrefixBasedAssertionGroupFormatter(
    private val prefix: String
) {
    fun formatWithGroupName(assertionPairFormatter: AssertionPairFormatter, assertionGroup: AssertionGroup, methodObject: AssertionFormatterMethodObject): AssertionFormatterMethodObject {
        methodObject.appendLnIndentAndPrefix()
        return formatAfterAppendLnEtc(assertionPairFormatter, assertionGroup, methodObject)
    }

    fun formatAfterAppendLnEtc(assertionPairFormatter: AssertionPairFormatter, assertionGroup: AssertionGroup, methodObject: AssertionFormatterMethodObject): AssertionFormatterMethodObject {
        val newMethodObject = methodObject.createChildWithNewPrefix(prefix)
        assertionPairFormatter.formatGroupHeader(methodObject, assertionGroup, newMethodObject)
        return newMethodObject
    }

}
