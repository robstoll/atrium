package ch.tutteli.atrium.reporting.text

import ch.tutteli.atrium.assertions.BulletPointIdentifier
import kotlin.reflect.KClass

interface BulletPointProvider {
    fun getBulletPoints(): Map<KClass<out BulletPointIdentifier>, String>
}

object UsingDefaultBulletPoints : BulletPointProvider {
    override fun getBulletPoints(): Map<KClass<out BulletPointIdentifier>, String> = emptyMap()
}
