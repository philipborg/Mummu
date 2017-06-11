//No appending, meaning if file exists it will remove the old file
TEXTIO.saveText("My very own text file", "filename.txt", CHARSETS.UTF_8(), false);
//Appending a new line. TEXTIO.ls() is a system dependent line seperator
TEXTIO.saveText(TEXTIO.ls() + "My appended line", "filename.txt", CHARSETS.UTF_8(), true);