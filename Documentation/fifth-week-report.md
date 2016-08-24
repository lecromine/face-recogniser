## Fifth week report

This week I implemented the closest match recognition process and it seems to be working reasonably well. So the core of the program is done and
what is left to do is some cleaning - the code is really messy and hard to read right now. Right now the program only uses AT&T database, but I will
look for some other face databases and add them.

At this point the slowest part of the code is loading the .csv files and writing them into a 2D arrays. At first I was going to go with .txt format
but as it turned out it was probably one of the slowest way to save 2D arrays. With .csv I managed to cut the size of the files to a half and the
reading time became drastically faster, too.

The UI is under progress. In the final version it will show the matching face and some information about the person. And I should start testing the code...
like three weeks ago... 