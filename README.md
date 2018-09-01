# Javafaces
An adaptation of the "Javafaces" code at code.google.com/p/javafaces


–––––Summary–––––

This is different from the original code in that the original compared a probe to other gallery images and found the single best match, this program sorts the gallery images by name and then finds the group of gallery images most like the probe image. The original program is also here for comparison and called "FaceRecFirst," while the modified version is called "Facerec."


–––––Configuration–––––

To make this run more smoothly, you should first go to FaceRecView and enter the paths to the directories you want to use for your images, so you don't have to spend forever looking through your files in order to select images. You should also go to CorrectCounter and enter the paths for you gallery folder and your probes folder at the specified locations. 


–––––Running–––––

To run the modified code, go to FaceRecMain and click run. To run the original, run FaceRecMainFirst. There is a class called CorrectCounter, which will run the program once for each face in a given probes folder using only one eigenface, then again using two eigenfaces, and so on up to a specified number. Between iterations at different numbers, the program will return the accuracy for the run and the change in accuracy since the previous run. This can be used to compare the original code to the new code by changing "frec" to be either a FaceRec or a FaceRecFirst.


–––––Adding More Images–––––

If you want to add more images to either your gallery or probes folder, say, of yourself and friends, be sure that the images are: 125x150, grayscale, have no alpha channel, and are titled with a name followed by a number (ex: JohnSmith3).
