//TODO remove file with 1.0.0
@file:Suppress("DEPRECATION")
package ch.tutteli.atrium.domain.robstoll.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.IteratorAssertions
import ch.tutteli.atrium.domain.robstoll.lib.creating._hasNext
import ch.tutteli.atrium.domain.robstoll.lib.creating._hasNotNext

class IteratorAssertionsImpl : IteratorAssertions {
    override fun <E, T : Iterator<E>> hasNext(expect: Expect<T>): Assertion = _hasNext(expect)

    override fun <E, T : Iterator<E>> hasNotNext(expect: Expect<T>): Assertion = _hasNotNext(expect)
}
