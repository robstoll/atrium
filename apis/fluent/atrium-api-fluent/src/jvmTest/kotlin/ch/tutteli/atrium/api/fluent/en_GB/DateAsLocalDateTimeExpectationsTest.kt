package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.integration.AbstractDateAsLocalDateTimeExpectationsTest
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.Date
import kotlin.test.Test

class DateAsLocalDateTimeExpectationsTest : AbstractDateAsLocalDateTimeExpectationsTest(
    fun1(Expect<Date>::asLocalDateTime)
) {

    @Suppress("unused", "UNUSED_VALUE")
    @Test
    fun ambiguityTest() {
        var date: Expect<Date> = expect(SimpleDateFormat("yyyy-MM-dd").parse("1995-07-17"))

        val localDateTime: Expect<LocalDateTime> = date.asLocalDateTime()
        localDateTime.toEqual(LocalDateTime.parse("1995-07-17T00:00:00"))
        date = date.asLocalDateTime { toEqual(LocalDateTime.parse("1995-07-17T00:00:00")) }
    }
}
