package ch.tutteli.atrium.spec.creating

import ch.tutteli.atrium.assertions.Message
import ch.tutteli.atrium.contains
import ch.tutteli.atrium.creating.IAssertionPlantNullable
import ch.tutteli.atrium.creating.IAssertionPlantWithCommonFields
import ch.tutteli.atrium.message
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import ch.tutteli.atrium.spec.check
import ch.tutteli.atrium.spec.setUp
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

open class AssertionPlantNullableSpec(
    verbs: IAssertionVerbFactory,
    testeeFactory: (IAssertionPlantWithCommonFields.CommonFields<Int?>) -> IAssertionPlantNullable<Int?>
) : Spek({


    describe("subject is null") {
        val subject: Int? = null
        check("fun ${IAssertionPlantNullable<Int?>::isNull.name} does not throw an Exception") {
            val testee = testeeFactory(verbs.checkNullable(subject).commonFields)
            testee.isNull()
        }
    }

    describe("subject is not null") {
        val assertionVerb = "myAssertionVerb"
        val subject: Int? = 1
        val assertionChecker = verbs.checkNullable(subject).commonFields.assertionChecker
        val testee = testeeFactory(IAssertionPlantWithCommonFields.CommonFields(assertionVerb, subject, assertionChecker))
        val expectFun = verbs.checkException {
            testee.isNull()
        }
        setUp("throws an AssertionError when checking") {
            context("exception message") {
                val assertMessage = expectFun.toThrow<AssertionError>().message
                it("contains the ${testee.commonFields::assertionVerb.name}'") {
                    assertMessage.contains(assertionVerb)
                }
                it("contains the '${testee::subject.name}'") {
                    assertMessage.contains(subject.toString())
                }
                it("contains the '${Message::description.name}' of the assertion-message - which should be ${IAssertionPlantNullable.Description::class.simpleName}") {
                    assertMessage.contains(IAssertionPlantNullable.Description)
                }
                it("contains the '${Message::representation.name}' of the assertion-message") {
                    assertMessage.contains(RawString.NULL.string)
                }
            }
        }
    }
})
