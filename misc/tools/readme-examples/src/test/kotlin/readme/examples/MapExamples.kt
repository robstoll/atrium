package readme.examples

import readme.examples.utils.expect
import ch.tutteli.atrium.api.fluent.en_GB.*
import org.junit.jupiter.api.Test
import readme.examples.jupiter.ReadmeTest
import java.math.BigDecimal

/**
 * See [ReadmeTest] on how these tests are written into README.md
 */
class MapExamples : ReadmeTest {

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
}
