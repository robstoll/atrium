package ch.tutteli.atrium.api.infix.en_GB.samples

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.Month
import kotlin.test.Test

class LocalDateExpectationSamples {

    @Test
    fun yearFeature() {
        expect(LocalDate.of(2021, Month.OCTOBER, 9)).year toEqual 2021
        //                                          | subject is now of type Int

        fails {
            expect(LocalDate.of(2021, Month.OCTOBER, 9)).year toEqual 2022
            //                                          | subject is now of type Int
        }
    }

    @Test
    fun year() {
        expect(LocalDate.of(2021, Month.OCTOBER, 9)) year {
            // subject inside this block is of type Int (actually 2021)
            it toEqual 2021
            it toBeGreaterThan 2020
        } // subject here is back to type LocalDateTime

        fails {
            expect(LocalDate.of(2021, Month.OCTOBER, 9)) year {
                // subject inside this block is of type Int (actually 2021)
                it notToEqual 2021
                it toBeGreaterThan 2022
                it toBeLessThan 2020
            } // subject here is back to type LocalDateTime
        }
    }

    @Test
    fun monthFeature() {
        expect(LocalDate.of(2021, Month.OCTOBER, 9))
            .month toEqual Month.OCTOBER.value
        //  | subject is now of type Int

        fails {
            expect(LocalDate.of(2021, Month.OCTOBER, 9))
                .month toEqual Month.SEPTEMBER.value
            //  | subject is now of type Int
        }
    }

    @Test
    fun month() {
        expect(LocalDate.of(2021, Month.OCTOBER, 9)) month {
            // subject inside this block is of type Int (actually Month.OCTOBER.value i.e. 10)
            it toEqual Month.OCTOBER.value
            it notToEqual Month.SEPTEMBER.value
        } // subject here is back to type LocalDateTime

        fails {
            expect(LocalDate.of(2021, Month.OCTOBER, 9)) month {
                // subject inside this block is of type Int (actually Month.OCTOBER.value i.e. 10)
                it toEqual Month.SEPTEMBER.value
                it notToEqual Month.OCTOBER.value
            } // subject here is back to type LocalDateTime
        }
    }

    @Test
    fun dayOfWeekFeature() {
        expect(LocalDate.of(2021, Month.OCTOBER, 9))
            .dayOfWeek toEqual DayOfWeek.SATURDAY
        //  | subject is now of type DayOfWeek

        fails {
            expect(LocalDate.of(2021, Month.OCTOBER, 9))
                .dayOfWeek toEqual DayOfWeek.MONDAY
            //  | subject is now of type DayOfWeek
        }
    }

    @Test
    fun dayOfWeek() {
        expect(LocalDate.of(2021, Month.OCTOBER, 9)) dayOfWeek {
            // subject inside this block is of type DayOfWeek (actually SATURDAY)
            it toEqual DayOfWeek.SATURDAY
            it notToEqual DayOfWeek.SUNDAY
        } // subject here is back to type LocalDateTime

        fails {
            expect(LocalDate.of(2021, Month.OCTOBER, 9)) dayOfWeek {
                // subject inside this block is of type DayOfWeek (actually SATURDAY)
                it toEqual DayOfWeek.MONDAY
                it notToEqual DayOfWeek.SATURDAY
            } // subject here is back to type LocalDateTime
        }
    }

    @Test
    fun dayFeature() {
        expect(LocalDate.of(2021, Month.OCTOBER, 9)).day toEqual 9
        //                                          | subject is now of type Int

        fails {
            expect(LocalDate.of(2021, Month.OCTOBER, 9)).day toEqual 5
            //                                          | subject is now of type Int
        }
    }

    @Test
    fun day() {
        expect(LocalDate.of(2021, Month.OCTOBER, 9)) day {
            // subject inside this block is of type Int (actually 9)
            it toEqual 9
            it toBeGreaterThan 5
        } // subject here is back to type LocalDateTime

        fails {
            expect(LocalDate.of(2021, Month.OCTOBER, 9)) day {
                // subject inside this block is of type Int (actually 9)
                it toEqual 5
                it toBeLessThan 7
            } // subject here is back to type LocalDateTime
        }
    }
}
