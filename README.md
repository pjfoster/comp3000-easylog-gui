# comp3000-easylog-gui
EasyLog is a user-friendly GUI that uses Apache Lucene to efficiency display, filter, and highlight operating system logs on OSX. Users can make composite boolean queries including substring searches, regex searches, and fuzzy searches. Created for COMP3000: Operating Systems. 

Created by: Patricia Foster

Last Updated: December 2016

--- INTRODUCTION

At present, most tools for viewing operating system logs are either too simple to be useful or too advanced to be user-friendly. Furthermore, logs are stored in multiple locations and formats. EasyLog addresses both these problems by combining the power of Apache Lucene's search capabilities with an intuitive GUI accessible to everyday users. 

--- IMPLEMENTATION DETAILS

The project is built using the MVC architecture. The view is designed with the mediator pattern and written with JavaFX. The code was tested for efficiency, accuracy (using JUnit), and accessibility (through a usability questionnaire). 

The GUI allows users to make composite boolean queries with up to three keywords, where each keyword can be a string search, a substring search, a RegEx search, or a fuzzy search. 

A special LogParser class was created to deal with the variations in the formatting of log files. The class's job is to seperate log files into invidiual entries based on the presence of timestamps.   

The detailed project report is included in the Documentation folder.
