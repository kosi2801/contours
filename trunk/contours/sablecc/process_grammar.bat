rem * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
rem * This file is part of SableCC.                             *
rem * See the file "LICENSE" for copyright information and the  *
rem * terms and conditions for copying, distribution and        *
rem * modification of SableCC.                                  *
rem * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
rem
rem IMPORTANT: Put the absolute path to sablecc.jar below.
rem

rmdir /s /q ..\grammar\at\
java -jar .\sablecc.jar ..\grammar\dfp.g
