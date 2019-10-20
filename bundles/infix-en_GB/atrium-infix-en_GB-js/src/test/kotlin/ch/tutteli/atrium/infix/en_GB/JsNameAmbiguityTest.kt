package ch.tutteli.atrium.infix.en_GB


import kotlin.test.Test

/**
 * As there are bugs related to JsName in Kotlin we should test each to be sure that it works also during runtime.
 */
class JsNameTest {

    @Test
    fun toBeNullable() {
        //TODO #233 uncomment once toBe is implemented
        //expect(null as Int?) toBe null
        //expect(1 as Int?) toBe 1
    }

    @Test
    fun isKeyValueNullable() {
        //TODO #233 uncomment once toBe is implemented
//        expect(mapOf(1 to null as Int?)).asEntries().containsExactly {
//            isKeyValue(1, null)
//        }
        //expect(mapOf(1 to null as Int?)) containsExactly Entries({isKeyValue(1, null)})
    }
}
