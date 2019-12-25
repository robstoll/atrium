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
    }


    @Suppress("unused")
    fun iterableAmbiguityTest() {
        //iterable
        expect(listOf(1, 2).asIterable())._domain.hasNext()

        //iterable comparable
        expect(listOf(1, 2).asIterable())._domain.min()
    }

    @Suppress("unused")
    fun collectionAmbiguityTest() {
        //collection
        expect(listOf(1, 2) as Collection<Int>)._domain.size

        //iterable
        expect(listOf(1, 2) as Collection<Int>)._domain.hasNext()

        //iterable comparable
        expect(listOf(1, 2) as Collection<Int>)._domain.min()
    }

    @Suppress("unused")
    fun listAmbiguityTest() {
        expect(listOf(1, 2))._domain
    }
}
