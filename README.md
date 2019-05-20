# Social-Network-Analysis

# 背景分析

A **social network** is a social structure made up of a set of social actors (such as individuals or organizations), sets of dyadic ties, and other social interactions between actors. In the context of networks, social capital exists where people have an advantage because of their location in a network. Contacts in a network provide information, opportunities and perspectives that can be beneficial to the central player in the network. Most social structures tend to be characterized by dense clusters of strong connections.

![](/img/1.jpg)

**社交网络分析**（SNA）是通过使用网络和图论来研究社会结构的过程。根据节点（网络中的个体角色，人或事物）以及连接它们的关系，边缘或链接（关系或交互）来表征网络结构。

本次试验中我们使用**元胞自动机**模型来对**社交网络**中任何人的关系进行**社交网络分析**，通过控制社交能力和社交资源两个变量，设计一系列社交规则，观察不同情况下社交网络达到稳态时的结果。

关键词： 社交网络（social network） 社交网络分析(social network analysis) 元胞自动机（cellular automata）

# 模型假设

+ 每一个社交个体只具有**社交能力**和**社交资源**两种属性
+ 封闭社交空间中总体资源总量不变
+ 社交个体只能与相邻的人发生交易
+ 资源总和大于一定成都后，社交个体必须生成新个体

# 符号说明


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


# 模型建立

## 社交环节对照


## 定义阶段

1. 社交能力值 `c _j`取值范围为 `0<=c _j<=10` 且为整数
2. 设定元胞网格总数量为`N*N`，社交个体总数量为`P`
3. 每一个元胞有且只能有个体,每个元胞内的个体有不同的社交能力值和资源数量
4. 不同社交能力值的个体分布满足`F(c)` 默认服从正态分布
5. 每个社交个体资源分配数量随机分配且满足 `sum(c _1+...c _P)=R`

![](/img/2.png)

## 迭代竞争阶段



# 检验求解

# 评价改进