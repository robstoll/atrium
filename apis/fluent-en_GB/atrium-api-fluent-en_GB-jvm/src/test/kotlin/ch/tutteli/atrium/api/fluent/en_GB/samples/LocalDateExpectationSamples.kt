package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.Month
import kotlin.test.Test

class LocalDateExpectationSamples {

    @Test
    fun yearFeature() {
        expect(LocalDate.of(2021, Month.OCTOBER, 9))
            .year.toEqual(2021)

        fails {
            expect(LocalDate.of(2021, Month.OCTOBER, 9))
                .year.notToEqual(2021)
        }
    }

    @Test
    fun year() {
        expect(LocalDate.of(2021, Month.OCTOBER, 9))
            .year {
                // subject inside this block is of type Int (actually 2021)
                toEqual(2021)
                toBeGreaterThan(2020)
            }

        fails {
            expect(LocalDate.of(2021, Month.OCTOBER, 9))
                .year {
                    // subject inside this block is of type Int (actually 2021)
                    notToEqual(2021)
                    toBeGreaterThan(2022)
                    toBeLessThan(2020)
                }
        }
    }

    @Test
    fun monthFeature() {
        expect(LocalDate.of(2021, Month.OCTOBER, 9))
            .month.toEqual(Month.OCTOBER.value)

        fails {
            expect(LocalDate.of(2021, Month.OCTOBER, 9))
                .month.toEqual(Month.SEPTEMBER.value)
        }
    }

    @Test
    fun month() {
        expect(LocalDate.of(2021, Month.OCTOBER, 9))
            .month {
                // subject inside this block is of type Int (actually Month.OCTOBER.value i.e. 10)
                toEqual(Month.OCTOBER.value)
                notToEqual(Month.SEPTEMBER.value)
            }

        fails {
            expect(LocalDate.of(2021, Month.OCTOBER, 9))
                .month {
                    // subject inside this block is of type Int (actually Month.OCTOBER.value i.e. 10)
                    toEqual(Month.SEPTEMBER.value)
                    notToEqual(Month.OCTOBER.value)
                }
        }
    }

    @Test
    fun dayOfWeekFeature() {
        expect(LocalDate.of(2021, Month.OCTOBER, 9))
            .dayOfWeek.toEqual(DayOfWeek.SATURDAY)

        fails {
            expect(LocalDate.of(2021, Month.OCTOBER, 9))
                .dayOfWeek.toEqual(DayOfWeek.MONDAY)
        }
    }

    @Test
    fun dayOfWeek() {
        expect(LocalDate.of(2021, Month.OCTOBER, 9))
            .dayOfWeek {
                // subject inside this block is of type DayOfWeek (actually SATURDAY)
                toEqual(DayOfWeek.SATURDAY)
                notToEqual(DayOfWeek.SUNDAY)
            }

        fails {
            expect(LocalDate.of(2021, Month.OCTOBER, 9))
                .dayOfWeek {
                    // subject inside this block is of type DayOfWeek (actually SATURDAY)
                    toEqual(DayOfWeek.MONDAY)
                    notToEqual(DayOfWeek.SATURDAY)
                }
        }
    }

    @Test
    fun dayFeature() {
        expect(LocalDate.of(2021, Month.OCTOBER, 9))
            .day.toEqual(9)

        fails {
            expect(LocalDate.of(2021, Month.OCTOBER, 9))
                .day.toEqual(5)
        }
    }

    @Test
    fun day() {
        expect(LocalDate.of(2021, Month.OCTOBER, 9))
            .day {
                // subject inside this block is of type Int (actually 9)
                toEqual(9)
                toBeGreaterThan(5)
            }

        fails {
            expect(LocalDate.of(2021, Month.OCTOBER, 9))
                .day {
                    // subject inside this block is of type Int (actually 9)
                    toEqual(5)
                    toBeLessThan(7)
                }
        }
    }

}
