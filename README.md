## Gradel 配置

### 一、配置环境变量

打开 系统属性->高级->环境变量-系统变量 中添加：
	
	GRADLE_HOME：D:\Apache\Gradle\gradle-5.3-all
	GRADLE_USER_HOME：D:\Apache\Gradle\repository //可省略
	PATH：;%GRADLE_HOME%\bin;

验证是否配置成功，进入cmd后，输入：

	gradle -v


<br>
<hr>
<br>

### 二、Eclipse 安装 Gradle 插件

Help->Eclipse Marketplace... 打开窗口后输入 “Gradle” 进行安装。安装完成配置 Gradle 路径和指定仓库路径，即可正常使用。


<br>
<hr>
<br>

### 三、配置Gradle的仓库路径修改的四种方法

**方法一：**

修改gradle.properties文件，增加一句

	gradle.user.home=D:/Apache/Gradle/repository

>但这种方法，需要每个项目文件都要改一遍，如果只是临时修改，可以采用这种方法，如果是针对所有项目的，不建议这种方法。

**方法二：**

Window->Preferences->Gradl 选择 “Local installation directory” 点击 “Browes...” 选择gradle路径；在 “Gradle user home” 中指定仓库位置； “Java home” 指定JDK，到此整个配置已结束。

**方法三：**

修改gradle启动脚本，进入gradle安装的bin目录，使用文本编辑器打开gradle.bat文件，在 “set APP_HOME=%DIRNAME%” 下面添加以下语句：

	set GRADLE_OPTS="-Dgradle.user.home=D:/Apache/Gradle/repository"

**方法四：**

以上几种方法，都不是最理想的方法，下面推荐Windows环境变量设置gradle仓库目录:

	变量名：GRADLE_USER_HOME
	变量值：D:\Apache\Gradle\repository


<br>
<hr>
<br>

### 四、创建项目

在Eclipse中右击 New->Other->Gradle->Gradle Project 打开 “New Gradle Project” 输入项目名后 “Next”, 打开 “Options” 窗口，如果在Eclipse已经配置了Gradle，直接点击 “Next” ，如果没有配置请选择 “Override Workspace Settings” ，才可以选择 “Gradle distribution” 下的选项，选择 “Local installation directory” 点击 “Browes...” 选择gradle路径；在 “Gradle user home” 中指定仓库位置； “Java home” 指定JDK，配置完成后点击 “Next” Gradle开始更新缓存，最后点击 “Finish” 完成（可省略）。

> Confingure Workspace Settings是查看已配置的信息

<br>
<hr>
<br>

### 五、Gradle配置build.gradle

>详细配置直接看代码

mavenLocal()表示会从本地获取资源，获取资源的顺序为: <br>
> %USER_HOME%/.m2/settings.xml中指定的路径   <br>
> %M2_HOME%/conf/settings.xml中指定的路径  <br>
> %USER_HOME%/.m2/repository <br>

> *注：这里发现一个BUG，按照官方文档说，如果系统用户文件夹下的.m2中不存在settings.xml文件会自动去找%M2_HOME%的settings.xml文件，实际却不会，然后直接指向了%USER_HOME%/.m2/repository。建议自定义maven本地库的，把%M2_HOME%的settings.xml文件COPY一份到%USER_HOME%/.m2目录下即可。*


Maven类似，gradle导入依赖包也可以定义其作用的生命周期： <br>
> compile：编译时必须。 <br>
> runtime：运行时必须，包括编译时。 <br>
> testCompile：测试编译时必须。 <br>
> testRuntime：测试运行时必须，包括编译时。 <br>

注：此外配置依赖包，还可以模块化配置、导入list、配置是否传递等

<br>

**plugin插件配置**

	apply plugin: 'java' //Java的JDK
	apply plugin: 'eclipse' //eclipse项目插件
	apply plugin: 'war' //项目构建输出war
	apply plugin: 'eclipse-wtp' //web传输层插件
	apply plugin: 'java-library' //Java Library插件通过提供有关Java库的特定知识来扩展Java插件的功能
	apply plugin: "maven" //Maven插件
	apply plugin: "maven-publish" //Maven 发布插件

或者

	plugins {
	    id 'java'
		id 'eclipse'
		id 'java-library'
		id 'war'
		id 'eclipse-wtp'
		id 'java-library'
	}

<br>

**ext是自定义属性**

现在很多人都喜欢把所有关于版本的信息都利用ext放在另一个自己新建的gradle文件中集中管理。

***例如：***

首先我们新建两个文件，分别叫build.gradle和version.gradle 

**version.gradle**

	ext{
		versionName = "1.0.0"
		versionCole = "1"
	}

**build.gradle**

	apply from: "version.gradle"
	
	task printStringClass{
	    println "当前版本是${versionName}"
	}	

**结果：**

	> Configure project :
	当前版本是1.0.0

<br>
<hr>
<br>

### 六、maven与gradle转换

#### maven -> gradle

先保证本机安装了gradle 2.0以上的版本，然后在maven项目的根目录下运行

    gradle init --type pom


#### gradle -> maven

在build.gradle中增加以下内容(group，version可自行修改，artifactId默认为目录名称)

	apply plugin: 'java'
	apply plugin: 'maven'
	group = 'com.101tec'
	version = '0.7-dev'
	sourceCompatibility = 1.6

然后./gradlew  build ，成功后将在build\poms目录下生成pom-default.xml文件，把它复制到根目录下，改名成pom.xml即可。

当然，通过修改build.gradle 也可以直接在根目录下生成pom.xml

	task writeNewPom << {
	    pom {
	        project {
	            inceptionYear '2008'
	            licenses {
	                license {
	                    name 'The Apache Software License, Version 2.0'
	                    url 'http://www.apache.org/licenses/LICENSE-2.0.txt'
	                    distribution 'repo'
	                }
	            }
	        }
	    }.writeTo("$buildDir/pom.xml")
	}





<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>

-----------


参考地址：
> https://blog.csdn.net/github_38616039/article/details/79933133 <br>
> https://blog.csdn.net/hu9645313573/article/details/75168360 <br>
> https://www.cnblogs.com/skzncer99/p/5315640.html <br>
> https://blog.csdn.net/lxlmycsdnfree/article/details/80198410 <br>
> https://blog.csdn.net/honghailiang888/article/details/54944621 <br>
> https://www.cnblogs.com/IcanFixIt/p/6909338.html <br>
