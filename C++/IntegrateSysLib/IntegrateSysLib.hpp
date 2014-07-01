#ifndef INTEGRATESYSLIB_H
#define INTEGRATESYSLIB_H

#define DLL_EXPORT extern "C" __declspec(dllexport)

// gets - sensores
DLL_EXPORT int getLuminosidade();
DLL_EXPORT int getTemperatura();
DLL_EXPORT int getUmidade();

// sets - sensores
DLL_EXPORT void setLuminosidade(int);
DLL_EXPORT void setTemperatura(int);
DLL_EXPORT void setUmidade(int);

// gets - atuadores
DLL_EXPORT int getNivelAquecedor();
DLL_EXPORT int getNivelLampada();
DLL_EXPORT int getNivelUmidificador();
DLL_EXPORT int getNivelVentilador();

// sets - atuadores
DLL_EXPORT void setNivelAquecedor(int);
DLL_EXPORT void setNivelLampada(int);
DLL_EXPORT void setNivelUmidificador(int);
DLL_EXPORT void setNivelVentilador(int);

#endif
