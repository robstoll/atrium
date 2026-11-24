package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.Fun1
import ch.tutteli.atrium.testfactories.TestFactory
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*
import kotlin.test.Test

@Suppress("FunctionName")
abstract class AbstractDateAsLocalDateExpectationsTest(
    private val asLocalDateSpec: Fun1<Date, Expect<LocalDate>.() -> Unit>
) : ExpectationFunctionBaseTest() {

    @TestFactory
    fun asLocalDate() = testFactory(asLocalDateSpec) { asLocalDateFun ->
        it("transformation can be applied and a subsequent expectation made") {
            val formatter: DateFormat = SimpleDateFormat("yyyy-MM-dd")
            val date = formatter.parse("1995-07-17")
            expect(date).asLocalDateFun { toEqual(LocalDate.parse("1995-07-17")) }
        }
    }
}
