package ch.tutteli.atrium.creating.impl

import ch.tutteli.atrium.core.polyfills.ConcurrentMap
import ch.tutteli.atrium.core.polyfills.MutableConcurrentMap
import ch.tutteli.atrium.core.polyfills.cast
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.creating.feature.ExperimentalFeatureInfo
import ch.tutteli.atrium.creating.feature.impl.StackTraceBasedFeatureInfo
import ch.tutteli.atrium.creating.feature.FeatureInfo
import ch.tutteli.atrium.reporting.*
import ch.tutteli.atrium.reporting.erroradjusters.MultiAtriumErrorAdjuster
import ch.tutteli.atrium.reporting.erroradjusters.RemoveAtriumFromAtriumError
import ch.tutteli.atrium.reporting.erroradjusters.RemoveRunnerFromAtriumError
import ch.tutteli.atrium.reporting.erroradjusters.impl.RemoveAtriumFromAtriumErrorImpl
import ch.tutteli.atrium.reporting.erroradjusters.impl.RemoveRunnerFromAtriumErrorImpl
import ch.tutteli.atrium.reporting.impl.AssertionFormatterControllerBasedFacade
import ch.tutteli.atrium.reporting.impl.DefaultAssertionFormatterController
import ch.tutteli.atrium.reporting.impl.OnlyFailureReporter
import ch.tutteli.atrium.reporting.text.*
import ch.tutteli.atrium.reporting.text.TextObjectFormatter
import ch.tutteli.atrium.reporting.text.impl.*
import ch.tutteli.atrium.reporting.translating.*
import ch.tutteli.atrium.reporting.translating.impl.ResourceBundleInspiredLocaleOrderDecider
import kotlin.reflect.KClass


@ExperimentalComponentFactoryContainer
internal abstract class ComponentFactoryContainerImpl : ComponentFactoryContainer {

    private val singletonComponents = MutableConcurrentMap<KClass<*>, Any>()

    final override fun <I : Any> buildOrNull(kClass: KClass<I>): I? =
        getFactoryOrNull(kClass)?.let { factory ->
            safeBuildAndMemoizeIfSingleton(kClass, factory)
        }

    final override fun <I : Any> buildChainedOrNull(kClass: KClass<I>): Sequence<I>? =
        getFactoryForChainedOrNull(kClass)?.map { factory ->
            safeBuildAndMemoizeIfSingleton(kClass, factory)
        }

    private fun <I : Any> safeBuildAndMemoizeIfSingleton(
        kClass: KClass<I>,
        factory: ComponentFactory
    ): I {
        val untypedInstance = if (factory.producesSingleton) {
            // we first check so that we only invoke the factory more than once in case of a race condition
            singletonComponents[kClass] ?: singletonComponents.putIfAbsent(kClass, factory.build(this))
        } else {
            factory.build(this)
        }
        return kClass.cast(untypedInstance)
    }

    override fun merge(componentFactoryContainer: ComponentFactoryContainer?): ComponentFactoryContainer =
        if (componentFactoryContainer == null) this
        else RedefiningComponentFactoryContainer(this, componentFactoryContainer)


    @ExperimentalComponentFactoryContainer
    private class RootComponentFactoryContainer(
        private val components: ConcurrentMap<KClass<*>, ComponentFactory>,
        private val chainedComponents: ConcurrentMap<KClass<*>, Sequence<ComponentFactory>>
    ) : ComponentFactoryContainerImpl() {

        override fun getFactoryOrNull(kClass: KClass<*>): ComponentFactory? =
            components[kClass]

        override fun getFactoryForChainedOrNull(kClass: KClass<*>): Sequence<ComponentFactory>? =
            chainedComponents[kClass]
    }

    private class RedefiningComponentFactoryContainer(
        private val previousFactoryContainer: ComponentFactoryContainer,
        private val redefiningFactoryContainer: ComponentFactoryContainer
    ) : ComponentFactoryContainerImpl() {

        override fun getFactoryOrNull(kClass: KClass<*>): ComponentFactory? =
            redefiningFactoryContainer.getFactoryOrNull(kClass) ?: previousFactoryContainer.getFactoryOrNull(kClass)

        override fun getFactoryForChainedOrNull(kClass: KClass<*>): Sequence<ComponentFactory>? {
            //TODO 1.1.0 rewrite to sequence { ... }
            val previousSequence = previousFactoryContainer.getFactoryForChainedOrNull(kClass)
            return redefiningFactoryContainer.getFactoryForChainedOrNull(kClass)?.let { redefinedSequence ->
                if (previousSequence != null) {
                    redefinedSequence.plus(previousSequence)
                } else {
                    redefinedSequence
                }
            } ?: previousSequence
        }
    }

    companion object {
        operator fun invoke(
            components: Map<KClass<*>, ComponentFactory>,
            chainedComponents: Map<KClass<*>, Sequence<ComponentFactory>>
        ): ComponentFactoryContainer =
            RootComponentFactoryContainer(ConcurrentMap(components), ConcurrentMap(chainedComponents))
    }
}

