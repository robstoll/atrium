import ch.tutteli.atrium.api.fluent.en_GB.feature
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.FeatureExpect
import java.sql.SQLException

val Expect<SQLException>.errorCode: FeatureExpect<SQLException, Int>
    get() = feature("errorCode", SQLException::getErrorCode)

fun Expect<SQLException>.errorCode(assertionCreator: Expect<Int>.() -> Unit) =
    feature("errorCode", SQLException::getErrorCode, assertionCreator)

val Expect<SQLException>.sqlState: FeatureExpect<SQLException, String>
    get() = feature("sqlState", SQLException::getSQLState)

fun Expect<SQLException>.sqlState(assertionCreator: Expect<String>.() -> Unit) =
    feature("sqlState", SQLException::getSQLState, assertionCreator)
