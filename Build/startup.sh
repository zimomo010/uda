#!/bin/bash

set -x


#===========================================================================================
# JVM Configuration
#===========================================================================================
# JAVA_OPT="${JAVA_OPT}  -Xms${JVM_XMS} -Xmx${JVM_XMX} -Xmn${JVM_XMN} -XX:MetaspaceSize=${JVM_MS} -XX:MaxMetaspaceSize=${JVM_MMS}"
# JAVA_OPT="${JAVA_OPT} -XX:+UseConcMarkSweepGC -XX:+UseCMSCompactAtFullCollection -XX:CMSInitiatingOccupancyFraction=70 -XX:+CMSParallelRemarkEnabled -XX:SoftRefLRUPolicyMSPerMB=0 -XX:+CMSClassUnloadingEnabled -XX:SurvivorRatio=8 "
JAVA_OPT="${JAVA_OPT} -Xms${JVM_XMS} -Xmx${JVM_XMX}"

appname=$(ls *.jar)

echo "Web-API Server  is starting, you can docker logs your container"
exec java ${JAVA_OPT}  -Dsun.io.useCanonCaches=false  -jar ${appname} --spring.config.location=/home/appdeploy/conf/