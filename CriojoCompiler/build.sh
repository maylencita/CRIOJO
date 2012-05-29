#!/bin/bash

SCALA_HOME=/opt/scala
SC_DIST_PATH=dist
SC_SRC_PATH=src/main
SC_BIN_PATH=bin
SC_INCLUDE_LIB_JAR=scala-library.jar
SC_MANIFEST_PATH=MANIFEST.MF
SC_STARTING_PATH=$(pwd)

if [[ ! $SCALA_HOME ]] ; then
    echo "ERROR: set a SCALA_HOME environment variable"
    exit 1
fi

if [[ ! -f $SCALA_HOME/lib/$SC_INCLUDE_LIB_JAR ]] ; then
    echo "ERROR: Cannot find Scala Libraries!"
    exit 1
fi

if [[ -z "$1" ]] ; then
    SC_APP=$(basename $SC_STARTING_PATH)
else
    SC_APP=$1
fi

[[ ! -d $SC_DIST_PATH ]] && mkdir $SC_DIST_PATH

if [[ ! -d $SC_BIN_PATH ]] ; then
    mkdir "$SC_BIN_PATH"
else
    rm -r "$SC_BIN_PATH"
    if [[ -d $SC_BIN_PATH ]] ; then
        echo "ERROR:  Cannot remove temp compile directory:  $SC_BIN_PATH"
        exit 1
    fi
    mkdir "$SC_BIN_PATH"
fi

if [[ ! -d $SC_SRC_PATH ]] || [[ ! -d $SC_DIST_PATH ]] || [[ ! -d $SC_BIN_PATH ]] ; then
    echo "ERROR: Directory not found!:  $SC_SRC_PATH or $SC_DIST_PATH or $SC_BIN_PATH"
    exit 1
fi

if [[ ! -f $SC_DIST_PATH/$SC_INCLUDE_LIB_JAR ]] ; then
    cp "$SCALA_HOME/lib/$SC_INCLUDE_LIB_JAR" "$SC_DIST_PATH"
fi

SCALA_MAIN=$(find ./$SC_SRC_PATH -name "$SC_APP.scala")
COMPILE_STATUS=$?
SCALA_MAIN_COUNT=$(echo "$SCALA_MAIN" | wc -l)


if [[ -f $SC_DIST_PATH/$SC_APP.jar ]] ; then
    rm "$SC_DIST_PATH/$SC_APP.jar"  
    if [[ -f $SC_DIST_PATH/$SC_APP.jar ]] ; then
        echo "Unable to remove existing distribution!:  $SC_DIST_PATH/$SC_APP.jar"
        exit 1
    fi
fi

if [[ ! -f $SC_MANIFEST_PATH ]] ; then
    LEN_BASE=$(echo $(( $(echo "./$SC_SRC_PATH" |wc -c) - 0 )))
    SC_MAIN_CLASS=$(echo $SCALA_MAIN |cut --complement -c1-$LEN_BASE)
    SC_MAIN_CLASS=${SC_MAIN_CLASS%%.*}
    SC_MAIN_CLASS=$(echo $SC_MAIN_CLASS |awk '{gsub( "/", "'"."'"); print}')

    echo $(echo "Main-Class: "$SC_MAIN_CLASS) > $SC_MANIFEST_PATH
    echo $(echo "Class-Path: "$SC_INCLUDE_LIB_JAR) >> $SC_MANIFEST_PATH
fi

scalac -sourcepath $SC_SRC_PATH -d $SC_BIN_PATH $SCALA_MAIN
COMPILE_STATUS=$?

if [[ $COMPILE_STATUS != "0" ]] ; then
    echo "Compile Failed!"
    exit 1
fi

cd "$SC_BIN_PATH"
jar -cfm ../$SC_DIST_PATH/$SC_APP.jar ../$SC_MANIFEST_PATH *
COMPILE_STATUS=$?
cd "$SC_STARTING_PATH"

if  [[ $COMPILE_STATUS != "0" ]] || [[ ! -f $SC_DIST_PATH/$SC_APP.jar ]] ; then
    echo "JAR Build Failed!"
    exit 1
fi

echo " "
echo "BUILD COMPLETE!... TO LAUNCH:  java -jar $SC_DIST_PATH/$SC_APP.jar"
echo " "
