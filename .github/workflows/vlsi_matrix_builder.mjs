//
//    __                          __
//   / /____ ___ ____  ___  ___ _/ /       This file is provided to you by https://github.com/tegonal/github-commons
//  / __/ -_) _ `/ _ \/ _ \/ _ `/ /        Copyright 2022 Tegonal Genossenschaft <info@tegonal.com>
//  \__/\__/\_, /\___/_//_/\_,_/_/         It is licensed under Apache License 2.0
//         /___/                           Please report bugs and contribute back your improvements
//
//                                         Version: v6.0.0
//##################################
// It's kind of a fat-file, including everyhting from
// https://github.com/vlsi/github-actions-random-matrix/blob/main/src
// so that no install via npm is necessary
// those files are licensed under Apache License 2.0 as well
//##################################
// -----------------------------------------------------------------------------------------------------------------
// https://github.com/vlsi/github-actions-random-matrix/blob/main/src/github_matrix_builder.mjs
// BEGIN INSERTED github_matrix_builder.mjs
// License: Apache-2.0
// Copyright Vladimir Sitnikov, 2021
// See https://github.com/vlsi/github-actions-random-matrix
import { appendFileSync } from 'fs';
import { EOL } from 'os';
import { randomBytes } from 'crypto';
import { createRequire } from 'module';
//import { MatrixBuilder, Axis } from './matrix_builder.mjs';

const require = createRequire(import.meta.url);
//const seedrandom = require('./seedrandom.cjs');

//export { MatrixBuilder, Axis };

function defaultSeedText() {
  const { RNG_SEED } = process.env;
  if (RNG_SEED) {
    return RNG_SEED;
  }
  const { GITHUB_PR_NUMBER } = process.env;
  if (GITHUB_PR_NUMBER) {
    return 'pr_' + GITHUB_PR_NUMBER;
  }
  return 'seed_' + Date.now() + '_' + randomBytes(16).toString('hex');
}

/**
 * Creates a MatrixBuilder pre-configured with a seedable RNG suitable for GitHub Actions.
 *
 * The seed is determined by (in priority order):
 * 1. The `seed` option passed to this function
 * 2. The RNG_SEED environment variable
 * 3. The GITHUB_PR_NUMBER environment variable (prefixed with 'pr_')
 * 4. A random seed based on current time and crypto random bytes
 *
 * The seed is logged to the GitHub Actions group log and step summary
 * for reproducibility.
 *
 * @param {object} [options]
 * @param {string} [options.seed] explicit seed value
 * @returns {{matrix: MatrixBuilder, random: function}} matrix builder and the random function
 */
export function createGitHubMatrixBuilder({seed} = {}) {
  const seedText = seed || defaultSeedText();
  const rng = new seedrandom(seedText);
  const random = () => rng();

  console.log('::group::RNG Seed');
  console.log('Initialized RNG with RNG_SEED = %s', seedText);
  console.log('::endgroup::');
  if (process.env.GITHUB_STEP_SUMMARY) {
    appendFileSync(process.env.GITHUB_STEP_SUMMARY, [
      '# Random Number Generator Seed',
      'To regenerate this matrix in a different build, run it with the following seed:',
      '',
      '    ' + seedText,
      '',
    ].join('\n'));
  }

  return {
    matrix: new MatrixBuilder({random}),
    random,
  };
}

/**
 * Writes a GitHub Actions step output, e.g. the generated matrix for a later job.
 *
 * The value is wrapped in a heredoc with a random delimiter, so a multiline or
 * untrusted value cannot break the file format or inject extra outputs. Objects are
 * JSON-encoded; strings are written verbatim.
 *
 * @param {string} name output name, e.g. 'matrix'
 * @param {*} value string, or any JSON-serializable value
 * @param {object} [options]
 * @param {string} [options.output] target file, defaults to the GITHUB_OUTPUT env var
 * @returns {boolean} true if written, false when no output target is set (e.g. running locally)
 */
export function setGitHubOutput(name, value, {output = process.env.GITHUB_OUTPUT} = {}) {
  if (!output) {
    return false;
  }
  const body = typeof value === 'string' ? value : JSON.stringify(value);
  const delimiter = 'ghadelimiter_' + randomBytes(16).toString('hex');
  appendFileSync(output, `${name}<<${delimiter}${EOL}${body}${EOL}${delimiter}${EOL}`, {
    encoding: 'utf8',
  });
  return true;
}

// END INSERTED github_matrix_builder.mjs
// -----------------------------------------------------------------------------------------------------------------


