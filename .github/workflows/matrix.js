const {MatrixBuilder} = require('./matrix_builder');
const {configureKotlinDefaults, javaDistributionAxis, javaVersionAxis, setMatrix} = require('./matrix_commons');

const matrix = new MatrixBuilder();
// we are not ready yet for jdk25 as it requires gradle 9.x which no longer supports jdk11 but we still want
// to support jdk11 for now
const withoutJdk25 = {...javaVersionAxis, values: javaVersionAxis.values.filter(x => x != '25') };
configureKotlinDefaults(matrix, javaDistributionAxis, withoutJdk25);

setMatrix(matrix, 4);
