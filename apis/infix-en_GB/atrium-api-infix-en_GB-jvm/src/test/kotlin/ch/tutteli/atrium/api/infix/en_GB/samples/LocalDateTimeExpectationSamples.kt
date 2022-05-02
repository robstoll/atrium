package ch.tutteli.atrium.api.infix.en_GB.samples

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.Month
import kotlin.test.Test

class LocalDateTimeExpectationSamples {

    @Test
    fun yearFeature() {
        expect(LocalDateTime.of(2021, Month.OCTOBER, 9, 11, 56))
            .year toEqual 2021
        //    | subject is now of type Int (actually 2021)

        fails {
            expect(LocalDateTime.of(2021, Month.OCTOBER, 9, 11, 56))
                .year toEqual 2022 toBeLessThan 2000
            //    |     |            | not reported because notToEqual already fails
            //    |     |            | use `year { ... }` if you want that all expectations are evaluated
            //    |     | fails
            //    | subject is now of type Int (actually 2021)
        }
    }

    @Test
    fun year() {
        expect(LocalDateTime.of(2021, Month.OCTOBER, 9, 11, 56)) year {
            // subject inside this expectation-group is of type Int (actually 2021)
            it toEqual 2021
            it toBeGreaterThan 2020
        } // subject here is back to type LocalDateTime

        fails {
            expect(LocalDateTime.of(2021, Month.OCTOBER, 9, 11, 56)) year {
                // subject inside this expectation-group is of type Int (actually 2021)
                it notToEqual 2021       // fails
                it toBeGreaterThan 2022  // not evaluated/reported because notToEqual already fails
                //                          use `.year.` if you want a fail fast behaviour
            } // subject here is back to type LocalDateTime
        }
    }

    @Test
    fun monthFeature() {
        expect(LocalDateTime.of(2021, Month.OCTOBER, 9, 11, 56))
            .month toEqual Month.OCTOBER.value
        //    | subject is now of type Int (actually Month.OCTOBER.value i.e. 10)

        fails {
            expect(LocalDateTime.of(2021, Month.OCTOBER, 9, 11, 56))
                .month toBeLessThan 9 toBeGreaterThan 11
            //    |     |              | not reported because toBeLessThan already fails
            //    |     |              | use `month { ... }` if you want that all expectations are evaluated
            //    |     | fails
            //    | subject is now of type Int (actually Month.OCTOBER.value i.e. 10)
        }
    }

    @Test
    fun month() {
        expect(LocalDateTime.of(2021, Month.OCTOBER, 9, 11, 56)) month {
            // subject inside this expectation-group is of type Int (actually Month.OCTOBER.value i.e. 10)
            it toEqual Month.OCTOBER.value
            it notToEqual Month.SEPTEMBER.value
        } // subject here is back to type LocalDateTime

        fails {
            expect(LocalDateTime.of(2021, Month.OCTOBER, 9, 11, 56)) month {
                // subject inside this expectation-group is of type Int (actually Month.OCTOBER.value i.e. 10)
                it toBeLessThan 9      // fails
                it toBeGreaterThan 11  // still evaluated even though toBeLessThan already fails
                //                        use `.month` if you want a fail fast behaviour
            } // subject here is back to type LocalDateTime
        }
    }

    @Test
    fun dayOfWeekFeature() {
        expect(LocalDateTime.of(2021, Month.OCTOBER, 9, 11, 56))
            .dayOfWeek toEqual DayOfWeek.SATURDAY
        //    | subject is now of type DayOfWeek (actually SATURDAY)

        fails {
            expect(LocalDateTime.of(2021, Month.OCTOBER, 9, 11, 56))
                .dayOfWeek toEqual DayOfWeek.MONDAY
            //    |          |              | not reported because toEqual already fails
            //    |          |              | use `dayOfWeek { ... }` if you want that all expectations are evaluated
            //    |          | fails
            //    | subject is now of type DayOfWeek (actually SATURDAY)
        }
    }

    @Test
    fun dayOfWeek() {
        expect(LocalDateTime.of(2021, Month.OCTOBER, 9, 11, 56)) dayOfWeek {
            // subject inside this expectation-group is of type DayOfWeek (actually SATURDAY)
            it toEqual DayOfWeek.SATURDAY
            it notToEqual DayOfWeek.SUNDAY
        } // subject here is back to type LocalDateTime

        fails {
            expect(LocalDateTime.of(2021, Month.OCTOBER, 9, 11, 56)) dayOfWeek {
                // subject inside this expectation-group is of type DayOfWeek (actually SATURDAY)
                it toEqual DayOfWeek.MONDAY       // fails
                it notToEqual DayOfWeek.SATURDAY  // still evaluated even though toEqual already fails
                //                                   use `.dayOfWeek.` if you want a fail fast behaviour
            } // subject here is back to type LocalDateTime
        }
    }

    @Test
    fun dayFeature() {
        expect(LocalDateTime.of(2021, Month.OCTOBER, 9, 11, 56))
            .day toEqual 9
        //    | subject is now of type Int (actually 9)

        fails {
            expect(LocalDateTime.of(2021, Month.OCTOBER, 9, 11, 56))
                .day toEqual 5 toBeGreaterThan 10
            //    |    |         | not reported because toEqual already fails
            //    |    |         | use `day { ... }` if you want that all expectations are evaluated
            //    |    | fails
            //    | subject is now of type Int (actually 9)
        }
    }

    @Test
    fun day() {
        expect(LocalDateTime.of(2021, Month.OCTOBER, 9, 11, 56)) day {
            // subject inside this expectation-group is of type Int (actually 9)
            it toEqual 9
            it toBeGreaterThan 5
        } // subject here is back to type LocalDateTime

        fails {
            expect(LocalDateTime.of(2021, Month.OCTOBER, 9, 11, 56)) day {
                // subject inside this expectation-group is of type Int (actually 9)
                it toEqual 5       // fails
                it toBeLessThan 7  // still evaluated even though toEqual already fails
                //                    use `.day` if you want a fail fast behaviour
            } // subject here is back to type LocalDateTime
        }
    }
}
