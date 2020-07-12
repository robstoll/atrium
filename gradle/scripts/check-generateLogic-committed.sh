#!/usr/bin/env bash
set -e

current_dir="$( cd "$(dirname "$0")" >/dev/null 2>&1 ; pwd -P )"

"$current_dir/../../gradlew" generateLogic

output=$(git status --porcelain);
if [ -n "$output" ]; then
  echo "You forgot to commit the changes of generateLogic - run \`./gradlew generateLogic\` again and commit them. Following files are affected:"
  printf "%s\n" "$output";
  git diff --color -R;
  exit 1;
fi
