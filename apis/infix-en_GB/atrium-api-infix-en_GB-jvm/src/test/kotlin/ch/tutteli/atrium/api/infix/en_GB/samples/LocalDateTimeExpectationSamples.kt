package ch.tutteli.atrium.api.infix.en_GB.samples

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.Month
import kotlin.test.Test

class LocalDateTimeExpectationSamples {

    @Test
    fun year() {
        expect(LocalDateTime.of(2021, Month.OCTOBER, 9, 11, 56)) year {
            it toEqual 2021
            it toBeGreaterThan 2020
        }

        fails {
            expect(LocalDateTime.of(2021, Month.OCTOBER, 9, 11, 56)) year {
                it notToEqual 2022
                it toBeGreaterThan 2022
                it toBeLessThan 2020
            }
        }
    }

    @Test
    fun month() {
        expect(LocalDateTime.of(2021, Month.OCTOBER, 9, 11, 56)) month {
            it toEqual Month.OCTOBER.value
            it notToEqual Month.SEPTEMBER.value
        }

        fails {
            expect(LocalDateTime.of(2021, Month.OCTOBER, 9, 11, 56)) month {
                it toEqual Month.SEPTEMBER.value
                it notToEqual Month.OCTOBER.value
            }
        }
    }

    @Test
    fun dayOfWeek() {
        expect(LocalDateTime.of(2021, Month.OCTOBER, 9, 11, 56)) dayOfWeek {
            it toEqual DayOfWeek.SATURDAY
            it notToEqual DayOfWeek.SUNDAY
        }

        fails {
            expect(LocalDateTime.of(2021, Month.OCTOBER, 9, 11, 56)) dayOfWeek {
                it toEqual DayOfWeek.MONDAY
                it notToEqual DayOfWeek.SATURDAY
            }
        }
    }

    @Test
    fun day() {
        expect(LocalDateTime.of(2021, Month.OCTOBER, 9, 11, 56)) day {
            it toEqual 9
            it toBeGreaterThan 5
        }

        fails {
            expect(LocalDateTime.of(2021, Month.OCTOBER, 9, 11, 56)) day {
                it toEqual 5
                it toBeLessThan 7
            }
        }
    }

}
