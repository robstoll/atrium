const {MatrixBuilder} = require('./matrix_builder');
const {configureKotlinDefaults, setMatrix} = require('./matrix_commons');

const matrix = new MatrixBuilder();
configureKotlinDefaults(matrix)

setMatrix(matrix, 4);
