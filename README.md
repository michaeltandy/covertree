Cover Tree
==========

Java implementation of a cover tree for clustering on large data sets.

This class provides a Java version of the cover tree nearest neighbor algorithm. It is based on Thomas Kollar's version of ["Cover Trees for Nearest Neighbor" by Beygelzimer, Kakade, Langford](https://homes.cs.washington.edu/~sham/papers/ml/cover_tree.pdf).

Nils Loehndorf extended the original algorithm is towards selecting K centers which are maximally different from one another from an online sample.

Michael Tandy changed some odds and ends - look at the commit history for the gory details.

Copyright (c) 2013, Nils Loehndorf. Portions copyright (c) 2015, Michael Tandy.
 
The software is provided 'as-is', without any express or implied warranty. In no event will the authors be held liable for any damages arising from the use of this software. Permission is granted to anyone to use this software for any purpose, including commercial applications, and to alter it and redistribute it freely.

[![Build Status](https://travis-ci.org/michaeltandy/covertree.svg?branch=master)](https://travis-ci.org/michaeltandy/covertree)
