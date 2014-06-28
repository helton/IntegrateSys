@echo off

SET VS_PATH=C:\Program Files (x86)\Microsoft Visual Studio 11.0\VC\bin
SET DUMPBIN=%VS_PATH%\dumpbin.exe
SET VCVARS32=%VS_PATH%\vcvars32.bat
SET DLL_PATH=IntegrateSysLib.dll

call "%VCVARS32%"
"%DUMPBIN%" /headers %DLL_PATH%
pause

@echo on