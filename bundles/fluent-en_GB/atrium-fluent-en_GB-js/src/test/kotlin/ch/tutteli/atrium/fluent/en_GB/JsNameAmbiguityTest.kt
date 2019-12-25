package ch.tutteli.atrium.fluent.en_GB

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.atrium.domain.creating._domain
import kotlin.test.Test

/**
 * As there are bugs related to @JsName in Kotlin we should test each to be sure that it works also during runtime.
 *
 * See https://youtrack.jetbrains.com/issue/KT-33294 for more information
 */
class JsNameAmbiguityTest {

    @Test
    fun toBeNullable() {
        expect(null as Int?).toBe(null)
        expect(1 as Int?).toBe(1)
    }

    @Test
    fun isKeyValueNullable() {
        expect(mapOf(1 to null as Int?)).asEntries().containsExactly {
            isKeyValue(1, null)
        }
        expect(mapOf(1 to 1)).asEntries().containsExactly {
            isKeyValue(1, 1)
        }
    }

    @Test
    fun mapContains() {
        expect(mapOf(1 to null as Int?)).contains(1 to null)
        expect(mapOf(1 to 1)).contains(1 to 1)

        expect(mapOf(1 to null as Int?)).contains(KeyValue(1, null))
        expect(mapOf(1 to 1)).contains(KeyValue(1) { toBe(1) })
    }


    @Suppress("unused")
    fun iterableAmbiguityTest() {
        //element non-Comparable
        expect(listOf(1 to 2, 2 to 3).asIterable())._domain
        //element Comparable
        expect(listOf(1, 2).asIterable())._domain.min()
    }

    @Suppress("unused")
    fun collectionAmbiguityTest() {
        //element non-Comparable
        expect(listOf(1 to 2, 2 to 3) as Collection<Pair<Int, Int>>)._domain
        //element Comparable
        expect(listOf(1, 2) as Collection<Int>)._domain
    }

    @Suppress("unused")
    fun listAmbiguityTest() {
        //element non-Comparable
        expect(listOf(1 to 2, 2 to 3))._domain
        //element Comparable
        expect(listOf(1, 2))._domain
    }

    @Suppress("unused")
    fun mapAmbiguityTest() {
        //value nullable
        expect(mapOf(1 to 2 as Int?))._domain
        //value non-nullable
        expect(mapOf(1 to 2))._domain
    }
}
