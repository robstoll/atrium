(function (root, factory) {
  if (typeof define === 'function' && define.amd)
    define(['exports', 'kotlin', 'atrium-core-api-js', 'atrium-domain-api-js', 'atrium-translations-js', 'kbox-js'], factory);
  else if (typeof exports === 'object')
    factory(module.exports, require('kotlin'), require('atrium-core-api-js'), require('atrium-domain-api-js'), require('atrium-translations-js'), require('kbox-js'));
  else {
    if (typeof kotlin === 'undefined') {
      throw new Error("Error loading module 'atrium-domain-builders-js'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'atrium-domain-builders-js'.");
    }
    if (typeof this['atrium-core-api-js'] === 'undefined') {
      throw new Error("Error loading module 'atrium-domain-builders-js'. Its dependency 'atrium-core-api-js' was not found. Please, check whether 'atrium-core-api-js' is loaded prior to 'atrium-domain-builders-js'.");
    }
    if (typeof this['atrium-domain-api-js'] === 'undefined') {
      throw new Error("Error loading module 'atrium-domain-builders-js'. Its dependency 'atrium-domain-api-js' was not found. Please, check whether 'atrium-domain-api-js' is loaded prior to 'atrium-domain-builders-js'.");
    }
    if (typeof this['atrium-translations-js'] === 'undefined') {
      throw new Error("Error loading module 'atrium-domain-builders-js'. Its dependency 'atrium-translations-js' was not found. Please, check whether 'atrium-translations-js' is loaded prior to 'atrium-domain-builders-js'.");
    }
    if (typeof this['kbox-js'] === 'undefined') {
      throw new Error("Error loading module 'atrium-domain-builders-js'. Its dependency 'kbox-js' was not found. Please, check whether 'kbox-js' is loaded prior to 'atrium-domain-builders-js'.");
    }
    root['atrium-domain-builders-js'] = factory(typeof this['atrium-domain-builders-js'] === 'undefined' ? {} : this['atrium-domain-builders-js'], kotlin, this['atrium-core-api-js'], this['atrium-domain-api-js'], this['atrium-translations-js'], this['kbox-js']);
  }
}(this, function (_, Kotlin, $module$atrium_core_api_js, $module$atrium_domain_api_js, $module$atrium_translations_js, $module$kbox_js) {
  'use strict';
  var $$importsForInline$$ = _.$$importsForInline$$ || (_.$$importsForInline$$ = {});
  var defineInlineFunction = Kotlin.defineInlineFunction;
  var wrapFunction = Kotlin.wrapFunction;
  var Kind_OBJECT = Kotlin.Kind.OBJECT;
  var changers = $module$atrium_domain_api_js.ch.tutteli.atrium.domain.creating.changers;
  var Kind_INTERFACE = Kotlin.Kind.INTERFACE;
  var CollectionAssertions = $module$atrium_domain_api_js.ch.tutteli.atrium.domain.creating.CollectionAssertions;
  var ComparableAssertions = $module$atrium_domain_api_js.ch.tutteli.atrium.domain.creating.ComparableAssertions;
  var FeatureAssertions = $module$atrium_domain_api_js.ch.tutteli.atrium.domain.creating.FeatureAssertions;
  var creating = $module$atrium_domain_api_js.ch.tutteli.atrium.domain.creating;
  var ListAssertions = $module$atrium_domain_api_js.ch.tutteli.atrium.domain.creating.ListAssertions;
  var MapAssertions = $module$atrium_domain_api_js.ch.tutteli.atrium.domain.creating.MapAssertions;
  var MapEntryAssertions = $module$atrium_domain_api_js.ch.tutteli.atrium.domain.creating.MapEntryAssertions;
  var getCallableRef = Kotlin.getCallableRef;
  var core = $module$atrium_core_api_js.ch.tutteli.atrium.core;
  var ErrorMessages = $module$atrium_translations_js.ch.tutteli.atrium.translations.ErrorMessages;
  var RawString = $module$atrium_core_api_js.ch.tutteli.atrium.reporting.RawString;
  var MetaFeature = $module$atrium_domain_api_js.ch.tutteli.atrium.domain.creating.MetaFeature;
  var Untranslatable = $module$atrium_core_api_js.ch.tutteli.atrium.reporting.translating.Untranslatable;
  var Some = $module$atrium_core_api_js.ch.tutteli.atrium.core.Some;
  var NewFeatureAssertions = $module$atrium_domain_api_js.ch.tutteli.atrium.domain.creating.NewFeatureAssertions;
  var Kind_CLASS = Kotlin.Kind.CLASS;
  var MetaFeature_init = $module$atrium_domain_api_js.ch.tutteli.atrium.domain.creating.MetaFeature_init_umlfku$;
  var equals = Kotlin.equals;
  var PairAssertions = $module$atrium_domain_api_js.ch.tutteli.atrium.domain.creating.PairAssertions;
  var Exception_init = Kotlin.kotlin.Exception_init_pdl1vj$;
  var Exception = Kotlin.kotlin.Exception;
  var AnyAssertions = $module$atrium_domain_api_js.ch.tutteli.atrium.domain.creating.AnyAssertions;
  var AnyTypeTransformationAssertions = $module$atrium_domain_api_js.ch.tutteli.atrium.domain.creating.any.typetransformation.creators.AnyTypeTransformationAssertions;
  var FailureHandlerFactory = $module$atrium_domain_api_js.ch.tutteli.atrium.domain.creating.any.typetransformation.failurehandlers.FailureHandlerFactory;
  var Expect = $module$atrium_core_api_js.ch.tutteli.atrium.creating.Expect;
  var AssertionPlant = $module$atrium_core_api_js.ch.tutteli.atrium.creating.AssertionPlant;
  var IllegalStateException_init = Kotlin.kotlin.IllegalStateException_init_pdl1vj$;
  var LazyRepresentation = $module$atrium_core_api_js.ch.tutteli.atrium.reporting.LazyRepresentation;
  var DescriptionAnyAssertion = $module$atrium_translations_js.ch.tutteli.atrium.translations.DescriptionAnyAssertion;
  var cast = $module$atrium_core_api_js.ch.tutteli.atrium.core.polyfills.cast_o6trj3$;
  var ExtractedFeaturePostStep = $module$atrium_domain_api_js.ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep;
  var get_invisibleGroup = $module$atrium_core_api_js.ch.tutteli.atrium.assertions.builders.get_invisibleGroup_5juyqv$;
  var SubjectChanger$FailureHandler = $module$atrium_domain_api_js.ch.tutteli.atrium.domain.creating.changers.SubjectChanger.FailureHandler;
  var builders = $module$atrium_core_api_js.ch.tutteli.atrium.assertions.builders;
  var ChangedSubjectPostStep = $module$atrium_domain_api_js.ch.tutteli.atrium.domain.creating.changers.ChangedSubjectPostStep;
  var CharSequenceAssertions = $module$atrium_domain_api_js.ch.tutteli.atrium.domain.creating.CharSequenceAssertions;
  var CharSequenceContainsAssertions = $module$atrium_domain_api_js.ch.tutteli.atrium.domain.creating.charsequence.contains.creators.CharSequenceContainsAssertions;
  var SearchBehaviourFactory = $module$atrium_domain_api_js.ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.SearchBehaviourFactory;
  var checkers = $module$atrium_domain_api_js.ch.tutteli.atrium.domain.creating.charsequence.contains.checkers;
  var listOf = Kotlin.kotlin.collections.listOf_mh5how$;
  var listOf_0 = Kotlin.kotlin.collections.listOf_i5x0yv$;
  var copyToArray = Kotlin.kotlin.collections.copyToArray;
  var CharSequenceContains$CheckerOption = $module$atrium_domain_api_js.ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains.CheckerOption;
  var AssertionCollector = $module$atrium_domain_api_js.ch.tutteli.atrium.domain.creating.collectors.AssertionCollector;
  var collectors = $module$atrium_domain_api_js.ch.tutteli.atrium.domain.creating.collectors;
  var checkers_0 = $module$atrium_domain_api_js.ch.tutteli.atrium.domain.creating.iterable.contains.checkers;
  var IterableContains$CheckerOption = $module$atrium_domain_api_js.ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains.CheckerOption;
  var IterableAssertions = $module$atrium_domain_api_js.ch.tutteli.atrium.domain.creating.IterableAssertions;
  var IterableContainsAssertions = $module$atrium_domain_api_js.ch.tutteli.atrium.domain.creating.iterable.contains.creators.IterableContainsAssertions;
  var SearchBehaviourFactory_0 = $module$atrium_domain_api_js.ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.SearchBehaviourFactory;
  var ThrowableAssertions = $module$atrium_domain_api_js.ch.tutteli.atrium.domain.creating.ThrowableAssertions;
  var ThrowableThrownAssertions = $module$atrium_domain_api_js.ch.tutteli.atrium.domain.creating.throwable.thrown.creators.ThrowableThrownAssertions;
  var AbsentThrowableMessageProviderFactory = $module$atrium_domain_api_js.ch.tutteli.atrium.domain.creating.throwable.thrown.providers.AbsentThrowableMessageProviderFactory;
  var reporting = $module$atrium_core_api_js.ch.tutteli.atrium.reporting;
  var PlantHasNoSubjectException_init = $module$atrium_core_api_js.ch.tutteli.atrium.creating.PlantHasNoSubjectException_init;
  var Unit = Kotlin.kotlin.Unit;
  var Annotation = Kotlin.kotlin.Annotation;
  var getDefaultLocale = $module$atrium_core_api_js.ch.tutteli.atrium.reporting.translating.getDefaultLocale;
  var first = Kotlin.kotlin.collections.first_2p1efm$;
  var drop = Kotlin.kotlin.collections.drop_ba2ldo$;
  var IllegalArgumentException_init = Kotlin.kotlin.IllegalArgumentException_init_pdl1vj$;
  var ReporterFactory = $module$atrium_core_api_js.ch.tutteli.atrium.reporting.ReporterFactory;
  var ArrayList_init = Kotlin.kotlin.collections.ArrayList_init_287e2$;
  var toMap = Kotlin.kotlin.collections.toMap_v2dak7$;
  var asIterable = Kotlin.kotlin.collections.asIterable_us0mfu$;
  var toList = Kotlin.kotlin.collections.toList_us0mfu$;
  var ReportingAssertionContainer$AssertionCheckerDecorator = $module$atrium_core_api_js.ch.tutteli.atrium.creating.ReportingAssertionContainer.AssertionCheckerDecorator;
  var varargToList = $module$kbox_js.ch.tutteli.kbox.varargToList_r59i0z$;
  var ArrayList_init_0 = Kotlin.kotlin.collections.ArrayList_init_ww73n8$;
  var toTypedArray = Kotlin.kotlin.collections.toTypedArray_964n91$;
  var toBoxedChar = Kotlin.toBoxedChar;
  var toTypedArray_0 = Kotlin.kotlin.collections.toTypedArray_355ntz$;
  var toTypedArray_1 = Kotlin.kotlin.collections.toTypedArray_i2lc79$;
  var toTypedArray_2 = Kotlin.kotlin.collections.toTypedArray_tmsbgo$;
  var toTypedArray_3 = Kotlin.kotlin.collections.toTypedArray_se6h4x$;
  var toTypedArray_4 = Kotlin.kotlin.collections.toTypedArray_rjqryz$;
  var toTypedArray_5 = Kotlin.kotlin.collections.toTypedArray_bvy38s$;
  var toTypedArray_6 = Kotlin.kotlin.collections.toTypedArray_l1lu5t$;
  var FloatingPointAssertions = $module$atrium_domain_api_js.ch.tutteli.atrium.domain.creating.FloatingPointAssertions;
  var getKClass = Kotlin.getKClass;
  var registerService = $module$atrium_core_api_js.ch.tutteli.atrium.core.polyfills.registerService_uj6y0s$;
  var UsingDefaultTranslator = $module$atrium_core_api_js.ch.tutteli.atrium.reporting.translating.UsingDefaultTranslator;
  var NotImplementedError_init = Kotlin.kotlin.NotImplementedError;
  PleaseUseReplacementException.prototype = Object.create(Exception.prototype);
  PleaseUseReplacementException.prototype.constructor = PleaseUseReplacementException;
  function AssertImpl() {
    AssertImpl_instance = this;
  }
  Object.defineProperty(AssertImpl.prototype, 'builder', {
    get: defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.AssertImpl.get_builder', wrapFunction(function () {
      var builders = _.$$importsForInline$$['atrium-core-api-js'].ch.tutteli.atrium.assertions.builders;
      return function () {
        return builders.assertionBuilder;
      };
    }))
  });
  Object.defineProperty(AssertImpl.prototype, 'collector', {
    get: defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.AssertImpl.get_collector', wrapFunction(function () {
      var collectors = _.ch.tutteli.atrium.domain.builders.creating.collectors;
      return function () {
        return collectors.AssertionCollectorBuilder;
      };
    }))
  });
  Object.defineProperty(AssertImpl.prototype, 'coreFactory', {
    get: defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.AssertImpl.get_coreFactory', wrapFunction(function () {
      var core = _.$$importsForInline$$['atrium-core-api-js'].ch.tutteli.atrium.core;
      return function () {
        return core.coreFactory;
      };
    }))
  });
  Object.defineProperty(AssertImpl.prototype, 'any', {
    get: defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.AssertImpl.get_any', wrapFunction(function () {
      var creating = _.ch.tutteli.atrium.domain.builders.creating;
      return function () {
        return creating.AnyAssertionsBuilder;
      };
    }))
  });
  Object.defineProperty(AssertImpl.prototype, 'charSequence', {
    get: defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.AssertImpl.get_charSequence', wrapFunction(function () {
      var creating = _.ch.tutteli.atrium.domain.builders.creating;
      return function () {
        return creating.CharSequenceAssertionsBuilder;
      };
    }))
  });
  Object.defineProperty(AssertImpl.prototype, 'collection', {
    get: defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.AssertImpl.get_collection', wrapFunction(function () {
      var creating = _.ch.tutteli.atrium.domain.builders.creating;
      return function () {
        return creating.CollectionAssertionsBuilder;
      };
    }))
  });
  Object.defineProperty(AssertImpl.prototype, 'comparable', {
    get: defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.AssertImpl.get_comparable', wrapFunction(function () {
      var creating = _.ch.tutteli.atrium.domain.builders.creating;
      return function () {
        return creating.ComparableAssertionsBuilder;
      };
    }))
  });
  Object.defineProperty(AssertImpl.prototype, 'feature', {
    get: defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.AssertImpl.get_feature', wrapFunction(function () {
      var creating = _.ch.tutteli.atrium.domain.builders.creating;
      return function () {
        return creating.FeatureAssertionsBuilder;
      };
    }))
  });
  Object.defineProperty(AssertImpl.prototype, 'floatingPoint', {
    get: defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.AssertImpl.get_floatingPoint', wrapFunction(function () {
      var creating = _.ch.tutteli.atrium.domain.builders.creating;
      return function () {
        return creating.FloatingPointAssertionsBuilder;
      };
    }))
  });
  Object.defineProperty(AssertImpl.prototype, 'iterable', {
    get: defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.AssertImpl.get_iterable', wrapFunction(function () {
      var creating = _.ch.tutteli.atrium.domain.builders.creating;
      return function () {
        return creating.IterableAssertionsBuilder;
      };
    }))
  });
  Object.defineProperty(AssertImpl.prototype, 'list', {
    get: defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.AssertImpl.get_list', wrapFunction(function () {
      var creating = _.ch.tutteli.atrium.domain.builders.creating;
      return function () {
        return creating.ListAssertionsBuilder;
      };
    }))
  });
  Object.defineProperty(AssertImpl.prototype, 'map', {
    get: defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.AssertImpl.get_map', wrapFunction(function () {
      var creating = _.ch.tutteli.atrium.domain.builders.creating;
      return function () {
        return creating.MapAssertionsBuilder;
      };
    }))
  });
  Object.defineProperty(AssertImpl.prototype, 'pair', {
    get: defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.AssertImpl.get_pair', wrapFunction(function () {
      var creating = _.ch.tutteli.atrium.domain.builders.creating;
      return function () {
        return creating.PairAssertionsBuilder;
      };
    }))
  });
  Object.defineProperty(AssertImpl.prototype, 'throwable', {
    get: defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.AssertImpl.get_throwable', wrapFunction(function () {
      var creating = _.ch.tutteli.atrium.domain.builders.creating;
      return function () {
        return creating.ThrowableAssertionsBuilder;
      };
    }))
  });
  AssertImpl.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'AssertImpl',
    interfaces: [AssertImplCommon]
  };
  var AssertImpl_instance = null;
  function AssertImpl_getInstance() {
    if (AssertImpl_instance === null) {
      new AssertImpl();
    }
    return AssertImpl_instance;
  }
  function AssertImplCommon() {
  }
  function AssertImplCommon$changeSubject$lambda(closure$subjectProvider) {
    return function (it) {
      return closure$subjectProvider();
    };
  }
  AssertImplCommon.prototype.changeSubject_y31nfh$ = function (originalPlant, subjectProvider) {
    return changers.subjectChanger.unreportedToAssert_ael2fa$(originalPlant, AssertImplCommon$changeSubject$lambda(subjectProvider));
  };
  AssertImplCommon.prototype.changeSubject_5p4xxt$ = function (originalAssertionCreator, transformation) {
    return changers.subjectChanger.unreported_sdes1g$(originalAssertionCreator, transformation);
  };
  function AssertImplCommon$changeToNullableSubject$lambda(closure$subjectProvider) {
    return function (it) {
      return closure$subjectProvider();
    };
  }
  AssertImplCommon.prototype.changeToNullableSubject_j73se8$ = function (originalPlant, subjectProvider) {
    return changers.subjectChanger.unreportedNullableToAssert_n8lbbx$(originalPlant, AssertImplCommon$changeToNullableSubject$lambda(subjectProvider));
  };
  AssertImplCommon.prototype.changeToNullableSubject_5p4xxt$ = function (originalAssertionCreator, transformation) {
    return this.changeSubject_5p4xxt$(originalAssertionCreator, transformation);
  };
  AssertImplCommon.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'AssertImplCommon',
    interfaces: []
  };
  function ExpectImpl() {
    ExpectImpl_instance = this;
  }
  Object.defineProperty(ExpectImpl.prototype, 'builder', {
    get: defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.ExpectImpl.get_builder', wrapFunction(function () {
      var builders = _.$$importsForInline$$['atrium-core-api-js'].ch.tutteli.atrium.assertions.builders;
      return function () {
        return builders.assertionBuilder;
      };
    }))
  });
  ExpectImpl.prototype.changeSubject_gh40re$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.ExpectImpl.changeSubject_gh40re$', wrapFunction(function () {
    var SubjectChangerBuilder = _.ch.tutteli.atrium.domain.builders.creating.changers.SubjectChangerBuilder;
    return function (originalAssertionContainer) {
      return SubjectChangerBuilder.Companion.create_gh40re$(originalAssertionContainer);
    };
  }));
  ExpectImpl.prototype.changeSubject_dcyqpd$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.ExpectImpl.changeSubject_dcyqpd$', wrapFunction(function () {
    var SubjectChangerBuilder = _.ch.tutteli.atrium.domain.builders.creating.changers.SubjectChangerBuilder;
    return function (originalAssertionContainer) {
      return SubjectChangerBuilder.Companion.create_dcyqpd$(originalAssertionContainer);
    };
  }));
  Object.defineProperty(ExpectImpl.prototype, 'collector', {
    get: defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.ExpectImpl.get_collector', wrapFunction(function () {
      var collectors = _.ch.tutteli.atrium.domain.builders.creating.collectors;
      return function () {
        return collectors.AssertionCollectorBuilder;
      };
    }))
  });
  Object.defineProperty(ExpectImpl.prototype, 'any', {
    get: defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.ExpectImpl.get_any', wrapFunction(function () {
      var creating = _.ch.tutteli.atrium.domain.builders.creating;
      return function () {
        return creating.AnyAssertionsBuilder;
      };
    }))
  });
  Object.defineProperty(ExpectImpl.prototype, 'charSequence', {
    get: defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.ExpectImpl.get_charSequence', wrapFunction(function () {
      var creating = _.ch.tutteli.atrium.domain.builders.creating;
      return function () {
        return creating.CharSequenceAssertionsBuilder;
      };
    }))
  });
  Object.defineProperty(ExpectImpl.prototype, 'collection', {
    get: defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.ExpectImpl.get_collection', wrapFunction(function () {
      var creating = _.ch.tutteli.atrium.domain.builders.creating;
      return function () {
        return creating.CollectionAssertionsBuilder;
      };
    }))
  });
  Object.defineProperty(ExpectImpl.prototype, 'comparable', {
    get: defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.ExpectImpl.get_comparable', wrapFunction(function () {
      var creating = _.ch.tutteli.atrium.domain.builders.creating;
      return function () {
        return creating.ComparableAssertionsBuilder;
      };
    }))
  });
  Object.defineProperty(ExpectImpl.prototype, 'feature', {
    get: defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.ExpectImpl.get_feature', wrapFunction(function () {
      var creating = _.ch.tutteli.atrium.domain.builders.creating;
      return function () {
        return creating.NewFeatureAssertionsBuilder;
      };
    }))
  });
  Object.defineProperty(ExpectImpl.prototype, 'floatingPoint', {
    get: defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.ExpectImpl.get_floatingPoint', wrapFunction(function () {
      var creating = _.ch.tutteli.atrium.domain.builders.creating;
      return function () {
        return creating.FloatingPointAssertionsBuilder;
      };
    }))
  });
  Object.defineProperty(ExpectImpl.prototype, 'iterable', {
    get: defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.ExpectImpl.get_iterable', wrapFunction(function () {
      var creating = _.ch.tutteli.atrium.domain.builders.creating;
      return function () {
        return creating.IterableAssertionsBuilder;
      };
    }))
  });
  Object.defineProperty(ExpectImpl.prototype, 'list', {
    get: function () {
      return ListAssertionsBuilder_getInstance();
    }
  });
  Object.defineProperty(ExpectImpl.prototype, 'map', {
    get: defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.ExpectImpl.get_map', wrapFunction(function () {
      var creating = _.ch.tutteli.atrium.domain.builders.creating;
      return function () {
        return creating.MapAssertionsBuilder;
      };
    }))
  });
  Object.defineProperty(ExpectImpl.prototype, 'pair', {
    get: defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.ExpectImpl.get_pair', wrapFunction(function () {
      var creating = _.ch.tutteli.atrium.domain.builders.creating;
      return function () {
        return creating.PairAssertionsBuilder;
      };
    }))
  });
  Object.defineProperty(ExpectImpl.prototype, 'throwable', {
    get: defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.ExpectImpl.get_throwable', wrapFunction(function () {
      var creating = _.ch.tutteli.atrium.domain.builders.creating;
      return function () {
        return creating.ThrowableAssertionsBuilder;
      };
    }))
  });
  ExpectImpl.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'ExpectImpl',
    interfaces: []
  };
  var ExpectImpl_instance = null;
  function ExpectImpl_getInstance() {
    if (ExpectImpl_instance === null) {
      new ExpectImpl();
    }
    return ExpectImpl_instance;
  }
  function CollectionAssertionsBuilder() {
    CollectionAssertionsBuilder_instance = this;
  }
  CollectionAssertionsBuilder.prototype.isEmpty_dx9xz9$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.CollectionAssertionsBuilder.isEmpty_dx9xz9$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (subjectProvider) {
      return creating.collectionAssertions.isEmpty_dx9xz9$(subjectProvider);
    };
  }));
  CollectionAssertionsBuilder.prototype.isNotEmpty_dx9xz9$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.CollectionAssertionsBuilder.isNotEmpty_dx9xz9$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (subjectProvider) {
      return creating.collectionAssertions.isNotEmpty_dx9xz9$(subjectProvider);
    };
  }));
  CollectionAssertionsBuilder.prototype.size_sqikts$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.CollectionAssertionsBuilder.size_sqikts$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (assertionContainer) {
      return creating.collectionAssertions.size_sqikts$(assertionContainer);
    };
  }));
  CollectionAssertionsBuilder.prototype.hasSize_s5wgvh$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.CollectionAssertionsBuilder.hasSize_s5wgvh$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, size) {
      return creating.collectionAssertions.hasSize_s5wgvh$(plant, size);
    };
  }));
  CollectionAssertionsBuilder.prototype.size_xupd9b$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.CollectionAssertionsBuilder.size_xupd9b$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, assertionCreator) {
      return creating.collectionAssertions.size_xupd9b$(plant, assertionCreator);
    };
  }));
  CollectionAssertionsBuilder.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'CollectionAssertionsBuilder',
    interfaces: [CollectionAssertions]
  };
  var CollectionAssertionsBuilder_instance = null;
  function CollectionAssertionsBuilder_getInstance() {
    if (CollectionAssertionsBuilder_instance === null) {
      new CollectionAssertionsBuilder();
    }
    return CollectionAssertionsBuilder_instance;
  }
  function ComparableAssertionsBuilder() {
    ComparableAssertionsBuilder_instance = this;
  }
  ComparableAssertionsBuilder.prototype.isLessThan_hz4bm$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.ComparableAssertionsBuilder.isLessThan_hz4bm$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (subjectProvider, expected) {
      return creating.comparableAssertions.isLessThan_hz4bm$(subjectProvider, expected);
    };
  }));
  ComparableAssertionsBuilder.prototype.isLessOrEquals_hz4bm$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.ComparableAssertionsBuilder.isLessOrEquals_hz4bm$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (subjectProvider, expected) {
      return creating.comparableAssertions.isLessOrEquals_hz4bm$(subjectProvider, expected);
    };
  }));
  ComparableAssertionsBuilder.prototype.isGreaterThan_hz4bm$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.ComparableAssertionsBuilder.isGreaterThan_hz4bm$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (subjectProvider, expected) {
      return creating.comparableAssertions.isGreaterThan_hz4bm$(subjectProvider, expected);
    };
  }));
  ComparableAssertionsBuilder.prototype.isGreaterOrEquals_hz4bm$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.ComparableAssertionsBuilder.isGreaterOrEquals_hz4bm$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (subjectProvider, expected) {
      return creating.comparableAssertions.isGreaterOrEquals_hz4bm$(subjectProvider, expected);
    };
  }));
  ComparableAssertionsBuilder.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'ComparableAssertionsBuilder',
    interfaces: [ComparableAssertions]
  };
  var ComparableAssertionsBuilder_instance = null;
  function ComparableAssertionsBuilder_getInstance() {
    if (ComparableAssertionsBuilder_instance === null) {
      new ComparableAssertionsBuilder();
    }
    return ComparableAssertionsBuilder_instance;
  }
  function FeatureAssertionsBuilder() {
    FeatureAssertionsBuilder_instance = this;
  }
  Object.defineProperty(FeatureAssertionsBuilder.prototype, 'extractor', {
    get: defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.get_extractor', wrapFunction(function () {
      var FeatureExtractor = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating.feature.extract.FeatureExtractor;
      return function () {
        return FeatureExtractor.Companion.builder;
      };
    }))
  });
  FeatureAssertionsBuilder.prototype.property_gavxn3$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.property_gavxn3$', wrapFunction(function () {
    var Untranslatable_init = _.$$importsForInline$$['atrium-core-api-js'].ch.tutteli.atrium.reporting.translating.Untranslatable;
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    function FeatureAssertionsBuilder$property$lambda(closure$property, closure$plant) {
      return function () {
        return closure$property(closure$plant.subject);
      };
    }
    return function (plant, property) {
      return creating.featureAssertions.property_v1jh8n$(plant, FeatureAssertionsBuilder$property$lambda(property, plant), new Untranslatable_init(property.callableName));
    };
  }));
  FeatureAssertionsBuilder.prototype.property_zb0lfa$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.property_zb0lfa$', wrapFunction(function () {
    var Untranslatable_init = _.$$importsForInline$$['atrium-core-api-js'].ch.tutteli.atrium.reporting.translating.Untranslatable;
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, property) {
      return creating.featureAssertions.property_v1jh8n$(plant, property, new Untranslatable_init(property.callableName));
    };
  }));
  FeatureAssertionsBuilder.prototype.property_v1jh8n$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.property_v1jh8n$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, subjectProvider, name) {
      return creating.featureAssertions.property_v1jh8n$(plant, subjectProvider, name);
    };
  }));
  FeatureAssertionsBuilder.prototype.property_lt1mpt$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.property_lt1mpt$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, subjectProvider, representationProvider, name) {
      return creating.featureAssertions.property_lt1mpt$(plant, subjectProvider, representationProvider, name);
    };
  }));
  FeatureAssertionsBuilder.prototype.property_962oci$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.property_962oci$', wrapFunction(function () {
    var Untranslatable_init = _.$$importsForInline$$['atrium-core-api-js'].ch.tutteli.atrium.reporting.translating.Untranslatable;
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    function FeatureAssertionsBuilder$property$lambda(closure$property, closure$plant) {
      return function () {
        return closure$property(closure$plant.subject);
      };
    }
    return function (plant, property, assertionCreator) {
      return creating.featureAssertions.property_v1jh8n$(plant, FeatureAssertionsBuilder$property$lambda(property, plant), new Untranslatable_init(property.callableName)).addAssertionsCreatedBy_rnr939$(assertionCreator);
    };
  }));
  FeatureAssertionsBuilder.prototype.property_ia71rt$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.property_ia71rt$', wrapFunction(function () {
    var Untranslatable_init = _.$$importsForInline$$['atrium-core-api-js'].ch.tutteli.atrium.reporting.translating.Untranslatable;
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, property, assertionCreator) {
      return creating.featureAssertions.property_v1jh8n$(plant, property, new Untranslatable_init(property.callableName)).addAssertionsCreatedBy_rnr939$(assertionCreator);
    };
  }));
  FeatureAssertionsBuilder.prototype.property_czjfoo$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.property_czjfoo$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, subjectProvider, name, assertionCreator) {
      return creating.featureAssertions.property_v1jh8n$(plant, subjectProvider, name).addAssertionsCreatedBy_rnr939$(assertionCreator);
    };
  }));
  FeatureAssertionsBuilder.prototype.property_c5edn4$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.property_c5edn4$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, subjectProvider, representationProvider, name, assertionCreator) {
      return creating.featureAssertions.property_lt1mpt$(plant, subjectProvider, representationProvider, name).addAssertionsCreatedBy_rnr939$(assertionCreator);
    };
  }));
  FeatureAssertionsBuilder.prototype.property_xf8vos$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.property_xf8vos$', wrapFunction(function () {
    var Untranslatable_init = _.$$importsForInline$$['atrium-core-api-js'].ch.tutteli.atrium.reporting.translating.Untranslatable;
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    function FeatureAssertionsBuilder$property$lambda(closure$property, closure$plant) {
      return function () {
        return closure$property(closure$plant.subject);
      };
    }
    return function (plant, property) {
      var l = FeatureAssertionsBuilder$property$lambda(property, plant);
      return creating.featureAssertions.property_j4srze$(plant, l, new Untranslatable_init(property.callableName));
    };
  }));
  FeatureAssertionsBuilder.prototype.property_c4d1ev$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.property_c4d1ev$', wrapFunction(function () {
    var Untranslatable_init = _.$$importsForInline$$['atrium-core-api-js'].ch.tutteli.atrium.reporting.translating.Untranslatable;
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, property) {
      return creating.featureAssertions.property_j4srze$(plant, property, new Untranslatable_init(property.callableName));
    };
  }));
  FeatureAssertionsBuilder.prototype.property_j4srze$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.property_j4srze$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, subjectProvider, name) {
      return creating.featureAssertions.property_j4srze$(plant, subjectProvider, name);
    };
  }));
  FeatureAssertionsBuilder.prototype.property_oeizz2$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.property_oeizz2$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, subjectProvider, representationProvider, name) {
      return creating.featureAssertions.property_oeizz2$(plant, subjectProvider, representationProvider, name);
    };
  }));
  FeatureAssertionsBuilder.prototype.returnValueOf0_zan685$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf0_zan685$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    function FeatureAssertionsBuilder$returnValueOf0$lambda(closure$method, closure$plant) {
      return function () {
        return closure$method(closure$plant.subject);
      };
    }
    return function (plant, method) {
      return creating.featureAssertions.returnValueOf0_6ilun3$(plant, FeatureAssertionsBuilder$returnValueOf0$lambda(method, plant), method.callableName);
    };
  }));
  FeatureAssertionsBuilder.prototype.returnValueOf0_im9o2b$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf0_im9o2b$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, method) {
      return creating.featureAssertions.returnValueOf0_6ilun3$(plant, method, method.callableName);
    };
  }));
  FeatureAssertionsBuilder.prototype.returnValueOf0_6ilun3$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf0_6ilun3$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, method, name) {
      return creating.featureAssertions.returnValueOf0_6ilun3$(plant, method, name);
    };
  }));
  FeatureAssertionsBuilder.prototype.returnValueOf0_254rx5$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf0_254rx5$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, method, representationProvider, name) {
      return creating.featureAssertions.returnValueOf0_254rx5$(plant, method, representationProvider, name);
    };
  }));
  FeatureAssertionsBuilder.prototype.returnValueOf0_sdreyc$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf0_sdreyc$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    function FeatureAssertionsBuilder$returnValueOf0$lambda(closure$method, closure$plant) {
      return function () {
        return closure$method(closure$plant.subject);
      };
    }
    return function (plant, method, assertionCreator) {
      return creating.featureAssertions.returnValueOf0_6ilun3$(plant, FeatureAssertionsBuilder$returnValueOf0$lambda(method, plant), method.callableName).addAssertionsCreatedBy_rnr939$(assertionCreator);
    };
  }));
  FeatureAssertionsBuilder.prototype.returnValueOf0_ye9v6k$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf0_ye9v6k$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, method, assertionCreator) {
      return creating.featureAssertions.returnValueOf0_6ilun3$(plant, method, method.callableName).addAssertionsCreatedBy_rnr939$(assertionCreator);
    };
  }));
  FeatureAssertionsBuilder.prototype.returnValueOf0_fbv0ls$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf0_fbv0ls$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, method, name, assertionCreator) {
      return creating.featureAssertions.returnValueOf0_6ilun3$(plant, method, name).addAssertionsCreatedBy_rnr939$(assertionCreator);
    };
  }));
  FeatureAssertionsBuilder.prototype.returnValueOf0_pwktqg$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf0_pwktqg$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, method, representationProvider, name, assertionCreator) {
      return creating.featureAssertions.returnValueOf0_254rx5$(plant, method, representationProvider, name).addAssertionsCreatedBy_rnr939$(assertionCreator);
    };
  }));
  FeatureAssertionsBuilder.prototype.returnValueOf0_evl28y$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf0_evl28y$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    function FeatureAssertionsBuilder$returnValueOf0$lambda(closure$method, closure$plant) {
      return function () {
        return closure$method(closure$plant.subject);
      };
    }
    return function (plant, method) {
      var l = FeatureAssertionsBuilder$returnValueOf0$lambda(method, plant);
      return creating.featureAssertions.returnValueOf0_awp556$(plant, l, l, method.callableName);
    };
  }));
  FeatureAssertionsBuilder.prototype.returnValueOf0_bx220m$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf0_bx220m$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, method) {
      return creating.featureAssertions.returnValueOf0_ux3z7m$(plant, method, method.callableName);
    };
  }));
  FeatureAssertionsBuilder.prototype.returnValueOf0_ux3z7m$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf0_ux3z7m$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, method, name) {
      return creating.featureAssertions.returnValueOf0_ux3z7m$(plant, method, name);
    };
  }));
  FeatureAssertionsBuilder.prototype.returnValueOf0_awp556$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf0_awp556$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, method, representationProvider, name) {
      return creating.featureAssertions.returnValueOf0_awp556$(plant, method, representationProvider, name);
    };
  }));
  FeatureAssertionsBuilder.prototype.returnValueOf1_v5ciks$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf1_v5ciks$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    function FeatureAssertionsBuilder$returnValueOf1$lambda(closure$method, closure$plant) {
      return function (a1) {
        return closure$method(closure$plant.subject, a1);
      };
    }
    return function (plant, method, arg1) {
      return creating.featureAssertions.returnValueOf1_z7b8x6$(plant, FeatureAssertionsBuilder$returnValueOf1$lambda(method, plant), arg1, method.callableName);
    };
  }));
  FeatureAssertionsBuilder.prototype.returnValueOf1_dba4nm$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf1_dba4nm$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, method, arg1) {
      return creating.featureAssertions.returnValueOf1_z7b8x6$(plant, method, arg1, method.callableName);
    };
  }));
  FeatureAssertionsBuilder.prototype.returnValueOf1_z7b8x6$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf1_z7b8x6$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, method, arg1, name) {
      return creating.featureAssertions.returnValueOf1_z7b8x6$(plant, method, arg1, name);
    };
  }));
  FeatureAssertionsBuilder.prototype.returnValueOf1_5sg69g$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf1_5sg69g$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, method, arg1, representationProvider, name) {
      return creating.featureAssertions.returnValueOf1_5sg69g$(plant, method, arg1, representationProvider, name);
    };
  }));
  FeatureAssertionsBuilder.prototype.returnValueOf1_5mrt18$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf1_5mrt18$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    function FeatureAssertionsBuilder$returnValueOf1$lambda(closure$method, closure$plant) {
      return function (a1) {
        return closure$method(closure$plant.subject, a1);
      };
    }
    return function (plant, method, arg1, assertionCreator) {
      return creating.featureAssertions.returnValueOf1_z7b8x6$(plant, FeatureAssertionsBuilder$returnValueOf1$lambda(method, plant), arg1, method.callableName).addAssertionsCreatedBy_rnr939$(assertionCreator);
    };
  }));
  FeatureAssertionsBuilder.prototype.returnValueOf1_fdrubi$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf1_fdrubi$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, method, arg1, assertionCreator) {
      return creating.featureAssertions.returnValueOf1_z7b8x6$(plant, method, arg1, method.callableName).addAssertionsCreatedBy_rnr939$(assertionCreator);
    };
  }));
  FeatureAssertionsBuilder.prototype.returnValueOf1_7z3zbu$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf1_7z3zbu$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, method, arg1, name, assertionCreator) {
      return creating.featureAssertions.returnValueOf1_z7b8x6$(plant, method, arg1, name).addAssertionsCreatedBy_rnr939$(assertionCreator);
    };
  }));
  FeatureAssertionsBuilder.prototype.returnValueOf1_97jvdo$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf1_97jvdo$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, method, arg1, representationProvider, name, assertionCreator) {
      return creating.featureAssertions.returnValueOf1_5sg69g$(plant, method, arg1, representationProvider, name).addAssertionsCreatedBy_rnr939$(assertionCreator);
    };
  }));
  FeatureAssertionsBuilder.prototype.returnValueOf1_sbu78v$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf1_sbu78v$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    function FeatureAssertionsBuilder$returnValueOf1$lambda(closure$method, closure$plant) {
      return function (a1) {
        return closure$method(closure$plant.subject, a1);
      };
    }
    return function (plant, method, arg1) {
      var l = FeatureAssertionsBuilder$returnValueOf1$lambda(method, plant);
      return creating.featureAssertions.returnValueOf1_w21xql$(plant, l, arg1, method.callableName);
    };
  }));
  FeatureAssertionsBuilder.prototype.returnValueOf1_nsxl1n$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf1_nsxl1n$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, method, arg1) {
      return creating.featureAssertions.returnValueOf1_w21xql$(plant, method, arg1, method.callableName);
    };
  }));
  FeatureAssertionsBuilder.prototype.returnValueOf1_w21xql$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf1_w21xql$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, method, arg1, name) {
      return creating.featureAssertions.returnValueOf1_w21xql$(plant, method, arg1, name);
    };
  }));
  FeatureAssertionsBuilder.prototype.returnValueOf1_jkim6v$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf1_jkim6v$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, method, arg1, representationProvider, name) {
      return creating.featureAssertions.returnValueOf1_jkim6v$(plant, method, arg1, representationProvider, name);
    };
  }));
  FeatureAssertionsBuilder.prototype.returnValueOf2_7u6ze0$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf2_7u6ze0$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    function FeatureAssertionsBuilder$returnValueOf2$lambda(closure$method, closure$plant) {
      return function (a1, a2) {
        return closure$method(closure$plant.subject, a1, a2);
      };
    }
    return function (plant, method, arg1, arg2) {
      return creating.featureAssertions.returnValueOf2_cb7pf4$(plant, FeatureAssertionsBuilder$returnValueOf2$lambda(method, plant), arg1, arg2, method.callableName);
    };
  }));
  FeatureAssertionsBuilder.prototype.returnValueOf2_w7g91g$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf2_w7g91g$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, method, arg1, arg2) {
      return creating.featureAssertions.returnValueOf2_cb7pf4$(plant, method, arg1, arg2, method.callableName);
    };
  }));
  FeatureAssertionsBuilder.prototype.returnValueOf2_cb7pf4$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf2_cb7pf4$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, method, arg1, arg2, name) {
      return creating.featureAssertions.returnValueOf2_cb7pf4$(plant, method, arg1, arg2, name);
    };
  }));
  FeatureAssertionsBuilder.prototype.returnValueOf2_x31pjs$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf2_x31pjs$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, method, arg1, arg2, representationProvider, name) {
      return creating.featureAssertions.returnValueOf2_x31pjs$(plant, method, arg1, arg2, representationProvider, name);
    };
  }));
  FeatureAssertionsBuilder.prototype.returnValueOf2_510ax$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf2_510ax$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    function FeatureAssertionsBuilder$returnValueOf2$lambda(closure$method, closure$plant) {
      return function (a1, a2) {
        return closure$method(closure$plant.subject, a1, a2);
      };
    }
    return function (plant, method, arg1, arg2, assertionCreator) {
      return creating.featureAssertions.returnValueOf2_cb7pf4$(plant, FeatureAssertionsBuilder$returnValueOf2$lambda(method, plant), arg1, arg2, method.callableName).addAssertionsCreatedBy_rnr939$(assertionCreator);
    };
  }));
  FeatureAssertionsBuilder.prototype.returnValueOf2_f5svcd$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf2_f5svcd$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, method, arg1, arg2, assertionCreator) {
      return creating.featureAssertions.returnValueOf2_cb7pf4$(plant, method, arg1, arg2, method.callableName).addAssertionsCreatedBy_rnr939$(assertionCreator);
    };
  }));
  FeatureAssertionsBuilder.prototype.returnValueOf2_12ukgv$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf2_12ukgv$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, method, arg1, arg2, name, assertionCreator) {
      return creating.featureAssertions.returnValueOf2_cb7pf4$(plant, method, arg1, arg2, name).addAssertionsCreatedBy_rnr939$(assertionCreator);
    };
  }));
  FeatureAssertionsBuilder.prototype.returnValueOf2_tf6wp5$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf2_tf6wp5$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, method, arg1, arg2, representationProvider, name, assertionCreator) {
      return creating.featureAssertions.returnValueOf2_x31pjs$(plant, method, arg1, arg2, representationProvider, name).addAssertionsCreatedBy_rnr939$(assertionCreator);
    };
  }));
  FeatureAssertionsBuilder.prototype.returnValueOf2_i4rr2j$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf2_i4rr2j$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    function FeatureAssertionsBuilder$returnValueOf2$lambda(closure$method, closure$plant) {
      return function (a1, a2) {
        return closure$method(closure$plant.subject, a1, a2);
      };
    }
    return function (plant, method, arg1, arg2) {
      var l = FeatureAssertionsBuilder$returnValueOf2$lambda(method, plant);
      return creating.featureAssertions.returnValueOf2_ecu6cz$(plant, l, arg1, arg2, method.callableName);
    };
  }));
  FeatureAssertionsBuilder.prototype.returnValueOf2_xkn7jb$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf2_xkn7jb$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, method, arg1, arg2) {
      return creating.featureAssertions.returnValueOf2_ecu6cz$(plant, method, arg1, arg2, method.callableName);
    };
  }));
  FeatureAssertionsBuilder.prototype.returnValueOf2_ecu6cz$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf2_ecu6cz$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, method, arg1, arg2, name) {
      return creating.featureAssertions.returnValueOf2_ecu6cz$(plant, method, arg1, arg2, name);
    };
  }));
  FeatureAssertionsBuilder.prototype.returnValueOf2_z8pjlh$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf2_z8pjlh$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, method, arg1, arg2, representationProvider, name) {
      return creating.featureAssertions.returnValueOf2_z8pjlh$(plant, method, arg1, arg2, representationProvider, name);
    };
  }));
  FeatureAssertionsBuilder.prototype.returnValueOf3_u6c7rz$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf3_u6c7rz$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    function FeatureAssertionsBuilder$returnValueOf3$lambda(closure$method, closure$plant) {
      return function (a1, a2, a3) {
        return closure$method(closure$plant.subject, a1, a2, a3);
      };
    }
    return function (plant, method, arg1, arg2, arg3) {
      return creating.featureAssertions.returnValueOf3_hcmhmj$(plant, FeatureAssertionsBuilder$returnValueOf3$lambda(method, plant), arg1, arg2, arg3, method.callableName);
    };
  }));
  FeatureAssertionsBuilder.prototype.returnValueOf3_9zkrvh$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf3_9zkrvh$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, method, arg1, arg2, arg3) {
      return creating.featureAssertions.returnValueOf3_hcmhmj$(plant, method, arg1, arg2, arg3, method.callableName);
    };
  }));
  FeatureAssertionsBuilder.prototype.returnValueOf3_hcmhmj$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf3_hcmhmj$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, method, arg1, arg2, arg3, name) {
      return creating.featureAssertions.returnValueOf3_hcmhmj$(plant, method, arg1, arg2, arg3, name);
    };
  }));
  FeatureAssertionsBuilder.prototype.returnValueOf3_ik3u93$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf3_ik3u93$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, method, arg1, arg2, arg3, representationProvider, name) {
      return creating.featureAssertions.returnValueOf3_ik3u93$(plant, method, arg1, arg2, arg3, representationProvider, name);
    };
  }));
  FeatureAssertionsBuilder.prototype.returnValueOf3_2zuaz3$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf3_2zuaz3$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    function FeatureAssertionsBuilder$returnValueOf3$lambda(closure$method, closure$plant) {
      return function (a1, a2, a3) {
        return closure$method(closure$plant.subject, a1, a2, a3);
      };
    }
    return function (plant, method, arg1, arg2, arg3, assertionCreator) {
      return creating.featureAssertions.returnValueOf3_hcmhmj$(plant, FeatureAssertionsBuilder$returnValueOf3$lambda(method, plant), arg1, arg2, arg3, method.callableName).addAssertionsCreatedBy_rnr939$(assertionCreator);
    };
  }));
  FeatureAssertionsBuilder.prototype.returnValueOf3_4ibmmp$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf3_4ibmmp$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, method, arg1, arg2, arg3, assertionCreator) {
      return creating.featureAssertions.returnValueOf3_hcmhmj$(plant, method, arg1, arg2, arg3, method.callableName).addAssertionsCreatedBy_rnr939$(assertionCreator);
    };
  }));
  FeatureAssertionsBuilder.prototype.returnValueOf3_307hzr$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf3_307hzr$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, method, arg1, arg2, arg3, name, assertionCreator) {
      return creating.featureAssertions.returnValueOf3_hcmhmj$(plant, method, arg1, arg2, arg3, name).addAssertionsCreatedBy_rnr939$(assertionCreator);
    };
  }));
  FeatureAssertionsBuilder.prototype.returnValueOf3_r7y64n$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf3_r7y64n$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, method, arg1, arg2, arg3, representationProvider, name, assertionCreator) {
      return creating.featureAssertions.returnValueOf3_ik3u93$(plant, method, arg1, arg2, arg3, representationProvider, name).addAssertionsCreatedBy_rnr939$(assertionCreator);
    };
  }));
  FeatureAssertionsBuilder.prototype.returnValueOf3_wle2bo$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf3_wle2bo$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    function FeatureAssertionsBuilder$returnValueOf3$lambda(closure$method, closure$plant) {
      return function (a1, a2, a3) {
        return closure$method(closure$plant.subject, a1, a2, a3);
      };
    }
    return function (plant, method, arg1, arg2, arg3) {
      var l = FeatureAssertionsBuilder$returnValueOf3$lambda(method, plant);
      return creating.featureAssertions.returnValueOf3_fwwyfy$(plant, l, arg1, arg2, arg3, method.callableName);
    };
  }));
  FeatureAssertionsBuilder.prototype.returnValueOf3_qwcpru$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf3_qwcpru$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, method, arg1, arg2, arg3) {
      return creating.featureAssertions.returnValueOf3_fwwyfy$(plant, method, arg1, arg2, arg3, method.callableName);
    };
  }));
  FeatureAssertionsBuilder.prototype.returnValueOf3_fwwyfy$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf3_fwwyfy$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, method, arg1, arg2, arg3, name) {
      return creating.featureAssertions.returnValueOf3_fwwyfy$(plant, method, arg1, arg2, arg3, name);
    };
  }));
  FeatureAssertionsBuilder.prototype.returnValueOf3_sx9l8c$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf3_sx9l8c$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, method, arg1, arg2, arg3, representationProvider, name) {
      return creating.featureAssertions.returnValueOf3_sx9l8c$(plant, method, arg1, arg2, arg3, representationProvider, name);
    };
  }));
  FeatureAssertionsBuilder.prototype.returnValueOf4_9rcdib$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf4_9rcdib$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    function FeatureAssertionsBuilder$returnValueOf4$lambda(closure$method, closure$plant) {
      return function (a1, a2, a3, a4) {
        return closure$method(closure$plant.subject, a1, a2, a3, a4);
      };
    }
    return function (plant, method, arg1, arg2, arg3, arg4) {
      return creating.featureAssertions.returnValueOf4_5owrw7$(plant, FeatureAssertionsBuilder$returnValueOf4$lambda(method, plant), arg1, arg2, arg3, arg4, method.callableName);
    };
  }));
  FeatureAssertionsBuilder.prototype.returnValueOf4_peoqkd$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf4_peoqkd$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, method, arg1, arg2, arg3, arg4) {
      return creating.featureAssertions.returnValueOf4_5owrw7$(plant, method, arg1, arg2, arg3, arg4, method.callableName);
    };
  }));
  FeatureAssertionsBuilder.prototype.returnValueOf4_5owrw7$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf4_5owrw7$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, method, arg1, arg2, arg3, arg4, name) {
      return creating.featureAssertions.returnValueOf4_5owrw7$(plant, method, arg1, arg2, arg3, arg4, name);
    };
  }));
  FeatureAssertionsBuilder.prototype.returnValueOf4_s4kp5j$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf4_s4kp5j$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, method, arg1, arg2, arg3, arg4, representationProvider, name) {
      return creating.featureAssertions.returnValueOf4_s4kp5j$(plant, method, arg1, arg2, arg3, arg4, representationProvider, name);
    };
  }));
  FeatureAssertionsBuilder.prototype.returnValueOf4_inzfa$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf4_inzfa$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    function FeatureAssertionsBuilder$returnValueOf4$lambda(closure$method, closure$plant) {
      return function (a1, a2, a3, a4) {
        return closure$method(closure$plant.subject, a1, a2, a3, a4);
      };
    }
    return function (plant, method, arg1, arg2, arg3, arg4, assertionCreator) {
      return creating.featureAssertions.returnValueOf4_5owrw7$(plant, FeatureAssertionsBuilder$returnValueOf4$lambda(method, plant), arg1, arg2, arg3, arg4, method.callableName).addAssertionsCreatedBy_rnr939$(assertionCreator);
    };
  }));
  FeatureAssertionsBuilder.prototype.returnValueOf4_c4j5qi$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf4_c4j5qi$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, method, arg1, arg2, arg3, arg4, assertionCreator) {
      return creating.featureAssertions.returnValueOf4_5owrw7$(plant, method, arg1, arg2, arg3, arg4, method.callableName).addAssertionsCreatedBy_rnr939$(assertionCreator);
    };
  }));
  FeatureAssertionsBuilder.prototype.returnValueOf4_sdh4wm$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf4_sdh4wm$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, method, arg1, arg2, arg3, arg4, name, assertionCreator) {
      return creating.featureAssertions.returnValueOf4_5owrw7$(plant, method, arg1, arg2, arg3, arg4, name).addAssertionsCreatedBy_rnr939$(assertionCreator);
    };
  }));
  FeatureAssertionsBuilder.prototype.returnValueOf4_mz2nm2$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf4_mz2nm2$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, method, arg1, arg2, arg3, arg4, representationProvider, name, assertionCreator) {
      return creating.featureAssertions.returnValueOf4_s4kp5j$(plant, method, arg1, arg2, arg3, arg4, representationProvider, name).addAssertionsCreatedBy_rnr939$(assertionCreator);
    };
  }));
  FeatureAssertionsBuilder.prototype.returnValueOf4_c6q3sw$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf4_c6q3sw$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    function FeatureAssertionsBuilder$returnValueOf4$lambda(closure$method, closure$plant) {
      return function (a1, a2, a3, a4) {
        return closure$method(closure$plant.subject, a1, a2, a3, a4);
      };
    }
    return function (plant, method, arg1, arg2, arg3, arg4) {
      var l = FeatureAssertionsBuilder$returnValueOf4$lambda(method, plant);
      return creating.featureAssertions.returnValueOf4_sspjtg$(plant, l, arg1, arg2, arg3, arg4, method.callableName);
    };
  }));
  FeatureAssertionsBuilder.prototype.returnValueOf4_recuu8$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf4_recuu8$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, method, arg1, arg2, arg3, arg4) {
      return creating.featureAssertions.returnValueOf4_sspjtg$(plant, method, arg1, arg2, arg3, arg4, method.callableName);
    };
  }));
  FeatureAssertionsBuilder.prototype.returnValueOf4_sspjtg$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf4_sspjtg$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, method, arg1, arg2, arg3, arg4, name) {
      return creating.featureAssertions.returnValueOf4_sspjtg$(plant, method, arg1, arg2, arg3, arg4, name);
    };
  }));
  FeatureAssertionsBuilder.prototype.returnValueOf4_lbgcwc$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf4_lbgcwc$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, method, arg1, arg2, arg3, arg4, representationProvider, name) {
      return creating.featureAssertions.returnValueOf4_lbgcwc$(plant, method, arg1, arg2, arg3, arg4, representationProvider, name);
    };
  }));
  FeatureAssertionsBuilder.prototype.returnValueOf5_7w2khq$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf5_7w2khq$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    function FeatureAssertionsBuilder$returnValueOf5$lambda(closure$method, closure$plant) {
      return function (a1, a2, a3, a4, a5) {
        return closure$method(closure$plant.subject, a1, a2, a3, a4, a5);
      };
    }
    return function (plant, method, arg1, arg2, arg3, arg4, arg5) {
      return creating.featureAssertions.returnValueOf5_nytevo$(plant, FeatureAssertionsBuilder$returnValueOf5$lambda(method, plant), arg1, arg2, arg3, arg4, arg5, method.callableName);
    };
  }));
  FeatureAssertionsBuilder.prototype.returnValueOf5_hko33w$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf5_hko33w$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, method, arg1, arg2, arg3, arg4, arg5) {
      return creating.featureAssertions.returnValueOf5_nytevo$(plant, method, arg1, arg2, arg3, arg4, arg5, method.callableName);
    };
  }));
  FeatureAssertionsBuilder.prototype.returnValueOf5_nytevo$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf5_nytevo$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, method, arg1, arg2, arg3, arg4, arg5, name) {
      return creating.featureAssertions.returnValueOf5_nytevo$(plant, method, arg1, arg2, arg3, arg4, arg5, name);
    };
  }));
  FeatureAssertionsBuilder.prototype.returnValueOf5_rmos1u$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf5_rmos1u$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, method, arg1, arg2, arg3, arg4, arg5, representationProvider, name) {
      return creating.featureAssertions.returnValueOf5_rmos1u$(plant, method, arg1, arg2, arg3, arg4, arg5, representationProvider, name);
    };
  }));
  FeatureAssertionsBuilder.prototype.returnValueOf5_2mzbfa$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf5_2mzbfa$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    function FeatureAssertionsBuilder$returnValueOf5$lambda(closure$method, closure$plant) {
      return function (a1, a2, a3, a4, a5) {
        return closure$method(closure$plant.subject, a1, a2, a3, a4, a5);
      };
    }
    return function (plant, method, arg1, arg2, arg3, arg4, arg5, assertionCreator) {
      return creating.featureAssertions.returnValueOf5_nytevo$(plant, FeatureAssertionsBuilder$returnValueOf5$lambda(method, plant), arg1, arg2, arg3, arg4, arg5, method.callableName).addAssertionsCreatedBy_rnr939$(assertionCreator);
    };
  }));
  FeatureAssertionsBuilder.prototype.returnValueOf5_cjrjl4$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf5_cjrjl4$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, method, arg1, arg2, arg3, arg4, arg5, assertionCreator) {
      return creating.featureAssertions.returnValueOf5_nytevo$(plant, method, arg1, arg2, arg3, arg4, arg5, method.callableName).addAssertionsCreatedBy_rnr939$(assertionCreator);
    };
  }));
  FeatureAssertionsBuilder.prototype.returnValueOf5_7udwhs$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf5_7udwhs$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, method, arg1, arg2, arg3, arg4, arg5, name, assertionCreator) {
      return creating.featureAssertions.returnValueOf5_nytevo$(plant, method, arg1, arg2, arg3, arg4, arg5, name).addAssertionsCreatedBy_rnr939$(assertionCreator);
    };
  }));
  FeatureAssertionsBuilder.prototype.returnValueOf5_4zrbqi$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf5_4zrbqi$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, method, arg1, arg2, arg3, arg4, arg5, representationProvider, name, assertionCreator) {
      return creating.featureAssertions.returnValueOf5_rmos1u$(plant, method, arg1, arg2, arg3, arg4, arg5, representationProvider, name).addAssertionsCreatedBy_rnr939$(assertionCreator);
    };
  }));
  FeatureAssertionsBuilder.prototype.returnValueOf5_wth7l$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf5_wth7l$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    function FeatureAssertionsBuilder$returnValueOf5$lambda(closure$method, closure$plant) {
      return function (a1, a2, a3, a4, a5) {
        return closure$method(closure$plant.subject, a1, a2, a3, a4, a5);
      };
    }
    return function (plant, method, arg1, arg2, arg3, arg4, arg5) {
      var l = FeatureAssertionsBuilder$returnValueOf5$lambda(method, plant);
      return creating.featureAssertions.returnValueOf5_hfvscp$(plant, l, arg1, arg2, arg3, arg4, arg5, method.callableName);
    };
  }));
  FeatureAssertionsBuilder.prototype.returnValueOf5_c5zh7j$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf5_c5zh7j$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, method, arg1, arg2, arg3, arg4, arg5) {
      return creating.featureAssertions.returnValueOf5_hfvscp$(plant, method, arg1, arg2, arg3, arg4, arg5, method.callableName);
    };
  }));
  FeatureAssertionsBuilder.prototype.returnValueOf5_hfvscp$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf5_hfvscp$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, method, arg1, arg2, arg3, arg4, arg5, name) {
      return creating.featureAssertions.returnValueOf5_hfvscp$(plant, method, arg1, arg2, arg3, arg4, arg5, name);
    };
  }));
  FeatureAssertionsBuilder.prototype.returnValueOf5_ph9kpt$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FeatureAssertionsBuilder.returnValueOf5_ph9kpt$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, method, arg1, arg2, arg3, arg4, arg5, representationProvider, name) {
      return creating.featureAssertions.returnValueOf5_ph9kpt$(plant, method, arg1, arg2, arg3, arg4, arg5, representationProvider, name);
    };
  }));
  FeatureAssertionsBuilder.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'FeatureAssertionsBuilder',
    interfaces: [FeatureAssertions]
  };
  var FeatureAssertionsBuilder_instance = null;
  function FeatureAssertionsBuilder_getInstance() {
    if (FeatureAssertionsBuilder_instance === null) {
      new FeatureAssertionsBuilder();
    }
    return FeatureAssertionsBuilder_instance;
  }
  function ListAssertionsBuilder() {
    ListAssertionsBuilder_instance = this;
  }
  ListAssertionsBuilder.prototype.get_a3ajxn$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.ListAssertionsBuilder.get_a3ajxn$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (assertionContainer, index) {
      return creating.listAssertions.get_a3ajxn$(assertionContainer, index);
    };
  }));
  ListAssertionsBuilder.prototype.get_1agat1$ = function (plant, index) {
    return creating.listAssertions.get_1agat1$(plant, index);
  };
  ListAssertionsBuilder.prototype.get_xq7pz1$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.ListAssertionsBuilder.get_xq7pz1$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, index, assertionCreator) {
      return creating.listAssertions.get_xq7pz1$(plant, index, assertionCreator);
    };
  }));
  ListAssertionsBuilder.prototype.getNullable_t1641a$ = function (plant, index) {
    return creating.listAssertions.getNullable_t1641a$(plant, index);
  };
  ListAssertionsBuilder.prototype.getNullable_g7wwbj$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.ListAssertionsBuilder.getNullable_g7wwbj$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, index, assertionCreator) {
      return creating.listAssertions.getNullable_g7wwbj$(plant, index, assertionCreator);
    };
  }));
  ListAssertionsBuilder.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'ListAssertionsBuilder',
    interfaces: [ListAssertions]
  };
  var ListAssertionsBuilder_instance = null;
  function ListAssertionsBuilder_getInstance() {
    if (ListAssertionsBuilder_instance === null) {
      new ListAssertionsBuilder();
    }
    return ListAssertionsBuilder_instance;
  }
  function MapAssertionsBuilder() {
    MapAssertionsBuilder_instance = this;
  }
  Object.defineProperty(MapAssertionsBuilder.prototype, 'entry', {
    get: defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.MapAssertionsBuilder.get_entry', wrapFunction(function () {
      var creating = _.ch.tutteli.atrium.domain.builders.creating;
      return function () {
        return creating.MapEntryAssertionsBuilder;
      };
    }))
  });
  MapAssertionsBuilder.prototype.contains_f7a2t8$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.MapAssertionsBuilder.contains_f7a2t8$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (assertionContainer, valueType, keyValuePairs) {
      return creating.mapAssertions.contains_f7a2t8$(assertionContainer, valueType, keyValuePairs);
    };
  }));
  MapAssertionsBuilder.prototype.containsKeyWithValueAssertions_z5t2mo$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.MapAssertionsBuilder.containsKeyWithValueAssertions_z5t2mo$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (assertionContainer, valueType, keyValues) {
      return creating.mapAssertions.containsKeyWithValueAssertions_z5t2mo$(assertionContainer, valueType, keyValues);
    };
  }));
  MapAssertionsBuilder.prototype.containsKey_l1dk5y$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.MapAssertionsBuilder.containsKey_l1dk5y$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (subjectProvider, key) {
      return creating.mapAssertions.containsKey_l1dk5y$(subjectProvider, key);
    };
  }));
  MapAssertionsBuilder.prototype.containsNotKey_l1dk5y$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.MapAssertionsBuilder.containsNotKey_l1dk5y$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (subjectProvider, key) {
      return creating.mapAssertions.containsNotKey_l1dk5y$(subjectProvider, key);
    };
  }));
  MapAssertionsBuilder.prototype.isEmpty_miysk1$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.MapAssertionsBuilder.isEmpty_miysk1$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (subjectProvider) {
      return creating.mapAssertions.isEmpty_miysk1$(subjectProvider);
    };
  }));
  MapAssertionsBuilder.prototype.getExisting_xsp6uc$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.MapAssertionsBuilder.getExisting_xsp6uc$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (assertionContainer, key) {
      return creating.mapAssertions.getExisting_xsp6uc$(assertionContainer, key);
    };
  }));
  MapAssertionsBuilder.prototype.isNotEmpty_miysk1$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.MapAssertionsBuilder.isNotEmpty_miysk1$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (subjectProvider) {
      return creating.mapAssertions.isNotEmpty_miysk1$(subjectProvider);
    };
  }));
  MapAssertionsBuilder.prototype.size_gg8irc$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.MapAssertionsBuilder.size_gg8irc$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (assertionContainer) {
      return creating.mapAssertions.size_gg8irc$(assertionContainer);
    };
  }));
  MapAssertionsBuilder.prototype.contains_kz0a7c$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.MapAssertionsBuilder.contains_kz0a7c$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, keyValuePairs) {
      return creating.mapAssertions.contains_kz0a7c$(plant, keyValuePairs);
    };
  }));
  MapAssertionsBuilder.prototype.containsKeyWithValueAssertions_qvj7tk$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.MapAssertionsBuilder.containsKeyWithValueAssertions_qvj7tk$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, keyValues) {
      return creating.mapAssertions.containsKeyWithValueAssertions_qvj7tk$(plant, keyValues);
    };
  }));
  MapAssertionsBuilder.prototype.getExisting_9dg1yy$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.MapAssertionsBuilder.getExisting_9dg1yy$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, key) {
      return creating.mapAssertions.getExisting_9dg1yy$(plant, key);
    };
  }));
  MapAssertionsBuilder.prototype.getExisting_99ax85$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.MapAssertionsBuilder.getExisting_99ax85$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, key, assertionCreator) {
      return creating.mapAssertions.getExisting_99ax85$(plant, key, assertionCreator);
    };
  }));
  MapAssertionsBuilder.prototype.getExistingNullable_x9kbod$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.MapAssertionsBuilder.getExistingNullable_x9kbod$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, key) {
      return creating.mapAssertions.getExistingNullable_x9kbod$(plant, key);
    };
  }));
  MapAssertionsBuilder.prototype.getExistingNullable_se1qmp$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.MapAssertionsBuilder.getExistingNullable_se1qmp$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, key, assertionCreator) {
      return creating.mapAssertions.getExistingNullable_se1qmp$(plant, key, assertionCreator);
    };
  }));
  MapAssertionsBuilder.prototype.hasSize_s812fp$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.MapAssertionsBuilder.hasSize_s812fp$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, size) {
      return creating.mapAssertions.hasSize_s812fp$(plant, size);
    };
  }));
  MapAssertionsBuilder.prototype.keys_q8smbf$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.MapAssertionsBuilder.keys_q8smbf$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, assertionCreator) {
      return creating.mapAssertions.keys_q8smbf$(plant, assertionCreator);
    };
  }));
  MapAssertionsBuilder.prototype.values_xld2cd$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.MapAssertionsBuilder.values_xld2cd$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, assertionCreator) {
      return creating.mapAssertions.values_xld2cd$(plant, assertionCreator);
    };
  }));
  MapAssertionsBuilder.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'MapAssertionsBuilder',
    interfaces: [MapAssertions]
  };
  var MapAssertionsBuilder_instance = null;
  function MapAssertionsBuilder_getInstance() {
    if (MapAssertionsBuilder_instance === null) {
      new MapAssertionsBuilder();
    }
    return MapAssertionsBuilder_instance;
  }
  function MapEntryAssertionsBuilder() {
    MapEntryAssertionsBuilder_instance = this;
  }
  MapEntryAssertionsBuilder.prototype.isKeyValue_me3t05$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.MapEntryAssertionsBuilder.isKeyValue_me3t05$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (assertionContainer, key, value) {
      return creating.mapEntryAssertions.isKeyValue_me3t05$(assertionContainer, key, value);
    };
  }));
  MapEntryAssertionsBuilder.prototype.isKeyValue_5btafg$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.MapEntryAssertionsBuilder.isKeyValue_5btafg$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (assertionContainer, key, value, keyType, valueType) {
      return creating.mapEntryAssertions.isKeyValue_5btafg$(assertionContainer, key, value, keyType, valueType);
    };
  }));
  MapEntryAssertionsBuilder.prototype.key_evlskh$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.MapEntryAssertionsBuilder.key_evlskh$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (assertionContainer) {
      return creating.mapEntryAssertions.key_evlskh$(assertionContainer);
    };
  }));
  MapEntryAssertionsBuilder.prototype.value_1x7llv$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.MapEntryAssertionsBuilder.value_1x7llv$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (assertionContainer) {
      return creating.mapEntryAssertions.value_1x7llv$(assertionContainer);
    };
  }));
  MapEntryAssertionsBuilder.prototype.isKeyValue_yzs2t2$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.MapEntryAssertionsBuilder.isKeyValue_yzs2t2$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, key, value) {
      return creating.mapEntryAssertions.isKeyValue_yzs2t2$(plant, key, value);
    };
  }));
  MapEntryAssertionsBuilder.prototype.key_ggy5ib$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.MapEntryAssertionsBuilder.key_ggy5ib$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, assertionCreator) {
      return creating.mapEntryAssertions.key_ggy5ib$(plant, assertionCreator);
    };
  }));
  MapEntryAssertionsBuilder.prototype.value_ql4mhr$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.MapEntryAssertionsBuilder.value_ql4mhr$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, assertionCreator) {
      return creating.mapEntryAssertions.value_ql4mhr$(plant, assertionCreator);
    };
  }));
  MapEntryAssertionsBuilder.prototype.nullableKey_c85b9b$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.MapEntryAssertionsBuilder.nullableKey_c85b9b$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, assertionCreator) {
      return creating.mapEntryAssertions.nullableKey_c85b9b$(plant, assertionCreator);
    };
  }));
  MapEntryAssertionsBuilder.prototype.nullableValue_clz22t$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.MapEntryAssertionsBuilder.nullableValue_clz22t$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, assertionCreator) {
      return creating.mapEntryAssertions.nullableValue_clz22t$(plant, assertionCreator);
    };
  }));
  MapEntryAssertionsBuilder.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'MapEntryAssertionsBuilder',
    interfaces: [MapEntryAssertions]
  };
  var MapEntryAssertionsBuilder_instance = null;
  function MapEntryAssertionsBuilder_getInstance() {
    if (MapEntryAssertionsBuilder_instance === null) {
      new MapEntryAssertionsBuilder();
    }
    return MapEntryAssertionsBuilder_instance;
  }
  function NewFeatureAssertionsBuilder() {
    NewFeatureAssertionsBuilder_instance = this;
  }
  NewFeatureAssertionsBuilder.prototype.extractor_gh40re$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.NewFeatureAssertionsBuilder.extractor_gh40re$', wrapFunction(function () {
    var FeatureExtractorBuilder = _.ch.tutteli.atrium.domain.builders.creating.changers.FeatureExtractorBuilder;
    return function (originalAssertionContainer) {
      return FeatureExtractorBuilder.Companion.create_gh40re$(originalAssertionContainer);
    };
  }));
  NewFeatureAssertionsBuilder.prototype.property_hkslrl$ = function (assertionContainer, property) {
    return this.extractFeature_0(assertionContainer, property.callableName, getCallableRef('get', function ($receiver, receiver) {
      return $receiver.get(receiver);
    }.bind(null, property)));
  };
  NewFeatureAssertionsBuilder.prototype.f0_fje91o$ = function (assertionContainer, f) {
    return this.extractFeature_0(assertionContainer, core.coreFactory.newMethodCallFormatter().formatCall_ykr607$(f.callableName, []), getCallableRef('invoke', function ($receiver, p1) {
      return $receiver(p1);
    }.bind(null, f)));
  };
  function NewFeatureAssertionsBuilder$f1$lambda(closure$f, closure$a1) {
    return function (it) {
      return closure$f(it, closure$a1);
    };
  }
  NewFeatureAssertionsBuilder.prototype.f1_mllof$ = function (assertionContainer, f, a1) {
    return this.extractFeature_0(assertionContainer, core.coreFactory.newMethodCallFormatter().formatCall_ykr607$(f.callableName, [a1]), NewFeatureAssertionsBuilder$f1$lambda(f, a1));
  };
  function NewFeatureAssertionsBuilder$f2$lambda(closure$f, closure$a1, closure$a2) {
    return function (it) {
      return closure$f(it, closure$a1, closure$a2);
    };
  }
  NewFeatureAssertionsBuilder.prototype.f2_369dpb$ = function (assertionContainer, f, a1, a2) {
    return this.extractFeature_0(assertionContainer, core.coreFactory.newMethodCallFormatter().formatCall_ykr607$(f.callableName, [a1, a2]), NewFeatureAssertionsBuilder$f2$lambda(f, a1, a2));
  };
  function NewFeatureAssertionsBuilder$f3$lambda(closure$f, closure$a1, closure$a2, closure$a3) {
    return function (it) {
      return closure$f(it, closure$a1, closure$a2, closure$a3);
    };
  }
  NewFeatureAssertionsBuilder.prototype.f3_bkz650$ = function (assertionContainer, f, a1, a2, a3) {
    return this.extractFeature_0(assertionContainer, core.coreFactory.newMethodCallFormatter().formatCall_ykr607$(f.callableName, [a1, a2, a3]), NewFeatureAssertionsBuilder$f3$lambda(f, a1, a2, a3));
  };
  function NewFeatureAssertionsBuilder$f4$lambda(closure$f, closure$a1, closure$a2, closure$a3, closure$a4) {
    return function (it) {
      return closure$f(it, closure$a1, closure$a2, closure$a3, closure$a4);
    };
  }
  NewFeatureAssertionsBuilder.prototype.f4_aty42e$ = function (assertionContainer, f, a1, a2, a3, a4) {
    return this.extractFeature_0(assertionContainer, core.coreFactory.newMethodCallFormatter().formatCall_ykr607$(f.callableName, [a1, a2, a3, a4]), NewFeatureAssertionsBuilder$f4$lambda(f, a1, a2, a3, a4));
  };
  function NewFeatureAssertionsBuilder$f5$lambda(closure$f, closure$a1, closure$a2, closure$a3, closure$a4, closure$a5) {
    return function (it) {
      return closure$f(it, closure$a1, closure$a2, closure$a3, closure$a4, closure$a5);
    };
  }
  NewFeatureAssertionsBuilder.prototype.f5_tcc981$ = function (assertionContainer, f, a1, a2, a3, a4, a5) {
    return this.extractFeature_0(assertionContainer, core.coreFactory.newMethodCallFormatter().formatCall_ykr607$(f.callableName, [a1, a2, a3, a4, a5]), NewFeatureAssertionsBuilder$f5$lambda(f, a1, a2, a3, a4, a5));
  };
  NewFeatureAssertionsBuilder.prototype.manualFeature_agtnra$ = function (assertionContainer, name, provider) {
    return this.extractFeature_0(assertionContainer, name, provider);
  };
  NewFeatureAssertionsBuilder.prototype.manualFeature_3kna5q$ = function (assertionContainer, name, provider) {
    return creating.newFeatureAssertions.genericFeature_qa9fd4$(assertionContainer, this.createMetaFeature_0(assertionContainer, name, provider));
  };
  NewFeatureAssertionsBuilder.prototype.genericSubjectBasedFeature_2a066g$ = function (assertionContainer, provider) {
    package$creating.NewFeatureAssertionsBuilder;
    var $this = assertionContainer.maybeSubject;
    var default_0 = getCallableRef('createFeatureSubjectNotDefined', function ($receiver) {
      return $receiver.createFeatureSubjectNotDefined_0();
    }.bind(null, this));
    var fold_2f1hkh$result;
    if (Kotlin.isType($this, Some)) {
      fold_2f1hkh$result = provider($this.value);
    }
     else if (equals($this, core.None)) {
      fold_2f1hkh$result = default_0();
    }
     else {
      fold_2f1hkh$result = Kotlin.noWhenBranchMatched();
    }
    return creating.newFeatureAssertions.genericFeature_qa9fd4$(assertionContainer, fold_2f1hkh$result);
  };
  NewFeatureAssertionsBuilder.prototype.createFeatureSubjectNotDefined_0 = function () {
    return new MetaFeature(ErrorMessages.DEDSCRIPTION_BASED_ON_SUBJECT, RawString.Companion.create_n3w7xm$(ErrorMessages.REPRESENTATION_BASED_ON_SUBJECT_NOT_DEFINED), core.None);
  };
  NewFeatureAssertionsBuilder.prototype.genericFeature_qa9fd4$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.NewFeatureAssertionsBuilder.genericFeature_qa9fd4$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (assertionContainer, metaFeature) {
      return creating.newFeatureAssertions.genericFeature_qa9fd4$(assertionContainer, metaFeature);
    };
  }));
  NewFeatureAssertionsBuilder.prototype.extractFeature_0 = function (assertionContainer, name, provider) {
    return creating.newFeatureAssertions.genericFeature_qa9fd4$(assertionContainer, this.createMetaFeature_1(assertionContainer, name, provider));
  };
  NewFeatureAssertionsBuilder.prototype.createMetaFeature_1 = function (assertionContainer, name, provider) {
    return this.createMetaFeature_0(assertionContainer, new Untranslatable(name), provider);
  };
  NewFeatureAssertionsBuilder.prototype.createMetaFeature_0 = function (assertionContainer, name, provider) {
    var $this = assertionContainer.maybeSubject;
    var fold_2f1hkh$result;
    if (Kotlin.isType($this, Some)) {
      var prop = provider($this.value);
      fold_2f1hkh$result = new MetaFeature(name, prop, new Some(prop));
    }
     else if (equals($this, core.None)) {
      fold_2f1hkh$result = new MetaFeature(name, RawString.Companion.create_n3w7xm$(ErrorMessages.REPRESENTATION_BASED_ON_SUBJECT_NOT_DEFINED), core.None);
    }
     else {
      fold_2f1hkh$result = Kotlin.noWhenBranchMatched();
    }
    return fold_2f1hkh$result;
  };
  Object.defineProperty(NewFeatureAssertionsBuilder.prototype, 'meta', {
    get: defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.NewFeatureAssertionsBuilder.get_meta', wrapFunction(function () {
      var creating = _.ch.tutteli.atrium.domain.builders.creating;
      return function () {
        return creating.MetaFeatureBuilder;
      };
    }))
  });
  NewFeatureAssertionsBuilder.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'NewFeatureAssertionsBuilder',
    interfaces: [NewFeatureAssertions]
  };
  var NewFeatureAssertionsBuilder_instance = null;
  function NewFeatureAssertionsBuilder_getInstance() {
    if (NewFeatureAssertionsBuilder_instance === null) {
      new NewFeatureAssertionsBuilder();
    }
    return NewFeatureAssertionsBuilder_instance;
  }
  function MetaFeatureOption(expect) {
    this.expect_0 = expect;
  }
  MetaFeatureOption.prototype.f_7kt5xd$ = function (property) {
    return this.p_7kt5xd$(property);
  };
  MetaFeatureOption.prototype.f_j9u1pg$ = function (f) {
    return this.f0_j9u1pg$(f);
  };
  MetaFeatureOption.prototype.f_tzpr7i$ = function (f, a1) {
    return this.f1_tzpr7i$(f, a1);
  };
  MetaFeatureOption.prototype.f_ae2tz$ = function (f, a1, a2) {
    return this.f2_ae2tz$(f, a1, a2);
  };
  MetaFeatureOption.prototype.f_e87nl7$ = function (f, a1, a2, a3) {
    return this.f3_e87nl7$(f, a1, a2, a3);
  };
  MetaFeatureOption.prototype.f_l24ww2$ = function (f, a1, a2, a3, a4) {
    return this.f4_l24ww2$(f, a1, a2, a3, a4);
  };
  MetaFeatureOption.prototype.f_64pk0w$ = function (f, a1, a2, a3, a4, a5) {
    return this.f5_64pk0w$(f, a1, a2, a3, a4, a5);
  };
  MetaFeatureOption.prototype.p_7kt5xd$ = function (property) {
    package$creating.NewFeatureAssertionsBuilder;
    return package$creating.MetaFeatureBuilder.property_7kt5xd$(property);
  };
  MetaFeatureOption.prototype.f0_j9u1pg$ = function (f) {
    package$creating.NewFeatureAssertionsBuilder;
    return package$creating.MetaFeatureBuilder.f0_gto95k$(this.expect_0, f);
  };
  MetaFeatureOption.prototype.f1_tzpr7i$ = function (f, a1) {
    package$creating.NewFeatureAssertionsBuilder;
    return package$creating.MetaFeatureBuilder.f1_oiyu6m$(this.expect_0, f, a1);
  };
  MetaFeatureOption.prototype.f2_ae2tz$ = function (f, a1, a2) {
    package$creating.NewFeatureAssertionsBuilder;
    return package$creating.MetaFeatureBuilder.f2_dpngmt$(this.expect_0, f, a1, a2);
  };
  MetaFeatureOption.prototype.f3_e87nl7$ = function (f, a1, a2, a3) {
    package$creating.NewFeatureAssertionsBuilder;
    return package$creating.MetaFeatureBuilder.f3_2qyaxr$(this.expect_0, f, a1, a2, a3);
  };
  MetaFeatureOption.prototype.f4_l24ww2$ = function (f, a1, a2, a3, a4) {
    package$creating.NewFeatureAssertionsBuilder;
    return package$creating.MetaFeatureBuilder.f4_9siunq$(this.expect_0, f, a1, a2, a3, a4);
  };
  MetaFeatureOption.prototype.f5_64pk0w$ = function (f, a1, a2, a3, a4, a5) {
    package$creating.NewFeatureAssertionsBuilder;
    return package$creating.MetaFeatureBuilder.f5_f3bhf0$(this.expect_0, f, a1, a2, a3, a4, a5);
  };
  MetaFeatureOption.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'MetaFeatureOption',
    interfaces: []
  };
  function MetaFeatureBuilder() {
    MetaFeatureBuilder_instance = this;
  }
  MetaFeatureBuilder.prototype.property_7kt5xd$ = function (property) {
    return MetaFeature_init(property.callableName, property());
  };
  MetaFeatureBuilder.prototype.f0_gto95k$ = function (assertionContainer, f) {
    return MetaFeature_init(core.coreFactory.newMethodCallFormatter().formatCall_ykr607$(f.callableName, []), f());
  };
  MetaFeatureBuilder.prototype.f1_oiyu6m$ = function (assertionContainer, f, a1) {
    return MetaFeature_init(core.coreFactory.newMethodCallFormatter().formatCall_ykr607$(f.callableName, [a1]), f(a1));
  };
  MetaFeatureBuilder.prototype.f2_dpngmt$ = function (assertionContainer, f, a1, a2) {
    return MetaFeature_init(core.coreFactory.newMethodCallFormatter().formatCall_ykr607$(f.callableName, [a1, a2]), f(a1, a2));
  };
  MetaFeatureBuilder.prototype.f3_2qyaxr$ = function (assertionContainer, f, a1, a2, a3) {
    return MetaFeature_init(core.coreFactory.newMethodCallFormatter().formatCall_ykr607$(f.callableName, [a1, a2, a3]), f(a1, a2, a3));
  };
  MetaFeatureBuilder.prototype.f4_9siunq$ = function (assertionContainer, f, a1, a2, a3, a4) {
    return MetaFeature_init(core.coreFactory.newMethodCallFormatter().formatCall_ykr607$(f.callableName, [a1, a2, a3, a4]), f(a1, a2, a3, a4));
  };
  MetaFeatureBuilder.prototype.f5_f3bhf0$ = function (assertionContainer, f, a1, a2, a3, a4, a5) {
    return MetaFeature_init(core.coreFactory.newMethodCallFormatter().formatCall_ykr607$(f.callableName, [a1, a2, a3, a4, a5]), f(a1, a2, a3, a4, a5));
  };
  MetaFeatureBuilder.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'MetaFeatureBuilder',
    interfaces: []
  };
  var MetaFeatureBuilder_instance = null;
  function MetaFeatureBuilder_getInstance() {
    if (MetaFeatureBuilder_instance === null) {
      new MetaFeatureBuilder();
    }
    return MetaFeatureBuilder_instance;
  }
  function PairAssertionsBuilder() {
    PairAssertionsBuilder_instance = this;
  }
  PairAssertionsBuilder.prototype.first_sj0ob6$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.PairAssertionsBuilder.first_sj0ob6$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (assertionContainer) {
      return creating.pairAssertions.first_sj0ob6$(assertionContainer);
    };
  }));
  PairAssertionsBuilder.prototype.second_bq7a4u$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.PairAssertionsBuilder.second_bq7a4u$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (assertionContainer) {
      return creating.pairAssertions.second_bq7a4u$(assertionContainer);
    };
  }));
  PairAssertionsBuilder.prototype.first_ct3cgu$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.PairAssertionsBuilder.first_ct3cgu$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, assertionCreator) {
      return creating.pairAssertions.first_ct3cgu$(plant, assertionCreator);
    };
  }));
  PairAssertionsBuilder.prototype.second_2owvhe$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.PairAssertionsBuilder.second_2owvhe$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, assertionCreator) {
      return creating.pairAssertions.second_2owvhe$(plant, assertionCreator);
    };
  }));
  PairAssertionsBuilder.prototype.nullableFirst_5p4mf6$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.PairAssertionsBuilder.nullableFirst_5p4mf6$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, assertionCreator) {
      return creating.pairAssertions.nullableFirst_5p4mf6$(plant, assertionCreator);
    };
  }));
  PairAssertionsBuilder.prototype.nullableSecond_uj8zra$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.PairAssertionsBuilder.nullableSecond_uj8zra$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, assertionCreator) {
      return creating.pairAssertions.nullableSecond_uj8zra$(plant, assertionCreator);
    };
  }));
  PairAssertionsBuilder.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'PairAssertionsBuilder',
    interfaces: [PairAssertions]
  };
  var PairAssertionsBuilder_instance = null;
  function PairAssertionsBuilder_getInstance() {
    if (PairAssertionsBuilder_instance === null) {
      new PairAssertionsBuilder();
    }
    return PairAssertionsBuilder_instance;
  }
  function PleaseUseReplacementException(reason) {
    Exception_init(reason, this);
    this.name = 'PleaseUseReplacementException';
  }
  PleaseUseReplacementException.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'PleaseUseReplacementException',
    interfaces: [Exception]
  };
  function AnyAssertionsBuilder() {
    AnyAssertionsBuilder_instance = this;
  }
  AnyAssertionsBuilder.prototype.toBe_7ehriy$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.AnyAssertionsBuilder.toBe_7ehriy$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (subjectProvider, expected) {
      return creating.anyAssertions.toBe_7ehriy$(subjectProvider, expected);
    };
  }));
  AnyAssertionsBuilder.prototype.notToBe_kqhmyx$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.AnyAssertionsBuilder.notToBe_kqhmyx$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (subjectProvider, expected) {
      return creating.anyAssertions.notToBe_kqhmyx$(subjectProvider, expected);
    };
  }));
  AnyAssertionsBuilder.prototype.isSame_kqhmyx$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.AnyAssertionsBuilder.isSame_kqhmyx$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (subjectProvider, expected) {
      return creating.anyAssertions.isSame_kqhmyx$(subjectProvider, expected);
    };
  }));
  AnyAssertionsBuilder.prototype.isNotSame_kqhmyx$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.AnyAssertionsBuilder.isNotSame_kqhmyx$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (subjectProvider, expected) {
      return creating.anyAssertions.isNotSame_kqhmyx$(subjectProvider, expected);
    };
  }));
  AnyAssertionsBuilder.prototype.toBeNull_dcyqpd$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.AnyAssertionsBuilder.toBeNull_dcyqpd$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (subjectProvider) {
      return creating.anyAssertions.toBeNull_dcyqpd$(subjectProvider);
    };
  }));
  AnyAssertionsBuilder.prototype.toBeNullable_qo3nx6$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.AnyAssertionsBuilder.toBeNullable_qo3nx6$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (assertionContainer, type, expectedOrNull) {
      return creating.anyAssertions.toBeNullable_qo3nx6$(assertionContainer, type, expectedOrNull);
    };
  }));
  AnyAssertionsBuilder.prototype.toBeNullIfNullGivenElse_dqq8i$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.AnyAssertionsBuilder.toBeNullIfNullGivenElse_dqq8i$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (assertionContainer, type, assertionCreatorOrNull) {
      return creating.anyAssertions.toBeNullIfNullGivenElse_dqq8i$(assertionContainer, type, assertionCreatorOrNull);
    };
  }));
  AnyAssertionsBuilder.prototype.isA_jdrxfq$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.AnyAssertionsBuilder.isA_jdrxfq$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (assertionContainer, subType) {
      return creating.anyAssertions.isA_jdrxfq$(assertionContainer, subType);
    };
  }));
  AnyAssertionsBuilder.prototype.isNullable_y7fwcm$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.AnyAssertionsBuilder.isNullable_y7fwcm$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, type, expectedOrNull) {
      return creating.anyAssertions.isNullable_y7fwcm$(plant, type, expectedOrNull);
    };
  }));
  AnyAssertionsBuilder.prototype.isNotNull_u0xxoi$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.AnyAssertionsBuilder.isNotNull_u0xxoi$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, type, assertionCreator) {
      return creating.anyAssertions.isNotNull_u0xxoi$(plant, type, assertionCreator);
    };
  }));
  AnyAssertionsBuilder.prototype.isNotNullBut_y7fwcm$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.AnyAssertionsBuilder.isNotNullBut_y7fwcm$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, type, expected) {
      return creating.anyAssertions.isNotNullBut_y7fwcm$(plant, type, expected);
    };
  }));
  AnyAssertionsBuilder.prototype.isNullIfNullGivenElse_x9kay7$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.AnyAssertionsBuilder.isNullIfNullGivenElse_x9kay7$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, type, assertionCreatorOrNull) {
      return creating.anyAssertions.isNullIfNullGivenElse_x9kay7$(plant, type, assertionCreatorOrNull);
    };
  }));
  Object.defineProperty(AnyAssertionsBuilder.prototype, 'typeTransformation', {
    get: defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.AnyAssertionsBuilder.get_typeTransformation', wrapFunction(function () {
      var creating = _.ch.tutteli.atrium.domain.builders.creating;
      return function () {
        return creating.AnyTypeTransformationAssertionsBuilder;
      };
    }))
  });
  AnyAssertionsBuilder.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'AnyAssertionsBuilder',
    interfaces: [AnyAssertions]
  };
  var AnyAssertionsBuilder_instance = null;
  function AnyAssertionsBuilder_getInstance() {
    if (AnyAssertionsBuilder_instance === null) {
      new AnyAssertionsBuilder();
    }
    return AnyAssertionsBuilder_instance;
  }
  function AnyTypeTransformationAssertionsBuilder() {
    AnyTypeTransformationAssertionsBuilder_instance = this;
  }
  AnyTypeTransformationAssertionsBuilder.prototype.isNotNull_u0xxoi$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.AnyTypeTransformationAssertionsBuilder.isNotNull_u0xxoi$', wrapFunction(function () {
    var creators = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating.any.typetransformation.creators;
    return function (plant, type, assertionCreator) {
      creators.anyTypeTransformationAssertions.isNotNull_u0xxoi$(plant, type, assertionCreator);
    };
  }));
  AnyTypeTransformationAssertionsBuilder.prototype.isA_e0ept2$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.AnyTypeTransformationAssertionsBuilder.isA_e0ept2$', wrapFunction(function () {
    var creators = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating.any.typetransformation.creators;
    return function (plant, subType, assertionCreator) {
      creators.anyTypeTransformationAssertions.isA_e0ept2$(plant, subType, assertionCreator);
    };
  }));
  AnyTypeTransformationAssertionsBuilder.prototype.downCast_h5e5o9$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.AnyTypeTransformationAssertionsBuilder.downCast_h5e5o9$', wrapFunction(function () {
    var creators = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating.any.typetransformation.creators;
    return function (description, subType, subjectPlant, assertionCreator, failureHandler) {
      creators.anyTypeTransformationAssertions.downCast_h5e5o9$(description, subType, subjectPlant, assertionCreator, failureHandler);
    };
  }));
  AnyTypeTransformationAssertionsBuilder.prototype.transform_57sl0j$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.AnyTypeTransformationAssertionsBuilder.transform_57sl0j$', wrapFunction(function () {
    var creators = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating.any.typetransformation.creators;
    return function (parameterObject, canBeTransformed, transform, failureHandler) {
      creators.anyTypeTransformationAssertions.transform_57sl0j$(parameterObject, canBeTransformed, transform, failureHandler);
    };
  }));
  Object.defineProperty(AnyTypeTransformationAssertionsBuilder.prototype, 'failureHandlers', {
    get: defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.AnyTypeTransformationAssertionsBuilder.get_failureHandlers', wrapFunction(function () {
      var creating = _.ch.tutteli.atrium.domain.builders.creating;
      return function () {
        return creating.AnyTypeTransformationFailureHandlerFactoryBuilder;
      };
    }))
  });
  AnyTypeTransformationAssertionsBuilder.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'AnyTypeTransformationAssertionsBuilder',
    interfaces: [AnyTypeTransformationAssertions]
  };
  var AnyTypeTransformationAssertionsBuilder_instance = null;
  function AnyTypeTransformationAssertionsBuilder_getInstance() {
    if (AnyTypeTransformationAssertionsBuilder_instance === null) {
      new AnyTypeTransformationAssertionsBuilder();
    }
    return AnyTypeTransformationAssertionsBuilder_instance;
  }
  function AnyTypeTransformationFailureHandlerFactoryBuilder() {
    AnyTypeTransformationFailureHandlerFactoryBuilder_instance = this;
  }
  AnyTypeTransformationFailureHandlerFactoryBuilder.prototype.newExplanatory_kz82n3$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.AnyTypeTransformationFailureHandlerFactoryBuilder.newExplanatory_kz82n3$', wrapFunction(function () {
    var failurehandlers = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating.any.typetransformation.failurehandlers;
    return function () {
      return failurehandlers.failureHandlerFactory.newExplanatory_kz82n3$();
    };
  }));
  AnyTypeTransformationFailureHandlerFactoryBuilder.prototype.newExplanatoryWithHint_dsn4qj$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.AnyTypeTransformationFailureHandlerFactoryBuilder.newExplanatoryWithHint_dsn4qj$', wrapFunction(function () {
    var failurehandlers = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating.any.typetransformation.failurehandlers;
    return function (showHint, failureHintFactory) {
      return failurehandlers.failureHandlerFactory.newExplanatoryWithHint_dsn4qj$(showHint, failureHintFactory);
    };
  }));
  AnyTypeTransformationFailureHandlerFactoryBuilder.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'AnyTypeTransformationFailureHandlerFactoryBuilder',
    interfaces: [FailureHandlerFactory]
  };
  var AnyTypeTransformationFailureHandlerFactoryBuilder_instance = null;
  function AnyTypeTransformationFailureHandlerFactoryBuilder_getInstance() {
    if (AnyTypeTransformationFailureHandlerFactoryBuilder_instance === null) {
      new AnyTypeTransformationFailureHandlerFactoryBuilder();
    }
    return AnyTypeTransformationFailureHandlerFactoryBuilder_instance;
  }
  function addAssertion($receiver, assertion) {
    return addAssertion_1($receiver.containsBuilder.subjectProvider, assertion);
  }
  function addAssertion_0($receiver, assertion) {
    return addAssertion_1($receiver.subjectProvider, assertion);
  }
  function addAssertion_1(subjectProvider, assertion) {
    if (Kotlin.isType(subjectProvider, Expect))
      return subjectProvider.addAssertion_94orq3$(assertion);
    else if (Kotlin.isType(subjectProvider, AssertionPlant))
      return asExpect(subjectProvider).addAssertion_94orq3$(assertion);
    else
      throw IllegalStateException_init('neither Expect nor Assert');
  }
  function addAssertionForAssert($receiver, assertion) {
    return addAssertionForAssert_1($receiver.containsBuilder.subjectProvider, assertion);
  }
  function addAssertionForAssert_0($receiver, assertion) {
    return addAssertionForAssert_1($receiver.subjectProvider, assertion);
  }
  function addAssertionForAssert_1(subjectProvider, assertion) {
    if (Kotlin.isType(subjectProvider, Expect))
      return asAssert(subjectProvider).addAssertion_94orq3$(assertion);
    else if (Kotlin.isType(subjectProvider, AssertionPlant))
      return subjectProvider.addAssertion_94orq3$(assertion);
    else
      throw IllegalStateException_init('neither Expect nor Assert');
  }
  function FeatureExtractorBuilder() {
    FeatureExtractorBuilder$Companion_getInstance();
  }
  function FeatureExtractorBuilder$Companion() {
    FeatureExtractorBuilder$Companion_instance = this;
  }
  FeatureExtractorBuilder$Companion.prototype.create_gh40re$ = function (originalAssertionContainer) {
    return FeatureExtractorBuilder$DescriptionStep$Companion_getInstance().create_gh40re$(originalAssertionContainer);
  };
  FeatureExtractorBuilder$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var FeatureExtractorBuilder$Companion_instance = null;
  function FeatureExtractorBuilder$Companion_getInstance() {
    if (FeatureExtractorBuilder$Companion_instance === null) {
      new FeatureExtractorBuilder$Companion();
    }
    return FeatureExtractorBuilder$Companion_instance;
  }
  function FeatureExtractorBuilder$DescriptionStep() {
    FeatureExtractorBuilder$DescriptionStep$Companion_getInstance();
  }
  FeatureExtractorBuilder$DescriptionStep.prototype.methodCall_25kzsl$ = function (methodName, arguments_0) {
    return this.withDescription_61zpoe$(core.coreFactory.newMethodCallFormatter().formatCall_ykr607$(methodName, arguments_0));
  };
  FeatureExtractorBuilder$DescriptionStep.prototype.withDescription_61zpoe$ = function (description) {
    return this.withDescription_n3w7xm$(new Untranslatable(description));
  };
  function FeatureExtractorBuilder$DescriptionStep$Companion() {
    FeatureExtractorBuilder$DescriptionStep$Companion_instance = this;
  }
  FeatureExtractorBuilder$DescriptionStep$Companion.prototype.create_gh40re$ = function (originalAssertionContainer) {
    return new DescriptionStepImpl(originalAssertionContainer);
  };
  FeatureExtractorBuilder$DescriptionStep$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var FeatureExtractorBuilder$DescriptionStep$Companion_instance = null;
  function FeatureExtractorBuilder$DescriptionStep$Companion_getInstance() {
    if (FeatureExtractorBuilder$DescriptionStep$Companion_instance === null) {
      new FeatureExtractorBuilder$DescriptionStep$Companion();
    }
    return FeatureExtractorBuilder$DescriptionStep$Companion_instance;
  }
  FeatureExtractorBuilder$DescriptionStep.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'DescriptionStep',
    interfaces: []
  };
  function FeatureExtractorBuilder$RepresentationInCaseOfFailureStep() {
    FeatureExtractorBuilder$RepresentationInCaseOfFailureStep$Companion_getInstance();
  }
  FeatureExtractorBuilder$RepresentationInCaseOfFailureStep.prototype.withRepresentationForFailure_n3w7xm$ = function (translatable) {
    return this.withRepresentationForFailure_za3rmp$(RawString.Companion.create_n3w7xm$(translatable));
  };
  FeatureExtractorBuilder$RepresentationInCaseOfFailureStep.prototype.withRepresentationForFailure_nq59yw$ = function (representationProvider) {
    return this.withRepresentationForFailure_za3rmp$(new LazyRepresentation(representationProvider));
  };
  function FeatureExtractorBuilder$RepresentationInCaseOfFailureStep$Companion() {
    FeatureExtractorBuilder$RepresentationInCaseOfFailureStep$Companion_instance = this;
  }
  FeatureExtractorBuilder$RepresentationInCaseOfFailureStep$Companion.prototype.create_kl4y9o$ = function (originalAssertionContainer, description) {
    return new RepresentationInCaseOfFailureStepImpl(originalAssertionContainer, description);
  };
  FeatureExtractorBuilder$RepresentationInCaseOfFailureStep$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var FeatureExtractorBuilder$RepresentationInCaseOfFailureStep$Companion_instance = null;
  function FeatureExtractorBuilder$RepresentationInCaseOfFailureStep$Companion_getInstance() {
    if (FeatureExtractorBuilder$RepresentationInCaseOfFailureStep$Companion_instance === null) {
      new FeatureExtractorBuilder$RepresentationInCaseOfFailureStep$Companion();
    }
    return FeatureExtractorBuilder$RepresentationInCaseOfFailureStep$Companion_instance;
  }
  FeatureExtractorBuilder$RepresentationInCaseOfFailureStep.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'RepresentationInCaseOfFailureStep',
    interfaces: []
  };
  function FeatureExtractorBuilder$FeatureExtractionStep() {
    FeatureExtractorBuilder$FeatureExtractionStep$Companion_getInstance();
  }
  function FeatureExtractorBuilder$FeatureExtractionStep$Companion() {
    FeatureExtractorBuilder$FeatureExtractionStep$Companion_instance = this;
  }
  FeatureExtractorBuilder$FeatureExtractionStep$Companion.prototype.create_27s6xn$ = function (originalAssertionContainer, description, representationForFailure) {
    return new FeatureExtractionStepImpl(originalAssertionContainer, description, representationForFailure);
  };
  FeatureExtractorBuilder$FeatureExtractionStep$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var FeatureExtractorBuilder$FeatureExtractionStep$Companion_instance = null;
  function FeatureExtractorBuilder$FeatureExtractionStep$Companion_getInstance() {
    if (FeatureExtractorBuilder$FeatureExtractionStep$Companion_instance === null) {
      new FeatureExtractorBuilder$FeatureExtractionStep$Companion();
    }
    return FeatureExtractorBuilder$FeatureExtractionStep$Companion_instance;
  }
  FeatureExtractorBuilder$FeatureExtractionStep.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'FeatureExtractionStep',
    interfaces: []
  };
  function FeatureExtractorBuilder$OptionalRepresentationStep() {
    FeatureExtractorBuilder$OptionalRepresentationStep$Companion_getInstance();
  }
  function FeatureExtractorBuilder$OptionalRepresentationStep$Companion() {
    FeatureExtractorBuilder$OptionalRepresentationStep$Companion_instance = this;
  }
  FeatureExtractorBuilder$OptionalRepresentationStep$Companion.prototype.create_wesv5$ = function (featureExtractionStep, featureExtraction) {
    return new OptionalRepresentationStepImpl(featureExtractionStep, featureExtraction);
  };
  FeatureExtractorBuilder$OptionalRepresentationStep$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var FeatureExtractorBuilder$OptionalRepresentationStep$Companion_instance = null;
  function FeatureExtractorBuilder$OptionalRepresentationStep$Companion_getInstance() {
    if (FeatureExtractorBuilder$OptionalRepresentationStep$Companion_instance === null) {
      new FeatureExtractorBuilder$OptionalRepresentationStep$Companion();
    }
    return FeatureExtractorBuilder$OptionalRepresentationStep$Companion_instance;
  }
  FeatureExtractorBuilder$OptionalRepresentationStep.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'OptionalRepresentationStep',
    interfaces: []
  };
  function FeatureExtractorBuilder$FinalStep() {
    FeatureExtractorBuilder$FinalStep$Companion_getInstance();
  }
  function FeatureExtractorBuilder$FinalStep$Companion() {
    FeatureExtractorBuilder$FinalStep$Companion_instance = this;
  }
  FeatureExtractorBuilder$FinalStep$Companion.prototype.create_jsnvij$ = function (featureExtractionStep, featureExtraction, representationInsteadOfFeature) {
    return new FinalStepImpl(featureExtractionStep, featureExtraction, representationInsteadOfFeature);
  };
  FeatureExtractorBuilder$FinalStep$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var FeatureExtractorBuilder$FinalStep$Companion_instance = null;
  function FeatureExtractorBuilder$FinalStep$Companion_getInstance() {
    if (FeatureExtractorBuilder$FinalStep$Companion_instance === null) {
      new FeatureExtractorBuilder$FinalStep$Companion();
    }
    return FeatureExtractorBuilder$FinalStep$Companion_instance;
  }
  FeatureExtractorBuilder$FinalStep.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'FinalStep',
    interfaces: []
  };
  FeatureExtractorBuilder.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'FeatureExtractorBuilder',
    interfaces: []
  };
  function SubjectChangerBuilder() {
    SubjectChangerBuilder$Companion_getInstance();
  }
  function SubjectChangerBuilder$Companion() {
    SubjectChangerBuilder$Companion_instance = this;
  }
  SubjectChangerBuilder$Companion.prototype.create_gh40re$ = function (originalAssertionContainer) {
    return new KindStepImpl(originalAssertionContainer);
  };
  SubjectChangerBuilder$Companion.prototype.create_dcyqpd$ = function (originalPlant) {
    return new DeprecatedKindStepImpl(originalPlant);
  };
  SubjectChangerBuilder$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var SubjectChangerBuilder$Companion_instance = null;
  function SubjectChangerBuilder$Companion_getInstance() {
    if (SubjectChangerBuilder$Companion_instance === null) {
      new SubjectChangerBuilder$Companion();
    }
    return SubjectChangerBuilder$Companion_instance;
  }
  function SubjectChangerBuilder$DeprecatedKindStep() {
  }
  SubjectChangerBuilder$DeprecatedKindStep.prototype.unreported_el91ig$ = function (transformation) {
    return changers.subjectChanger.unreportedToAssert_ael2fa$(this.originalPlant, transformation);
  };
  SubjectChangerBuilder$DeprecatedKindStep.prototype.unreportedNullable_2o04qz$ = function (transformation) {
    return changers.subjectChanger.unreportedNullableToAssert_n8lbbx$(this.originalPlant, transformation);
  };
  SubjectChangerBuilder$DeprecatedKindStep.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'DeprecatedKindStep',
    interfaces: []
  };
  function SubjectChangerBuilder$KindStep() {
  }
  SubjectChangerBuilder$KindStep.prototype.unreported_2o04qz$ = function (transformation) {
    return changers.subjectChanger.unreported_sdes1g$(this.originalAssertionContainer, transformation);
  };
  SubjectChangerBuilder$KindStep.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'KindStep',
    interfaces: []
  };
  function SubjectChangerBuilder$DescriptionRepresentationStep() {
    SubjectChangerBuilder$DescriptionRepresentationStep$Companion_getInstance();
  }
  function SubjectChangerBuilder$DescriptionRepresentationStep$downCastTo$lambda(closure$subType) {
    return function (it) {
      var predicate = closure$subType.isInstance_s8jyv4$(it);
      return predicate ? new Some(cast(closure$subType, it)) : core.None;
    };
  }
  SubjectChangerBuilder$DescriptionRepresentationStep.prototype.downCastTo_lmshww$ = function (subType) {
    return this.withDescriptionAndRepresentation_75z4jm$(DescriptionAnyAssertion.IS_A, subType).withTransformation_oolzno$(SubjectChangerBuilder$DescriptionRepresentationStep$downCastTo$lambda(subType));
  };
  SubjectChangerBuilder$DescriptionRepresentationStep.prototype.withDescriptionAndRepresentation_4w9ihe$ = function (description, representation) {
    return this.withDescriptionAndRepresentation_75z4jm$(new Untranslatable(description), representation);
  };
  function SubjectChangerBuilder$DescriptionRepresentationStep$Companion() {
    SubjectChangerBuilder$DescriptionRepresentationStep$Companion_instance = this;
  }
  SubjectChangerBuilder$DescriptionRepresentationStep$Companion.prototype.create_gh40re$ = function (originalAssertionContainer) {
    return new DescriptionRepresentationStepImpl(originalAssertionContainer);
  };
  SubjectChangerBuilder$DescriptionRepresentationStep$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var SubjectChangerBuilder$DescriptionRepresentationStep$Companion_instance = null;
  function SubjectChangerBuilder$DescriptionRepresentationStep$Companion_getInstance() {
    if (SubjectChangerBuilder$DescriptionRepresentationStep$Companion_instance === null) {
      new SubjectChangerBuilder$DescriptionRepresentationStep$Companion();
    }
    return SubjectChangerBuilder$DescriptionRepresentationStep$Companion_instance;
  }
  SubjectChangerBuilder$DescriptionRepresentationStep.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'DescriptionRepresentationStep',
    interfaces: []
  };
  function SubjectChangerBuilder$TransformationStep() {
    SubjectChangerBuilder$TransformationStep$Companion_getInstance();
  }
  function SubjectChangerBuilder$TransformationStep$Companion() {
    SubjectChangerBuilder$TransformationStep$Companion_instance = this;
  }
  SubjectChangerBuilder$TransformationStep$Companion.prototype.create_27s6xn$ = function (originalAssertionContainer, description, representation) {
    return new TransformationStepImpl(originalAssertionContainer, description, representation);
  };
  SubjectChangerBuilder$TransformationStep$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var SubjectChangerBuilder$TransformationStep$Companion_instance = null;
  function SubjectChangerBuilder$TransformationStep$Companion_getInstance() {
    if (SubjectChangerBuilder$TransformationStep$Companion_instance === null) {
      new SubjectChangerBuilder$TransformationStep$Companion();
    }
    return SubjectChangerBuilder$TransformationStep$Companion_instance;
  }
  SubjectChangerBuilder$TransformationStep.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'TransformationStep',
    interfaces: []
  };
  function SubjectChangerBuilder$FailureHandlerOption() {
    SubjectChangerBuilder$FailureHandlerOption$Companion_getInstance();
  }
  SubjectChangerBuilder$FailureHandlerOption.prototype.build = function () {
    return this.withDefaultFailureHandler().build();
  };
  function SubjectChangerBuilder$FailureHandlerOption$Companion() {
    SubjectChangerBuilder$FailureHandlerOption$Companion_instance = this;
  }
  SubjectChangerBuilder$FailureHandlerOption$Companion.prototype.create_q4t1zd$ = function (transformationStep, transformation) {
    return new FailureHandlerOptionImpl(transformationStep, transformation);
  };
  SubjectChangerBuilder$FailureHandlerOption$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var SubjectChangerBuilder$FailureHandlerOption$Companion_instance = null;
  function SubjectChangerBuilder$FailureHandlerOption$Companion_getInstance() {
    if (SubjectChangerBuilder$FailureHandlerOption$Companion_instance === null) {
      new SubjectChangerBuilder$FailureHandlerOption$Companion();
    }
    return SubjectChangerBuilder$FailureHandlerOption$Companion_instance;
  }
  SubjectChangerBuilder$FailureHandlerOption.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'FailureHandlerOption',
    interfaces: []
  };
  function SubjectChangerBuilder$FinalStep() {
    SubjectChangerBuilder$FinalStep$Companion_getInstance();
  }
  function SubjectChangerBuilder$FinalStep$Companion() {
    SubjectChangerBuilder$FinalStep$Companion_instance = this;
  }
  SubjectChangerBuilder$FinalStep$Companion.prototype.create_g03tq0$ = function (transformationStep, transformation, failureHandler) {
    return new FinalStepImpl_0(transformationStep, transformation, failureHandler);
  };
  SubjectChangerBuilder$FinalStep$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var SubjectChangerBuilder$FinalStep$Companion_instance = null;
  function SubjectChangerBuilder$FinalStep$Companion_getInstance() {
    if (SubjectChangerBuilder$FinalStep$Companion_instance === null) {
      new SubjectChangerBuilder$FinalStep$Companion();
    }
    return SubjectChangerBuilder$FinalStep$Companion_instance;
  }
  SubjectChangerBuilder$FinalStep.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'FinalStep',
    interfaces: []
  };
  SubjectChangerBuilder.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'SubjectChangerBuilder',
    interfaces: []
  };
  function DescriptionStepImpl(originalAssertionContainer) {
    this.originalAssertionContainer_2kxmeg$_0 = originalAssertionContainer;
  }
  Object.defineProperty(DescriptionStepImpl.prototype, 'originalAssertionContainer', {
    get: function () {
      return this.originalAssertionContainer_2kxmeg$_0;
    }
  });
  DescriptionStepImpl.prototype.withDescription_n3w7xm$ = function (translatable) {
    return FeatureExtractorBuilder$RepresentationInCaseOfFailureStep$Companion_getInstance().create_kl4y9o$(this.originalAssertionContainer, translatable);
  };
  DescriptionStepImpl.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'DescriptionStepImpl',
    interfaces: [FeatureExtractorBuilder$DescriptionStep]
  };
  function RepresentationInCaseOfFailureStepImpl(originalAssertionContainer, description) {
    this.originalAssertionContainer_g5w0rh$_0 = originalAssertionContainer;
    this.description_au9fpx$_0 = description;
  }
  Object.defineProperty(RepresentationInCaseOfFailureStepImpl.prototype, 'originalAssertionContainer', {
    get: function () {
      return this.originalAssertionContainer_g5w0rh$_0;
    }
  });
  Object.defineProperty(RepresentationInCaseOfFailureStepImpl.prototype, 'description', {
    get: function () {
      return this.description_au9fpx$_0;
    }
  });
  RepresentationInCaseOfFailureStepImpl.prototype.withRepresentationForFailure_za3rmp$ = function (representation) {
    return FeatureExtractorBuilder$FeatureExtractionStep$Companion_getInstance().create_27s6xn$(this.originalAssertionContainer, this.description, representation);
  };
  RepresentationInCaseOfFailureStepImpl.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'RepresentationInCaseOfFailureStepImpl',
    interfaces: [FeatureExtractorBuilder$RepresentationInCaseOfFailureStep]
  };
  function FeatureExtractionStepImpl(originalAssertionContainer, description, representationForFailure) {
    this.originalAssertionContainer_mkms2x$_0 = originalAssertionContainer;
    this.description_gip3c1$_0 = description;
    this.representationForFailure_pjnf05$_0 = representationForFailure;
  }
  Object.defineProperty(FeatureExtractionStepImpl.prototype, 'originalAssertionContainer', {
    get: function () {
      return this.originalAssertionContainer_mkms2x$_0;
    }
  });
  Object.defineProperty(FeatureExtractionStepImpl.prototype, 'description', {
    get: function () {
      return this.description_gip3c1$_0;
    }
  });
  Object.defineProperty(FeatureExtractionStepImpl.prototype, 'representationForFailure', {
    get: function () {
      return this.representationForFailure_pjnf05$_0;
    }
  });
  FeatureExtractionStepImpl.prototype.withFeatureExtraction_oolzno$ = function (extraction) {
    return FeatureExtractorBuilder$OptionalRepresentationStep$Companion_getInstance().create_wesv5$(this, extraction);
  };
  FeatureExtractionStepImpl.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'FeatureExtractionStepImpl',
    interfaces: [FeatureExtractorBuilder$FeatureExtractionStep]
  };
  function OptionalRepresentationStepImpl(featureExtractionStep, featureExtraction) {
    this.featureExtractionStep_98zcvq$_0 = featureExtractionStep;
    this.featureExtraction_k2uzxy$_0 = featureExtraction;
  }
  Object.defineProperty(OptionalRepresentationStepImpl.prototype, 'featureExtractionStep', {
    get: function () {
      return this.featureExtractionStep_98zcvq$_0;
    }
  });
  Object.defineProperty(OptionalRepresentationStepImpl.prototype, 'featureExtraction', {
    get: function () {
      return this.featureExtraction_k2uzxy$_0;
    }
  });
  OptionalRepresentationStepImpl.prototype.withRepresentationInsteadOfFeature_za3rmp$ = function (representation) {
    return this.createFinalStep_0(representation);
  };
  OptionalRepresentationStepImpl.prototype.build = function () {
    return this.createFinalStep_0(null).build();
  };
  OptionalRepresentationStepImpl.prototype.createFinalStep_0 = function (representation) {
    return FeatureExtractorBuilder$FinalStep$Companion_getInstance().create_jsnvij$(this.featureExtractionStep, this.featureExtraction, representation);
  };
  OptionalRepresentationStepImpl.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'OptionalRepresentationStepImpl',
    interfaces: [FeatureExtractorBuilder$OptionalRepresentationStep]
  };
  function FinalStepImpl(featureExtractionStep, featureExtraction, representationInsteadOfFeature) {
    this.featureExtractionStep_8z05af$_0 = featureExtractionStep;
    this.featureExtraction_a9vsgr$_0 = featureExtraction;
    this.representationInsteadOfFeature_ax9r60$_0 = representationInsteadOfFeature;
  }
  Object.defineProperty(FinalStepImpl.prototype, 'featureExtractionStep', {
    get: function () {
      return this.featureExtractionStep_8z05af$_0;
    }
  });
  Object.defineProperty(FinalStepImpl.prototype, 'featureExtraction', {
    get: function () {
      return this.featureExtraction_a9vsgr$_0;
    }
  });
  Object.defineProperty(FinalStepImpl.prototype, 'representationInsteadOfFeature', {
    get: function () {
      return this.representationInsteadOfFeature_ax9r60$_0;
    }
  });
  function FinalStepImpl$build$lambda(this$FinalStepImpl) {
    return function ($receiver) {
      return this$FinalStepImpl.extractIt_0($receiver, core.None);
    };
  }
  function FinalStepImpl$build$lambda_0(this$FinalStepImpl) {
    return function ($receiver, assertionCreator) {
      return this$FinalStepImpl.extractIt_0($receiver, new Some(assertionCreator));
    };
  }
  FinalStepImpl.prototype.build = function () {
    return new ExtractedFeaturePostStep(this.featureExtractionStep.originalAssertionContainer, FinalStepImpl$build$lambda(this), FinalStepImpl$build$lambda_0(this));
  };
  FinalStepImpl.prototype.extractIt_0 = function (expect, subAssertions) {
    return changers.featureExtractor.extract_h9u5x1$(expect, this.featureExtractionStep.description, this.featureExtractionStep.representationForFailure, this.featureExtraction, subAssertions, this.representationInsteadOfFeature);
  };
  FinalStepImpl.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'FinalStepImpl',
    interfaces: [FeatureExtractorBuilder$FinalStep]
  };
  function DefaultFailureHandlerImpl() {
  }
  DefaultFailureHandlerImpl.prototype.createAssertion_59fm6a$ = function (originalAssertionContainer, descriptiveAssertion, maybeAssertionCreator) {
    var fold_2f1hkh$result;
    if (Kotlin.isType(maybeAssertionCreator, Some)) {
      var assertionCreator = maybeAssertionCreator.value;
      fold_2f1hkh$result = get_invisibleGroup(builders.assertionBuilder).withAssertions_goesb0$(descriptiveAssertion, collectAssertions_0(builders.assertionBuilder.explanatoryGroup.withDefaultType, core.None, assertionCreator).build()).build();
    }
     else if (equals(maybeAssertionCreator, core.None)) {
      fold_2f1hkh$result = descriptiveAssertion;
    }
     else {
      fold_2f1hkh$result = Kotlin.noWhenBranchMatched();
    }
    return fold_2f1hkh$result;
  };
  DefaultFailureHandlerImpl.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'DefaultFailureHandlerImpl',
    interfaces: [SubjectChanger$FailureHandler]
  };
  function KindStepImpl(originalAssertionContainer) {
    this.originalAssertionContainer_vs1x8w$_0 = originalAssertionContainer;
  }
  Object.defineProperty(KindStepImpl.prototype, 'originalAssertionContainer', {
    get: function () {
      return this.originalAssertionContainer_vs1x8w$_0;
    }
  });
  KindStepImpl.prototype.reportBuilder = function () {
    return SubjectChangerBuilder$DescriptionRepresentationStep$Companion_getInstance().create_gh40re$(this.originalAssertionContainer);
  };
  KindStepImpl.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'KindStepImpl',
    interfaces: [SubjectChangerBuilder$KindStep]
  };
  function DeprecatedKindStepImpl(originalPlant) {
    this.originalPlant_xonps7$_0 = originalPlant;
  }
  Object.defineProperty(DeprecatedKindStepImpl.prototype, 'originalPlant', {
    get: function () {
      return this.originalPlant_xonps7$_0;
    }
  });
  DeprecatedKindStepImpl.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'DeprecatedKindStepImpl',
    interfaces: [SubjectChangerBuilder$DeprecatedKindStep]
  };
  function DescriptionRepresentationStepImpl(originalAssertionContainer) {
    this.originalAssertionContainer_dmtzgj$_0 = originalAssertionContainer;
  }
  Object.defineProperty(DescriptionRepresentationStepImpl.prototype, 'originalAssertionContainer', {
    get: function () {
      return this.originalAssertionContainer_dmtzgj$_0;
    }
  });
  DescriptionRepresentationStepImpl.prototype.withDescriptionAndRepresentation_75z4jm$ = function (description, representation) {
    return SubjectChangerBuilder$TransformationStep$Companion_getInstance().create_27s6xn$(this.originalAssertionContainer, description, representation != null ? representation : RawString.Companion.NULL);
  };
  DescriptionRepresentationStepImpl.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'DescriptionRepresentationStepImpl',
    interfaces: [SubjectChangerBuilder$DescriptionRepresentationStep]
  };
  function TransformationStepImpl(originalAssertionContainer, description, representation) {
    this.originalAssertionContainer_vsla4l$_0 = originalAssertionContainer;
    this.description_mu743h$_0 = description;
    this.representation_8im4a0$_0 = representation;
  }
  Object.defineProperty(TransformationStepImpl.prototype, 'originalAssertionContainer', {
    get: function () {
      return this.originalAssertionContainer_vsla4l$_0;
    }
  });
  Object.defineProperty(TransformationStepImpl.prototype, 'description', {
    get: function () {
      return this.description_mu743h$_0;
    }
  });
  Object.defineProperty(TransformationStepImpl.prototype, 'representation', {
    get: function () {
      return this.representation_8im4a0$_0;
    }
  });
  TransformationStepImpl.prototype.withTransformation_oolzno$ = function (transformation) {
    return SubjectChangerBuilder$FailureHandlerOption$Companion_getInstance().create_q4t1zd$(this, transformation);
  };
  TransformationStepImpl.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'TransformationStepImpl',
    interfaces: [SubjectChangerBuilder$TransformationStep]
  };
  function FailureHandlerOptionImpl(transformationStep, transformation) {
    this.transformationStep_g9l37k$_0 = transformationStep;
    this.transformation_ow1omk$_0 = transformation;
  }
  Object.defineProperty(FailureHandlerOptionImpl.prototype, 'transformationStep', {
    get: function () {
      return this.transformationStep_g9l37k$_0;
    }
  });
  Object.defineProperty(FailureHandlerOptionImpl.prototype, 'transformation', {
    get: function () {
      return this.transformation_ow1omk$_0;
    }
  });
  FailureHandlerOptionImpl.prototype.withFailureHandler_yr4upn$ = function (failureHandler) {
    return SubjectChangerBuilder$FinalStep$Companion_getInstance().create_g03tq0$(this.transformationStep, this.transformation, failureHandler);
  };
  FailureHandlerOptionImpl.prototype.withDefaultFailureHandler = function () {
    return this.withFailureHandler_yr4upn$(new DefaultFailureHandlerImpl());
  };
  FailureHandlerOptionImpl.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'FailureHandlerOptionImpl',
    interfaces: [SubjectChangerBuilder$FailureHandlerOption]
  };
  function FinalStepImpl_0(transformationStep, transformation, failureHandler) {
    this.transformationStep_jwii85$_0 = transformationStep;
    this.transformation_gly8un$_0 = transformation;
    this.failureHandler_qah58m$_0 = failureHandler;
  }
  Object.defineProperty(FinalStepImpl_0.prototype, 'transformationStep', {
    get: function () {
      return this.transformationStep_jwii85$_0;
    }
  });
  Object.defineProperty(FinalStepImpl_0.prototype, 'transformation', {
    get: function () {
      return this.transformation_gly8un$_0;
    }
  });
  Object.defineProperty(FinalStepImpl_0.prototype, 'failureHandler', {
    get: function () {
      return this.failureHandler_qah58m$_0;
    }
  });
  function FinalStepImpl$build$lambda_1(this$FinalStepImpl) {
    return function ($receiver) {
      return this$FinalStepImpl.transformIt_0($receiver, core.None);
    };
  }
  function FinalStepImpl$build$lambda_2(this$FinalStepImpl) {
    return function ($receiver, assertionCreator) {
      return this$FinalStepImpl.transformIt_0($receiver, new Some(assertionCreator));
    };
  }
  FinalStepImpl_0.prototype.build = function () {
    return new ChangedSubjectPostStep(this.transformationStep.originalAssertionContainer, FinalStepImpl$build$lambda_1(this), FinalStepImpl$build$lambda_2(this));
  };
  FinalStepImpl_0.prototype.transformIt_0 = function (expect, subAssertions) {
    return changers.subjectChanger.reported_hg9i7u$(expect, this.transformationStep.description, this.transformationStep.representation, this.transformation, this.failureHandler, subAssertions);
  };
  FinalStepImpl_0.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'FinalStepImpl',
    interfaces: [SubjectChangerBuilder$FinalStep]
  };
  function CharSequenceAssertionsBuilder() {
    CharSequenceAssertionsBuilder_instance = this;
  }
  CharSequenceAssertionsBuilder.prototype.containsBuilder_20mk8n$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.CharSequenceAssertionsBuilder.containsBuilder_20mk8n$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (subjectProvider) {
      return creating.charSequenceAssertions.containsBuilder_20mk8n$(subjectProvider);
    };
  }));
  CharSequenceAssertionsBuilder.prototype.containsNotBuilder_20mk8n$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.CharSequenceAssertionsBuilder.containsNotBuilder_20mk8n$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (subjectProvider) {
      return creating.charSequenceAssertions.containsNotBuilder_20mk8n$(subjectProvider);
    };
  }));
  CharSequenceAssertionsBuilder.prototype.startsWith_phxxlr$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.CharSequenceAssertionsBuilder.startsWith_phxxlr$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (subjectProvider, expected) {
      return creating.charSequenceAssertions.startsWith_phxxlr$(subjectProvider, expected);
    };
  }));
  CharSequenceAssertionsBuilder.prototype.startsNotWith_phxxlr$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.CharSequenceAssertionsBuilder.startsNotWith_phxxlr$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (subjectProvider, expected) {
      return creating.charSequenceAssertions.startsNotWith_phxxlr$(subjectProvider, expected);
    };
  }));
  CharSequenceAssertionsBuilder.prototype.endsWith_phxxlr$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.CharSequenceAssertionsBuilder.endsWith_phxxlr$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (subjectProvider, expected) {
      return creating.charSequenceAssertions.endsWith_phxxlr$(subjectProvider, expected);
    };
  }));
  CharSequenceAssertionsBuilder.prototype.endsNotWith_phxxlr$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.CharSequenceAssertionsBuilder.endsNotWith_phxxlr$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (subjectProvider, expected) {
      return creating.charSequenceAssertions.endsNotWith_phxxlr$(subjectProvider, expected);
    };
  }));
  CharSequenceAssertionsBuilder.prototype.isEmpty_3rtcbd$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.CharSequenceAssertionsBuilder.isEmpty_3rtcbd$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (subjectProvider) {
      return creating.charSequenceAssertions.isEmpty_3rtcbd$(subjectProvider);
    };
  }));
  CharSequenceAssertionsBuilder.prototype.isNotEmpty_3rtcbd$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.CharSequenceAssertionsBuilder.isNotEmpty_3rtcbd$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (subjectProvider) {
      return creating.charSequenceAssertions.isNotEmpty_3rtcbd$(subjectProvider);
    };
  }));
  CharSequenceAssertionsBuilder.prototype.isNotBlank_3rtcbd$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.CharSequenceAssertionsBuilder.isNotBlank_3rtcbd$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (subjectProvider) {
      return creating.charSequenceAssertions.isNotBlank_3rtcbd$(subjectProvider);
    };
  }));
  CharSequenceAssertionsBuilder.prototype.matches_qr4gd5$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.CharSequenceAssertionsBuilder.matches_qr4gd5$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (assertionContainer, expected) {
      return creating.charSequenceAssertions.matches_qr4gd5$(assertionContainer, expected);
    };
  }));
  CharSequenceAssertionsBuilder.prototype.mismatches_qr4gd5$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.CharSequenceAssertionsBuilder.mismatches_qr4gd5$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (assertionContainer, expected) {
      return creating.charSequenceAssertions.mismatches_qr4gd5$(assertionContainer, expected);
    };
  }));
  Object.defineProperty(CharSequenceAssertionsBuilder.prototype, 'contains', {
    get: defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.CharSequenceAssertionsBuilder.get_contains', wrapFunction(function () {
      var creating = _.ch.tutteli.atrium.domain.builders.creating;
      return function () {
        return creating.CharSequenceContainsAssertionsBuilder;
      };
    }))
  });
  CharSequenceAssertionsBuilder.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'CharSequenceAssertionsBuilder',
    interfaces: [CharSequenceAssertions]
  };
  var CharSequenceAssertionsBuilder_instance = null;
  function CharSequenceAssertionsBuilder_getInstance() {
    if (CharSequenceAssertionsBuilder_instance === null) {
      new CharSequenceAssertionsBuilder();
    }
    return CharSequenceAssertionsBuilder_instance;
  }
  function CharSequenceContainsAssertionsBuilder() {
    CharSequenceContainsAssertionsBuilder_instance = this;
  }
  CharSequenceContainsAssertionsBuilder.prototype.values_34khi3$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.CharSequenceContainsAssertionsBuilder.values_34khi3$', wrapFunction(function () {
    var creators = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating.charsequence.contains.creators;
    return function (checkerOption, expected) {
      return creators.charSequenceContainsAssertions.values_34khi3$(checkerOption, expected);
    };
  }));
  CharSequenceContainsAssertionsBuilder.prototype.valuesIgnoringCase_56nr2g$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.CharSequenceContainsAssertionsBuilder.valuesIgnoringCase_56nr2g$', wrapFunction(function () {
    var creators = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating.charsequence.contains.creators;
    return function (checkerOption, expected) {
      return creators.charSequenceContainsAssertions.valuesIgnoringCase_56nr2g$(checkerOption, expected);
    };
  }));
  CharSequenceContainsAssertionsBuilder.prototype.defaultTranslationOf_ouhusm$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.CharSequenceContainsAssertionsBuilder.defaultTranslationOf_ouhusm$', wrapFunction(function () {
    var creators = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating.charsequence.contains.creators;
    return function (checkerOption, expected) {
      return creators.charSequenceContainsAssertions.defaultTranslationOf_ouhusm$(checkerOption, expected);
    };
  }));
  CharSequenceContainsAssertionsBuilder.prototype.defaultTranslationOfIgnoringCase_28ojgd$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.CharSequenceContainsAssertionsBuilder.defaultTranslationOfIgnoringCase_28ojgd$', wrapFunction(function () {
    var creators = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating.charsequence.contains.creators;
    return function (checkerOption, expected) {
      return creators.charSequenceContainsAssertions.defaultTranslationOfIgnoringCase_28ojgd$(checkerOption, expected);
    };
  }));
  CharSequenceContainsAssertionsBuilder.prototype.regex_kf2upq$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.CharSequenceContainsAssertionsBuilder.regex_kf2upq$', wrapFunction(function () {
    var wrapFunction = Kotlin.wrapFunction;
    var collectionSizeOrDefault = Kotlin.kotlin.collections.collectionSizeOrDefault_ba2ldo$;
    var ArrayList_init = Kotlin.kotlin.collections.ArrayList_init_ww73n8$;
    var Regex_init = Kotlin.kotlin.text.Regex_init_61zpoe$;
    var creators = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating.charsequence.contains.creators;
    var CharSequenceContainsAssertionsBuilder$regex$lambda = wrapFunction(function () {
      var Regex_init = Kotlin.kotlin.text.Regex_init_61zpoe$;
      return function (it) {
        return Regex_init(it);
      };
    });
    return function (checkerOption, expected) {
      var destination = ArrayList_init(collectionSizeOrDefault(expected, 10));
      var tmp$;
      tmp$ = expected.iterator();
      while (tmp$.hasNext()) {
        var item = tmp$.next();
        destination.add_11rb$(Regex_init(item));
      }
      return creators.charSequenceContainsAssertions.regex_imkq43$(checkerOption, destination);
    };
  }));
  CharSequenceContainsAssertionsBuilder.prototype.regex_imkq43$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.CharSequenceContainsAssertionsBuilder.regex_imkq43$', wrapFunction(function () {
    var creators = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating.charsequence.contains.creators;
    return function (checkerOption, expected) {
      return creators.charSequenceContainsAssertions.regex_imkq43$(checkerOption, expected);
    };
  }));
  CharSequenceContainsAssertionsBuilder.prototype.regexIgnoringCase_dzi1c1$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.CharSequenceContainsAssertionsBuilder.regexIgnoringCase_dzi1c1$', wrapFunction(function () {
    var creators = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating.charsequence.contains.creators;
    return function (checkerOption, expected) {
      return creators.charSequenceContainsAssertions.regexIgnoringCase_dzi1c1$(checkerOption, expected);
    };
  }));
  Object.defineProperty(CharSequenceContainsAssertionsBuilder.prototype, 'searchBehaviours', {
    get: defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.CharSequenceContainsAssertionsBuilder.get_searchBehaviours', wrapFunction(function () {
      var creating = _.ch.tutteli.atrium.domain.builders.creating;
      return function () {
        return creating.CharSequenceContainsSearchBehaviourFactoryBuilder;
      };
    }))
  });
  CharSequenceContainsAssertionsBuilder.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'CharSequenceContainsAssertionsBuilder',
    interfaces: [CharSequenceContainsAssertions]
  };
  var CharSequenceContainsAssertionsBuilder_instance = null;
  function CharSequenceContainsAssertionsBuilder_getInstance() {
    if (CharSequenceContainsAssertionsBuilder_instance === null) {
      new CharSequenceContainsAssertionsBuilder();
    }
    return CharSequenceContainsAssertionsBuilder_instance;
  }
  function CharSequenceContainsSearchBehaviourFactoryBuilder() {
    CharSequenceContainsSearchBehaviourFactoryBuilder_instance = this;
  }
  CharSequenceContainsSearchBehaviourFactoryBuilder.prototype.ignoringCase_553t70$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.CharSequenceContainsSearchBehaviourFactoryBuilder.ignoringCase_553t70$', wrapFunction(function () {
    var searchbehaviours = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours;
    return function (containsBuilder) {
      return searchbehaviours.searchBehaviourFactory.ignoringCase_553t70$(containsBuilder);
    };
  }));
  CharSequenceContainsSearchBehaviourFactoryBuilder.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'CharSequenceContainsSearchBehaviourFactoryBuilder',
    interfaces: [SearchBehaviourFactory]
  };
  var CharSequenceContainsSearchBehaviourFactoryBuilder_instance = null;
  function CharSequenceContainsSearchBehaviourFactoryBuilder_getInstance() {
    if (CharSequenceContainsSearchBehaviourFactoryBuilder_instance === null) {
      new CharSequenceContainsSearchBehaviourFactoryBuilder();
    }
    return CharSequenceContainsSearchBehaviourFactoryBuilder_instance;
  }
  function AtLeastCheckerOptionBase(times, containsBuilder, nameContainsNotFun, atLeastCall) {
    this.times_d8jxbm$_0 = times;
    this.containsBuilder_nid3zw$_0 = containsBuilder;
    this.checkers_3djbj2$_0 = listOf(checkers.checkerFactory.newAtLeastChecker_yuuvyu$(this.times, nameContainsNotFun, atLeastCall));
  }
  Object.defineProperty(AtLeastCheckerOptionBase.prototype, 'times', {
    get: function () {
      return this.times_d8jxbm$_0;
    }
  });
  Object.defineProperty(AtLeastCheckerOptionBase.prototype, 'containsBuilder', {
    get: function () {
      return this.containsBuilder_nid3zw$_0;
    }
  });
  Object.defineProperty(AtLeastCheckerOptionBase.prototype, 'checkers', {
    get: function () {
      return this.checkers_3djbj2$_0;
    }
  });
  AtLeastCheckerOptionBase.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AtLeastCheckerOptionBase',
    interfaces: [WithTimesCheckerOption]
  };
  function AtMostCheckerOptionBase(times, containsBuilder, nameContainsNotFun, atMostCall, atLeastCall, exactlyCall) {
    this.times_k5w6tk$_0 = times;
    this.containsBuilder_2cwnpu$_0 = containsBuilder;
    validateAtMost(this.times, atMostCall, atLeastCall, exactlyCall);
    this.checkers_52zu7s$_0 = listOf_0([checkers.checkerFactory.newAtLeastChecker_yuuvyu$(1, nameContainsNotFun, atLeastCall), checkers.checkerFactory.newAtMostChecker_yuuvyu$(this.times, nameContainsNotFun, atMostCall)]);
  }
  Object.defineProperty(AtMostCheckerOptionBase.prototype, 'times', {
    get: function () {
      return this.times_k5w6tk$_0;
    }
  });
  Object.defineProperty(AtMostCheckerOptionBase.prototype, 'containsBuilder', {
    get: function () {
      return this.containsBuilder_2cwnpu$_0;
    }
  });
  Object.defineProperty(AtMostCheckerOptionBase.prototype, 'checkers', {
    get: function () {
      return this.checkers_52zu7s$_0;
    }
  });
  AtMostCheckerOptionBase.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AtMostCheckerOptionBase',
    interfaces: [WithTimesCheckerOption]
  };
  function ButAtMostCheckerOptionBase(times, atLeastBuilder, containsBuilder, nameContainsNotFun, atLeastButAtMostCall, atMostCall, atLeastCall, butAtMostCall, exactlyCall) {
    this.times_fzhab7$_0 = times;
    this.containsBuilder_wem37n$_0 = containsBuilder;
    validateButAtMost(atLeastBuilder.times, this.times, atLeastButAtMostCall, atLeastCall, butAtMostCall, exactlyCall);
    this.checkers_uocnsz$_0 = listOf_0(copyToArray(atLeastBuilder.checkers).concat([checkers.checkerFactory.newAtMostChecker_yuuvyu$(this.times, nameContainsNotFun, atMostCall)]));
  }
  Object.defineProperty(ButAtMostCheckerOptionBase.prototype, 'times', {
    get: function () {
      return this.times_fzhab7$_0;
    }
  });
  Object.defineProperty(ButAtMostCheckerOptionBase.prototype, 'containsBuilder', {
    get: function () {
      return this.containsBuilder_wem37n$_0;
    }
  });
  Object.defineProperty(ButAtMostCheckerOptionBase.prototype, 'checkers', {
    get: function () {
      return this.checkers_uocnsz$_0;
    }
  });
  ButAtMostCheckerOptionBase.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ButAtMostCheckerOptionBase',
    interfaces: [WithTimesCheckerOption]
  };
  function ExactlyCheckerOptionBase(times, containsBuilder, nameContainsNotFun, exactlyCall) {
    this.times_d1n7p4$_0 = times;
    this.containsBuilder_6mrfvy$_0 = containsBuilder;
    this.checkers_sbz3aw$_0 = listOf(checkers.checkerFactory.newExactlyChecker_yuuvyu$(this.times, nameContainsNotFun, exactlyCall));
  }
  Object.defineProperty(ExactlyCheckerOptionBase.prototype, 'times', {
    get: function () {
      return this.times_d1n7p4$_0;
    }
  });
  Object.defineProperty(ExactlyCheckerOptionBase.prototype, 'containsBuilder', {
    get: function () {
      return this.containsBuilder_6mrfvy$_0;
    }
  });
  Object.defineProperty(ExactlyCheckerOptionBase.prototype, 'checkers', {
    get: function () {
      return this.checkers_sbz3aw$_0;
    }
  });
  ExactlyCheckerOptionBase.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ExactlyCheckerOptionBase',
    interfaces: [WithTimesCheckerOption]
  };
  function NotCheckerOptionBase(containsBuilder) {
    this.containsBuilder_wtz813$_0 = containsBuilder;
    this.checkers_wxx129$_0 = listOf(checkers.checkerFactory.newNotChecker());
  }
  Object.defineProperty(NotCheckerOptionBase.prototype, 'containsBuilder', {
    get: function () {
      return this.containsBuilder_wtz813$_0;
    }
  });
  Object.defineProperty(NotCheckerOptionBase.prototype, 'checkers', {
    get: function () {
      return this.checkers_wxx129$_0;
    }
  });
  NotCheckerOptionBase.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'NotCheckerOptionBase',
    interfaces: [CharSequenceContains$CheckerOption]
  };
  function NotOrAtMostCheckerOptionBase(times, containsBuilder, nameContainsNotFun, notOrAtMostCall) {
    this.times_t6a394$_0 = times;
    this.containsBuilder_lcsiaa$_0 = containsBuilder;
    this.checkers_3s103s$_0 = listOf(checkers.checkerFactory.newAtMostChecker_yuuvyu$(this.times, nameContainsNotFun, notOrAtMostCall));
  }
  Object.defineProperty(NotOrAtMostCheckerOptionBase.prototype, 'times', {
    get: function () {
      return this.times_t6a394$_0;
    }
  });
  Object.defineProperty(NotOrAtMostCheckerOptionBase.prototype, 'containsBuilder', {
    get: function () {
      return this.containsBuilder_lcsiaa$_0;
    }
  });
  Object.defineProperty(NotOrAtMostCheckerOptionBase.prototype, 'checkers', {
    get: function () {
      return this.checkers_3s103s$_0;
    }
  });
  NotOrAtMostCheckerOptionBase.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'NotOrAtMostCheckerOptionBase',
    interfaces: [WithTimesCheckerOption]
  };
  function WithTimesCheckerOption() {
  }
  WithTimesCheckerOption.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'WithTimesCheckerOption',
    interfaces: [CharSequenceContains$CheckerOption]
  };
  function AssertionCollectorBuilder() {
    AssertionCollectorBuilder_instance = this;
  }
  AssertionCollectorBuilder.prototype.collect_9ebvy2$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.collectors.AssertionCollectorBuilder.collect_9ebvy2$', wrapFunction(function () {
    var collectors = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating.collectors;
    return function (maybeSubject, assertionCreator) {
      return collectors.assertionCollector.collect_9ebvy2$(maybeSubject, assertionCreator);
    };
  }));
  AssertionCollectorBuilder.prototype.collectForComposition_9ebvy2$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.collectors.AssertionCollectorBuilder.collectForComposition_9ebvy2$', wrapFunction(function () {
    var collectors = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating.collectors;
    return function (maybeSubject, assertionCreator) {
      return collectors.assertionCollector.collectForComposition_9ebvy2$(maybeSubject, assertionCreator);
    };
  }));
  AssertionCollectorBuilder.prototype.collect_2bcx7n$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.collectors.AssertionCollectorBuilder.collect_2bcx7n$', wrapFunction(function () {
    var collectors = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating.collectors;
    return function (subjectProvider, collectingPlantFactory, assertionCreator) {
      return collectors.assertionCollector.collect_2bcx7n$(subjectProvider, collectingPlantFactory, assertionCreator);
    };
  }));
  AssertionCollectorBuilder.prototype.collectOrExplain_7ixqcy$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.collectors.AssertionCollectorBuilder.collectOrExplain_7ixqcy$', wrapFunction(function () {
    var collectors = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating.collectors;
    return function (safeToCollect, warningCannotEvaluate, subjectProvider, collectingPlantFactory, assertionCreator) {
      return collectors.assertionCollector.collectOrExplain_7ixqcy$(safeToCollect, warningCannotEvaluate, subjectProvider, collectingPlantFactory, assertionCreator);
    };
  }));
  Object.defineProperty(AssertionCollectorBuilder.prototype, 'forExplanation', {
    get: defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.collectors.AssertionCollectorBuilder.get_forExplanation', wrapFunction(function () {
      var collectors = _.ch.tutteli.atrium.domain.builders.creating.collectors;
      return function () {
        return collectors.ExplainingAssertionCollectorOption;
      };
    }))
  });
  AssertionCollectorBuilder.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'AssertionCollectorBuilder',
    interfaces: [AssertionCollector]
  };
  var AssertionCollectorBuilder_instance = null;
  function AssertionCollectorBuilder_getInstance() {
    if (AssertionCollectorBuilder_instance === null) {
      new AssertionCollectorBuilder();
    }
    return AssertionCollectorBuilder_instance;
  }
  function ExplainingAssertionCollectorOption() {
    ExplainingAssertionCollectorOption_instance = this;
  }
  Object.defineProperty(ExplainingAssertionCollectorOption.prototype, 'throwIfNoAssertionIsCollected', {
    get: defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.collectors.ExplainingAssertionCollectorOption.get_throwIfNoAssertionIsCollected', wrapFunction(function () {
      var collectors = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating.collectors;
      return function () {
        return collectors.throwingAssertionCollectorForExplanation;
      };
    }))
  });
  Object.defineProperty(ExplainingAssertionCollectorOption.prototype, 'doNotThrowIfNoAssertionIsCollected', {
    get: defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.collectors.ExplainingAssertionCollectorOption.get_doNotThrowIfNoAssertionIsCollected', wrapFunction(function () {
      var collectors = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating.collectors;
      return function () {
        return collectors.nonThrowingAssertionCollectorForExplanation;
      };
    }))
  });
  ExplainingAssertionCollectorOption.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'ExplainingAssertionCollectorOption',
    interfaces: []
  };
  var ExplainingAssertionCollectorOption_instance = null;
  function ExplainingAssertionCollectorOption_getInstance() {
    if (ExplainingAssertionCollectorOption_instance === null) {
      new ExplainingAssertionCollectorOption();
    }
    return ExplainingAssertionCollectorOption_instance;
  }
  function collectAssertions($receiver, assertionContainer, assertionCreator) {
    return collectAssertions_0($receiver, assertionContainer.maybeSubject, assertionCreator);
  }
  function collectAssertions_0($receiver, maybeSubject, assertionCreator) {
    package$collectors.AssertionCollectorBuilder;
    return $receiver.withAssertions_tgi7xs$(collectors.assertionCollector.collectForComposition_9ebvy2$(maybeSubject, assertionCreator));
  }
  function AtLeastCheckerOptionBase_0(times, containsBuilder, nameContainsNotFun, atLeastCall) {
    this.times_1yhsd$_0 = times;
    this.containsBuilder_47bga1$_0 = containsBuilder;
    this.checkers_7jvpwd$_0 = listOf(checkers_0.checkerFactory.newAtLeastChecker_yuuvyu$(this.times, nameContainsNotFun, atLeastCall));
  }
  Object.defineProperty(AtLeastCheckerOptionBase_0.prototype, 'times', {
    get: function () {
      return this.times_1yhsd$_0;
    }
  });
  Object.defineProperty(AtLeastCheckerOptionBase_0.prototype, 'containsBuilder', {
    get: function () {
      return this.containsBuilder_47bga1$_0;
    }
  });
  Object.defineProperty(AtLeastCheckerOptionBase_0.prototype, 'checkers', {
    get: function () {
      return this.checkers_7jvpwd$_0;
    }
  });
  AtLeastCheckerOptionBase_0.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AtLeastCheckerOptionBase',
    interfaces: [WithTimesCheckerOption_0]
  };
  function AtMostCheckerOptionBase_0(times, containsBuilder, nameContainsNotFun, atMostCall, atLeastCall, exactlyCall) {
    this.times_5gs06r$_0 = times;
    this.containsBuilder_ca78o9$_0 = containsBuilder;
    validateAtMost(this.times, atMostCall, atLeastCall, exactlyCall);
    this.checkers_un1rh9$_0 = listOf_0([checkers_0.checkerFactory.newAtLeastChecker_yuuvyu$(1, nameContainsNotFun, atLeastCall), checkers_0.checkerFactory.newAtMostChecker_yuuvyu$(this.times, nameContainsNotFun, atMostCall)]);
  }
  Object.defineProperty(AtMostCheckerOptionBase_0.prototype, 'times', {
    get: function () {
      return this.times_5gs06r$_0;
    }
  });
  Object.defineProperty(AtMostCheckerOptionBase_0.prototype, 'containsBuilder', {
    get: function () {
      return this.containsBuilder_ca78o9$_0;
    }
  });
  Object.defineProperty(AtMostCheckerOptionBase_0.prototype, 'checkers', {
    get: function () {
      return this.checkers_un1rh9$_0;
    }
  });
  AtMostCheckerOptionBase_0.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AtMostCheckerOptionBase',
    interfaces: [WithTimesCheckerOption_0]
  };
  function ButAtMostCheckerOptionBase_0(times, atLeastBuilder, containsBuilder, nameContainsNotFun, atLeastButAtMostCall, atMostCall, atLeastCall, butAtMostCall, exactlyCall) {
    this.times_9kkmya$_0 = times;
    this.containsBuilder_se2r6w$_0 = containsBuilder;
    validateButAtMost(atLeastBuilder.times, this.times, atLeastButAtMostCall, atLeastCall, butAtMostCall, exactlyCall);
    this.checkers_k65nzy$_0 = listOf_0(copyToArray(atLeastBuilder.checkers).concat([checkers_0.checkerFactory.newAtMostChecker_yuuvyu$(this.times, nameContainsNotFun, atMostCall)]));
  }
  Object.defineProperty(ButAtMostCheckerOptionBase_0.prototype, 'times', {
    get: function () {
      return this.times_9kkmya$_0;
    }
  });
  Object.defineProperty(ButAtMostCheckerOptionBase_0.prototype, 'containsBuilder', {
    get: function () {
      return this.containsBuilder_se2r6w$_0;
    }
  });
  Object.defineProperty(ButAtMostCheckerOptionBase_0.prototype, 'checkers', {
    get: function () {
      return this.checkers_k65nzy$_0;
    }
  });
  ButAtMostCheckerOptionBase_0.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ButAtMostCheckerOptionBase',
    interfaces: [WithTimesCheckerOption_0]
  };
  function ExactlyCheckerOptionBase_0(times, containsBuilder, nameContainsNotFun, exactlyCall) {
    this.times_q88n8d$_0 = times;
    this.containsBuilder_ycg05v$_0 = containsBuilder;
    this.checkers_vrpx8t$_0 = listOf(checkers_0.checkerFactory.newExactlyChecker_yuuvyu$(this.times, nameContainsNotFun, exactlyCall));
  }
  Object.defineProperty(ExactlyCheckerOptionBase_0.prototype, 'times', {
    get: function () {
      return this.times_q88n8d$_0;
    }
  });
  Object.defineProperty(ExactlyCheckerOptionBase_0.prototype, 'containsBuilder', {
    get: function () {
      return this.containsBuilder_ycg05v$_0;
    }
  });
  Object.defineProperty(ExactlyCheckerOptionBase_0.prototype, 'checkers', {
    get: function () {
      return this.checkers_vrpx8t$_0;
    }
  });
  ExactlyCheckerOptionBase_0.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ExactlyCheckerOptionBase',
    interfaces: [WithTimesCheckerOption_0]
  };
  function NotCheckerOptionBase_0(containsBuilder) {
    this.containsBuilder_8ple2c$_0 = containsBuilder;
    this.checkers_7b8u1y$_0 = listOf(checkers_0.checkerFactory.newNotChecker());
  }
  Object.defineProperty(NotCheckerOptionBase_0.prototype, 'containsBuilder', {
    get: function () {
      return this.containsBuilder_8ple2c$_0;
    }
  });
  Object.defineProperty(NotCheckerOptionBase_0.prototype, 'checkers', {
    get: function () {
      return this.checkers_7b8u1y$_0;
    }
  });
  NotCheckerOptionBase_0.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'NotCheckerOptionBase',
    interfaces: [IterableContains$CheckerOption]
  };
  function NotOrAtMostCheckerOptionBase_0(times, containsBuilder, nameContainsNotFun, notOrAtMostCall) {
    this.times_cso1nh$_0 = times;
    this.containsBuilder_r0amc3$_0 = containsBuilder;
    this.checkers_d2r1hp$_0 = listOf(checkers_0.checkerFactory.newAtMostChecker_yuuvyu$(this.times, nameContainsNotFun, notOrAtMostCall));
  }
  Object.defineProperty(NotOrAtMostCheckerOptionBase_0.prototype, 'times', {
    get: function () {
      return this.times_cso1nh$_0;
    }
  });
  Object.defineProperty(NotOrAtMostCheckerOptionBase_0.prototype, 'containsBuilder', {
    get: function () {
      return this.containsBuilder_r0amc3$_0;
    }
  });
  Object.defineProperty(NotOrAtMostCheckerOptionBase_0.prototype, 'checkers', {
    get: function () {
      return this.checkers_d2r1hp$_0;
    }
  });
  NotOrAtMostCheckerOptionBase_0.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'NotOrAtMostCheckerOptionBase',
    interfaces: [WithTimesCheckerOption_0]
  };
  function WithTimesCheckerOption_0() {
  }
  WithTimesCheckerOption_0.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'WithTimesCheckerOption',
    interfaces: [IterableContains$CheckerOption]
  };
  function IterableAssertionsBuilder() {
    IterableAssertionsBuilder_instance = this;
  }
  IterableAssertionsBuilder.prototype.containsBuilder_fyqaqw$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.IterableAssertionsBuilder.containsBuilder_fyqaqw$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (subjectProvider) {
      return creating.iterableAssertions.containsBuilder_fyqaqw$(subjectProvider);
    };
  }));
  IterableAssertionsBuilder.prototype.containsNotBuilder_fyqaqw$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.IterableAssertionsBuilder.containsNotBuilder_fyqaqw$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (subjectProvider) {
      return creating.iterableAssertions.containsNotBuilder_fyqaqw$(subjectProvider);
    };
  }));
  IterableAssertionsBuilder.prototype.all_ye5jvu$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.IterableAssertionsBuilder.all_ye5jvu$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (assertionContainer, assertionCreator) {
      return creating.iterableAssertions.all_ye5jvu$(assertionContainer, assertionCreator);
    };
  }));
  IterableAssertionsBuilder.prototype.hasNext_cqhzmn$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.IterableAssertionsBuilder.hasNext_cqhzmn$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (expect) {
      return creating.iterableAssertions.hasNext_cqhzmn$(expect);
    };
  }));
  IterableAssertionsBuilder.prototype.hasNotNext_cqhzmn$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.IterableAssertionsBuilder.hasNotNext_cqhzmn$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (expect) {
      return creating.iterableAssertions.hasNotNext_cqhzmn$(expect);
    };
  }));
  IterableAssertionsBuilder.prototype.min_2yf3k7$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.IterableAssertionsBuilder.min_2yf3k7$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (assertionContainer) {
      return creating.iterableAssertions.min_2yf3k7$(assertionContainer);
    };
  }));
  IterableAssertionsBuilder.prototype.max_2yf3k7$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.IterableAssertionsBuilder.max_2yf3k7$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (assertionContainer) {
      return creating.iterableAssertions.max_2yf3k7$(assertionContainer);
    };
  }));
  Object.defineProperty(IterableAssertionsBuilder.prototype, 'contains', {
    get: defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.IterableAssertionsBuilder.get_contains', wrapFunction(function () {
      var creating = _.ch.tutteli.atrium.domain.builders.creating;
      return function () {
        return creating.IterableContainsAssertionsBuilder;
      };
    }))
  });
  IterableAssertionsBuilder.prototype.all_rcrmla$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.IterableAssertionsBuilder.all_rcrmla$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (plant, assertionCreator) {
      return creating.iterableAssertions.all_rcrmla$(plant, assertionCreator);
    };
  }));
  IterableAssertionsBuilder.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'IterableAssertionsBuilder',
    interfaces: [IterableAssertions]
  };
  var IterableAssertionsBuilder_instance = null;
  function IterableAssertionsBuilder_getInstance() {
    if (IterableAssertionsBuilder_instance === null) {
      new IterableAssertionsBuilder();
    }
    return IterableAssertionsBuilder_instance;
  }
  function IterableContainsAssertionsBuilder() {
    IterableContainsAssertionsBuilder_instance = this;
  }
  IterableContainsAssertionsBuilder.prototype.valuesInAnyOrder_34ablu$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.IterableContainsAssertionsBuilder.valuesInAnyOrder_34ablu$', wrapFunction(function () {
    var creators = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating.iterable.contains.creators;
    return function (checkerOption, expected) {
      return creators.iterableContainsAssertions.valuesInAnyOrder_34ablu$(checkerOption, expected);
    };
  }));
  IterableContainsAssertionsBuilder.prototype.entriesInAnyOrder_7k2oo7$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.IterableContainsAssertionsBuilder.entriesInAnyOrder_7k2oo7$', wrapFunction(function () {
    var creators = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating.iterable.contains.creators;
    return function (checkerOption, assertionCreators) {
      return creators.iterableContainsAssertions.entriesInAnyOrder_7k2oo7$(checkerOption, assertionCreators);
    };
  }));
  IterableContainsAssertionsBuilder.prototype.valuesInAnyOrderOnly_8257eh$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.IterableContainsAssertionsBuilder.valuesInAnyOrderOnly_8257eh$', wrapFunction(function () {
    var creators = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating.iterable.contains.creators;
    return function (builder, expected) {
      return creators.iterableContainsAssertions.valuesInAnyOrderOnly_8257eh$(builder, expected);
    };
  }));
  IterableContainsAssertionsBuilder.prototype.entriesInAnyOrderOnly_uep0gu$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.IterableContainsAssertionsBuilder.entriesInAnyOrderOnly_uep0gu$', wrapFunction(function () {
    var creators = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating.iterable.contains.creators;
    return function (builder, assertionCreators) {
      return creators.iterableContainsAssertions.entriesInAnyOrderOnly_uep0gu$(builder, assertionCreators);
    };
  }));
  IterableContainsAssertionsBuilder.prototype.valuesInOrderOnly_nkuvon$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.IterableContainsAssertionsBuilder.valuesInOrderOnly_nkuvon$', wrapFunction(function () {
    var creators = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating.iterable.contains.creators;
    return function (builder, expected) {
      return creators.iterableContainsAssertions.valuesInOrderOnly_nkuvon$(builder, expected);
    };
  }));
  IterableContainsAssertionsBuilder.prototype.entriesInOrderOnly_x166i8$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.IterableContainsAssertionsBuilder.entriesInOrderOnly_x166i8$', wrapFunction(function () {
    var creators = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating.iterable.contains.creators;
    return function (builder, assertionCreators) {
      return creators.iterableContainsAssertions.entriesInOrderOnly_x166i8$(builder, assertionCreators);
    };
  }));
  IterableContainsAssertionsBuilder.prototype.valuesInOrderOnlyGrouped_qy4r6y$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.IterableContainsAssertionsBuilder.valuesInOrderOnlyGrouped_qy4r6y$', wrapFunction(function () {
    var creators = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating.iterable.contains.creators;
    return function (builder, groups) {
      return creators.iterableContainsAssertions.valuesInOrderOnlyGrouped_qy4r6y$(builder, groups);
    };
  }));
  IterableContainsAssertionsBuilder.prototype.entriesInOrderOnlyGrouped_wsh6rf$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.IterableContainsAssertionsBuilder.entriesInOrderOnlyGrouped_wsh6rf$', wrapFunction(function () {
    var creators = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating.iterable.contains.creators;
    return function (builder, groups) {
      return creators.iterableContainsAssertions.entriesInOrderOnlyGrouped_wsh6rf$(builder, groups);
    };
  }));
  Object.defineProperty(IterableContainsAssertionsBuilder.prototype, 'searchBehaviours', {
    get: defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.IterableContainsAssertionsBuilder.get_searchBehaviours', wrapFunction(function () {
      var creating = _.ch.tutteli.atrium.domain.builders.creating;
      return function () {
        return creating.IterableContainsSearchBehaviourFactoryBuilder;
      };
    }))
  });
  IterableContainsAssertionsBuilder.prototype.entriesInAnyOrderWithAssert_w7knt0$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.IterableContainsAssertionsBuilder.entriesInAnyOrderWithAssert_w7knt0$', wrapFunction(function () {
    var creators = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating.iterable.contains.creators;
    return function (checkerOption, assertionCreators) {
      return creators.iterableContainsAssertions.entriesInAnyOrderWithAssert_w7knt0$(checkerOption, assertionCreators);
    };
  }));
  IterableContainsAssertionsBuilder.prototype.entriesInAnyOrderOnlyWithAssert_xcv0gv$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.IterableContainsAssertionsBuilder.entriesInAnyOrderOnlyWithAssert_xcv0gv$', wrapFunction(function () {
    var creators = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating.iterable.contains.creators;
    return function (builder, assertionCreators) {
      return creators.iterableContainsAssertions.entriesInAnyOrderOnlyWithAssert_xcv0gv$(builder, assertionCreators);
    };
  }));
  IterableContainsAssertionsBuilder.prototype.entriesInOrderOnlyWithAssert_mxfmod$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.IterableContainsAssertionsBuilder.entriesInOrderOnlyWithAssert_mxfmod$', wrapFunction(function () {
    var creators = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating.iterable.contains.creators;
    return function (builder, assertionCreators) {
      return creators.iterableContainsAssertions.entriesInOrderOnlyWithAssert_mxfmod$(builder, assertionCreators);
    };
  }));
  IterableContainsAssertionsBuilder.prototype.entriesInOrderOnlyGroupedWithAssert_gua2de$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.IterableContainsAssertionsBuilder.entriesInOrderOnlyGroupedWithAssert_gua2de$', wrapFunction(function () {
    var creators = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating.iterable.contains.creators;
    return function (builder, groups) {
      return creators.iterableContainsAssertions.entriesInOrderOnlyGroupedWithAssert_gua2de$(builder, groups);
    };
  }));
  IterableContainsAssertionsBuilder.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'IterableContainsAssertionsBuilder',
    interfaces: [IterableContainsAssertions]
  };
  var IterableContainsAssertionsBuilder_instance = null;
  function IterableContainsAssertionsBuilder_getInstance() {
    if (IterableContainsAssertionsBuilder_instance === null) {
      new IterableContainsAssertionsBuilder();
    }
    return IterableContainsAssertionsBuilder_instance;
  }
  function IterableContainsSearchBehaviourFactoryBuilder() {
    IterableContainsSearchBehaviourFactoryBuilder_instance = this;
  }
  IterableContainsSearchBehaviourFactoryBuilder.prototype.inAnyOrder_mn0tah$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.IterableContainsSearchBehaviourFactoryBuilder.inAnyOrder_mn0tah$', wrapFunction(function () {
    var searchbehaviours = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours;
    return function (builder) {
      return searchbehaviours.searchBehaviourFactory.inAnyOrder_mn0tah$(builder);
    };
  }));
  IterableContainsSearchBehaviourFactoryBuilder.prototype.inAnyOrderOnly_i6bwk2$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.IterableContainsSearchBehaviourFactoryBuilder.inAnyOrderOnly_i6bwk2$', wrapFunction(function () {
    var searchbehaviours = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours;
    return function (builder) {
      return searchbehaviours.searchBehaviourFactory.inAnyOrderOnly_i6bwk2$(builder);
    };
  }));
  IterableContainsSearchBehaviourFactoryBuilder.prototype.inOrder_mn0tah$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.IterableContainsSearchBehaviourFactoryBuilder.inOrder_mn0tah$', wrapFunction(function () {
    var searchbehaviours = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours;
    return function (builder) {
      return searchbehaviours.searchBehaviourFactory.inOrder_mn0tah$(builder);
    };
  }));
  IterableContainsSearchBehaviourFactoryBuilder.prototype.inOrderOnly_9wcnok$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.IterableContainsSearchBehaviourFactoryBuilder.inOrderOnly_9wcnok$', wrapFunction(function () {
    var searchbehaviours = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours;
    return function (builder) {
      return searchbehaviours.searchBehaviourFactory.inOrderOnly_9wcnok$(builder);
    };
  }));
  IterableContainsSearchBehaviourFactoryBuilder.prototype.inOrderOnlyGrouped_owsmxk$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.IterableContainsSearchBehaviourFactoryBuilder.inOrderOnlyGrouped_owsmxk$', wrapFunction(function () {
    var searchbehaviours = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours;
    return function (builder) {
      return searchbehaviours.searchBehaviourFactory.inOrderOnlyGrouped_owsmxk$(builder);
    };
  }));
  IterableContainsSearchBehaviourFactoryBuilder.prototype.inOrderOnlyGroupedWithin_izjtps$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.IterableContainsSearchBehaviourFactoryBuilder.inOrderOnlyGroupedWithin_izjtps$', wrapFunction(function () {
    var searchbehaviours = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours;
    return function (builder) {
      return searchbehaviours.searchBehaviourFactory.inOrderOnlyGroupedWithin_izjtps$(builder);
    };
  }));
  IterableContainsSearchBehaviourFactoryBuilder.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'IterableContainsSearchBehaviourFactoryBuilder',
    interfaces: [SearchBehaviourFactory_0]
  };
  var IterableContainsSearchBehaviourFactoryBuilder_instance = null;
  function IterableContainsSearchBehaviourFactoryBuilder_getInstance() {
    if (IterableContainsSearchBehaviourFactoryBuilder_instance === null) {
      new IterableContainsSearchBehaviourFactoryBuilder();
    }
    return IterableContainsSearchBehaviourFactoryBuilder_instance;
  }
  function ThrowableAssertionsBuilder() {
    ThrowableAssertionsBuilder_instance = this;
  }
  ThrowableAssertionsBuilder.prototype.thrownBuilder_1nnvc6$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.ThrowableAssertionsBuilder.thrownBuilder_1nnvc6$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (assertionVerb, act, reporter) {
      return creating.throwableAssertions.thrownBuilder_1nnvc6$(assertionVerb, act, reporter);
    };
  }));
  Object.defineProperty(ThrowableAssertionsBuilder.prototype, 'thrown', {
    get: defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.ThrowableAssertionsBuilder.get_thrown', wrapFunction(function () {
      var creating = _.ch.tutteli.atrium.domain.builders.creating;
      return function () {
        return creating.ThrowableThrownAssertionsBuilder;
      };
    }))
  });
  ThrowableAssertionsBuilder.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'ThrowableAssertionsBuilder',
    interfaces: [ThrowableAssertions]
  };
  var ThrowableAssertionsBuilder_instance = null;
  function ThrowableAssertionsBuilder_getInstance() {
    if (ThrowableAssertionsBuilder_instance === null) {
      new ThrowableAssertionsBuilder();
    }
    return ThrowableAssertionsBuilder_instance;
  }
  function ThrowableThrownAssertionsBuilder() {
    ThrowableThrownAssertionsBuilder_instance = this;
  }
  ThrowableThrownAssertionsBuilder.prototype.isA_j1v2cy$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.ThrowableThrownAssertionsBuilder.isA_j1v2cy$', wrapFunction(function () {
    var creators = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating.throwable.thrown.creators;
    return function (throwableThrownBuilder, expectedType) {
      return creators.throwableThrownAssertions.isA_j1v2cy$(throwableThrownBuilder, expectedType);
    };
  }));
  ThrowableThrownAssertionsBuilder.prototype.notThrown_sruzb2$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.ThrowableThrownAssertionsBuilder.notThrown_sruzb2$', wrapFunction(function () {
    var creators = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating.throwable.thrown.creators;
    return function (throwableThrownBuilder) {
      return creators.throwableThrownAssertions.notThrown_sruzb2$(throwableThrownBuilder);
    };
  }));
  ThrowableThrownAssertionsBuilder.prototype.toBe_vdu9lw$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.ThrowableThrownAssertionsBuilder.toBe_vdu9lw$', wrapFunction(function () {
    var creators = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating.throwable.thrown.creators;
    return function (throwableThrownBuilder, expectedType, assertionCreator) {
      creators.throwableThrownAssertions.toBe_vdu9lw$(throwableThrownBuilder, expectedType, assertionCreator);
    };
  }));
  ThrowableThrownAssertionsBuilder.prototype.nothingThrown_sruzb2$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.ThrowableThrownAssertionsBuilder.nothingThrown_sruzb2$', wrapFunction(function () {
    var creators = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating.throwable.thrown.creators;
    return function (throwableThrownBuilder) {
      creators.throwableThrownAssertions.nothingThrown_sruzb2$(throwableThrownBuilder);
    };
  }));
  Object.defineProperty(ThrowableThrownAssertionsBuilder.prototype, 'providers', {
    get: defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.ThrowableThrownAssertionsBuilder.get_providers', wrapFunction(function () {
      var creating = _.ch.tutteli.atrium.domain.builders.creating;
      return function () {
        return creating.AbsentThrowableMessageProviderFactoryBuilder;
      };
    }))
  });
  ThrowableThrownAssertionsBuilder.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'ThrowableThrownAssertionsBuilder',
    interfaces: [ThrowableThrownAssertions]
  };
  var ThrowableThrownAssertionsBuilder_instance = null;
  function ThrowableThrownAssertionsBuilder_getInstance() {
    if (ThrowableThrownAssertionsBuilder_instance === null) {
      new ThrowableThrownAssertionsBuilder();
    }
    return ThrowableThrownAssertionsBuilder_instance;
  }
  function AbsentThrowableMessageProviderFactoryBuilder() {
    AbsentThrowableMessageProviderFactoryBuilder_instance = this;
  }
  AbsentThrowableMessageProviderFactoryBuilder.prototype.translatableBased_n3w7xm$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.AbsentThrowableMessageProviderFactoryBuilder.translatableBased_n3w7xm$', wrapFunction(function () {
    var providers = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating.throwable.thrown.providers;
    return function (translatable) {
      return providers.absentThrowableMessageProviderFactory.translatableBased_n3w7xm$(translatable);
    };
  }));
  AbsentThrowableMessageProviderFactoryBuilder.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'AbsentThrowableMessageProviderFactoryBuilder',
    interfaces: [AbsentThrowableMessageProviderFactory]
  };
  var AbsentThrowableMessageProviderFactoryBuilder_instance = null;
  function AbsentThrowableMessageProviderFactoryBuilder_getInstance() {
    if (AbsentThrowableMessageProviderFactoryBuilder_instance === null) {
      new AbsentThrowableMessageProviderFactoryBuilder();
    }
    return AbsentThrowableMessageProviderFactoryBuilder_instance;
  }
  function asAssert$lambda(this$asAssert) {
    return function () {
      var $receiver = this$asAssert.maybeSubject;
      var fold_2f1hkh$result;
      if (Kotlin.isType($receiver, Some)) {
        fold_2f1hkh$result = $receiver.value;
      }
       else if (equals($receiver, core.None)) {
        throw PlantHasNoSubjectException_init();
      }
       else {
        fold_2f1hkh$result = Kotlin.noWhenBranchMatched();
      }
      return fold_2f1hkh$result;
    };
  }
  function asAssert($receiver) {
    return core.coreFactory.newReportingPlant_xxj474$(reporting.SHOULD_NOT_BE_SHOWN_TO_THE_USER_BUG_TRANSLATABLE, asAssert$lambda($receiver), core.coreFactory.newDelegatingAssertionChecker_10aezz$($receiver));
  }
  function asAssert_0($receiver, assertionCreator) {
    return asAssert($receiver).addAssertionsCreatedBy_rnr939$(assertionCreator);
  }
  function asAssert$lambda_0(this$asAssert) {
    return function () {
      var $receiver = this$asAssert.maybeSubject;
      var fold_2f1hkh$result;
      if (Kotlin.isType($receiver, Some)) {
        fold_2f1hkh$result = $receiver.value;
      }
       else if (equals($receiver, core.None)) {
        throw PlantHasNoSubjectException_init();
      }
       else {
        fold_2f1hkh$result = Kotlin.noWhenBranchMatched();
      }
      return fold_2f1hkh$result;
    };
  }
  function asAssert($receiver) {
    return core.coreFactory.newReportingPlantNullable_xmyivw$(reporting.SHOULD_NOT_BE_SHOWN_TO_THE_USER_BUG_TRANSLATABLE, asAssert$lambda_0($receiver), core.coreFactory.newDelegatingAssertionChecker_10aezz$($receiver));
  }
  function asSubExpect$lambda(closure$assertionCreatorOrNull) {
    return function ($receiver) {
      if (closure$assertionCreatorOrNull != null) {
        asAssert_0($receiver, closure$assertionCreatorOrNull);
      }
      return Unit;
    };
  }
  function asSubExpect(assertionCreatorOrNull) {
    return asSubExpect$lambda(assertionCreatorOrNull);
  }
  function asExpect($receiver) {
    return core.coreFactory.newDelegatingReportingAssertionContainer_8dvlby$($receiver, $receiver.maybeSubject);
  }
  function asExpect_0($receiver, assertionCreator) {
    asExpect($receiver).addAssertionsCreatedBy_rp2vuk$(assertionCreator);
    return $receiver;
  }
  function AssertionFormatterControllerOption() {
    AssertionFormatterControllerOption$Companion_getInstance();
  }
  function AssertionFormatterControllerOption$Companion() {
    AssertionFormatterControllerOption$Companion_instance = this;
  }
  AssertionFormatterControllerOption$Companion.prototype.create_w899fu$ = function (objectFormatter, translator) {
    return new AssertionFormatterControllerOptionImpl(objectFormatter, translator);
  };
  AssertionFormatterControllerOption$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var AssertionFormatterControllerOption$Companion_instance = null;
  function AssertionFormatterControllerOption$Companion_getInstance() {
    if (AssertionFormatterControllerOption$Companion_instance === null) {
      new AssertionFormatterControllerOption$Companion();
    }
    return AssertionFormatterControllerOption$Companion_instance;
  }
  AssertionFormatterControllerOption.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'AssertionFormatterControllerOption',
    interfaces: []
  };
  function AssertionFormatterFacadeOption() {
    AssertionFormatterFacadeOption$Companion_getInstance();
  }
  function AssertionFormatterFacadeOption$Companion() {
    AssertionFormatterFacadeOption$Companion_instance = this;
  }
  AssertionFormatterFacadeOption$Companion.prototype.create_bao0p0$ = function (assertionFormatterController, objectFormatter, translator) {
    return new AssertionFormatterFacadeOptionImpl(assertionFormatterController, objectFormatter, translator);
  };
  AssertionFormatterFacadeOption$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var AssertionFormatterFacadeOption$Companion_instance = null;
  function AssertionFormatterFacadeOption$Companion_getInstance() {
    if (AssertionFormatterFacadeOption$Companion_instance === null) {
      new AssertionFormatterFacadeOption$Companion();
    }
    return AssertionFormatterFacadeOption$Companion_instance;
  }
  AssertionFormatterFacadeOption.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'AssertionFormatterFacadeOption',
    interfaces: []
  };
  function AssertionPairFormatterOption() {
    AssertionPairFormatterOption$Companion_getInstance();
  }
  AssertionPairFormatterOption.prototype.withTextSameLineAssertionPairFormatter = function () {
    return this.withTextAssertionPairFormatter_a4qe1c$(getCallableRef('newTextSameLineAssertionPairFormatter', function ($receiver, objectFormatter, translator) {
      return $receiver.newTextSameLineAssertionPairFormatter_w899fu$(objectFormatter, translator);
    }.bind(null, core.coreFactory)));
  };
  AssertionPairFormatterOption.prototype.withTextNextLineAssertionPairFormatter = function () {
    return this.withTextAssertionPairFormatter_a4qe1c$(getCallableRef('newTextNextLineAssertionPairFormatter', function ($receiver, objectFormatter, translator) {
      return $receiver.newTextNextLineAssertionPairFormatter_w899fu$(objectFormatter, translator);
    }.bind(null, core.coreFactory)));
  };
  function AssertionPairFormatterOption$Companion() {
    AssertionPairFormatterOption$Companion_instance = this;
  }
  AssertionPairFormatterOption$Companion.prototype.create_nq23lq$ = function (assertionFormatterFacade, objectFormatter, translator) {
    return this.create_jgevz6$(new AssertionFormatterChosenOptions(assertionFormatterFacade, objectFormatter, translator));
  };
  AssertionPairFormatterOption$Companion.prototype.create_jgevz6$ = function (options) {
    return new AssertionPairFormatterOptionImpl(options);
  };
  AssertionPairFormatterOption$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var AssertionPairFormatterOption$Companion_instance = null;
  function AssertionPairFormatterOption$Companion_getInstance() {
    if (AssertionPairFormatterOption$Companion_instance === null) {
      new AssertionPairFormatterOption$Companion();
    }
    return AssertionPairFormatterOption$Companion_instance;
  }
  AssertionPairFormatterOption.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'AssertionPairFormatterOption',
    interfaces: []
  };
  function AssertionFormatterChosenOptions(assertionFormatterFacade, objectFormatter, translator) {
    this.assertionFormatterFacade = assertionFormatterFacade;
    this.objectFormatter = objectFormatter;
    this.translator = translator;
  }
  AssertionFormatterChosenOptions.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AssertionFormatterChosenOptions',
    interfaces: []
  };
  AssertionFormatterChosenOptions.prototype.component1 = function () {
    return this.assertionFormatterFacade;
  };
  AssertionFormatterChosenOptions.prototype.component2 = function () {
    return this.objectFormatter;
  };
  AssertionFormatterChosenOptions.prototype.component3 = function () {
    return this.translator;
  };
  AssertionFormatterChosenOptions.prototype.copy_nq23lq$ = function (assertionFormatterFacade, objectFormatter, translator) {
    return new AssertionFormatterChosenOptions(assertionFormatterFacade === void 0 ? this.assertionFormatterFacade : assertionFormatterFacade, objectFormatter === void 0 ? this.objectFormatter : objectFormatter, translator === void 0 ? this.translator : translator);
  };
  AssertionFormatterChosenOptions.prototype.toString = function () {
    return 'AssertionFormatterChosenOptions(assertionFormatterFacade=' + Kotlin.toString(this.assertionFormatterFacade) + (', objectFormatter=' + Kotlin.toString(this.objectFormatter)) + (', translator=' + Kotlin.toString(this.translator)) + ')';
  };
  AssertionFormatterChosenOptions.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.assertionFormatterFacade) | 0;
    result = result * 31 + Kotlin.hashCode(this.objectFormatter) | 0;
    result = result * 31 + Kotlin.hashCode(this.translator) | 0;
    return result;
  };
  AssertionFormatterChosenOptions.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.assertionFormatterFacade, other.assertionFormatterFacade) && Kotlin.equals(this.objectFormatter, other.objectFormatter) && Kotlin.equals(this.translator, other.translator)))));
  };
  function AtriumErrorAdjusterCommonOption() {
  }
  AtriumErrorAdjusterCommonOption.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'AtriumErrorAdjusterCommonOption',
    interfaces: []
  };
  function AtriumErrorAdjusterOption() {
    AtriumErrorAdjusterOption$Companion_getInstance();
  }
  function AtriumErrorAdjusterOption$withDefaultAtriumErrorAdjusters$lambda($receiver) {
    $receiver.withRemoveAtriumFromAtriumErrorAdjuster();
    $receiver.withRemoveRunnerAtriumErrorAdjuster();
    return Unit;
  }
  AtriumErrorAdjusterOption.prototype.withDefaultAtriumErrorAdjusters = function () {
    return this.withMultipleAdjusters_bs5h2v$(AtriumErrorAdjusterOption$withDefaultAtriumErrorAdjusters$lambda);
  };
  function AtriumErrorAdjusterOption$Companion() {
    AtriumErrorAdjusterOption$Companion_instance = this;
  }
  AtriumErrorAdjusterOption$Companion.prototype.create_9vo2uw$ = function (assertionFormatterFacade) {
    return new AtriumErrorAdjusterOptionImpl(assertionFormatterFacade);
  };
  AtriumErrorAdjusterOption$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var AtriumErrorAdjusterOption$Companion_instance = null;
  function AtriumErrorAdjusterOption$Companion_getInstance() {
    if (AtriumErrorAdjusterOption$Companion_instance === null) {
      new AtriumErrorAdjusterOption$Companion();
    }
    return AtriumErrorAdjusterOption$Companion_instance;
  }
  AtriumErrorAdjusterOption.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'AtriumErrorAdjusterOption',
    interfaces: [AtriumErrorAdjusterCommonOption]
  };
  function MultipleAdjustersOptionMarker() {
  }
  MultipleAdjustersOptionMarker.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'MultipleAdjustersOptionMarker',
    interfaces: [Annotation]
  };
  function MultipleAdjustersOption() {
    MultipleAdjustersOption$Companion_getInstance();
  }
  function MultipleAdjustersOption$Companion() {
    MultipleAdjustersOption$Companion_instance = this;
  }
  MultipleAdjustersOption$Companion.prototype.create = function () {
    return new MultipleAdjustersOptionImpl();
  };
  MultipleAdjustersOption$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var MultipleAdjustersOption$Companion_instance = null;
  function MultipleAdjustersOption$Companion_getInstance() {
    if (MultipleAdjustersOption$Companion_instance === null) {
      new MultipleAdjustersOption$Companion();
    }
    return MultipleAdjustersOption$Companion_instance;
  }
  MultipleAdjustersOption.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'MultipleAdjustersOption',
    interfaces: [AtriumErrorAdjusterCommonOption]
  };
  function ExpectBuilder() {
    ExpectBuilder$Companion_getInstance();
  }
  function ExpectBuilder$Companion() {
    ExpectBuilder$Companion_instance = this;
  }
  ExpectBuilder$Companion.prototype.forSubject_mh5how$ = function (subject) {
    return new AssertionVerbStepImpl(new Some(subject));
  };
  ExpectBuilder$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var ExpectBuilder$Companion_instance = null;
  function ExpectBuilder$Companion_getInstance() {
    if (ExpectBuilder$Companion_instance === null) {
      new ExpectBuilder$Companion();
    }
    return ExpectBuilder$Companion_instance;
  }
  function ExpectBuilder$AssertionVerbStep() {
  }
  ExpectBuilder$AssertionVerbStep.prototype.withVerb_61zpoe$ = function (verb) {
    return this.withVerb_n3w7xm$(new Untranslatable(verb));
  };
  ExpectBuilder$AssertionVerbStep.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'AssertionVerbStep',
    interfaces: []
  };
  function ExpectBuilder$OptionsStep() {
    ExpectBuilder$OptionsStep$Companion_getInstance();
  }
  ExpectBuilder$OptionsStep.prototype.withOptions_bgu5w4$ = function (configuration) {
    return this.withOptions_rh67mr$(ExpectOptions_0(configuration));
  };
  function ExpectBuilder$OptionsStep$Companion() {
    ExpectBuilder$OptionsStep$Companion_instance = this;
  }
  ExpectBuilder$OptionsStep$Companion.prototype.create_6ct7t7$ = function (maybeSubject, assertionVerb) {
    return new OptionsStepImpl(maybeSubject, assertionVerb);
  };
  ExpectBuilder$OptionsStep$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var ExpectBuilder$OptionsStep$Companion_instance = null;
  function ExpectBuilder$OptionsStep$Companion_getInstance() {
    if (ExpectBuilder$OptionsStep$Companion_instance === null) {
      new ExpectBuilder$OptionsStep$Companion();
    }
    return ExpectBuilder$OptionsStep$Companion_instance;
  }
  ExpectBuilder$OptionsStep.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'OptionsStep',
    interfaces: []
  };
  function ExpectBuilder$OptionsChooser() {
    ExpectBuilder$OptionsChooser$Companion_getInstance();
  }
  ExpectBuilder$OptionsChooser.prototype.withVerb_61zpoe$ = function (verb) {
    this.withVerb_n3w7xm$(new Untranslatable(verb));
  };
  function ExpectBuilder$OptionsChooser$Companion() {
    ExpectBuilder$OptionsChooser$Companion_instance = this;
  }
  ExpectBuilder$OptionsChooser$Companion.prototype.createAndBuild_bgu5w4$ = function (configuration) {
    var $receiver = new OptionsChooserImpl();
    configuration($receiver);
    return $receiver.build();
  };
  ExpectBuilder$OptionsChooser$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var ExpectBuilder$OptionsChooser$Companion_instance = null;
  function ExpectBuilder$OptionsChooser$Companion_getInstance() {
    if (ExpectBuilder$OptionsChooser$Companion_instance === null) {
      new ExpectBuilder$OptionsChooser$Companion();
    }
    return ExpectBuilder$OptionsChooser$Companion_instance;
  }
  ExpectBuilder$OptionsChooser.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'OptionsChooser',
    interfaces: []
  };
  function ExpectBuilder$FinalStep() {
    ExpectBuilder$FinalStep$Companion_getInstance();
  }
  function ExpectBuilder$FinalStep$Companion() {
    ExpectBuilder$FinalStep$Companion_instance = this;
  }
  ExpectBuilder$FinalStep$Companion.prototype.create_z99nu7$ = function (maybeSubject, assertionVerb, options) {
    return new FinalStepImpl_1(maybeSubject, assertionVerb, options);
  };
  ExpectBuilder$FinalStep$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var ExpectBuilder$FinalStep$Companion_instance = null;
  function ExpectBuilder$FinalStep$Companion_getInstance() {
    if (ExpectBuilder$FinalStep$Companion_instance === null) {
      new ExpectBuilder$FinalStep$Companion();
    }
    return ExpectBuilder$FinalStep$Companion_instance;
  }
  ExpectBuilder$FinalStep.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'FinalStep',
    interfaces: []
  };
  ExpectBuilder.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'ExpectBuilder',
    interfaces: []
  };
  function ExpectOptions(assertionVerb, representation, nullRepresentation, reporter) {
    if (assertionVerb === void 0)
      assertionVerb = null;
    if (representation === void 0)
      representation = null;
    if (nullRepresentation === void 0)
      nullRepresentation = null;
    if (reporter === void 0)
      reporter = null;
    this.assertionVerb = assertionVerb;
    this.representation = representation;
    this.nullRepresentation = nullRepresentation;
    this.reporter = reporter;
  }
  ExpectOptions.prototype.merge_rh67mr$ = function (options) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2;
    return new ExpectOptions((tmp$ = options.assertionVerb) != null ? tmp$ : this.assertionVerb, (tmp$_0 = options.representation) != null ? tmp$_0 : this.representation, (tmp$_1 = options.nullRepresentation) != null ? tmp$_1 : this.nullRepresentation, (tmp$_2 = options.reporter) != null ? tmp$_2 : this.reporter);
  };
  ExpectOptions.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ExpectOptions',
    interfaces: []
  };
  ExpectOptions.prototype.component1 = function () {
    return this.assertionVerb;
  };
  ExpectOptions.prototype.component2 = function () {
    return this.representation;
  };
  ExpectOptions.prototype.component3 = function () {
    return this.nullRepresentation;
  };
  ExpectOptions.prototype.component4 = function () {
    return this.reporter;
  };
  ExpectOptions.prototype.copy_kbnb09$ = function (assertionVerb, representation, nullRepresentation, reporter) {
    return new ExpectOptions(assertionVerb === void 0 ? this.assertionVerb : assertionVerb, representation === void 0 ? this.representation : representation, nullRepresentation === void 0 ? this.nullRepresentation : nullRepresentation, reporter === void 0 ? this.reporter : reporter);
  };
  ExpectOptions.prototype.toString = function () {
    return 'ExpectOptions(assertionVerb=' + Kotlin.toString(this.assertionVerb) + (', representation=' + Kotlin.toString(this.representation)) + (', nullRepresentation=' + Kotlin.toString(this.nullRepresentation)) + (', reporter=' + Kotlin.toString(this.reporter)) + ')';
  };
  ExpectOptions.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.assertionVerb) | 0;
    result = result * 31 + Kotlin.hashCode(this.representation) | 0;
    result = result * 31 + Kotlin.hashCode(this.nullRepresentation) | 0;
    result = result * 31 + Kotlin.hashCode(this.reporter) | 0;
    return result;
  };
  ExpectOptions.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.assertionVerb, other.assertionVerb) && Kotlin.equals(this.representation, other.representation) && Kotlin.equals(this.nullRepresentation, other.nullRepresentation) && Kotlin.equals(this.reporter, other.reporter)))));
  };
  function ExpectOptions_0(configuration) {
    return ExpectBuilder$OptionsChooser$Companion_getInstance().createAndBuild_bgu5w4$(configuration);
  }
  function LocaleOrderDeciderOption() {
    LocaleOrderDeciderOption$Companion_getInstance();
  }
  function LocaleOrderDeciderOption$Companion() {
    LocaleOrderDeciderOption$Companion_instance = this;
  }
  LocaleOrderDeciderOption$Companion.prototype.create_s0r2l8$ = function (translationSupplier) {
    return new LocaleOrderDeciderOptionImpl(translationSupplier);
  };
  LocaleOrderDeciderOption$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var LocaleOrderDeciderOption$Companion_instance = null;
  function LocaleOrderDeciderOption$Companion_getInstance() {
    if (LocaleOrderDeciderOption$Companion_instance === null) {
      new LocaleOrderDeciderOption$Companion();
    }
    return LocaleOrderDeciderOption$Companion_instance;
  }
  LocaleOrderDeciderOption.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'LocaleOrderDeciderOption',
    interfaces: []
  };
  function ObjectFormatterOption() {
    ObjectFormatterOption$Companion_getInstance();
  }
  function ObjectFormatterOption$Companion() {
    ObjectFormatterOption$Companion_instance = this;
  }
  ObjectFormatterOption$Companion.prototype.create_9hgjhv$ = function (translator) {
    return new ObjectFormatterOptionImpl(translator);
  };
  ObjectFormatterOption$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var ObjectFormatterOption$Companion_instance = null;
  function ObjectFormatterOption$Companion_getInstance() {
    if (ObjectFormatterOption$Companion_instance === null) {
      new ObjectFormatterOption$Companion();
    }
    return ObjectFormatterOption$Companion_instance;
  }
  ObjectFormatterOption.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'ObjectFormatterOption',
    interfaces: []
  };
  var reporterBuilder;
  function ReporterBuilderCommon() {
  }
  ReporterBuilderCommon.prototype.withoutTranslationsUseDefaultLocale = function () {
    return this.withoutTranslations_6rc0d$(getDefaultLocale());
  };
  ReporterBuilderCommon.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'ReporterBuilderCommon',
    interfaces: []
  };
  function ReporterBuilderFinalStep() {
    ReporterBuilderFinalStep$Companion_getInstance();
  }
  function ReporterBuilderFinalStep$Companion() {
    ReporterBuilderFinalStep$Companion_instance = this;
  }
  ReporterBuilderFinalStep$Companion.prototype.create_hvogvh$ = function (factory) {
    return new ReporterBuilderFinalStepImpl(factory);
  };
  ReporterBuilderFinalStep$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var ReporterBuilderFinalStep$Companion_instance = null;
  function ReporterBuilderFinalStep$Companion_getInstance() {
    if (ReporterBuilderFinalStep$Companion_instance === null) {
      new ReporterBuilderFinalStep$Companion();
    }
    return ReporterBuilderFinalStep$Companion_instance;
  }
  ReporterBuilderFinalStep.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'ReporterBuilderFinalStep',
    interfaces: []
  };
  function ReporterOption() {
    ReporterOption$Companion_getInstance();
  }
  function ReporterOption$Companion() {
    ReporterOption$Companion_instance = this;
  }
  ReporterOption$Companion.prototype.create_xg08ug$ = function (assertionFormatterFacade, atriumErrorAdjuster) {
    return new ReporterOptionImpl(assertionFormatterFacade, atriumErrorAdjuster);
  };
  ReporterOption$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var ReporterOption$Companion_instance = null;
  function ReporterOption$Companion_getInstance() {
    if (ReporterOption$Companion_instance === null) {
      new ReporterOption$Companion();
    }
    return ReporterOption$Companion_instance;
  }
  ReporterOption.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'ReporterOption',
    interfaces: []
  };
  function TextAssertionFormatterOptionCommon() {
  }
  TextAssertionFormatterOptionCommon.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'TextAssertionFormatterOptionCommon',
    interfaces: []
  };
  function TranslatorOptionCommon() {
  }
  TranslatorOptionCommon.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'TranslatorOptionCommon',
    interfaces: []
  };
  function AssertionFormatterControllerOptionImpl(objectFormatter, translator) {
    this.objectFormatter_dsbsjs$_0 = objectFormatter;
    this.translator_eflvhl$_0 = translator;
  }
  Object.defineProperty(AssertionFormatterControllerOptionImpl.prototype, 'objectFormatter', {
    get: function () {
      return this.objectFormatter_dsbsjs$_0;
    }
  });
  Object.defineProperty(AssertionFormatterControllerOptionImpl.prototype, 'translator', {
    get: function () {
      return this.translator_eflvhl$_0;
    }
  });
  AssertionFormatterControllerOptionImpl.prototype.withDefaultAssertionFormatterController = function () {
    return this.withAssertionFormatterController_kxnrei$(core.coreFactory.newAssertionFormatterController());
  };
  AssertionFormatterControllerOptionImpl.prototype.withAssertionFormatterController_kxnrei$ = function (assertionFormatterController) {
    return AssertionFormatterFacadeOption$Companion_getInstance().create_bao0p0$(assertionFormatterController, this.objectFormatter, this.translator);
  };
  AssertionFormatterControllerOptionImpl.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AssertionFormatterControllerOptionImpl',
    interfaces: [AssertionFormatterControllerOption]
  };
  function AssertionFormatterFacadeOptionImpl(assertionFormatterController, objectFormatter, translator) {
    this.assertionFormatterController_bg4jt7$_0 = assertionFormatterController;
    this.objectFormatter_raocg6$_0 = objectFormatter;
    this.translator_du4yqd$_0 = translator;
  }
  Object.defineProperty(AssertionFormatterFacadeOptionImpl.prototype, 'assertionFormatterController', {
    get: function () {
      return this.assertionFormatterController_bg4jt7$_0;
    }
  });
  Object.defineProperty(AssertionFormatterFacadeOptionImpl.prototype, 'objectFormatter', {
    get: function () {
      return this.objectFormatter_raocg6$_0;
    }
  });
  Object.defineProperty(AssertionFormatterFacadeOptionImpl.prototype, 'translator', {
    get: function () {
      return this.translator_du4yqd$_0;
    }
  });
  AssertionFormatterFacadeOptionImpl.prototype.withDefaultAssertionFormatterFacade = function () {
    return this.withAssertionFormatterFacade_s6t19s$(getCallableRef('newAssertionFormatterFacade', function ($receiver, assertionFormatterController) {
      return $receiver.newAssertionFormatterFacade_kxnrei$(assertionFormatterController);
    }.bind(null, core.coreFactory)));
  };
  AssertionFormatterFacadeOptionImpl.prototype.withAssertionFormatterFacade_s6t19s$ = function (factory) {
    return AssertionPairFormatterOption$Companion_getInstance().create_nq23lq$(factory(this.assertionFormatterController), this.objectFormatter, this.translator);
  };
  AssertionFormatterFacadeOptionImpl.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AssertionFormatterFacadeOptionImpl',
    interfaces: [AssertionFormatterFacadeOption]
  };
  function AssertionPairFormatterOptionImpl(options) {
    this.options_n4vxfp$_0 = options;
  }
  Object.defineProperty(AssertionPairFormatterOptionImpl.prototype, 'options', {
    get: function () {
      return this.options_n4vxfp$_0;
    }
  });
  AssertionPairFormatterOptionImpl.prototype.withTextAssertionPairFormatter_a4qe1c$ = function (factory) {
    return TextAssertionFormatterOption$Companion_getInstance().create_9tpuz2$(this.options, factory(this.options.objectFormatter, this.options.translator));
  };
  AssertionPairFormatterOptionImpl.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AssertionPairFormatterOptionImpl',
    interfaces: [AssertionPairFormatterOption]
  };
  function AtriumErrorAdjusterOptionImpl(assertionFormatterFacade) {
    this.assertionFormatterFacade_c73b6z$_0 = assertionFormatterFacade;
  }
  Object.defineProperty(AtriumErrorAdjusterOptionImpl.prototype, 'assertionFormatterFacade', {
    get: function () {
      return this.assertionFormatterFacade_c73b6z$_0;
    }
  });
  AtriumErrorAdjusterOptionImpl.prototype.withNoOpAtriumErrorAdjuster = function () {
    return this.createReporterOption_0(core.coreFactory.newNoOpAtriumErrorAdjuster());
  };
  AtriumErrorAdjusterOptionImpl.prototype.withRemoveAtriumFromAtriumErrorAdjuster = function () {
    return this.createReporterOption_0(core.coreFactory.newRemoveAtriumFromAtriumErrorAdjuster());
  };
  AtriumErrorAdjusterOptionImpl.prototype.withRemoveRunnerAtriumErrorAdjuster = function () {
    return this.createReporterOption_0(core.coreFactory.newRemoveRunnerAtriumErrorAdjuster());
  };
  AtriumErrorAdjusterOptionImpl.prototype.withAtriumErrorAdjuster_4vh2ek$ = function (adjuster) {
    return this.createReporterOption_0(adjuster);
  };
  AtriumErrorAdjusterOptionImpl.prototype.withMultipleAdjusters_bs5h2v$ = function (configure) {
    var $receiver = MultipleAdjustersOption$Companion_getInstance().create();
    configure($receiver);
    var adjusters = $receiver.adjusters;
    if (!(adjusters.size > 1)) {
      var message = 'You need to define at least two adjusters, only ' + adjusters.size + ' defined.';
      throw IllegalArgumentException_init(message.toString());
    }
    var first_0 = first(adjusters);
    var tail = drop(adjusters, 1);
    return this.createReporterOption_0(core.coreFactory.newMultiAtriumErrorAdjuster_5feozf$(first_0, first(tail), drop(tail, 1)));
  };
  AtriumErrorAdjusterOptionImpl.prototype.createReporterOption_0 = function (atriumErrorAdjuster) {
    return ReporterOption$Companion_getInstance().create_xg08ug$(this.assertionFormatterFacade, atriumErrorAdjuster);
  };
  AtriumErrorAdjusterOptionImpl.prototype.withOnlyFailureReporter = function () {
    return this.withDefaultAtriumErrorAdjusters().withOnlyFailureReporter();
  };
  function AtriumErrorAdjusterOptionImpl$withCustomReporter$lambda(closure$factory) {
    return function (facade, f) {
      return closure$factory(facade);
    };
  }
  AtriumErrorAdjusterOptionImpl.prototype.withCustomReporter_3ts4st$ = function (factory) {
    return this.withNoOpAtriumErrorAdjuster().withCustomReporter_c7kdd9$(AtriumErrorAdjusterOptionImpl$withCustomReporter$lambda(factory));
  };
  AtriumErrorAdjusterOptionImpl.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AtriumErrorAdjusterOptionImpl',
    interfaces: [AtriumErrorAdjusterOption]
  };
  function DefaultReporterFactory() {
    this.id_qx5lln$_0 = 'default';
  }
  Object.defineProperty(DefaultReporterFactory.prototype, 'id', {
    get: function () {
      return this.id_qx5lln$_0;
    }
  });
  DefaultReporterFactory.prototype.create = function () {
    return ReporterBuilder$Companion_getInstance().create().withoutTranslationsUseDefaultLocale().withDetailedObjectFormatter().withDefaultAssertionFormatterController().withDefaultAssertionFormatterFacade().withTextSameLineAssertionPairFormatter().withTextCapabilities_6rtt22$([]).withDefaultAtriumErrorAdjusters().withOnlyFailureReporter().build();
  };
  DefaultReporterFactory.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'DefaultReporterFactory',
    interfaces: [ReporterFactory]
  };
  function LocaleOrderDeciderOptionImpl(translationSupplier) {
    this.translationSupplier_bhrr5g$_0 = translationSupplier;
  }
  Object.defineProperty(LocaleOrderDeciderOptionImpl.prototype, 'translationSupplier', {
    get: function () {
      return this.translationSupplier_bhrr5g$_0;
    }
  });
  LocaleOrderDeciderOptionImpl.prototype.withDefaultLocaleOrderDecider = function () {
    return this.withLocaleOrderDecider_p9oky9$(core.coreFactory.newLocaleOrderDecider());
  };
  LocaleOrderDeciderOptionImpl.prototype.withLocaleOrderDecider_p9oky9$ = function (localeOrderDecider) {
    return new TranslatorOptionImpl(this.translationSupplier, localeOrderDecider);
  };
  LocaleOrderDeciderOptionImpl.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LocaleOrderDeciderOptionImpl',
    interfaces: [LocaleOrderDeciderOption]
  };
  function MultipleAdjustersOptionImpl() {
    this._adjusters_0 = ArrayList_init();
  }
  Object.defineProperty(MultipleAdjustersOptionImpl.prototype, 'adjusters', {
    get: function () {
      return this._adjusters_0;
    }
  });
  MultipleAdjustersOptionImpl.prototype.withRemoveRunnerAtriumErrorAdjuster = function () {
    this._adjusters_0.add_11rb$(core.coreFactory.newRemoveRunnerAtriumErrorAdjuster());
    return Unit;
  };
  MultipleAdjustersOptionImpl.prototype.withRemoveAtriumFromAtriumErrorAdjuster = function () {
    this._adjusters_0.add_11rb$(core.coreFactory.newRemoveAtriumFromAtriumErrorAdjuster());
    return Unit;
  };
  MultipleAdjustersOptionImpl.prototype.withAtriumErrorAdjuster_4vh2ek$ = function (adjuster) {
    this._adjusters_0.add_11rb$(adjuster);
    return Unit;
  };
  MultipleAdjustersOptionImpl.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'MultipleAdjustersOptionImpl',
    interfaces: [MultipleAdjustersOption]
  };
  function ObjectFormatterOptionImpl(translator) {
    this.translator_juhlsq$_0 = translator;
  }
  Object.defineProperty(ObjectFormatterOptionImpl.prototype, 'translator', {
    get: function () {
      return this.translator_juhlsq$_0;
    }
  });
  ObjectFormatterOptionImpl.prototype.withDetailedObjectFormatter = function () {
    return this.withObjectFormatter_h0zbzc$(getCallableRef('newDetailedObjectFormatter', function ($receiver, translator) {
      return $receiver.newDetailedObjectFormatter_9hgjhv$(translator);
    }.bind(null, core.coreFactory)));
  };
  ObjectFormatterOptionImpl.prototype.withObjectFormatter_h0zbzc$ = function (factory) {
    return AssertionFormatterControllerOption$Companion_getInstance().create_w899fu$(factory(this.translator), this.translator);
  };
  ObjectFormatterOptionImpl.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ObjectFormatterOptionImpl',
    interfaces: [ObjectFormatterOption]
  };
  function ReporterBuilderFinalStepImpl(factory) {
    this.factory_0 = factory;
  }
  ReporterBuilderFinalStepImpl.prototype.build = function () {
    return this.factory_0();
  };
  ReporterBuilderFinalStepImpl.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ReporterBuilderFinalStepImpl',
    interfaces: [ReporterBuilderFinalStep]
  };
  function ReporterOptionImpl(assertionFormatterFacade, atriumErrorAdjuster) {
    this.assertionFormatterFacade_qqjpe4$_0 = assertionFormatterFacade;
    this.atriumErrorAdjuster_6lrjic$_0 = atriumErrorAdjuster;
  }
  Object.defineProperty(ReporterOptionImpl.prototype, 'assertionFormatterFacade', {
    get: function () {
      return this.assertionFormatterFacade_qqjpe4$_0;
    }
  });
  Object.defineProperty(ReporterOptionImpl.prototype, 'atriumErrorAdjuster', {
    get: function () {
      return this.atriumErrorAdjuster_6lrjic$_0;
    }
  });
  ReporterOptionImpl.prototype.withOnlyFailureReporter = function () {
    return this.withCustomReporter_c7kdd9$(getCallableRef('newOnlyFailureReporter', function ($receiver, assertionFormatterFacade, atriumErrorAdjuster) {
      return $receiver.newOnlyFailureReporter_xg08ug$(assertionFormatterFacade, atriumErrorAdjuster);
    }.bind(null, core.coreFactory)));
  };
  function ReporterOptionImpl$withCustomReporter$lambda(closure$factory, this$ReporterOptionImpl) {
    return function () {
      return closure$factory(this$ReporterOptionImpl.assertionFormatterFacade, this$ReporterOptionImpl.atriumErrorAdjuster);
    };
  }
  ReporterOptionImpl.prototype.withCustomReporter_c7kdd9$ = function (factory) {
    return ReporterBuilderFinalStep$Companion_getInstance().create_hvogvh$(ReporterOptionImpl$withCustomReporter$lambda(factory, this));
  };
  ReporterOptionImpl.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ReporterOptionImpl',
    interfaces: [ReporterOption]
  };
  function TextAssertionFormatterOptionImpl(options, assertionPairFormatter) {
    this.options_xcizak$_0 = options;
    this.assertionPairFormatter_1lc40k$_0 = assertionPairFormatter;
  }
  Object.defineProperty(TextAssertionFormatterOptionImpl.prototype, 'options', {
    get: function () {
      return this.options_xcizak$_0;
    }
  });
  Object.defineProperty(TextAssertionFormatterOptionImpl.prototype, 'assertionPairFormatter', {
    get: function () {
      return this.assertionPairFormatter_1lc40k$_0;
    }
  });
  TextAssertionFormatterOptionImpl.prototype.withTextCapabilities_6rtt22$ = function (bulletPoints) {
    core.coreFactory.registerTextAssertionFormatterCapabilities_i11dch$(toMap(bulletPoints), this.options.assertionFormatterFacade, this.assertionPairFormatter, this.options.objectFormatter, this.options.translator);
    return AtriumErrorAdjusterOption$Companion_getInstance().create_9vo2uw$(this.options.assertionFormatterFacade);
  };
  TextAssertionFormatterOptionImpl.prototype.withTextAssertionFormatter_rmxq2f$ = function (factory, otherFactories) {
    var iterable = asIterable(otherFactories);
    this.options.assertionFormatterFacade.register_kbpzuy$(factory(this.options));
    var tmp$;
    tmp$ = iterable.iterator();
    while (tmp$.hasNext()) {
      var element_0 = tmp$.next();
      this.options.assertionFormatterFacade.register_kbpzuy$(element_0(this.options));
    }
    return AtriumErrorAdjusterOption$Companion_getInstance().create_9vo2uw$(this.options.assertionFormatterFacade);
  };
  TextAssertionFormatterOptionImpl.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'TextAssertionFormatterOptionImpl',
    interfaces: [TextAssertionFormatterOption]
  };
  function TranslatorOptionImpl(translationSupplier, localeOrderDecider) {
    this.translationSupplier_78ekuo$_0 = translationSupplier;
    this.localeOrderDecider_psf89d$_0 = localeOrderDecider;
  }
  Object.defineProperty(TranslatorOptionImpl.prototype, 'translationSupplier', {
    get: function () {
      return this.translationSupplier_78ekuo$_0;
    }
  });
  Object.defineProperty(TranslatorOptionImpl.prototype, 'localeOrderDecider', {
    get: function () {
      return this.localeOrderDecider_psf89d$_0;
    }
  });
  TranslatorOptionImpl.prototype.withDefaultTranslator_eeixxd$ = function (primaryLocale, fallbackLocales) {
    return new ObjectFormatterOptionImpl(core.coreFactory.newTranslator_eiztks$(this.translationSupplier, this.localeOrderDecider, primaryLocale, toList(fallbackLocales)));
  };
  TranslatorOptionImpl.prototype.withTranslator_x7mru$ = function (factory) {
    return new ObjectFormatterOptionImpl(factory(this.translationSupplier, this.localeOrderDecider));
  };
  TranslatorOptionImpl.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'TranslatorOptionImpl',
    interfaces: [TranslatorOption]
  };
  function OptionsChooserImpl() {
    this.description_0 = null;
    this.representation_0 = null;
    this.nullRepresentation_0 = null;
    this.reporter_0 = null;
  }
  OptionsChooserImpl.prototype.withVerb_n3w7xm$ = function (verb) {
    this.description_0 = verb;
  };
  OptionsChooserImpl.prototype.withRepresentation_za3rmp$ = function (representation) {
    this.representation_0 = representation;
  };
  OptionsChooserImpl.prototype.withNullRepresentation_za3rmp$ = function (representation) {
    this.nullRepresentation_0 = representation;
  };
  OptionsChooserImpl.prototype.withReporter_22a895$ = function (reporter) {
    this.reporter_0 = reporter;
  };
  OptionsChooserImpl.prototype.build = function () {
    return new ExpectOptions(this.description_0, this.representation_0, this.nullRepresentation_0, this.reporter_0);
  };
  OptionsChooserImpl.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'OptionsChooserImpl',
    interfaces: [ExpectBuilder$OptionsChooser]
  };
  function AssertionVerbStepImpl(maybeSubject) {
    this.maybeSubject_mpvdbm$_0 = maybeSubject;
  }
  Object.defineProperty(AssertionVerbStepImpl.prototype, 'maybeSubject', {
    get: function () {
      return this.maybeSubject_mpvdbm$_0;
    }
  });
  AssertionVerbStepImpl.prototype.withVerb_n3w7xm$ = function (verb) {
    return ExpectBuilder$OptionsStep$Companion_getInstance().create_6ct7t7$(this.maybeSubject, verb);
  };
  AssertionVerbStepImpl.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AssertionVerbStepImpl',
    interfaces: [ExpectBuilder$AssertionVerbStep]
  };
  function OptionsStepImpl(maybeSubject, assertionVerb) {
    this.maybeSubject_bua3rj$_0 = maybeSubject;
    this.assertionVerb_jf2k74$_0 = assertionVerb;
  }
  Object.defineProperty(OptionsStepImpl.prototype, 'maybeSubject', {
    get: function () {
      return this.maybeSubject_bua3rj$_0;
    }
  });
  Object.defineProperty(OptionsStepImpl.prototype, 'assertionVerb', {
    get: function () {
      return this.assertionVerb_jf2k74$_0;
    }
  });
  OptionsStepImpl.prototype.withOptions_rh67mr$ = function (expectOptions) {
    return this.toFinalStep_0(expectOptions);
  };
  OptionsStepImpl.prototype.withoutOptions = function () {
    return this.toFinalStep_0(null);
  };
  OptionsStepImpl.prototype.toFinalStep_0 = function (expectOptions) {
    return ExpectBuilder$FinalStep$Companion_getInstance().create_z99nu7$(this.maybeSubject, this.assertionVerb, expectOptions);
  };
  OptionsStepImpl.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'OptionsStepImpl',
    interfaces: [ExpectBuilder$OptionsStep]
  };
  function FinalStepImpl_1(maybeSubject, assertionVerb, options) {
    this.maybeSubject_99oti1$_0 = maybeSubject;
    this.assertionVerb_4a0eqw$_0 = assertionVerb;
    this.options_jte7u3$_0 = options;
  }
  Object.defineProperty(FinalStepImpl_1.prototype, 'maybeSubject', {
    get: function () {
      return this.maybeSubject_99oti1$_0;
    }
  });
  Object.defineProperty(FinalStepImpl_1.prototype, 'assertionVerb', {
    get: function () {
      return this.assertionVerb_4a0eqw$_0;
    }
  });
  Object.defineProperty(FinalStepImpl_1.prototype, 'options', {
    get: function () {
      return this.options_jte7u3$_0;
    }
  });
  FinalStepImpl_1.prototype.build = function () {
    var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4, tmp$_5, tmp$_6, tmp$_7, tmp$_8, tmp$_9, tmp$_10;
    tmp$_10 = core.coreFactory;
    tmp$_9 = ReportingAssertionContainer$AssertionCheckerDecorator.Companion;
    tmp$_1 = (tmp$_0 = (tmp$ = this.options) != null ? tmp$.assertionVerb : null) != null ? tmp$_0 : this.assertionVerb;
    tmp$_2 = this.maybeSubject;
    var tmp$_11;
    if ((tmp$_4 = (tmp$_3 = this.options) != null ? tmp$_3.representation : null) != null)
      tmp$_11 = tmp$_4;
    else {
      var $receiver = this.maybeSubject;
      var fold_2f1hkh$result;
      if (Kotlin.isType($receiver, Some)) {
        fold_2f1hkh$result = $receiver.value;
      }
       else if (equals($receiver, core.None)) {
        fold_2f1hkh$result = RawString.Companion.create_61zpoe$(reporting.SHOULD_NOT_BE_SHOWN_TO_THE_USER_BUG);
      }
       else {
        fold_2f1hkh$result = Kotlin.noWhenBranchMatched();
      }
      tmp$_11 = fold_2f1hkh$result;
    }
    return tmp$_10.newReportingAssertionContainer_gglseu$(tmp$_9.create_dd5zo7$(tmp$_1, tmp$_2, tmp$_11, core.coreFactory.newThrowingAssertionChecker_22a895$((tmp$_6 = (tmp$_5 = this.options) != null ? tmp$_5.reporter : null) != null ? tmp$_6 : reporting.reporter), (tmp$_8 = (tmp$_7 = this.options) != null ? tmp$_7.nullRepresentation : null) != null ? tmp$_8 : RawString.Companion.NULL));
  };
  FinalStepImpl_1.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'FinalStepImpl',
    interfaces: [ExpectBuilder$FinalStep]
  };
  function VarArgHelper() {
  }
  Object.defineProperty(VarArgHelper.prototype, 'mapArguments', {
    get: function () {
      return new ArgumentMapperBuilder(this.expected, this.otherExpected);
    }
  });
  VarArgHelper.prototype.toList = function () {
    return varargToList(this.expected, this.otherExpected);
  };
  VarArgHelper.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'VarArgHelper',
    interfaces: []
  };
  function validateAtMost(times, atMostCall, atLeastCall, exactlyCall) {
    if (!(1 !== times)) {
      var message = 'use ' + exactlyCall(times) + ' instead of ' + atMostCall(times) + '; ' + (atMostCall(times) + ' defines implicitly ' + atLeastCall(1) + ' as well');
      throw IllegalArgumentException_init(message.toString());
    }
  }
  function validateButAtMost(atLeastTimes, butAtMostTimes, atLeastButAtMostCall, atLeastCall, butAtMostCall, exactlyCall) {
    if (!(atLeastTimes !== butAtMostTimes)) {
      var message = 'use ' + exactlyCall(butAtMostTimes) + ' instead of ' + atLeastButAtMostCall(butAtMostTimes, butAtMostTimes);
      throw IllegalArgumentException_init(message.toString());
    }
    if (!(atLeastTimes < butAtMostTimes)) {
      var message_0 = 'specifying ' + butAtMostCall(butAtMostTimes) + ' does not make sense if ' + atLeastCall(atLeastTimes) + ' was used before';
      throw IllegalArgumentException_init(message_0.toString());
    }
  }
  function Group() {
  }
  Group.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'Group',
    interfaces: []
  };
  function GroupWithoutNullableEntries() {
  }
  GroupWithoutNullableEntries.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'GroupWithoutNullableEntries',
    interfaces: [Group]
  };
  function GroupWithNullableEntries() {
  }
  GroupWithNullableEntries.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'GroupWithNullableEntries',
    interfaces: [Group]
  };
  function groupsToList(firstGroup, secondGroup, otherGroups) {
    var groups = ArrayList_init_0(otherGroups.length + 2 | 0);
    requireNotEmptyAndAdd(groups, firstGroup);
    requireNotEmptyAndAdd(groups, secondGroup);
    var tmp$;
    for (tmp$ = 0; tmp$ !== otherGroups.length; ++tmp$) {
      var element = otherGroups[tmp$];
      requireNotEmptyAndAdd(groups, element);
    }
    return groups;
  }
  function requireNotEmptyAndAdd(groups, group) {
    var list = group.toList();
    if (!!list.isEmpty()) {
      var message = 'a group of values cannot be empty.';
      throw IllegalArgumentException_init(message.toString());
    }
    groups.add_11rb$(list);
  }
  function mapArguments(first, others) {
    return new ArgumentMapperBuilder(first, others);
  }
  var mapArguments_0 = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.utils.mapArguments_dsbepb$', wrapFunction(function () {
    var mapArguments = _.ch.tutteli.atrium.domain.builders.utils.mapArguments_r59i0z$;
    var to = Kotlin.kotlin.to_ujzrz7$;
    var ArrayList_init = Kotlin.kotlin.collections.ArrayList_init_ww73n8$;
    var copyToArray = Kotlin.kotlin.collections.copyToArray;
    return function (R_0, isR, first, others, mapper) {
      var $this = mapArguments(first, others);
      var tmp$ = mapper($this.first);
      var $receiver = $this.others;
      var destination = ArrayList_init($receiver.length);
      var tmp$_0;
      for (tmp$_0 = 0; tmp$_0 !== $receiver.length; ++tmp$_0) {
        var item = $receiver[tmp$_0];
        destination.add_11rb$(mapper(item));
      }
      return to(tmp$, copyToArray(destination));
    };
  }));
  function mapArguments_1(first, others) {
    return mapArguments(first, toTypedArray(others));
  }
  var mapArguments_2 = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.utils.mapArguments_1eioew$', wrapFunction(function () {
    var mapArguments = _.ch.tutteli.atrium.domain.builders.utils.mapArguments_63hmpn$;
    var to = Kotlin.kotlin.to_ujzrz7$;
    var ArrayList_init = Kotlin.kotlin.collections.ArrayList_init_ww73n8$;
    var copyToArray = Kotlin.kotlin.collections.copyToArray;
    return function (R_0, isR, first, others, mapper) {
      var $this = mapArguments(first, others);
      var tmp$ = mapper($this.first);
      var $receiver = $this.others;
      var destination = ArrayList_init($receiver.length);
      var tmp$_0;
      for (tmp$_0 = 0; tmp$_0 !== $receiver.length; ++tmp$_0) {
        var item = $receiver[tmp$_0];
        destination.add_11rb$(mapper(item));
      }
      return to(tmp$, copyToArray(destination));
    };
  }));
  function mapArguments_3(first, others) {
    return mapArguments(toBoxedChar(first), toTypedArray_0(others));
  }
  var mapArguments_4 = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.utils.mapArguments_8ffogq$', wrapFunction(function () {
    var mapArguments = _.ch.tutteli.atrium.domain.builders.utils.mapArguments_m72iov$;
    var to = Kotlin.kotlin.to_ujzrz7$;
    var ArrayList_init = Kotlin.kotlin.collections.ArrayList_init_ww73n8$;
    var copyToArray = Kotlin.kotlin.collections.copyToArray;
    return function (R_0, isR, first, others, mapper) {
      var $this = mapArguments(first, others);
      var tmp$ = mapper($this.first);
      var $receiver = $this.others;
      var destination = ArrayList_init($receiver.length);
      var tmp$_0;
      for (tmp$_0 = 0; tmp$_0 !== $receiver.length; ++tmp$_0) {
        var item = $receiver[tmp$_0];
        destination.add_11rb$(mapper(item));
      }
      return to(tmp$, copyToArray(destination));
    };
  }));
  function mapArguments_5(first, others) {
    return mapArguments(first, toTypedArray_1(others));
  }
  var mapArguments_6 = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.utils.mapArguments_ckkfuc$', wrapFunction(function () {
    var mapArguments = _.ch.tutteli.atrium.domain.builders.utils.mapArguments_9qrz6b$;
    var to = Kotlin.kotlin.to_ujzrz7$;
    var ArrayList_init = Kotlin.kotlin.collections.ArrayList_init_ww73n8$;
    var copyToArray = Kotlin.kotlin.collections.copyToArray;
    return function (R_0, isR, first, others, mapper) {
      var $this = mapArguments(first, others);
      var tmp$ = mapper($this.first);
      var $receiver = $this.others;
      var destination = ArrayList_init($receiver.length);
      var tmp$_0;
      for (tmp$_0 = 0; tmp$_0 !== $receiver.length; ++tmp$_0) {
        var item = $receiver[tmp$_0];
        destination.add_11rb$(mapper(item));
      }
      return to(tmp$, copyToArray(destination));
    };
  }));
  function mapArguments_7(first, others) {
    return mapArguments(first, toTypedArray_2(others));
  }
  var mapArguments_8 = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.utils.mapArguments_vv7zq7$', wrapFunction(function () {
    var mapArguments = _.ch.tutteli.atrium.domain.builders.utils.mapArguments_aio0fn$;
    var to = Kotlin.kotlin.to_ujzrz7$;
    var ArrayList_init = Kotlin.kotlin.collections.ArrayList_init_ww73n8$;
    var copyToArray = Kotlin.kotlin.collections.copyToArray;
    return function (R_0, isR, first, others, mapper) {
      var $this = mapArguments(first, others);
      var tmp$ = mapper($this.first);
      var $receiver = $this.others;
      var destination = ArrayList_init($receiver.length);
      var tmp$_0;
      for (tmp$_0 = 0; tmp$_0 !== $receiver.length; ++tmp$_0) {
        var item = $receiver[tmp$_0];
        destination.add_11rb$(mapper(item));
      }
      return to(tmp$, copyToArray(destination));
    };
  }));
  function mapArguments_9(first, others) {
    return mapArguments(first, toTypedArray_3(others));
  }
  var mapArguments_10 = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.utils.mapArguments_p26mj0$', wrapFunction(function () {
    var mapArguments = _.ch.tutteli.atrium.domain.builders.utils.mapArguments_hkm0fh$;
    var to = Kotlin.kotlin.to_ujzrz7$;
    var ArrayList_init = Kotlin.kotlin.collections.ArrayList_init_ww73n8$;
    var copyToArray = Kotlin.kotlin.collections.copyToArray;
    return function (R_0, isR, first, others, mapper) {
      var $this = mapArguments(first, others);
      var tmp$ = mapper($this.first);
      var $receiver = $this.others;
      var destination = ArrayList_init($receiver.length);
      var tmp$_0;
      for (tmp$_0 = 0; tmp$_0 !== $receiver.length; ++tmp$_0) {
        var item = $receiver[tmp$_0];
        destination.add_11rb$(mapper(item));
      }
      return to(tmp$, copyToArray(destination));
    };
  }));
  function mapArguments_11(first, others) {
    return mapArguments(first, toTypedArray_4(others));
  }
  var mapArguments_12 = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.utils.mapArguments_eqv5g4$', wrapFunction(function () {
    var mapArguments = _.ch.tutteli.atrium.domain.builders.utils.mapArguments_f6ygq5$;
    var to = Kotlin.kotlin.to_ujzrz7$;
    var ArrayList_init = Kotlin.kotlin.collections.ArrayList_init_ww73n8$;
    var copyToArray = Kotlin.kotlin.collections.copyToArray;
    return function (R_0, isR, first, others, mapper) {
      var $this = mapArguments(first, others);
      var tmp$ = mapper($this.first);
      var $receiver = $this.others;
      var destination = ArrayList_init($receiver.length);
      var tmp$_0;
      for (tmp$_0 = 0; tmp$_0 !== $receiver.length; ++tmp$_0) {
        var item = $receiver[tmp$_0];
        destination.add_11rb$(mapper(item));
      }
      return to(tmp$, copyToArray(destination));
    };
  }));
  function mapArguments_13(first, others) {
    return mapArguments(first, toTypedArray_5(others));
  }
  var mapArguments_14 = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.utils.mapArguments_p9en4x$', wrapFunction(function () {
    var mapArguments = _.ch.tutteli.atrium.domain.builders.utils.mapArguments_4avpt5$;
    var to = Kotlin.kotlin.to_ujzrz7$;
    var ArrayList_init = Kotlin.kotlin.collections.ArrayList_init_ww73n8$;
    var copyToArray = Kotlin.kotlin.collections.copyToArray;
    return function (R_0, isR, first, others, mapper) {
      var $this = mapArguments(first, others);
      var tmp$ = mapper($this.first);
      var $receiver = $this.others;
      var destination = ArrayList_init($receiver.length);
      var tmp$_0;
      for (tmp$_0 = 0; tmp$_0 !== $receiver.length; ++tmp$_0) {
        var item = $receiver[tmp$_0];
        destination.add_11rb$(mapper(item));
      }
      return to(tmp$, copyToArray(destination));
    };
  }));
  function mapArguments_15(first, others) {
    return mapArguments(first, toTypedArray_6(others));
  }
  var mapArguments_16 = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.utils.mapArguments_vnhmvc$', wrapFunction(function () {
    var mapArguments = _.ch.tutteli.atrium.domain.builders.utils.mapArguments_5vfawd$;
    var to = Kotlin.kotlin.to_ujzrz7$;
    var ArrayList_init = Kotlin.kotlin.collections.ArrayList_init_ww73n8$;
    var copyToArray = Kotlin.kotlin.collections.copyToArray;
    return function (R_0, isR, first, others, mapper) {
      var $this = mapArguments(first, others);
      var tmp$ = mapper($this.first);
      var $receiver = $this.others;
      var destination = ArrayList_init($receiver.length);
      var tmp$_0;
      for (tmp$_0 = 0; tmp$_0 !== $receiver.length; ++tmp$_0) {
        var item = $receiver[tmp$_0];
        destination.add_11rb$(mapper(item));
      }
      return to(tmp$, copyToArray(destination));
    };
  }));
  function ArgumentMapperBuilder(first, others) {
    this.first = first;
    this.others = others;
  }
  ArgumentMapperBuilder.prototype.toExpect_7t1ggy$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.utils.ArgumentMapperBuilder.toExpect_7t1ggy$', wrapFunction(function () {
    var Unit = Kotlin.kotlin.Unit;
    var to = Kotlin.kotlin.to_ujzrz7$;
    var ArrayList_init = Kotlin.kotlin.collections.ArrayList_init_ww73n8$;
    var copyToArray = Kotlin.kotlin.collections.copyToArray;
    function ArgumentMapperBuilder$toExpect$lambda$lambda(closure$assertionCreator, closure$t) {
      return function ($receiver) {
        closure$assertionCreator($receiver, closure$t);
        return Unit;
      };
    }
    return function (R_0, isR, assertionCreator) {
      var tmp$ = ArgumentMapperBuilder$toExpect$lambda$lambda(assertionCreator, this.first);
      var $receiver = this.others;
      var destination = ArrayList_init($receiver.length);
      var tmp$_0;
      for (tmp$_0 = 0; tmp$_0 !== $receiver.length; ++tmp$_0) {
        var item = $receiver[tmp$_0];
        destination.add_11rb$(ArgumentMapperBuilder$toExpect$lambda$lambda(assertionCreator, item));
      }
      return to(tmp$, copyToArray(destination));
    };
  }));
  ArgumentMapperBuilder.prototype.toAssert_ac9zug$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.utils.ArgumentMapperBuilder.toAssert_ac9zug$', wrapFunction(function () {
    var Unit = Kotlin.kotlin.Unit;
    var to = Kotlin.kotlin.to_ujzrz7$;
    var ArrayList_init = Kotlin.kotlin.collections.ArrayList_init_ww73n8$;
    var copyToArray = Kotlin.kotlin.collections.copyToArray;
    function ArgumentMapperBuilder$toAssert$lambda$lambda(closure$assertionCreator, closure$t) {
      return function ($receiver) {
        closure$assertionCreator($receiver, closure$t);
        return Unit;
      };
    }
    return function (R_0, isR, assertionCreator) {
      var tmp$ = ArgumentMapperBuilder$toAssert$lambda$lambda(assertionCreator, this.first);
      var $receiver = this.others;
      var destination = ArrayList_init($receiver.length);
      var tmp$_0;
      for (tmp$_0 = 0; tmp$_0 !== $receiver.length; ++tmp$_0) {
        var item = $receiver[tmp$_0];
        destination.add_11rb$(ArgumentMapperBuilder$toAssert$lambda$lambda(assertionCreator, item));
      }
      return to(tmp$, copyToArray(destination));
    };
  }));
  ArgumentMapperBuilder.prototype.toAssertionPlantNullable_j75w1i$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.utils.ArgumentMapperBuilder.toAssertionPlantNullable_j75w1i$', wrapFunction(function () {
    var Unit = Kotlin.kotlin.Unit;
    var to = Kotlin.kotlin.to_ujzrz7$;
    var ArrayList_init = Kotlin.kotlin.collections.ArrayList_init_ww73n8$;
    var copyToArray = Kotlin.kotlin.collections.copyToArray;
    function ArgumentMapperBuilder$toAssertionPlantNullable$lambda$lambda(closure$assertionCreator, closure$t) {
      return function ($receiver) {
        closure$assertionCreator($receiver, closure$t);
        return Unit;
      };
    }
    return function (R_0, isR, assertionCreator) {
      var tmp$ = ArgumentMapperBuilder$toAssertionPlantNullable$lambda$lambda(assertionCreator, this.first);
      var $receiver = this.others;
      var destination = ArrayList_init($receiver.length);
      var tmp$_0;
      for (tmp$_0 = 0; tmp$_0 !== $receiver.length; ++tmp$_0) {
        var item = $receiver[tmp$_0];
        destination.add_11rb$(ArgumentMapperBuilder$toAssertionPlantNullable$lambda$lambda(assertionCreator, item));
      }
      return to(tmp$, copyToArray(destination));
    };
  }));
  ArgumentMapperBuilder.prototype.to_2o04qz$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.utils.ArgumentMapperBuilder.to_2o04qz$', wrapFunction(function () {
    var to = Kotlin.kotlin.to_ujzrz7$;
    var ArrayList_init = Kotlin.kotlin.collections.ArrayList_init_ww73n8$;
    var copyToArray = Kotlin.kotlin.collections.copyToArray;
    return function (R_0, isR, mapper) {
      var tmp$ = mapper(this.first);
      var $receiver = this.others;
      var destination = ArrayList_init($receiver.length);
      var tmp$_0;
      for (tmp$_0 = 0; tmp$_0 !== $receiver.length; ++tmp$_0) {
        var item = $receiver[tmp$_0];
        destination.add_11rb$(mapper(item));
      }
      return to(tmp$, copyToArray(destination));
    };
  }));
  ArgumentMapperBuilder.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ArgumentMapperBuilder',
    interfaces: []
  };
  function toNullOr($receiver) {
    return new ArgumentToNullOrMapperBuilder($receiver);
  }
  function toNullOr($receiver) {
    throw new PleaseUseReplacementException('Since your arguments are not nullable it does not make sense to call this function.');
  }
  function ArgumentToNullOrMapperBuilder(argumentMapperBuilder) {
    this.argumentMapperBuilder = argumentMapperBuilder;
  }
  ArgumentToNullOrMapperBuilder.prototype.toExpect_56o28n$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.utils.ArgumentToNullOrMapperBuilder.toExpect_56o28n$', wrapFunction(function () {
    var Unit = Kotlin.kotlin.Unit;
    var to = Kotlin.kotlin.to_ujzrz7$;
    var ArrayList_init = Kotlin.kotlin.collections.ArrayList_init_ww73n8$;
    var copyToArray = Kotlin.kotlin.collections.copyToArray;
    function ArgumentToNullOrMapperBuilder$toExpect$lambda$lambda$lambda(closure$assertionCreator, closure$t) {
      return function ($receiver) {
        closure$assertionCreator($receiver, closure$t);
        return Unit;
      };
    }
    return function (assertionCreator) {
      var $this = this.argumentMapperBuilder;
      var nullableT = $this.first;
      var tmp$ = nullableT != null ? ArgumentToNullOrMapperBuilder$toExpect$lambda$lambda$lambda(assertionCreator, nullableT) : null;
      var $receiver = $this.others;
      var destination = ArrayList_init($receiver.length);
      var tmp$_0;
      for (tmp$_0 = 0; tmp$_0 !== $receiver.length; ++tmp$_0) {
        var item = $receiver[tmp$_0];
        destination.add_11rb$(item != null ? ArgumentToNullOrMapperBuilder$toExpect$lambda$lambda$lambda(assertionCreator, item) : null);
      }
      return to(tmp$, copyToArray(destination));
    };
  }));
  ArgumentToNullOrMapperBuilder.prototype.toAssert_nu9jkw$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.utils.ArgumentToNullOrMapperBuilder.toAssert_nu9jkw$', wrapFunction(function () {
    var Unit = Kotlin.kotlin.Unit;
    var to = Kotlin.kotlin.to_ujzrz7$;
    var ArrayList_init = Kotlin.kotlin.collections.ArrayList_init_ww73n8$;
    var copyToArray = Kotlin.kotlin.collections.copyToArray;
    function ArgumentToNullOrMapperBuilder$toAssert$lambda$lambda$lambda(closure$assertionCreator, closure$t) {
      return function ($receiver) {
        closure$assertionCreator($receiver, closure$t);
        return Unit;
      };
    }
    return function (assertionCreator) {
      var $this = this.argumentMapperBuilder;
      var nullableT = $this.first;
      var tmp$ = nullableT != null ? ArgumentToNullOrMapperBuilder$toAssert$lambda$lambda$lambda(assertionCreator, nullableT) : null;
      var $receiver = $this.others;
      var destination = ArrayList_init($receiver.length);
      var tmp$_0;
      for (tmp$_0 = 0; tmp$_0 !== $receiver.length; ++tmp$_0) {
        var item = $receiver[tmp$_0];
        destination.add_11rb$(item != null ? ArgumentToNullOrMapperBuilder$toAssert$lambda$lambda$lambda(assertionCreator, item) : null);
      }
      return to(tmp$, copyToArray(destination));
    };
  }));
  ArgumentToNullOrMapperBuilder.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ArgumentToNullOrMapperBuilder',
    interfaces: []
  };
  var nullable = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.utils.nullable_mh5how$', function (t) {
    return t;
  });
  var nullable_0 = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.utils.nullable_j9u1pg$', function (t) {
    return t;
  });
  var nullableContainer = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.utils.nullableContainer_yl67zr$', function (iterable) {
    return iterable;
  });
  var nullableContainer_0 = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.utils.nullableContainer_5x1j0d$', function (arr) {
    return arr;
  });
  var nullableKeyMap = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.utils.nullableKeyMap_zhczhj$', function (map) {
    return map;
  });
  var nullableValueMap = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.utils.nullableValueMap_2gsboo$', function (map) {
    return map;
  });
  var nullableKeyValueMap = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.utils.nullableKeyValueMap_73mtqc$', function (map) {
    return map;
  });
  var nullable_1 = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.utils.nullable_rdzs90$', function (t) {
    return t;
  });
  var nullable_2 = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.utils.nullable_it1gp8$', function (t) {
    return t;
  });
  var nullable_3 = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.utils.nullable_e2ncze$', function (t) {
    return t;
  });
  var nullable_4 = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.utils.nullable_v91fg0$', function (t) {
    return t;
  });
  var nullable_5 = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.utils.nullable_3qeua4$', function (t) {
    return t;
  });
  var subAssert = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.utils.subAssert_e8co0r$', function (assertionCreator) {
    return assertionCreator;
  });
  var subAssertNullable = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.utils.subAssertNullable_4m3k9$', function (assertionCreator) {
    return assertionCreator;
  });
  var subExpect = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.utils.subExpect_jnzojz$', function (assertionCreator) {
    return assertionCreator;
  });
  function FloatingPointAssertionsBuilder() {
    FloatingPointAssertionsBuilder_instance = this;
  }
  FloatingPointAssertionsBuilder.prototype.toBeWithErrorTolerance_bvnqyq$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FloatingPointAssertionsBuilder.toBeWithErrorTolerance_bvnqyq$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (subjectProvider, expected, tolerance) {
      return creating.floatingPointAssertions.toBeWithErrorTolerance_bvnqyq$(subjectProvider, expected, tolerance);
    };
  }));
  FloatingPointAssertionsBuilder.prototype.toBeWithErrorTolerance_afszox$ = defineInlineFunction('atrium-domain-builders-js.ch.tutteli.atrium.domain.builders.creating.FloatingPointAssertionsBuilder.toBeWithErrorTolerance_afszox$', wrapFunction(function () {
    var creating = _.$$importsForInline$$['atrium-domain-api-js'].ch.tutteli.atrium.domain.creating;
    return function (subjectProvider, expected, tolerance) {
      return creating.floatingPointAssertions.toBeWithErrorTolerance_afszox$(subjectProvider, expected, tolerance);
    };
  }));
  FloatingPointAssertionsBuilder.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'FloatingPointAssertionsBuilder',
    interfaces: [FloatingPointAssertions]
  };
  var FloatingPointAssertionsBuilder_instance = null;
  function FloatingPointAssertionsBuilder_getInstance() {
    if (FloatingPointAssertionsBuilder_instance === null) {
      new FloatingPointAssertionsBuilder();
    }
    return FloatingPointAssertionsBuilder_instance;
  }
  function register$lambda$lambda() {
    return new DefaultReporterFactory();
  }
  var register;
  function ReporterBuilder() {
    ReporterBuilder$Companion_getInstance();
  }
  function ReporterBuilder$Companion() {
    ReporterBuilder$Companion_instance = this;
  }
  ReporterBuilder$Companion.prototype.create = function () {
    return ReporterBuilderImpl_getInstance();
  };
  ReporterBuilder$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var ReporterBuilder$Companion_instance = null;
  function ReporterBuilder$Companion_getInstance() {
    if (ReporterBuilder$Companion_instance === null) {
      new ReporterBuilder$Companion();
    }
    return ReporterBuilder$Companion_instance;
  }
  ReporterBuilder.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'ReporterBuilder',
    interfaces: [ReporterBuilderCommon]
  };
  function TextAssertionFormatterOption() {
    TextAssertionFormatterOption$Companion_getInstance();
  }
  function TextAssertionFormatterOption$Companion() {
    TextAssertionFormatterOption$Companion_instance = this;
  }
  TextAssertionFormatterOption$Companion.prototype.create_9tpuz2$ = function (options, assertionPairFormatter) {
    return new TextAssertionFormatterOptionImpl(options, assertionPairFormatter);
  };
  TextAssertionFormatterOption$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var TextAssertionFormatterOption$Companion_instance = null;
  function TextAssertionFormatterOption$Companion_getInstance() {
    if (TextAssertionFormatterOption$Companion_instance === null) {
      new TextAssertionFormatterOption$Companion();
    }
    return TextAssertionFormatterOption$Companion_instance;
  }
  TextAssertionFormatterOption.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'TextAssertionFormatterOption',
    interfaces: [TextAssertionFormatterOptionCommon]
  };
  function TranslatorOption() {
  }
  TranslatorOption.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'TranslatorOption',
    interfaces: [TranslatorOptionCommon]
  };
  function ReporterBuilderImpl() {
    ReporterBuilderImpl_instance = this;
  }
  ReporterBuilderImpl.prototype.withoutTranslations_6rc0d$ = function (primaryLocale) {
    return this.withTranslator_9hgjhv$(new UsingDefaultTranslator(primaryLocale));
  };
  ReporterBuilderImpl.prototype.withTranslator_9hgjhv$ = function (translator) {
    return ObjectFormatterOption$Companion_getInstance().create_9hgjhv$(translator);
  };
  ReporterBuilderImpl.prototype.withDefaultTranslationSupplier = function () {
    throw new NotImplementedError_init('An operation is not implemented: ' + 'we have to implement a translation supplier for JS');
  };
  ReporterBuilderImpl.prototype.withTranslationSupplier_s0r2l8$ = function (translationSupplier) {
    return LocaleOrderDeciderOption$Companion_getInstance().create_s0r2l8$(translationSupplier);
  };
  ReporterBuilderImpl.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'ReporterBuilderImpl',
    interfaces: [ReporterBuilder]
  };
  var ReporterBuilderImpl_instance = null;
  function ReporterBuilderImpl_getInstance() {
    if (ReporterBuilderImpl_instance === null) {
      new ReporterBuilderImpl();
    }
    return ReporterBuilderImpl_instance;
  }
  $$importsForInline$$['atrium-core-api-js'] = $module$atrium_core_api_js;
  var package$ch = _.ch || (_.ch = {});
  var package$tutteli = package$ch.tutteli || (package$ch.tutteli = {});
  var package$atrium = package$tutteli.atrium || (package$tutteli.atrium = {});
  var package$domain = package$atrium.domain || (package$atrium.domain = {});
  var package$builders = package$domain.builders || (package$domain.builders = {});
  var package$creating = package$builders.creating || (package$builders.creating = {});
  var package$collectors = package$creating.collectors || (package$creating.collectors = {});
  Object.defineProperty(package$builders, 'AssertImpl', {
    get: AssertImpl_getInstance
  });
  package$builders.AssertImplCommon = AssertImplCommon;
  var package$changers = package$creating.changers || (package$creating.changers = {});
  package$changers.SubjectChangerBuilder = SubjectChangerBuilder;
  Object.defineProperty(package$builders, 'ExpectImpl', {
    get: ExpectImpl_getInstance
  });
  $$importsForInline$$['atrium-domain-api-js'] = $module$atrium_domain_api_js;
  Object.defineProperty(package$creating, 'CollectionAssertionsBuilder', {
    get: CollectionAssertionsBuilder_getInstance
  });
  Object.defineProperty(package$creating, 'ComparableAssertionsBuilder', {
    get: ComparableAssertionsBuilder_getInstance
  });
  $$importsForInline$$['atrium-domain-builders-js'] = _;
  Object.defineProperty(package$creating, 'FeatureAssertionsBuilder', {
    get: FeatureAssertionsBuilder_getInstance
  });
  Object.defineProperty(package$creating, 'ListAssertionsBuilder', {
    get: ListAssertionsBuilder_getInstance
  });
  Object.defineProperty(package$creating, 'MapAssertionsBuilder', {
    get: MapAssertionsBuilder_getInstance
  });
  Object.defineProperty(package$creating, 'MapEntryAssertionsBuilder', {
    get: MapEntryAssertionsBuilder_getInstance
  });
  package$changers.FeatureExtractorBuilder = FeatureExtractorBuilder;
  Object.defineProperty(package$creating, 'NewFeatureAssertionsBuilder', {
    get: NewFeatureAssertionsBuilder_getInstance
  });
  package$creating.MetaFeatureOption = MetaFeatureOption;
  Object.defineProperty(package$creating, 'MetaFeatureBuilder', {
    get: MetaFeatureBuilder_getInstance
  });
  Object.defineProperty(package$creating, 'PairAssertionsBuilder', {
    get: PairAssertionsBuilder_getInstance
  });
  package$creating.PleaseUseReplacementException = PleaseUseReplacementException;
  Object.defineProperty(package$creating, 'AnyAssertionsBuilder', {
    get: AnyAssertionsBuilder_getInstance
  });
  Object.defineProperty(package$creating, 'AnyTypeTransformationAssertionsBuilder', {
    get: AnyTypeTransformationAssertionsBuilder_getInstance
  });
  Object.defineProperty(package$creating, 'AnyTypeTransformationFailureHandlerFactoryBuilder', {
    get: AnyTypeTransformationFailureHandlerFactoryBuilder_getInstance
  });
  var package$basic = package$creating.basic || (package$creating.basic = {});
  var package$contains = package$basic.contains || (package$basic.contains = {});
  package$contains.addAssertion_b6ozzt$ = addAssertion;
  package$contains.addAssertion_wjune8$ = addAssertion_0;
  package$contains.addAssertionForAssert_b6ozzt$ = addAssertionForAssert;
  package$contains.addAssertionForAssert_wjune8$ = addAssertionForAssert_0;
  Object.defineProperty(FeatureExtractorBuilder, 'Companion', {
    get: FeatureExtractorBuilder$Companion_getInstance
  });
  Object.defineProperty(FeatureExtractorBuilder$DescriptionStep, 'Companion', {
    get: FeatureExtractorBuilder$DescriptionStep$Companion_getInstance
  });
  FeatureExtractorBuilder.DescriptionStep = FeatureExtractorBuilder$DescriptionStep;
  Object.defineProperty(FeatureExtractorBuilder$RepresentationInCaseOfFailureStep, 'Companion', {
    get: FeatureExtractorBuilder$RepresentationInCaseOfFailureStep$Companion_getInstance
  });
  FeatureExtractorBuilder.RepresentationInCaseOfFailureStep = FeatureExtractorBuilder$RepresentationInCaseOfFailureStep;
  Object.defineProperty(FeatureExtractorBuilder$FeatureExtractionStep, 'Companion', {
    get: FeatureExtractorBuilder$FeatureExtractionStep$Companion_getInstance
  });
  FeatureExtractorBuilder.FeatureExtractionStep = FeatureExtractorBuilder$FeatureExtractionStep;
  Object.defineProperty(FeatureExtractorBuilder$OptionalRepresentationStep, 'Companion', {
    get: FeatureExtractorBuilder$OptionalRepresentationStep$Companion_getInstance
  });
  FeatureExtractorBuilder.OptionalRepresentationStep = FeatureExtractorBuilder$OptionalRepresentationStep;
  Object.defineProperty(FeatureExtractorBuilder$FinalStep, 'Companion', {
    get: FeatureExtractorBuilder$FinalStep$Companion_getInstance
  });
  FeatureExtractorBuilder.FinalStep = FeatureExtractorBuilder$FinalStep;
  Object.defineProperty(SubjectChangerBuilder, 'Companion', {
    get: SubjectChangerBuilder$Companion_getInstance
  });
  SubjectChangerBuilder.DeprecatedKindStep = SubjectChangerBuilder$DeprecatedKindStep;
  SubjectChangerBuilder.KindStep = SubjectChangerBuilder$KindStep;
  Object.defineProperty(SubjectChangerBuilder$DescriptionRepresentationStep, 'Companion', {
    get: SubjectChangerBuilder$DescriptionRepresentationStep$Companion_getInstance
  });
  SubjectChangerBuilder.DescriptionRepresentationStep = SubjectChangerBuilder$DescriptionRepresentationStep;
  Object.defineProperty(SubjectChangerBuilder$TransformationStep, 'Companion', {
    get: SubjectChangerBuilder$TransformationStep$Companion_getInstance
  });
  SubjectChangerBuilder.TransformationStep = SubjectChangerBuilder$TransformationStep;
  Object.defineProperty(SubjectChangerBuilder$FailureHandlerOption, 'Companion', {
    get: SubjectChangerBuilder$FailureHandlerOption$Companion_getInstance
  });
  SubjectChangerBuilder.FailureHandlerOption = SubjectChangerBuilder$FailureHandlerOption;
  Object.defineProperty(SubjectChangerBuilder$FinalStep, 'Companion', {
    get: SubjectChangerBuilder$FinalStep$Companion_getInstance
  });
  SubjectChangerBuilder.FinalStep = SubjectChangerBuilder$FinalStep;
  var package$impl = package$changers.impl || (package$changers.impl = {});
  var package$featureextractor = package$impl.featureextractor || (package$impl.featureextractor = {});
  package$featureextractor.DescriptionStepImpl = DescriptionStepImpl;
  package$featureextractor.RepresentationInCaseOfFailureStepImpl = RepresentationInCaseOfFailureStepImpl;
  package$featureextractor.FeatureExtractionStepImpl = FeatureExtractionStepImpl;
  package$featureextractor.OptionalRepresentationStepImpl = OptionalRepresentationStepImpl;
  package$featureextractor.FinalStepImpl = FinalStepImpl;
  var package$subjectchanger = package$impl.subjectchanger || (package$impl.subjectchanger = {});
  package$subjectchanger.DefaultFailureHandlerImpl = DefaultFailureHandlerImpl;
  package$subjectchanger.KindStepImpl = KindStepImpl;
  package$subjectchanger.DeprecatedKindStepImpl = DeprecatedKindStepImpl;
  package$subjectchanger.DescriptionRepresentationStepImpl = DescriptionRepresentationStepImpl;
  package$subjectchanger.TransformationStepImpl = TransformationStepImpl;
  package$subjectchanger.FailureHandlerOptionImpl = FailureHandlerOptionImpl;
  package$subjectchanger.FinalStepImpl = FinalStepImpl_0;
  Object.defineProperty(package$creating, 'CharSequenceAssertionsBuilder', {
    get: CharSequenceAssertionsBuilder_getInstance
  });
  Object.defineProperty(package$creating, 'CharSequenceContainsAssertionsBuilder', {
    get: CharSequenceContainsAssertionsBuilder_getInstance
  });
  Object.defineProperty(package$creating, 'CharSequenceContainsSearchBehaviourFactoryBuilder', {
    get: CharSequenceContainsSearchBehaviourFactoryBuilder_getInstance
  });
  var package$charsequence = package$creating.charsequence || (package$creating.charsequence = {});
  var package$contains_0 = package$charsequence.contains || (package$charsequence.contains = {});
  var package$builders_0 = package$contains_0.builders || (package$contains_0.builders = {});
  package$builders_0.AtLeastCheckerOptionBase = AtLeastCheckerOptionBase;
  package$builders_0.AtMostCheckerOptionBase = AtMostCheckerOptionBase;
  package$builders_0.ButAtMostCheckerOptionBase = ButAtMostCheckerOptionBase;
  package$builders_0.ExactlyCheckerOptionBase = ExactlyCheckerOptionBase;
  package$builders_0.NotCheckerOptionBase = NotCheckerOptionBase;
  package$builders_0.NotOrAtMostCheckerOptionBase = NotOrAtMostCheckerOptionBase;
  package$builders_0.WithTimesCheckerOption = WithTimesCheckerOption;
  Object.defineProperty(package$collectors, 'AssertionCollectorBuilder', {
    get: AssertionCollectorBuilder_getInstance
  });
  Object.defineProperty(package$collectors, 'ExplainingAssertionCollectorOption', {
    get: ExplainingAssertionCollectorOption_getInstance
  });
  package$collectors.collectAssertions_qz2hyk$ = collectAssertions;
  package$collectors.collectAssertions_xonqtz$ = collectAssertions_0;
  var package$iterable = package$creating.iterable || (package$creating.iterable = {});
  var package$contains_1 = package$iterable.contains || (package$iterable.contains = {});
  var package$builders_1 = package$contains_1.builders || (package$contains_1.builders = {});
  package$builders_1.AtLeastCheckerOptionBase = AtLeastCheckerOptionBase_0;
  package$builders_1.AtMostCheckerOptionBase = AtMostCheckerOptionBase_0;
  package$builders_1.ButAtMostCheckerOptionBase = ButAtMostCheckerOptionBase_0;
  package$builders_1.ExactlyCheckerOptionBase = ExactlyCheckerOptionBase_0;
  package$builders_1.NotCheckerOptionBase = NotCheckerOptionBase_0;
  package$builders_1.NotOrAtMostCheckerOptionBase = NotOrAtMostCheckerOptionBase_0;
  package$builders_1.WithTimesCheckerOption = WithTimesCheckerOption_0;
  Object.defineProperty(package$creating, 'IterableAssertionsBuilder', {
    get: IterableAssertionsBuilder_getInstance
  });
  Object.defineProperty(package$creating, 'IterableContainsAssertionsBuilder', {
    get: IterableContainsAssertionsBuilder_getInstance
  });
  Object.defineProperty(package$creating, 'IterableContainsSearchBehaviourFactoryBuilder', {
    get: IterableContainsSearchBehaviourFactoryBuilder_getInstance
  });
  Object.defineProperty(package$creating, 'ThrowableAssertionsBuilder', {
    get: ThrowableAssertionsBuilder_getInstance
  });
  Object.defineProperty(package$creating, 'ThrowableThrownAssertionsBuilder', {
    get: ThrowableThrownAssertionsBuilder_getInstance
  });
  Object.defineProperty(package$creating, 'AbsentThrowableMessageProviderFactoryBuilder', {
    get: AbsentThrowableMessageProviderFactoryBuilder_getInstance
  });
  var package$migration = package$builders.migration || (package$builders.migration = {});
  package$migration.asAssert = asAssert;
  package$migration.asAssert_q7eyaa$ = asAssert_0;
  package$migration.asSubExpect_po38ko$ = asSubExpect;
  package$migration.asExpect_f8nd0p$ = asExpect;
  package$migration.asExpect_lt8sq3$ = asExpect_0;
  Object.defineProperty(AssertionFormatterControllerOption, 'Companion', {
    get: AssertionFormatterControllerOption$Companion_getInstance
  });
  var package$reporting = package$builders.reporting || (package$builders.reporting = {});
  package$reporting.AssertionFormatterControllerOption = AssertionFormatterControllerOption;
  Object.defineProperty(AssertionFormatterFacadeOption, 'Companion', {
    get: AssertionFormatterFacadeOption$Companion_getInstance
  });
  package$reporting.AssertionFormatterFacadeOption = AssertionFormatterFacadeOption;
  Object.defineProperty(AssertionPairFormatterOption, 'Companion', {
    get: AssertionPairFormatterOption$Companion_getInstance
  });
  package$reporting.AssertionPairFormatterOption = AssertionPairFormatterOption;
  package$reporting.AssertionFormatterChosenOptions = AssertionFormatterChosenOptions;
  package$reporting.AtriumErrorAdjusterCommonOption = AtriumErrorAdjusterCommonOption;
  Object.defineProperty(AtriumErrorAdjusterOption, 'Companion', {
    get: AtriumErrorAdjusterOption$Companion_getInstance
  });
  package$reporting.AtriumErrorAdjusterOption = AtriumErrorAdjusterOption;
  package$reporting.MultipleAdjustersOptionMarker = MultipleAdjustersOptionMarker;
  Object.defineProperty(MultipleAdjustersOption, 'Companion', {
    get: MultipleAdjustersOption$Companion_getInstance
  });
  package$reporting.MultipleAdjustersOption = MultipleAdjustersOption;
  Object.defineProperty(ExpectBuilder, 'Companion', {
    get: ExpectBuilder$Companion_getInstance
  });
  ExpectBuilder.AssertionVerbStep = ExpectBuilder$AssertionVerbStep;
  Object.defineProperty(ExpectBuilder$OptionsStep, 'Companion', {
    get: ExpectBuilder$OptionsStep$Companion_getInstance
  });
  ExpectBuilder.OptionsStep = ExpectBuilder$OptionsStep;
  Object.defineProperty(ExpectBuilder$OptionsChooser, 'Companion', {
    get: ExpectBuilder$OptionsChooser$Companion_getInstance
  });
  ExpectBuilder.OptionsChooser = ExpectBuilder$OptionsChooser;
  Object.defineProperty(ExpectBuilder$FinalStep, 'Companion', {
    get: ExpectBuilder$FinalStep$Companion_getInstance
  });
  ExpectBuilder.FinalStep = ExpectBuilder$FinalStep;
  package$reporting.ExpectBuilder = ExpectBuilder;
  package$reporting.ExpectOptions = ExpectOptions;
  package$reporting.ExpectOptions_bgu5w4$ = ExpectOptions_0;
  Object.defineProperty(LocaleOrderDeciderOption, 'Companion', {
    get: LocaleOrderDeciderOption$Companion_getInstance
  });
  package$reporting.LocaleOrderDeciderOption = LocaleOrderDeciderOption;
  Object.defineProperty(ObjectFormatterOption, 'Companion', {
    get: ObjectFormatterOption$Companion_getInstance
  });
  package$reporting.ObjectFormatterOption = ObjectFormatterOption;
  Object.defineProperty(package$reporting, 'reporterBuilder', {
    get: function () {
      return reporterBuilder;
    }
  });
  package$reporting.ReporterBuilderCommon = ReporterBuilderCommon;
  Object.defineProperty(ReporterBuilderFinalStep, 'Companion', {
    get: ReporterBuilderFinalStep$Companion_getInstance
  });
  package$reporting.ReporterBuilderFinalStep = ReporterBuilderFinalStep;
  Object.defineProperty(ReporterOption, 'Companion', {
    get: ReporterOption$Companion_getInstance
  });
  package$reporting.ReporterOption = ReporterOption;
  package$reporting.TextAssertionFormatterOptionCommon = TextAssertionFormatterOptionCommon;
  package$reporting.TranslatorOptionCommon = TranslatorOptionCommon;
  var package$impl_0 = package$reporting.impl || (package$reporting.impl = {});
  package$impl_0.AssertionFormatterControllerOptionImpl = AssertionFormatterControllerOptionImpl;
  package$impl_0.AssertionFormatterFacadeOptionImpl = AssertionFormatterFacadeOptionImpl;
  package$impl_0.AssertionPairFormatterOptionImpl = AssertionPairFormatterOptionImpl;
  package$impl_0.AtriumErrorAdjusterOptionImpl = AtriumErrorAdjusterOptionImpl;
  package$impl_0.DefaultReporterFactory = DefaultReporterFactory;
  package$impl_0.LocaleOrderDeciderOptionImpl = LocaleOrderDeciderOptionImpl;
  package$impl_0.MultipleAdjustersOptionImpl = MultipleAdjustersOptionImpl;
  package$impl_0.ObjectFormatterOptionImpl = ObjectFormatterOptionImpl;
  package$impl_0.ReporterBuilderFinalStepImpl = ReporterBuilderFinalStepImpl;
  package$impl_0.ReporterOptionImpl = ReporterOptionImpl;
  $$importsForInline$$['kbox-js'] = $module$kbox_js;
  package$impl_0.TextAssertionFormatterOptionImpl = TextAssertionFormatterOptionImpl;
  package$impl_0.TranslatorOptionImpl = TranslatorOptionImpl;
  var package$verb = package$impl_0.verb || (package$impl_0.verb = {});
  package$verb.OptionsChooserImpl = OptionsChooserImpl;
  package$verb.AssertionVerbStepImpl = AssertionVerbStepImpl;
  package$verb.OptionsStepImpl = OptionsStepImpl;
  package$verb.FinalStepImpl = FinalStepImpl_1;
  var package$utils = package$builders.utils || (package$builders.utils = {});
  package$utils.VarArgHelper = VarArgHelper;
  package$utils.validateAtMost_2zccmg$ = validateAtMost;
  package$utils.validateButAtMost_54mtdk$ = validateButAtMost;
  package$utils.Group = Group;
  package$utils.GroupWithoutNullableEntries = GroupWithoutNullableEntries;
  package$utils.GroupWithNullableEntries = GroupWithNullableEntries;
  package$utils.groupsToList_nrpso6$ = groupsToList;
  package$utils.mapArguments_r59i0z$ = mapArguments;
  package$utils.mapArguments_63hmpn$ = mapArguments_1;
  package$utils.mapArguments_m72iov$ = mapArguments_3;
  package$utils.mapArguments_9qrz6b$ = mapArguments_5;
  package$utils.mapArguments_aio0fn$ = mapArguments_7;
  package$utils.mapArguments_hkm0fh$ = mapArguments_9;
  package$utils.mapArguments_f6ygq5$ = mapArguments_11;
  package$utils.mapArguments_4avpt5$ = mapArguments_13;
  package$utils.mapArguments_5vfawd$ = mapArguments_15;
  package$utils.subExpect_jnzojz$ = subExpect;
  package$utils.subAssert_e8co0r$ = subAssert;
  package$utils.subAssertNullable_4m3k9$ = subAssertNullable;
  package$utils.ArgumentMapperBuilder = ArgumentMapperBuilder;
  package$utils.toNullOr_ba88xb$ = toNullOr;
  package$utils.ArgumentToNullOrMapperBuilder = ArgumentToNullOrMapperBuilder;
  package$utils.nullable_mh5how$ = nullable;
  package$utils.nullable_j9u1pg$ = nullable_0;
  package$utils.nullableContainer_yl67zr$ = nullableContainer;
  package$utils.nullableContainer_5x1j0d$ = nullableContainer_0;
  package$utils.nullableKeyMap_zhczhj$ = nullableKeyMap;
  package$utils.nullableValueMap_2gsboo$ = nullableValueMap;
  package$utils.nullableKeyValueMap_73mtqc$ = nullableKeyValueMap;
  package$utils.nullable_rdzs90$ = nullable_1;
  package$utils.nullable_it1gp8$ = nullable_2;
  package$utils.nullable_e2ncze$ = nullable_3;
  package$utils.nullable_v91fg0$ = nullable_4;
  package$utils.nullable_3qeua4$ = nullable_5;
  Object.defineProperty(package$creating, 'FloatingPointAssertionsBuilder', {
    get: FloatingPointAssertionsBuilder_getInstance
  });
  Object.defineProperty(ReporterBuilder, 'Companion', {
    get: ReporterBuilder$Companion_getInstance
  });
  package$reporting.ReporterBuilder = ReporterBuilder;
  Object.defineProperty(TextAssertionFormatterOption, 'Companion', {
    get: TextAssertionFormatterOption$Companion_getInstance
  });
  package$reporting.TextAssertionFormatterOption = TextAssertionFormatterOption;
  package$reporting.TranslatorOption = TranslatorOption;
  Object.defineProperty(package$impl_0, 'ReporterBuilderImpl', {
    get: ReporterBuilderImpl_getInstance
  });
  AssertImpl.prototype.changeSubject_y31nfh$ = AssertImplCommon.prototype.changeSubject_y31nfh$;
  AssertImpl.prototype.changeSubject_5p4xxt$ = AssertImplCommon.prototype.changeSubject_5p4xxt$;
  AssertImpl.prototype.changeToNullableSubject_j73se8$ = AssertImplCommon.prototype.changeToNullableSubject_j73se8$;
  AssertImpl.prototype.changeToNullableSubject_5p4xxt$ = AssertImplCommon.prototype.changeToNullableSubject_5p4xxt$;
  AnyAssertionsBuilder.prototype.notToBeNull_e1rtsw$ = AnyAssertions.prototype.notToBeNull_e1rtsw$;
  DescriptionStepImpl.prototype.withDescription_61zpoe$ = FeatureExtractorBuilder$DescriptionStep.prototype.withDescription_61zpoe$;
  DescriptionStepImpl.prototype.methodCall_25kzsl$ = FeatureExtractorBuilder$DescriptionStep.prototype.methodCall_25kzsl$;
  RepresentationInCaseOfFailureStepImpl.prototype.withRepresentationForFailure_n3w7xm$ = FeatureExtractorBuilder$RepresentationInCaseOfFailureStep.prototype.withRepresentationForFailure_n3w7xm$;
  RepresentationInCaseOfFailureStepImpl.prototype.withRepresentationForFailure_nq59yw$ = FeatureExtractorBuilder$RepresentationInCaseOfFailureStep.prototype.withRepresentationForFailure_nq59yw$;
  KindStepImpl.prototype.unreported_2o04qz$ = SubjectChangerBuilder$KindStep.prototype.unreported_2o04qz$;
  DeprecatedKindStepImpl.prototype.unreported_el91ig$ = SubjectChangerBuilder$DeprecatedKindStep.prototype.unreported_el91ig$;
  DeprecatedKindStepImpl.prototype.unreportedNullable_2o04qz$ = SubjectChangerBuilder$DeprecatedKindStep.prototype.unreportedNullable_2o04qz$;
  DescriptionRepresentationStepImpl.prototype.withDescriptionAndRepresentation_4w9ihe$ = SubjectChangerBuilder$DescriptionRepresentationStep.prototype.withDescriptionAndRepresentation_4w9ihe$;
  DescriptionRepresentationStepImpl.prototype.downCastTo_lmshww$ = SubjectChangerBuilder$DescriptionRepresentationStep.prototype.downCastTo_lmshww$;
  FailureHandlerOptionImpl.prototype.build = SubjectChangerBuilder$FailureHandlerOption.prototype.build;
  AssertionCollectorBuilder.prototype.collect_md0kdf$ = AssertionCollector.prototype.collect_md0kdf$;
  AssertionCollectorBuilder.prototype.collect_4q8p40$ = AssertionCollector.prototype.collect_4q8p40$;
  AssertionCollectorBuilder.prototype.collect_p44pa9$ = AssertionCollector.prototype.collect_p44pa9$;
  AssertionCollectorBuilder.prototype.collectOrExplain_yzke50$ = AssertionCollector.prototype.collectOrExplain_yzke50$;
  AssertionCollectorBuilder.prototype.collectOrExplain_wkzgjz$ = AssertionCollector.prototype.collectOrExplain_wkzgjz$;
  AssertionCollectorBuilder.prototype.collectNullable_x73ge1$ = AssertionCollector.prototype.collectNullable_x73ge1$;
  AssertionCollectorBuilder.prototype.collectNullable_vi88l7$ = AssertionCollector.prototype.collectNullable_vi88l7$;
  AssertionCollectorBuilder.prototype.collectNullableOrExplain_4y03eg$ = AssertionCollector.prototype.collectNullableOrExplain_4y03eg$;
  AssertionCollectorBuilder.prototype.collectNullableOrExplain_g3yuno$ = AssertionCollector.prototype.collectNullableOrExplain_g3yuno$;
  ThrowableAssertionsBuilder.prototype.thrownBuilder_996pa$ = ThrowableAssertions.prototype.thrownBuilder_996pa$;
  AssertionPairFormatterOptionImpl.prototype.withTextSameLineAssertionPairFormatter = AssertionPairFormatterOption.prototype.withTextSameLineAssertionPairFormatter;
  AssertionPairFormatterOptionImpl.prototype.withTextNextLineAssertionPairFormatter = AssertionPairFormatterOption.prototype.withTextNextLineAssertionPairFormatter;
  AtriumErrorAdjusterOptionImpl.prototype.withDefaultAtriumErrorAdjusters = AtriumErrorAdjusterOption.prototype.withDefaultAtriumErrorAdjusters;
  OptionsChooserImpl.prototype.withVerb_61zpoe$ = ExpectBuilder$OptionsChooser.prototype.withVerb_61zpoe$;
  AssertionVerbStepImpl.prototype.withVerb_61zpoe$ = ExpectBuilder$AssertionVerbStep.prototype.withVerb_61zpoe$;
  OptionsStepImpl.prototype.withOptions_bgu5w4$ = ExpectBuilder$OptionsStep.prototype.withOptions_bgu5w4$;
  ReporterBuilder.prototype.withoutTranslationsUseDefaultLocale = ReporterBuilderCommon.prototype.withoutTranslationsUseDefaultLocale;
  ReporterBuilderImpl.prototype.withoutTranslationsUseDefaultLocale = ReporterBuilder.prototype.withoutTranslationsUseDefaultLocale;
  reporterBuilder = ReporterBuilderImpl_getInstance();
  registerService(getKClass(ReporterFactory), register$lambda$lambda);
  register = Unit;
  Kotlin.defineModule('atrium-domain-builders-js', _);
  return _;
}));

//# sourceMappingURL=atrium-domain-builders-js.js.map
