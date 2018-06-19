package ch.tutteli.atrium.core

/**
 * Caches the result of evaluating this function in case it gets called and does not call it a second time but returns
 * the same result again.
 *
 * @return The result of evaluating this function (calling it).
 */
fun <T> (() -> T).evalOnce(): () -> T {
    val v by lazy { this() }
    return { v }
}
