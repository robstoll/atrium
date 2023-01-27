package ch.tutteli.atrium.api.infix.en_GB.samples

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import kotlin.test.Test

class DateSubjectChangerSamples {

    private val formatter: DateFormat = SimpleDateFormat("yyyy-MM-dd")

    @Test
    fun asLocalDateFeature() {
        val date = formatter.parse("1995-07-17")

        expect(date) asLocalDate o toEqual(LocalDate.parse("1995-07-17")) // Subject is now of type LocalDate

        fails {
            expect(date) asLocalDate
                o toBeAfter(LocalDate.parse("2025-07-17")) toBeBefore(LocalDate.parse("1996-07-17"))
        }
    }

    @Test
    fun asLocalDate() {
        val date = formatter.parse("1995-07-17")

        expect(date) asLocalDate { // subject within this expectation-group is of type LocalDate
            it toBeAfterOrTheSamePointInTimeAs(LocalDate.parse("1994-07-17"))
            it toBeBeforeOrTheSamePointInTimeAs(LocalDate.parse("1996-07-17"))
        } // subject here is back to type java.util.Date

        fails {
            // all expectations inside an expectation-group are evaluated together; for more details see:
            // https://github.com/robstoll/atrium#define-single-expectations-or-an-expectation-group

            expect(date) asLocalDate {
                it toBeAfter(LocalDate.parse("2025-07-17"))            // fails
                it toBeBefore(LocalDate.parse("1994-07-17"))           // still evaluated even though `toBeAfter` already fails
                                                                            // use ` asLocalDate().` if you want a fail fast behaviour
            }
        }
    }
}