// -----------------------------------------------------------------------------------------------------------------
// https://github.com/vlsi/github-actions-random-matrix/blob/main/src/matrix_builder.mjs
// BEGIN INSERTED matrix_builder.mjs
// License: Apache-2.0
// Copyright Vladimir Sitnikov, 2021
// See https://github.com/vlsi/github-actions-random-matrix

function pairKey(ai, vi, aj, vj) {
  return `${ai}:${vi}|${aj}:${vj}`;
}

class Axis {
  constructor({name, title, values}) {
    this.name = name;
    this.title = title;
    this.values = values;
    // Precompute normalized weights for pair scoring.
    // Each value's weight is normalized so that the axis weights sum to 1.
    const totalWeight = values.reduce((a, b) => a + (b.weight || 1), 0);
    this.normalizedWeights = values.map(v => (v.weight || 1) / totalWeight);
    // Map from value reference to its index for O(1) lookup
    this.valueIndex = new Map(values.map((v, i) => [v, i]));
  }

  static matches(row, filter) {
    if (typeof filter === 'function') {
      return filter(row);
    }
    if (Array.isArray(filter)) {
      // e.g. row={os: 'windows'}; filter=[{os: 'linux'}, {os: 'linux'}]
      return filter.some(v => Axis.matches(row, v));
    }
    if (typeof filter === 'object') {
      // e.g. row={jdk: {name: 'openjdk', version: 8}}; filter={jdk: {version: 8}}
      for (const [key, value] of Object.entries(filter)) {
        if (!row.hasOwnProperty(key) || !Axis.matches(row[key], value)) {
          return false;
        }
      }
      return true;
    }
    return row === filter;
  }

  pickValue(filter, random = Math.random) {
    let values = this.values;
    if (filter) {
      values = values.filter(v => Axis.matches(v, filter));
    }
    if (values.length === 0) {
      const filterStr = typeof filter === 'string' ? filter.toString() : JSON.stringify(filter);
      throw Error(`No values produced for axis '${this.name}' from ${JSON.stringify(this.values)}, filter=${filterStr}`);
    }
    return values[Math.floor(random() * values.length)];
  }
}

class MatrixBuilder {
  /**
   * @param {object} [options]
   * @param {function} [options.random] random number generator returning [0, 1), defaults to Math.random
   */
  constructor({random = Math.random} = {}) {
    this._random = random;
    this.axes = [];
    this.axisByName = {};
    this.rows = [];
    this.duplicates = {};
    this.constraints = [];
    this._failOnUnsatisfiableFilters = false;
    this._pairsInitialized = false;
    this._uncoveredPairs = null;
    this._totalPairs = 0;
    this._totalPairsWeight = 0;
    this._uncoveredPairsWeight = 0;
  }

  /**
   * Adds a constraint on a set of axes.
   * The predicate receives axis values in the same order as axisNames
   * and returns true if the combination is allowed.
   * @param {string[]} axisNames
   * @param {function} predicate
   */
  constrain(axisNames, predicate) {
    this.constraints.push({axisNames, predicate});
  }

  /**
   * Specifies exclude filter (e.g. exclude a forbidden combination).
   * The filter must be an object with keys matching axis names.
   * @param filter object filter (functions are not supported, use constrain() instead)
   */
  exclude(filter) {
    if (typeof filter === 'function') {
      throw new Error('Function excludes are not supported, use constrain() instead');
    }
    const axisNames = Object.keys(filter);
    this.constrain(axisNames, (...values) => {
      const partial = Object.fromEntries(axisNames.map((name, i) => [name, values[i]]));
      return !Axis.matches(partial, filter);
    });
  }

  /**
   * Adds implication like `antecedent -> consequent`.
   * In other words, if `antecedent` holds, then `consequent` must also hold.
   * @param antecedent object filter
   * @param consequent object filter
   */
  imply(antecedent, consequent) {
    const axisNames = [...new Set([
      ...Object.keys(antecedent),
      ...Object.keys(consequent),
    ])];
    this.constrain(axisNames, (...values) => {
      const partial = Object.fromEntries(axisNames.map((name, i) => [name, values[i]]));
      return !Axis.matches(partial, antecedent)
          || Axis.matches(partial, consequent);
    });
  }

  addAxis({name, title, values}) {
    const axis = new Axis({name, title, values});
    this.axes.push(axis);
    this.axisByName[name] = axis;
    return axis;
  }

  setNamePattern(names) {
    this.namePattern = names;
  }

