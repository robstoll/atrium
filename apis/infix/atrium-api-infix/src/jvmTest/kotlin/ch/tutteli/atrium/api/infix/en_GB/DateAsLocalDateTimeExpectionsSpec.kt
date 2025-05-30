package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.integration.DateAsLocalDateTimeExpectationsSpec
import ch.tutteli.atrium.specs.notImplemented
import java.util.Date

class DateAsLocalDateTimeExpectationsSpec : DateAsLocalDateTimeExpectationsSpec(
    Expect<Date>::asLocalDateTime
){
    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var date: Expect<Date> = notImplemented()

        date asLocalDateTime o
        date = date asLocalDateTime {  }
    }
}
