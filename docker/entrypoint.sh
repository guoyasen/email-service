#!/bin/bash
set -ex
USER_NAME=daemon
USER_ID=$(id -u)
if [ $$ -eq 1 -a x"$USER_ID" != x"0" ];then
        sed "/^$USER_NAME/s/[^:]*/$USER_ID/3" /etc/passwd > /tmp/passwd
        cat /tmp/passwd > /etc/passwd
        rm -f /tmp/passwd
fi
paramNum=$#
if [ "X${paramNum}" != "X" -a "X${paramNum}" != "X0" ];then
    exec $@
else
    echo "Command can not be NULL"
fi
