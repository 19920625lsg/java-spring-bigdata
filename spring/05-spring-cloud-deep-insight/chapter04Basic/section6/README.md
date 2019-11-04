启动redis的命令
> /Users/liangshanguang/Program/redis   docker run --name myredis -p 6379:6379 -v $PWD/data:/data -d docker.io/redis --requirepass “123456”