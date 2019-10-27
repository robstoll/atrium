package ch.tutteli.atrium.domain.robstoll.creating

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.LocalDateTimeAssertions
import ch.tutteli.atrium.domain.robstoll.lib.creating._dayOfWeek
import ch.tutteli.atrium.domain.robstoll.lib.creating._month
import ch.tutteli.atrium.domain.robstoll.lib.creating._year
import java.time.LocalDateTime

class LocalDateTimeAssertionsImpl : LocalDateTimeAssertions {
    override fun <T: LocalDateTime> year(assertionContainer: Expect<T>) = _year(assertionContainer)

    override fun <T: LocalDateTime> month(assertionContainer: Expect<T>) = _month(assertionContainer)

    override fun <T: LocalDateTime> dayOfWeek(assertionContainer: Expect<T>) = _dayOfWeek(assertionContainer)
}
