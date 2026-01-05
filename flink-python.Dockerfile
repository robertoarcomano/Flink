FROM flink:1.19

USER root
RUN apt-get update && apt-get install -y python3 python3-pip \
    && ln -sf /usr/bin/python3 /usr/bin/python \
    && pip3 install apache-flink==1.19.1 protobuf \
    && apt-get clean

USER flink
