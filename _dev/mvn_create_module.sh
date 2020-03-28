#!/usr/bin/env bash

PROJECT_DIR="$(cd $(dirname "${BASH_SOURCE[0]}") && pwd )/.."

cd ${PROJECT_DIR} && mvn archetype:generate -DgroupId=$1 -DartifactId=$2 \
-DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false


