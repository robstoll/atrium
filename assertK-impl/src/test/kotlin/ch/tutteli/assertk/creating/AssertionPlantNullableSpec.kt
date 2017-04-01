package ch.tutteli.assertk.creating

import ch.tutteli.assertk.check
import ch.tutteli.assertk.contains
import ch.tutteli.assertk.describe
import ch.tutteli.assertk.message
import ch.tutteli.assertk.reporting.RawString
import ch.tutteli.assertk.verbs.assert.assert
import ch.tutteli.assertk.verbs.expect.expect
import org.jetbrains.spek.api.Spek

class AssertionPlantNullableSpec : Spek({
    describe("subject is null") {
        val i: Int? = null
        group("isNull() does not throw an Exception") {
            check("using the plant directly") {
                val testee = AssertionPlantFactory.newNullable("assert", i)
                testee.isNull()
            }
            check("using `assert`") {
                assert(i).isNull()
            }
        }
    }

    describe("subject is not null") {
        val i: Int? = 1
        group("isNull()  throws an AssertionError") {
            check("using the plant directly") {
                val testee = AssertionPlantFactory.newNullable("assert", i)
                expect {
                    testee.isNull()
                }.toThrow<AssertionError>().and.message.contains("to be", RawString.NULL)
            }
            check("using `assert`") {
                expect {
                    assert(i).isNull()
                }.toThrow<AssertionError>().and.message.contains("to be", RawString.NULL)
            }
        }
    }
})
