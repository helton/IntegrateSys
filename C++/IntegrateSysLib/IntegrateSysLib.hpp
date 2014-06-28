#ifndef INTEGRATESYSLIB_H
#define INTEGRATESYSLIB_H

#define DLL_EXPORT extern "C" __declspec(dllexport)

// gets - sensores
DLL_EXPORT int getTemperatura();
DLL_EXPORT int getUmidade();
DLL_EXPORT bool hasLuminosidade();
DLL_EXPORT bool hasAgua();
DLL_EXPORT bool hasEnergia();
DLL_EXPORT bool hasIncendio();

// gets - atuadores
DLL_EXPORT bool statusVentiladores();
DLL_EXPORT bool statusAquecedores();
DLL_EXPORT bool statusUmedecedores();
DLL_EXPORT bool statusLampadas();
DLL_EXPORT bool statusAlarme();

// sets - sensores
DLL_EXPORT void setAgua(bool);
DLL_EXPORT void setEnergia(bool);
DLL_EXPORT void setIncendio(bool);
DLL_EXPORT void setLuminosidade(bool);
DLL_EXPORT void setTemperatura(int);
DLL_EXPORT void setUmidade(int);

// sets - atuadores
DLL_EXPORT void setAlarme(bool);
DLL_EXPORT void setAquecedores(bool);
DLL_EXPORT void setLampadas(bool);
DLL_EXPORT void setUmedecedores(bool);
DLL_EXPORT void setVentiladores(bool);

#endif
