# Emprical-Study
Emprical Study of Network Flow Algorithms
Implemetation of Network Flow Algorithms without using any dedicated libraries.
Majorly focusing on Push Relabel as it seems to be optimizable in several ways.

A generic set of classes and objects are used for the nodes and edges as well as methods for their respective properties
The Base code can be dound in the folder GraphCode.

The graph format currently used is 
"v1 v2 C": indicating a forward edge 'v1->v2', having a capacity as 'C'
The algorithm, intentionally, assumes 't' to be the last entry as a vertex in the input file.
Also as max flow is calculated on a directed graph, try to avoid a double edged pair of ndoes.
