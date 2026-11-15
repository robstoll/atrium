import {createGitHubMatrixBuilder} from './vlsi_matrix_builder.mjs';
import {
    configureKotlinDefaults,
    filterValues,
    generateJvmRows,
    javaDistributionAxis,
    javaVersionAxis,
    setMatrix
} from "./matrix_commons.mjs";

const {matrix} = createGitHubMatrixBuilder();
// we are not ready yet for jdk25 as it requires gradle 9.x which no longer supports jdk11 but we still want
// to support jdk11 for now
configureKotlinDefaults(matrix, {
    versionAxis: filterValues(javaVersionAxis, x => x !== '25'),
    // makes atrium-samples-test fail with > Flight Recorder is not supported on this VM, pause for the moment
    distributionAxis: filterValues(javaDistributionAxis, x => x !== 'semeru')
});
const include = generateJvmRows(matrix, process.env.GITHUB_EVENT_NAME === "pull_request" ? 3 : 1)
setMatrix(matrix, include);
