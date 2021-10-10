package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.Month
import kotlin.test.Test

class LocalDateTimeExpectationSamples {

    @Test
    fun year() {
        expect(LocalDateTime.of(2021, Month.OCTOBER, 9, 11, 56))
            .year {
                // subject inside this block is of type Int (actually 2021)
                toEqual(2021)
                toBeGreaterThan(2020)
            }

        fails {
            expect(LocalDateTime.of(2021, Month.OCTOBER, 9, 11, 56))
                .year {
                    // subject inside this block is of type Int (actually 2021)
                    notToEqual(2022)
                    toBeGreaterThan(2022)
                    toBeLessThan(2020)
                }
        }
    }

    @Test
    fun month() {
        expect(LocalDateTime.of(2021, Month.OCTOBER, 9, 11, 56))
            .month {
                // subject inside this block is of type Int (actually Month.OCTOBER.value i.e. 10)
                toEqual(Month.OCTOBER.value)
                notToEqual(Month.SEPTEMBER.value)
            }

        fails {
            expect(LocalDateTime.of(2021, Month.OCTOBER, 9, 11, 56))
                .month {
                    // subject inside this block is of type Int (actually Month.OCTOBER.value i.e. 10)
                    toEqual(Month.SEPTEMBER.value)
                    notToEqual(Month.OCTOBER.value)
                }
        }
    }

    @Test
    fun dayOfWeek() {
        expect(LocalDateTime.of(2021, Month.OCTOBER, 9, 11, 56))
            .dayOfWeek {
                // subject inside this block is of type DayOfWeek (actually SATURDAY)
                toEqual(DayOfWeek.SATURDAY)
                notToEqual(DayOfWeek.SUNDAY)
            }

        fails {
            expect(LocalDateTime.of(2021, Month.OCTOBER, 9, 11, 56))
                .dayOfWeek {
                    // subject inside this block is of type DayOfWeek (actually SATURDAY)
                    toEqual(DayOfWeek.MONDAY)
                    notToEqual(DayOfWeek.SATURDAY)
                }
        }
    }

    @Test
    fun day() {
        expect(LocalDateTime.of(2021, Month.OCTOBER, 9, 11, 56))
            .day {
                // subject inside this block is of type Int (actually 9)
                toEqual(9)
                toBeGreaterThan(5)
            }

        fails {
            expect(LocalDateTime.of(2021, Month.OCTOBER, 9, 11, 56))
                .day {
                    // subject inside this block is of type Int (actually 9)
                    toEqual(5)
                    toBeLessThan(7)
                }
        }
    }

}
