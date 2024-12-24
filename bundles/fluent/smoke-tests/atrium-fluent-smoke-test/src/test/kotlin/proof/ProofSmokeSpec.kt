package proof

import ch.tutteli.atrium.api.fluent.en_GB.notToExist
import ch.tutteli.atrium.api.verbs.expect
import org.spekframework.spek2.Spek
import java.nio.file.Paths

//TODO 1.4.0 switch to kotlin_test note that we need to activate junit for this project otherwise it is not executed
// make it fail and check it is executed as expected.
object ProofSmokeSpec : Spek({
    test("see if `Path.existsNot` can be used") {
        expect(Paths.get("nonExisting")).notToExist()
    }
})
