package ch.tutteli.atrium.test.creating

import ch.tutteli.atrium.contains
import ch.tutteli.atrium.creating.IAssertionPlantNullable
import ch.tutteli.atrium.creating.IAssertionPlantWithCommonFields
import ch.tutteli.atrium.message
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.test.IAssertionVerbFactory
import ch.tutteli.atrium.test.check
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe

open class AssertionPlantNullableSpec(
    val verbs: IAssertionVerbFactory,
    testeeFactory: (IAssertionPlantWithCommonFields.CommonFields<Int?>) -> IAssertionPlantNullable<Int?>
) : Spek({
    describe("subject is null") {
        val subject: Int? = null
        check("isNull() does not throw an Exception") {
            val testee = testeeFactory(verbs.checkNullable(subject).commonFields)
            testee.isNull()
        }
    }

    describe("subject is not null") {
        val subject: Int? = 1
        check("isNull()  throws an AssertionError") {
            val testee = testeeFactory(verbs.checkNullable(subject).commonFields)
            verbs.checkException {
                testee.isNull()
            }.toThrow<AssertionError>().and.message.contains("to be", RawString.NULL)
        }
    }
})
