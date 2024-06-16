let { MatrixBuilder } = require('./matrix_builder');
const matrix = new MatrixBuilder();

// Add axes for the matrix
matrix.addAxis({
    name: 'os',
    title: x => x.replace('-latest', ''),
    values: [
        'ubuntu-latest',
        'windows-latest'
    ]
});
matrix.addAxis({
    name: 'java_version',
    values: ['11', '17']
});

// Configure the order of the fields in job name
matrix.setNamePattern(['os', 'java_version']);

// Ensure at least one windows and at least one linux job is present (macos is almost the same as linux)
matrix.generateRow({ os: 'windows-latest' });
matrix.generateRow({ os: 'ubuntu-latest' });

// Generate more rows, no duplicates would be generated
const include = matrix.generateRows(process.env.MATRIX_JOBS || 5);
if (include.length === 0) {
    throw new Error('Matrix list is empty');
}
// Sort jobs by name, however, numeric parts are sorted appropriately
// For instance, 'windows 8' would come before 'windows 11'
include.sort((a, b) => a.name.localeCompare(b.name, undefined, { numeric: true }));

console.log(include);
console.log('::set-output name=matrix::' + JSON.stringify({ include }));
