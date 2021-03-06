*This project was created in support of 'Automatic Scaling of Cloud-Based Web Applications,' a research paper I produced during my senior year at Brown University. Many thanks to [Rodrigo Fonseca](http://cs.brown.edu/~rfonseca/) for his guidance and support during the semester. You can download a copy of the paper [here](https://github.com/walterblaurock/cluster-monitor/blob/master/Automatic%20Scaling%20of%20Cloud-Based%20Web%20Applications.pdf?raw=true).*

## ClusterMonitor

There exist many cloud-computing platforms, such as Amazon’s EC2, which allow the developer to quickly add and remove server instances on an hourly basis. Despite this capability, there is still no widespread solution for automating the scaling process, instead requiring periodic monitoring and human intervention to add or remove instances. I propose a solution to respond to an increase or decrease in traffic through the use of user-configurable rules to avoid the need for human intervention beyond the initial setup. The result is the potential for significant savings, both in time and money, for the developer.

## Usage

In order to take advantage of ClusterMonitor, the developer must implement three basic things. First is an interface between ClusterMonitor and the cloud computing provider. This is necessary to allow ClusterMonitor to add instances to and remove instances from the cluster. Second is an interface between ClusterMonitor and the individual instances. This is necessary to allow ClusterMonitor to get system performance metrics. Finally, a set of rules must be input that determine what conditions must be met for ClusterMonitor to add and remove instances from the cluster.