@ExperimentalComponentFactoryContainer
private infix fun <T : Any> KClass<T>.createVia(factory: (ComponentFactoryContainer) -> T): Pair<KClass<*>, ComponentFactory> =
    this to ComponentFactory(factory, producesSingleton = false)

@ExperimentalComponentFactoryContainer
private infix fun <T : Any> KClass<T>.createSingletonVia(factory: (ComponentFactoryContainer) -> T): Pair<KClass<*>, ComponentFactory> =
    this to ComponentFactory(factory, producesSingleton = true)


@ExperimentalComponentFactoryContainer
private infix fun <T : Any> KClass<T>.createChainVia(factories: Sequence<(ComponentFactoryContainer) -> T>): Pair<KClass<*>, Sequence<ComponentFactory>> =
    this to factories.map { ComponentFactory(it, producesSingleton = false) }


@ExperimentalComponentFactoryContainer
internal object DefaultComponentFactoryContainer : ComponentFactoryContainer by ComponentFactoryContainerImpl(
    mapOf(
        Reporter::class createSingletonVia { c ->
            OnlyFailureReporter(c.build())
        },

        AssertionFormatterController::class createVia { _ -> DefaultAssertionFormatterController() },
        ObjectFormatter::class createVia { c -> c.build<TextObjectFormatter>() },
        AssertionPairFormatter::class createVia { c -> c.build<TextAssertionPairFormatter>() },
        MethodCallFormatter::class createVia { c -> c.build<TextMethodCallFormatter>() },

        LocaleProvider::class createVia { _ -> UseDefaultLocaleAsPrimary },
        Translator::class createSingletonVia { c ->
            UsingDefaultTranslator(c.build<LocaleProvider>().getPrimaryLocale())
        },
        LocaleOrderDecider::class createVia { _ -> ResourceBundleInspiredLocaleOrderDecider },
        RemoveAtriumFromAtriumError::class createVia { _ -> RemoveAtriumFromAtriumErrorImpl() },
        RemoveRunnerFromAtriumError::class createVia { _ -> RemoveRunnerFromAtriumErrorImpl() },
        AtriumErrorAdjuster::class createSingletonVia { c ->
            MultiAtriumErrorAdjuster(
                c.build<RemoveAtriumFromAtriumError>(),
                c.build<RemoveRunnerFromAtriumError>(),
                emptyList()
            )
        },
        @Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
        @UseExperimental(ExperimentalFeatureInfo::class)
        FeatureInfo::class createVia { _ ->
            @Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
            @UseExperimental(ExperimentalFeatureInfo::class)
            StackTraceBasedFeatureInfo()
        },

        //Text specific factories
        AssertionFormatterFacade::class createVia { c ->
            AssertionFormatterControllerBasedFacade(c.build()).apply {
                c.buildChained<TextAssertionFormatterFactory>().forEach { factory ->
                    register { controller -> factory.build(controller) }
                }
            }
        },
        TextObjectFormatter::class createSingletonVia { c -> DefaultTextObjectFormatter(c.build()) },
        TextAssertionPairFormatter::class createVia { c ->
            TextAssertionPairFormatter.newSameLine(c.build(), c.build())
        },
        TextMethodCallFormatter::class createVia { _ -> DefaultTextMethodCallFormatter },
        BulletPointProvider::class createVia { _ -> UsingDefaultBulletPoints }
    ),

    mapOf(TextAssertionFormatterFactory::class createChainVia
        sequenceOf(
            { c ->
                val bulletPoints = c.build<BulletPointProvider>().getBulletPoints()
                val textAssertionPairFormatter = c.build<TextAssertionPairFormatter>()
                TextAssertionFormatterFactory { controller ->
                    TextListAssertionGroupFormatter(bulletPoints, controller, textAssertionPairFormatter)
                }
            },
            { c ->
                val bulletPoints = c.build<BulletPointProvider>().getBulletPoints()
                val textAssertionPairFormatter = c.build<TextAssertionPairFormatter>()
                TextAssertionFormatterFactory { controller ->
                    TextFeatureAssertionGroupFormatter(bulletPoints, controller, textAssertionPairFormatter)
                }
            },
            { c ->
                val bulletPoints = c.build<BulletPointProvider>().getBulletPoints()
                TextAssertionFormatterFactory { controller ->
                    TextExplanatoryAssertionGroupFormatter(bulletPoints, controller)
                }
            },
            { c ->
                val bulletPoints = c.build<BulletPointProvider>().getBulletPoints()
                val textAssertionPairFormatter = c.build<TextAssertionPairFormatter>()
                TextAssertionFormatterFactory { controller ->
                    TextSummaryAssertionGroupFormatter(bulletPoints, controller, textAssertionPairFormatter)
                }
            },
            { c ->
                val objectFormatter = c.build<TextObjectFormatter>()
                val bulletPoints = c.build<BulletPointProvider>().getBulletPoints()
                val textAssertionPairFormatter = c.build<TextAssertionPairFormatter>()
                TextAssertionFormatterFactory { controller ->
                    TextFallbackAssertionFormatter(
                        bulletPoints,
                        controller,
                        textAssertionPairFormatter,
                        objectFormatter
                    )
                }
            }
        )
    )
)
