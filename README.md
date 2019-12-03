# CycleViewPager2
## Introduction

使用 ViewPager2 实现无限轮播效果，可以用来实现 banner。

## Screenshots

![cycle-MultiplePagerScaleIn](assets/cycle.gif)

## Features

* 支持无限自动轮播
* 支持一屏显示 3 个 Item 的切换效果

## Getting started

在项目的根节点的 `build.gradle` 中添加如下代码
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

在项目的 `build.gradle` 中添加
```
dependencies {
    implementation 'com.github.wangpeiyuan:CycleViewPager2:v1.0.0'
}
```

**注意：还需要自行添加 ViewPager2 的依赖**。如：
```
dependencies {
    implementation "androidx.viewpager2:viewpager2:1.0.0-beta04"
}
``` 

ViewPager2 最新版本请点击[**此处**](https://developer.android.com/jetpack/androidx/releases/viewpager2)

## Usage

//todo

## Todo

- [ ] 增加 Indicator
- [ ] 增加其他切换效果

## License

Apache License, Version 2.0