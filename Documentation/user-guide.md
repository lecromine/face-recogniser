## User guide

1. Go to [AT&T database of faces](http://www.cl.cam.ac.uk/research/dtg/attarchive/facedatabase.html), download the .zip file and extract it.

2. Insert the AT&T database location to the text field. This needs to be done every time you restart the program.
	- This program will create two .csv files in to the AT&T location: RandomMatrix.csv which is used in the projection process and 
ProjectedFaceMatrix.csv which contains all the projected face matrices in a table.

3. Choose any file in .pgm format and press Recognise.
	- If you want to test the program with the AT&T faces, move a face file from the database to a different folder, remove ProjectedFaceMatrix.csv
and RandomMatrix.csv, enter the AT&T database location again and rerun the program.
	- There are multiple sites on internet you can use to convert .jpg files to .pgm. [Google search: convert jpg to pgm](https://www.google.fi/webhp?sourceid=chrome-instant&ion=1&espv=2&ie=UTF-8#q=convert%20jpg%20to%20pgm)
