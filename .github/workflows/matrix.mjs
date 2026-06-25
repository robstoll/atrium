// Generates a random subset of valid java_distribution, java_version, and os combinations.
// Preview the result locally with "node matrix.mjs".
// See https://github.com/vlsi/github-actions-random-matrix
import { appendFileSync } from 'fs';
import { EOL } from 'os';
import { createGitHubMatrixBuilder } from '@vlsi/github-actions-random-matrix/github';

const { matrix } = createGitHubMatrixBuilder();

// Some include/exclude filters can become unsatisfiable, and the matrix ignores that by default.
// Uncomment the line below to fail instead, which is handy after adding new axes or constraints.
// matrix.failOnUnsatisfiableFilters(true);

matrix.addAxis({
  name: 'java_distribution',
  values: [
    'corretto',
    'dragonwell',
    'graalvm',
    'liberica',
    'microsoft',
    'sapmachine',
    'semeru',
    'temurin',
    'zulu',
  ]
});

// Java 25 needs Gradle 9.x, which no longer supports Java 11. We still want to test Java 11,
// so Java 25 is held back for now.
matrix.addAxis({
  name: 'java_version',
  title: x => 'Java ' + x,
  values: [
    '11',
    '17',
    '21',
  ]
});

matrix.addAxis({
  name: 'os',
  title: x => x.replace('-latest', ''),
  values: [
    'ubuntu-latest',
    'windows-latest',
    'macos-latest',
  ]
});

// graalvm needs at least Java 17, and for Java 17 only 17.0.12 is available. Rewriting 17 to
// 17.0.12 has other implications, so to keep it simple graalvm skips Java 11 and 17.
matrix.exclude({java_distribution: 'graalvm', java_version: '11'});
matrix.exclude({java_distribution: 'graalvm', java_version: '17'});

// dragonwell has no macOS (arm64) build.
matrix.exclude({java_distribution: 'dragonwell', os: 'macos-latest'});

// Axis order in the CI job name; the individual titles are joined with a comma.
matrix.setNamePattern(['java_version', 'java_distribution', 'os']);

// Build the whole matrix in a single call. generateRows guarantees a row for each requirement,
// packs them into as few jobs as it can, and spends the rest of the MATRIX_JOBS budget on
// pairwise coverage.
const include = matrix.generateRows(Number(process.env.MATRIX_JOBS || 4), {
  require: [
    // Test the oldest and the newest supported Java version.
    {java_version: matrix.axisByName.java_version.values[0]},
    {java_version: matrix.axisByName.java_version.values.slice(-1)[0]},
    // Test at least one Linux and one Windows job.
    {os: 'ubuntu-latest'},
    {os: 'windows-latest'},
  ],
});

if (include.length === 0) {
  throw new Error('Matrix list is empty');
}

// Sort jobs by name; numeric parts sort naturally, so "Java 11" comes before "Java 17".
include.sort((a, b) => a.name.localeCompare(b.name, undefined, {numeric: true}));

console.log(include);

const filePath = process.env['GITHUB_OUTPUT'] || '';
if (filePath) {
  appendFileSync(filePath, `matrix<<MATRIX_BODY${EOL}${JSON.stringify({include})}${EOL}MATRIX_BODY${EOL}`, {
    encoding: 'utf8'
  });
}
