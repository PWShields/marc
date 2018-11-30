#!/usr/bin/env bash
CONTAINER=none
PORT=40109
REPO=git@github.com:nla/marc-converter
GIT_BRANCH=master
APP_OPTS= ""
JAVA_OPTS= -Dserver.port=40109 -jar /apps/marc-converter/lib/marc.jar
HEAP_SIZE=1024m
