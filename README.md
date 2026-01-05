# Flink
Apache Flink tutorial

## Installation
### Docker buildx version
You need to be sure to have at least buildx version >= 0.17
```
sudo apt remove docker-buildx
mkdir -p ~/.docker/cli-plugins

curl -SL https://github.com/docker/buildx/releases/download/v0.17.0/buildx-v0.17.0.linux-amd64 \
  -o ~/.docker/cli-plugins/docker-buildx

chmod +x ~/.docker/cli-plugins/docker-buildx
```

### Docker-compose
The necessary python packages will be installed using the <a href=flink-python.Dockerfile>flink-python.Dockerfile</a>
```                        
docker-compose up -d
```

### Install Maven
```
 sudo apt install maven
```

### Launch the python version
```
docker-compose exec -it jobmanager bin/flink run -py /data/wordcount.py /data/input_file.txt
```
You can see the results on the /data/output_file.txt

### Build the java version
```
mvn clean package
```

### Launch the java version
```
docker-compose exec -it jobmanager bin/flink run /data/target/flink-wordcount-1.0-shaded.jar /data/input_file.txt
```
You can see the results on the /data/output_fileTIMESTAMP file or directory depending on you specified parallelism = 1 or more.

Note: you can also connect to the GUI, upload the jar file and launch it specifying:

1. Arguments
2. Parallelism


