@ECHO off
IF NOT EXIST %1.java GOTO NOEXISTEjava

javac %1.java

IF NOT EXIST %1.class GOTO NOEXISTEclass
javah %1

IF NOT EXIST %1.h GOTO NOEXISTEh
java %1
GOTO TERMINAR

:NOEXISTEjava
ECHO.
ECHO El archivo %1.java no existe
GOTO TERMINAR

:NOEXISTEclass
ECHO.
ECHO El archivo %1.class no se pudo crear
GOTO TERMINAR

:NOEXISTEh
ECHO.
ECHO El archivo %1.h no se pudo crear
GOTO TERMINAR

:TERMINAR
ECHO ======= Fin de la ejecicion =======
ECHO.

