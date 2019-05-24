# Social-Network-Analysis

<!-- TOC -->

- [Social-Network-Analysis](#social-network-analysis)
- [1 Background Analysis](#1-background-analysis)
            - [Keywords: Social Network、 Social Network Analysis、 Cellular Automata](#keywords-social-network-social-network-analysis-cellular-automata)
- [2 Model Hypothesis](#2-model-hypothesis)
- [3 Symbol Description](#3-symbol-description)
- [4 Model Building](#4-model-building)
    - [4.1 Social Link Control](#41-social-link-control)
    - [4.2 Definition Stage](#42-definition-stage)
    - [4.3 Iterative competition phase](#43-iterative-competition-phase)
        - [4.3.1 Judging social ability value](#431-judging-social-ability-value)
        - [4.3.2 Whether to start social](#432-whether-to-start-social)
        - [4.3.3 Starting social](#433-starting-social)
        - [4.3.4 Changes in the number of resources](#434-changes-in-the-number-of-resources)
        - [4.3.5 Change in social ability value](#435-change-in-social-ability-value)
- [5 Simulation and Simulation Design](#5-simulation-and-simulation-design)
    - [5.1 Programming](#51-programming)
    - [5.2 Experimental group design](#52-experimental-group-design)
    - [5.3 Results Analysis Design](#53-results-analysis-design)
- [6 Statistic Analysis](#6-statistic-analysis)
    - [6.1 Icon statistics](#61-icon-statistics)
    - [6.2 Group experiment comparison results](#62-group-experiment-comparison-results)
- [7 Evaluation improvement](#7-evaluation-improvement)
    - [7.1 Model advantage](#71-model-advantage)
    - [7.2 Model disadvantage](#72-model-disadvantage)
    - [7.3 Model improvement](#73-model-improvement)

<!-- /TOC -->

# 1 Background Analysis
A **social network** is a social structure made up of a set of social actors (such as individuals or organizations), sets of dyadic ties, and other social interactions between actors. In the context of networks, social capital exists where people have an advantage because of their location in a network. Contacts in a network provide information, opportunities and perspectives that can be beneficial to the central player in the network. Most social structures tend to be characterized by dense clusters of strong connections.

![](/img/1.jpg)

**Social Network Analysis** (SNA) is the process of studying social structure through the use of networks and graph theory. The network structure is characterized by nodes (individual roles in the network, people or things) and the relationships that connect them, edges or links (relationships or interactions).

In this experiment, we used the **cellular automaton model to perform a social network analysis on the relationship of anyone in the social network**, and designed one by controlling two variables of social competence and social resources. A series of social rules that look at the results of social networks reaching steady state in different situations.

#### Keywords: Social Network、 Social Network Analysis、 Cellular Automata

# 2 Model Hypothesis

+ Each social entity has only **social ability** and **social resources**
+ The total amount of resources in the closed social space remains unchanged
+ Social individuals can only trade with neighbors
+ After the sum of resources is greater than a certain Chengdu, social individuals must generate new individuals

# 3 Symbol Description

Symbol|description
--|--
N|cell grid unilateral step size|
R|Total resources, the sum of all social individual resources
P|The total number of social individuals
F(c)|Probability distribution function of individuals with different social abilities. The default is normal distribution.
r _j|The total number of resources owned by the individual `j`
c _j|The social ability value of the individual `j`
t _j| the `j` round iteration
Tj _nm| indicates the exchange of the `j` round of individual `n` and individual `m`

# 4 Model Building

## 4.1 Social Link Control

At this stage, the social scenes in real life are reasonably abstracted into mathematical problems in the model, and a comparison is made. As far as possible, the test results satisfy the true experimental scene. In a real social environment, everyone wants to benefit from socializing. Whether or not you can survive on a social field depends on two attributes: **social ability** and **social resource**.

**Social Resources**: Social resources represent the currency, information, or other things that this individual has that can be used to exchange with others on the social field. The social resources of an individual are constantly changing, and the amount of each increase or decrease is derived from the difference in the ability to exchange the two. In this system, resources can be created and not conserved.

**Social Ability**: Social ability reflects the ability of a person to exchange resources for social behavior. The stronger the ability, the more likely it is to obtain resources; the weaker the ability, the more likely the resources are plundered. Social ability is likely to increase in one-time social behavior and may weaken.

Real scene | model abstraction
--|--
In real life, even if the neighbors do not necessarily have social interactions | two neighbors have a certain probability p(c _i/c _i+ c _j) exchange resources
In real life, after the failure of communication, it usually hits its self-confidence.| After trying social failure, the social ability will decrease. c _i=c _i - x
In real life, after the successful communication, it usually increases its self-confidence. |After trying to succeed, the social ability will increase c _i=c _i + x
It's hard to maintain social relationships when resources are scarce in real life | Social resources r _j=0 when individuals die, remove from the grid
When there are too many resources in real life, new social channels can be generated.| When individual resources reach a certain amount, there are empty cells around them, and individuals must split new individuals.

## 4.2 Definition Stage

1. The social ability value `c _j` has a value range of `0<=c _j<=10` and is an integer
2. Set the total number of cell grids to `N*N`, and the total number of social individuals is `P`
3. Each cell has and can only have individuals, and individuals within each cell have different social ability values and resource numbers.
4. Individual distribution of different social ability values satisfies `F(c)` default compliance with normal distribution
5. The number of each social individual resource allocation is randomly assigned and satisfies `sum(c _1+...c _P)=R`

![](/img/2.png)

## 4.3 Iterative competition phase

Iterative hypothesis

Each iteration, the current individual in the traversal process, the social ability value is c_i, the number of resources is r_i, the social ability value of the individual adjacent to the individual is c_j, and the number of resources is r_j.

![](/img/3.png)

There are 5 links in each iteration, provided that two individuals are adjacent, and then proceed accordingly.

1. Judging social skills
2. Is there socialization?
3. Social selection resource exchange direction occurs
4. Changes in the amount of resources
5. Change the social ability value to complete the social status of each individual.

### 4.3.1 Judging social ability value

In real life, people with high social abilities take the initiative in the initiation of social relationships. Therefore, at the beginning of each iteration, each individual is traversed in turn, and the social ability value of all neighboring individuals around it is judged. We assume that each social interaction is initiated by individuals with higher social values. For example, the current cell is c_i and c_j is a neighbor of c_i. C_j is ignored when c_i < c_j, and c_j is called a social candidate of c_i when c_i >= c_j.

![](/img/9.png)

### 4.3.2 Whether to start social

In this step, the individual initiates a social behavior to the surrounding social candidate. But everything is accidental. People with high social skills don't necessarily socialize with everyone around you. For an individual c_i, even if c_i >= c_j, it does not necessarily socialize with c_j. We assume that the probability that this high social ability value individual (c_i) will socialize to a social candidate (c_j) is c_i / (c_i + c_j).

![](/img/10.png)

### 4.3.3 Starting social

Social interaction in reality is essentially the exchange of resources. For each social act, one party loses resources and the other gets resources. Individuals with high social ability values ​​do not always have access to resources, which is similar to social behavior in reality. We assume that the probability that the individual with high social ability value (c_i) obtains the number of resources is c_i / (c_i + c_j), and the probability of losing the number of resources c_j / (c_i + c_j).

### 4.3.4 Changes in the number of resources

For each social behavior, the number of resources exchanged between the two individuals who initiated the socialization is |c_i - c_j|. In other words, people with strong social skills will be more likely to get resources from social behavior. The more disparity between the social skills of the two sides, the more resources are acquired or lost.

![](/img/7.png)

When the number of resources of an individual after the change in energy resources is zero, the individual will die and no longer participate in social behavior.

### 4.3.5 Change in social ability value

In the display society, each social behavior can bring success, positive social experience, and enhance self-confidence in the social process. A failed society can leave a shadow on people's minds and make people fear social. At the end of each iteration, if the social interaction between the two individuals is successful, the social ability is increased by x, otherwise the step is directly performed after judging that the social is initiated: both social skills are weakened by x.

![](/img/8.png)

# 5 Simulation and Simulation Design

## 5.1 Programming

In order to validate the model, simulate the results of the social network environment, use the Java language for simulation, and observe the statistical results of the model under different conditions. We use the java graphical interface to visualize the individual's resources and individual social ability values, and record the individual changes in each iteration, and output them as files.

The programming framework is as follows:

![](/img/6.png)

Using the JFrame visualization framework, the left image is the individual resource, the right image is the individual's social ability value, the light color indicates that the value is low, and vice versa indicates that the value is high.

The following is the individual distribution in the initial situation:

![](/img/4.png)

The following is the individual distribution under steady state conditions:

![](/img/5.png)

Also save the data file for each iteration:


## 5.2 Experimental group design

The number of initial individuals is different (grid size 50*50), the initial social capacity distribution value is different, and the initial resource allocation mode is different.

The following 7 sets of experimental methods are designed to output and save statistics.

Experiment number|initialize the number of individuals|initialize social capability value distribution|initialize resource allocation method
--|--|--|--
1|30*30|Normal distribution|Average distribution
2|20*20|Normal distribution|Average distribution
3|10*10|normal distribution|average distribution
4|30*30|Isocent distribution|Average distribution
5|30*30|Social ability value is relevant|Average distribution
6|30*30|Normal distribution|positive correlation with social ability value
7|30*30|Normal distribution|Negative correlation with social ability values

## 5.3 Results Analysis Design

Three groups of analyses were conducted separately, and the results were statistically concluded.

Group number|experimental variable|experimental quantification|experimental combination
--|--|--|--
Group one|Number of individuals|individual distribution, resource allocation method|1, 2, 3
Group two | Individual Distribution | Individual Quantity, Resource Allocation Method | 1, 4, 5
Group three|Resource allocation method|number of individuals, individual distribution|1,6,7

# 6 Statistic Analysis

##	6.1 Icon statistics

如下是实验一的统计分析过程

实验编号|初始化个体数量|初始化社交能力值分布|初始化资源分配方式
--|--|--|--
1|30*30|正态分布|平均分配

程序运行结果结束后对数据进行分析统计，从6个维度分析：

+ 随着时间变化各段能力值资源总和变化过程

![](/img/11.png)

从第一张图可以看出，在初始情况时，由于能力值服从正态分布，资源拥有量在45到55之间的人数最多，小于45的人数与大于5的人数次之。随着迭代次数的增加，能力值在55以上的人群渐渐掌控了大多数的资源，并且在之后的第60到80轮迭代中就达到稳定。而对于55以下的人群他们掌控着约占整体十分之一的资源，并且在第60轮到100轮进行不断内耗。

+ 随着时间变化各段能力值个体数量变化过程

![](/img/12.png)

从第二张图可以看出，初始情况下，个体按照能力值呈现正态分布。在多次迭代中，能力值在55以上的个体稳步增加，而其他两类的人数渐渐减少至占总量十分之一的规模，且总量也在不断减少。体在进行社交一段时间后，大多数人的社交能力都得到了提高，且能力低的人遭到淘汰，体现了社会种群的进化与优胜劣汰。

+ 随着时间变化生成新个体的数量

![](/img/13.png)

从第三张图可以看出，死亡个体在前30轮迭代中比较多，在这个过程中，能力值在55以上的死亡个体数量逐步上升，而虽然45至55的死亡个体数量在第十轮迭代是还与高能力值的死亡个体数量差不多，但是在20个迭代后迅速下降至与低能力值新个体数量相同的水平。

+ 随着时间变化生成死亡个体的数量

![](/img/14.png)

从第四张图可以看出，在10轮迭代之前，中，低能力值者的新个体数量都比较多，但是在30轮迭代之后，迅速下降到比较低的水平。而高能力者的新生个体数量则一直维持在较高的水平。结合上一张的死亡个体数量图，可以看到随着时间推移高能力者的数量渐渐增加，与图二的柱形图趋势一致。

+ 整体中各个社交能力之发生分裂的比例和死亡的比例

![](/img/15.png)

从分裂图中可以看到，与我们设立的条件一致，能力值在30以上的人群更容易分裂新个体，而能力值越低的个体，越不容易分裂新个体。能力值与新生个体数呈正相关。这体现了能力值低的人很难实现资源的积累，而能力值越高，就容易积累资源。

从死亡比率图中我们可以看到，只是能力值在50以上的个体死亡数就占了总数的71%，死亡数与能力值呈正相关。死亡率与新生率相互依存，使系统处在不断变化的状态中。表明在社交过程中风险与机会并存。

##  6.2 Group experiment comparison results

+ 个体数量影响

在不同个体数量影响下，结果相差不大.根据实验数据，初始的个体数量与资源总量和各个能力值群体的数量呈正相关，但是对资源和人口数量的变化趋势，新生率，死亡率并没有影响。

+ 个体分布影响

根据实验数据，在不同分布下，每个指标的前期迭代情况差别较大。均值分布情况下的曲线变化更加平滑，且基本上都是单调增长。变化过程体现出了明显的方向性。在与社交能力值呈正相关的情况下，曲线以极快的速度趋于平原然后在小范围内波动。增加高能力者的比例使得进化过程加速了。可以看到，无论个体服从什么样的分布，只要规律不变，最后整个社交场的演进方向是相同的，即多数进化，少数劣汰。

+ 资源分配方式影响

在负相关分布中，大量存在着两种与情况1不同的极端属性个体，即高能力低资源型个体与低能力高资源型个体。可以预见的是，由于发生社交行为时，资源的掠夺与给予只取决于能力，与资源无关，所以与实验数据所表现出来的相同：即使在初始迭代中出现了高能力个体与低能力个体的数量，持有资源，死亡数和新生数的交叉，但是在50轮的迭代之后，结果仍与情况1的变化趋势相同。在实验7中，资源与能力分布的正相关加剧了这一过程，使各曲线很快地收敛至稳态。可以看到，资源分布只影响系统达到稳态的时间，不影响整个系统的进化方向。

# 7 Evaluation improvement

## 7.1 Model advantage

+ 每个个体同时具有社交能力值和资源数两个属性，个体之间互动复杂程度更高，更接近真实社交。
+ 由社交能力值影响社交行为，个体之间的互动较为合理。
+ 以一定的概率发生社交和交换资源，可以有效防止贫富差距过大。
+ 个体可以分裂，增加了复杂程度，也一定程度上避免了贫富差距。

## 7.2 Model disadvantage

+ 只考虑了社交能力值一种影响社交行为的因素。
+ 分裂行为只由一个个体决定，与真实情况不符。
+ 社交能力值和资源数的改变不具有说服力。

## 7.3 Model improvement

+ 适当增加影响社交行为的因素，比如个人情绪等，一定程度上影响社交行为。
+ 考虑仿真真实社会，由两个个体分裂出新个体。
+ 设计更严格的变化公式，以提高模型的仿真程度。


