package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.integration.DateAsLocalDateTimeExpectationsSpec
import ch.tutteli.atrium.specs.notImplemented
import java.util.Date

class DateAsLocalDateTimeExpectationsSpec : DateAsLocalDateTimeExpectationsSpec(
    Expect<Date>::asLocalDateTime
) {
    @Suppress("unused")
    private fun ambiguityTest() {
        var date: Expect<Date> = notImplemented()

        date.asLocalDateTime()
        date = date.asLocalDateTime {  }
    }
}
