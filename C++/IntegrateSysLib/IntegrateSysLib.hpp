#ifndef INTEGRATESYSLIB_H
#define INTEGRATESYSLIB_H

#define DLL_EXPORT extern "C" __declspec(dllexport)

// gets - sensores
DLL_EXPORT int getTemperatura();
DLL_EXPORT int getUmidade();
DLL_EXPORT int getLuminosidade();
DLL_EXPORT bool hasAgua();
DLL_EXPORT bool hasEnergia();
DLL_EXPORT bool inIncendio();

// gets - atuadores
DLL_EXPORT bool statusVentilador();
DLL_EXPORT bool statusAquecedor();
DLL_EXPORT bool statusUmedecedor();
DLL_EXPORT bool statusLampada();
DLL_EXPORT bool statusAlarme();

// sets - sensores
DLL_EXPORT void setAgua(bool);
DLL_EXPORT void setEnergia(bool);
DLL_EXPORT void setIncendio(bool);
DLL_EXPORT void setLuminosidade(int);
DLL_EXPORT void setTemperatura(int);
DLL_EXPORT void setUmidade(int);

// sets - atuadores
DLL_EXPORT void setAlarme(bool);
DLL_EXPORT void setAquecedor(bool);
DLL_EXPORT void setLampada(bool);
DLL_EXPORT void setUmedecedor(bool);
DLL_EXPORT void setVentilador(bool);

#endif
