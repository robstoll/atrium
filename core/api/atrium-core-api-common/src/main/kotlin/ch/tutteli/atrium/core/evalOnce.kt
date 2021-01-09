package ch.tutteli.atrium.core

/**
 * Caches the result of evaluating this function in case it gets called and does not call it a second time but returns
 * the same result again.
 *
 * @return The result of evaluating this function (calling it).
 */
//TODO remove with 0.17.0
@Deprecated("Will be removed with 0.17.0 without replacement")
fun <T> (() -> T).evalOnce(): () -> T {
    val v by lazy { this() }
    return { v }
}
