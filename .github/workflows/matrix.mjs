import {createGitHubMatrixBuilder} from './vlsi_matrix_builder.mjs';
import {configureKotlinDefaults, filterValues, generateJvmRows, javaVersionAxis, setMatrix} from "./matrix_commons.mjs";

const {matrix} = createGitHubMatrixBuilder();
// we are not ready yet for jdk25 as it requires gradle 9.x which no longer supports jdk11 but we still want
// to support jdk11 for now
configureKotlinDefaults(matrix, {versionAxis: filterValues(javaVersionAxis, x => x !== '25')});
const include = generateJvmRows(matrix, process.env.GITHUB_EVENT_NAME === "pull_request" ? 3 : 1)
setMatrix(matrix, include);
