#!/usr/bin/env bash

mvn package
java -Xss1024k -jar target/Test-APP-1.0-SNAPSHOT-jar-with-dependencies.jar&
echo $! > running.pid