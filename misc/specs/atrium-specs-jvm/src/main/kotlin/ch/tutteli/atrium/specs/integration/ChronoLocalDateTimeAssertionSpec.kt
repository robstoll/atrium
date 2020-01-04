package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.messageContains
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionDateTimeLikeAssertion
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.chrono.JapaneseDate

abstract class ChronoLocalDateTimeAssertionSpec(
    isBefore: Fun1<LocalDateTime, LocalDateTime>,
    isBeforeOrEquals: Fun1<LocalDateTime, LocalDateTime>,
    isAfter: Fun1<LocalDateTime, LocalDateTime>,
    describePrefix: String = "[Atrium] "
) : Spek({

    val ten = LocalDateTime.of(2019, 12, 24, 10, 15, 30)
    val eleven = LocalDateTime.of(2019, 12, 24, 11, 15, 30)
    val twelve = LocalDateTime.of(2019, 12, 24, 12, 15, 30)

    include(object : SubjectLessSpec<LocalDateTime>(
        describePrefix,
        isBefore.forSubjectLess(eleven),
        isBeforeOrEquals.forSubjectLess(eleven),
        isAfter.forSubjectLess(eleven)
    ) {})

    val isBeforeDescr = DescriptionDateTimeLikeAssertion.IS_BEFORE.getDefault()
    val isBeforeOrEqualsDescr = DescriptionDateTimeLikeAssertion.IS_BEFORE_OR_EQUALS.getDefault()
    val isAfterDescr = DescriptionDateTimeLikeAssertion.IS_AFTER.getDefault()

    listOf(
        eleven
        //TODO #289 add the following case, adjust isBefore etc. to Fun1<ChronoLocalDateTime<*>, ChronoLocalDateTime<*>>
//        JapaneseDate.of(2019, 12, 23).atTime(LocalTime.of(11, 15, 30))
    ).forEach { subject ->
        val fluent = expect(subject)

        describe("$describePrefix subject is $subject") {
            describe("${isBefore.name} ...") {
                val isBeforeFun = isBefore.lambda

                it("$ten throws an AssertionError") {
                    expect {
                        fluent.isBeforeFun(ten)
                    }.toThrow<AssertionError> { messageContains("$isBeforeDescr: $ten") }
                }
                it("$eleven throws an AssertionError") {
                    expect {
                        fluent.isBeforeFun(eleven)
                    }.toThrow<AssertionError> { messageContains("$isBeforeDescr: $eleven") }
                }
                it("$twelve does not throw") {
                    fluent.isBeforeFun(twelve)
                }
            }
            describe("${isBeforeOrEquals.name} ...") {
                val isBeforeOrEqualsFun = isBeforeOrEquals.lambda

                it("$ten throws an AssertionError") {
                    expect {
                        fluent.isBeforeOrEqualsFun(ten)
                    }.toThrow<AssertionError> { messageContains("$isBeforeOrEqualsDescr: $ten") }
                }
                it("$eleven does not throw") {
                    fluent.isBeforeOrEqualsFun(eleven)
                }
                it("$twelve does not throw") {
                    fluent.isBeforeOrEqualsFun(twelve)
                }
            }

            describe("${isAfter.name} ...") {
                val isAfterFun = isAfter.lambda

                it("$ten does not throw") {
                    fluent.isAfterFun(ten)
                }
                it("$eleven throws an AssertionError") {
                    expect {
                        fluent.isAfterFun(eleven)
                    }.toThrow<AssertionError> { messageContains("$isAfterDescr: $eleven") }
                }
                it("$twelve throws an AssertionError") {
                    expect {
                        fluent.isAfterFun(twelve)
                    }.toThrow<AssertionError> { messageContains("$isAfterDescr: $twelve") }
                }
            }
        }
    }
})
