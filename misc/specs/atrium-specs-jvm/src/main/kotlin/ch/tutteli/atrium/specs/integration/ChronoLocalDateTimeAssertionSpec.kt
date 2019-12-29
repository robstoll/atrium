package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.messageContains
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionDateTimeLikeAssertion
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.chrono.ChronoLocalDate
import java.time.chrono.ChronoLocalDateTime

abstract class ChronoLocalDateTimeAssertionSpec(
    isAfter: Fun1<LocalDateTime, LocalDateTime>,
    isBeforeOrEquals: Fun1<LocalDateTime, LocalDateTime>,
    describePrefix: String = "[Atrium] "
) : Spek({

    include(object : SubjectLessSpec<LocalDateTime>(
        describePrefix,
        isAfter.forSubjectLess(LocalDateTime.of(2019, 12, 24, 11, 15, 30)),
        isBeforeOrEquals.forSubjectLess(LocalDateTime.of(2019, 12, 24, 11, 15, 30))
    ){})

    val isAfterDescr = DescriptionDateTimeLikeAssertion.IS_AFTER.getDefault()
    val isBeforeOrEqualsDescr = DescriptionDateTimeLikeAssertion.IS_BEFORE_OR_EQUALS.getDefault()

    val fluent = expect(LocalDateTime.of(2019, 12, 24, 11, 15, 30))

    describe("$describePrefix contextSubject is 2019-12-24T11:15:30") {
        describe("${isAfter.name} ...") {
            val isAfterFun = isAfter.lambda

            it("2019-12-24T12:15:30 throws an AssertionError") {
                expect {
                    fluent.isAfterFun(LocalDateTime.of(2019,12,24,12,15,30))
                }.toThrow<AssertionError> { messageContains("$isAfterDescr: 2019-12-24T12:15:30") }
            }
            it("2019-12-24T11:15:30 throws an AssertionError") {
                expect {
                    fluent.isAfterFun(LocalDateTime.of(2019,12,24,11,15,30))
                }.toThrow<AssertionError> { messageContains("$isAfterDescr: 2019-12-24T11:15:30") }
            }
            it("2019-12-24T:10:15:30 does not throw") {
                fluent.isAfterFun(LocalDateTime.of(2019,12,24,10,15,30))
            }
        }
        describe("${isBeforeOrEquals.name} ...") {
            val isBeforeOrEqualsFun = isBeforeOrEquals.lambda

            it("2019-12-24T12:15:30 does not throw") {
                fluent.isBeforeOrEqualsFun(LocalDateTime.of(2019,12,24,12,15,30))
            }
            it("2019-12-24T11:15:30 does not throw") {
                fluent.isBeforeOrEqualsFun(LocalDateTime.of(2019,12,24,11,15,30))
            }
            it("2019-12-24T:10:15:30 throws an AssertionError") {
                expect {
                    fluent.isBeforeOrEqualsFun(LocalDateTime.of(2019,12,24,10,15,30))
                }.toThrow<AssertionError> { messageContains("$isBeforeOrEqualsDescr: 2019-12-24T10:15:30")}
            }
        }
    }
})
