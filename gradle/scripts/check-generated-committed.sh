#!/usr/bin/env bash
set -e

current_dir="$( cd "$(dirname "$0")" >/dev/null 2>&1 ; pwd -P )"

"$current_dir/../../gradlew" generateLogic :readme-examples:readme

output=$(git status --porcelain);
if [ -n "$output" ]; then
  echo "You forgot to commit some generated files. Run \`./gradlew generateLogic :readme-examples:readme\` and commit them. The following files are affected:"
  printf "%s\n" "$output";
  git diff --color -R;
  exit 1;
fi
