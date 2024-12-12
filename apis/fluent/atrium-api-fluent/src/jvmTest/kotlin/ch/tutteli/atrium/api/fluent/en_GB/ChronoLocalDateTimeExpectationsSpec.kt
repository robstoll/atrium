package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.notImplemented
import org.spekframework.spek2.Spek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.chrono.ChronoLocalDate
import java.time.chrono.ChronoLocalDateTime

class ChronoLocalDateTimeExpectationsSpec : Spek({
    include(ChronoLocalDateTimeSpec)
    include(ChronoLocalDateTimeAsStringSpec)
}) {
    object ChronoLocalDateTimeSpec : ch.tutteli.atrium.specs.integration.ChronoLocalDateTimeExpectationsSpec(
        fun1<ChronoLocalDateTime<*>, ChronoLocalDateTime<*>>(Expect<ChronoLocalDateTime<*>>::toBeBefore),
        fun1<ChronoLocalDateTime<*>, ChronoLocalDateTime<*>>(Expect<ChronoLocalDateTime<*>>::toBeBeforeOrTheSamePointInTimeAs),
        fun1<ChronoLocalDateTime<*>, ChronoLocalDateTime<*>>(Expect<ChronoLocalDateTime<*>>::toBeAfter),
        fun1<ChronoLocalDateTime<*>, ChronoLocalDateTime<*>>(Expect<ChronoLocalDateTime<*>>::toBeAfterOrTheSamePointInTimeAs),
        fun1<ChronoLocalDateTime<*>, ChronoLocalDateTime<*>>(Expect<ChronoLocalDateTime<*>>::toBeTheSamePointInTimeAs)
    )

    object ChronoLocalDateTimeAsStringSpec :
        ch.tutteli.atrium.specs.integration.ChronoLocalDateTimeAsStringExpectationsSpec(
            fun1<ChronoLocalDateTime<*>, String>(Expect<ChronoLocalDateTime<*>>::toBeBefore),
            fun1<ChronoLocalDateTime<*>, String>(Expect<ChronoLocalDateTime<*>>::toBeBeforeOrTheSamePointInTimeAs),
            fun1<ChronoLocalDateTime<*>, String>(Expect<ChronoLocalDateTime<*>>::toBeAfter),
            fun1<ChronoLocalDateTime<*>, String>(Expect<ChronoLocalDateTime<*>>::toBeAfterOrTheSamePointInTimeAs),
            fun1<ChronoLocalDateTime<*>, String>(Expect<ChronoLocalDateTime<*>>::toBeTheSamePointInTimeAs)
        )

    @Suppress("unused")
    private fun ambiguityTest() {
        val chronoLocalDateTime: ChronoLocalDateTime<*> = notImplemented()
        var a1: Expect<ChronoLocalDateTime<ChronoLocalDate>> = notImplemented()
        var a2: Expect<ChronoLocalDateTime<LocalDate>> = notImplemented()
        var a3: Expect<ChronoLocalDateTime<*>> = notImplemented()
        var a4: Expect<LocalDateTime> = notImplemented()

        a1 = a1.toBeBefore(LocalDateTime.now())
        a1 = a1.toBeBeforeOrTheSamePointInTimeAs(LocalDateTime.now())
        a1 = a1.toBeAfter(LocalDateTime.now())
        a1 = a1.toBeAfterOrTheSamePointInTimeAs(LocalDateTime.now())
        a1 = a1.toBeTheSamePointInTimeAs(LocalDateTime.now())

        a2 = a2.toBeBefore(LocalDateTime.now())
        a2 = a2.toBeBeforeOrTheSamePointInTimeAs(LocalDateTime.now())
        a2 = a2.toBeAfter(LocalDateTime.now())
        a2 = a2.toBeAfterOrTheSamePointInTimeAs(LocalDateTime.now())
        a2 = a2.toBeTheSamePointInTimeAs(LocalDateTime.now())

        a3 = a3.toBeBefore(LocalDateTime.now())
        a3 = a3.toBeBeforeOrTheSamePointInTimeAs(LocalDateTime.now())
        a3 = a3.toBeAfter(LocalDateTime.now())
        a3 = a3.toBeAfterOrTheSamePointInTimeAs(LocalDateTime.now())
        a3 = a3.toBeTheSamePointInTimeAs(LocalDateTime.now())

        a4 = a4.toBeBefore(LocalDateTime.now())
        a4 = a4.toBeBeforeOrTheSamePointInTimeAs(LocalDateTime.now())
        a4 = a4.toBeAfter(LocalDateTime.now())
        a4 = a4.toBeAfterOrTheSamePointInTimeAs(LocalDateTime.now())
        a4 = a4.toBeTheSamePointInTimeAs(LocalDateTime.now())

        a1 = a1.toBeBefore(chronoLocalDateTime)
        a1 = a1.toBeBeforeOrTheSamePointInTimeAs(chronoLocalDateTime)
        a1 = a1.toBeAfter(chronoLocalDateTime)
        a1 = a1.toBeAfterOrTheSamePointInTimeAs(chronoLocalDateTime)
        a1 = a1.toBeTheSamePointInTimeAs(chronoLocalDateTime)

        a2 = a2.toBeBefore(chronoLocalDateTime)
        a2 = a2.toBeBeforeOrTheSamePointInTimeAs(chronoLocalDateTime)
        a2 = a2.toBeAfter(chronoLocalDateTime)
        a2 = a2.toBeAfterOrTheSamePointInTimeAs(chronoLocalDateTime)
        a2 = a2.toBeTheSamePointInTimeAs(chronoLocalDateTime)

        a3 = a3.toBeBefore(chronoLocalDateTime)
        a3 = a3.toBeBeforeOrTheSamePointInTimeAs(chronoLocalDateTime)
        a3 = a3.toBeAfter(chronoLocalDateTime)
        a3 = a3.toBeAfterOrTheSamePointInTimeAs(chronoLocalDateTime)
        a3 = a3.toBeTheSamePointInTimeAs(chronoLocalDateTime)

        a4 = a4.toBeBefore(chronoLocalDateTime)
        a4 = a4.toBeBeforeOrTheSamePointInTimeAs(chronoLocalDateTime)
        a4 = a4.toBeAfter(chronoLocalDateTime)
        a4 = a4.toBeAfterOrTheSamePointInTimeAs(chronoLocalDateTime)
        a4 = a4.toBeTheSamePointInTimeAs(chronoLocalDateTime)

        a1 = a1.toBeBefore("also not ambiguous if string is passed")
        a1 = a1.toBeBeforeOrTheSamePointInTimeAs("also not ambiguous if string is passed")
        a1 = a1.toBeAfter("also not ambiguous if string is passed")
        a1 = a1.toBeAfterOrTheSamePointInTimeAs("also not ambiguous if string is passed")
        a1 = a1.toBeTheSamePointInTimeAs("also not ambiguous if string is passed")

        a2 = a2.toBeBefore("also not ambiguous if string is passed")
        a2 = a2.toBeBeforeOrTheSamePointInTimeAs("also not ambiguous if string is passed")
        a2 = a2.toBeAfter("also not ambiguous if string is passed")
        a2 = a2.toBeAfterOrTheSamePointInTimeAs("also not ambiguous if string is passed")
        a2 = a2.toBeTheSamePointInTimeAs("also not ambiguous if string is passed")

        a3 = a3.toBeBefore("also not ambiguous if string is passed")
        a3 = a3.toBeBeforeOrTheSamePointInTimeAs("also not ambiguous if string is passed")
        a3 = a3.toBeAfter("also not ambiguous if string is passed")
        a3 = a3.toBeAfterOrTheSamePointInTimeAs("also not ambiguous if string is passed")
        a3 = a3.toBeTheSamePointInTimeAs("also not ambiguous if string is passed")

        a4 = a4.toBeBefore("also not ambiguous if string is passed")
        a4 = a4.toBeBeforeOrTheSamePointInTimeAs("also not ambiguous if string is passed")
        a4 = a4.toBeAfter("also not ambiguous if string is passed")
        a4 = a4.toBeAfterOrTheSamePointInTimeAs("also not ambiguous if string is passed")
        a4 = a4.toBeTheSamePointInTimeAs("also not ambiguous if string is passed")
    }
}
