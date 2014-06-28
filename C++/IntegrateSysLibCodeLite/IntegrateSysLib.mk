##
## Auto Generated makefile by CodeLite IDE
## any manual changes will be erased      
##
## Release
ProjectName            :=IntegrateSysLib
ConfigurationName      :=Release
WorkspacePath          := "C:\IntegrateSys\C++\IntegrateSysLibCodeLite"
ProjectPath            := "C:\IntegrateSys\C++\IntegrateSysLibCodeLite"
IntermediateDirectory  :=./Release
OutDir                 := $(IntermediateDirectory)
CurrentFileName        :=
CurrentFilePath        :=
CurrentFileFullPath    :=
User                   :=Helton
Date                   :=06/28/14
CodeLitePath           :="C:\Program Files (x86)\CodeLite"
LinkerName             :=C:\MinGW-4.8.1\bin\g++.exe 
SharedObjectLinkerName :=C:\MinGW-4.8.1\bin\g++.exe -shared -fPIC
ObjectSuffix           :=.o
DependSuffix           :=.o.d
PreprocessSuffix       :=.i
DebugSwitch            :=-g 
IncludeSwitch          :=-I
LibrarySwitch          :=-l
OutputSwitch           :=-o 
LibraryPathSwitch      :=-L
PreprocessorSwitch     :=-D
SourceSwitch           :=-c 
OutputFile             :=$(IntermediateDirectory)/$(ProjectName).dll
Preprocessors          :=
ObjectSwitch           :=-o 
ArchiveOutputSwitch    := 
PreprocessOnlySwitch   :=-E
ObjectsFileList        :="IntegrateSysLib.txt"
PCHCompileFlags        :=
MakeDirCommand         :=makedir
RcCmpOptions           := 
RcCompilerName         :=C:\MinGW-4.8.1\bin\windres.exe 
LinkOptions            :=  -O2
IncludePath            :=  $(IncludeSwitch). $(IncludeSwitch). 
IncludePCH             := 
RcIncludePath          := 
Libs                   := 
ArLibs                 :=  
LibPath                := $(LibraryPathSwitch). 

##
## Common variables
## AR, CXX, CC, AS, CXXFLAGS and CFLAGS can be overriden using an environment variables
##
AR       := C:\MinGW-4.8.1\bin\ar.exe rcu
CXX      := C:\MinGW-4.8.1\bin\g++.exe 
CC       := C:\MinGW-4.8.1\bin\gcc.exe 
CXXFLAGS :=   $(Preprocessors)
CFLAGS   :=   $(Preprocessors)
ASFLAGS  := 
AS       := C:\MinGW-4.8.1\bin\as.exe 


##
## User defined environment variables
##
CodeLiteDir:=C:\Program Files (x86)\CodeLite
UNIT_TEST_PP_SRC_DIR:=C:\UnitTest++-1.3
Objects0=$(IntermediateDirectory)/IntegrateSysLib.cpp$(ObjectSuffix) 



Objects=$(Objects0) 

##
## Main Build Targets 
##
.PHONY: all clean PreBuild PrePreBuild PostBuild
all: $(OutputFile)

$(OutputFile): $(IntermediateDirectory)/.d $(Objects) 
	@$(MakeDirCommand) $(@D)
	@echo "" > $(IntermediateDirectory)/.d
	@echo $(Objects0)  > $(ObjectsFileList)
	$(SharedObjectLinkerName) $(OutputSwitch)$(OutputFile) @$(ObjectsFileList) $(LibPath) $(Libs) $(LinkOptions)
	@$(MakeDirCommand) "C:\IntegrateSys\C++\IntegrateSysLibCodeLite/.build-release"
	@echo rebuilt > "C:\IntegrateSys\C++\IntegrateSysLibCodeLite/.build-release/IntegrateSysLib"

$(IntermediateDirectory)/.d:
	@$(MakeDirCommand) "./Release"

PreBuild:


##
## Objects
##
$(IntermediateDirectory)/IntegrateSysLib.cpp$(ObjectSuffix): IntegrateSysLib.cpp $(IntermediateDirectory)/IntegrateSysLib.cpp$(DependSuffix)
	$(CXX) $(IncludePCH) $(SourceSwitch) "C:/IntegrateSys/C++/IntegrateSysLibCodeLite/IntegrateSysLib.cpp" $(CXXFLAGS) $(ObjectSwitch)$(IntermediateDirectory)/IntegrateSysLib.cpp$(ObjectSuffix) $(IncludePath)
$(IntermediateDirectory)/IntegrateSysLib.cpp$(DependSuffix): IntegrateSysLib.cpp
	@$(CXX) $(CXXFLAGS) $(IncludePCH) $(IncludePath) -MG -MP -MT$(IntermediateDirectory)/IntegrateSysLib.cpp$(ObjectSuffix) -MF$(IntermediateDirectory)/IntegrateSysLib.cpp$(DependSuffix) -MM "IntegrateSysLib.cpp"

$(IntermediateDirectory)/IntegrateSysLib.cpp$(PreprocessSuffix): IntegrateSysLib.cpp
	@$(CXX) $(CXXFLAGS) $(IncludePCH) $(IncludePath) $(PreprocessOnlySwitch) $(OutputSwitch) $(IntermediateDirectory)/IntegrateSysLib.cpp$(PreprocessSuffix) "IntegrateSysLib.cpp"


-include $(IntermediateDirectory)/*$(DependSuffix)
##
## Clean
##
clean:
	$(RM) $(IntermediateDirectory)/IntegrateSysLib.cpp$(ObjectSuffix)
	$(RM) $(IntermediateDirectory)/IntegrateSysLib.cpp$(DependSuffix)
	$(RM) $(IntermediateDirectory)/IntegrateSysLib.cpp$(PreprocessSuffix)
	$(RM) $(OutputFile)
	$(RM) $(OutputFile)
	$(RM) ".build-release/IntegrateSysLib"


