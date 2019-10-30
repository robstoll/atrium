package ch.tutteli.atrium.domain.robstoll.creating

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.OptionalAssertions
import ch.tutteli.atrium.domain.robstoll.lib.creating._isEmpty
import java.util.*

class OptionalAssertionsImpl : OptionalAssertions {

    override fun <T : Optional<*>> isEmpty(assertionContainer: Expect<T>) = _isEmpty(assertionContainer)
}
