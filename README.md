# Social-Network-Analysis

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

### 迭代假设
每一轮迭代，遍历过程中的当前个体，社交能力值为c_i，资源数为r_i，该个体临近个体的社交能力值为c_j，资源数为r_j。

每轮迭代都有以下几个过程

### 判断社交能力值
每轮迭代，依次遍历每一个个体，判断其周围所有临近个体的社交能力值大小。我们假设每一次社交均有社交值较高的个体发起，即当且仅当c_i >= c_j时发起社交。

### 是否发起社交
对于每一个个体，即使c_i >= c_j，也不一定发起社交，我们假设高社交能力值的个体（c_i）发起社交的概率为c_i / (c_i + c_j)。

### 发起社交
对于每一次社交行为，高社交能力值的个体并不总能获得资源数，这与现实中的社交行为相似。我们假设高社交能力值的个体（c_i）获得资源数的概率为c_i / (c_i + c_j)，失去资源数的概率c_j / (c_i + c_j)

### 资源数变化
每次社交行为，发起社交的两个个体之间交换的资源数为c_i - c_j。

### 社交能力值变化
每轮迭代结束，如果两个个体之间成功发生社交，则社交能力增加x，否则减少x。

![](/img/3.png)


# 5 分析统计

# 6 评价改进