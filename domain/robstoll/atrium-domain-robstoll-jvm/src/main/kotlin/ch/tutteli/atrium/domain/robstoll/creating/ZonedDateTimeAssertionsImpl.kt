package ch.tutteli.atrium.domain.robstoll.creating

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.ZonedDateTimeAssertions
import ch.tutteli.atrium.domain.robstoll.lib.creating._year
import java.time.ZonedDateTime

class ZonedDateTimeAssertionsImpl : ZonedDateTimeAssertions {
    override fun year(assertionContainer: Expect<ZonedDateTime>) = _year(assertionContainer)
}
