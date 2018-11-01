#!/bin/bash
# build image, upload image, update service image

mv ./build/libs/*.jar /server/currency/currency.jar
nohup java -jar /server/currency/currency.jar > /server/currency/currency.log &
echo "---done---"