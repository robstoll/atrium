package readme.examples

//@formatter:off
//snippet-mapArguments-start
import ch.tutteli.atrium.logic.utils.mapArguments
//snippet-mapArguments-end
//snippet-own-boolean-import-start
import ch.tutteli.atrium.logic._logic
//snippet-own-boolean-import-end
//@formatter:on

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.reporting.Text
import org.junit.jupiter.api.Test
import readme.examples.utils.expect

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

class OwnExpectationFunctionsSpec {

    //snippet-own-boolean-1-start
    fun Expect<Int>.toBeAMultipleOf(base: Int) =
        _logic.createAndAppend("is multiple of", base) { it % base == 0 }
    //snippet-own-boolean-1-end
    @Test
    fun `code-own-boolean-1`() {
        //snippet-own-boolean-import-insert

        //snippet-own-boolean-1-insert
    }
    @Test
    fun `ex-own-boolean-1`() {
        expect(12).toBeAMultipleOf(5)
    }

    //snippet-own-boolean-2-start
    fun Expect<Int>.toBeEven() =
        _logic.createAndAppend("is", Text("an even number")) { it % 2 == 0 }
    //snippet-own-boolean-2-end
    @Test
    fun `code-own-boolean-2`() {
        //snippet-own-boolean-import-insert

        //snippet-own-boolean-2-insert
    }
    @Test
    fun `ex-own-boolean-2`() {
        expect(13).toBeEven()
    }
    @Test
    fun `code-own-compose-3a`() {
        //snippet-own-Person-insert
    }

    //snippet-own-compose-3b-start
    fun Expect<Person>.toHaveNumberOfChildren(number: Int): Expect<Person> =
        feature(Person::children) { toHaveSize(number) }

    //snippet-own-compose-3b-end
    @Test
    fun `code-own-compose-3b`() {
        //snippet-own-compose-3b-insert
    }

    @Test
    fun `ex-own-compose-3`() {
        expect(Person("Susanne", "Whitley", 43, emptyList()))
            .toHaveNumberOfChildren(2)
    }

    //snippet-own-compose-4-start
    fun Expect<Person>.toHaveAdultChildren(): Expect<Person> =
        feature(Person::children) {
            toHaveElementsAndAll {
                feature(Person::age).toBeGreaterThanOrEqualTo(18)
            }
        }

    //snippet-own-compose-4-end
    @Test
    fun `code-own-compose-4`() {
        //snippet-own-compose-4-insert
    }
    @Test
    fun `ex-own-compose-4`() {
        expect(Person("Susanne", "Whitley", 43, emptyList()))
            .toHaveAdultChildren()
    }

    @Test
    fun `code-own-compose-5`() {
        //snippet-children-insert
    }
    //@formatter:off
    @Test
    fun `ex-own-compose-5`() {
        expect(Person("Susanne", "Whitley", 43, listOf(Person("Petra", "Whitley", 12, emptyList()))))
            .children { // using the fun -> expectation-group, ergo sub expectations don't fail fast
                toHaveElementsAndNone {
                    feature { f(it::firstName) }.toStartWith("Ro")
                }
                toHaveElementsAndAll {
                    feature { f(it::lastName) }.toEqual("Whitley")
                }
            } // subject is still Person here
            .children  // using the val -> subsequent expectations are about children and fail fast
                .toHaveSize(2)
                .toHaveElementsAndAny {
                    feature { f(it::age) }.toBeGreaterThan(18)
                }
    }
    //@formatter:on


    //snippet-own-compose-6-start
    fun <T : List<Pair<String, String>>> Expect<T>.areNamesOf(
        person: Person, vararg otherPersons: Person
    ): Expect<T> {
        val (pair, otherPairs) = mapArguments(person, otherPersons) { it.firstName to it.lastName }
        return toContain.inAnyOrder.only.values(pair, *otherPairs)
    }
    //snippet-own-compose-6-end

    @Test
    fun `code-own-compose-6`() {
        //snippet-mapArguments-insert

        //snippet-own-compose-6-insert
    }
    @Test
    fun `code-own-compose-7`() {
        fun <T : List<Pair<String, String>>> Expect<T>.sameInitialsAs(
            person: Person, vararg otherPersons: Person
        ): Expect<T> {
            val (first, others) = mapArguments(person, otherPersons).toExpect<Pair<String, String>> {
                first.toStartWith(it.firstName[0].toString())
                second.toStartWith(it.lastName[0].toString())
            }
            return toContain.inOrder.only.entries(first, *others)
        }
    }
}

//snippet-own-Person-start
data class Person(
    val firstName: String,
    val lastName: String,
    val age: Int,
    val children: Collection<Person>
    // ...  and others
)
//snippet-own-Person-end

//snippet-children-start
val Expect<Person>.children: Expect<Collection<Person>> get() = feature(Person::children)

fun Expect<Person>.children(assertionCreator: Expect<Collection<Person>>.() -> Unit): Expect<Person> =
    feature(Person::children, assertionCreator)
//snippet-children-end
