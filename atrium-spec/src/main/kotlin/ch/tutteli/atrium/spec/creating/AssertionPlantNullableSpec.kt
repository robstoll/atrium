package ch.tutteli.atrium.spec.creating

import ch.tutteli.atrium.api.cc.en_UK.contains
import ch.tutteli.atrium.api.cc.en_UK.containsDefaultTranslationOf
import ch.tutteli.atrium.api.cc.en_UK.message
import ch.tutteli.atrium.api.cc.en_UK.toThrow
import ch.tutteli.atrium.assertions.IBasicAssertion
import ch.tutteli.atrium.creating.IAssertionPlantNullable
import ch.tutteli.atrium.creating.IAssertionPlantWithCommonFields
import ch.tutteli.atrium.creating.IReportingAssertionPlantNullable
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.spec.*
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it

abstract class AssertionPlantNullableSpec(
    verbs: IAssertionVerbFactory,
    testeeFactory: (IAssertionPlantWithCommonFields.CommonFields<Int?>) -> IReportingAssertionPlantNullable<Int?>,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun prefixedDescribe(description: String, body: SpecBody.() -> Unit) {
        prefixedDescribe(describePrefix, description, body)
    }

    prefixedDescribe("fun ${IReportingAssertionPlantNullable<Int?>::isNull.name}") {

        context("subject is null") {
            val subject: Int? = null
            val commonFields = (verbs.checkNullable(subject) as IReportingAssertionPlantNullable<Int?>).commonFields
            check("fun ${IReportingAssertionPlantNullable<Int?>::isNull.name} does not throw an Exception") {
                val testee = testeeFactory(commonFields)
                testee.isNull()
            }
        }

        context("subject is not null") {
            val assertionVerb = AssertionVerb.VERB
            val subject: Int? = 1
            val assertionChecker = (verbs.checkNullable(subject) as IReportingAssertionPlantNullable<Int?>).commonFields.assertionChecker
            val testee = testeeFactory(IAssertionPlantWithCommonFields.CommonFields(assertionVerb, subject, assertionChecker, RawString.NULL))
            val expectFun = verbs.checkException {
                testee.isNull()
            }
            setUp("throws an AssertionError when checking") {
                context("exception message") {
                    it("contains the ${testee.commonFields::assertionVerb.name}'") {
                        expectFun.toThrow<AssertionError> { message { containsDefaultTranslationOf(assertionVerb) } }
                    }
                    it("contains the '${testee::subject.name}'") {
                        expectFun.toThrow<AssertionError> { message { contains(subject.toString()) } }
                    }
                    it("contains the '${IBasicAssertion::description.name}' of the assertion-message - which should be ${IAssertionPlantNullable.AssertionDescription::class.simpleName}") {
                        expectFun.toThrow<AssertionError> {
                            message { containsDefaultTranslationOf(IAssertionPlantNullable.AssertionDescription) }
                        }
                    }
                    it("contains the '${IBasicAssertion::expected.name}' of the assertion-message") {
                        expectFun.toThrow<AssertionError> { message { contains(RawString.NULL.string) } }
                    }
                }
            }
        }
    }
})
