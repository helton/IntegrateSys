@echo off

echo Gerando pasta bin...

cd "%~dp0"
IF EXIST bin (
    rmdir bin /s /q
)
mkdir bin
mkdir "bin\32 bits"
mkdir "bin\64 bits"
copy /y "Java\IntegrateSys\target\IntegrateSys-1.0-SNAPSHOT-jar-with-dependencies.jar" "bin\32 bits\IntegrateSys.jar"
copy /y "Java\IntegrateSys\target\IntegrateSys-1.0-SNAPSHOT-jar-with-dependencies.jar" "bin\64 bits\IntegrateSys.jar"
copy /y "C++\IntegrateSysLib\Release\IntegrateSysLib.dll" "bin\32 bits\IntegrateSysLib.dll"
copy /y "C++\IntegrateSysLib\x64\Release\IntegrateSysLib.dll" "bin\64 bits\IntegrateSysLib.dll"

echo Processo concluido.
pause

@echo on