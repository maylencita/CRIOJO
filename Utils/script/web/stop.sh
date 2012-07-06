#!/usr/bin/env bash

pidtostop=`cat running.pid`
kill -9 $pidtostop