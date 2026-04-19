FROM itd-docker-uat.dev.jfrog.gtjai.net/benefitunion/jdk-base_21:1.0.4
LABEL app="gj-web-api"  "author"="benefitunion"
RUN groupadd -r -g 998 appdeploy \
 && useradd  -r -u 999 -g 998 appdeploy \
    && mkdir -p  /home/appdeploy/logs/gj-web-api/
ENV JVM_XMS="1024m" \
    JVM_XMX="1024m" \
    JVM_XMN="512m"  \
    LC_ALL="zh_CN.utf8"

# set startup log dir
WORKDIR /home/appdeploy/
ADD target/gj-web-api-*.jar  /home/appdeploy/
ADD Build/startup.sh  /home/appdeploy/
RUN chown -R appdeploy:appdeploy /home/appdeploy \
&& mkdir /home/appdeploy/conf
USER appdeploy
RUN chmod +x startup.sh
ENTRYPOINT ["./startup.sh"]