package readme.examples

import readme.examples.utils.expect
import ch.tutteli.atrium.api.fluent.en_GB.*
import org.spekframework.spek2.Spek
import java.math.BigDecimal

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
class MostExamplesSpec : Spek({

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

    test("ex-map-only-1") {
        expect(mapOf("a" to 1, "b" to 2)).containsOnly("b" to 2)
    }
    test("ex-map-only-2") {
        expect(mapOf("a" to 1, "b" to 2)).containsOnly(
            KeyValue("c") { toBe(2) },
            KeyValue("a") { isLessThan(2) },
            KeyValue("b") { isLessThan(2) }
        )
    }

    test("ex-map-builder-1") {
        expect(mapOf("a" to 1, "b" to 2)).contains.inOrder.only.entries("b" to 2, "a" to 1)
    }
    test("ex-map-builder-2") {
        expect(mapOf("a" to 1, "b" to 2)).contains.inOrder.only.entries(
            KeyValue("a") { isLessThan(2) },
            KeyValue("b") { isLessThan(2) })
    }

    //snippet-SimplePerson-start
    data class Person(val firstName: String, val lastName: String, val age: Int)
    //snippet-SimplePerson-end

    test("ex-map-3") {
        //snippet-SimplePerson-insert
        val bernstein = Person("Leonard", "Bernstein", 50)
        expect(mapOf("bernstein" to bernstein))
            .getExisting("bernstein") {
                feature { f(it::firstName) }.toBe("Leonard")
                feature { f(it::age) }.toBe(60)
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

    test("ex-because-1") {
        expect("filename?")
            .because("? is not allowed in file names on Windows") {
                containsNot("?")
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
