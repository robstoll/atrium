package ch.tutteli.atrium.logic.creating.filesystem

import java.io.IOException
import java.nio.file.Path

/**
 * Executes the given [block] and catches [IOException]s.
 *
 * @return [Success] with [this] path and [block]’s result if [block] executes successfully.
 *  [Failure] with [this] path and the thrown [IOException] if [block] throws an [IOException]
 *  @throws Exception any exception that is thrown by [block] if it is not an [IOException]
 */
inline fun <T> Path.runCatchingIo(block: Path.() -> T): IoResult<T> = try {
    Success(this, this.block())
} catch (e: IOException) {
    Failure(this, e)
}

sealed class IoResult<out T>(val path: Path)
class Success<out T>(path: Path, val value: T) : IoResult<T>(path)
class Failure(path: Path, val exception: IOException) : IoResult<Nothing>(path)
