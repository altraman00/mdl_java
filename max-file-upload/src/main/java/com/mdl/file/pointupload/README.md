springboot 断点上传 

访问地址 
http://127.0.0.1:8080/shard_file_upload.html

参考：https://www.jb51.net/article/190808.htm


FileUploadUtils 中的 checkAndSetUploadProgress 判断文件是否已经上传完成，然后将下面文件重命名的逻辑有问题，文件其实还未全部上传完就已经被重命名了