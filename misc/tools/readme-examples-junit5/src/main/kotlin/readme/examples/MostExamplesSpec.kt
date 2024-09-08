package readme.examples

import readme.examples.utils.expect
import ch.tutteli.atrium.api.fluent.en_GB.*
import org.junit.jupiter.api.Test
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
class MostExamplesSpec {

    @Test
    fun `ex-single`() {
        // two single expectations, only first evaluated
        expect(4 + 6).toBeLessThan(5).toBeGreaterThan(10)
    }

    fun codeSingleNotExecutedHenceDoesNotFail() {
        //snippet-code-single-start
        expect(4 + 6).toBeLessThan(5)
        expect(4 + 6).toBeGreaterThan(10)
        //snippet-code-single-end
    }

    @Test
    fun `code-single-explained`() {
        //snippet-code-single-insert
    }

    @Test
    fun `ex-group`() {
        // expectation-group with two expectations, both evaluated
        expect(4 + 6) {
            toBeLessThan(5)
            toBeGreaterThan(10)
        }
    }

    fun codeAndNotExecutedHenceDoesNotFail() {
        //snippet-code-and-start
        expect(5) {
            // ...
        } and { // if the previous block fails, then this one is not evaluated
            // ...
        }
        //snippet-code-and-end
    }

    @Test
    fun `code-and`() {
        expect(5).toBeGreaterThan(2).and.toBeLessThan(10)

        //snippet-code-and-insert
    }

    @Test
    fun `ex-toThrow1`() {
        expect {
            // this lambda does something but eventually...
            throw IllegalArgumentException("name is empty")
        }.toThrow<IllegalStateException>()
    }

    @Test
    fun `ex-toThrow2`() {
        expect {
            throw IllegalArgumentException()
        }.toThrow<IllegalArgumentException> {
            message { toStartWith("firstName") }
        }
    }

    @Test
    fun `ex-toThrow3`() {
        expect {
            throw IllegalArgumentException()
        }.toThrow<IllegalArgumentException>().message.toStartWith("firstName")
    }

    @Test
    fun `ex-notToThrow`() {
        expect {
            // this block does something but eventually...
            throw IllegalArgumentException("name is empty", RuntimeException("a cause"))
        }.notToThrow()
    }

    @Test
    fun `ex-type-expectations-1`() {
        //snippet-type-expectations-insert
        expect(x).toBeAnInstanceOf<SubType2> {
            feature { f(it::word) }.toEqual("goodbye")
            feature { f(it::flag) }.toEqual(false)
        }
    }
    @Test
    fun `ex-type-expectations-2`() {
        expect(x).toBeAnInstanceOf<SubType1>()
            .feature { f(it::number) }
            .toEqual(2)
    }

    @Test
    fun `ex-nullable-1`() {
        val slogan1: String? = "postulating expectations made easy"
        expect(slogan1).toEqual(null)
    }
    @Test
    fun `ex-nullable-2`() {
        val slogan2: String? = null
        expect(slogan2).toEqual("postulating expectations made easy")
    }
    val slogan2: String? = null
    @Test
    fun `ex-nullable-3`() {
        expect(slogan2)        // subject has type String?
            .notToEqualNull()  // subject is narrowed to String
            .toStartWith("atrium")
    }
    @Test
    fun `ex-nullable-4`() {
        expect(slogan2).notToEqualNull { toStartWith("atrium") }
    }
    @Test
    fun `ex-collection-short-1`() {
        expect(listOf(1, 2, 2, 4)).toContain(2, 3)
    }

    @Test
    fun `ex-collection-short-2`() {
        expect(listOf(1, 2, 2, 4)).toContain(
            { toBeLessThan(0) },
            { toBeGreaterThan(2).toBeLessThan(4) }
        )
    }

    @Test
    fun `ex-collection-any`() {
        expect(listOf(1, 2, 3, 4)).toHaveElementsAndAny {
            toBeLessThan(0)
        }
    }

    @Test
    fun `ex-collection-none`() {
        expect(listOf(1, 2, 3, 4)).toHaveElementsAndNone {
            toBeGreaterThan(2)
        }
    }

    @Test
    fun `ex-collection-all`() {
        expect(listOf(1, 2, 3, 4)).toHaveElementsAndAll {
            toBeGreaterThan(2)
        }
    }

