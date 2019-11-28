package ch.tutteli.atrium.fluent.en_GB

import ch.tutteli.atrium.api.fluent.en_GB.asEntries
import ch.tutteli.atrium.api.fluent.en_GB.containsExactly
import ch.tutteli.atrium.api.fluent.en_GB.isKeyValue
import ch.tutteli.atrium.api.fluent.en_GB.toBe
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test

/**
 * As there are bugs related to JsName in Kotlin we should test each to be sure that it works also during runtime.
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
}
