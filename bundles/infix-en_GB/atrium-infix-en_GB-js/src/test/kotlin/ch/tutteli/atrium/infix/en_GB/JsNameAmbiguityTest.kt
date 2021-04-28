package ch.tutteli.atrium.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.asEntries
import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.atrium.creating.Expect
import kotlin.test.Test

/**
 * As there are bugs related to JsName in Kotlin we should test each to be sure that it works also during runtime.
 */
class JsNameAmbiguityTest {

    @Test
    fun toBeNullable() {
        expect(null as Int?) toEqual null
        expect(1 as Int?) toEqual 1
    }

    @Test
    fun isKeyValueNullable() {
        expect(mapOf(1 to null as Int?)).asEntries(o).toContainExactly(fun Expect<Map.Entry<Int, Int?>>.() {
            it isKeyValue (1 to null)
        })
    }
}
