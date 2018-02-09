import ch.tutteli.atrium.api.cc.de_CH.ist
import ch.tutteli.atrium.verbs.assert.assert
import org.jetbrains.spek.api.Spek

object SmokeSpec : Spek({
    test("see if `ist` can be used") {
        assert(1).ist(1)
    }
})
