package ch.tutteli.atrium.domain.robstoll.creating

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.LocalDateAssertions
import ch.tutteli.atrium.domain.robstoll.lib.creating._dayOfWeek
import ch.tutteli.atrium.domain.robstoll.lib.creating._month
import ch.tutteli.atrium.domain.robstoll.lib.creating._year
import java.time.LocalDate

class LocalDateAssertionsImpl : LocalDateAssertions {
    override fun <T: LocalDate> year(assertionContainer: Expect<T>) = _year(assertionContainer)

    override fun <T: LocalDate> month(assertionContainer: Expect<T>) = _month(assertionContainer)

    override fun <T : LocalDate> dayOfWeek(assertionContainer: Expect<T>) = _dayOfWeek(assertionContainer)
}
