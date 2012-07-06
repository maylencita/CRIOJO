#!/usr/bin/env bash

mvn compile
mvn exec:java -Dexec.mainClass=application.AppLauncher&
echo $! > running.pid