  /**
   * Returns true if the row satisfies all constraints.
   * @param row input row
   * @returns {boolean}
   */
  matches(row) {
    return this.constraints.every(({axisNames, predicate}) => {
      const values = axisNames.map(name => row[name]);
      return predicate(...values);
    });
  }

  failOnUnsatisfiableFilters(value) {
    this._failOnUnsatisfiableFilters = value;
  }

  /**
   * Returns constraints whose scope is a subset of the given axis names.
   * These constraints can be fully evaluated on a partial row containing only those axes.
   */
  _constraintsForAxes(axisNameSet) {
    return this.constraints.filter(({axisNames}) =>
      axisNames.every(name => axisNameSet.has(name))
    );
  }

  /**
   * Checks if a partial row (subset of axes) satisfies all constraints
   * whose scope is within the given axes.
   */
  _checkPartial(partial, relevantConstraints) {
    return relevantConstraints.every(({axisNames, predicate}) => {
      const values = axisNames.map(name => partial[name]);
      return predicate(...values);
    });
  }

  /**
   * Initializes the set of all feasible value pairs to cover.
   * Pairs that violate constraints are excluded upfront.
   * Called lazily on first generateRow call (after all axes and constraints are configured).
   */
  _initPairs() {
    if (this._pairsInitialized) return;
    this._pairsInitialized = true;
    this._uncoveredPairs = new Set();
    let totalWeight = 0;
    for (let i = 0; i < this.axes.length; i++) {
      for (let j = i + 1; j < this.axes.length; j++) {
        const axisNameSet = new Set([this.axes[i].name, this.axes[j].name]);
        const relevant = this._constraintsForAxes(axisNameSet);
        for (let vi = 0; vi < this.axes[i].values.length; vi++) {
          const wi = this.axes[i].normalizedWeights[vi];
          for (let vj = 0; vj < this.axes[j].values.length; vj++) {
            const partial = {
              [this.axes[i].name]: this.axes[i].values[vi],
              [this.axes[j].name]: this.axes[j].values[vj],
            };
            if (!this._checkPartial(partial, relevant)) continue;
            this._uncoveredPairs.add(pairKey(i, vi, j, vj));
            totalWeight += wi * this.axes[j].normalizedWeights[vj];
          }
        }
      }
    }
    this._totalPairs = this._uncoveredPairs.size;
    this._totalPairsWeight = totalWeight;
    this._uncoveredPairsWeight = totalWeight;
  }

  /**
   * Scores a candidate row by the weighted sum of uncovered pairs it would cover.
   * Each pair's contribution is normalizedWeight_i * normalizedWeight_j,
   * so axes with different weight scales contribute fairly.
   */
  _scoreNewPairs(row) {
    let score = 0;
    for (let i = 0; i < this.axes.length; i++) {
      const axisI = this.axes[i];
      const vi = axisI.valueIndex.get(row[axisI.name]);
      const wi = axisI.normalizedWeights[vi];
      for (let j = i + 1; j < this.axes.length; j++) {
        const axisJ = this.axes[j];
        const vj = axisJ.valueIndex.get(row[axisJ.name]);
        if (this._uncoveredPairs.has(pairKey(i, vi, j, vj))) {
          score += wi * axisJ.normalizedWeights[vj];
        }
      }
    }
    return score;
  }

  /**
   * Marks all pairs in a row as covered.
   */
  _markCovered(row) {
    let weight = 0;
    for (let i = 0; i < this.axes.length; i++) {
      const vi = this.axes[i].valueIndex.get(row[this.axes[i].name]);
      const wi = this.axes[i].normalizedWeights[vi];
      for (let j = i + 1; j < this.axes.length; j++) {
        const vj = this.axes[j].valueIndex.get(row[this.axes[j].name]);
        if (this._uncoveredPairs.delete(pairKey(i, vi, j, vj))) {
          weight += wi * this.axes[j].normalizedWeights[vj];
        }
      }
    }
    this._uncoveredPairsWeight -= weight;
  }

  /**
   * Generates a single valid candidate row matching the optional filter.
   * Returns null if no valid candidate can be produced after several attempts.
   */
  _generateCandidate(filter) {
    for (let attempt = 0; attempt < 20; attempt++) {
      const row = this.axes.reduce(
        (prev, next) =>
          Object.assign(prev, {
            [next.name]: next.pickValue(filter ? filter[next.name] : undefined, this._random)
          }),
        {}
      );
      if (this.matches(row)) {
        return row;
      }
    }
    return null;
  }

