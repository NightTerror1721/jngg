#!/bin/sh
SERVICE_NAME=DCBoxDaemon
PATH_TO_JAR=/home/pokemondongo/DCBOX_Server/target/DCBOX_Server.jar
PID_PATH_NAME=/tmp/DCBOX_Daemon-pid
case $1 in
    start)
        echo "Starting $SERVICE_NAME ..."
        cd /home/pokemondongo/DCBOX_Server/target
        if [ ! -f $PID_PATH_NAME ]; then
            nohup java -jar $PATH_TO_JAR /tmp 2>> /home/pokemondongo/DCBOX_Server/target/ServiceStderr.log >> /home/pokemondongo/DCBOX_Server/target/ServiceStdout.log &
                        echo $! > $PID_PATH_NAME
            echo "$SERVICE_NAME started ..."
        else
            echo "$SERVICE_NAME is already running ..."
        fi
    ;;
    stop)
        if [ -f $PID_PATH_NAME ]; then
            PID=$(cat $PID_PATH_NAME);
            echo "$SERVICE_NAME stoping ..."
            kill $PID;
            echo "$SERVICE_NAME stopped ..."
            rm $PID_PATH_NAME
        else
            echo "$SERVICE_NAME is not running ..."
        fi
    ;;
    restart)
        if [ -f $PID_PATH_NAME ]; then
            cd /home/pokemondongo/DCBOX_Server/target
            PID=$(cat $PID_PATH_NAME);
            echo "$SERVICE_NAME stopping ...";
            kill $PID;
            echo "$SERVICE_NAME stopped ...";
            rm $PID_PATH_NAME
            echo "$SERVICE_NAME starting ..."
            nohup java -jar $PATH_TO_JAR /tmp 2>> /home/pokemondongo/DCBOX_Server/target/ServiceStderr.log >> /home/pokemondongo/DCBOX_Server/target/ServiceStdout.log &
                        echo $! > $PID_PATH_NAME
            echo "$SERVICE_NAME started ..."
        else
            echo "$SERVICE_NAME is not running ..."
        fi
    ;;
esac
