package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.notImplemented
import org.spekframework.spek2.Spek
import java.time.LocalDate
import java.time.chrono.ChronoLocalDate
import java.time.chrono.JapaneseDate

class ChronoLocalDateExpectationsSpec : Spek({
    include(ChronoLocalDateSpec)
    include(ChronoLocalDateAsStringSpec)
}) {
    object ChronoLocalDateSpec : ch.tutteli.atrium.specs.integration.ChronoLocalDateExpectationsSpec(
        fun1<ChronoLocalDate, ChronoLocalDate>(Expect<ChronoLocalDate>::toBeBefore),
        fun1<ChronoLocalDate, ChronoLocalDate>(Expect<ChronoLocalDate>::toBeBeforeOrTheSamePointInTimeAs),
        fun1<ChronoLocalDate, ChronoLocalDate>(Expect<ChronoLocalDate>::toBeAfter),
        fun1<ChronoLocalDate, ChronoLocalDate>(Expect<ChronoLocalDate>::toBeAfterOrTheSamePointInTimeAs),
        fun1<ChronoLocalDate, ChronoLocalDate>(Expect<ChronoLocalDate>::toBeTheSamePointInTimeAs)
    )

    object ChronoLocalDateAsStringSpec : ch.tutteli.atrium.specs.integration.ChronoLocalDateAsStringExpectationsSpec(
        fun1<ChronoLocalDate, String>(Expect<ChronoLocalDate>::toBeBefore),
        fun1<ChronoLocalDate, String>(Expect<ChronoLocalDate>::toBeBeforeOrTheSamePointInTimeAs),
        fun1<ChronoLocalDate, String>(Expect<ChronoLocalDate>::toBeAfter),
        fun1<ChronoLocalDate, String>(Expect<ChronoLocalDate>::toBeAfterOrTheSamePointInTimeAs),
        fun1<ChronoLocalDate, String>(Expect<ChronoLocalDate>::toBeTheSamePointInTimeAs)
    )

    @Suppress("unused")
    private fun ambiguityTest() {
        val chronoLocalDate: ChronoLocalDate = notImplemented()
        var a1: Expect<ChronoLocalDate> = notImplemented()
        var a2: Expect<LocalDate> = notImplemented()

        a1 = a1 toBeBefore LocalDate.now()
        a1 = a1 toBeBeforeOrTheSamePointInTimeAs LocalDate.now()
        a1 = a1 toBeAfter LocalDate.now()
        a1 = a1 toBeAfterOrTheSamePointInTimeAs LocalDate.now()
        a1 = a1 toBeTheSamePointInTimeAs LocalDate.now()

        a2 = a2 toBeBefore LocalDate.now()
        a2 = a2 toBeBeforeOrTheSamePointInTimeAs LocalDate.now()
        a2 = a2 toBeAfter LocalDate.now()
        a2 = a2 toBeAfterOrTheSamePointInTimeAs LocalDate.now()
        a2 = a2 toBeTheSamePointInTimeAs LocalDate.now()

        a1 = a1 toBeBefore JapaneseDate.now()
        a1 = a1 toBeBeforeOrTheSamePointInTimeAs JapaneseDate.now()
        a1 = a1 toBeAfter JapaneseDate.now()
        a1 = a1 toBeAfterOrTheSamePointInTimeAs JapaneseDate.now()
        a1 = a1 toBeTheSamePointInTimeAs JapaneseDate.now()

        a2 = a2 toBeBefore JapaneseDate.now()
        a2 = a2 toBeBeforeOrTheSamePointInTimeAs JapaneseDate.now()
        a2 = a2 toBeAfter JapaneseDate.now()
        a2 = a2 toBeAfterOrTheSamePointInTimeAs JapaneseDate.now()
        a2 = a2 toBeTheSamePointInTimeAs JapaneseDate.now()

        a1 = a1 toBeBefore chronoLocalDate
        a1 = a1 toBeBeforeOrTheSamePointInTimeAs chronoLocalDate
        a1 = a1 toBeAfter chronoLocalDate
        a1 = a1 toBeAfterOrTheSamePointInTimeAs chronoLocalDate
        a1 = a1 toBeTheSamePointInTimeAs chronoLocalDate

        a2 = a2 toBeBefore chronoLocalDate
        a2 = a2 toBeBeforeOrTheSamePointInTimeAs chronoLocalDate
        a2 = a2 toBeAfter chronoLocalDate
        a2 = a2 toBeAfterOrTheSamePointInTimeAs chronoLocalDate
        a2 = a2 toBeTheSamePointInTimeAs chronoLocalDate

        a1 = a1 toBeBefore "also not ambiguous if string is passed"
        a1 = a1 toBeBeforeOrTheSamePointInTimeAs "also not ambiguous if string is passed"
        a1 = a1 toBeAfter "also not ambiguous if string is passed"
        a1 = a1 toBeAfterOrTheSamePointInTimeAs "also not ambiguous if string is passed"
        a1 = a1 toBeTheSamePointInTimeAs "also not ambiguous if string is passed"

        a2 = a2 toBeBefore "also not ambiguous if string is passed"
        a2 = a2 toBeBeforeOrTheSamePointInTimeAs "also not ambiguous if string is passed"
        a2 = a2 toBeAfter "also not ambiguous if string is passed"
        a2 = a2 toBeAfterOrTheSamePointInTimeAs "also not ambiguous if string is passed"
        a2 = a2 toBeTheSamePointInTimeAs "also not ambiguous if string is passed"
    }
}
