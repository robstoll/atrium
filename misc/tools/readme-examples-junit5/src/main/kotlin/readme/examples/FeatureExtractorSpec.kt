package readme.examples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.creating.Expect
import org.junit.jupiter.api.Test
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
class FeatureExtractorSpec {

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

    @Test
    fun `code-Person`() {
        //snippet-Person-insert
    }

    @Test
    fun `ex-its-single`() {
        expect(myPerson)
            .its({ isStudent }) { toEqual(true) } // fails, subject still Person afterwards
            .its { fullName() }                   // not evaluated anymore, subject String afterwards
            .toStartWith("rob")                   // not evaluated anymore
    }

    //@formatter:off
    @Test
    fun `ex-its-group`() {
        expect(myPerson) { // forms an expectation-group

            its({ firstName }) {   // forms an expectation-group
                toStartWith("Pe")  // fails
                toEndWith("er")    // is evaluated nonetheless
            }                      // fails as a whole

            // still evaluated, as it is in outer expectation-group
            its { lastName }.toEqual("Dummy")
        }
    }
    //@formatter:on

    @Test
    fun `ex-property-methods-single`() {
        expect(myPerson)
            .feature({ f(it::isStudent) }) { toEqual(true) } // fails, subject still Person afterwards
            .feature { f(it::fullName) }                     // not evaluated anymore, subject String afterwards
            .toStartWith("rob")                              // not evaluated anymore
    }

    //@formatter:off
    @Test
    fun `ex-property-methods-group`() {
        expect(myPerson) { // forms an expectation-group

            feature({ f(it::firstName) }) { // forms an expectation-group
                toStartWith("Pe")           // fails
                toEndWith("er")             // is evaluated nonetheless
            }                               // fails as a whole

            // still evaluated, as it is in outer expectation-group
            feature { f(it::lastName) }.toEqual("Dummy")
        }
    }
    //@formatter:on

    @Test
    fun `ex-methods-args`() {
        expect(myPerson)
            .feature { f(it::nickname, false) } // subject narrowed to String
            .toEqual("Robert aka. Stoll")       // fails
            .toStartWith("llotS")               // not evaluated anymore
    }

    //@formatter:off
    //snippet-Family-start
    data class FamilyMember(val name: String)

    data class Family(val members: List<FamilyMember>)

    val myFamily = Family(listOf(FamilyMember("Robert")))
    //snippet-Family-end
    //@formatter:on

    @Test
    fun `ex-arbitrary-features`() {
        //snippet-Family-insert
        expect(myFamily)
            .feature("the number of members", { members.size }) { toEqual(1) } // subject still Family afterwards
            .feature("the first member's name") { members.first().name }       // subject narrowed to String
            .toEqual("Peter")
    }

    //snippet-within-funs-start
    fun <F : Any, T : Pair<F, *>> Expect<T>.firstToBeDoneWrong(expected: F) =
        feature({ f(it::first) }) { toEqual(expected) }

    fun <F : Any, T : Pair<F, *>> Expect<T>.firstToBe(expected: F) =
        feature(Pair<F, *>::first) { toEqual(expected) }
    //snippet-within-funs-end

    @Test
    fun `ex-within-expectation-functions`() {
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

    @Test
    fun `code-ambiguity-problems`() {
        //snippet-worst-case-insert

        expect(WorstCase()) {
            feature { p<Int>(it::propAndFun) }
            feature { f0<Int>(it::propAndFun) }
            feature { f0<Int>(it::overloaded) }
            feature { f1<Boolean, Int>(it::overloaded, true) }.toEqual(1)
        }
    }

    data class Person2(
        val firstName: String,
        val lastName: String,
        val age: Int,
        val children: Collection<Person2>
    )

    class DataGenerator {
        fun getRandomPersonsWithChildren(): List<Person2> =
            listOf(Person2("Felix", "Mendelssohn", 2, children = emptyList()))
    }

    val dataGenerator = DataGenerator()

    @Test
    fun `code-extractSubject`() {
        val persons = dataGenerator.getRandomPersonsWithChildren()
        expect(persons).toHaveElementsAndAll {
            extractSubject { person ->
                feature { f(it::children) }.notToHaveElementsOrAll {
                    because("person should at least be 16 years older than its children") {
                        feature { f(it::age) }.toBeLessThan(person.age - 16)
                    }
                }
            }
        }
    }
}
