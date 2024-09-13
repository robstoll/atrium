package readme.examples

import readme.examples.utils.expect
import ch.tutteli.atrium.api.fluent.en_GB.*
import org.junit.jupiter.api.Test
import readme.examples.jupiter.ReadmeTest

/**
 * See [ReadmeTest] on how these tests are written into README.md
 */
class CollectionExamples : ReadmeTest {
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
}