  /**
   * Computes the display name for a row based on the name pattern.
   */
  _computeName(row) {
    return this.namePattern.map(axisName => {
      let value = row[axisName];
      const title = value.title;
      if (typeof title != 'undefined') {
        return title;
      }
      const computeTitle = this.axisByName[axisName].title;
      if (computeTitle) {
        return computeTitle(value);
      }
      if (typeof value === 'object' && value.hasOwnProperty('value')) {
        return value.value;
      }
      return value;
    }).filter(Boolean).join(", ");
  }

  /**
   * Adds a row that matches the given filter to the resulting matrix.
   * Among many random candidates satisfying the filter, picks the one
   * that covers the most previously-uncovered parameter pairs.
   *
   * filter values could be
   *  - literal values: filter={os: 'windows-latest'}
   *  - arrays: filter={os: ['windows-latest', 'linux-latest']}
   *  - functions: filter={os: x => x!='windows-latest'}
   * @param filter object with keys matching axes names
   * @returns {*}
   */
  /**
   * Generates many random candidates matching `filter`, scores each by the
   * weighted number of new pairs it would cover (plus an optional `bonus`), and
   * commits the best-scoring one to the matrix. Returns the committed row, or
   * null if no valid candidate could be produced.
   *
   * `bonus(candidate)` lets callers add to the base pair-coverage score, e.g. to
   * reward rows that also satisfy still-open batch requirements (see
   * {@link generateRows}).
   */
  _addBestRow(filter, bonus) {
    const numCandidates = 1000;
    let bestRow = null;
    let bestScore = -1;

    for (let n = 0; n < numCandidates; n++) {
      const candidate = this._generateCandidate(filter);
      if (!candidate) {
        continue;
      }

      const key = JSON.stringify(candidate);
      if (this.duplicates.hasOwnProperty(key)) continue;

      let score = this._scoreNewPairs(candidate);
      if (bonus) {
        score += bonus(candidate);
      }
      if (score > bestScore) {
        bestScore = score;
        bestRow = candidate;
      }
    }

    if (!bestRow) {
      return null;
    }
    const key = JSON.stringify(bestRow);
    this.duplicates[key] = true;
    bestRow.name = this._computeName(bestRow);
    this._markCovered(bestRow);
    this.rows.push(bestRow);
    return bestRow;
  }

  generateRow(filter, {warnOnFailure = true} = {}) {
    this._initPairs();
    if (filter) {
      // If matching row already exists, no need to generate more
      const existing = this.rows.find(v => Axis.matches(v, filter));
      if (existing) {
        return existing;
      }
    }

    const row = this._addBestRow(filter, null);
    if (row) {
      return row;
    }

    const filterStr = typeof filter === 'string' ? filter.toString() : JSON.stringify(filter);
    const msg = `Unable to generate row for ${filterStr}. Please check include and exclude filters`;
    if (this._failOnUnsatisfiableFilters) {
      throw Error(msg);
    } else if (warnOnFailure) {
      console.warn(msg);
    }
  }

  ensureAllAxisValuesCovered(axisName) {
    for (let value of this.axisByName[axisName].values) {
      this.generateRow({[axisName]: value});
    }
  }

  /**
   * Returns a filter for every value of an axis, as a list suitable for the
   * `require` option of {@link generateRows}. For example,
   * `allAxisValues('ssl')` returns `[{ssl: <value0>}, {ssl: <value1>}, ...]`,
   * the batch equivalent of {@link ensureAllAxisValuesCovered}.
   */
  allAxisValues(axisName) {
    return this.axisByName[axisName].values.map(value => ({[axisName]: value}));
  }

  /**
   * Fisher-Yates shuffle in place, driven by the builder's seeded RNG so the
   * result stays reproducible for a given seed.
   */
  _shuffle(array) {
    for (let i = array.length - 1; i > 0; i--) {
      const j = Math.floor(this._random() * (i + 1));
      [array[i], array[j]] = [array[j], array[i]];
    }
    return array;
  }

  /**
   * Normalizes `require` entries to `{filter, tag}`. A bare filter object stays
   * a filter; a `{filter, tag}` wrapper is passed through.
   */
  _normalizeRequirements(specs) {
    return specs.map(spec => {
      if (spec && typeof spec === 'object' && !Array.isArray(spec)
          && ('filter' in spec || 'tag' in spec)) {
        return {filter: spec.filter, tag: spec.tag};
      }
      return {filter: spec, tag: undefined};
    });
  }

  /**
   * Number of axes a requirement filter pins. More pinned axes => more specific,
   * so it should anchor its own row rather than hope to be covered incidentally.
   */
  _filterKeyCount(filter) {
    return filter && typeof filter === 'object' && !Array.isArray(filter)
      ? Object.keys(filter).length
      : 1;
  }

