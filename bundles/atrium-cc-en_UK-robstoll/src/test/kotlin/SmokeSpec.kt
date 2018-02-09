
import ch.tutteli.atrium.api.cc.en_UK.toBe
import ch.tutteli.atrium.verbs.assertthat.assertThat
import org.jetbrains.spek.api.Spek

object SmokeSpec : Spek({
    test("see if `toBe` can be used") {
        assertThat(1).toBe(1)
    }
})
