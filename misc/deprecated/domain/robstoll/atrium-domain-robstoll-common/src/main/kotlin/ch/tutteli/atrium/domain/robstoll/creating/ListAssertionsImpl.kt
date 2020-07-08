//TODO remove file with 1.0.0
@file:Suppress("DEPRECATION")
package ch.tutteli.atrium.domain.robstoll.creating

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.ListAssertions
import ch.tutteli.atrium.domain.robstoll.lib.creating._get


class ListAssertionsImpl : ListAssertions, ListAssertionsDeprecatedImpl() {

    override fun <E, T : List<E>> get(
        expect: Expect<T>,
        index: Int
    ) = _get(expect, index)
}
