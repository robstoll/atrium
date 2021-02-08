package ch.tutteli.atrium.creating.impl

import ch.tutteli.atrium.creating.ComponentFactoryContainer
import ch.tutteli.atrium.creating.ExperimentalComponentFactoryContainer
import ch.tutteli.atrium.creating.build
import ch.tutteli.atrium.reporting.AtriumErrorAdjuster
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.reporting.erroradjusters.MultiAtriumErrorAdjuster
import ch.tutteli.atrium.reporting.erroradjusters.RemoveAtriumFromAtriumError
import ch.tutteli.atrium.reporting.erroradjusters.RemoveRunnerAtriumError
import ch.tutteli.atrium.reporting.erroradjusters.impl.RemoveAtriumFromAtriumErrorImpl
import ch.tutteli.atrium.reporting.erroradjusters.impl.RemoveRunnerAtriumErrorImpl
import ch.tutteli.atrium.reporting.reporter
import kotlin.reflect.KClass

@ExperimentalComponentFactoryContainer
internal abstract class ComponentFactoryContainerImpl : ComponentFactoryContainer {

    override fun merge(componentFactoryContainer: ComponentFactoryContainer?): ComponentFactoryContainer =
        if (componentFactoryContainer == null) this
        else RedefiningComponentFactoryContainer(this, componentFactoryContainer)

    @ExperimentalComponentFactoryContainer
    private class RootComponentFactoryContainer(
        private val components: Map<out KClass<*>, (ComponentFactoryContainer) -> Any>,
        private val chainedComponents: Map<out KClass<*>, Sequence<(ComponentFactoryContainer) -> Any>>
    ) : ComponentFactoryContainerImpl() {

        override fun getFactoryOrNull(kClass: KClass<*>): ((ComponentFactoryContainer) -> Any)? =
            components[kClass]

        override fun getFactoryForChainedOrNull(
            kClass: KClass<*>
        ): (Sequence<(ComponentFactoryContainer) -> Any>)? = chainedComponents[kClass]
    }

    private class RedefiningComponentFactoryContainer(
        private val previousFactoryContainer: ComponentFactoryContainer,
        private val redefiningFactoryContainer: ComponentFactoryContainer
    ) : ComponentFactoryContainerImpl() {

        override fun getFactoryOrNull(kClass: KClass<*>): ((ComponentFactoryContainer) -> Any)? =
            redefiningFactoryContainer.getFactoryOrNull(kClass) ?: previousFactoryContainer.getFactoryOrNull(kClass)

        override fun getFactoryForChainedOrNull(kClass: KClass<*>): Sequence<(ComponentFactoryContainer) -> Any>? {
            //TODO 1.0.0 rewrite to sequence { ... }
            val previousSequence = previousFactoryContainer.getFactoryForChainedOrNull(kClass)
            return redefiningFactoryContainer.getFactoryForChainedOrNull(kClass)?.let { redefinedSequence ->
                if (previousSequence != null) redefinedSequence.plus(previousSequence)
                else redefinedSequence
            } ?: previousSequence
        }
    }

    companion object {
        operator fun invoke(
            components: Map<out KClass<*>, (ComponentFactoryContainer) -> Any>,
            chainedComponents: Map<out KClass<*>, Sequence<(ComponentFactoryContainer) -> Any>>
        ): ComponentFactoryContainer = RootComponentFactoryContainer(components, chainedComponents)
    }
}

// TODO 0.17.0 I guess it would make sense to cache instance here and only re-create them if this component was merged
// with another because most Atrium users won't change the components and it would most likely just be a waste of
// resources to re-create all over and over again. On the other hand they would be very short-lived an most likely
// just be swept away when GC kicks in (not survive the young generation)
@ExperimentalComponentFactoryContainer
//TODO 0.17.0 or 0.18.0 make internal
object DefaultComponentFactoryContainer : ComponentFactoryContainer by ComponentFactoryContainerImpl(
    mapOf(
        Reporter::class to { _ ->
            //TODO 0.16.0 exchange with new Reportable based Reporter
            reporter
        },
        RemoveAtriumFromAtriumError::class to { _ -> RemoveAtriumFromAtriumErrorImpl() },
        RemoveRunnerAtriumError::class to { _ -> RemoveRunnerAtriumErrorImpl() },
        AtriumErrorAdjuster::class to { c ->
            MultiAtriumErrorAdjuster(
                c.build<RemoveAtriumFromAtriumError>(),
                c.build<RemoveRunnerAtriumError>(),
                emptyList()
            )
        }
    ),
    //TODO 0.16.0 define default chainable types like TextFormatter
    emptyMap()
)
