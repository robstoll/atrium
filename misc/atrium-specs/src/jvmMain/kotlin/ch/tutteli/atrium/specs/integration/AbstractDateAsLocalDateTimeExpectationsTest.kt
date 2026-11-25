package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.Fun1
import ch.tutteli.atrium.testfactories.TestFactory
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*
import kotlin.test.Test

@Suppress("FunctionName")
abstract class AbstractDateAsLocalDateTimeExpectationsTest(
    private val asLocalDateTimeSpec: Fun1<Date, Expect<LocalDateTime>.() -> Unit>
) : ExpectationFunctionBaseTest() {

    @TestFactory
    fun asLocalDateTime() = testFactory(asLocalDateTimeSpec) { asLocalDateTimeFun ->
        it("transformation can be applied and a subsequent expectation made") {
            val formatter: DateFormat = SimpleDateFormat("yyyy-MM-dd")
            val date = formatter.parse("1995-07-17")
            expect(date).asLocalDateTimeFun { toEqual(LocalDateTime.parse("1995-07-17T00:00:00")) }
        }
        it("transformation can be applied where java.util.Date has a defined time and a subsequent expectation made") {
            val formatter: DateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            val date = formatter.parse("1995-07-17 13:12:11")
            expect(date).asLocalDateTimeFun { toEqual(LocalDateTime.parse("1995-07-17T13:12:11")) }
        }
    }
}
