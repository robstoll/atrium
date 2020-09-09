package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.testutils.WithAsciiReporter
import org.spekframework.spek2.Spek
import java.time.LocalDate
import java.time.chrono.ChronoLocalDate
import java.time.chrono.JapaneseDate

class ChronoLocalDateAssertionsSpec : Spek({
    include(ChronoLocalDateSpec)
    include(ChronoLocalDateAsStringSpec)
}) {
    object ChronoLocalDateSpec : ch.tutteli.atrium.specs.integration.ChronoLocalDateAssertionSpec(
        fun1<ChronoLocalDate, ChronoLocalDate>(Expect<ChronoLocalDate>::isBefore),
        fun1<ChronoLocalDate, ChronoLocalDate>(Expect<ChronoLocalDate>::isBeforeOrEqual),
        fun1<ChronoLocalDate, ChronoLocalDate>(Expect<ChronoLocalDate>::isAfter),
        fun1<ChronoLocalDate, ChronoLocalDate>(Expect<ChronoLocalDate>::isAfterOrEqual),
        fun1<ChronoLocalDate, ChronoLocalDate>(Expect<ChronoLocalDate>::isEqual)
    )

    object ChronoLocalDateAsStringSpec : ch.tutteli.atrium.specs.integration.ChronoLocalDateAsStringAssertionSpec(
        fun1<ChronoLocalDate, String>(Expect<ChronoLocalDate>::isBefore),
        fun1<ChronoLocalDate, String>(Expect<ChronoLocalDate>::isBeforeOrEqual),
        fun1<ChronoLocalDate, String>(Expect<ChronoLocalDate>::isAfter),
        fun1<ChronoLocalDate, String>(Expect<ChronoLocalDate>::isAfterOrEqual),
        fun1<ChronoLocalDate, String>(Expect<ChronoLocalDate>::isEqual)
    )

    companion object : WithAsciiReporter()

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        val chronoLocalDate: ChronoLocalDate = notImplemented()
        var a1: Expect<ChronoLocalDate> = notImplemented()
        var a2: Expect<LocalDate> = notImplemented()

        a1 = a1 isBefore LocalDate.now()
        a1 = a1 isBeforeOrEqual LocalDate.now()
        a1 = a1 isAfter LocalDate.now()
        a1 = a1 isAfterOrEqual LocalDate.now()
        a1 = a1 isEqual LocalDate.now()

        a2 = a2 isBefore LocalDate.now()
        a2 = a2 isBeforeOrEqual LocalDate.now()
        a2 = a2 isAfter LocalDate.now()
        a2 = a2 isAfterOrEqual LocalDate.now()
        a2 = a2 isEqual LocalDate.now()

        a1 = a1 isBefore JapaneseDate.now()
        a1 = a1 isBeforeOrEqual JapaneseDate.now()
        a1 = a1 isAfter JapaneseDate.now()
        a1 = a1 isAfterOrEqual JapaneseDate.now()
        a1 = a1 isEqual JapaneseDate.now()

        a2 = a2 isBefore JapaneseDate.now()
        a2 = a2 isBeforeOrEqual JapaneseDate.now()
        a2 = a2 isAfter JapaneseDate.now()
        a2 = a2 isAfterOrEqual JapaneseDate.now()
        a2 = a2 isEqual JapaneseDate.now()

        a1 = a1 isBefore chronoLocalDate
        a1 = a1 isBeforeOrEqual chronoLocalDate
        a1 = a1 isAfter chronoLocalDate
        a1 = a1 isAfterOrEqual chronoLocalDate
        a1 = a1 isEqual chronoLocalDate

        a2 = a2 isBefore chronoLocalDate
        a2 = a2 isBeforeOrEqual chronoLocalDate
        a2 = a2 isAfter chronoLocalDate
        a2 = a2 isAfterOrEqual chronoLocalDate
        a2 = a2 isEqual chronoLocalDate

        a1 = a1 isBefore "also not ambiguous if string is passed"
        a1 = a1 isBeforeOrEqual "also not ambiguous if string is passed"
        a1 = a1 isAfter "also not ambiguous if string is passed"
        a1 = a1 isAfterOrEqual "also not ambiguous if string is passed"
        a1 = a1 isEqual "also not ambiguous if string is passed"

        a2 = a2 isBefore "also not ambiguous if string is passed"
        a2 = a2 isBeforeOrEqual "also not ambiguous if string is passed"
        a2 = a2 isAfter "also not ambiguous if string is passed"
        a2 = a2 isAfterOrEqual "also not ambiguous if string is passed"
        a2 = a2 isEqual "also not ambiguous if string is passed"
    }
}
