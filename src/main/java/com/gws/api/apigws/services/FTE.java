package com.gws.api.apigws.services;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.springframework.stereotype.Service;

@Service
public class FTE {

//    está media é para calcular um grupo e tirar o FTE delas
//    public double mediaHorasTrabalhadas(double[] horasTrabalhadas){
//        double[] horastrabalhadas = {160, 150, 170, 155}; // Substitua pelos seus dados reais
//
//        // Crie um objeto DescriptiveStatistics
//        DescriptiveStatistics stats = new DescriptiveStatistics();
//
//        // Adicione os valores ao objeto
//        for (double horas : horasTrabalhadas) {
//            stats.addValue(horas);
//        }
//
//        // Calcule a média
//        double media = stats.getMean();
//
//        // Imprima a média
//        return media;
//    }


    public double somaHorasTrabalhadas(double[] pontosTrabalho){
        double soma = 0;

        for (double horas : pontosTrabalho) {
            soma += horas;
        }

        return soma;
    }

    public String calculoFTE(double horaMesTrabalhado){

        Double resultadoHora = horaMesTrabalhado/160;

        return "FTE = "+resultadoHora;
    }
}