    @Test
    fun `ex-collection-builder-1`() {
        expect(listOf(1, 2, 2, 4)).toContain.inOrder.only.entries({ toBeLessThan(3) }, { toBeLessThan(2) })
    }
    @Test
    fun `ex-collection-reportOptions-1`() {
        expect(listOf(1, 2, 2, 4)).toContainExactly(
            { toBeLessThan(3) },
            { toBeLessThan(2) },
            { toBeGreaterThan(1) },
            report = { showOnlyFailingIfMoreExpectedElementsThan(2) }
        )
    }
    @Test
    fun `ex-collection-builder-2`() {
        expect(listOf(1, 2, 2, 4)).toContain.inOrder.only.values(1, 2, 2, 3, 4)
    }
    @Test
    fun `ex-collection-builder-3`() {
        expect(listOf(1, 2, 2, 4)).toContain.inAnyOrder.atLeast(1).butAtMost(2).entries({ toBeLessThan(3) })
    }
    @Test
    fun `ex-collection-builder-4`() {
        expect(listOf(1, 2, 2, 4)).toContain.inAnyOrder.only.values(1, 2, 3, 4)
    }
    @Test
    fun `ex-collection-builder-5`() {
        expect(listOf(1, 2, 2, 4)).toContain.inAnyOrder.only.values(4, 3, 2, 2, 1)
    }

    @Test
    fun `ex-map-1`() {
        expect(mapOf("a" to 1, "b" to 2)).toContain("c" to 2, "a" to 1, "b" to 1)
    }
    @Test
    fun `ex-map-2`() {
        expect(mapOf("a" to 1, "b" to 2)).toContain(
            KeyValue("c") { toEqual(2) },
            KeyValue("a") { toBeGreaterThan(2) },
            KeyValue("b") { toBeLessThan(2) }
        )
    }

    @Test
    fun `ex-map-only-1`() {
        expect(mapOf("a" to 1, "b" to 2)).toContainOnly("b" to 2)
    }
    @Test
    fun `ex-map-only-2`() {
        expect(mapOf("a" to 1, "b" to 2)).toContainOnly(
            KeyValue("c") { toEqual(2) },
            KeyValue("a") { toBeLessThan(2) },
            KeyValue("b") { toBeLessThan(2) }
        )
    }

    @Test
    fun `ex-map-builder-1`() {
        expect(mapOf("a" to 1, "b" to 2)).toContain.inOrder.only.entries("b" to 2, "a" to 1)
    }
    @Test
    fun `ex-map-builder-2`() {
        expect(mapOf("a" to 1, "b" to 2)).toContain.inOrder.only.entries(
            KeyValue("a") { toBeLessThan(2) },
            KeyValue("b") { toBeLessThan(2) })
    }

    //snippet-SimplePerson-start
    data class Person(val firstName: String, val lastName: String, val age: Int)
    //snippet-SimplePerson-end

    @Test
    fun `ex-map-3`() {
        //snippet-SimplePerson-insert
        val bernstein = Person("Leonard", "Bernstein", 50)
        expect(mapOf("bernstein" to bernstein))
            .getExisting("bernstein") {
                feature { f(it::firstName) }.toEqual("Leonard")
                feature { f(it::age) }.toEqual(60)
            }
            .getExisting("einstein") {
                feature { f(it::firstName) }.toEqual("Albert")
            }
    }
    @Test
    fun `ex-map-4`() {
        expect(mapOf("a" to 1, "b" to 2)) {
            keys { toHaveElementsAndAll { toStartWith("a") } }
            values { toHaveElementsAndNone { toBeGreaterThan(1) } }
        }
    }
    @Test
    fun `ex-map-5`() {
        expect(linkedMapOf("a" to 1, "b" to 2)).asEntries().toContain.inOrder.only.entries(
            { toEqualKeyValue("a", 1) },
            {
                key.toStartWith("a")
                value.toBeGreaterThan(2)
            }
        )
    }

    @Test
    fun `ex-because-1`() {
        expect("filename?")
            .because("? is not allowed in file names on Windows") {
                notToContain("?")
            }
    }

    @Test
    fun `exs-add-info-1`() {
        expect(listOf(1, 2, 3)).toContain.inOrder.only.values(1, 3)
    }
    @Test
    fun `exs-add-info-2`() {
        expect(9.99f).toEqualWithErrorTolerance(10.0f, 0.01f)
    }
    @Test
    fun `ex-add-info-3`() {
        expect {
            try {
                throw UnsupportedOperationException("not supported")
            } catch (t: Throwable) {
                throw IllegalArgumentException("no no no...", t)
            }
        }.toThrow<IllegalStateException> { messageToContain("no no no") }
    }

    @Test
    fun `ex-pitfall-1`() {
        expect(BigDecimal.TEN).toEqualIncludingScale(BigDecimal("10.0"))
    }
    @Test
    fun `ex-pitfall-2`() {
        expect(listOf(1)).get(0) {}
    }
}

//@formatter:off
//snippet-type-expectations-start
interface SuperType

data class SubType1(val number: Int) : SuperType
data class SubType2(val word: String, val flag: Boolean) : SuperType

val x: SuperType = SubType2("hello", flag = true)
//snippet-type-expectations-end
//@formatter:on