  /**
   * Removes from `pending` every requirement that some existing row already
   * satisfies, firing its `tag(row)` callback once. Mutates `pending`.
   */
  _resolveSatisfied(pending) {
    for (let k = pending.length - 1; k >= 0; k--) {
      const req = pending[k];
      const row = this.rows.find(r => Axis.matches(r, req.filter));
      if (row) {
        if (req.tag) {
          req.tag(row);
        }
        pending.splice(k, 1);
      }
    }
  }

  /**
   * Reports a configuration problem (e.g. a misspelled option). Warns by
   * default, or throws when failOnUnsatisfiableFilters(true) has been set, to
   * match how generateRows escalates its other problems.
   */
  _reportConfigProblem(msg) {
    if (this._failOnUnsatisfiableFilters) {
      throw Error(msg);
    } else {
      console.warn(msg);
    }
  }

  /**
   * Warns about keys other than `require`/`requirePacking`/`fill` in a
   * generateRows options bag. Such keys are almost always typos (e.g.
   * `required`) whose only effect is that the intended requirements are
   * silently dropped.
   */
  _validateOptionKeys(options) {
    const known = new Set(['require', 'requirePacking', 'fill']);
    const unknown = Object.keys(options).filter(k => !known.has(k));
    if (unknown.length > 0) {
      this._reportConfigProblem(
        `generateRows ignored unknown option(s) ${unknown.map(k => `'${k}'`).join(', ')}; ` +
        `supported options are 'require', 'requirePacking', and 'fill'`);
    }
  }

  /**
   * A legacy fill filter may only key on axis names. Any other key is silently
   * ignored by the pairwise fill, which looks exactly like the requirements
   * were dropped — most commonly a misspelled `require` (e.g. `required`), the
   * top cause of "generateRows ignored my requirements" confusion. Warn instead.
   */
  _validateFillFilterKeys(filter) {
    if (!filter || typeof filter !== 'object' || Array.isArray(filter)) {
      return;
    }
    const unknown = Object.keys(filter).filter(k => !this.axisByName.hasOwnProperty(k));
    if (unknown.length === 0) {
      return;
    }
    const hint = unknown.some(k => /^(require|fill)/i.test(k))
      ? `; did you mean generateRows(maxRows, {require: [...]})?`
      : '';
    this._reportConfigProblem(
      `generateRows ignored filter key(s) ${unknown.map(k => `'${k}'`).join(', ')} ` +
      `that do not match any axis name${hint}`);
  }

