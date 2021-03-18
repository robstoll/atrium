package readme.examples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.creating.Expect
import org.spekframework.spek2.Spek
import readme.examples.utils.expect
import java.util.*

/**
 * The tests and error message are written here and automatically placed into the README via generation.
 * The generation is done during the project built. To trigger it manually, you have to run:
 * ```
 * ./gradlew :readme-examples:build
 * ```
 *
 * There are currently three kind of tags supported:
 * - <ex-xy> => places code and output into the tag
 * - <exs-xy> => places code into the tag, output will be put into a tag named <exs-xy-output>
 * - <code> => is not supposed to fail and only the code is put into the code
 *
 * Moreover, all tags can reuse snippets defined in this file with corresponding markers
 */
class FeatureExtractorSpec : Spek({

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

    test("code-Person") {
        //snippet-Person-insert
    }

    test("ex-its-single") {
        expect(myPerson)
            .its({ isStudent }) { toBe(true) } // fails, subject still Person afterwards
            .its { fullName() }                // not evaluated anymore, subject String afterwards
            .startsWith("rob")                 // not evaluated anymore
    }

    //@formatter:off
    test("ex-its-group") {
        expect(myPerson) { // forms an assertion group block

            its({ firstName }) {   // forms an assertion group block
                startsWith("Pe")   // fails
                endsWith("er")     // is evaluated nonetheless
            }                      // fails as a whole

            // still evaluated, as it is in outer assertion group block
            its { lastName }.toBe("Dummy")
        }
    }
    //@formatter:on

    test("ex-property-methods-single") {
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

            // still evaluated, as it is in outer assertion group block
            feature { f(it::lastName) }.toBe("Dummy")
        }
    }
    //@formatter:on

    test("ex-methods-args") {
        expect(myPerson)
            .feature { f(it::nickname, false) } // subject narrowed to String
            .toBe("Robert aka. Stoll")          // fails
            .startsWith("llotS")                // not evaluated anymore
    }

    //@formatter:off
    //snippet-Family-start
    data class FamilyMember(val name: String)

    data class Family(val members: List<FamilyMember>)

    val myFamily = Family(listOf(FamilyMember("Robert")))
    //snippet-Family-end
    //@formatter:on

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

    test("code-ambiguity-problems") {
        //snippet-worst-case-insert

        expect(WorstCase()) {
            feature { p<Int>(it::propAndFun) }
            feature { f0<Int>(it::propAndFun) }
            feature { f0<Int>(it::overloaded) }
            feature { f1<Boolean, Int>(it::overloaded, true) }.toBe(1)
        }
    }
})
