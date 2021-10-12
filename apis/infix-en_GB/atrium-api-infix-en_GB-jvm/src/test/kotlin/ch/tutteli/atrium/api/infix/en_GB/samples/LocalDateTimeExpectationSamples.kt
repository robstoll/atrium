package ch.tutteli.atrium.api.infix.en_GB.samples

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.infix.en_GB.workaround.it
import ch.tutteli.atrium.api.verbs.internal.expect
import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.Month
import kotlin.test.Test

class LocalDateTimeExpectationSamples {

    @Test
    fun yearFeature() {
        expect(LocalDateTime.of(2021, Month.OCTOBER, 9, 11, 56)).year toEqual 2021

        fails {
            expect(LocalDateTime.of(2021, Month.OCTOBER, 9, 11, 56)).year toEqual 2022
        }
    }

    @Test
    fun year() {
        expect(LocalDateTime.of(2021, Month.OCTOBER, 9, 11, 56)) year {
            // subject inside this block is of type Int (actually 2021)
            it toEqual 2021
            it toBeGreaterThan 2020
        } // subject here is back to type LocalDateTime

        fails {
            expect(LocalDateTime.of(2021, Month.OCTOBER, 9, 11, 56)) year {
                // subject inside this block is of type Int (actually 2021)
                it notToEqual 2021
                it toBeGreaterThan 2022
                it toBeLessThan 2020
            } // subject here is back to type LocalDateTime
        }
    }

    @Test
    fun monthFeature() {
        expect(LocalDateTime.of(2021, Month.OCTOBER, 9, 11, 56))
            .month toEqual Month.OCTOBER.value

        fails {
            expect(LocalDateTime.of(2021, Month.OCTOBER, 9, 11, 56))
                .month toEqual Month.SEPTEMBER.value
        }
    }

    @Test
    fun month() {
        expect(LocalDateTime.of(2021, Month.OCTOBER, 9, 11, 56)) month {
            // subject inside this block is of type Int (actually Month.OCTOBER.value i.e. 10)
            it toEqual Month.OCTOBER.value
            it notToEqual Month.SEPTEMBER.value
        } // subject here is back to type LocalDateTime

        fails {
            expect(LocalDateTime.of(2021, Month.OCTOBER, 9, 11, 56)) month {
                // subject inside this block is of type Int (actually Month.OCTOBER.value i.e. 10)
                it toEqual Month.SEPTEMBER.value
                it notToEqual Month.OCTOBER.value
            } // subject here is back to type LocalDateTime
        }
    }

    @Test
    fun dayOfWeekFeature() {
        expect(LocalDateTime.of(2021, Month.OCTOBER, 9, 11, 56))
            .dayOfWeek toEqual DayOfWeek.SATURDAY

        fails {
            expect(LocalDateTime.of(2021, Month.OCTOBER, 9, 11, 56))
                .dayOfWeek toEqual DayOfWeek.MONDAY
        }
    }

    @Test
    fun dayOfWeek() {
        expect(LocalDateTime.of(2021, Month.OCTOBER, 9, 11, 56)) dayOfWeek {
            // subject inside this block is of type DayOfWeek (actually SATURDAY)
            it toEqual DayOfWeek.SATURDAY
            it notToEqual DayOfWeek.SUNDAY
        } // subject here is back to type LocalDateTime

        fails {
            expect(LocalDateTime.of(2021, Month.OCTOBER, 9, 11, 56)) dayOfWeek {
                // subject inside this block is of type DayOfWeek (actually SATURDAY)
                it toEqual DayOfWeek.MONDAY
                it notToEqual DayOfWeek.SATURDAY
            } // subject here is back to type LocalDateTime
        }
    }

    @Test
    fun dayFeature() {
        expect(LocalDateTime.of(2021, Month.OCTOBER, 9, 11, 56)).day toEqual 9

        fails {
            expect(LocalDateTime.of(2021, Month.OCTOBER, 9, 11, 56)).day toEqual 5
        }
    }

    @Test
    fun day() {
        expect(LocalDateTime.of(2021, Month.OCTOBER, 9, 11, 56)) day {
            // subject inside this block is of type Int (actually 9)
            it toEqual 9
            it toBeGreaterThan 5
        } // subject here is back to type LocalDateTime

        fails {
            expect(LocalDateTime.of(2021, Month.OCTOBER, 9, 11, 56)) day {
                // subject inside this block is of type Int (actually 9)
                it toEqual 5
                it toBeLessThan 7
            } // subject here is back to type LocalDateTime
        }
    }
}
