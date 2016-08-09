# Third week report

This week I began implementing the matrix operations that are needed for projections. This included initializing the random matrix
that is used projecting faces to lower dimensions as well as creating method for matrix multiplications. Right now it looks like
some part of the code will be O(n^3) although this part will be run only when new faces are added to the database. 
I think there isn't any easier or quickier way to multiply matrices so this is going to have to do.