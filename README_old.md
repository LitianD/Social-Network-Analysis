# Social-Network-Analysis

<!-- TOC -->

- [Social-Network-Analysis](#social-network-analysis)
- [1 背景分析](#1-背景分析)
- [2 模型假设](#2-模型假设)
- [3 符号说明](#3-符号说明)
- [4 模型建立](#4-模型建立)
    - [4.1 社交环节对照](#41-社交环节对照)
    - [4.2 定义阶段](#42-定义阶段)
    - [4.3 迭代竞争阶段](#43-迭代竞争阶段)
        - [4.3.1判断社交能力值](#431判断社交能力值)
        - [4.3.2是否发起社交](#432是否发起社交)
        - [4.3.3发起社交](#433发起社交)
        - [4.3.4资源数变化](#434资源数变化)
        - [4.3.5社交能力值变化](#435社交能力值变化)
- [5 仿真模拟设计](#5-仿真模拟设计)
    - [5.1 程序设计](#51-程序设计)
    - [5.2 实验分组设计](#52-实验分组设计)
    - [5.3结果分析设计](#53结果分析设计)
- [6 统计分析](#6-统计分析)
    - [6.1 图标统计结果](#61-图标统计结果)
    - [6.2 分组实验对比结果](#62-分组实验对比结果)
        - [6.2.1 个体数量影响](#621-个体数量影响)
        - [6.2.2 个体分布影响](#622-个体分布影响)
        - [6.2.3 资源分配方式影响](#623-资源分配方式影响)
    - [6.3 总体结论](#63-总体结论)
- [7 评价改进](#7-评价改进)
    - [7.1 模型优点](#71-模型优点)
    - [7.2 模型缺点](#72-模型缺点)
    - [7.3 模型改进](#73-模型改进)

<!-- /TOC -->

# 1 背景分析

A **social network** is a social structure made up of a set of social actors (such as individuals or organizations), sets of dyadic ties, and other social interactions between actors. In the context of networks, social capital exists where people have an advantage because of their location in a network. Contacts in a network provide information, opportunities and perspectives that can be beneficial to the central player in the network. Most social structures tend to be characterized by dense clusters of strong connections.

![](/img/1.jpg)

**社交网络分析**（SNA）是通过使用网络和图论来研究社会结构的过程。根据节点（网络中的个体角色，人或事物）以及连接它们的关系，边缘或链接（关系或交互）来表征网络结构。

本次试验中我们使用**元胞自动机**模型来对**社交网络**中任何人的关系进行**社交网络分析**，通过控制社交能力和社交资源两个变量，设计一系列社交规则，观察不同情况下社交网络达到稳态时的结果。

关键词： 社交网络（social network） 社交网络分析(social network analysis) 元胞自动机（cellular automata）

# 2 模型假设

+ 每一个社交个体只具有**社交能力**和**社交资源**两种属性
+ 封闭社交空间中总体资源总量不变
+ 社交个体只能与相邻的人发生交易
+ 资源总和大于一定成都后，社交个体必须生成新个体

# 3 符号说明

符号|说明
--|--
N|元胞网格单边步长|
R|总资源量，所有社交个体资源的总和
P|总社交个体数量
F(c)|不同社交能力个体的概率分布函数 默认为正态分布
r _j|个体 `j` 具有的资源总数量
c _j|个体 `j` 的社交能力值
t _j|第 `j` 轮迭代
Tj _nm|表示第`j`轮个体`n`和个体`m`交换情况


# 4 模型建立

## 4.1 社交环节对照

此阶段目的时将现实生活中的社交场景合理抽象为模型中的数学问题，形成对照，尽可能是试验结果满足真是的试验场景。

真实场景|模型抽象
--|--
现实生活中即使即使近邻也未必会有社交往来|两个近邻个体有一定概率p(c _i/c _i+ c _j)交换资源
现实生活中交往失败后通常会打击其自信心|尝试社交失败后社交能力会降低 c _i=c _i - x
现实生活中交往成功后后通常会增加其自信心|尝试社交成功后社交能力会增加 c _i=c _i + x
现实生活中资源缺乏时很难维持社交关系|社交资源 r _j=0时个体死亡，从网格中移除
现实生活中资源过多时可以产生新社交渠道|个体资源达到一定量时，且其周围有空余元胞，个体必须分裂出新个体

## 4.2 定义阶段

1. 社交能力值 `c _j`取值范围为 `0<=c _j<=10` 且为整数
2. 设定元胞网格总数量为`N*N`，社交个体总数量为`P`
3. 每一个元胞有且只能有个体,每个元胞内的个体有不同的社交能力值和资源数量
4. 不同社交能力值的个体分布满足`F(c)` 默认服从正态分布
5. 每个社交个体资源分配数量随机分配且满足 `sum(c _1+...c _P)=R`

![](/img/2.png)

## 4.3 迭代竞争阶段

迭代假设

每一轮迭代，遍历过程中的当前个体，社交能力值为c_i，资源数为r_i，该个体临近个体的社交能力值为c_j，资源数为r_j。

![](/img/3.png)

每一个迭代环节有5个环节，前提为两个 个体相邻，之后依此进行

1. 判断社交能力 
2. 是否发生社交 
3. 发生社交选择资源交换方向 
4. 资源数量变化 
5. 社交能力值变化，完成每个个体的社交状态。

### 4.3.1判断社交能力值

每轮迭代，依次遍历每一个个体，判断其周围所有临近个体的社交能力值大小。我们假设每一次社交均有社交值较高的个体发起，即当且仅当c_i >= c_j时发起社交。

### 4.3.2是否发起社交

对于每一个个体，即使c_i >= c_j，也不一定发起社交，我们假设高社交能力值的个体（c_i）发起社交的概率为c_i / (c_i + c_j)。

### 4.3.3发起社交

对于每一次社交行为，高社交能力值的个体并不总能获得资源数，这与现实中的社交行为相似。我们假设高社交能力值的个体（c_i）获得资源数的概率为c_i / (c_i + c_j)，失去资源数的概率c_j / (c_i + c_j)

### 4.3.4资源数变化

每次社交行为，发起社交的两个个体之间交换的资源数为c_i - c_j。

### 4.3.5社交能力值变化

每轮迭代结束，如果两个个体之间成功发生社交，则社交能力增加x，否则减少x。

# 5 仿真模拟设计

## 5.1 程序设计

为了验证模型，仿真社交网络环境结果，使用Java语言进行模拟仿真，并观察统计在不同条件下的模型统计结果。我们使用java图形界面对个体的资源与个体社交能力值进行可视化展示，并且记录每次迭代过程中个体的变化，输出为文件。

程序设计框架如下：

![](/img/6.png)

使用JFrame可视化框架，左图为个体资源，右图为个体的社交能力值，颜色浅表示数值低，反之表示数值高。

如下为初始情况下的个体分布：

![](/img/4.png)

如下为稳态情况下的个体分布：

![](/img/5.png)

同时保存每次迭代过程的数据文件：

【这里放一张文件保存格式的示意图，最好是每次迭代一个】
## 5.2 实验分组设计

分别在：初始化个体数量不同（网格大小50*50），初始化社交能力分布值不同，初始化资源分配方式不同。

共设计以下7组实验方法，输出并且保存统计数据

实验编号|初始化个体数量|初始化社交能力值分布|初始化资源分配方式
--|--|--|--
1|30*30|正态分布|平均分配
2|20*20|正态分布|平均分配
3|10*10|正态分布|平均分配
4|30*30|等量分布|平均分配 
5|30*30|社交能力值呈相关|平均分配
6|30*30|正态分布|与社交能力值正相关
7|30*30|正态分布|与社交能力值负相关

## 5.3结果分析设计

分别涉进行三组分析，并统计结果，得出结论。

分组编号|实验变量|实验定量|实验组合
--|--|--|--
分组一|个体数量|个体分布、资源分配方式|1、2、3
分组二|个体分布|个体数量、资源分配方式|1、4、5
分组三|资源分配方式|个体数量、个体分布|1、6、7

# 6 统计分析

##	6.1 图标统计结果

+ 随着时间变化各段能力值资源总和变化过程
+ 随着时间变化各段能力值个体数量变化过程
+ 随着时间变化生成新个体的数量
+ 随着时间变化生成死亡个体的数量

+ 整体中各个社交能力之发生分裂的比例
+ 整体中各个社交能力之发生死亡的比例

##  6.2 分组实验对比结果

### 6.2.1 个体数量影响

### 6.2.2 个体分布影响

### 6.2.3 资源分配方式影响

## 6.3 总体结论

# 7 评价改进

## 7.1 模型优点

+ 每个个体同时具有社交能力值和资源数两个属性，个体之间互动复杂程度更高，更接近真实社交。
+ 由社交能力值影响社交行为，个体之间的互动较为合理。
+ 以一定的概率发生社交和交换资源，可以有效防止贫富差距过大。
+ 个体可以分裂，增加了复杂程度，也一定程度上避免了贫富差距。

## 7.2 模型缺点

+ 只考虑了社交能力值一种影响社交行为的因素。
+ 分裂行为只由一个个体决定，与真实情况不符。
+ 社交能力值和资源数的改变不具有说服力。

## 7.3 模型改进

+ 适当增加影响社交行为的因素，比如个人情绪等，一定程度上影响社交行为。
+ 考虑仿真真实社会，由两个个体分裂出新个体。
+ 设计更严格的变化公式，以提高模型的仿真程度。