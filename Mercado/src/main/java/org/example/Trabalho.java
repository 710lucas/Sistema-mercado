package org.example;

import java.util.Date;

public class Trabalho {
    private Date horaDeEntrada;
    private Date horaDeSaida;

    public Trabalho(Date entrada, Date saida){
        horaDeEntrada = entrada;
        horaDeSaida = saida;
    }

    public Date getHoraDeEntrada() {
        return horaDeEntrada;
    }

    public Date getHoraDeSaida() {
        return horaDeSaida;
    }

    public long calculaHorasTrabalhadas(){
        long segundos = calculaSegundos();
        long horas = segundos/3600;
        return horas;
    }
    public long calculaSegundos(){
        long segundos = (horaDeSaida.getTime()-horaDeEntrada.getTime())/1000;
        return segundos;
    }
}
