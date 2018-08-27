# MyRichText
我的富文本编辑module
=========
我写的这个module是对richeditor-android进行了封装，把常用的集成进来，并对页面布局进行了优化，
你们要用的话直接下载这个项目，进行导入即可使用。
集成的内容如下

字体加粗、字体下划线、斜体、字体颜色修改，字体标题大小变换、以及插入图片、生成html，结合webview，以及带有引用功能。
--

如果功能觉得不够多里面MainActivity里面的类集成了更多，我现在只是隐藏了，需要更多富文本请看源码
把AndroidManifest.xml文件里的启动类改成MainActivity,现在启动类是MainActivityTest
--

注意事项
==
这个webview编辑器由于需要获取焦点，比如改变字体颜色啥的，还有字体变粗，而我用的popwindow也是要获取焦点的，然而这不是重点，
--
重点是唤醒的键盘也是会获取焦点，而且优先级高于前面两者，所以当键盘隐藏的时候是编辑器里面的有些功能就失效了，所以就得在AndroidManifest.xml
--
里面加属性让键盘不主动隐藏
--
觉得满意的话，可否留下你们的小心心^_^
--

The module I wrote was encapsulation of richeditor-android, bringing in common integration and optimizing page layout.

You need to download the project directly and import it.

The content of the integration is as follows



Font thickening, font underline, italic, font color modification, font size change, and inserting pictures, generating HTML, combining WebView, and with reference function.

-

If the function doesn't feel enough, the class in MainActivity is integrated more. I'm just hiding now. I need more rich text. Please look at the source code.

Change the startup class in the AndroidManifest.xml file to MainActivity, and now the startup class is MainActivityTest.

-

The point is that the wake-up keyboard also gets the focus, and the priority is higher than the previous two, so when the keyboard is hidden, some of the functions in the editor fail, so it has to be in Android Manifest. xml.

-

Add attributes inside so that the keyboard does not take the initiative to hide.

-

If you feel satisfied, can you leave your little heart?

demo图片如下

![效果图1](https://github.com/oyd5201/MyProject/raw/master/images/xgt1.png)
![效果图2](https://github.com/oyd5201/MyProject/raw/master/images/xgt2.png)
![效果图3](https://github.com/oyd5201/MyProject/raw/master/images/xgt3.png)
