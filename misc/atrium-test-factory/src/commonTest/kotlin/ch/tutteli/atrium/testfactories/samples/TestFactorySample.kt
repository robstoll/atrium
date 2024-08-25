package ch.tutteli.atrium.testfactories.samples

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ExpectGrouping
import ch.tutteli.atrium.creating.ExpectationVerbs
import ch.tutteli.atrium.testfactories.TestFactoryBuilder
import ch.tutteli.atrium.testfactories.testFactoryTemplate
import com.example.expect

class TestFactorySample {

    fun testFactoryTemplate() {
        // usually define as object with name instead of anonymous object, only anonymous here because we are in
        // a function
        val myVerbs = object : ExpectationVerbs {
            override fun <T> expect(subject: T): Expect<T> = com.example.expect(subject)

            override fun <T> expect(subject: T, expectationCreator: Expect<T>.() -> Unit): Expect<T> =
                com.example.expect(subject, expectationCreator)

            override fun expectGrouped(
                description: String?,
                groupingActions: ExpectGrouping.() -> Unit
            ): ExpectGrouping = com.example.expectGrouped(description ?: "my expectations", groupingActions)

            override fun <T> expectInExpectGrouped(expectGrouping: ExpectGrouping, subject: T): Expect<T> {
                // extension functions cannot be called with fully qualified name, i.e we have defined an
                // `import com.example.expect` where `expect` is `fun ExpectGrouping.expect(...)`
                return expectGrouping.expect(subject)
            }

            override fun <T> expectInExpectGrouped(
                expectGrouping: ExpectGrouping,
                subject: T,
                expectationCreator: Expect<T>.() -> Unit
            ): Expect<T> = expectGrouping.expect(subject, expectationCreator)
        }

        fun testFactory(setup: TestFactoryBuilder.() -> Unit) = testFactoryTemplate(setup, myVerbs)
    }

    fun testFactoryTemplateVarag() {
        // usually define as object with name instead of anonymous object, only anonymous here because we are in
        // a function
        val myVerbs = object : ExpectationVerbs {
            override fun <T> expect(subject: T): Expect<T> = com.example.expect(subject)

            override fun <T> expect(subject: T, expectationCreator: Expect<T>.() -> Unit): Expect<T> =
                com.example.expect(subject, expectationCreator)

            override fun expectGrouped(
                description: String?,
                groupingActions: ExpectGrouping.() -> Unit
            ): ExpectGrouping = com.example.expectGrouped(description ?: "my expectations", groupingActions)

            override fun <T> expectInExpectGrouped(expectGrouping: ExpectGrouping, subject: T): Expect<T> {
                // extension functions cannot be called with fully qualified name, i.e we have defined an
                // `import com.example.expect` where `expect` is `fun ExpectGrouping.expect(...)`
                return expectGrouping.expect(subject)
            }

            override fun <T> expectInExpectGrouped(
                expectGrouping: ExpectGrouping,
                subject: T,
                expectationCreator: Expect<T>.() -> Unit
            ): Expect<T> = expectGrouping.expect(subject, expectationCreator)
        }

        fun testFactory(setup: TestFactoryBuilder.() -> Unit, vararg otherSetups: TestFactoryBuilder.() -> Unit) =
            testFactoryTemplate(setup, otherSetups, myVerbs)
    }
}
