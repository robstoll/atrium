package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import java.time.*
import kotlin.test.Test

class ZonedDateTimeFeatureExtractorSamples {

    @Test
    fun yearFeature() {
        val zonedDateTime = ZonedDateTime.of(LocalDateTime.of(2021, 10, 9, 11, 56), ZoneId.systemDefault())

        expect(zonedDateTime)
            .year // subject is now of type Int (actually 2021)
            .toEqual(2021)

        fails {
            expect(zonedDateTime)
                .year // subject is now of type Int (actually 2021)
                .toBeGreaterThan(2030)  // fails
                .toBeLessThan(2000)     // not evaluated/reported because toBeLessThan already fails
            //                             use `.year { ... }` if you want that all expectations are evaluated
        }
    }

    @Test
    fun year() {
        val zonedDateTime = ZonedDateTime.of(LocalDateTime.of(2021, 10, 9, 11, 56), ZoneId.systemDefault())

        expect(zonedDateTime)
            .year { // subject inside this expectation-group is of type Int (actually 2021)
                toEqual(2021)
                toBeGreaterThan(2020)
            } // subject here is back to type ZonedDateTime

        fails {
            expect(zonedDateTime)
                .year { // subject inside this expectation-group is of type Int (actually 2021)
                    notToEqual(1980)    // fails
                    toBeLessThan(2000)  // not evaluated/reported because notToEqual already fails
                    //                     use `.year.` if you want a fail fast behaviour
                }
        }
    }

    @Test
    fun monthFeature() {
        val zonedDateTime = ZonedDateTime.of(LocalDateTime.of(2021, 10, 9, 11, 56), ZoneId.systemDefault())

        expect(zonedDateTime)
            .month // subject is now of type Int (actually Month.OCTOBER.value i.e. 10)
            .toEqual(10)

        fails {
            expect(zonedDateTime)
                .month               // subject is now of type Int (actually Month.OCTOBER.value i.e. 10)
                .toBeLessThan(9)     // fails
                .toBeGreaterThan(11) // not evaluated/reported because toBeLessThan already fails
            //                          use `.month { ... }` if you want that all expectations are evaluated
        }
    }

    @Test
    fun month() {
        val zonedDateTime = ZonedDateTime.of(LocalDateTime.of(2021, 10, 9, 11, 56), ZoneId.systemDefault())

        expect(zonedDateTime)
            .month { // subject inside this expectation-group is of type Int (actually Month.OCTOBER.value i.e. 10)
                toBeGreaterThan(5)
                notToEqual(8)
            } // subject here is back to type ZonedDateTime

        fails {
            expect(zonedDateTime)
                .month { // subject inside this expectation-group is of type Int (actually Month.OCTOBER.value i.e. 10)
                    toBeLessThan(9)      // fails
                    toBeGreaterThan(11)  // still evaluated even though toBeLessThan already fails
                    //                      use `.month.` if you want a fail fast behaviour
                }
        }
    }

    @Test
    fun dayOfWeekFeature() {
        val zonedDateTime = ZonedDateTime.of(LocalDateTime.of(2021, 10, 9, 11, 56), ZoneId.systemDefault())

        expect(zonedDateTime)
            .dayOfWeek // subject is now of type DayOfWeek (actually SATURDAY)
            .toEqual(DayOfWeek.SATURDAY)


        fails {
            expect(zonedDateTime)
                .dayOfWeek // subject is now of type DayOfWeek (actually SATURDAY)
                .toEqual(DayOfWeek.MONDAY)       // fails
                .notToEqual(DayOfWeek.SATURDAY)  // not evaluated/reported because toEqual already fails
            //                                      use `.dayOfWeek { ... }` if you want that all expectations are evaluated

        }
    }

    @Test
    fun dayOfWeek() {
        val zonedDateTime = ZonedDateTime.of(LocalDateTime.of(2021, 10, 9, 11, 56), ZoneId.systemDefault())

        expect(zonedDateTime)
            .dayOfWeek { // subject inside this expectation-group is of type DayOfWeek (actually SATURDAY)
                toEqual(DayOfWeek.SATURDAY)
                notToEqual(DayOfWeek.SUNDAY)
            } // subject here is back to type ZonedDateTime

        fails {
            expect(zonedDateTime)
                .dayOfWeek { // subject inside this expectation-group is of type DayOfWeek (actually SATURDAY)
                    toEqual(DayOfWeek.MONDAY)       // fails
                    notToEqual(DayOfWeek.SATURDAY)  // still evaluated even though toEqual already fails
                    //                                 use `.dayOfWeek.` if you want a fail fast behaviour
                }
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
                .toBeGreaterThan(10) // not evaluated/reported because toEqual already fails
            //                          use `.day { ... }` if you want that all expectations are evaluated
        }
    }

    @Test
    fun day() {
        val zonedDateTime = ZonedDateTime.of(LocalDateTime.of(2021, 10, 9, 11, 56), ZoneId.systemDefault())

        expect(zonedDateTime)
            .day { // subject inside this expectation-group is of type Int (actually 9)
                toEqual(9)
                toBeGreaterThan(5)
            } // subject here is back to type ZonedDateTime

        fails {
            expect(zonedDateTime)
                .day { // subject inside this expectation-group is of type Int (actually 9)
                    toEqual(5)       // fails
                    toBeLessThan(7)  // still evaluated even though toEqual already fails
                    //                  use `.day.` if you want a fail fast behaviour
                } // subject here is back to type ZonedDateTime
        }
    }
}
