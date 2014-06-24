#include "IntegrateSysLib.hpp"
#include <windows.h>

//sensores
int _temperatura = 10;
int _umidade = 60;
bool _luminosidade = false;
bool _agua = true;
bool _energia = true;
bool _incendio = false;

// atuadores
bool _ventiladores = false;
bool _aquecedores = false;
bool _umedecedores = false;
bool _lampadas = true;
bool _alarme = false;

int getTemperatura() {
	return _temperatura;
}

int getUmidade() {
	return _umidade;
}

bool hasLuminosidade() {
	return _luminosidade;
}

bool hasAgua() {
	return _agua;
}

bool hasEnergia() {
	return _energia;
}

bool hasIncendio() {
	return _incendio;
}

bool statusVentiladores() {
	return _ventiladores;
}

bool statusAquecedores() {
	return _aquecedores;
}

bool statusUmedecedores() {
	return _umedecedores;
}

bool statusLampadas() {
	return _lampadas;
}

bool statusAlarme() {
	return _alarme;
}

void setAgua(bool agua) {
	_agua = agua;
}

void setEnergia(bool energia) {
	_energia = energia;
}

void setIncendio(bool incendio) {
	_incendio = incendio;
}

void setLuminosidade(bool luminosidade) {
	_luminosidade = luminosidade;
}

void setTemperatura(int temperatura) {
	_temperatura = temperatura;
}

void setUmidade(int umidade) {
	_umidade = umidade;
}

void setAlarme(bool alarme) {
	_alarme = alarme;
}

void setAquecedores(bool aquecedores) {
	_aquecedores = aquecedores;
}

void setLampadas(bool lampadas) {
	_lampadas = lampadas;
}

void setUmedecedores(bool umedecedores) {
	_umedecedores = umedecedores;
}

void setVentiladores(bool ventiladores) {
	_ventiladores = ventiladores;
}
