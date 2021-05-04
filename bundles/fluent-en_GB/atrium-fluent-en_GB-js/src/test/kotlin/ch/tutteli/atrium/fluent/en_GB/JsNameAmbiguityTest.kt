package ch.tutteli.atrium.fluent.en_GB

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.atrium.creating.Expect
import kotlin.test.Test

/**
 * As there are bugs related to JsName in Kotlin we should test each to be sure that it works also during runtime.
 */
class JsNameAmbiguityTest {

    @Test
    fun toBeNullable() {
        expect(null as Int?).toEqual(null)
        expect(1 as Int?).toEqual(1)
    }

    @Test
    fun isKeyValueNullable() {
        expect(mapOf(1 to null as Int?)).asEntries().toContainExactly {
            isKeyValue(1, null)
        }
    }
}
