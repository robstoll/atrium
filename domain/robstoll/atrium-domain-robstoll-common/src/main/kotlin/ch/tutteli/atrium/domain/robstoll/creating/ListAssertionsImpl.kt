package ch.tutteli.atrium.domain.robstoll.creating

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.ListAssertions
import ch.tutteli.atrium.domain.robstoll.lib.creating._get


class ListAssertionsImpl : ListAssertions, ListAssertionsDeprecatedImpl() {

    override fun <E, T : List<E>> get(
        assertionContainer: Expect<T>,
        index: Int
    ) = _get(assertionContainer, index)
}
