package readme.examples

//snippet-import-start
import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
//snippet-import-end
import ch.tutteli.atrium.creating.Expect
//snippet-subExpect-start
import ch.tutteli.atrium.domain.builders.utils.subExpect
//snippet-subExpect-end
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

    test("snippet-import") {}
    test("snippet-subExpect") {}

    test("ex-first") {
        //snippet-import-insert

        val x = 10
        expect(x).toBe(9)
    }

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
    //snippet-Person-end
    //snippet-myPerson-start
    val myPerson = Person("Robert", "Stoll", false)
    //snippet-myPerson-end

    test("snippet-Person") {}

    test("ex-property-methods-single") {
        //snippet-Person-insert
        //snippet-myPerson-insert
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
        expect(slogan2)     // subject has type String?
            .notToBeNull()  // subject narrowed to String
            .startsWith("atrium")
    }
    test("ex-nullable-4") {
        expect(slogan2).notToBeNull { startsWith("atrium") }
    }

    test("ex-collection-short-1") {
        expect(listOf(1, 2, 2, 4)).contains(2, 3)
    }

    test("ex-collection-short-2") {
        expect(listOf(1, 2, 2, 4)).contains(
            { isLessThan(0) },
            { isGreaterThan(2).isLessThan(4) }
        )
    }

    test("ex-collection-any") {
        expect(listOf(1, 2, 3, 4)).any { isLessThan(0) }
    }
    test("ex-collection-none") {
        expect(listOf(1, 2, 3, 4)).none { isGreaterThan(2) }
    }
    test("ex-collection-all") {
        expect(listOf(1, 2, 3, 4)).all { isGreaterThan(2) }
    }

    test("ex-collection-builder-1") {
        expect(listOf(1, 2, 2, 4)).contains.inOrder.only.entries({ isLessThan(3) }, { isLessThan(2) })
    }
    test("ex-collection-builder-2") {
        expect(listOf(1, 2, 2, 4)).contains.inOrder.only.values(1, 2, 2, 3, 4)
    }
    test("ex-collection-builder-3") {
        expect(listOf(1, 2, 2, 4)).contains.inAnyOrder.atLeast(1).butAtMost(2).entries({ isLessThan(3) })
    }
    test("ex-collection-builder-4") {
        expect(listOf(1, 2, 2, 4)).contains.inAnyOrder.only.values(1, 2, 3, 4)
    }
    test("ex-collection-builder-5") {
        expect(listOf(1, 2, 2, 4)).contains.inAnyOrder.only.values(4, 3, 2, 2, 1)
    }

    test("ex-map-1") {
        expect(mapOf("a" to 1, "b" to 2)).contains("c" to 2, "a" to 1, "b" to 1)
    }
    test("ex-map-2") {
        expect(mapOf("a" to 1, "b" to 2)).contains(
            KeyValue("c") { toBe(2) },
            KeyValue("a") { isGreaterThan(2) },
            KeyValue("b") { isLessThan(2) }
        )
    }

    test("ex-map-3") {
        //snippet-Person-insert
        val bernstein = Person("Leonard", "Bernstein", isStudent = false)
        expect(mapOf("bernstein" to bernstein))
            .getExisting("bernstein") {
                feature { f(it::firstName) }.toBe("Leonard")
                feature { f(it::isStudent) }.toBe(false)
            }
            .getExisting("einstein") {
                feature { f(it::firstName) }.toBe("Albert")
            }
    }
    test("ex-map-4") {
        expect(mapOf("a" to 1, "b" to 2)) {
            keys { all { startsWith("a") } }
            values { none { isGreaterThan(1) } }
        }
    }
    test("ex-map-5") {
        expect(linkedMapOf("a" to 1, "b" to 2)).asEntries().contains.inOrder.only.entries(
            { isKeyValue("a", 1) },
            {
                key.startsWith("a")
                value.isGreaterThan(2)
            }
        )
    }

    //snippet-data-driven-1-start
    fun myFun(i: Int) = (i + 97).toChar()
    //snippet-data-driven-1-end
    test("snippet-data-driven-1") {}

    test("ex-data-driven-1") {
        //snippet-data-driven-1-insert

        expect("calling myFun with...") {
            mapOf(
                1 to 'a',
                2 to 'c',
                3 to 'e'
            ).forEach { (arg, result) ->
                feature { f(::myFun, arg) }.toBe(result)
            }
        }
    }

    test("ex-data-driven-2") {
        //snippet-subExpect-insert

        expect("calling myFun with ...") {
            mapOf(
                1 to subExpect<Char> { isLessThan('f') },
                2 to subExpect { toBe('c') },
                3 to subExpect { isGreaterThan('e') }
            ).forEach { (arg, assertionCreator) ->
                feature({ f(::myFun, arg) }, assertionCreator)
            }
        }
    }

    //snippet-data-driven-3-start
    fun myNullableFun(i: Int) = if (i > 0) i.toString() else null
    //snippet-data-driven-3-end
    test("snippet-data-driven-3") {}

    test("ex-data-driven-3") {
        //snippet-data-driven-3-insert

        expect("calling myNullableFun with ...") {
            mapOf(
                Int.MIN_VALUE to subExpect<String> { contains("min") },
                -1 to null,
                0 to null,
                1 to subExpect { toBe("1") },
                2 to subExpect { endsWith("2") },
                Int.MAX_VALUE to subExpect { toBe("max") }
            ).forEach { (arg, assertionCreatorOrNull) ->
                feature { f(::myNullableFun, arg) }.toBeNullIfNullGivenElse(assertionCreatorOrNull)
            }
        }
    }


    test("exs-add-info-1") {
        expect(listOf(1, 2, 3)).contains.inOrder.only.values(1, 3)
    }
    test("exs-add-info-2") {
        expect(9.99f).toBeWithErrorTolerance(10.0f, 0.01f)
    }
    test("ex-add-info-3") {
        expect {
            try {
                throw UnsupportedOperationException("not supported")
            } catch (t: Throwable) {
                throw IllegalArgumentException("no no no...", t)
            }
        }.toThrow<IllegalStateException> { messageContains("no no no") }
    }

    test("ex-pitfall-1") {
        expect(BigDecimal.TEN).isEqualIncludingScale(BigDecimal("10.0"))
    }
    test("ex-pitfall-2") {
        expect(listOf(1)).get(0) {}
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
