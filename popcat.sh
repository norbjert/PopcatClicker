#!/bin/sh
git pull
sudo apt install gradle -y
gradle jar
nohup java -jar "$(pwd)"/build/libs/discordSlave1-1.0.jar &