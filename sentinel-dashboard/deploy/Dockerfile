FROM java:8

# add api jar
VOLUME /tmp

#定义时区参数
ENV TZ=Asia/Shanghai
#设置时区
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo '$TZ' > /etc/timezone

ENV JAVA_OPTS=""
ENV NACOS_SERVER_ADDR=""
ENV NACOS_USERNAME=""
ENV NACOS_PASSWORD=""
ENV NACOS_NAMESPACE=""
ENV NACOS_GROUP_ID=""

EXPOSE 8858

COPY sentinel-dashboard-1.8.1.jar /sentinel-dashboard-1.8.1.jar
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -Dds.pr.nacos.server-addr=$NACOS_SERVER_ADDR -Dds.pr.nacos.username=$NACOS_USERNAME -Dds.pr.nacos.password=$NACOS_PASSWORD -Dds.pr.nacos.namespace=$NACOS_NAMESPACE -Dds.pr.nacos.group-id=$NACOS_GROUP_ID -jar /sentinel-dashboard-1.8.1.jar" ]