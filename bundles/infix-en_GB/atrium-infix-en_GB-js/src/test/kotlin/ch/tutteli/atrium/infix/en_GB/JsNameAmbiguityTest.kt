package ch.tutteli.atrium.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.asEntries
import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test

/**
 * As there are bugs related to JsName in Kotlin we should test each to be sure that it works also during runtime.
 */
class JsNameAmbiguityTest {

    @Test
    fun toBeNullable() {
        expect(null as Int?) toBe null
        expect(1 as Int?) toBe 1
    }

    @Test
    fun isKeyValueNullable() {
        expect(mapOf(1 to null as Int?)).asEntries(o).containsExactly {
            it isKeyValue (1 to null)
        }
    }
}
