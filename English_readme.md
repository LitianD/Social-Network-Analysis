# Social-Network-Analysis

<!-- TOC -->

- [Social-Network-Analysis](#social-network-analysis)
- [1 Background Analysis](#1-background-analysis)
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

