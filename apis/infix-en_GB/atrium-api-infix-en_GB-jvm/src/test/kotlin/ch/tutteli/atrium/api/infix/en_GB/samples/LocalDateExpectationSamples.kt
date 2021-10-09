package ch.tutteli.atrium.api.infix.en_GB.samples

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.Month
import kotlin.test.Test

class LocalDateExpectationSamples {

    @Test
    fun year() {
        expect(LocalDate.of(2021, Month.OCTOBER, 9)) year {
            it toEqual 2021
            it toBeGreaterThan 2020
        }

        fails {
            expect(LocalDate.of(2021, Month.OCTOBER, 9)) year {
                it notToEqual 2022
                it toBeGreaterThan 2022
                it toBeLessThan 2020
            }
        }
    }

    @Test
    fun month() {
        expect(LocalDate.of(2021, Month.OCTOBER, 9)) month {
            it toEqual Month.OCTOBER.value
            it notToEqual Month.SEPTEMBER.value
        }

        fails {
            expect(LocalDate.of(2021, Month.OCTOBER, 9)) month {
                it toEqual Month.SEPTEMBER.value
                it notToEqual Month.OCTOBER.value
            }
        }
    }

    @Test
    fun dayOfWeek() {
        expect(LocalDate.of(2021, Month.OCTOBER, 9)) dayOfWeek {
            it toEqual DayOfWeek.SATURDAY
            it notToEqual DayOfWeek.SUNDAY
        }

        fails {
            expect(LocalDate.of(2021, Month.OCTOBER, 9)) dayOfWeek {
                it toEqual DayOfWeek.MONDAY
                it notToEqual DayOfWeek.SATURDAY
            }
        }
    }

    @Test
    fun day() {
        expect(LocalDate.of(2021, Month.OCTOBER, 9)) day {
            it toEqual 9
            it toBeGreaterThan 5
        }

        fails {
            expect(LocalDate.of(2021, Month.OCTOBER, 9)) day {
                it toEqual 5
                it toBeLessThan 7
            }
        }
    }

}
