package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.IAssertionGroup

class TextPrefixBasedAssertionGroupFormatter(
    private val prefix: String
) {
    fun formatWithGroupName(assertionPairFormatter: IAssertionPairFormatter, assertionGroup: IAssertionGroup, methodObject: AssertionFormatterMethodObject): AssertionFormatterMethodObject {
        methodObject.appendLnIndentAndPrefix()
        return formatAfterAppendLnEtc(assertionPairFormatter, assertionGroup, methodObject)
    }

    fun formatAfterAppendLnEtc(assertionPairFormatter: IAssertionPairFormatter, assertionGroup: IAssertionGroup, methodObject: AssertionFormatterMethodObject): AssertionFormatterMethodObject {
        assertionPairFormatter.format(methodObject, assertionGroup.name, assertionGroup.subject)
        return methodObject.createChildWithNewPrefix(prefix)
    }

}
