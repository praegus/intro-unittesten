#!/bin/bash
echo Building Docker Image
docker build -t praegus-perf-test .
echo Start Docker Container @ port 8080
docker run -p 8080:8080 praegus-perf-test
