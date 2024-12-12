package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.notImplemented
import org.spekframework.spek2.Spek
import java.time.LocalDate
import java.time.ZonedDateTime
import java.time.chrono.ChronoLocalDate
import java.time.chrono.ChronoZonedDateTime

class ChronoZonedDateTimeExpectationsSpec : Spek({
    include(ChronoZonedDateTimeSpec)
    include(ChronoZonedDateTimeAsStringSpec)
}) {
    object ChronoZonedDateTimeSpec : ch.tutteli.atrium.specs.integration.ChronoZonedDateTimeExpectationsSpec(
        fun1<ChronoZonedDateTime<*>, ChronoZonedDateTime<*>>(Expect<ChronoZonedDateTime<*>>::toBeBefore),
        fun1<ChronoZonedDateTime<*>, ChronoZonedDateTime<*>>(Expect<ChronoZonedDateTime<*>>::toBeBeforeOrTheSamePointInTimeAs),
        fun1<ChronoZonedDateTime<*>, ChronoZonedDateTime<*>>(Expect<ChronoZonedDateTime<*>>::toBeAfter),
        fun1<ChronoZonedDateTime<*>, ChronoZonedDateTime<*>>(Expect<ChronoZonedDateTime<*>>::toBeAfterOrTheSamePointInTimeAs),
        fun1<ChronoZonedDateTime<*>, ChronoZonedDateTime<*>>(Expect<ChronoZonedDateTime<*>>::toBeTheSamePointInTimeAs)
    )

    object ChronoZonedDateTimeAsStringSpec :
        ch.tutteli.atrium.specs.integration.ChronoZonedDateTimeAsStringExpectationsSpec(
            fun1<ChronoZonedDateTime<*>, String>(Expect<ChronoZonedDateTime<*>>::toBeBefore),
            fun1<ChronoZonedDateTime<*>, String>(Expect<ChronoZonedDateTime<*>>::toBeBeforeOrTheSamePointInTimeAs),
            fun1<ChronoZonedDateTime<*>, String>(Expect<ChronoZonedDateTime<*>>::toBeAfter),
            fun1<ChronoZonedDateTime<*>, String>(Expect<ChronoZonedDateTime<*>>::toBeAfterOrTheSamePointInTimeAs),
            fun1<ChronoZonedDateTime<*>, String>(Expect<ChronoZonedDateTime<*>>::toBeTheSamePointInTimeAs)
        )

    @Suppress("unused")
    private fun ambiguityTest() {
        val chronoZonedDateTime: ChronoZonedDateTime<*> = notImplemented()
        var a1: Expect<ChronoZonedDateTime<ChronoLocalDate>> = notImplemented()
        var a2: Expect<ChronoZonedDateTime<LocalDate>> = notImplemented()
        var a3: Expect<ChronoZonedDateTime<*>> = notImplemented()
        var a4: Expect<ZonedDateTime> = notImplemented()


        a1 = a1.toBeBefore(ZonedDateTime.now())
        a1 = a1.toBeBeforeOrTheSamePointInTimeAs(ZonedDateTime.now())
        a1 = a1.toBeAfter(ZonedDateTime.now())
        a1 = a1.toBeAfterOrTheSamePointInTimeAs(ZonedDateTime.now())
        a1 = a1.toBeTheSamePointInTimeAs(ZonedDateTime.now())

        a2 = a2.toBeBefore(ZonedDateTime.now())
        a2 = a2.toBeBeforeOrTheSamePointInTimeAs(ZonedDateTime.now())
        a2 = a2.toBeAfter(ZonedDateTime.now())
        a2 = a2.toBeAfterOrTheSamePointInTimeAs(ZonedDateTime.now())
        a2 = a2.toBeTheSamePointInTimeAs(ZonedDateTime.now())

        a3 = a3.toBeBefore(ZonedDateTime.now())
        a3 = a3.toBeBeforeOrTheSamePointInTimeAs(ZonedDateTime.now())
        a3 = a3.toBeAfter(ZonedDateTime.now())
        a3 = a3.toBeAfterOrTheSamePointInTimeAs(ZonedDateTime.now())
        a3 = a3.toBeTheSamePointInTimeAs(ZonedDateTime.now())

        a4 = a4.toBeBefore(ZonedDateTime.now())
        a4 = a4.toBeBeforeOrTheSamePointInTimeAs(ZonedDateTime.now())
        a4 = a4.toBeAfter(ZonedDateTime.now())
        a4 = a4.toBeAfterOrTheSamePointInTimeAs(ZonedDateTime.now())
        a4 = a4.toBeTheSamePointInTimeAs(ZonedDateTime.now())


        a1 = a1.toBeBefore(chronoZonedDateTime)
        a1 = a1.toBeBeforeOrTheSamePointInTimeAs(chronoZonedDateTime)
        a1 = a1.toBeAfter(chronoZonedDateTime)
        a1 = a1.toBeAfterOrTheSamePointInTimeAs(chronoZonedDateTime)
        a1 = a1.toBeTheSamePointInTimeAs(chronoZonedDateTime)

        a2 = a2.toBeBefore(chronoZonedDateTime)
        a2 = a2.toBeBeforeOrTheSamePointInTimeAs(chronoZonedDateTime)
        a2 = a2.toBeAfter(chronoZonedDateTime)
        a2 = a2.toBeAfterOrTheSamePointInTimeAs(chronoZonedDateTime)
        a2 = a2.toBeTheSamePointInTimeAs(chronoZonedDateTime)

        a3 = a3.toBeBefore(chronoZonedDateTime)
        a3 = a3.toBeBeforeOrTheSamePointInTimeAs(chronoZonedDateTime)
        a3 = a3.toBeAfter(chronoZonedDateTime)
        a3 = a3.toBeAfterOrTheSamePointInTimeAs(chronoZonedDateTime)
        a3 = a3.toBeTheSamePointInTimeAs(chronoZonedDateTime)

        a4 = a4.toBeBefore(chronoZonedDateTime)
        a4 = a4.toBeBeforeOrTheSamePointInTimeAs(chronoZonedDateTime)
        a4 = a4.toBeAfter(chronoZonedDateTime)
        a4 = a4.toBeAfterOrTheSamePointInTimeAs(chronoZonedDateTime)
        a4 = a4.toBeTheSamePointInTimeAs(chronoZonedDateTime)

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
