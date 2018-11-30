#!/usr/bin/env bash

mvn clean package

mkdir -p $1/lib

cp backend/target/*.jar $1/lib/marc.jar
