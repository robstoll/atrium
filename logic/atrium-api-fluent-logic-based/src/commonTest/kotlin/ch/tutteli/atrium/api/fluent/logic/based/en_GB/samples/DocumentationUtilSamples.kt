package ch.tutteli.atrium.api.fluent.logic.based.en_GB.samples

import ch.tutteli.atrium.api.fluent.logic.based.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test

class DocumentationUtilSamples {

    @Test
    fun because() {

        expect("filename")
            .because("? is not allowed in file names on Windows") {
                notToContain("?")
            }

        data class Person(val age: Int)
        val customers = listOf(Person(21))

        expect(customers).toHaveElementsAndAll {
            because("the legal age of maturity in Switzerland is 18") {
                feature { f(it::age) }.toBeGreaterThanOrEqualTo(18)
            }
        }
    }
}
