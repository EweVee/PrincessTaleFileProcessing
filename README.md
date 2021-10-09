# PrincessTaleFileProcessing
This project is for parsing files extracted from the Princess Tale mobile game. The purpose is to combine data and export it in a format that is easy for community consumption.

## Usage

### Extracting Data (Android)
Use whatever files browser you have on your mobile device and go to the PT application directory
* Internal storage/Android/data/com.gamepub.pt/files/

Look in the Balance/ and Localize/ directories for files useful for this project. When you import the files, make sure to attach timestamps in the file name for future reference.

### Parsing
The code is split into the types of information that is present in Princess Tales. The data folders contain just the files extracted from the game and a bean that parses it. The way that these files are organize do change over time as gamepub develops the game, so there are timestamped parsers.

### Executing
Write tests and execute them in your favorite IDE to see what data exists and how to use them.

#### ImageProcessorTest
ImageProcessorTest.java has a single test that can be run to parse dungeon data and export the results as png.

