## ELK记录log

### 简介
ELK是三个开源软件的缩写，分别表示：Elasticsearch , Logstash, Kibana , 它们都是开源软件。新增了一个FileBeat，它是一个轻量级的日志收集处理工具(Agent)，Filebeat占用资源少，适合于在各个服务器上搜集日志后传输给Logstash，官方也推荐此工具。
原本我使用的logstash，太消耗资源了。这里使用filebeat替换它。


链接：https://www.jianshu.com/p/f4812c21d6a8


### 过程
#### 1.创建目录
#### 2.目录暂定为elk
#### 3.依次创建需要的文件和文件夹。

### 编写配置文件:
#### docker-compose.yml的配置
#### filebeat.yml的配置

### 准备测试数据
#### 准备一份测试数据test_log放在./log下，方便测试效果

### 启动：
#### docker-compose up -d


### 查看：
#### 在kibana中，查询索引为test_filebeat的数据
