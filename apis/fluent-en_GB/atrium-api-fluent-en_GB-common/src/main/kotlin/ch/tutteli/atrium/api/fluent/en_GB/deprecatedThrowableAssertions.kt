package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.typeutils.CharSequenceOrNumberOrChar

/**
 * Expects that the property [Throwable.message] of the subject of `this` expectation is not null and contains
 * [expected]'s [toString] representation and the [toString] representation of the [otherExpected] (if given),
 * using a non-disjoint search.
 *
 * It is more or less a shortcut for `message { contains.atLeast(1).values(expected, otherExpected) }`, depending on
 * the implementation though.
 *
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed.
 * This function expects [CharSequenceOrNumberOrChar] (which is a typealias for [Any]) for your convenience,
 * so that you can mix [String] and [Int] for instance.
 *
 * @return an [Expect] for the subject of `this` expectation.
 */
@Deprecated(
    "Use messageToContain; will be removed with 1.0.0 at the latest",
    ReplaceWith(
        "this.messageToContain<T>(expected, *otherExpected)",
        "ch.tutteli.atrium.api.fluent.en_GB.messageToContain"
    )
)
fun <T : Throwable> Expect<T>.messageContains(
    expected: CharSequenceOrNumberOrChar,
    vararg otherExpected: CharSequenceOrNumberOrChar
): Expect<T> = message { toContain(expected, *otherExpected) }
