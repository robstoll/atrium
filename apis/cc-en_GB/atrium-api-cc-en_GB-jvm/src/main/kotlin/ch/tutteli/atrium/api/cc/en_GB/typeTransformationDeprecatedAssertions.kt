@file:Suppress("DEPRECATION" /* will be removed with 0.10.0 */)
@file:JvmMultifileClass
@file:JvmName("TypeTransformationAssertionsKt")

package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.creating.SubjectProvider

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
@Deprecated(
    "Will be removed with 0.10.0 because it is redundant in terms of `ist(expected)` without adding enough to be a legit alternative.",
    ReplaceWith("toBe(expected)")
)
inline fun <reified T : Any> AssertionPlantNullable<T?>.notToBeNullBut(expected: T) {
    notToBeNull {
        @Suppress("DEPRECATION")
        toBe(expected)
    }
}

