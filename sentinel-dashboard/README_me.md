# Sentinel
fork from https://github.com/jiajiangnan/Sentinel

基于sentinel-1.8.1版本,默认端口8858
根据https://github.com/jiajiangnan/Sentinel添加日志以及修改了一些代码

默认使用nacos模式

修改配置原因:datasource.provider命名太长，运行报错

配置修改:
```properties
ds.pr=nacos
ds.pr.nacos.server-addr=localhost:8848
ds.pr.nacos.username=nacos
ds.pr.nacos.password=nacos
ds.pr.nacos.namespace=public
ds.pr.nacos.group-id=SENTINEL_GROUP
```

# 使用

```yml
version: '3'
services:
  sentinel-dashboard:
    image: fxdom/sentinel-dashboard-nacos:1.8.1
    container_name: sentinel-dashboard
    restart: always
    # restart: on-failure
    environment:
      - JAVA_OPTS=-Dserver.port=8858 -Dcsp.sentinel.dashboard.server=localhost:8858 -Dsentinel.dashboard.auth.username=sentinel -Dsentinel.dashboard.auth.password=sentinel -Dproject.name=sentinel-dashboard
      # 填写nacos的ip，即是是本机也不使用localhost | 127.0.0.1
      - NACOS_SERVER_ADDR=192.168.2.105:8848
      - NACOS_USERNAME=nacos
      - NACOS_PASSWORD=nacos
      - NACOS_NAMESPACE=public
      - NACOS_GROUP_ID=SENTINEL_GROUP
    ports:
      - "8858:8858"
    volumes:
      - ./logs:/root/log
      - /etc/localtime:/etc/localtime:ro
    networks:
      - sentinel_net
networks:
  sentinel_net:
    driver: bridge
```
