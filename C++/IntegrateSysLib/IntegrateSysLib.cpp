#include "IntegrateSysLib.hpp"

//sensores
int _temperatura = 10;
int _umidade = 60;
int _luminosidade = 50;

// atuadores
int _nivelAquecedor = 0;
int _nivelLampada = 0;
int _nivelUmidificador = 0;
int _nivelVentilador = 0;

// gets - sensores
int getLuminosidade() {
	return _luminosidade;
}

int getTemperatura() {
	return _temperatura;
}

int getUmidade() {
	return _umidade;
}

// sets - sensores
void setLuminosidade(int luminosidade) {
	_luminosidade = luminosidade;
}

void setTemperatura(int temperatura) {
	_temperatura = temperatura;
}

void setUmidade(int umidade) {
	_umidade = umidade;
}

// gets - atuadores
int getNivelAquecedor() {
	return _nivelAquecedor;
}

int getNivelLampada() {
	return _nivelLampada;
}

int getNivelUmidificador() {
	return _nivelUmidificador;
}

int getNivelVentilador() {
	return _nivelVentilador;
}

// sets - atuadores
void setNivelAquecedor(int nivelAquecedor) {
	_nivelAquecedor = nivelAquecedor;
}

void setNivelLampada(int nivelLampada) {
	_nivelLampada = nivelLampada;
}

void setNivelUmidificador(int nivelUmidificador) {
	_nivelUmidificador = nivelUmidificador;
}

void setNivelVentilador(int nivelVentilador) {
	_nivelVentilador = nivelVentilador;
}
