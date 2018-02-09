import ch.tutteli.atrium.api.cc.infix.en_UK.toBe
import ch.tutteli.atrium.verbs.expect.expect
import org.jetbrains.spek.api.Spek

object SmokeSpec : Spek({
    test("see if `toBe` can be used") {
        expect(1) toBe 1
    }
})
