package ch.tutteli.atrium.creating.impl

import ch.tutteli.atrium.core.polyfills.ConcurrentMap
import ch.tutteli.atrium.core.polyfills.MutableConcurrentMap
import ch.tutteli.atrium.core.polyfills.cast
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.creating.feature.ExperimentalFeatureInfo
import ch.tutteli.atrium.creating.feature.FeatureInfo
import ch.tutteli.atrium.creating.feature.impl.StackTraceBasedFeatureInfo
import ch.tutteli.atrium.reporting.*
import ch.tutteli.atrium.reporting.erroradjusters.MultiAtriumErrorAdjuster
import ch.tutteli.atrium.reporting.erroradjusters.RemoveAtriumFromAtriumError
import ch.tutteli.atrium.reporting.erroradjusters.RemoveRunnerFromAtriumError
import ch.tutteli.atrium.reporting.erroradjusters.impl.RemoveAtriumFromAtriumErrorImpl
import ch.tutteli.atrium.reporting.erroradjusters.impl.RemoveRunnerFromAtriumErrorImpl
import ch.tutteli.atrium.reporting.filters.ReportableFilter
import ch.tutteli.atrium.reporting.prerendering.text.TextDesignationPreRenderer
import ch.tutteli.atrium.reporting.prerendering.text.TextPreRenderController
import ch.tutteli.atrium.reporting.prerendering.text.TextPreRenderer
import ch.tutteli.atrium.reporting.prerendering.text.impl.*
import ch.tutteli.atrium.reporting.prerendering.text.impl.assertion.*
import ch.tutteli.atrium.reporting.text.*
import ch.tutteli.atrium.reporting.text.impl.*
import ch.tutteli.atrium.reporting.theming.text.*
import ch.tutteli.atrium.reporting.theming.text.impl.*
import ch.tutteli.atrium.reporting.theming.text.impl.DefaultTextIconStyler
import ch.tutteli.atrium.reporting.theming.text.impl.DefaultTextStyler
import ch.tutteli.atrium.reporting.theming.text.impl.DefaultThemeProvider
import ch.tutteli.atrium.reporting.theming.text.impl.MordantBasedUtf8SupportDeterminer
import ch.tutteli.atrium.reporting.translating.*
import com.github.ajalt.mordant.rendering.AnsiLevel
import com.github.ajalt.mordant.terminal.Terminal
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
            //TODO 1.3.0 rewrite to sequence { ... }
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
@OptIn(ExperimentalFeatureInfo::class)
internal object DefaultComponentFactoryContainer : ComponentFactoryContainer by ComponentFactoryContainerImpl(
    components = mapOf(
        Reporter::class createSingletonVia { c ->
            c.build<TextReporter>()
        },
        TextReporter::class createSingletonVia { c ->
            DefaultTextReporter(c.build())
        },
        TextPreRenderController::class createVia { c ->
            DefaultTextPreRenderController(c.buildChained(), c.buildChained(), c.build(), c.build())
        },
        ReportableFilter::class createVia { _ ->
            ReportableFilter.failingProofsAndNonProof()
        },
        TextIconStyler::class createSingletonVia { c ->
            DefaultTextIconStyler(c.build(),c.build())
        },
        TextStyler::class createSingletonVia { c ->
            DefaultTextStyler(c.build())
        },
        TextThemeProvider::class createSingletonVia { c ->
            // TODO 1.3.0 would need to detect if ANSI is supported
            DefaultThemeProvider(c.build())
        },
        TextObjFormatter::class createVia { c ->
            DefaultTextObjFormatter(c.build(), c.build())
        },
        Utf8SupportDeterminer::class createVia { c ->
            MordantBasedUtf8SupportDeterminer(c.build())
        },
        Terminal::class createVia { _ ->
            Terminal(ansiLevel = AnsiLevel.TRUECOLOR)
        },
        MonospaceLengthCalculator::class createVia { _ ->
            StringLengthMonospaceLengthCalculator
        },

        //TODO 2.0.0 remove
        @Suppress("DEPRECATION")
        AssertionFormatterController::class createVia { _ ->
            //TODO 2.0.0 remove
            @Suppress("DEPRECATION")
            ch.tutteli.atrium.reporting.impl.DefaultAssertionFormatterController()
        },
        ObjectFormatter::class createVia { c -> c.build<TextObjectFormatter>() },
        AssertionPairFormatter::class createVia { c -> c.build<TextAssertionPairFormatter>() },
        MethodCallFormatter::class createVia { c -> c.build<TextMethodCallFormatter>() },

        LocaleProvider::class createVia { _ -> UseDefaultLocaleAsPrimary },
        //TODO 2.0.0 remove
        @Suppress("DEPRECATION")
        Translator::class createSingletonVia { c ->
            //TODO 2.0.0 remove
            @Suppress("DEPRECATION")
            UsingDefaultTranslator(c.build<LocaleProvider>().getPrimaryLocale())
        },
        //TODO 2.0.0 remove
        @Suppress("DEPRECATION")
        LocaleOrderDecider::class createVia { _ ->
            //TODO 2.0.0 remove
            @Suppress("DEPRECATION")
            ch.tutteli.atrium.reporting.translating.impl.ResourceBundleInspiredLocaleOrderDecider
        },
        RemoveAtriumFromAtriumError::class createVia { _ -> RemoveAtriumFromAtriumErrorImpl() },
        RemoveRunnerFromAtriumError::class createVia { _ -> RemoveRunnerFromAtriumErrorImpl() },
        AtriumErrorAdjuster::class createSingletonVia { c ->
            MultiAtriumErrorAdjuster(
                c.build<RemoveAtriumFromAtriumError>(),
                c.build<RemoveRunnerFromAtriumError>(),
                emptyList()
            )
        },

        FeatureInfo::class createVia { _ ->
            StackTraceBasedFeatureInfo()
        },

        //TODO 2.0.0 remove
        @Suppress("DEPRECATION")
        //Text specific factories
        AssertionFormatterFacade::class createVia { c ->
            //TODO 2.0.0 remove
            @Suppress("DEPRECATION")
            ch.tutteli.atrium.reporting.impl.AssertionFormatterControllerBasedFacade(c.build()).apply {
                //TODO 2.0.0 remove
                @Suppress("DEPRECATION")
                c.buildChained<TextAssertionFormatterFactory>().forEach { factory ->
                    register { controller -> factory.build(controller) }
                }
            }
        },
        TextObjectFormatter::class createSingletonVia { _ -> DefaultTextObjectFormatter() },
        TextAssertionPairFormatter::class createVia { c ->
            TextAssertionPairFormatter.newSameLine(c.build())
        },
        TextMethodCallFormatter::class createVia { _ -> DefaultTextMethodCallFormatter },
        BulletPointProvider::class createVia { _ -> UsingDefaultBulletPoints }
    ),

    chainedComponents = mapOf(
        TextPreRenderer::class createChainVia sequenceOf(

            { c ->
                @Suppress("DEPRECATION")
                DefaultExplanatoryAssertionTextPreRenderer(c.build())
            },
            { c ->
                @Suppress("DEPRECATION")
                DefaultRepresentationOnlyAssertionTextPreRenderer(c.build())
            },
            { _ ->
                @Suppress("DEPRECATION")
                DefaultDescriptiveAssertionTextPreRenderer()
            },
            { _ ->
                @Suppress("DEPRECATION")
                DefaultExplanatoryAssertionGroupTextPreRenderer()
            },
            { c ->
                @Suppress("DEPRECATION")
                DefaultAssertionGroupTextPreRenderer(c.build())
            },

            // ProofGroup
            { _ -> DefaultInvisibleLikeProofGroupTextPreRenderer() },
            { c -> DefaultFeatureProofGroupTextPreRenderer(c.build()) },
            { _ -> DefaultRootProofGroupTextPreRenderer() },
            { _ -> DefaultFallbackProofGroupWithDesignationTextPreRenderer() },


            // ReportableGroup
            { c -> DefaultDebugGroupTextPreRenderer(c.build()) },
            { c -> DefaultInformationGroupTextPreRenderer(c.build()) },
            { c -> DefaultFailureExplanationGroupTextPreRenderer(c.build()) },
            { _ -> DefaultProofExplanationTextPreRenderer() },
            { _ -> DefaultUsageHintGroupTextPreRenderer() },
            { _ -> DefaultFallbackReportableGroupWithDesignationTextPreRenderer() },


            // Proofs non-group
            { _ -> DefaultSimpleProofTextPreRenderer() },

            // Reportable non-group
            { c -> DefaultRowTextPreRenderer(c.build()) },
            { _ -> DefaultColumnTextPreRenderer() },
            { c -> DefaultRepresentationTextPreRenderer(c.build()) },
            { c -> DefaultTextElementTextPreRenderer(c.build()) },
        ),
        TextDesignationPreRenderer::class createChainVia sequenceOf(
            { c -> DefaultInlineDesignatorPreRenderer(c.build()) }
        ),

        //TODO 2.0.0 remove
        @Suppress("DEPRECATION")
        TextAssertionFormatterFactory::class createChainVia
            sequenceOf(
                { c ->
                    val bulletPoints = c.build<BulletPointProvider>().getBulletPoints()
                    val textAssertionPairFormatter = c.build<TextAssertionPairFormatter>()
                    //TODO 2.0.0 remove
                    @Suppress("DEPRECATION")
                    TextAssertionFormatterFactory { controller ->
                        TextListAssertionGroupFormatter(bulletPoints, controller, textAssertionPairFormatter)
                    }
                },
                { c ->
                    val bulletPoints = c.build<BulletPointProvider>().getBulletPoints()
                    val textAssertionPairFormatter = c.build<TextAssertionPairFormatter>()
                    //TODO 2.0.0 remove
                    @Suppress("DEPRECATION")
                    TextAssertionFormatterFactory { controller ->
                        TextFeatureAssertionGroupFormatter(bulletPoints, controller, textAssertionPairFormatter)
                    }
                },
                { c ->
                    val bulletPoints = c.build<BulletPointProvider>().getBulletPoints()
                    //TODO 2.0.0 remove
                    @Suppress("DEPRECATION")
                    TextAssertionFormatterFactory { controller ->
                        TextExplanatoryAssertionGroupFormatter(bulletPoints, controller)
                    }
                },
                { c ->
                    val bulletPoints = c.build<BulletPointProvider>().getBulletPoints()
                    val textAssertionPairFormatter = c.build<TextAssertionPairFormatter>()
                    //TODO 2.0.0 remove
                    @Suppress("DEPRECATION")
                    TextAssertionFormatterFactory { controller ->
                        TextSummaryAssertionGroupFormatter(bulletPoints, controller, textAssertionPairFormatter)
                    }
                },
                { c ->
                    val bulletPoints = c.build<BulletPointProvider>().getBulletPoints()
                    val textAssertionPairFormatter = c.build<TextAssertionPairFormatter>()
                    //TODO 2.0.0 remove
                    @Suppress("DEPRECATION")
                    TextAssertionFormatterFactory { controller ->
                        TextGroupingAssertionGroupFormatter(bulletPoints, controller, textAssertionPairFormatter)
                    }
                },
                { c ->
                    val objectFormatter = c.build<TextObjectFormatter>()
                    val bulletPoints = c.build<BulletPointProvider>().getBulletPoints()
                    val textAssertionPairFormatter = c.build<TextAssertionPairFormatter>()
                    //TODO 2.0.0 remove
                    @Suppress("DEPRECATION")
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
