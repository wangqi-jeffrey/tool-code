#### eclipse 安装MyBatis Generator 插件

MyBatis官方提供的一个eclipse插件，可以用于生成Mybatis所需要的映射文件。

现在网上大多提供的都是在线安装和离线安装的做法，在线安装的网址：http://mybatis.googlecode.com/svn/sub-projects/generator/trunk/eclipse/UpdateSite/，但是所提供的这个网址已经变成404的了。所以在这里着重说说下离线安装的方法。

现在Mybatis的代码是在GitHub上面进行托管，地址为：https://github.com/mybatis。在里面可以看到有Mybatis下面不同的项目。

本文所需要用到的插件在https://github.com/mybatis/generator/tree/master/eclipse/UpdateSite这里下载，下载完之后解压开来，然后把UpdateSite这个文件夹复制到eclipse目录下面的dropins文件夹内，重启eclipse就可以看到右键多增加了一个Generate MyBatis的选项了。