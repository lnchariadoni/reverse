#!/bin/sh

echo "Running Spotless check..."

mvn spotless:check

if [ $? -ne 0 ]; then
  echo "Spotless check failed. fixing formatting issues by running 'mvn spotless:apply'."
  mvn spotless:apply
  exit 0
else
  echo "Spotless check passed."
  exit 0
fi
