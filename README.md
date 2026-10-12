## Java Text and DOCX file analyzer
Simple java program which reads and analyzes the data from a text file and a DOCX file. Uses Apache POI for DOCX file operations. 
### Instructions
Open the Maven project in the terminal (the folder containing the `pom.xml` file), and run the following command: `mvn compile exec:java -Dexec.mainClass=Main`
### Example Program Output
<img width="456" height="463" alt="program-execution" src="https://github.com/user-attachments/assets/2938197a-d219-46d8-9702-55d29f363b3f" />    

### Reflection
When running this program, both files have the same word, line, and character counts, however the DOCX file is larger in terms of file size. 
This is because the TXT file is stored in simple plain text while the DOCX file special formatting information that takes up additional space.
