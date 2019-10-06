package ch.tutteli.atrium.domain.robstoll.creating

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.LocalDateTimeAssertions
import ch.tutteli.atrium.domain.robstoll.lib.creating._year
import java.time.LocalDateTime

class LocalDateTimeAssertionsImpl : LocalDateTimeAssertions {
    override fun year(assertionContainer: Expect<LocalDateTime>) = _year(assertionContainer)
}
