package ch.tutteli.atrium.spec.reporting.translating

import ch.tutteli.atrium.api.cc.en_UK.toThrow
import ch.tutteli.atrium.reporting.translating.TranslationSupplier
import ch.tutteli.atrium.reporting.translating.Untranslatable
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.it
import java.util.*

abstract class TranslationSupplierSpec(
    verbs: IAssertionVerbFactory,
    testeeFactory: () -> TranslationSupplier,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit)
        = describeFun(describePrefix, funName, body = body)

    val testee = testeeFactory()

    describeFun(testee::get.name) {
        it("throws an ${IllegalArgumentException::class.simpleName} in case one passes Locale.ROOT"){
            verbs.checkException {
                testee.get(Untranslatable("whatever"), Locale.ROOT)
            }.toThrow<IllegalArgumentException>()
        }
    }
})
