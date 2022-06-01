package ch.tutteli.atrium.reporting.text

import ch.tutteli.atrium.assertions.BulletPointIdentifier
import kotlin.reflect.KClass

/**
 * Responsible to provide custom bullet points used in text reporting.
 */
interface BulletPointProvider {
    fun getBulletPoints(): Map<KClass<out BulletPointIdentifier>, String>
}

/**
 * Does not define custom bullet points, i.e. returns an empty map for [getBulletPoints] so that the default bullet
 * points are used.
 */
object UsingDefaultBulletPoints : BulletPointProvider {
    /**
     * Returns an empty map so that the default bullet points are used.
     */
    override fun getBulletPoints(): Map<KClass<out BulletPointIdentifier>, String> = emptyMap()
}
