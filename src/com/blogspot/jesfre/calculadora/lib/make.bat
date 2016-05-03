@ECHO off
IF NOT EXIST %1.asm GOTO NOEXISTE


ml /c /coff /Cp %1.asm
Link /DLL /NOENTRY /DEF:%1.def /subsystem:windows %1.obj
GOTO TERMINAR

:NOEXISTE
ECHO.
ECHO El archivo %1.asm no existe

:TERMINAR
ECHO _

