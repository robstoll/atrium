package ch.tutteli.atrium.domain.robstoll.creating

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.ZonedDateTimeAssertions
import ch.tutteli.atrium.domain.robstoll.lib.creating._month
import ch.tutteli.atrium.domain.robstoll.lib.creating._year
import java.time.ZonedDateTime

class ZonedDateTimeAssertionsImpl : ZonedDateTimeAssertions {
    override fun <T: ZonedDateTime> year(assertionContainer: Expect<T>) = _year(assertionContainer)

    override fun <T: ZonedDateTime> month(assertionContainer: Expect<T>) = _month(assertionContainer)
}
