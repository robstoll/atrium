package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

abstract class DateAsLocalDateTimeExpectationsSpec(
    asLocalDateTime: Expect<Date>.(Expect<LocalDateTime>.() -> Unit) -> Expect<Date>
) : Spek({
    describe("asLocalDateTime") {
        it("transformation can be applied and a subsequent assertion made") {
            val formatter: DateFormat = SimpleDateFormat("yyyy-MM-dd")
            val date = formatter.parse("1995-07-17")
            expect(date).asLocalDateTime { toEqual(LocalDateTime.parse("1995-07-17T00:00:00")) }
        }
    }
})

