package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.time.LocalDate
import java.util.Date

abstract class DateAsLocalDateExpectationsSpec(
    asLocalDate: Expect<Date>.(Expect<LocalDate>.() -> Unit) -> Expect<Date>
) : Spek({
    describe("asLocalDate") {
        it("transformation can be applied and a subsequent assertion made") {
            val date = Date()
            expect(date).asLocalDate { toEqual(LocalDate.now()) }
        }
    }
})
