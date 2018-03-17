package ch.tutteli.atrium.assertions.basic.contains.builders

/**
 * Validates that times is not `1`; throws an IllegalArgumentException otherwise, pointing the user to use the given
 * [exactlyCall] instead of the given [atMostCall].
 */
@Deprecated("Use the fun from package creating, will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.domain.creating.basic.contains.builders.validateAtMost"))
fun validateAtMost(
    times: Int,
    atMostCall: (Int) -> String,
    atLeastCall: (Int) -> String,
    exactlyCall: (Int) -> String
) {
    require(1 != times) {
        "use ${exactlyCall(times)} instead of ${atMostCall(times)}; ${atMostCall(times)} defines implicitly ${atLeastCall(1)} as well"
    }
}

/**
 * Validates that [atLeastTimes] is not equal to or greater than [butAtMostTimes]; throws IllegalArgumentException
 * otherwise, pointing the user to use the given [exactlyCall] in case [atLeastTimes] equals [butAtMostTimes].
 */
@Deprecated("Use the fun from package creating, will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.domain.creating.basic.contains.builders.validateButAtMost"))
fun validateButAtMost(
    atLeastTimes: Int,
    butAtMostTimes: Int,
    atLeastButAtMostCall: (Int, Int) -> String,
    atLeastCall: (Int) -> String,
    butAtMostCall: (Int) -> String,
    exactlyCall: (Int) -> String
) {
    require(atLeastTimes != butAtMostTimes) {
        "use ${exactlyCall(butAtMostTimes)} instead of ${atLeastButAtMostCall(butAtMostTimes, butAtMostTimes)}"
    }

    require(atLeastTimes < butAtMostTimes) {
        "specifying ${butAtMostCall(butAtMostTimes)} does not make sense if ${atLeastCall(atLeastTimes)} was used before"
    }
}
