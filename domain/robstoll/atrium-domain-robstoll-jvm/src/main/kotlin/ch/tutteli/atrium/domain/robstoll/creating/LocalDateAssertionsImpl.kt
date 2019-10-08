package ch.tutteli.atrium.domain.robstoll.creating

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.LocalDateAssertions
import ch.tutteli.atrium.domain.robstoll.lib.creating._month
import ch.tutteli.atrium.domain.robstoll.lib.creating._year
import java.time.LocalDate

class LocalDateAssertionsImpl : LocalDateAssertions {
    override fun year(assertionContainer: Expect<LocalDate>) = _year(assertionContainer)

    override fun month(assertionContainer: Expect<LocalDate>) = _month(assertionContainer)
}
