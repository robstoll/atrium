package ch.tutteli.atrium

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe

object BooleanAssertionSpec : Spek( {
    describe("subject is `true`"){
        val fluent = assert(true)
        test("${fluent::isTrue.name} does not throw"){
            fluent.isTrue()
        }
        test("${fluent::isFalse.name} throws an AssertionError containing ${DescriptionAnyAssertion::class.simpleName}.${DescriptionAnyAssertion.TO_BE} and `: false`"){
            expect {
                fluent.isFalse()
            }.toThrow<AssertionError>().and.message.containsDefaultTranslationOf(DescriptionAnyAssertion.TO_BE).and.contains(": false")
        }
    }

    describe("subject is `false`"){
        val fluent = assert(false)

        test("${fluent::isTrue.name} throws an AssertionError containing ${DescriptionAnyAssertion::class.simpleName}.${DescriptionAnyAssertion.TO_BE} and `: true`"){
            expect {
                fluent.isTrue()
            }.toThrow<AssertionError>().and.message.containsDefaultTranslationOf(DescriptionAnyAssertion.TO_BE).and.contains(": true")
        }
        test("${fluent::isFalse.name} does not throw"){
            fluent.isFalse()
        }
    }
})
