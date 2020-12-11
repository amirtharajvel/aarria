#!/bin/bash
d=$(date '+%Y-%m-%d-%H-%M-%S')-fallsbuy.sql;
mkdir -p /opt/fallsbuy/mysqldump/;
/usr/bin/mysqldump -uroot -pSuper*Star77 fallsbuy > /opt/fallsbuy/mysqldump/"$d"
#ssh into fallsbuycdn and delete files if more than 3 files present
sshpass -f /opt/fallsbuycdn.txt ssh root@172.105.35.20 "mkdir -p /opt/fallsbuy/mysqldump/" && sshpass -f /opt/fallsbuycdn.txt scp /opt/fallsbuy/mysqldump/* root@172.105.35.20:/opt/fallsbuy/mysqldump/
sshpass -f /opt/fallsbuycdn.txt ssh root@172.105.35.20 "cd /opt/fallsbuy/mysqldump/; ls -1t | tail -n +4 | xargs rm -f"

