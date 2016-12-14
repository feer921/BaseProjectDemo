# BaseProjectDemo
Android快速开发框架BaseProject的Demo

# 下载此Demo工程运行方法

由于本Demo工程为库工程BaseProject的Demo工程，本库依赖于
[BaseProject](https://github.com/feer921/BaseProject)，并且在本工程的settings.gradle
下有如下配置:
> 
    include ':app'
    include ':BaseProject'
    project(':BaseProject').projectDir = new File(rootProject.projectDir, '../BaseProject')


即需要下载BaseProject需要放在与本工程同级目录下，当然也可自已手动更改