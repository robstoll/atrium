package ch.tutteli.atrium.reporting.text

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.api.verbs.internal.expectGrouped
import ch.tutteli.atrium.core.polyfills.objClass
import ch.tutteli.atrium.core.polyfills.objInterface
import ch.tutteli.atrium.reporting.text.impl.DefaultTextObjectFormatter
import ch.tutteli.atrium.reporting.translating.UsingDefaultTranslator
import kotlin.test.Test

class TextObjectFormatterTest {
    val testee = DefaultTextObjectFormatter(UsingDefaultTranslator())

    @Test
    fun anonymous_class_interface(){
        expectGrouped {
            expect(testee.format(objInterface)) toStartWith  "[object Object]"
            // should work but does not, see https://youtrack.jetbrains.com/issue/KT-64184
//            expect(objClass as Any) feature { f(it::toString) } toEqual "[object Object]"
            expect(testee.format(objClass)) toStartWith  "null []"
        }
    }
}
