package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import java.time.*
import kotlin.test.Test

class ZonedDateTimeExpectationSamples {

    @Test
    fun yearFeature() {
        expect(ZonedDateTime.of(LocalDateTime.of(2021, 10, 9, 11, 56), ZoneId.systemDefault()))
            .year // subject is now of type Int (actually 2021)
            .toEqual(2021)

        fails {
            expect(ZonedDateTime.of(LocalDateTime.of(2021, 10, 9, 11, 56), ZoneId.systemDefault()))
                .year // subject is now of type Int (actually 2021)
                .toBeGreaterThan(2030)  // fails
                .toBeLessThan(2000)     // not reported because toBeLessThan already fails
            //                             use `.year { ... }` if you want that all expectations are evaluated
        }
    }

    @Test
    fun year() {
        expect(ZonedDateTime.of(LocalDateTime.of(2021, 10, 9, 11, 56), ZoneId.systemDefault()))
            .year { // subject inside this block is of type Int (actually 2021)
                toEqual(2021)
                toBeGreaterThan(2020)
            } // subject here is back to type ZonedDateTime

        fails {
            expect(ZonedDateTime.of(LocalDateTime.of(2021, 10, 9, 11, 56), ZoneId.systemDefault()))
                .year { // subject inside this block is of type Int (actually 2021)
                    notToEqual(1980)    // fails
                    toBeLessThan(2000)  // not reported because notToEqual already fails
                    //                     use `.year.` if you want a fail fast behaviour
                } // subject here is back to type ZonedDateTime
        }
    }

    @Test
    fun monthFeature() {
        expect(ZonedDateTime.of(LocalDateTime.of(2021, 10, 9, 11, 56), ZoneId.systemDefault()))
            .month // subject is now of type Int (actually Month.OCTOBER.value i.e. 10)
            .toEqual(10)

        fails {
            expect(ZonedDateTime.of(LocalDateTime.of(2021, 10, 9, 11, 56), ZoneId.systemDefault()))
                .month               // subject is now of type Int (actually Month.OCTOBER.value i.e. 10)
                .toBeLessThan(9)     // fails
                .toBeGreaterThan(11) // not reported because toBeLessThan already fails
            //                          use `.month { ... }` if you want that all expectations are evaluated
        }
    }

    @Test
    fun month() {
        expect(ZonedDateTime.of(LocalDateTime.of(2021, 10, 9, 11, 56), ZoneId.systemDefault()))
            .month { // subject inside this block is of type Int (actually Month.OCTOBER.value i.e. 10)
                toBeGreaterThan(5)
                notToEqual(8)
            } // subject here is back to type ZonedDateTime

        fails {
            expect(ZonedDateTime.of(LocalDateTime.of(2021, 10, 9, 11, 56), ZoneId.systemDefault()))
                .month { // subject inside this block is of type Int (actually Month.OCTOBER.value i.e. 10)
                    toBeLessThan(9)      // fails
                    toBeGreaterThan(11)  // still evaluated even though toBeLessThan already fails
                    //                      use `.month.` if you want a fail fast behaviour
                } // subject here is back to type ZonedDateTime
        }
    }

    @Test
    fun dayOfWeekFeature() {
        expect(ZonedDateTime.of(LocalDateTime.of(2021, 10, 9, 11, 56), ZoneId.systemDefault()))
            .dayOfWeek // subject is now of type DayOfWeek (actually SATURDAY)
            .toEqual(DayOfWeek.SATURDAY)


        fails {
            expect(ZonedDateTime.of(LocalDateTime.of(2021, 10, 9, 11, 56), ZoneId.systemDefault()))
                .dayOfWeek // subject is now of type DayOfWeek (actually SATURDAY)
                .toEqual(DayOfWeek.MONDAY)       // fails
                .notToEqual(DayOfWeek.SATURDAY)  // not reported because toEqual already fails
            //                                      use `.dayOfWeek { ... }` if you want that all expectations are evaluated

        }
    }

    @Test
    fun dayOfWeek() {
        expect(ZonedDateTime.of(LocalDateTime.of(2021, 10, 9, 11, 56), ZoneId.systemDefault()))
            .dayOfWeek { // subject inside this block is of type DayOfWeek (actually SATURDAY)
                toEqual(DayOfWeek.SATURDAY)
                notToEqual(DayOfWeek.SUNDAY)
            } // subject here is back to type ZonedDateTime

        fails {
            expect(ZonedDateTime.of(LocalDateTime.of(2021, 10, 9, 11, 56), ZoneId.systemDefault()))
                .dayOfWeek { // subject inside this block is of type DayOfWeek (actually SATURDAY)
                    toEqual(DayOfWeek.MONDAY)       // fails
                    notToEqual(DayOfWeek.SATURDAY)  // still evaluated even though toEqual already fails
                    //                                 use `.dayOfWeek.` if you want a fail fast behaviour
                } // subject here is back to type ZonedDateTime
        }
    }

    @Test
    fun dayFeature() {
        expect(ZonedDateTime.of(LocalDateTime.of(2021, 10, 9, 11, 56), ZoneId.systemDefault()))
            .day // subject is now of type Int (actually 9)
            .toEqual(9)

        fails {
            expect(ZonedDateTime.of(LocalDateTime.of(2021, 10, 9, 11, 56), ZoneId.systemDefault()))
                .day // subject is now of type Int (actually 9)
                .toEqual(5)          // fails
                .toBeGreaterThan(10) // not reported because toEqual already fails
            //                          use `.day { ... }` if you want that all expectations are evaluated
        }
    }

    @Test
    fun day() {
        expect(ZonedDateTime.of(LocalDateTime.of(2021, 10, 9, 11, 56), ZoneId.systemDefault()))
            .day { // subject inside this block is of type Int (actually 9)
                toEqual(9)
                toBeGreaterThan(5)
            } // subject here is back to type ZonedDateTime

        fails {
            expect(ZonedDateTime.of(LocalDateTime.of(2021, 10, 9, 11, 56), ZoneId.systemDefault()))
                .day { // subject inside this block is of type Int (actually 9)
                    toEqual(5)       // fails
                    toBeLessThan(7)  // still evaluated even though toEqual already fails
                    //                  use `.day.` if you want a fail fast behaviour
                } // subject here is back to type ZonedDateTime
        }
    }
}
