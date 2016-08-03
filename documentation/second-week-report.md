# Second week report

This week I began with some research how I could make the program as fast as possible.
I already, as said last week, have an O(n^2) solution in mind, which would be
kind of straightforward to create.

The problem with this concept is that every time I want to recognise a face, I need
to find the shortest Euclidean distance. It can be resolved with the formula of 
Euclidean distance. After random projection operation this is much faster than
it is with the original face vectors, but the solution is still too slow.

So how can we know the distance before resolving it? We need this information
to construct the graph that can be used afterwards as a quicker way to find the 
nearest match for faces. In the graph the faces are already sorted in a way
so that there's no need to check every face every time. I'm starting to think
that this problem might be too extensive to be solved in just one month.