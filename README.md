# SFCalc 粘液计算器

粘液科技计算器，基于john000708的SlimeCalculator

## 下载

**自动更新**: 汉化版构建#1及以后版本包含自动更新功能，默认开启。

点击这里下载 SFCalc: [下载 SFCalc](https://builds.guizhanss.net/ybw0014/SFCalc/master)

<p align="center">
  <a href="https://github.com/ybw0014/SFCalc/actions/workflows/maven.yml">
    <img src="https://github.com/ybw0014/SFCalc/actions/workflows/maven.yml/badge.svg" alt="Java CI"/>
  </a>
  <a href="https://builds.guizhanss.net/ybw0014/SFCalc/master">
    <img src="https://builds.guizhanss.net/f/ybw0014/SFCalc/master/badge.svg" alt="Build status"/>
  </a>
</p>

## 使用

通过该指令来使用: /sfcalc. 该附属可以计算制造目标物品需要多少基础资源。

用法: `/sfcalc calc [目标物品ID] [数量]`

目标物品是物品的ID（目前没有对照表，只能在游戏中手持目标物品使用/sf id查看，或翻阅源代码查看）

数量是可选的，如果你不指定数量，那么默认数量为1个。

使用 `/sfcalc needed` 来代替 `/sfcalc calc`，可以显示还需要多少物品，会根据物品栏中的已有物品计算

Minecraft 版本: 1.15-1.18

Slimefun 版本: RC-27 以上
