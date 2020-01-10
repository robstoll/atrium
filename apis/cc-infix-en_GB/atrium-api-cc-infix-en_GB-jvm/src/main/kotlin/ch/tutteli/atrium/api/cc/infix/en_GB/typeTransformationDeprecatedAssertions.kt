@file:Suppress("DEPRECATION" /* will be removed with 0.10.0 */)
@file:JvmMultifileClass
@file:JvmName("TypeTransformationAssertionsKt")
package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.ERR_KEYWORD_GIVEN_COLLECTION_ASSUMED
import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.Keyword
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.builders.creating.PleaseUseReplacementException


/**
 * Makes the assertion that [AssertionPlantNullable.subject][SubjectProvider.subject] is not null but the [expected] value.
 *
 * Is a shortcut for `istNichtNull { ist(expected) }`
 *
 * @return Notice, that this assertion function cannot provide a fluent API because it depends on whether the first
 *   assertion ([Assert.subject][SubjectProvider.subject] is not null) holds or not.
 *
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("Will be removed with 0.10.0 because it is redundant in terms of `ist(expected)` without adding enough to be a legit alternative.", ReplaceWith("ist(expected)"))
inline infix fun <reified T : Any> AssertionPlantNullable<T?>.notToBeNullBut(expected: T) {
    notToBeNull { this toBe expected }
}

@Deprecated(ERR_KEYWORD_GIVEN_COLLECTION_ASSUMED, ReplaceWith("this notToBeNullBut (keyword as Any)"))
@Suppress("UNUSED_PARAMETER", "unused")
infix fun <T: Any> AssertionPlantNullable<T>.notToBeNullBut(keyword: Keyword): Nothing
    = throw PleaseUseReplacementException("this notToBe (keyword as Any)")
