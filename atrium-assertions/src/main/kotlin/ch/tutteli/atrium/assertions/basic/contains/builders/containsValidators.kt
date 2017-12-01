package ch.tutteli.atrium.assertions.basic.contains.builders

/**
 * Validates that times is not `1`; throws an IllegalArgumentException otherwise, pointing the user to use the given
 * [nameExactlyFun] instead of the given [nameAtMostFun].
 */
fun validateAtMost(
    times: Int,
    nameAtMostFun: String,
    nameAtLeastFun: String,
    nameExactlyFun: String
) {
    require(1 != times) {
        "use $nameExactlyFun($times) instead of $nameAtMostFun($times); $nameAtMostFun defines implicitly $nameAtLeastFun(1) as well"
    }
}

/**
 * Validates that [atLeastTimes] is not equal to or greater than [butAtMostTimes]; throws IllegalArgumentException
 * otherwise, pointing the user to use the given [nameExactlyFun] in case [atLeastTimes] equals [butAtMostTimes].
 */
fun validateButAtMost(
    atLeastTimes: Int,
    butAtMostTimes: Int,
    nameAtLeastFun: String,
    nameButAtMostFun: String,
    nameExactlyFun: String
) {
    require(atLeastTimes != butAtMostTimes) {
        "use $nameExactlyFun($butAtMostTimes) instead of $nameAtLeastFun($butAtMostTimes).$nameButAtMostFun($butAtMostTimes)"
    }

    require(atLeastTimes < butAtMostTimes) {
        "specifying $nameButAtMostFun($butAtMostTimes) does not make sense if $nameAtLeastFun($atLeastTimes) was used before"
    }
}
