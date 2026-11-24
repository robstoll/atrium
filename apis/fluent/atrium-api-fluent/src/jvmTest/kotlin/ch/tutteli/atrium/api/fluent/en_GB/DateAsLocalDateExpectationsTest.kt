package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.integration.AbstractDateAsLocalDateExpectationsTest
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Date
import kotlin.test.Test

class DateAsLocalDateExpectationsTest : AbstractDateAsLocalDateExpectationsTest(
    fun1(Expect<Date>::asLocalDate)
) {

    @Suppress("unused", "UNUSED_VALUE")
    @Test
    fun ambiguityTest() {
        var date: Expect<Date> = expect(SimpleDateFormat("yyyy-MM-dd").parse("1995-07-17"))

        val localDate: Expect<LocalDate> = date.asLocalDate()
        localDate.toEqual(LocalDate.parse("1995-07-17"))
        date = date.asLocalDate { toEqual(LocalDate.parse("1995-07-17")) }
    }
}
