#!/bin/sh

./gradlew clean build
docker build --platform linux/amd64 -t won983212/happyburstday:latest -t won983212/happyburstday:latest .
docker login -u won983212 -p $PASSWORD
docker push won983212/happyburstday:latest
