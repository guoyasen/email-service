FROM hub.huarun.com/base/openjdk:8u242-jre-alpine

MAINTAINER "zhaobang.huang@iquantex.com"

WORKDIR /opt/deployments

#COPY --from=agent /opt/deployments/skywalking-agent/ /opt/deployments/skywalking-agent/

COPY entrypoint.sh /entrypoint.sh

COPY app.jar app.jar


RUN chmod +x /entrypoint.sh \
    && chgrp -R 0 /opt/deployments/ \
    && chmod -R g=u /opt/deployments \
    && addgroup daemon root \
    && chmod g+w /etc/passwd
ENTRYPOINT ["/entrypoint.sh"]
CMD ["java","-jar","-XX:+UnlockExperimentalVMOptions","-XX:+UseCGroupMemoryLimitForHeap","-XX:MaxRAMFraction=2","-XshowSettings:vm","/opt/deployments/app.jar"]
