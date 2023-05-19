
package ch.tutteli.atrium.api.infix.en_GB.samples

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.atrium.creating.Expect
import kotlin.test.Test

class DocumentationUtilSamples {

    @Test
    fun becauseOf() {
        data class Person(val age: Int)
        val customers = listOf(Person(21))

        expect("filename") because of("? is not allowed in file names on Windows") {
            it notToContain "?"
        }

        expect(customers) toHaveElementsAndAll (fun Expect<Person>.() {
            it because of("the legal age of maturity in Switzerland is 18") {
                feature { f(it::age) } toBeGreaterThanOrEqualTo 18
            }
        })
    }
}
