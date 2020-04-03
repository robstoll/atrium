package ch.tutteli.atrium.api.infix.en_GB.creating.charsequence

import ch.tutteli.atrium.domain.builders.utils.VarArgHelper

/**
 * Parameter object to express `String, vararg String` in the infix-api.
 */
class RegexPatterns(pattern: String, vararg otherPatterns: String) :
    VarArgHelper<String> {
    override val expected = pattern
    override val otherExpected = otherPatterns
}
