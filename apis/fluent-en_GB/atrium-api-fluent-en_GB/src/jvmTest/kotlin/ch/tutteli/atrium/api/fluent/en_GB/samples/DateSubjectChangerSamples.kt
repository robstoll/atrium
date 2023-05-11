package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import kotlin.test.Test

class DateSubjectChangerSamples {

    private val formatter: DateFormat = SimpleDateFormat("yyyy-MM-dd")

    @Test
    fun asLocalDateFeature() {
        val date = formatter.parse("1995-07-17")

        expect(date)
            .asLocalDate() // subject is now of type LocalDate
            .toEqual(LocalDate.parse("1995-07-17"))

        fails {
            expect(date)
                .asLocalDate()                                  // subject is now of type LocalDate
                .toBeAfter(LocalDate.parse("2025-07-17"))       // fails
                .toBeBefore(LocalDate.parse("1996-07-17"))      // not evaluated/reported because `toBeAfter` already fails
                                                                    // use `.asLocalDate { ... }` if you want all assertions evaluated
        }
    }

    @Test
    fun asLocalDate() {
        val date = formatter.parse("1995-07-17")

        expect(date).asLocalDate { // subject within this expectation-group is of type LocalDate
            toBeAfterOrTheSamePointInTimeAs(LocalDate.parse("1994-07-17"))
            toBeBeforeOrTheSamePointInTimeAs(LocalDate.parse("1996-07-17"))
        } // subject here is back to type java.util.Date

        fails {
            // all expectations inside an expectation-group are evaluated together; for more details see:
            // https://github.com/robstoll/atrium#define-single-expectations-or-an-expectation-group

            expect(date).asLocalDate {
                toBeAfter(LocalDate.parse("2025-07-17"))            // fails
                toBeBefore(LocalDate.parse("1994-07-17"))           // still evaluated even though `toBeAfter` already fails
                                                                        //  use `.asLocalDate().` if you want a fail fast behaviour
            }
        }
    }

    @Test
    fun asLocalDateTimeFeature() {
        val date = formatter.parse("1995-07-17")

        expect(date)
            .asLocalDateTime() // subject is now of type LocalDate
            .toEqual(LocalDateTime.parse("1995-07-17T00:00:00"))

        fails {
            expect(date)
                .asLocalDateTime()                                  // subject is now of type LocalDateTime
                .toBeAfter(LocalDateTime.parse("2025-07-17T00:00:00"))       // fails
                .toBeBefore(LocalDateTime.parse("1996-07-17T00:00:00"))      // not evaluated/reported because `toBeAfter` already fails
                                                                                  // use `.asLocalDateTime { ... }` if you want all assertions evaluated
        }
    }

    @Test
    fun asLocalDateTime() {
        val date = formatter.parse("1995-07-17")

        expect(date).asLocalDateTime { // subject within this expectation-group is of type LocalDateTime
            toBeAfterOrTheSamePointInTimeAs(LocalDateTime.parse("1994-07-17T00:00:00"))
            toBeBeforeOrTheSamePointInTimeAs(LocalDateTime.parse("1996-07-17T00:00:00"))
        } // subject here is back to type java.util.Date

        fails {
            // all expectations inside an expectation-group are evaluated together; for more details see:
            // https://github.com/robstoll/atrium#define-single-expectations-or-an-expectation-group

            expect(date).asLocalDateTime {
                toBeAfter(LocalDateTime.parse("2025-07-17T00:00:00"))            // fails
                toBeBefore(LocalDateTime.parse("1994-07-17T00:00:00"))           // still evaluated even though `toBeAfter` already fails
                                                                                    //  use `.asLocalDateTime().` if you want a fail fast behaviour
            }
        }
    }
}
