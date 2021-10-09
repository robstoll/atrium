package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.Month
import kotlin.test.Test

class LocalDateExpectationSamples {

    @Test
    fun year() {
        expect(LocalDate.of(2021, Month.OCTOBER, 9))
            .year {
                toEqual(2021)
                toBeGreaterThan(2020)
            }

        fails {
            expect(LocalDate.of(2021, Month.OCTOBER, 9))
                .year {
                    notToEqual(2022)
                    toBeGreaterThan(2022)
                    toBeLessThan(2020)
                }
        }
    }

    @Test
    fun month() {
        expect(LocalDate.of(2021, Month.OCTOBER, 9))
            .month {
                toEqual(Month.OCTOBER.value)
                notToEqual(Month.SEPTEMBER.value)
            }

        fails {
            expect(LocalDate.of(2021, Month.OCTOBER, 9))
                .month {
                    toEqual(Month.SEPTEMBER.value)
                    notToEqual(Month.OCTOBER.value)
                }
        }
    }

    @Test
    fun dayOfWeek() {
        expect(LocalDate.of(2021, Month.OCTOBER, 9))
            .dayOfWeek {
                toEqual(DayOfWeek.SATURDAY)
                notToEqual(DayOfWeek.SUNDAY)
            }

        fails {
            expect(LocalDate.of(2021, Month.OCTOBER, 9))
                .dayOfWeek {
                    toEqual(DayOfWeek.MONDAY)
                    notToEqual(DayOfWeek.SATURDAY)
                }
        }
    }

    @Test
    fun day() {
        expect(LocalDate.of(2021, Month.OCTOBER, 9))
            .day {
                toEqual(9)
                toBeGreaterThan(5)
            }

        fails {
            expect(LocalDate.of(2021, Month.OCTOBER, 9))
                .day {
                    toEqual(5)
                    toBeLessThan(7)
                }
        }
    }

}
