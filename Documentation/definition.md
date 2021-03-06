﻿# Definition

My goal in this program is to have a time complexity of O(n log n). It's important to find effective solution for this 
kind of problem because face databases usually include large amounts of data. O(n^2) kind of solutions usually work
for smaller collections which is not practical, because the more there are faces in a database, the more accurate the
match is.

This program will use graphs: when we have a large collection of different faces and each face has a
Long as it's representative, we don't need to go through the whole collection when we try to find the
nearest match for the face we want to recognise. In my earlier implementation of this problem
the time complexity was O(n^2) which is not very good solution.

At first the program is given n faces of k people, and it will find the Long representation for all the faces.
When new faces are added it will not affect the faces that were added before, so everything goes fast and smooth. 
When an user has a headshot of the person she wants to identify she uploads the photo and runs the program which
then looks for the nearest match for the uploaded photo.

### References:
* [Satunnaisprojektiomenetelmä kasvojentunnistuksessa.](https://www.dropbox.com/s/hcmxbqr4y076cvl/RPFR2016.pdf?dl=0) (2016) Maria Yli-Luukko


