#include "IntegrateSysLib.hpp"

//sensores
int _temperatura = 10;
int _umidade = 60;
int _luminosidade = 50;
bool _agua = true;
bool _energia = true;
bool _incendio = false;

// atuadores
bool _ventilador = false;
bool _aquecedor = false;
bool _umidificador = false;
bool _lampada = true;
bool _alarme = false;

int getTemperatura() {
	return _temperatura;
}

int getUmidade() {
	return _umidade;
}

int getLuminosidade() {
	return _luminosidade;
}

bool hasAgua() {
	return _agua;
}

bool hasEnergia() {
	return _energia;
}

bool inIncendio() {
	return _incendio;
}

bool statusVentilador() {
	return _ventilador;
}

bool statusAquecedor() {
	return _aquecedor;
}

bool statusUmidificador() {
	return _umidificador;
}

bool statusLampada() {
	return _lampada;
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

void setLuminosidade(int luminosidade) {
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

void setAquecedor(bool aquecedor) {
	_aquecedor = aquecedor;
}

void setLampada(bool lampada) {
	_lampada = lampada;
}

void setUmidificador(bool umidificador) {
	_umidificador = umidificador;
}

void setVentilador(bool ventilador) {
	_ventilador = ventilador;
}
