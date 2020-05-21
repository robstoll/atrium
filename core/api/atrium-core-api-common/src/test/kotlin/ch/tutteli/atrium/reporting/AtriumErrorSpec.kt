package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.core.coreFactory
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class AtriumErrorSpec : Spek({
    describe("creating an AtriumError") {
        it("has `null` as cause - regression for #303") {
            val e = AtriumError.create("hello world", coreFactory.newNoOpAtriumErrorAdjuster())
            expect(e).feature(Throwable::cause).toBe(null)
        }
    }
})
