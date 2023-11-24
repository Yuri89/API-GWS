package gws.api.aplication.serviceTest;

import com.gws.api.apigws.services.FTE;
import org.junit.jupiter.api.Test;

public class FTEcalculo {

    @Test
    public void calculo(){
        FTE fte = new FTE();

        double horastrabalhadas = fte.somaHorasTrabalhadas(new double[]{8.0,7.5,8.0,8.0,6.0,8.0,0.0,8.0,7.5,8.0,8.0,6.0,8.0,0.0,8.0,7.5,8.0,8.0,6.0,8.0,0.0,8.0,7.5,8.0,8.0,6.0,8.0,0.0,});
        String resultado = fte.calculoFTE(horastrabalhadas);


        System.out.println(resultado);
    }

}