  /**
   * Generates rows until `maxRows` is reached.
   *
   * With no options (or a legacy filter object) this is the original pairwise
   * fill: each new row maximizes uncovered-pair coverage.
   *
   * With `{require: [...]}` the listed combinations become a batch of hard
   * requirements. The result is guaranteed to contain a row matching each one
   * (subject to feasibility and the `maxRows` budget). Unlike calling
   * generateRow() once per requirement, the outcome does not depend on the
   * order of the list, and the job count is `maxRows`, not an emergent function
   * of that order.
   *
   * `requirePacking` controls how requirements share rows; either way the
   * `maxRows` budget and the per-requirement guarantee hold:
   *  - `'when-needed'` (default) merges requirements into one row only when the
   *    remaining budget is too tight to give each its own row. This keeps the
   *    required values from always being paired with each other, so they
   *    combine with more varied partners (see issue #11).
   *  - `'always'` merges every compatible requirement as tightly as possible,
   *    spending the fewest rows on requirements and leaving the most budget for
   *    the plain pairwise-coverage fill. This is the original behavior.
   *
   * Each `require` entry is either a filter (`{ssl: {value: 'yes'}}`) or a
   * `{filter, tag}` object whose `tag(row)` is called with the row that ends up
   * satisfying it — handy for marking a specific job without searching the
   * result again, e.g. a single coverage job:
   *   {filter: {os: {value: 'ubuntu-latest'}, ...}, tag: r => r.collectCoverage = true}
   *
   * Rows generated earlier (e.g. a pinned row from generateRow()) count toward
   * both the budget and requirement satisfaction.
   *
   * @param {number} maxRows
   * @param {object} [options] `{require, requirePacking, fill}`, or a legacy fill filter
   * @param {Array} [options.require] batch of hard requirements
   * @param {'when-needed'|'always'} [options.requirePacking='when-needed'] how tightly requirements share rows
   * @param {object|function} [options.fill] filter applied to the pairwise-coverage fill rows
   * @returns {Array} the generated rows
   */
  generateRows(maxRows, options) {
    this._initPairs();

    let requireSpecs = [];
    let fillFilter;
    let requirePacking = 'when-needed';
    if (options && (options.require || options.fill || options.requirePacking)) {
      requireSpecs = options.require || [];
      fillFilter = options.fill;
      requirePacking = options.requirePacking || 'when-needed';
      this._validateOptionKeys(options);
    } else {
      // Backward compatible: generateRows(maxRows[, filter])
      fillFilter = options;
      this._validateFillFilterKeys(fillFilter);
    }
    if (requirePacking !== 'when-needed' && requirePacking !== 'always') {
      throw new Error(`Invalid requirePacking: ${JSON.stringify(requirePacking)}. Expected 'when-needed' or 'always'.`);
    }

    const pending = this._normalizeRequirements(requireSpecs);
    // Phase 1 consumes `pending` in order, and that order decides which
    // requirement anchors a row and which one gets packed onto somebody else's
    // anchor. A fixed order therefore biases the same requirement into the same
    // spot on every seed (issue #11), so shuffle before consuming. Warnings
    // still list requirements as the caller wrote them.
    const declarationOrder = new Map(pending.map((req, i) => [req, i]));
    const asDeclared = reqs =>
      reqs.slice().sort((x, y) => declarationOrder.get(x) - declarationOrder.get(y));
    this._shuffle(pending);
    const infeasible = [];

    // Pinned / pre-existing rows may already satisfy some requirements.
    this._resolveSatisfied(pending);

    // When packing is on, a satisfied requirement must outweigh any pair-coverage
    // delta, so packing requirements into as few rows as possible always wins;
    // ties break on coverage.
    const BONUS = 1000;
    const requirementBonus = candidate => {
      let n = 0;
      for (const req of pending) {
        if (Axis.matches(candidate, req.filter)) {
          n++;
        }
      }
      return n * BONUS;
    };

    // Phase 1: satisfy requirements. Anchor each row on the most specific open
    // requirement (most pinned axes), ties broken by the shuffled order above.
    // Pack the broad ones onto that anchor when
    // `requirePacking` is 'always', or (for 'when-needed') only when the
    // leftover budget is too tight to give every open requirement its own row;
    // otherwise leave the anchor's other axes to random pair-coverage. Forced
    // packing always pairs the required values with each other (e.g. it pins
    // {a:1} to {b:'a'} in the same row), which starves variability, so under
    // 'when-needed' we pay that price only when the budget makes it unavoidable
    // (see issue #11).
    while (this.rows.length < maxRows && pending.length > 0) {
      const anchor = pending.reduce((a, b) =>
        this._filterKeyCount(b.filter) > this._filterKeyCount(a.filter) ? b : a);
      const mustPack = requirePacking === 'always'
        || pending.length > maxRows - this.rows.length;
      const row = this._addBestRow(anchor.filter, mustPack ? requirementBonus : null);
      if (!row) {
        infeasible.push(anchor);
        pending.splice(pending.indexOf(anchor), 1);
        continue;
      }
      this._resolveSatisfied(pending);
    }

    // Phase 2: spend the remaining budget on plain pairwise coverage.
    for (let i = 0; this.rows.length < maxRows && i < maxRows; i++) {
      const row = this.generateRow(fillFilter, {warnOnFailure: false});
      if (!row) {
        break;
      }
    }

    const problems = [];
    if (infeasible.length > 0) {
      problems.push(`unsatisfiable: ${asDeclared(infeasible).map(r => JSON.stringify(r.filter)).join(', ')}`);
    }
    if (pending.length > 0) {
      problems.push(`did not fit into ${maxRows} rows: ${asDeclared(pending).map(r => JSON.stringify(r.filter)).join(', ')}`);
    }
    if (problems.length > 0) {
      const msg = `generateRows could not satisfy all requirements (${problems.join('; ')})`;
      if (this._failOnUnsatisfiableFilters) {
        throw Error(msg);
      } else {
        console.warn(msg);
      }
    }

    return this.rows;
  }

  /**
   * Returns pair coverage statistics for the generated rows.
   * @returns {{covered: number, total: number, percentage: string, weightPercentage: string}}
   */
  pairCoverageReport() {
    this._initPairs();
    const covered = this._totalPairs - this._uncoveredPairs.size;
    const coveredWeight = this._totalPairsWeight - this._uncoveredPairsWeight;
    return {
      covered,
      total: this._totalPairs,
      percentage: (covered / this._totalPairs * 100).toFixed(1),
      weightPercentage: (coveredWeight / this._totalPairsWeight * 100).toFixed(1)
    };
  }

