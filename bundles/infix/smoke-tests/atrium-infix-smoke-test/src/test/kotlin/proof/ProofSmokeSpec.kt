package proof

import ch.tutteli.atrium.api.infix.en_GB.existing
import ch.tutteli.atrium.api.infix.en_GB.notToBe
import ch.tutteli.atrium.api.verbs.expect
import org.spekframework.spek2.Spek
import java.nio.file.Paths

object ProofSmokeSpec : Spek({
    test("see if `Path.existsNot` can be used") {
        expect(Paths.get("nonExisting")) notToBe existing
    }
})
