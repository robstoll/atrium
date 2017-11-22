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
    if (1 == times) throw IllegalArgumentException(
        "use $nameExactlyFun($times) instead of $nameAtMostFun($times); $nameAtMostFun defines implicitly $nameAtLeastFun(1) as well")
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
    if (atLeastTimes == butAtMostTimes) throw IllegalArgumentException(
        "use $nameExactlyFun($butAtMostTimes) instead of $nameAtLeastFun($butAtMostTimes).$nameButAtMostFun($butAtMostTimes)")

    if (atLeastTimes > butAtMostTimes) throw IllegalArgumentException(
        "specifying $nameButAtMostFun($butAtMostTimes) does not make sense if $nameAtLeastFun($atLeastTimes) was used before")
}