  /**
   * Computes the number of all the possible combinations.
   * @returns {{bad: number, good: number}}
   */
  summary() {
    if (this.axes.length === 0) {
      return {good: 0, bad: 0};
    }

    const row = {};
    let good = 0;
    let bad = 0;

    const visit = axisIndex => {
      if (axisIndex === this.axes.length) {
        if (this.matches(row)) {
          good++;
        } else {
          bad++;
        }
        return;
      }

      const axis = this.axes[axisIndex];
      for (const value of axis.values) {
        row[axis.name] = value;
        visit(axisIndex + 1);
      }
    };

    visit(0);
    return {good, bad};
  }
}

export { Axis, MatrixBuilder };

// END INSERTED matrix_builder.mjs
// -----------------------------------------------------------------------------------------------------------------


// -----------------------------------------------------------------------------------------------------------------
const seedrandom = (function () {
	const module = { exports: {} };

// https://github.com/vlsi/github-actions-random-matrix/blob/main/src/seedrandom.cjs
// BEGIN INSERTED seedrandom.cjs
/*
Copyright 2019 David Bau.

Permission is hereby granted, free of charge, to any person obtaining
a copy of this software and associated documentation files (the
"Software"), to deal in the Software without restriction, including
without limitation the rights to use, copy, modify, merge, publish,
distribute, sublicense, and/or sell copies of the Software, and to
permit persons to whom the Software is furnished to do so, subject to
the following conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

*/

(function (global, pool, math) {
//
// The following constants are related to IEEE 754 limits.
//

var width = 256,        // each RC4 output is 0 <= x < 256
    chunks = 6,         // at least six RC4 outputs for each double
    digits = 52,        // there are 52 significant digits in a double
    rngname = 'random', // rngname: name for Math.random and Math.seedrandom
    startdenom = math.pow(width, chunks),
    significance = math.pow(2, digits),
    overflow = significance * 2,
    mask = width - 1,
    nodecrypto;         // node.js crypto module, initialized at the bottom.

//
// seedrandom()
// This is the seedrandom function described above.
//
function seedrandom(seed, options, callback) {
  var key = [];
  options = (options == true) ? { entropy: true } : (options || {});

  // Flatten the seed string or build one from local entropy if needed.
  var shortseed = mixkey(flatten(
    options.entropy ? [seed, tostring(pool)] :
    (seed == null) ? autoseed() : seed, 3), key);

  // Use the seed to initialize an ARC4 generator.
  var arc4 = new ARC4(key);

  // This function returns a random double in [0, 1) that contains
  // randomness in every bit of the mantissa of the IEEE 754 value.
  var prng = function() {
    var n = arc4.g(chunks),             // Start with a numerator n < 2 ^ 48
        d = startdenom,                 //   and denominator d = 2 ^ 48.
        x = 0;                          //   and no 'extra last byte'.
    while (n < significance) {          // Fill up all significant digits by
      n = (n + x) * width;              //   shifting numerator and
      d *= width;                       //   denominator and generating a
      x = arc4.g(1);                    //   new least-significant-byte.
    }
    while (n >= overflow) {             // To avoid rounding up, before adding
      n /= 2;                           //   last byte, shift everything
      d /= 2;                           //   right using integer math until
      x >>>= 1;                         //   we have exactly the desired bits.
    }
    return (n + x) / d;                 // Form the number within [0, 1).
  };

  prng.int32 = function() { return arc4.g(4) | 0; }
  prng.quick = function() { return arc4.g(4) / 0x100000000; }
  prng.double = prng;

  // Mix the randomness into accumulated entropy.
  mixkey(tostring(arc4.S), pool);

  // Calling convention: what to return as a function of prng, seed, is_math.
  return (options.pass || callback ||
      function(prng, seed, is_math_call, state) {
        if (state) {
          // Load the arc4 state from the given state if it has an S array.
          if (state.S) { copy(state, arc4); }
          // Only provide the .state method if requested via options.state.
          prng.state = function() { return copy(arc4, {}); }
        }

        // If called as a method of Math (Math.seedrandom()), mutate
        // Math.random because that is how seedrandom.js has worked since v1.0.
        if (is_math_call) { math[rngname] = prng; return seed; }

        // Otherwise, it is a newer calling convention, so return the
        // prng directly.
        else return prng;
      })(
  prng,
  shortseed,
  'global' in options ? options.global : (this == math),
  options.state);
}

//
// ARC4
//
// An ARC4 implementation.  The constructor takes a key in the form of
// an array of at most (width) integers that should be 0 <= x < (width).
//
// The g(count) method returns a pseudorandom integer that concatenates
// the next (count) outputs from ARC4.  Its return value is a number x
// that is in the range 0 <= x < (width ^ count).
//
function ARC4(key) {
  var t, keylen = key.length,
      me = this, i = 0, j = me.i = me.j = 0, s = me.S = [];

  // The empty key [] is treated as [0].
  if (!keylen) { key = [keylen++]; }

  // Set up S using the standard key scheduling algorithm.
  while (i < width) {
    s[i] = i++;
  }
  for (i = 0; i < width; i++) {
    s[i] = s[j = mask & (j + key[i % keylen] + (t = s[i]))];
    s[j] = t;
  }

  // The "g" method returns the next (count) outputs as one number.
  (me.g = function(count) {
    // Using instance members instead of closure state nearly doubles speed.
    var t, r = 0,
        i = me.i, j = me.j, s = me.S;
    while (count--) {
      t = s[i = mask & (i + 1)];
      r = r * width + s[mask & ((s[i] = s[j = mask & (j + t)]) + (s[j] = t))];
    }
    me.i = i; me.j = j;
    return r;
    // For robust unpredictability, the function call below automatically
    // discards an initial batch of values.  This is called RC4-drop[256].
    // See http://google.com/search?q=rsa+fluhrer+response&btnI
  })(width);
}

//
// copy()
// Copies internal state of ARC4 to or from a plain object.
//
function copy(f, t) {
  t.i = f.i;
  t.j = f.j;
  t.S = f.S.slice();
  return t;
};

//
// flatten()
// Converts an object tree to nested arrays of strings.
//
function flatten(obj, depth) {
  var result = [], typ = (typeof obj), prop;
  if (depth && typ == 'object') {
    for (prop in obj) {
      try { result.push(flatten(obj[prop], depth - 1)); } catch (e) {}
    }
  }
  return (result.length ? result : typ == 'string' ? obj : obj + '\0');
}

//
// mixkey()
// Mixes a string seed into a key that is an array of integers, and
// returns a shortened string seed that is equivalent to the result key.
//
function mixkey(seed, key) {
  var stringseed = seed + '', smear, j = 0;
  while (j < stringseed.length) {
    key[mask & j] =
      mask & ((smear ^= key[mask & j] * 19) + stringseed.charCodeAt(j++));
  }
  return tostring(key);
}

//
// autoseed()
// Returns an object for autoseeding, using window.crypto and Node crypto
// module if available.
//
function autoseed() {
  try {
    var out;
    if (nodecrypto && (out = nodecrypto.randomBytes)) {
      // The use of 'out' to remember randomBytes makes tight minified code.
      out = out(width);
    } else {
      out = new Uint8Array(width);
      (global.crypto || global.msCrypto).getRandomValues(out);
    }
    return tostring(out);
  } catch (e) {
    var browser = global.navigator,
        plugins = browser && browser.plugins;
    return [+new Date, global, plugins, global.screen, tostring(pool)];
  }
}

//
// tostring()
// Converts an array of charcodes to a string
//
function tostring(a) {
  return String.fromCharCode.apply(0, a);
}

//
// When seedrandom.js is loaded, we immediately mix a few bits
// from the built-in RNG into the entropy pool.  Because we do
// not want to interfere with deterministic PRNG state later,
// seedrandom will not call math.random on its own again after
// initialization.
//
mixkey(math.random(), pool);

//
// Nodejs and AMD support: export the implementation as a module using
// either convention.
//
if ((typeof module) == 'object' && module.exports) {
  module.exports = seedrandom;
  // When in node.js, try using crypto package for autoseeding.
  try {
    nodecrypto = require('crypto');
  } catch (ex) {}
} else if ((typeof define) == 'function' && define.amd) {
  define(function() { return seedrandom; });
} else {
  // When included as a plain script, set up Math.seedrandom global.
  math['seed' + rngname] = seedrandom;
}


// End anonymous scope, and pass initial values.
})(
  // global: `self` in browsers (including strict mode and web workers),
  // otherwise `this` in Node and other environments
  (typeof self !== 'undefined') ? self : this,
  [],     // pool: entropy pool starts empty
  Math    // math: package containing random, pow, and seedrandom
);

// END INSERTED seedrandom.cjs
// -----------------------------------------------------------------------------------------------------------------
	return module.exports;
})();
