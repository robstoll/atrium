
package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.js.JsName

class WorstCase {

    val propAndFun: Int = 1
    @JsName("propFun")
    fun propAndFun(): Int = 1

    fun overloaded(): Int = 1
    fun overloaded(@Suppress("unused", "UNUSED_PARAMETER") b: Boolean): Int = 1
}

// we don't run any tests in here but check that the compiler does not report any ambiguities
fun testOverloadAmbiguity() {
    expect(WorstCase()) {
        feature { p(it::propAndFun) }
        feature { f0(it::propAndFun) }
        feature { f0(it::overloaded) }
        feature { f1(it::overloaded, true) }
    }
}
