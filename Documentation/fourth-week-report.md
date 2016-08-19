# Fourth week report

This week I started on the recognition part of the program. This is the part that needs to be as optimal as possible
because it's the most repeated part of the execution. It will implement the formula of [Euclidean distances](https://en.wikipedia.org/wiki/Euclidean_distance).
This way we can find the face vector in the database that is the closest match.

The method of closest match has proven to be quite accurate one especially in my own experiments with R. When a shortest distance is found
it gives us extra information about the probability of successful recognition: when the distance is larger than, say, 300, it is reasonable to
question the accuracy of the match. In this case it is possible that the database does not include any photos of the person.

The reading part of the code is very slow due to it's time complexity of O(n^3) and this is the part of the code we don't want to execute everytime
we want to recognise a face. So it is important to save the projected face matrices for further use. I'm not familiar with saving variables so this will
take some time to figure out.