#!/usr/bin/env bash

mvn clean package

echo 'Starting deploy. Coping files...'

scp -i /c/java/ssh/bot.pem \
    /c/Users/Sergey/Documents/GitHub/bot/target/bot-1.0-SNAPSHOT.jar \
    ubuntu@ec2-3-16-25-219.us-east-2.compute.amazonaws.com:/home/ubuntu/

echo 'Restart server...'

ssh -i /c/java/ssh/bot.pem ubuntu@ec2-3-16-25-219.us-east-2.compute.amazonaws.com << EOF

echo 'Killing java processes...'
pgrep java | xargs kill -9

echo 'Starting java...'
nohup java -jar bot-1.0-SNAPSHOT.jar > logj.txt &

EOF

echo 'Deploy complete'