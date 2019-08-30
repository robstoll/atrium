package readme.examples

//snippet-import-start
import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
//snippet-import-end
import ch.tutteli.atrium.creating.Expect
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

    //@formatter:off
    test("ex-property-methods-group") {
        expect(myPerson) { // forms an assertion group block

            feature({ f(it::firstName) }) { // forms an assertion group block
                startsWith("Pe")            // fails
                endsWith("er")              // is evaluated nonetheless
            }                               // fails as a whole

            feature { f(it::lastName) }.toBe("Dummy") // still evaluated, as it is in outer assertion group block
        }
    }
    //@formatter:on

    test("ex-methods-args") {
        expect(myPerson)
            .feature { f(it::nickname, false) } // subject narrowed to String
            .toBe("Robert aka. Stoll")  // fails
            .startsWith("llotS")         // not evaluated anymore
    }

    //@formatter:off
    //snippet-Family-start
    data class FamilyMember(val name: String)
    data class Family(val members: List<FamilyMember>)

    val myFamily = Family(listOf(FamilyMember("Robert")))
    //snippet-Family-end
    //@formatter:on

    test("snippet-Family") { }

    test("ex-arbitrary-features") {
        //snippet-Family-insert
        expect(myFamily)
            .feature("number of members", { members.size }) { toBe(1) } // subject still Family afterwards
            .feature("first member's name") { members.first().name }    // subject narrowed to String
            .toBe("Peter")
    }

    //snippet-within-funs-start
    fun <F : Any, T : Pair<F, *>> Expect<T>.firstToBeDoneWrong(expected: F) =
        feature({ f(it::first) }) { toBe(expected) }

    fun <F : Any, T : Pair<F, *>> Expect<T>.firstToBe(expected: F) =
        feature(Pair<F, *>::first) { toBe(expected) }
    //snippet-within-funs-end
    test("snippet-within-funs") { }

    test("ex-within-assertion-functions") {
        //snippet-within-funs-insert

        expect(listOf(1 to "a", 2 to "b")).get(10) {
            firstToBeDoneWrong(1)
            firstToBe(1)
        }
    }


    @Suppress("UNUSED_PARAMETER")
    //snippet-worst-case-start
    class WorstCase {
        val propAndFun: Int = 1
        fun propAndFun(): Int = 1

        fun overloaded(): Int = 1
        fun overloaded(b: Boolean): Int = 1
    }
    //snippet-worst-case-end
    test("snippet-worst-case") {}

    test("code-ambiguity-problems") {
        //snippet-worst-case-insert

        expect(WorstCase()) {
            feature { p<Int>(it::propAndFun) }
            feature { f0<Int>(it::propAndFun) }
            feature { f0<Int>(it::overloaded) }
            feature { f1<Boolean, Int>(it::overloaded, true) }.toBe(1)
        }
    }


    test("snippet-type-assertions") {}
    test("ex-type-assertions-1") {
        //snippet-type-assertions-insert
        expect(x).isA<SubType1>()
            .feature { f(it::number) }
            .toBe(2)
    }
    test("ex-type-assertions-2") {
        expect(x).isA<SubType2> {
            feature { f(it::word) }.toBe("goodbye")
            feature { f(it::flag) }.toBe(false)
        }
    }

    test("ex-nullable-1") {
        val slogan1: String? = "postulating assertions made easy"
        expect(slogan1).toBe(null)
    }
    test("ex-nullable-2") {
        val slogan2: String? = null
        expect(slogan2).toBe("postulating assertions made easy")
    }
    val slogan2: String? = null
    test("ex-nullable-3") {
        expect(slogan2).notToBeNull().startsWith("atrium")
    }
    test("ex-nullable-4") {
        expect(slogan2).notToBeNull { startsWith("atrium") }
    }
})


//@formatter:off
//snippet-type-assertions-start
interface SuperType

data class SubType1(val number: Int) : SuperType
data class SubType2(val word: String, val flag: Boolean) : SuperType

val x: SuperType = SubType2("hello", flag = true)
//snippet-type-assertions-end
//@formatter:on
