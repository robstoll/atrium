(function (root, factory) {
  if (typeof define === 'function' && define.amd)
    define(['exports', 'kotlin', 'atrium-core-api-js'], factory);
  else if (typeof exports === 'object')
    factory(module.exports, require('kotlin'), require('atrium-core-api-js'));
  else {
    if (typeof kotlin === 'undefined') {
      throw new Error("Error loading module 'atrium-translations-js'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'atrium-translations-js'.");
    }
    if (typeof this['atrium-core-api-js'] === 'undefined') {
      throw new Error("Error loading module 'atrium-translations-js'. Its dependency 'atrium-core-api-js' was not found. Please, check whether 'atrium-core-api-js' is loaded prior to 'atrium-translations-js'.");
    }
    root['atrium-translations-js'] = factory(typeof this['atrium-translations-js'] === 'undefined' ? {} : this['atrium-translations-js'], kotlin, this['atrium-core-api-js']);
  }
}(this, function (_, Kotlin, $module$atrium_core_api_js) {
  'use strict';
  var Enum = Kotlin.kotlin.Enum;
  var Kind_CLASS = Kotlin.Kind.CLASS;
  var StringBasedTranslatable = $module$atrium_core_api_js.ch.tutteli.atrium.reporting.translating.StringBasedTranslatable;
  var throwISE = Kotlin.throwISE;
  DescriptionAnyAssertion.prototype = Object.create(Enum.prototype);
  DescriptionAnyAssertion.prototype.constructor = DescriptionAnyAssertion;
  DescriptionBasic.prototype = Object.create(Enum.prototype);
  DescriptionBasic.prototype.constructor = DescriptionBasic;
  DescriptionCharSequenceAssertion.prototype = Object.create(Enum.prototype);
  DescriptionCharSequenceAssertion.prototype.constructor = DescriptionCharSequenceAssertion;
  DescriptionCollectionAssertion.prototype = Object.create(Enum.prototype);
  DescriptionCollectionAssertion.prototype.constructor = DescriptionCollectionAssertion;
  DescriptionComparableAssertion.prototype = Object.create(Enum.prototype);
  DescriptionComparableAssertion.prototype.constructor = DescriptionComparableAssertion;
  DescriptionDateTimeLikeAssertion.prototype = Object.create(Enum.prototype);
  DescriptionDateTimeLikeAssertion.prototype.constructor = DescriptionDateTimeLikeAssertion;
  DescriptionFloatingPointAssertion.prototype = Object.create(Enum.prototype);
  DescriptionFloatingPointAssertion.prototype.constructor = DescriptionFloatingPointAssertion;
  DescriptionIterableAssertion.prototype = Object.create(Enum.prototype);
  DescriptionIterableAssertion.prototype.constructor = DescriptionIterableAssertion;
  DescriptionListAssertion.prototype = Object.create(Enum.prototype);
  DescriptionListAssertion.prototype.constructor = DescriptionListAssertion;
  DescriptionMapAssertion.prototype = Object.create(Enum.prototype);
  DescriptionMapAssertion.prototype.constructor = DescriptionMapAssertion;
  DescriptionThrowableAssertion.prototype = Object.create(Enum.prototype);
  DescriptionThrowableAssertion.prototype.constructor = DescriptionThrowableAssertion;
  DescriptionTypeTransformationAssertion.prototype = Object.create(Enum.prototype);
  DescriptionTypeTransformationAssertion.prototype.constructor = DescriptionTypeTransformationAssertion;
  ErrorMessages.prototype = Object.create(Enum.prototype);
  ErrorMessages.prototype.constructor = ErrorMessages;
  function DescriptionAnyAssertion(name, ordinal, value) {
    Enum.call(this);
    this.value_7qyuv9$_0 = value;
    this.name$ = name;
    this.ordinal$ = ordinal;
  }
  function DescriptionAnyAssertion_initFields() {
    DescriptionAnyAssertion_initFields = function () {
    };
    DescriptionAnyAssertion$TO_BE_instance = new DescriptionAnyAssertion('TO_BE', 0, DescriptionBasic$TO_BE_getInstance().value);
    DescriptionAnyAssertion$NOT_TO_BE_instance = new DescriptionAnyAssertion('NOT_TO_BE', 1, DescriptionBasic$NOT_TO_BE_getInstance().value);
    DescriptionAnyAssertion$IS_A_instance = new DescriptionAnyAssertion('IS_A', 2, 'is instance of type');
    DescriptionAnyAssertion$IS_SAME_instance = new DescriptionAnyAssertion('IS_SAME', 3, 'is the same as');
    DescriptionAnyAssertion$IS_NOT_SAME_instance = new DescriptionAnyAssertion('IS_NOT_SAME', 4, 'is not the same as');
  }
  Object.defineProperty(DescriptionAnyAssertion.prototype, 'value', {
    get: function () {
      return this.value_7qyuv9$_0;
    }
  });
  var DescriptionAnyAssertion$TO_BE_instance;
  function DescriptionAnyAssertion$TO_BE_getInstance() {
    DescriptionAnyAssertion_initFields();
    return DescriptionAnyAssertion$TO_BE_instance;
  }
  var DescriptionAnyAssertion$NOT_TO_BE_instance;
  function DescriptionAnyAssertion$NOT_TO_BE_getInstance() {
    DescriptionAnyAssertion_initFields();
    return DescriptionAnyAssertion$NOT_TO_BE_instance;
  }
  var DescriptionAnyAssertion$IS_A_instance;
  function DescriptionAnyAssertion$IS_A_getInstance() {
    DescriptionAnyAssertion_initFields();
    return DescriptionAnyAssertion$IS_A_instance;
  }
  var DescriptionAnyAssertion$IS_SAME_instance;
  function DescriptionAnyAssertion$IS_SAME_getInstance() {
    DescriptionAnyAssertion_initFields();
    return DescriptionAnyAssertion$IS_SAME_instance;
  }
  var DescriptionAnyAssertion$IS_NOT_SAME_instance;
  function DescriptionAnyAssertion$IS_NOT_SAME_getInstance() {
    DescriptionAnyAssertion_initFields();
    return DescriptionAnyAssertion$IS_NOT_SAME_instance;
  }
  DescriptionAnyAssertion.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'DescriptionAnyAssertion',
    interfaces: [StringBasedTranslatable, Enum]
  };
  function DescriptionAnyAssertion$values() {
    return [DescriptionAnyAssertion$TO_BE_getInstance(), DescriptionAnyAssertion$NOT_TO_BE_getInstance(), DescriptionAnyAssertion$IS_A_getInstance(), DescriptionAnyAssertion$IS_SAME_getInstance(), DescriptionAnyAssertion$IS_NOT_SAME_getInstance()];
  }
  DescriptionAnyAssertion.values = DescriptionAnyAssertion$values;
  function DescriptionAnyAssertion$valueOf(name) {
    switch (name) {
      case 'TO_BE':
        return DescriptionAnyAssertion$TO_BE_getInstance();
      case 'NOT_TO_BE':
        return DescriptionAnyAssertion$NOT_TO_BE_getInstance();
      case 'IS_A':
        return DescriptionAnyAssertion$IS_A_getInstance();
      case 'IS_SAME':
        return DescriptionAnyAssertion$IS_SAME_getInstance();
      case 'IS_NOT_SAME':
        return DescriptionAnyAssertion$IS_NOT_SAME_getInstance();
      default:throwISE('No enum constant ch.tutteli.atrium.translations.DescriptionAnyAssertion.' + name);
    }
  }
  DescriptionAnyAssertion.valueOf_61zpoe$ = DescriptionAnyAssertion$valueOf;
  function DescriptionBasic(name, ordinal, value) {
    Enum.call(this);
    this.value_rm9x5b$_0 = value;
    this.name$ = name;
    this.ordinal$ = ordinal;
  }
  function DescriptionBasic_initFields() {
    DescriptionBasic_initFields = function () {
    };
    DescriptionBasic$TO_instance = new DescriptionBasic('TO', 0, 'to');
    DescriptionBasic$NOT_TO_instance = new DescriptionBasic('NOT_TO', 1, 'not to');
    DescriptionBasic$TO_BE_instance = new DescriptionBasic('TO_BE', 2, 'to be');
    DescriptionBasic$NOT_TO_BE_instance = new DescriptionBasic('NOT_TO_BE', 3, 'not to be');
    DescriptionBasic$IS_instance = new DescriptionBasic('IS', 4, 'is');
    DescriptionBasic$IS_NOT_instance = new DescriptionBasic('IS_NOT', 5, 'is not');
    DescriptionBasic$HAS_instance = new DescriptionBasic('HAS', 6, 'has');
    DescriptionBasic$HAS_NOT_instance = new DescriptionBasic('HAS_NOT', 7, 'has not');
    DescriptionBasic$WAS_instance = new DescriptionBasic('WAS', 8, 'was');
    DescriptionBasic$NONE_instance = new DescriptionBasic('NONE', 9, 'none');
  }
  Object.defineProperty(DescriptionBasic.prototype, 'value', {
    get: function () {
      return this.value_rm9x5b$_0;
    }
  });
  var DescriptionBasic$TO_instance;
  function DescriptionBasic$TO_getInstance() {
    DescriptionBasic_initFields();
    return DescriptionBasic$TO_instance;
  }
  var DescriptionBasic$NOT_TO_instance;
  function DescriptionBasic$NOT_TO_getInstance() {
    DescriptionBasic_initFields();
    return DescriptionBasic$NOT_TO_instance;
  }
  var DescriptionBasic$TO_BE_instance;
  function DescriptionBasic$TO_BE_getInstance() {
    DescriptionBasic_initFields();
    return DescriptionBasic$TO_BE_instance;
  }
  var DescriptionBasic$NOT_TO_BE_instance;
  function DescriptionBasic$NOT_TO_BE_getInstance() {
    DescriptionBasic_initFields();
    return DescriptionBasic$NOT_TO_BE_instance;
  }
  var DescriptionBasic$IS_instance;
  function DescriptionBasic$IS_getInstance() {
    DescriptionBasic_initFields();
    return DescriptionBasic$IS_instance;
  }
  var DescriptionBasic$IS_NOT_instance;
  function DescriptionBasic$IS_NOT_getInstance() {
    DescriptionBasic_initFields();
    return DescriptionBasic$IS_NOT_instance;
  }
  var DescriptionBasic$HAS_instance;
  function DescriptionBasic$HAS_getInstance() {
    DescriptionBasic_initFields();
    return DescriptionBasic$HAS_instance;
  }
  var DescriptionBasic$HAS_NOT_instance;
  function DescriptionBasic$HAS_NOT_getInstance() {
    DescriptionBasic_initFields();
    return DescriptionBasic$HAS_NOT_instance;
  }
  var DescriptionBasic$WAS_instance;
  function DescriptionBasic$WAS_getInstance() {
    DescriptionBasic_initFields();
    return DescriptionBasic$WAS_instance;
  }
  var DescriptionBasic$NONE_instance;
  function DescriptionBasic$NONE_getInstance() {
    DescriptionBasic_initFields();
    return DescriptionBasic$NONE_instance;
  }
  DescriptionBasic.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'DescriptionBasic',
    interfaces: [StringBasedTranslatable, Enum]
  };
  function DescriptionBasic$values() {
    return [DescriptionBasic$TO_getInstance(), DescriptionBasic$NOT_TO_getInstance(), DescriptionBasic$TO_BE_getInstance(), DescriptionBasic$NOT_TO_BE_getInstance(), DescriptionBasic$IS_getInstance(), DescriptionBasic$IS_NOT_getInstance(), DescriptionBasic$HAS_getInstance(), DescriptionBasic$HAS_NOT_getInstance(), DescriptionBasic$WAS_getInstance(), DescriptionBasic$NONE_getInstance()];
  }
  DescriptionBasic.values = DescriptionBasic$values;
  function DescriptionBasic$valueOf(name) {
    switch (name) {
      case 'TO':
        return DescriptionBasic$TO_getInstance();
      case 'NOT_TO':
        return DescriptionBasic$NOT_TO_getInstance();
      case 'TO_BE':
        return DescriptionBasic$TO_BE_getInstance();
      case 'NOT_TO_BE':
        return DescriptionBasic$NOT_TO_BE_getInstance();
      case 'IS':
        return DescriptionBasic$IS_getInstance();
      case 'IS_NOT':
        return DescriptionBasic$IS_NOT_getInstance();
      case 'HAS':
        return DescriptionBasic$HAS_getInstance();
      case 'HAS_NOT':
        return DescriptionBasic$HAS_NOT_getInstance();
      case 'WAS':
        return DescriptionBasic$WAS_getInstance();
      case 'NONE':
        return DescriptionBasic$NONE_getInstance();
      default:throwISE('No enum constant ch.tutteli.atrium.translations.DescriptionBasic.' + name);
    }
  }
  DescriptionBasic.valueOf_61zpoe$ = DescriptionBasic$valueOf;
  function DescriptionCharSequenceAssertion(name, ordinal, value) {
    Enum.call(this);
    this.value_8lj6du$_0 = value;
    this.name$ = name;
    this.ordinal$ = ordinal;
  }
  function DescriptionCharSequenceAssertion_initFields() {
    DescriptionCharSequenceAssertion_initFields = function () {
    };
    DescriptionCharSequenceAssertion$AT_LEAST_instance = new DescriptionCharSequenceAssertion('AT_LEAST', 0, 'is at least');
    DescriptionCharSequenceAssertion$AT_MOST_instance = new DescriptionCharSequenceAssertion('AT_MOST', 1, 'is at most');
    DescriptionCharSequenceAssertion$BLANK_instance = new DescriptionCharSequenceAssertion('BLANK', 2, 'blank');
    DescriptionCharSequenceAssertion$CONTAINS_instance = new DescriptionCharSequenceAssertion('CONTAINS', 3, 'contains');
    DescriptionCharSequenceAssertion$CONTAINS_NOT_instance = new DescriptionCharSequenceAssertion('CONTAINS_NOT', 4, 'does not contain');
    DescriptionCharSequenceAssertion$EMPTY_instance = new DescriptionCharSequenceAssertion('EMPTY', 5, 'empty');
    DescriptionCharSequenceAssertion$ENDS_WITH_instance = new DescriptionCharSequenceAssertion('ENDS_WITH', 6, 'ends with');
    DescriptionCharSequenceAssertion$ENDS_NOT_WITH_instance = new DescriptionCharSequenceAssertion('ENDS_NOT_WITH', 7, 'does not end with');
    DescriptionCharSequenceAssertion$EXACTLY_instance = new DescriptionCharSequenceAssertion('EXACTLY', 8, 'is exactly');
    DescriptionCharSequenceAssertion$IGNORING_CASE_instance = new DescriptionCharSequenceAssertion('IGNORING_CASE', 9, '%s, ignoring case');
    DescriptionCharSequenceAssertion$MATCHES_instance = new DescriptionCharSequenceAssertion('MATCHES', 10, 'matches entirely');
    DescriptionCharSequenceAssertion$MISMATCHES_instance = new DescriptionCharSequenceAssertion('MISMATCHES', 11, 'does not match entirely');
    DescriptionCharSequenceAssertion$NUMBER_OF_OCCURRENCES_instance = new DescriptionCharSequenceAssertion('NUMBER_OF_OCCURRENCES', 12, 'number of occurrences');
    DescriptionCharSequenceAssertion$STARTS_WITH_instance = new DescriptionCharSequenceAssertion('STARTS_WITH', 13, 'starts with');
    DescriptionCharSequenceAssertion$STARTS_NOT_WITH_instance = new DescriptionCharSequenceAssertion('STARTS_NOT_WITH', 14, 'does not start with');
    DescriptionCharSequenceAssertion$STRING_MATCHING_REGEX_instance = new DescriptionCharSequenceAssertion('STRING_MATCHING_REGEX', 15, 'string matching regex');
    DescriptionCharSequenceAssertion$VALUE_instance = new DescriptionCharSequenceAssertion('VALUE', 16, 'value');
  }
  Object.defineProperty(DescriptionCharSequenceAssertion.prototype, 'value', {
    get: function () {
      return this.value_8lj6du$_0;
    }
  });
  var DescriptionCharSequenceAssertion$AT_LEAST_instance;
  function DescriptionCharSequenceAssertion$AT_LEAST_getInstance() {
    DescriptionCharSequenceAssertion_initFields();
    return DescriptionCharSequenceAssertion$AT_LEAST_instance;
  }
  var DescriptionCharSequenceAssertion$AT_MOST_instance;
  function DescriptionCharSequenceAssertion$AT_MOST_getInstance() {
    DescriptionCharSequenceAssertion_initFields();
    return DescriptionCharSequenceAssertion$AT_MOST_instance;
  }
  var DescriptionCharSequenceAssertion$BLANK_instance;
  function DescriptionCharSequenceAssertion$BLANK_getInstance() {
    DescriptionCharSequenceAssertion_initFields();
    return DescriptionCharSequenceAssertion$BLANK_instance;
  }
  var DescriptionCharSequenceAssertion$CONTAINS_instance;
  function DescriptionCharSequenceAssertion$CONTAINS_getInstance() {
    DescriptionCharSequenceAssertion_initFields();
    return DescriptionCharSequenceAssertion$CONTAINS_instance;
  }
  var DescriptionCharSequenceAssertion$CONTAINS_NOT_instance;
  function DescriptionCharSequenceAssertion$CONTAINS_NOT_getInstance() {
    DescriptionCharSequenceAssertion_initFields();
    return DescriptionCharSequenceAssertion$CONTAINS_NOT_instance;
  }
  var DescriptionCharSequenceAssertion$EMPTY_instance;
  function DescriptionCharSequenceAssertion$EMPTY_getInstance() {
    DescriptionCharSequenceAssertion_initFields();
    return DescriptionCharSequenceAssertion$EMPTY_instance;
  }
  var DescriptionCharSequenceAssertion$ENDS_WITH_instance;
  function DescriptionCharSequenceAssertion$ENDS_WITH_getInstance() {
    DescriptionCharSequenceAssertion_initFields();
    return DescriptionCharSequenceAssertion$ENDS_WITH_instance;
  }
  var DescriptionCharSequenceAssertion$ENDS_NOT_WITH_instance;
  function DescriptionCharSequenceAssertion$ENDS_NOT_WITH_getInstance() {
    DescriptionCharSequenceAssertion_initFields();
    return DescriptionCharSequenceAssertion$ENDS_NOT_WITH_instance;
  }
  var DescriptionCharSequenceAssertion$EXACTLY_instance;
  function DescriptionCharSequenceAssertion$EXACTLY_getInstance() {
    DescriptionCharSequenceAssertion_initFields();
    return DescriptionCharSequenceAssertion$EXACTLY_instance;
  }
  var DescriptionCharSequenceAssertion$IGNORING_CASE_instance;
  function DescriptionCharSequenceAssertion$IGNORING_CASE_getInstance() {
    DescriptionCharSequenceAssertion_initFields();
    return DescriptionCharSequenceAssertion$IGNORING_CASE_instance;
  }
  var DescriptionCharSequenceAssertion$MATCHES_instance;
  function DescriptionCharSequenceAssertion$MATCHES_getInstance() {
    DescriptionCharSequenceAssertion_initFields();
    return DescriptionCharSequenceAssertion$MATCHES_instance;
  }
  var DescriptionCharSequenceAssertion$MISMATCHES_instance;
  function DescriptionCharSequenceAssertion$MISMATCHES_getInstance() {
    DescriptionCharSequenceAssertion_initFields();
    return DescriptionCharSequenceAssertion$MISMATCHES_instance;
  }
  var DescriptionCharSequenceAssertion$NUMBER_OF_OCCURRENCES_instance;
  function DescriptionCharSequenceAssertion$NUMBER_OF_OCCURRENCES_getInstance() {
    DescriptionCharSequenceAssertion_initFields();
    return DescriptionCharSequenceAssertion$NUMBER_OF_OCCURRENCES_instance;
  }
  var DescriptionCharSequenceAssertion$STARTS_WITH_instance;
  function DescriptionCharSequenceAssertion$STARTS_WITH_getInstance() {
    DescriptionCharSequenceAssertion_initFields();
    return DescriptionCharSequenceAssertion$STARTS_WITH_instance;
  }
  var DescriptionCharSequenceAssertion$STARTS_NOT_WITH_instance;
  function DescriptionCharSequenceAssertion$STARTS_NOT_WITH_getInstance() {
    DescriptionCharSequenceAssertion_initFields();
    return DescriptionCharSequenceAssertion$STARTS_NOT_WITH_instance;
  }
  var DescriptionCharSequenceAssertion$STRING_MATCHING_REGEX_instance;
  function DescriptionCharSequenceAssertion$STRING_MATCHING_REGEX_getInstance() {
    DescriptionCharSequenceAssertion_initFields();
    return DescriptionCharSequenceAssertion$STRING_MATCHING_REGEX_instance;
  }
  var DescriptionCharSequenceAssertion$VALUE_instance;
  function DescriptionCharSequenceAssertion$VALUE_getInstance() {
    DescriptionCharSequenceAssertion_initFields();
    return DescriptionCharSequenceAssertion$VALUE_instance;
  }
  DescriptionCharSequenceAssertion.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'DescriptionCharSequenceAssertion',
    interfaces: [StringBasedTranslatable, Enum]
  };
  function DescriptionCharSequenceAssertion$values() {
    return [DescriptionCharSequenceAssertion$AT_LEAST_getInstance(), DescriptionCharSequenceAssertion$AT_MOST_getInstance(), DescriptionCharSequenceAssertion$BLANK_getInstance(), DescriptionCharSequenceAssertion$CONTAINS_getInstance(), DescriptionCharSequenceAssertion$CONTAINS_NOT_getInstance(), DescriptionCharSequenceAssertion$EMPTY_getInstance(), DescriptionCharSequenceAssertion$ENDS_WITH_getInstance(), DescriptionCharSequenceAssertion$ENDS_NOT_WITH_getInstance(), DescriptionCharSequenceAssertion$EXACTLY_getInstance(), DescriptionCharSequenceAssertion$IGNORING_CASE_getInstance(), DescriptionCharSequenceAssertion$MATCHES_getInstance(), DescriptionCharSequenceAssertion$MISMATCHES_getInstance(), DescriptionCharSequenceAssertion$NUMBER_OF_OCCURRENCES_getInstance(), DescriptionCharSequenceAssertion$STARTS_WITH_getInstance(), DescriptionCharSequenceAssertion$STARTS_NOT_WITH_getInstance(), DescriptionCharSequenceAssertion$STRING_MATCHING_REGEX_getInstance(), DescriptionCharSequenceAssertion$VALUE_getInstance()];
  }
  DescriptionCharSequenceAssertion.values = DescriptionCharSequenceAssertion$values;
  function DescriptionCharSequenceAssertion$valueOf(name) {
    switch (name) {
      case 'AT_LEAST':
        return DescriptionCharSequenceAssertion$AT_LEAST_getInstance();
      case 'AT_MOST':
        return DescriptionCharSequenceAssertion$AT_MOST_getInstance();
      case 'BLANK':
        return DescriptionCharSequenceAssertion$BLANK_getInstance();
      case 'CONTAINS':
        return DescriptionCharSequenceAssertion$CONTAINS_getInstance();
      case 'CONTAINS_NOT':
        return DescriptionCharSequenceAssertion$CONTAINS_NOT_getInstance();
      case 'EMPTY':
        return DescriptionCharSequenceAssertion$EMPTY_getInstance();
      case 'ENDS_WITH':
        return DescriptionCharSequenceAssertion$ENDS_WITH_getInstance();
      case 'ENDS_NOT_WITH':
        return DescriptionCharSequenceAssertion$ENDS_NOT_WITH_getInstance();
      case 'EXACTLY':
        return DescriptionCharSequenceAssertion$EXACTLY_getInstance();
      case 'IGNORING_CASE':
        return DescriptionCharSequenceAssertion$IGNORING_CASE_getInstance();
      case 'MATCHES':
        return DescriptionCharSequenceAssertion$MATCHES_getInstance();
      case 'MISMATCHES':
        return DescriptionCharSequenceAssertion$MISMATCHES_getInstance();
      case 'NUMBER_OF_OCCURRENCES':
        return DescriptionCharSequenceAssertion$NUMBER_OF_OCCURRENCES_getInstance();
      case 'STARTS_WITH':
        return DescriptionCharSequenceAssertion$STARTS_WITH_getInstance();
      case 'STARTS_NOT_WITH':
        return DescriptionCharSequenceAssertion$STARTS_NOT_WITH_getInstance();
      case 'STRING_MATCHING_REGEX':
        return DescriptionCharSequenceAssertion$STRING_MATCHING_REGEX_getInstance();
      case 'VALUE':
        return DescriptionCharSequenceAssertion$VALUE_getInstance();
      default:throwISE('No enum constant ch.tutteli.atrium.translations.DescriptionCharSequenceAssertion.' + name);
    }
  }
  DescriptionCharSequenceAssertion.valueOf_61zpoe$ = DescriptionCharSequenceAssertion$valueOf;
  function DescriptionCollectionAssertion(name, ordinal, value) {
    Enum.call(this);
    this.value_lah2ih$_0 = value;
    this.name$ = name;
    this.ordinal$ = ordinal;
  }
  function DescriptionCollectionAssertion_initFields() {
    DescriptionCollectionAssertion_initFields = function () {
    };
    DescriptionCollectionAssertion$EMPTY_instance = new DescriptionCollectionAssertion('EMPTY', 0, 'empty');
    DescriptionCollectionAssertion$SIZE_instance = new DescriptionCollectionAssertion('SIZE', 1, 'size');
  }
  Object.defineProperty(DescriptionCollectionAssertion.prototype, 'value', {
    get: function () {
      return this.value_lah2ih$_0;
    }
  });
  var DescriptionCollectionAssertion$EMPTY_instance;
  function DescriptionCollectionAssertion$EMPTY_getInstance() {
    DescriptionCollectionAssertion_initFields();
    return DescriptionCollectionAssertion$EMPTY_instance;
  }
  var DescriptionCollectionAssertion$SIZE_instance;
  function DescriptionCollectionAssertion$SIZE_getInstance() {
    DescriptionCollectionAssertion_initFields();
    return DescriptionCollectionAssertion$SIZE_instance;
  }
  DescriptionCollectionAssertion.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'DescriptionCollectionAssertion',
    interfaces: [StringBasedTranslatable, Enum]
  };
  function DescriptionCollectionAssertion$values() {
    return [DescriptionCollectionAssertion$EMPTY_getInstance(), DescriptionCollectionAssertion$SIZE_getInstance()];
  }
  DescriptionCollectionAssertion.values = DescriptionCollectionAssertion$values;
  function DescriptionCollectionAssertion$valueOf(name) {
    switch (name) {
      case 'EMPTY':
        return DescriptionCollectionAssertion$EMPTY_getInstance();
      case 'SIZE':
        return DescriptionCollectionAssertion$SIZE_getInstance();
      default:throwISE('No enum constant ch.tutteli.atrium.translations.DescriptionCollectionAssertion.' + name);
    }
  }
  DescriptionCollectionAssertion.valueOf_61zpoe$ = DescriptionCollectionAssertion$valueOf;
  function DescriptionComparableAssertion(name, ordinal, value) {
    Enum.call(this);
    this.value_8w01f9$_0 = value;
    this.name$ = name;
    this.ordinal$ = ordinal;
  }
  function DescriptionComparableAssertion_initFields() {
    DescriptionComparableAssertion_initFields = function () {
    };
    DescriptionComparableAssertion$IS_LESS_THAN_instance = new DescriptionComparableAssertion('IS_LESS_THAN', 0, 'is less than');
    DescriptionComparableAssertion$IS_LESS_OR_EQUALS_instance = new DescriptionComparableAssertion('IS_LESS_OR_EQUALS', 1, 'is less or equals');
    DescriptionComparableAssertion$IS_GREATER_THAN_instance = new DescriptionComparableAssertion('IS_GREATER_THAN', 2, 'is greater than');
    DescriptionComparableAssertion$IS_GREATER_OR_EQUALS_instance = new DescriptionComparableAssertion('IS_GREATER_OR_EQUALS', 3, 'is greater or equals');
  }
  Object.defineProperty(DescriptionComparableAssertion.prototype, 'value', {
    get: function () {
      return this.value_8w01f9$_0;
    }
  });
  var DescriptionComparableAssertion$IS_LESS_THAN_instance;
  function DescriptionComparableAssertion$IS_LESS_THAN_getInstance() {
    DescriptionComparableAssertion_initFields();
    return DescriptionComparableAssertion$IS_LESS_THAN_instance;
  }
  var DescriptionComparableAssertion$IS_LESS_OR_EQUALS_instance;
  function DescriptionComparableAssertion$IS_LESS_OR_EQUALS_getInstance() {
    DescriptionComparableAssertion_initFields();
    return DescriptionComparableAssertion$IS_LESS_OR_EQUALS_instance;
  }
  var DescriptionComparableAssertion$IS_GREATER_THAN_instance;
  function DescriptionComparableAssertion$IS_GREATER_THAN_getInstance() {
    DescriptionComparableAssertion_initFields();
    return DescriptionComparableAssertion$IS_GREATER_THAN_instance;
  }
  var DescriptionComparableAssertion$IS_GREATER_OR_EQUALS_instance;
  function DescriptionComparableAssertion$IS_GREATER_OR_EQUALS_getInstance() {
    DescriptionComparableAssertion_initFields();
    return DescriptionComparableAssertion$IS_GREATER_OR_EQUALS_instance;
  }
  DescriptionComparableAssertion.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'DescriptionComparableAssertion',
    interfaces: [StringBasedTranslatable, Enum]
  };
  function DescriptionComparableAssertion$values() {
    return [DescriptionComparableAssertion$IS_LESS_THAN_getInstance(), DescriptionComparableAssertion$IS_LESS_OR_EQUALS_getInstance(), DescriptionComparableAssertion$IS_GREATER_THAN_getInstance(), DescriptionComparableAssertion$IS_GREATER_OR_EQUALS_getInstance()];
  }
  DescriptionComparableAssertion.values = DescriptionComparableAssertion$values;
  function DescriptionComparableAssertion$valueOf(name) {
    switch (name) {
      case 'IS_LESS_THAN':
        return DescriptionComparableAssertion$IS_LESS_THAN_getInstance();
      case 'IS_LESS_OR_EQUALS':
        return DescriptionComparableAssertion$IS_LESS_OR_EQUALS_getInstance();
      case 'IS_GREATER_THAN':
        return DescriptionComparableAssertion$IS_GREATER_THAN_getInstance();
      case 'IS_GREATER_OR_EQUALS':
        return DescriptionComparableAssertion$IS_GREATER_OR_EQUALS_getInstance();
      default:throwISE('No enum constant ch.tutteli.atrium.translations.DescriptionComparableAssertion.' + name);
    }
  }
  DescriptionComparableAssertion.valueOf_61zpoe$ = DescriptionComparableAssertion$valueOf;
  function DescriptionDateTimeLikeAssertion(name, ordinal, value) {
    Enum.call(this);
    this.value_msuamr$_0 = value;
    this.name$ = name;
    this.ordinal$ = ordinal;
  }
  function DescriptionDateTimeLikeAssertion_initFields() {
    DescriptionDateTimeLikeAssertion_initFields = function () {
    };
    DescriptionDateTimeLikeAssertion$YEAR_instance = new DescriptionDateTimeLikeAssertion('YEAR', 0, 'year');
    DescriptionDateTimeLikeAssertion$MONTH_instance = new DescriptionDateTimeLikeAssertion('MONTH', 1, 'month');
    DescriptionDateTimeLikeAssertion$DAY_OF_WEEK_instance = new DescriptionDateTimeLikeAssertion('DAY_OF_WEEK', 2, 'day of week');
  }
  Object.defineProperty(DescriptionDateTimeLikeAssertion.prototype, 'value', {
    get: function () {
      return this.value_msuamr$_0;
    }
  });
  var DescriptionDateTimeLikeAssertion$YEAR_instance;
  function DescriptionDateTimeLikeAssertion$YEAR_getInstance() {
    DescriptionDateTimeLikeAssertion_initFields();
    return DescriptionDateTimeLikeAssertion$YEAR_instance;
  }
  var DescriptionDateTimeLikeAssertion$MONTH_instance;
  function DescriptionDateTimeLikeAssertion$MONTH_getInstance() {
    DescriptionDateTimeLikeAssertion_initFields();
    return DescriptionDateTimeLikeAssertion$MONTH_instance;
  }
  var DescriptionDateTimeLikeAssertion$DAY_OF_WEEK_instance;
  function DescriptionDateTimeLikeAssertion$DAY_OF_WEEK_getInstance() {
    DescriptionDateTimeLikeAssertion_initFields();
    return DescriptionDateTimeLikeAssertion$DAY_OF_WEEK_instance;
  }
  DescriptionDateTimeLikeAssertion.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'DescriptionDateTimeLikeAssertion',
    interfaces: [StringBasedTranslatable, Enum]
  };
  function DescriptionDateTimeLikeAssertion$values() {
    return [DescriptionDateTimeLikeAssertion$YEAR_getInstance(), DescriptionDateTimeLikeAssertion$MONTH_getInstance(), DescriptionDateTimeLikeAssertion$DAY_OF_WEEK_getInstance()];
  }
  DescriptionDateTimeLikeAssertion.values = DescriptionDateTimeLikeAssertion$values;
  function DescriptionDateTimeLikeAssertion$valueOf(name) {
    switch (name) {
      case 'YEAR':
        return DescriptionDateTimeLikeAssertion$YEAR_getInstance();
      case 'MONTH':
        return DescriptionDateTimeLikeAssertion$MONTH_getInstance();
      case 'DAY_OF_WEEK':
        return DescriptionDateTimeLikeAssertion$DAY_OF_WEEK_getInstance();
      default:throwISE('No enum constant ch.tutteli.atrium.translations.DescriptionDateTimeLikeAssertion.' + name);
    }
  }
  DescriptionDateTimeLikeAssertion.valueOf_61zpoe$ = DescriptionDateTimeLikeAssertion$valueOf;
  function DescriptionFloatingPointAssertion(name, ordinal, value) {
    Enum.call(this);
    this.value_2rkrnd$_0 = value;
    this.name$ = name;
    this.ordinal$ = ordinal;
  }
  function DescriptionFloatingPointAssertion_initFields() {
    DescriptionFloatingPointAssertion_initFields = function () {
    };
    DescriptionFloatingPointAssertion$TO_BE_WITH_ERROR_TOLERANCE_instance = new DescriptionFloatingPointAssertion('TO_BE_WITH_ERROR_TOLERANCE', 0, 'to be (error \xB1 %s)');
    DescriptionFloatingPointAssertion$FAILURE_DUE_TO_FLOATING_POINT_NUMBER_instance = new DescriptionFloatingPointAssertion('FAILURE_DUE_TO_FLOATING_POINT_NUMBER', 1, 'failure might be due to using %s, see exact check on the next line');
    DescriptionFloatingPointAssertion$TO_BE_WITH_ERROR_TOLERANCE_EXPLAINED_instance = new DescriptionFloatingPointAssertion('TO_BE_WITH_ERROR_TOLERANCE_EXPLAINED', 2, 'exact check is |%s - %s| = %s \u2264 %s');
  }
  Object.defineProperty(DescriptionFloatingPointAssertion.prototype, 'value', {
    get: function () {
      return this.value_2rkrnd$_0;
    }
  });
  var DescriptionFloatingPointAssertion$TO_BE_WITH_ERROR_TOLERANCE_instance;
  function DescriptionFloatingPointAssertion$TO_BE_WITH_ERROR_TOLERANCE_getInstance() {
    DescriptionFloatingPointAssertion_initFields();
    return DescriptionFloatingPointAssertion$TO_BE_WITH_ERROR_TOLERANCE_instance;
  }
  var DescriptionFloatingPointAssertion$FAILURE_DUE_TO_FLOATING_POINT_NUMBER_instance;
  function DescriptionFloatingPointAssertion$FAILURE_DUE_TO_FLOATING_POINT_NUMBER_getInstance() {
    DescriptionFloatingPointAssertion_initFields();
    return DescriptionFloatingPointAssertion$FAILURE_DUE_TO_FLOATING_POINT_NUMBER_instance;
  }
  var DescriptionFloatingPointAssertion$TO_BE_WITH_ERROR_TOLERANCE_EXPLAINED_instance;
  function DescriptionFloatingPointAssertion$TO_BE_WITH_ERROR_TOLERANCE_EXPLAINED_getInstance() {
    DescriptionFloatingPointAssertion_initFields();
    return DescriptionFloatingPointAssertion$TO_BE_WITH_ERROR_TOLERANCE_EXPLAINED_instance;
  }
  DescriptionFloatingPointAssertion.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'DescriptionFloatingPointAssertion',
    interfaces: [StringBasedTranslatable, Enum]
  };
  function DescriptionFloatingPointAssertion$values() {
    return [DescriptionFloatingPointAssertion$TO_BE_WITH_ERROR_TOLERANCE_getInstance(), DescriptionFloatingPointAssertion$FAILURE_DUE_TO_FLOATING_POINT_NUMBER_getInstance(), DescriptionFloatingPointAssertion$TO_BE_WITH_ERROR_TOLERANCE_EXPLAINED_getInstance()];
  }
  DescriptionFloatingPointAssertion.values = DescriptionFloatingPointAssertion$values;
  function DescriptionFloatingPointAssertion$valueOf(name) {
    switch (name) {
      case 'TO_BE_WITH_ERROR_TOLERANCE':
        return DescriptionFloatingPointAssertion$TO_BE_WITH_ERROR_TOLERANCE_getInstance();
      case 'FAILURE_DUE_TO_FLOATING_POINT_NUMBER':
        return DescriptionFloatingPointAssertion$FAILURE_DUE_TO_FLOATING_POINT_NUMBER_getInstance();
      case 'TO_BE_WITH_ERROR_TOLERANCE_EXPLAINED':
        return DescriptionFloatingPointAssertion$TO_BE_WITH_ERROR_TOLERANCE_EXPLAINED_getInstance();
      default:throwISE('No enum constant ch.tutteli.atrium.translations.DescriptionFloatingPointAssertion.' + name);
    }
  }
  DescriptionFloatingPointAssertion.valueOf_61zpoe$ = DescriptionFloatingPointAssertion$valueOf;
  function DescriptionIterableAssertion(name, ordinal, value) {
    Enum.call(this);
    this.value_a03ej1$_0 = value;
    this.name$ = name;
    this.ordinal$ = ordinal;
  }
  function DescriptionIterableAssertion_initFields() {
    DescriptionIterableAssertion_initFields = function () {
    };
    DescriptionIterableAssertion$ALL_instance = new DescriptionIterableAssertion('ALL', 0, 'all entries');
    DescriptionIterableAssertion$AN_ENTRY_WHICH_instance = new DescriptionIterableAssertion('AN_ENTRY_WHICH', 1, 'an entry which');
    DescriptionIterableAssertion$AN_ENTRY_WHICH_IS_instance = new DescriptionIterableAssertion('AN_ENTRY_WHICH_IS', 2, 'an entry which is');
    DescriptionIterableAssertion$AT_LEAST_instance = new DescriptionIterableAssertion('AT_LEAST', 3, 'is at least');
    DescriptionIterableAssertion$AT_MOST_instance = new DescriptionIterableAssertion('AT_MOST', 4, 'is at most');
    DescriptionIterableAssertion$CONTAINS_instance = new DescriptionIterableAssertion('CONTAINS', 5, 'contains');
    DescriptionIterableAssertion$CONTAINS_NOT_instance = new DescriptionIterableAssertion('CONTAINS_NOT', 6, 'does not contain');
    DescriptionIterableAssertion$ENTRY_WITH_INDEX_instance = new DescriptionIterableAssertion('ENTRY_WITH_INDEX', 7, 'entry %s');
    DescriptionIterableAssertion$EXACTLY_instance = new DescriptionIterableAssertion('EXACTLY', 8, 'is exactly');
    DescriptionIterableAssertion$HAS_ELEMENT_instance = new DescriptionIterableAssertion('HAS_ELEMENT', 9, 'has at least one element');
    DescriptionIterableAssertion$IN_ANY_ORDER_instance = new DescriptionIterableAssertion('IN_ANY_ORDER', 10, '%s, in any order');
    DescriptionIterableAssertion$IN_ANY_ORDER_ONLY_instance = new DescriptionIterableAssertion('IN_ANY_ORDER_ONLY', 11, '%s only, in any order');
    DescriptionIterableAssertion$IN_ORDER_instance = new DescriptionIterableAssertion('IN_ORDER', 12, '%, in order');
    DescriptionIterableAssertion$IN_ORDER_ONLY_instance = new DescriptionIterableAssertion('IN_ORDER_ONLY', 13, '%s only, in order');
    DescriptionIterableAssertion$IN_ORDER_ONLY_GROUPED_instance = new DescriptionIterableAssertion('IN_ORDER_ONLY_GROUPED', 14, '%s only, in order, grouped');
    DescriptionIterableAssertion$INDEX_instance = new DescriptionIterableAssertion('INDEX', 15, 'index %s');
    DescriptionIterableAssertion$INDEX_FROM_TO_instance = new DescriptionIterableAssertion('INDEX_FROM_TO', 16, 'index %s..%s');
    DescriptionIterableAssertion$NUMBER_OF_OCCURRENCES_instance = new DescriptionIterableAssertion('NUMBER_OF_OCCURRENCES', 17, 'number of occurrences');
    DescriptionIterableAssertion$SIZE_EXCEEDED_instance = new DescriptionIterableAssertion('SIZE_EXCEEDED', 18, '\u2757\u2757 hasNext() returned false');
    DescriptionIterableAssertion$CANNOT_EVALUATE_SUBJECT_EMPTY_ITERABLE_instance = new DescriptionIterableAssertion('CANNOT_EVALUATE_SUBJECT_EMPTY_ITERABLE', 19, 'Could not evaluate the defined assertion(s) -- `Iterable` has no next entry.\nVisit the following site for an explanation: https://docs.atriumlib.org/could-not-evaluate-assertions');
    DescriptionIterableAssertion$CANNOT_EVALUATE_SUBJECT_ONLY_NULL_instance = new DescriptionIterableAssertion('CANNOT_EVALUATE_SUBJECT_ONLY_NULL', 20, 'Could not evaluate the defined assertion(s) -- `Iterable` returns only `null` for `next()`.\nVisit the following site for an explanation: https://docs.atriumlib.org/could-not-evaluate-assertions');
    DescriptionIterableAssertion$WARNING_ADDITIONAL_ENTRIES_instance = new DescriptionIterableAssertion('WARNING_ADDITIONAL_ENTRIES', 21, 'additional entries detected');
    DescriptionIterableAssertion$WARNING_MISMATCHES_instance = new DescriptionIterableAssertion('WARNING_MISMATCHES', 22, 'following entries were mismatched');
    DescriptionIterableAssertion$WARNING_MISMATCHES_ADDITIONAL_ENTRIES_instance = new DescriptionIterableAssertion('WARNING_MISMATCHES_ADDITIONAL_ENTRIES', 23, 'mismatches and additional entries detected');
    DescriptionIterableAssertion$NEXT_ELEMENT_instance = new DescriptionIterableAssertion('NEXT_ELEMENT', 24, 'a next element');
    DescriptionIterableAssertion$NO_ELEMENTS_instance = new DescriptionIterableAssertion('NO_ELEMENTS', 25, '\u2757\u2757 cannot be determined, empty Iterable');
  }
  Object.defineProperty(DescriptionIterableAssertion.prototype, 'value', {
    get: function () {
      return this.value_a03ej1$_0;
    }
  });
  var DescriptionIterableAssertion$ALL_instance;
  function DescriptionIterableAssertion$ALL_getInstance() {
    DescriptionIterableAssertion_initFields();
    return DescriptionIterableAssertion$ALL_instance;
  }
  var DescriptionIterableAssertion$AN_ENTRY_WHICH_instance;
  function DescriptionIterableAssertion$AN_ENTRY_WHICH_getInstance() {
    DescriptionIterableAssertion_initFields();
    return DescriptionIterableAssertion$AN_ENTRY_WHICH_instance;
  }
  var DescriptionIterableAssertion$AN_ENTRY_WHICH_IS_instance;
  function DescriptionIterableAssertion$AN_ENTRY_WHICH_IS_getInstance() {
    DescriptionIterableAssertion_initFields();
    return DescriptionIterableAssertion$AN_ENTRY_WHICH_IS_instance;
  }
  var DescriptionIterableAssertion$AT_LEAST_instance;
  function DescriptionIterableAssertion$AT_LEAST_getInstance() {
    DescriptionIterableAssertion_initFields();
    return DescriptionIterableAssertion$AT_LEAST_instance;
  }
  var DescriptionIterableAssertion$AT_MOST_instance;
  function DescriptionIterableAssertion$AT_MOST_getInstance() {
    DescriptionIterableAssertion_initFields();
    return DescriptionIterableAssertion$AT_MOST_instance;
  }
  var DescriptionIterableAssertion$CONTAINS_instance;
  function DescriptionIterableAssertion$CONTAINS_getInstance() {
    DescriptionIterableAssertion_initFields();
    return DescriptionIterableAssertion$CONTAINS_instance;
  }
  var DescriptionIterableAssertion$CONTAINS_NOT_instance;
  function DescriptionIterableAssertion$CONTAINS_NOT_getInstance() {
    DescriptionIterableAssertion_initFields();
    return DescriptionIterableAssertion$CONTAINS_NOT_instance;
  }
  var DescriptionIterableAssertion$ENTRY_WITH_INDEX_instance;
  function DescriptionIterableAssertion$ENTRY_WITH_INDEX_getInstance() {
    DescriptionIterableAssertion_initFields();
    return DescriptionIterableAssertion$ENTRY_WITH_INDEX_instance;
  }
  var DescriptionIterableAssertion$EXACTLY_instance;
  function DescriptionIterableAssertion$EXACTLY_getInstance() {
    DescriptionIterableAssertion_initFields();
    return DescriptionIterableAssertion$EXACTLY_instance;
  }
  var DescriptionIterableAssertion$HAS_ELEMENT_instance;
  function DescriptionIterableAssertion$HAS_ELEMENT_getInstance() {
    DescriptionIterableAssertion_initFields();
    return DescriptionIterableAssertion$HAS_ELEMENT_instance;
  }
  var DescriptionIterableAssertion$IN_ANY_ORDER_instance;
  function DescriptionIterableAssertion$IN_ANY_ORDER_getInstance() {
    DescriptionIterableAssertion_initFields();
    return DescriptionIterableAssertion$IN_ANY_ORDER_instance;
  }
  var DescriptionIterableAssertion$IN_ANY_ORDER_ONLY_instance;
  function DescriptionIterableAssertion$IN_ANY_ORDER_ONLY_getInstance() {
    DescriptionIterableAssertion_initFields();
    return DescriptionIterableAssertion$IN_ANY_ORDER_ONLY_instance;
  }
  var DescriptionIterableAssertion$IN_ORDER_instance;
  function DescriptionIterableAssertion$IN_ORDER_getInstance() {
    DescriptionIterableAssertion_initFields();
    return DescriptionIterableAssertion$IN_ORDER_instance;
  }
  var DescriptionIterableAssertion$IN_ORDER_ONLY_instance;
  function DescriptionIterableAssertion$IN_ORDER_ONLY_getInstance() {
    DescriptionIterableAssertion_initFields();
    return DescriptionIterableAssertion$IN_ORDER_ONLY_instance;
  }
  var DescriptionIterableAssertion$IN_ORDER_ONLY_GROUPED_instance;
  function DescriptionIterableAssertion$IN_ORDER_ONLY_GROUPED_getInstance() {
    DescriptionIterableAssertion_initFields();
    return DescriptionIterableAssertion$IN_ORDER_ONLY_GROUPED_instance;
  }
  var DescriptionIterableAssertion$INDEX_instance;
  function DescriptionIterableAssertion$INDEX_getInstance() {
    DescriptionIterableAssertion_initFields();
    return DescriptionIterableAssertion$INDEX_instance;
  }
  var DescriptionIterableAssertion$INDEX_FROM_TO_instance;
  function DescriptionIterableAssertion$INDEX_FROM_TO_getInstance() {
    DescriptionIterableAssertion_initFields();
    return DescriptionIterableAssertion$INDEX_FROM_TO_instance;
  }
  var DescriptionIterableAssertion$NUMBER_OF_OCCURRENCES_instance;
  function DescriptionIterableAssertion$NUMBER_OF_OCCURRENCES_getInstance() {
    DescriptionIterableAssertion_initFields();
    return DescriptionIterableAssertion$NUMBER_OF_OCCURRENCES_instance;
  }
  var DescriptionIterableAssertion$SIZE_EXCEEDED_instance;
  function DescriptionIterableAssertion$SIZE_EXCEEDED_getInstance() {
    DescriptionIterableAssertion_initFields();
    return DescriptionIterableAssertion$SIZE_EXCEEDED_instance;
  }
  var DescriptionIterableAssertion$CANNOT_EVALUATE_SUBJECT_EMPTY_ITERABLE_instance;
  function DescriptionIterableAssertion$CANNOT_EVALUATE_SUBJECT_EMPTY_ITERABLE_getInstance() {
    DescriptionIterableAssertion_initFields();
    return DescriptionIterableAssertion$CANNOT_EVALUATE_SUBJECT_EMPTY_ITERABLE_instance;
  }
  var DescriptionIterableAssertion$CANNOT_EVALUATE_SUBJECT_ONLY_NULL_instance;
  function DescriptionIterableAssertion$CANNOT_EVALUATE_SUBJECT_ONLY_NULL_getInstance() {
    DescriptionIterableAssertion_initFields();
    return DescriptionIterableAssertion$CANNOT_EVALUATE_SUBJECT_ONLY_NULL_instance;
  }
  var DescriptionIterableAssertion$WARNING_ADDITIONAL_ENTRIES_instance;
  function DescriptionIterableAssertion$WARNING_ADDITIONAL_ENTRIES_getInstance() {
    DescriptionIterableAssertion_initFields();
    return DescriptionIterableAssertion$WARNING_ADDITIONAL_ENTRIES_instance;
  }
  var DescriptionIterableAssertion$WARNING_MISMATCHES_instance;
  function DescriptionIterableAssertion$WARNING_MISMATCHES_getInstance() {
    DescriptionIterableAssertion_initFields();
    return DescriptionIterableAssertion$WARNING_MISMATCHES_instance;
  }
  var DescriptionIterableAssertion$WARNING_MISMATCHES_ADDITIONAL_ENTRIES_instance;
  function DescriptionIterableAssertion$WARNING_MISMATCHES_ADDITIONAL_ENTRIES_getInstance() {
    DescriptionIterableAssertion_initFields();
    return DescriptionIterableAssertion$WARNING_MISMATCHES_ADDITIONAL_ENTRIES_instance;
  }
  var DescriptionIterableAssertion$NEXT_ELEMENT_instance;
  function DescriptionIterableAssertion$NEXT_ELEMENT_getInstance() {
    DescriptionIterableAssertion_initFields();
    return DescriptionIterableAssertion$NEXT_ELEMENT_instance;
  }
  var DescriptionIterableAssertion$NO_ELEMENTS_instance;
  function DescriptionIterableAssertion$NO_ELEMENTS_getInstance() {
    DescriptionIterableAssertion_initFields();
    return DescriptionIterableAssertion$NO_ELEMENTS_instance;
  }
  DescriptionIterableAssertion.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'DescriptionIterableAssertion',
    interfaces: [StringBasedTranslatable, Enum]
  };
  function DescriptionIterableAssertion$values() {
    return [DescriptionIterableAssertion$ALL_getInstance(), DescriptionIterableAssertion$AN_ENTRY_WHICH_getInstance(), DescriptionIterableAssertion$AN_ENTRY_WHICH_IS_getInstance(), DescriptionIterableAssertion$AT_LEAST_getInstance(), DescriptionIterableAssertion$AT_MOST_getInstance(), DescriptionIterableAssertion$CONTAINS_getInstance(), DescriptionIterableAssertion$CONTAINS_NOT_getInstance(), DescriptionIterableAssertion$ENTRY_WITH_INDEX_getInstance(), DescriptionIterableAssertion$EXACTLY_getInstance(), DescriptionIterableAssertion$HAS_ELEMENT_getInstance(), DescriptionIterableAssertion$IN_ANY_ORDER_getInstance(), DescriptionIterableAssertion$IN_ANY_ORDER_ONLY_getInstance(), DescriptionIterableAssertion$IN_ORDER_getInstance(), DescriptionIterableAssertion$IN_ORDER_ONLY_getInstance(), DescriptionIterableAssertion$IN_ORDER_ONLY_GROUPED_getInstance(), DescriptionIterableAssertion$INDEX_getInstance(), DescriptionIterableAssertion$INDEX_FROM_TO_getInstance(), DescriptionIterableAssertion$NUMBER_OF_OCCURRENCES_getInstance(), DescriptionIterableAssertion$SIZE_EXCEEDED_getInstance(), DescriptionIterableAssertion$CANNOT_EVALUATE_SUBJECT_EMPTY_ITERABLE_getInstance(), DescriptionIterableAssertion$CANNOT_EVALUATE_SUBJECT_ONLY_NULL_getInstance(), DescriptionIterableAssertion$WARNING_ADDITIONAL_ENTRIES_getInstance(), DescriptionIterableAssertion$WARNING_MISMATCHES_getInstance(), DescriptionIterableAssertion$WARNING_MISMATCHES_ADDITIONAL_ENTRIES_getInstance(), DescriptionIterableAssertion$NEXT_ELEMENT_getInstance(), DescriptionIterableAssertion$NO_ELEMENTS_getInstance()];
  }
  DescriptionIterableAssertion.values = DescriptionIterableAssertion$values;
  function DescriptionIterableAssertion$valueOf(name) {
    switch (name) {
      case 'ALL':
        return DescriptionIterableAssertion$ALL_getInstance();
      case 'AN_ENTRY_WHICH':
        return DescriptionIterableAssertion$AN_ENTRY_WHICH_getInstance();
      case 'AN_ENTRY_WHICH_IS':
        return DescriptionIterableAssertion$AN_ENTRY_WHICH_IS_getInstance();
      case 'AT_LEAST':
        return DescriptionIterableAssertion$AT_LEAST_getInstance();
      case 'AT_MOST':
        return DescriptionIterableAssertion$AT_MOST_getInstance();
      case 'CONTAINS':
        return DescriptionIterableAssertion$CONTAINS_getInstance();
      case 'CONTAINS_NOT':
        return DescriptionIterableAssertion$CONTAINS_NOT_getInstance();
      case 'ENTRY_WITH_INDEX':
        return DescriptionIterableAssertion$ENTRY_WITH_INDEX_getInstance();
      case 'EXACTLY':
        return DescriptionIterableAssertion$EXACTLY_getInstance();
      case 'HAS_ELEMENT':
        return DescriptionIterableAssertion$HAS_ELEMENT_getInstance();
      case 'IN_ANY_ORDER':
        return DescriptionIterableAssertion$IN_ANY_ORDER_getInstance();
      case 'IN_ANY_ORDER_ONLY':
        return DescriptionIterableAssertion$IN_ANY_ORDER_ONLY_getInstance();
      case 'IN_ORDER':
        return DescriptionIterableAssertion$IN_ORDER_getInstance();
      case 'IN_ORDER_ONLY':
        return DescriptionIterableAssertion$IN_ORDER_ONLY_getInstance();
      case 'IN_ORDER_ONLY_GROUPED':
        return DescriptionIterableAssertion$IN_ORDER_ONLY_GROUPED_getInstance();
      case 'INDEX':
        return DescriptionIterableAssertion$INDEX_getInstance();
      case 'INDEX_FROM_TO':
        return DescriptionIterableAssertion$INDEX_FROM_TO_getInstance();
      case 'NUMBER_OF_OCCURRENCES':
        return DescriptionIterableAssertion$NUMBER_OF_OCCURRENCES_getInstance();
      case 'SIZE_EXCEEDED':
        return DescriptionIterableAssertion$SIZE_EXCEEDED_getInstance();
      case 'CANNOT_EVALUATE_SUBJECT_EMPTY_ITERABLE':
        return DescriptionIterableAssertion$CANNOT_EVALUATE_SUBJECT_EMPTY_ITERABLE_getInstance();
      case 'CANNOT_EVALUATE_SUBJECT_ONLY_NULL':
        return DescriptionIterableAssertion$CANNOT_EVALUATE_SUBJECT_ONLY_NULL_getInstance();
      case 'WARNING_ADDITIONAL_ENTRIES':
        return DescriptionIterableAssertion$WARNING_ADDITIONAL_ENTRIES_getInstance();
      case 'WARNING_MISMATCHES':
        return DescriptionIterableAssertion$WARNING_MISMATCHES_getInstance();
      case 'WARNING_MISMATCHES_ADDITIONAL_ENTRIES':
        return DescriptionIterableAssertion$WARNING_MISMATCHES_ADDITIONAL_ENTRIES_getInstance();
      case 'NEXT_ELEMENT':
        return DescriptionIterableAssertion$NEXT_ELEMENT_getInstance();
      case 'NO_ELEMENTS':
        return DescriptionIterableAssertion$NO_ELEMENTS_getInstance();
      default:throwISE('No enum constant ch.tutteli.atrium.translations.DescriptionIterableAssertion.' + name);
    }
  }
  DescriptionIterableAssertion.valueOf_61zpoe$ = DescriptionIterableAssertion$valueOf;
  var COULD_NOT_EVALUATE_DEFINED_ASSERTIONS;
  var VISIT_COULD_NOT_EVALUATE_ASSERTIONS;
  function DescriptionListAssertion(name, ordinal, value) {
    Enum.call(this);
    this.value_qwxyfb$_0 = value;
    this.name$ = name;
    this.ordinal$ = ordinal;
  }
  function DescriptionListAssertion_initFields() {
    DescriptionListAssertion_initFields = function () {
    };
    DescriptionListAssertion$CANNOT_EVALUATE_INDEX_OUT_OF_BOUNDS_instance = new DescriptionListAssertion('CANNOT_EVALUATE_INDEX_OUT_OF_BOUNDS', 0, 'Could not evaluate the defined assertion(s) -- index out of bounds.\nVisit the following site for an explanation: https://docs.atriumlib.org/could-not-evaluate-assertions');
    DescriptionListAssertion$INDEX_OUT_OF_BOUNDS_instance = new DescriptionListAssertion('INDEX_OUT_OF_BOUNDS', 1, '\u2757\u2757 index out of bounds');
  }
  Object.defineProperty(DescriptionListAssertion.prototype, 'value', {
    get: function () {
      return this.value_qwxyfb$_0;
    }
  });
  var DescriptionListAssertion$CANNOT_EVALUATE_INDEX_OUT_OF_BOUNDS_instance;
  function DescriptionListAssertion$CANNOT_EVALUATE_INDEX_OUT_OF_BOUNDS_getInstance() {
    DescriptionListAssertion_initFields();
    return DescriptionListAssertion$CANNOT_EVALUATE_INDEX_OUT_OF_BOUNDS_instance;
  }
  var DescriptionListAssertion$INDEX_OUT_OF_BOUNDS_instance;
  function DescriptionListAssertion$INDEX_OUT_OF_BOUNDS_getInstance() {
    DescriptionListAssertion_initFields();
    return DescriptionListAssertion$INDEX_OUT_OF_BOUNDS_instance;
  }
  DescriptionListAssertion.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'DescriptionListAssertion',
    interfaces: [StringBasedTranslatable, Enum]
  };
  function DescriptionListAssertion$values() {
    return [DescriptionListAssertion$CANNOT_EVALUATE_INDEX_OUT_OF_BOUNDS_getInstance(), DescriptionListAssertion$INDEX_OUT_OF_BOUNDS_getInstance()];
  }
  DescriptionListAssertion.values = DescriptionListAssertion$values;
  function DescriptionListAssertion$valueOf(name) {
    switch (name) {
      case 'CANNOT_EVALUATE_INDEX_OUT_OF_BOUNDS':
        return DescriptionListAssertion$CANNOT_EVALUATE_INDEX_OUT_OF_BOUNDS_getInstance();
      case 'INDEX_OUT_OF_BOUNDS':
        return DescriptionListAssertion$INDEX_OUT_OF_BOUNDS_getInstance();
      default:throwISE('No enum constant ch.tutteli.atrium.translations.DescriptionListAssertion.' + name);
    }
  }
  DescriptionListAssertion.valueOf_61zpoe$ = DescriptionListAssertion$valueOf;
  function DescriptionMapAssertion(name, ordinal, value) {
    Enum.call(this);
    this.value_kce1jp$_0 = value;
    this.name$ = name;
    this.ordinal$ = ordinal;
  }
  function DescriptionMapAssertion_initFields() {
    DescriptionMapAssertion_initFields = function () {
    };
    DescriptionMapAssertion$CANNOT_EVALUATE_KEY_DOES_NOT_EXIST_instance = new DescriptionMapAssertion('CANNOT_EVALUATE_KEY_DOES_NOT_EXIST', 0, 'Could not evaluate the defined assertion(s) -- given key does not exist.\nVisit the following site for an explanation: https://docs.atriumlib.org/could-not-evaluate-assertions');
    DescriptionMapAssertion$CONTAINS_IN_ANY_ORDER_instance = new DescriptionMapAssertion('CONTAINS_IN_ANY_ORDER', 1, 'contains, in any order');
    DescriptionMapAssertion$CONTAINS_KEY_instance = new DescriptionMapAssertion('CONTAINS_KEY', 2, 'contains key');
    DescriptionMapAssertion$CONTAINS_NOT_KEY_instance = new DescriptionMapAssertion('CONTAINS_NOT_KEY', 3, 'does not contain key');
    DescriptionMapAssertion$ENTRY_WITH_KEY_instance = new DescriptionMapAssertion('ENTRY_WITH_KEY', 4, 'entry %s');
    DescriptionMapAssertion$KEY_DOES_NOT_EXIST_instance = new DescriptionMapAssertion('KEY_DOES_NOT_EXIST', 5, '\u2757\u2757 key does not exist');
    DescriptionMapAssertion$SIZE_instance = new DescriptionMapAssertion('SIZE', 6, 'size');
  }
  Object.defineProperty(DescriptionMapAssertion.prototype, 'value', {
    get: function () {
      return this.value_kce1jp$_0;
    }
  });
  var DescriptionMapAssertion$CANNOT_EVALUATE_KEY_DOES_NOT_EXIST_instance;
  function DescriptionMapAssertion$CANNOT_EVALUATE_KEY_DOES_NOT_EXIST_getInstance() {
    DescriptionMapAssertion_initFields();
    return DescriptionMapAssertion$CANNOT_EVALUATE_KEY_DOES_NOT_EXIST_instance;
  }
  var DescriptionMapAssertion$CONTAINS_IN_ANY_ORDER_instance;
  function DescriptionMapAssertion$CONTAINS_IN_ANY_ORDER_getInstance() {
    DescriptionMapAssertion_initFields();
    return DescriptionMapAssertion$CONTAINS_IN_ANY_ORDER_instance;
  }
  var DescriptionMapAssertion$CONTAINS_KEY_instance;
  function DescriptionMapAssertion$CONTAINS_KEY_getInstance() {
    DescriptionMapAssertion_initFields();
    return DescriptionMapAssertion$CONTAINS_KEY_instance;
  }
  var DescriptionMapAssertion$CONTAINS_NOT_KEY_instance;
  function DescriptionMapAssertion$CONTAINS_NOT_KEY_getInstance() {
    DescriptionMapAssertion_initFields();
    return DescriptionMapAssertion$CONTAINS_NOT_KEY_instance;
  }
  var DescriptionMapAssertion$ENTRY_WITH_KEY_instance;
  function DescriptionMapAssertion$ENTRY_WITH_KEY_getInstance() {
    DescriptionMapAssertion_initFields();
    return DescriptionMapAssertion$ENTRY_WITH_KEY_instance;
  }
  var DescriptionMapAssertion$KEY_DOES_NOT_EXIST_instance;
  function DescriptionMapAssertion$KEY_DOES_NOT_EXIST_getInstance() {
    DescriptionMapAssertion_initFields();
    return DescriptionMapAssertion$KEY_DOES_NOT_EXIST_instance;
  }
  var DescriptionMapAssertion$SIZE_instance;
  function DescriptionMapAssertion$SIZE_getInstance() {
    DescriptionMapAssertion_initFields();
    return DescriptionMapAssertion$SIZE_instance;
  }
  DescriptionMapAssertion.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'DescriptionMapAssertion',
    interfaces: [StringBasedTranslatable, Enum]
  };
  function DescriptionMapAssertion$values() {
    return [DescriptionMapAssertion$CANNOT_EVALUATE_KEY_DOES_NOT_EXIST_getInstance(), DescriptionMapAssertion$CONTAINS_IN_ANY_ORDER_getInstance(), DescriptionMapAssertion$CONTAINS_KEY_getInstance(), DescriptionMapAssertion$CONTAINS_NOT_KEY_getInstance(), DescriptionMapAssertion$ENTRY_WITH_KEY_getInstance(), DescriptionMapAssertion$KEY_DOES_NOT_EXIST_getInstance(), DescriptionMapAssertion$SIZE_getInstance()];
  }
  DescriptionMapAssertion.values = DescriptionMapAssertion$values;
  function DescriptionMapAssertion$valueOf(name) {
    switch (name) {
      case 'CANNOT_EVALUATE_KEY_DOES_NOT_EXIST':
        return DescriptionMapAssertion$CANNOT_EVALUATE_KEY_DOES_NOT_EXIST_getInstance();
      case 'CONTAINS_IN_ANY_ORDER':
        return DescriptionMapAssertion$CONTAINS_IN_ANY_ORDER_getInstance();
      case 'CONTAINS_KEY':
        return DescriptionMapAssertion$CONTAINS_KEY_getInstance();
      case 'CONTAINS_NOT_KEY':
        return DescriptionMapAssertion$CONTAINS_NOT_KEY_getInstance();
      case 'ENTRY_WITH_KEY':
        return DescriptionMapAssertion$ENTRY_WITH_KEY_getInstance();
      case 'KEY_DOES_NOT_EXIST':
        return DescriptionMapAssertion$KEY_DOES_NOT_EXIST_getInstance();
      case 'SIZE':
        return DescriptionMapAssertion$SIZE_getInstance();
      default:throwISE('No enum constant ch.tutteli.atrium.translations.DescriptionMapAssertion.' + name);
    }
  }
  DescriptionMapAssertion.valueOf_61zpoe$ = DescriptionMapAssertion$valueOf;
  function DescriptionThrowableAssertion(name, ordinal, value) {
    Enum.call(this);
    this.value_7s06wh$_0 = value;
    this.name$ = name;
    this.ordinal$ = ordinal;
  }
  function DescriptionThrowableAssertion_initFields() {
    DescriptionThrowableAssertion_initFields = function () {
    };
    DescriptionThrowableAssertion$IS_A_instance = new DescriptionThrowableAssertion('IS_A', 0, 'is a');
    DescriptionThrowableAssertion$NO_EXCEPTION_OCCURRED_instance = new DescriptionThrowableAssertion('NO_EXCEPTION_OCCURRED', 1, 'no exception occurred');
    DescriptionThrowableAssertion$OCCURRED_EXCEPTION_CAUSE_instance = new DescriptionThrowableAssertion('OCCURRED_EXCEPTION_CAUSE', 2, 'cause');
    DescriptionThrowableAssertion$OCCURRED_EXCEPTION_PROPERTIES_instance = new DescriptionThrowableAssertion('OCCURRED_EXCEPTION_PROPERTIES', 3, 'Properties of the unexpected %s');
    DescriptionThrowableAssertion$OCCURRED_EXCEPTION_MESSAGE_instance = new DescriptionThrowableAssertion('OCCURRED_EXCEPTION_MESSAGE', 4, 'message');
    DescriptionThrowableAssertion$OCCURRED_EXCEPTION_STACKTRACE_instance = new DescriptionThrowableAssertion('OCCURRED_EXCEPTION_STACKTRACE', 5, 'stacktrace');
    DescriptionThrowableAssertion$OCCURRED_EXCEPTION_SUPPRESSED_instance = new DescriptionThrowableAssertion('OCCURRED_EXCEPTION_SUPPRESSED', 6, 'suppressed');
    DescriptionThrowableAssertion$IS_NOT_THROWN_1_instance = new DescriptionThrowableAssertion('IS_NOT_THROWN_1', 7, 'is');
    DescriptionThrowableAssertion$IS_NOT_THROWN_2_instance = new DescriptionThrowableAssertion('IS_NOT_THROWN_2', 8, 'not thrown at all');
  }
  Object.defineProperty(DescriptionThrowableAssertion.prototype, 'value', {
    get: function () {
      return this.value_7s06wh$_0;
    }
  });
  var DescriptionThrowableAssertion$IS_A_instance;
  function DescriptionThrowableAssertion$IS_A_getInstance() {
    DescriptionThrowableAssertion_initFields();
    return DescriptionThrowableAssertion$IS_A_instance;
  }
  var DescriptionThrowableAssertion$NO_EXCEPTION_OCCURRED_instance;
  function DescriptionThrowableAssertion$NO_EXCEPTION_OCCURRED_getInstance() {
    DescriptionThrowableAssertion_initFields();
    return DescriptionThrowableAssertion$NO_EXCEPTION_OCCURRED_instance;
  }
  var DescriptionThrowableAssertion$OCCURRED_EXCEPTION_CAUSE_instance;
  function DescriptionThrowableAssertion$OCCURRED_EXCEPTION_CAUSE_getInstance() {
    DescriptionThrowableAssertion_initFields();
    return DescriptionThrowableAssertion$OCCURRED_EXCEPTION_CAUSE_instance;
  }
  var DescriptionThrowableAssertion$OCCURRED_EXCEPTION_PROPERTIES_instance;
  function DescriptionThrowableAssertion$OCCURRED_EXCEPTION_PROPERTIES_getInstance() {
    DescriptionThrowableAssertion_initFields();
    return DescriptionThrowableAssertion$OCCURRED_EXCEPTION_PROPERTIES_instance;
  }
  var DescriptionThrowableAssertion$OCCURRED_EXCEPTION_MESSAGE_instance;
  function DescriptionThrowableAssertion$OCCURRED_EXCEPTION_MESSAGE_getInstance() {
    DescriptionThrowableAssertion_initFields();
    return DescriptionThrowableAssertion$OCCURRED_EXCEPTION_MESSAGE_instance;
  }
  var DescriptionThrowableAssertion$OCCURRED_EXCEPTION_STACKTRACE_instance;
  function DescriptionThrowableAssertion$OCCURRED_EXCEPTION_STACKTRACE_getInstance() {
    DescriptionThrowableAssertion_initFields();
    return DescriptionThrowableAssertion$OCCURRED_EXCEPTION_STACKTRACE_instance;
  }
  var DescriptionThrowableAssertion$OCCURRED_EXCEPTION_SUPPRESSED_instance;
  function DescriptionThrowableAssertion$OCCURRED_EXCEPTION_SUPPRESSED_getInstance() {
    DescriptionThrowableAssertion_initFields();
    return DescriptionThrowableAssertion$OCCURRED_EXCEPTION_SUPPRESSED_instance;
  }
  var DescriptionThrowableAssertion$IS_NOT_THROWN_1_instance;
  function DescriptionThrowableAssertion$IS_NOT_THROWN_1_getInstance() {
    DescriptionThrowableAssertion_initFields();
    return DescriptionThrowableAssertion$IS_NOT_THROWN_1_instance;
  }
  var DescriptionThrowableAssertion$IS_NOT_THROWN_2_instance;
  function DescriptionThrowableAssertion$IS_NOT_THROWN_2_getInstance() {
    DescriptionThrowableAssertion_initFields();
    return DescriptionThrowableAssertion$IS_NOT_THROWN_2_instance;
  }
  DescriptionThrowableAssertion.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'DescriptionThrowableAssertion',
    interfaces: [StringBasedTranslatable, Enum]
  };
  function DescriptionThrowableAssertion$values() {
    return [DescriptionThrowableAssertion$IS_A_getInstance(), DescriptionThrowableAssertion$NO_EXCEPTION_OCCURRED_getInstance(), DescriptionThrowableAssertion$OCCURRED_EXCEPTION_CAUSE_getInstance(), DescriptionThrowableAssertion$OCCURRED_EXCEPTION_PROPERTIES_getInstance(), DescriptionThrowableAssertion$OCCURRED_EXCEPTION_MESSAGE_getInstance(), DescriptionThrowableAssertion$OCCURRED_EXCEPTION_STACKTRACE_getInstance(), DescriptionThrowableAssertion$OCCURRED_EXCEPTION_SUPPRESSED_getInstance(), DescriptionThrowableAssertion$IS_NOT_THROWN_1_getInstance(), DescriptionThrowableAssertion$IS_NOT_THROWN_2_getInstance()];
  }
  DescriptionThrowableAssertion.values = DescriptionThrowableAssertion$values;
  function DescriptionThrowableAssertion$valueOf(name) {
    switch (name) {
      case 'IS_A':
        return DescriptionThrowableAssertion$IS_A_getInstance();
      case 'NO_EXCEPTION_OCCURRED':
        return DescriptionThrowableAssertion$NO_EXCEPTION_OCCURRED_getInstance();
      case 'OCCURRED_EXCEPTION_CAUSE':
        return DescriptionThrowableAssertion$OCCURRED_EXCEPTION_CAUSE_getInstance();
      case 'OCCURRED_EXCEPTION_PROPERTIES':
        return DescriptionThrowableAssertion$OCCURRED_EXCEPTION_PROPERTIES_getInstance();
      case 'OCCURRED_EXCEPTION_MESSAGE':
        return DescriptionThrowableAssertion$OCCURRED_EXCEPTION_MESSAGE_getInstance();
      case 'OCCURRED_EXCEPTION_STACKTRACE':
        return DescriptionThrowableAssertion$OCCURRED_EXCEPTION_STACKTRACE_getInstance();
      case 'OCCURRED_EXCEPTION_SUPPRESSED':
        return DescriptionThrowableAssertion$OCCURRED_EXCEPTION_SUPPRESSED_getInstance();
      case 'IS_NOT_THROWN_1':
        return DescriptionThrowableAssertion$IS_NOT_THROWN_1_getInstance();
      case 'IS_NOT_THROWN_2':
        return DescriptionThrowableAssertion$IS_NOT_THROWN_2_getInstance();
      default:throwISE('No enum constant ch.tutteli.atrium.translations.DescriptionThrowableAssertion.' + name);
    }
  }
  DescriptionThrowableAssertion.valueOf_61zpoe$ = DescriptionThrowableAssertion$valueOf;
  function DescriptionTypeTransformationAssertion(name, ordinal, value) {
    Enum.call(this);
    this.value_f01zr2$_0 = value;
    this.name$ = name;
    this.ordinal$ = ordinal;
  }
  function DescriptionTypeTransformationAssertion_initFields() {
    DescriptionTypeTransformationAssertion_initFields = function () {
    };
    DescriptionTypeTransformationAssertion$IS_A_instance = new DescriptionTypeTransformationAssertion('IS_A', 0, 'is type or sub-type of');
    DescriptionTypeTransformationAssertion$WARNING_DOWN_CAST_FAILED_instance = new DescriptionTypeTransformationAssertion('WARNING_DOWN_CAST_FAILED', 1, 'Could not evaluate the defined assertion(s) -- the down-cast to %s failed.\nVisit the following site for an explanation: https://docs.atriumlib.org/could-not-evaluate-assertions');
  }
  Object.defineProperty(DescriptionTypeTransformationAssertion.prototype, 'value', {
    get: function () {
      return this.value_f01zr2$_0;
    }
  });
  var DescriptionTypeTransformationAssertion$IS_A_instance;
  function DescriptionTypeTransformationAssertion$IS_A_getInstance() {
    DescriptionTypeTransformationAssertion_initFields();
    return DescriptionTypeTransformationAssertion$IS_A_instance;
  }
  var DescriptionTypeTransformationAssertion$WARNING_DOWN_CAST_FAILED_instance;
  function DescriptionTypeTransformationAssertion$WARNING_DOWN_CAST_FAILED_getInstance() {
    DescriptionTypeTransformationAssertion_initFields();
    return DescriptionTypeTransformationAssertion$WARNING_DOWN_CAST_FAILED_instance;
  }
  DescriptionTypeTransformationAssertion.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'DescriptionTypeTransformationAssertion',
    interfaces: [StringBasedTranslatable, Enum]
  };
  function DescriptionTypeTransformationAssertion$values() {
    return [DescriptionTypeTransformationAssertion$IS_A_getInstance(), DescriptionTypeTransformationAssertion$WARNING_DOWN_CAST_FAILED_getInstance()];
  }
  DescriptionTypeTransformationAssertion.values = DescriptionTypeTransformationAssertion$values;
  function DescriptionTypeTransformationAssertion$valueOf(name) {
    switch (name) {
      case 'IS_A':
        return DescriptionTypeTransformationAssertion$IS_A_getInstance();
      case 'WARNING_DOWN_CAST_FAILED':
        return DescriptionTypeTransformationAssertion$WARNING_DOWN_CAST_FAILED_getInstance();
      default:throwISE('No enum constant ch.tutteli.atrium.translations.DescriptionTypeTransformationAssertion.' + name);
    }
  }
  DescriptionTypeTransformationAssertion.valueOf_61zpoe$ = DescriptionTypeTransformationAssertion$valueOf;
  function ErrorMessages(name, ordinal, value) {
    Enum.call(this);
    this.value_3sl8rd$_0 = value;
    this.name$ = name;
    this.ordinal$ = ordinal;
  }
  function ErrorMessages_initFields() {
    ErrorMessages_initFields = function () {
    };
    ErrorMessages$AT_LEAST_ONE_ASSERTION_DEFINED_instance = new ErrorMessages('AT_LEAST_ONE_ASSERTION_DEFINED', 0, 'at least one assertion defined');
    ErrorMessages$FORGOT_DO_DEFINE_ASSERTION_instance = new ErrorMessages('FORGOT_DO_DEFINE_ASSERTION', 1, 'You forgot to define assertions in the assertionCreator-lambda');
    ErrorMessages$HINT_AT_LEAST_ONE_ASSERTION_DEFINED_instance = new ErrorMessages('HINT_AT_LEAST_ONE_ASSERTION_DEFINED', 2, 'Sometimes you can use an alternative to `{ }` For instance, instead of `toThrow<..> { }` you should use `toThrow<..>()`');
    ErrorMessages$DEDSCRIPTION_BASED_ON_SUBJECT_instance = new ErrorMessages('DEDSCRIPTION_BASED_ON_SUBJECT', 3, 'CANNOT show description as it is based on subject which is not defined');
    ErrorMessages$REPRESENTATION_BASED_ON_SUBJECT_NOT_DEFINED_instance = new ErrorMessages('REPRESENTATION_BASED_ON_SUBJECT_NOT_DEFINED', 4, 'CANNOT evaluate representation as it is based on subject which is not defined.');
    ErrorMessages$SUBJECT_ACCESSED_TOO_EARLY_instance = new ErrorMessages('SUBJECT_ACCESSED_TOO_EARLY', 5, 'Could not evaluate sub-assertions; the subject was accessed too early. Please report a bug at https://github.com/robstoll/atrium/issues/new including stacktrace if possible -- thank you');
  }
  Object.defineProperty(ErrorMessages.prototype, 'value', {
    get: function () {
      return this.value_3sl8rd$_0;
    }
  });
  var ErrorMessages$AT_LEAST_ONE_ASSERTION_DEFINED_instance;
  function ErrorMessages$AT_LEAST_ONE_ASSERTION_DEFINED_getInstance() {
    ErrorMessages_initFields();
    return ErrorMessages$AT_LEAST_ONE_ASSERTION_DEFINED_instance;
  }
  var ErrorMessages$FORGOT_DO_DEFINE_ASSERTION_instance;
  function ErrorMessages$FORGOT_DO_DEFINE_ASSERTION_getInstance() {
    ErrorMessages_initFields();
    return ErrorMessages$FORGOT_DO_DEFINE_ASSERTION_instance;
  }
  var ErrorMessages$HINT_AT_LEAST_ONE_ASSERTION_DEFINED_instance;
  function ErrorMessages$HINT_AT_LEAST_ONE_ASSERTION_DEFINED_getInstance() {
    ErrorMessages_initFields();
    return ErrorMessages$HINT_AT_LEAST_ONE_ASSERTION_DEFINED_instance;
  }
  var ErrorMessages$DEDSCRIPTION_BASED_ON_SUBJECT_instance;
  function ErrorMessages$DEDSCRIPTION_BASED_ON_SUBJECT_getInstance() {
    ErrorMessages_initFields();
    return ErrorMessages$DEDSCRIPTION_BASED_ON_SUBJECT_instance;
  }
  var ErrorMessages$REPRESENTATION_BASED_ON_SUBJECT_NOT_DEFINED_instance;
  function ErrorMessages$REPRESENTATION_BASED_ON_SUBJECT_NOT_DEFINED_getInstance() {
    ErrorMessages_initFields();
    return ErrorMessages$REPRESENTATION_BASED_ON_SUBJECT_NOT_DEFINED_instance;
  }
  var ErrorMessages$SUBJECT_ACCESSED_TOO_EARLY_instance;
  function ErrorMessages$SUBJECT_ACCESSED_TOO_EARLY_getInstance() {
    ErrorMessages_initFields();
    return ErrorMessages$SUBJECT_ACCESSED_TOO_EARLY_instance;
  }
  ErrorMessages.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ErrorMessages',
    interfaces: [StringBasedTranslatable, Enum]
  };
  function ErrorMessages$values() {
    return [ErrorMessages$AT_LEAST_ONE_ASSERTION_DEFINED_getInstance(), ErrorMessages$FORGOT_DO_DEFINE_ASSERTION_getInstance(), ErrorMessages$HINT_AT_LEAST_ONE_ASSERTION_DEFINED_getInstance(), ErrorMessages$DEDSCRIPTION_BASED_ON_SUBJECT_getInstance(), ErrorMessages$REPRESENTATION_BASED_ON_SUBJECT_NOT_DEFINED_getInstance(), ErrorMessages$SUBJECT_ACCESSED_TOO_EARLY_getInstance()];
  }
  ErrorMessages.values = ErrorMessages$values;
  function ErrorMessages$valueOf(name) {
    switch (name) {
      case 'AT_LEAST_ONE_ASSERTION_DEFINED':
        return ErrorMessages$AT_LEAST_ONE_ASSERTION_DEFINED_getInstance();
      case 'FORGOT_DO_DEFINE_ASSERTION':
        return ErrorMessages$FORGOT_DO_DEFINE_ASSERTION_getInstance();
      case 'HINT_AT_LEAST_ONE_ASSERTION_DEFINED':
        return ErrorMessages$HINT_AT_LEAST_ONE_ASSERTION_DEFINED_getInstance();
      case 'DEDSCRIPTION_BASED_ON_SUBJECT':
        return ErrorMessages$DEDSCRIPTION_BASED_ON_SUBJECT_getInstance();
      case 'REPRESENTATION_BASED_ON_SUBJECT_NOT_DEFINED':
        return ErrorMessages$REPRESENTATION_BASED_ON_SUBJECT_NOT_DEFINED_getInstance();
      case 'SUBJECT_ACCESSED_TOO_EARLY':
        return ErrorMessages$SUBJECT_ACCESSED_TOO_EARLY_getInstance();
      default:throwISE('No enum constant ch.tutteli.atrium.translations.ErrorMessages.' + name);
    }
  }
  ErrorMessages.valueOf_61zpoe$ = ErrorMessages$valueOf;
  Object.defineProperty(DescriptionAnyAssertion, 'TO_BE', {
    get: DescriptionAnyAssertion$TO_BE_getInstance
  });
  Object.defineProperty(DescriptionAnyAssertion, 'NOT_TO_BE', {
    get: DescriptionAnyAssertion$NOT_TO_BE_getInstance
  });
  Object.defineProperty(DescriptionAnyAssertion, 'IS_A', {
    get: DescriptionAnyAssertion$IS_A_getInstance
  });
  Object.defineProperty(DescriptionAnyAssertion, 'IS_SAME', {
    get: DescriptionAnyAssertion$IS_SAME_getInstance
  });
  Object.defineProperty(DescriptionAnyAssertion, 'IS_NOT_SAME', {
    get: DescriptionAnyAssertion$IS_NOT_SAME_getInstance
  });
  var package$ch = _.ch || (_.ch = {});
  var package$tutteli = package$ch.tutteli || (package$ch.tutteli = {});
  var package$atrium = package$tutteli.atrium || (package$tutteli.atrium = {});
  var package$translations = package$atrium.translations || (package$atrium.translations = {});
  package$translations.DescriptionAnyAssertion = DescriptionAnyAssertion;
  Object.defineProperty(DescriptionBasic, 'TO', {
    get: DescriptionBasic$TO_getInstance
  });
  Object.defineProperty(DescriptionBasic, 'NOT_TO', {
    get: DescriptionBasic$NOT_TO_getInstance
  });
  Object.defineProperty(DescriptionBasic, 'TO_BE', {
    get: DescriptionBasic$TO_BE_getInstance
  });
  Object.defineProperty(DescriptionBasic, 'NOT_TO_BE', {
    get: DescriptionBasic$NOT_TO_BE_getInstance
  });
  Object.defineProperty(DescriptionBasic, 'IS', {
    get: DescriptionBasic$IS_getInstance
  });
  Object.defineProperty(DescriptionBasic, 'IS_NOT', {
    get: DescriptionBasic$IS_NOT_getInstance
  });
  Object.defineProperty(DescriptionBasic, 'HAS', {
    get: DescriptionBasic$HAS_getInstance
  });
  Object.defineProperty(DescriptionBasic, 'HAS_NOT', {
    get: DescriptionBasic$HAS_NOT_getInstance
  });
  Object.defineProperty(DescriptionBasic, 'WAS', {
    get: DescriptionBasic$WAS_getInstance
  });
  Object.defineProperty(DescriptionBasic, 'NONE', {
    get: DescriptionBasic$NONE_getInstance
  });
  package$translations.DescriptionBasic = DescriptionBasic;
  Object.defineProperty(DescriptionCharSequenceAssertion, 'AT_LEAST', {
    get: DescriptionCharSequenceAssertion$AT_LEAST_getInstance
  });
  Object.defineProperty(DescriptionCharSequenceAssertion, 'AT_MOST', {
    get: DescriptionCharSequenceAssertion$AT_MOST_getInstance
  });
  Object.defineProperty(DescriptionCharSequenceAssertion, 'BLANK', {
    get: DescriptionCharSequenceAssertion$BLANK_getInstance
  });
  Object.defineProperty(DescriptionCharSequenceAssertion, 'CONTAINS', {
    get: DescriptionCharSequenceAssertion$CONTAINS_getInstance
  });
  Object.defineProperty(DescriptionCharSequenceAssertion, 'CONTAINS_NOT', {
    get: DescriptionCharSequenceAssertion$CONTAINS_NOT_getInstance
  });
  Object.defineProperty(DescriptionCharSequenceAssertion, 'EMPTY', {
    get: DescriptionCharSequenceAssertion$EMPTY_getInstance
  });
  Object.defineProperty(DescriptionCharSequenceAssertion, 'ENDS_WITH', {
    get: DescriptionCharSequenceAssertion$ENDS_WITH_getInstance
  });
  Object.defineProperty(DescriptionCharSequenceAssertion, 'ENDS_NOT_WITH', {
    get: DescriptionCharSequenceAssertion$ENDS_NOT_WITH_getInstance
  });
  Object.defineProperty(DescriptionCharSequenceAssertion, 'EXACTLY', {
    get: DescriptionCharSequenceAssertion$EXACTLY_getInstance
  });
  Object.defineProperty(DescriptionCharSequenceAssertion, 'IGNORING_CASE', {
    get: DescriptionCharSequenceAssertion$IGNORING_CASE_getInstance
  });
  Object.defineProperty(DescriptionCharSequenceAssertion, 'MATCHES', {
    get: DescriptionCharSequenceAssertion$MATCHES_getInstance
  });
  Object.defineProperty(DescriptionCharSequenceAssertion, 'MISMATCHES', {
    get: DescriptionCharSequenceAssertion$MISMATCHES_getInstance
  });
  Object.defineProperty(DescriptionCharSequenceAssertion, 'NUMBER_OF_OCCURRENCES', {
    get: DescriptionCharSequenceAssertion$NUMBER_OF_OCCURRENCES_getInstance
  });
  Object.defineProperty(DescriptionCharSequenceAssertion, 'STARTS_WITH', {
    get: DescriptionCharSequenceAssertion$STARTS_WITH_getInstance
  });
  Object.defineProperty(DescriptionCharSequenceAssertion, 'STARTS_NOT_WITH', {
    get: DescriptionCharSequenceAssertion$STARTS_NOT_WITH_getInstance
  });
  Object.defineProperty(DescriptionCharSequenceAssertion, 'STRING_MATCHING_REGEX', {
    get: DescriptionCharSequenceAssertion$STRING_MATCHING_REGEX_getInstance
  });
  Object.defineProperty(DescriptionCharSequenceAssertion, 'VALUE', {
    get: DescriptionCharSequenceAssertion$VALUE_getInstance
  });
  package$translations.DescriptionCharSequenceAssertion = DescriptionCharSequenceAssertion;
  Object.defineProperty(DescriptionCollectionAssertion, 'EMPTY', {
    get: DescriptionCollectionAssertion$EMPTY_getInstance
  });
  Object.defineProperty(DescriptionCollectionAssertion, 'SIZE', {
    get: DescriptionCollectionAssertion$SIZE_getInstance
  });
  package$translations.DescriptionCollectionAssertion = DescriptionCollectionAssertion;
  Object.defineProperty(DescriptionComparableAssertion, 'IS_LESS_THAN', {
    get: DescriptionComparableAssertion$IS_LESS_THAN_getInstance
  });
  Object.defineProperty(DescriptionComparableAssertion, 'IS_LESS_OR_EQUALS', {
    get: DescriptionComparableAssertion$IS_LESS_OR_EQUALS_getInstance
  });
  Object.defineProperty(DescriptionComparableAssertion, 'IS_GREATER_THAN', {
    get: DescriptionComparableAssertion$IS_GREATER_THAN_getInstance
  });
  Object.defineProperty(DescriptionComparableAssertion, 'IS_GREATER_OR_EQUALS', {
    get: DescriptionComparableAssertion$IS_GREATER_OR_EQUALS_getInstance
  });
  package$translations.DescriptionComparableAssertion = DescriptionComparableAssertion;
  Object.defineProperty(DescriptionDateTimeLikeAssertion, 'YEAR', {
    get: DescriptionDateTimeLikeAssertion$YEAR_getInstance
  });
  Object.defineProperty(DescriptionDateTimeLikeAssertion, 'MONTH', {
    get: DescriptionDateTimeLikeAssertion$MONTH_getInstance
  });
  Object.defineProperty(DescriptionDateTimeLikeAssertion, 'DAY_OF_WEEK', {
    get: DescriptionDateTimeLikeAssertion$DAY_OF_WEEK_getInstance
  });
  package$translations.DescriptionDateTimeLikeAssertion = DescriptionDateTimeLikeAssertion;
  Object.defineProperty(DescriptionFloatingPointAssertion, 'TO_BE_WITH_ERROR_TOLERANCE', {
    get: DescriptionFloatingPointAssertion$TO_BE_WITH_ERROR_TOLERANCE_getInstance
  });
  Object.defineProperty(DescriptionFloatingPointAssertion, 'FAILURE_DUE_TO_FLOATING_POINT_NUMBER', {
    get: DescriptionFloatingPointAssertion$FAILURE_DUE_TO_FLOATING_POINT_NUMBER_getInstance
  });
  Object.defineProperty(DescriptionFloatingPointAssertion, 'TO_BE_WITH_ERROR_TOLERANCE_EXPLAINED', {
    get: DescriptionFloatingPointAssertion$TO_BE_WITH_ERROR_TOLERANCE_EXPLAINED_getInstance
  });
  package$translations.DescriptionFloatingPointAssertion = DescriptionFloatingPointAssertion;
  Object.defineProperty(DescriptionIterableAssertion, 'ALL', {
    get: DescriptionIterableAssertion$ALL_getInstance
  });
  Object.defineProperty(DescriptionIterableAssertion, 'AN_ENTRY_WHICH', {
    get: DescriptionIterableAssertion$AN_ENTRY_WHICH_getInstance
  });
  Object.defineProperty(DescriptionIterableAssertion, 'AN_ENTRY_WHICH_IS', {
    get: DescriptionIterableAssertion$AN_ENTRY_WHICH_IS_getInstance
  });
  Object.defineProperty(DescriptionIterableAssertion, 'AT_LEAST', {
    get: DescriptionIterableAssertion$AT_LEAST_getInstance
  });
  Object.defineProperty(DescriptionIterableAssertion, 'AT_MOST', {
    get: DescriptionIterableAssertion$AT_MOST_getInstance
  });
  Object.defineProperty(DescriptionIterableAssertion, 'CONTAINS', {
    get: DescriptionIterableAssertion$CONTAINS_getInstance
  });
  Object.defineProperty(DescriptionIterableAssertion, 'CONTAINS_NOT', {
    get: DescriptionIterableAssertion$CONTAINS_NOT_getInstance
  });
  Object.defineProperty(DescriptionIterableAssertion, 'ENTRY_WITH_INDEX', {
    get: DescriptionIterableAssertion$ENTRY_WITH_INDEX_getInstance
  });
  Object.defineProperty(DescriptionIterableAssertion, 'EXACTLY', {
    get: DescriptionIterableAssertion$EXACTLY_getInstance
  });
  Object.defineProperty(DescriptionIterableAssertion, 'HAS_ELEMENT', {
    get: DescriptionIterableAssertion$HAS_ELEMENT_getInstance
  });
  Object.defineProperty(DescriptionIterableAssertion, 'IN_ANY_ORDER', {
    get: DescriptionIterableAssertion$IN_ANY_ORDER_getInstance
  });
  Object.defineProperty(DescriptionIterableAssertion, 'IN_ANY_ORDER_ONLY', {
    get: DescriptionIterableAssertion$IN_ANY_ORDER_ONLY_getInstance
  });
  Object.defineProperty(DescriptionIterableAssertion, 'IN_ORDER', {
    get: DescriptionIterableAssertion$IN_ORDER_getInstance
  });
  Object.defineProperty(DescriptionIterableAssertion, 'IN_ORDER_ONLY', {
    get: DescriptionIterableAssertion$IN_ORDER_ONLY_getInstance
  });
  Object.defineProperty(DescriptionIterableAssertion, 'IN_ORDER_ONLY_GROUPED', {
    get: DescriptionIterableAssertion$IN_ORDER_ONLY_GROUPED_getInstance
  });
  Object.defineProperty(DescriptionIterableAssertion, 'INDEX', {
    get: DescriptionIterableAssertion$INDEX_getInstance
  });
  Object.defineProperty(DescriptionIterableAssertion, 'INDEX_FROM_TO', {
    get: DescriptionIterableAssertion$INDEX_FROM_TO_getInstance
  });
  Object.defineProperty(DescriptionIterableAssertion, 'NUMBER_OF_OCCURRENCES', {
    get: DescriptionIterableAssertion$NUMBER_OF_OCCURRENCES_getInstance
  });
  Object.defineProperty(DescriptionIterableAssertion, 'SIZE_EXCEEDED', {
    get: DescriptionIterableAssertion$SIZE_EXCEEDED_getInstance
  });
  Object.defineProperty(DescriptionIterableAssertion, 'CANNOT_EVALUATE_SUBJECT_EMPTY_ITERABLE', {
    get: DescriptionIterableAssertion$CANNOT_EVALUATE_SUBJECT_EMPTY_ITERABLE_getInstance
  });
  Object.defineProperty(DescriptionIterableAssertion, 'CANNOT_EVALUATE_SUBJECT_ONLY_NULL', {
    get: DescriptionIterableAssertion$CANNOT_EVALUATE_SUBJECT_ONLY_NULL_getInstance
  });
  Object.defineProperty(DescriptionIterableAssertion, 'WARNING_ADDITIONAL_ENTRIES', {
    get: DescriptionIterableAssertion$WARNING_ADDITIONAL_ENTRIES_getInstance
  });
  Object.defineProperty(DescriptionIterableAssertion, 'WARNING_MISMATCHES', {
    get: DescriptionIterableAssertion$WARNING_MISMATCHES_getInstance
  });
  Object.defineProperty(DescriptionIterableAssertion, 'WARNING_MISMATCHES_ADDITIONAL_ENTRIES', {
    get: DescriptionIterableAssertion$WARNING_MISMATCHES_ADDITIONAL_ENTRIES_getInstance
  });
  Object.defineProperty(DescriptionIterableAssertion, 'NEXT_ELEMENT', {
    get: DescriptionIterableAssertion$NEXT_ELEMENT_getInstance
  });
  Object.defineProperty(DescriptionIterableAssertion, 'NO_ELEMENTS', {
    get: DescriptionIterableAssertion$NO_ELEMENTS_getInstance
  });
  package$translations.DescriptionIterableAssertion = DescriptionIterableAssertion;
  Object.defineProperty(package$translations, 'COULD_NOT_EVALUATE_DEFINED_ASSERTIONS_8be2vx$', {
    get: function () {
      return COULD_NOT_EVALUATE_DEFINED_ASSERTIONS;
    }
  });
  Object.defineProperty(package$translations, 'VISIT_COULD_NOT_EVALUATE_ASSERTIONS_8be2vx$', {
    get: function () {
      return VISIT_COULD_NOT_EVALUATE_ASSERTIONS;
    }
  });
  Object.defineProperty(DescriptionListAssertion, 'CANNOT_EVALUATE_INDEX_OUT_OF_BOUNDS', {
    get: DescriptionListAssertion$CANNOT_EVALUATE_INDEX_OUT_OF_BOUNDS_getInstance
  });
  Object.defineProperty(DescriptionListAssertion, 'INDEX_OUT_OF_BOUNDS', {
    get: DescriptionListAssertion$INDEX_OUT_OF_BOUNDS_getInstance
  });
  package$translations.DescriptionListAssertion = DescriptionListAssertion;
  Object.defineProperty(DescriptionMapAssertion, 'CANNOT_EVALUATE_KEY_DOES_NOT_EXIST', {
    get: DescriptionMapAssertion$CANNOT_EVALUATE_KEY_DOES_NOT_EXIST_getInstance
  });
  Object.defineProperty(DescriptionMapAssertion, 'CONTAINS_IN_ANY_ORDER', {
    get: DescriptionMapAssertion$CONTAINS_IN_ANY_ORDER_getInstance
  });
  Object.defineProperty(DescriptionMapAssertion, 'CONTAINS_KEY', {
    get: DescriptionMapAssertion$CONTAINS_KEY_getInstance
  });
  Object.defineProperty(DescriptionMapAssertion, 'CONTAINS_NOT_KEY', {
    get: DescriptionMapAssertion$CONTAINS_NOT_KEY_getInstance
  });
  Object.defineProperty(DescriptionMapAssertion, 'ENTRY_WITH_KEY', {
    get: DescriptionMapAssertion$ENTRY_WITH_KEY_getInstance
  });
  Object.defineProperty(DescriptionMapAssertion, 'KEY_DOES_NOT_EXIST', {
    get: DescriptionMapAssertion$KEY_DOES_NOT_EXIST_getInstance
  });
  Object.defineProperty(DescriptionMapAssertion, 'SIZE', {
    get: DescriptionMapAssertion$SIZE_getInstance
  });
  package$translations.DescriptionMapAssertion = DescriptionMapAssertion;
  Object.defineProperty(DescriptionThrowableAssertion, 'IS_A', {
    get: DescriptionThrowableAssertion$IS_A_getInstance
  });
  Object.defineProperty(DescriptionThrowableAssertion, 'NO_EXCEPTION_OCCURRED', {
    get: DescriptionThrowableAssertion$NO_EXCEPTION_OCCURRED_getInstance
  });
  Object.defineProperty(DescriptionThrowableAssertion, 'OCCURRED_EXCEPTION_CAUSE', {
    get: DescriptionThrowableAssertion$OCCURRED_EXCEPTION_CAUSE_getInstance
  });
  Object.defineProperty(DescriptionThrowableAssertion, 'OCCURRED_EXCEPTION_PROPERTIES', {
    get: DescriptionThrowableAssertion$OCCURRED_EXCEPTION_PROPERTIES_getInstance
  });
  Object.defineProperty(DescriptionThrowableAssertion, 'OCCURRED_EXCEPTION_MESSAGE', {
    get: DescriptionThrowableAssertion$OCCURRED_EXCEPTION_MESSAGE_getInstance
  });
  Object.defineProperty(DescriptionThrowableAssertion, 'OCCURRED_EXCEPTION_STACKTRACE', {
    get: DescriptionThrowableAssertion$OCCURRED_EXCEPTION_STACKTRACE_getInstance
  });
  Object.defineProperty(DescriptionThrowableAssertion, 'OCCURRED_EXCEPTION_SUPPRESSED', {
    get: DescriptionThrowableAssertion$OCCURRED_EXCEPTION_SUPPRESSED_getInstance
  });
  Object.defineProperty(DescriptionThrowableAssertion, 'IS_NOT_THROWN_1', {
    get: DescriptionThrowableAssertion$IS_NOT_THROWN_1_getInstance
  });
  Object.defineProperty(DescriptionThrowableAssertion, 'IS_NOT_THROWN_2', {
    get: DescriptionThrowableAssertion$IS_NOT_THROWN_2_getInstance
  });
  package$translations.DescriptionThrowableAssertion = DescriptionThrowableAssertion;
  Object.defineProperty(DescriptionTypeTransformationAssertion, 'IS_A', {
    get: DescriptionTypeTransformationAssertion$IS_A_getInstance
  });
  Object.defineProperty(DescriptionTypeTransformationAssertion, 'WARNING_DOWN_CAST_FAILED', {
    get: DescriptionTypeTransformationAssertion$WARNING_DOWN_CAST_FAILED_getInstance
  });
  package$translations.DescriptionTypeTransformationAssertion = DescriptionTypeTransformationAssertion;
  Object.defineProperty(ErrorMessages, 'AT_LEAST_ONE_ASSERTION_DEFINED', {
    get: ErrorMessages$AT_LEAST_ONE_ASSERTION_DEFINED_getInstance
  });
  Object.defineProperty(ErrorMessages, 'FORGOT_DO_DEFINE_ASSERTION', {
    get: ErrorMessages$FORGOT_DO_DEFINE_ASSERTION_getInstance
  });
  Object.defineProperty(ErrorMessages, 'HINT_AT_LEAST_ONE_ASSERTION_DEFINED', {
    get: ErrorMessages$HINT_AT_LEAST_ONE_ASSERTION_DEFINED_getInstance
  });
  Object.defineProperty(ErrorMessages, 'DEDSCRIPTION_BASED_ON_SUBJECT', {
    get: ErrorMessages$DEDSCRIPTION_BASED_ON_SUBJECT_getInstance
  });
  Object.defineProperty(ErrorMessages, 'REPRESENTATION_BASED_ON_SUBJECT_NOT_DEFINED', {
    get: ErrorMessages$REPRESENTATION_BASED_ON_SUBJECT_NOT_DEFINED_getInstance
  });
  Object.defineProperty(ErrorMessages, 'SUBJECT_ACCESSED_TOO_EARLY', {
    get: ErrorMessages$SUBJECT_ACCESSED_TOO_EARLY_getInstance
  });
  package$translations.ErrorMessages = ErrorMessages;
  Object.defineProperty(DescriptionAnyAssertion.prototype, 'id', Object.getOwnPropertyDescriptor(StringBasedTranslatable.prototype, 'id'));
  DescriptionAnyAssertion.prototype.getDefault = StringBasedTranslatable.prototype.getDefault;
  Object.defineProperty(DescriptionBasic.prototype, 'id', Object.getOwnPropertyDescriptor(StringBasedTranslatable.prototype, 'id'));
  DescriptionBasic.prototype.getDefault = StringBasedTranslatable.prototype.getDefault;
  Object.defineProperty(DescriptionCharSequenceAssertion.prototype, 'id', Object.getOwnPropertyDescriptor(StringBasedTranslatable.prototype, 'id'));
  DescriptionCharSequenceAssertion.prototype.getDefault = StringBasedTranslatable.prototype.getDefault;
  Object.defineProperty(DescriptionCollectionAssertion.prototype, 'id', Object.getOwnPropertyDescriptor(StringBasedTranslatable.prototype, 'id'));
  DescriptionCollectionAssertion.prototype.getDefault = StringBasedTranslatable.prototype.getDefault;
  Object.defineProperty(DescriptionComparableAssertion.prototype, 'id', Object.getOwnPropertyDescriptor(StringBasedTranslatable.prototype, 'id'));
  DescriptionComparableAssertion.prototype.getDefault = StringBasedTranslatable.prototype.getDefault;
  Object.defineProperty(DescriptionDateTimeLikeAssertion.prototype, 'id', Object.getOwnPropertyDescriptor(StringBasedTranslatable.prototype, 'id'));
  DescriptionDateTimeLikeAssertion.prototype.getDefault = StringBasedTranslatable.prototype.getDefault;
  Object.defineProperty(DescriptionFloatingPointAssertion.prototype, 'id', Object.getOwnPropertyDescriptor(StringBasedTranslatable.prototype, 'id'));
  DescriptionFloatingPointAssertion.prototype.getDefault = StringBasedTranslatable.prototype.getDefault;
  Object.defineProperty(DescriptionIterableAssertion.prototype, 'id', Object.getOwnPropertyDescriptor(StringBasedTranslatable.prototype, 'id'));
  DescriptionIterableAssertion.prototype.getDefault = StringBasedTranslatable.prototype.getDefault;
  Object.defineProperty(DescriptionListAssertion.prototype, 'id', Object.getOwnPropertyDescriptor(StringBasedTranslatable.prototype, 'id'));
  DescriptionListAssertion.prototype.getDefault = StringBasedTranslatable.prototype.getDefault;
  Object.defineProperty(DescriptionMapAssertion.prototype, 'id', Object.getOwnPropertyDescriptor(StringBasedTranslatable.prototype, 'id'));
  DescriptionMapAssertion.prototype.getDefault = StringBasedTranslatable.prototype.getDefault;
  Object.defineProperty(DescriptionThrowableAssertion.prototype, 'id', Object.getOwnPropertyDescriptor(StringBasedTranslatable.prototype, 'id'));
  DescriptionThrowableAssertion.prototype.getDefault = StringBasedTranslatable.prototype.getDefault;
  Object.defineProperty(DescriptionTypeTransformationAssertion.prototype, 'id', Object.getOwnPropertyDescriptor(StringBasedTranslatable.prototype, 'id'));
  DescriptionTypeTransformationAssertion.prototype.getDefault = StringBasedTranslatable.prototype.getDefault;
  Object.defineProperty(ErrorMessages.prototype, 'id', Object.getOwnPropertyDescriptor(StringBasedTranslatable.prototype, 'id'));
  ErrorMessages.prototype.getDefault = StringBasedTranslatable.prototype.getDefault;
  COULD_NOT_EVALUATE_DEFINED_ASSERTIONS = 'Could not evaluate the defined assertion(s)';
  VISIT_COULD_NOT_EVALUATE_ASSERTIONS = 'Visit the following site for an explanation: https://docs.atriumlib.org/could-not-evaluate-assertions';
  Kotlin.defineModule('atrium-translations-js', _);
  return _;
}));

//# sourceMappingURL=atrium-translations-js.js.map
