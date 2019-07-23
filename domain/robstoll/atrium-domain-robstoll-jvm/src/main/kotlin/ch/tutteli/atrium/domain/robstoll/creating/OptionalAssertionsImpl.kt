package ch.tutteli.atrium.domain.robstoll.creating

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.OptionalAssertions
import ch.tutteli.atrium.domain.robstoll.lib.creating._isPresent
import java.util.*

/**
 * Robstoll's implementation of [OptionalAssertions].
 */
class OptionalAssertionsImpl : OptionalAssertions {

    override fun <T> isPresent(assertionContainer: Expect<Optional<T>>) = _isPresent(assertionContainer)
}
