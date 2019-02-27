# AutoReddot
A Tool for Automatically Calculating Red dot
写了一个demo可以自动显示红点，在其他界面红点数变化，对应的父界面的红点也会发生变化。

如下图：

![image](https://upload-images.jianshu.io/upload_images/5223818-1c6644c81c97c197.gif?imageMogr2/auto-orient/strip)

**设计思路：**

每个红点可视为为一个节点，每个节点都可以作为父节点或子节点，类似树状。与树状不一样的是，子节点可以有多个父节点（例如在一个子页面里面的红点有多个快捷入口红点）。

**节点数据结构：**

1.点数

2.子节点列表

3.父节点列表。如下图

实现算法：只有子节点可主动设置数据变化，当一个个子节点的数据发生变化，需要通知父节点重新计算点数，计算方法为将所有子节点点数相加，然后递归触发父节点计算方法，向上刷新数据。父节点可以做清零操作，即把所有子节点点数置0，向下递归清零，并且向上递归计算点数。下图是一个例子
![image](https://upload-images.jianshu.io/upload_images/5223818-dddae94e9422eea3.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
代码例子：
其实只有两个类

***Reddot***  为控制红点的类,包含标识key、红点值、父节点、子节点、自动计算通知更新接口等

***ReddotManage*** 为维护Reddot的类


```

public class ReddotList {

public static ReddotmainReddot =new Reddot("mainReddot");

    public static ReddotsecondReedot1 =new Reddot("secondReedot1",mainReddot);

    public static  ReddotsecondReedot2 =new Reddot("secondReedot2",mainReddot);

    public static void init(){
        secondReedot1.addOne();
        secondReedot2.addOne();
    }
}

```

数据需要再使用前初始化、这里放在Application 这里了
```
public class DemoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ReddotList.init();
    }
}
```

Activity有两个MainActivity 和 SecondActivity。MainActivty有个红点，改红点数值为 SecondActivity的红点数值总和
```
        //注册红点监听器
        ReddotList.mainReddot.registerLisenter(this);

```
更新回调方法
```
  @Override
    public void updateView(String key, int count) {
        if (key.equals(ReddotList.mainReddot.getKey())) {
            mMainDot.setVisibility(count > 0 ? View.VISIBLE : View.GONE);
            mMainDot.setText(count + "");
        }
    }
```
红点点数变化方法，还有setValue等
```
@Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_left_add:
                ReddotList.secondReedot1.addOne();
                break;
            case R.id.button_left_reduce:
                ReddotList.secondReedot1.reduceOne();
                break;
            case R.id.button_right_add:
                ReddotList.secondReedot2.addOne();
                break;
            case R.id.button_right_reduce:
                ReddotList.secondReedot2.reduceOne();
                break;
        }
    }
```
页面被销毁时候记得调用unRegister 或clearLinsenter 方法
```
 @Override
    protected void onDestroy() {
        super.onDestroy();
        ReddotManager.clearLinsenter(ReddotList.secondReedot1.getKey());
        ReddotManager.clearLinsenter(ReddotList.secondReedot2.getKey());
    }
```

demo 地址： [https://github.com/collinWong/AutoReddot](https://github.com/collinWong/AutoReddot)
