#!/bin/sh
git pull
#sudo apt install gradle -y
gradle build
nohup java -jar "$(pwd)"/build/libs/popcatClicker-0.0.1-SNAPSHOT.jar &
