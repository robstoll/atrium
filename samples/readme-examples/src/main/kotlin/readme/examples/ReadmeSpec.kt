package readme.examples

//snippet-import-start
import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
//snippet-import-end
import org.spekframework.spek2.Spek

/**
 * The output of the tests written here are placed into the README.
 * The generation is done this project is built, to trigger it manually, you have to use:
 * ```
 * ./gr :readme-examples:build
 * ```
 *
 * Adding new examples is simply adding a new test and referencing it in the README.
 * Adding a new snippet requires that you:
 * - set the markers //snippet-xy-start and //snippet-xy-end
 * - create a test with the corresponding name, leaving the body empty: e.g. test("snippet-xy") {}
 */
class ReadmeSpec : Spek({

    test("ex-first") {
        //snippet-import-insert

        val x = 10
        expect(x).toBe(9)
    }
    test("snippet-import") {}

    test("ex-single") {
        // two single assertions, only first evaluated
        expect(4 + 6).isLessThan(5).isGreaterThan(10)
    }

    test("ex-group") {
        // assertion group with two assertions, both evaluated
        expect(4 + 6) {
            isLessThan(5)
            isGreaterThan(10)
        }
    }

    test("ex-toThrow1") {
        expect {
            //this block does something but eventually...
            throw IllegalArgumentException("name is empty")
        }.toThrow<IllegalStateException>()
    }

    test("ex-toThrow2") {
        expect {
            throw IllegalArgumentException()
        }.toThrow<IllegalArgumentException>().message.startsWith("firstName")
    }

    test("ex-toThrow3") {
        expect {
            throw IllegalArgumentException()
        }.toThrow<IllegalArgumentException> {
            message { startsWith("firstName") }
        }
    }

    test("ex-notToThrow") {
        expect {
            //this block does something but eventually...
            throw IllegalArgumentException("name is empty", RuntimeException("a cause"))
        }.notToThrow()
    }

    //snippet-Person-start
    data class Person(val firstName: String, val lastName: String, val isStudent: Boolean) {
        fun fullName() = "$firstName $lastName"
        fun nickname(includeLastName: Boolean) = when (includeLastName) {
            false -> "Mr. $firstName"
            true -> "$firstName aka. $lastName"
        }
    }

    val myPerson = Person("Robert", "Stoll", false)
    //snippet-Person-end

    test("snippet-Person") {}

    test("ex-property-methods-single") {
        //snippet-Person-insert
        expect(myPerson)
            .feature({ f(it::isStudent) }) { toBe(true) } // fails, subject still Person afterwards
            .feature { f(it::fullName) }                  // not evaluated anymore, subject String afterwards
            .startsWith("rob")                            // not evaluated anymore
    }

    test("ex-property-methods-group") {
        expect(myPerson) { // forms an assertion group block

            feature({ f(it::firstName) }) { // forms an assertion group block
                startsWith("Pe")            // fails
                endsWith("er")              // is evaluated nonetheless
            }                               // fails as a whole

            feature { f(it::lastName) }.toBe("Dummy") // still evaluated, as it is in outer assertion group block
        }
    }
})
