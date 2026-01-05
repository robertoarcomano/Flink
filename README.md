# Flink
Apache Flink tutorial

## Installation
### Docker buildx version
You need to be sure that you have at least buildx version >= 0.17
```
sudo apt remove docker-buildx
mkdir -p ~/.docker/cli-plugins

curl -SL https://github.com/docker/buildx/releases/download/v0.17.0/buildx-v0.17.0.linux-amd64 \
  -o ~/.docker/cli-plugins/docker-buildx

chmod +x ~/.docker/cli-plugins/docker-buildx
```

### Docker-compose
```                        
docker-compose up -d
```
