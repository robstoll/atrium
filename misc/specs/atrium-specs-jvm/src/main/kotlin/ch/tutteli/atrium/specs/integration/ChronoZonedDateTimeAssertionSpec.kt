package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.messageContains
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionDateTimeLikeAssertion
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.time.ZoneId
import java.time.ZonedDateTime

const val SPEK_ZONE_ID = "Europe/Zurich"

abstract class ChronoZonedDateTimeAssertionSpec(
    isAfter: Fun1<ZonedDateTime, ZonedDateTime>,
    isBeforeOrEquals: Fun1<ZonedDateTime, ZonedDateTime>,
    isBefore: Fun1<ZonedDateTime, ZonedDateTime>,
    describePrefix: String = "[Atrium] "
) : Spek({

    include(object : SubjectLessSpec<ZonedDateTime>(
        describePrefix,
        isAfter.forSubjectLess(ZonedDateTime.of(2019, 12, 24,11,15,30, 210, ZoneId.of(SPEK_ZONE_ID))),
        isBeforeOrEquals.forSubjectLess(ZonedDateTime.of(2019, 12, 24,11,15,30, 210, ZoneId.of(SPEK_ZONE_ID))),
        isBefore.forSubjectLess(ZonedDateTime.of(2019, 12, 24,11,15,30, 210, ZoneId.of(SPEK_ZONE_ID)))
    ){})

    val isAfterDescr = DescriptionDateTimeLikeAssertion.IS_AFTER.getDefault()
    val isBeforeOrEqualsDescr = DescriptionDateTimeLikeAssertion.IS_BEFORE_OR_EQUALS.getDefault()
    val isBeforeDescr = DescriptionDateTimeLikeAssertion.IS_BEFORE.getDefault()

    val fluent = expect(ZonedDateTime.of(2019, 12, 24,11,15,30, 210, ZoneId.of(SPEK_ZONE_ID)))

    describe("$describePrefix contextSubject 2019-12-24T11:15:30.210+01:00") {
        describe("${isAfter.name} ...") {
            val isAfterFun = isAfter.lambda

            it("2019-12-24T12:15:30.210+01:00 throws an AssertionError") {
                expect {
                    fluent.isAfterFun(ZonedDateTime.of(2019, 12, 24,12,15,30, 210, ZoneId.of(SPEK_ZONE_ID)))
                }.toThrow<AssertionError> { messageContains("$isAfterDescr: 2019-12-24T12:15:30.000000210+01:00[Europe/Zurich]") }
            }
            it("2019-12-24T11:15:30.210+01:00 throws an AssertionError") {
                expect {
                    fluent.isAfterFun(ZonedDateTime.of(2019, 12, 24,11,15,30, 210, ZoneId.of(SPEK_ZONE_ID)))
                }.toThrow<AssertionError> { messageContains("$isAfterDescr: 2019-12-24T11:15:30.000000210+01:00[Europe/Zurich]") }
            }
            it("2019-12-24T10:15:30.210+01:00 does not throw") {
                fluent.isAfterFun(ZonedDateTime.of(2019, 12, 24,10,15,30, 210, ZoneId.of(SPEK_ZONE_ID)))
            }
        }
        describe("${isBeforeOrEquals.name} ...") {
            val isBeforeOrEqualsFun = isBeforeOrEquals.lambda

            it("2019-12-24T12:15:30.210+01:00 does not throw") {
                fluent.isBeforeOrEqualsFun(ZonedDateTime.of(2019, 12, 24,12,15,30, 210, ZoneId.of(SPEK_ZONE_ID)))
            }
            it("2019-12-24T11:15:30.210+01:00 does not throw") {
                fluent.isBeforeOrEqualsFun(ZonedDateTime.of(2019, 12, 24,11,15,30, 210, ZoneId.of(SPEK_ZONE_ID)))
            }
            it("2019-12-24T10:15:30.210+01:00 throws an AssertionError") {
                expect {
                    fluent.isBeforeOrEqualsFun(ZonedDateTime.of(2019, 12, 24,10,15,30, 210, ZoneId.of(SPEK_ZONE_ID)))
                }.toThrow<AssertionError> { messageContains("$isBeforeOrEqualsDescr: 2019-12-24T10:15:30.000000210+01:00[Europe/Zurich]")}
            }
        }
        describe("${isBefore.name} ...") {
            val isBeforeFun = isBefore.lambda

            it("2019-12-24T12:15:30.210+01:00 does not throw") {
                fluent.isBeforeFun(ZonedDateTime.of(2019, 12, 24,12,15,30, 210, ZoneId.of(SPEK_ZONE_ID)))
            }
            it("2019-12-24T11:15:30.210+01:00 does not throw") {
                expect{
                    fluent.isBeforeFun(ZonedDateTime.of(2019, 12, 24,11,15,30, 210, ZoneId.of(SPEK_ZONE_ID)))
                }.toThrow<AssertionError> { messageContains("$isBeforeDescr: 2019-12-24T11:15:30.000000210+01:00[Europe/Zurich]")}
            }
            it("2019-12-24T10:15:30.210+01:00 throws an AssertionError") {
                expect {
                    fluent.isBeforeFun(ZonedDateTime.of(2019, 12, 24,10,15,30, 210, ZoneId.of(SPEK_ZONE_ID)))
                }.toThrow<AssertionError> { messageContains("$isBeforeDescr: 2019-12-24T10:15:30.000000210+01:00[Europe/Zurich]")}
            }
        }
    }
})
