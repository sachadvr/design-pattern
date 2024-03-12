#!/bin/bash

packageName="com.fges.todoapp.App"

if ! compileErrors=$(mvn compile 2>&1); then
  echo -e "$compileErrors"
  exit 1
fi

args=""
for arg in "$@"
do
    args="$args \"$arg\"";
done

mavenFlags+=("-q") # Display only errors

mvn $mavenFlags exec:java -Dexec.mainClass="$packageName" -Dexec.args="$args"
