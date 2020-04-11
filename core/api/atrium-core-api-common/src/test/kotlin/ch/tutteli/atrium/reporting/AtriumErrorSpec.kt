@file:Suppress("DEPRECATION" /* remove once we have migrated to new infix API */)
package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.api.cc.infix.en_GB.property
import ch.tutteli.atrium.api.cc.infix.en_GB.toBe
import ch.tutteli.atrium.verbs.internal.assert
import ch.tutteli.atrium.core.coreFactory
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class AtriumErrorSpec : Spek({
    describe("creating an AtriumError") {
        //regression for #303
        it("has `null` as cause") {
            val e = AtriumError.create("hello world", coreFactory.newNoOpAtriumErrorAdjuster())
            assert(e).property(Throwable::cause).toBe(null)
        }
    }
})
