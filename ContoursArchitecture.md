# Contours Architecture #

The Countours architecture is divided into several stages. As a visual guide take the following graph:

![http://contours.googlecode.com/svn/trunk/contours/website/ContoursArchitecture.png](http://contours.googlecode.com/svn/trunk/contours/website/ContoursArchitecture.png)

The yellow parts are the parts which build the foundation of the Contours framework:
  * The **Contours Grammar** defines the syntax of the custom File Definition. This grammar is taken by the **SableCC** parser generator and creates a parser for the Contours File Definition syntax.
  * The **Logic** is the bunch of code which is attached to the SableCC-generated parser and is responsible for generating a reader for the type of file defined by the user-provided **File Definition**.

The dark blue part is the user-provided part:
  * The **File Definition** contains a description for a specific file format and is provided by the user. The Contours parser takes this definition and generates the **Reader** for these types of file and also the required class-structure for the handling of the read data later on in the users application.

The green parts are the ones generated with a specific **File Definition**:
  * The **Reader** is responsible for reading a **Binary File**, analyzing its structure according to the definition and filling the data into the **Data Structure**.
  * The **Data Structure** is composed by hirarchical organized Java classes which build the storage container for the read data from the file. This structure is then available for further processing of the user application.