# 基于openjdk:8-alpine镜像构建
FROM openjdk:8-alpine

# 指定工作目录，会到工作目录下执行命令
WORKDIR /app/

# ./target/app.jar 换成项目打包jar的路径和名字
ADD ./target/*.jar /app/app.jar

#通过文件链接的方式修改 时区
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# 设置暴露的端口号
EXPOSE 8080

# JVM启动参数设置
ENV JAVA_OPTS="\
-server \
-Duser.timezone=Asia/Shanghai \
-Xmx512m \
-Xms512m \
-XX:MaxMetaspaceSize=128m \
-XX:+UseSerialGC \
-XX:+HeapDumpOnOutOfMemoryError \
-XX:HeapDumpPath=/app/logs/dump/dumpfile.hprof"

# Jar包启动命令
ENTRYPOINT java ${JAVA_OPTS} -jar app.jar --server.port=8080