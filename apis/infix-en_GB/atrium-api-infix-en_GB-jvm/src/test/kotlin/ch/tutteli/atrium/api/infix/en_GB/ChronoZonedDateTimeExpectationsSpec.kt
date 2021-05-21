package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.notImplemented
import org.spekframework.spek2.Spek
import java.time.LocalDate
import java.time.ZonedDateTime
import java.time.chrono.ChronoLocalDate
import java.time.chrono.ChronoZonedDateTime

class ChronoZonedDateTimeExpectationsSpec : Spek({
    include(ChronoZonedDateTimeExpectationsSpec)
    include(ChronoZonedDateTimeAsStringExpectationsSpec)
}) {
    object ChronoZonedDateTimeExpectationsSpec :
        ch.tutteli.atrium.specs.integration.ChronoZonedDateTimeExpectationsSpec(
            fun1<ChronoZonedDateTime<*>, ChronoZonedDateTime<*>>(Expect<ChronoZonedDateTime<*>>::toBeBefore),
            fun1<ChronoZonedDateTime<*>, ChronoZonedDateTime<*>>(Expect<ChronoZonedDateTime<*>>::toBeBeforeOrTheSamePointInTimeAs),
            fun1<ChronoZonedDateTime<*>, ChronoZonedDateTime<*>>(Expect<ChronoZonedDateTime<*>>::toBeAfter),
            fun1<ChronoZonedDateTime<*>, ChronoZonedDateTime<*>>(Expect<ChronoZonedDateTime<*>>::toBeAfterOrTheSamePointInTimeAs),
            fun1<ChronoZonedDateTime<*>, ChronoZonedDateTime<*>>(Expect<ChronoZonedDateTime<*>>::toBeTheSamePointInTimeAs)
        )

    object ChronoZonedDateTimeAsStringExpectationsSpec :
        ch.tutteli.atrium.specs.integration.ChronoZonedDateTimeAsStringExpectationsSpec(
            fun1<ChronoZonedDateTime<*>, String>(Expect<ChronoZonedDateTime<*>>::toBeBefore),
            fun1<ChronoZonedDateTime<*>, String>(Expect<ChronoZonedDateTime<*>>::toBeBeforeOrTheSamePointInTimeAs),
            fun1<ChronoZonedDateTime<*>, String>(Expect<ChronoZonedDateTime<*>>::toBeAfter),
            fun1<ChronoZonedDateTime<*>, String>(Expect<ChronoZonedDateTime<*>>::toBeAfterOrTheSamePointInTimeAs),
            fun1<ChronoZonedDateTime<*>, String>(Expect<ChronoZonedDateTime<*>>::toBeTheSamePointInTimeAs)
        )

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        val chronoZonedDateTime: ChronoZonedDateTime<*> = notImplemented()
        var a1: Expect<ChronoZonedDateTime<ChronoLocalDate>> = notImplemented()
        var a2: Expect<ChronoZonedDateTime<LocalDate>> = notImplemented()
        var a3: Expect<ChronoZonedDateTime<*>> = notImplemented()
        var a4: Expect<ZonedDateTime> = notImplemented()


        a1 = a1 isBefore ZonedDateTime.now()
        a1 = a1 isBeforeOrEqual ZonedDateTime.now()
        a1 = a1 isAfter ZonedDateTime.now()
        a1 = a1 isAfterOrEqual ZonedDateTime.now()
        a1 = a1 isEqual ZonedDateTime.now()

        a2 = a2 isBefore ZonedDateTime.now()
        a2 = a2 isBeforeOrEqual ZonedDateTime.now()
        a2 = a2 isAfter ZonedDateTime.now()
        a2 = a2 isAfterOrEqual ZonedDateTime.now()
        a2 = a2 isEqual ZonedDateTime.now()

        a3 = a3 isBefore ZonedDateTime.now()
        a3 = a3 isBeforeOrEqual ZonedDateTime.now()
        a3 = a3 isAfter ZonedDateTime.now()
        a3 = a3 isAfterOrEqual ZonedDateTime.now()
        a3 = a3 isEqual ZonedDateTime.now()

        a4 = a4 isBefore ZonedDateTime.now()
        a4 = a4 isBeforeOrEqual ZonedDateTime.now()
        a4 = a4 isAfter ZonedDateTime.now()
        a4 = a4 isAfterOrEqual ZonedDateTime.now()
        a4 = a4 isEqual ZonedDateTime.now()


        a1 = a1 isBefore chronoZonedDateTime
        a1 = a1 isBeforeOrEqual chronoZonedDateTime
        a1 = a1 isAfter chronoZonedDateTime
        a1 = a1 isAfterOrEqual chronoZonedDateTime
        a1 = a1 isEqual chronoZonedDateTime

        a2 = a2 isBefore chronoZonedDateTime
        a2 = a2 isBeforeOrEqual chronoZonedDateTime
        a2 = a2 isAfter chronoZonedDateTime
        a2 = a2 isAfterOrEqual chronoZonedDateTime
        a2 = a2 isEqual chronoZonedDateTime

        a3 = a3 isBefore chronoZonedDateTime
        a3 = a3 isBeforeOrEqual chronoZonedDateTime
        a3 = a3 isAfter chronoZonedDateTime
        a3 = a3 isAfterOrEqual chronoZonedDateTime
        a3 = a3 isEqual chronoZonedDateTime

        a4 = a4 isBefore chronoZonedDateTime
        a4 = a4 isBeforeOrEqual chronoZonedDateTime
        a4 = a4 isAfter chronoZonedDateTime
        a4 = a4 isAfterOrEqual chronoZonedDateTime
        a4 = a4 isEqual chronoZonedDateTime

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

        a3 = a3 isBefore "also not ambiguous if string is passed"
        a3 = a3 isBeforeOrEqual "also not ambiguous if string is passed"
        a3 = a3 isAfter "also not ambiguous if string is passed"
        a3 = a3 isAfterOrEqual "also not ambiguous if string is passed"
        a3 = a3 isEqual "also not ambiguous if string is passed"

        a4 = a4 isBefore "also not ambiguous if string is passed"
        a4 = a4 isBeforeOrEqual "also not ambiguous if string is passed"
        a4 = a4 isAfter "also not ambiguous if string is passed"
        a4 = a4 isAfterOrEqual "also not ambiguous if string is passed"
        a4 = a4 isEqual "also not ambiguous if string is passed"
    }
}
