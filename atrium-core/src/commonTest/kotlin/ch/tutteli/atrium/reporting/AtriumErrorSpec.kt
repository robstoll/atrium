package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.api.infix.en_GB.feature
import ch.tutteli.atrium.api.infix.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.assertions.BasicDescriptiveAssertion
import ch.tutteli.atrium.core.falseProvider
import ch.tutteli.atrium.reporting.erroradjusters.NoOpAtriumErrorAdjuster
import ch.tutteli.atrium.reporting.translating.Untranslatable
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class AtriumErrorSpec : Spek({
    describe("creating an AtriumError") {
        it("has `null` as cause - regression for #303") {
            val e = AtriumError.create(
                "hello world",
                BasicDescriptiveAssertion(Untranslatable("no really the reason"), 1, falseProvider),
                NoOpAtriumErrorAdjuster
            )
            expect(e).feature(Throwable::cause) toEqual null
        }
    }
})
