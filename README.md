# Boat Club

## Class diagram:
<img src="/img/Class.png" width="800"> <br>

Its a little bit cluttery and hard to understand, but i made sure to add all functions and variables in this class diagram. The only exception is MainHandler, it has many functions shared with BoatInterface, but rather makes the choices and navigates through the list, whilst BoatInterface prints to console and checks for input integer (which is the choice).

Also, Filewriter needs to have access to Member and Boat to be able to reconstruct(load) and construct (save) from/to a file.

The brains are in the MainHandler, but some responsibility is also given to the other classes, and all values are held in other classes (excluding the arraylist of members.)

One flaw that is in the code is that MainHandler has one function that really should be in BoatInterface, its called addBoat(). The reason i could not make it so is because addBoat can return 4 different classes, and you can't really do that. Could probably make four functions, but this is still working just misplaced maybe.

Also, as you can see, boats have subclasses, and subsubclasses, and subsubsubclasses. This is because many boats share the same variables, so its better to extend on what already exists.