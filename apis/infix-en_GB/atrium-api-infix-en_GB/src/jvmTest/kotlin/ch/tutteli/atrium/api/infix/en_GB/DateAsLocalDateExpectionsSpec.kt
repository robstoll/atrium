package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.fluent.en_GB.asLocalDate
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.integration.DateAsLocalDateExpectationsSpec
import ch.tutteli.atrium.specs.notImplemented
import java.util.Date

class DateAsLocalDateExpectionsSpec : DateAsLocalDateExpectationsSpec(
    Expect<Date>::asLocalDate
) {
    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var date: Expect<Date> = notImplemented()

        date asLocalDate o
        date = date asLocalDate {  }
    }
}
