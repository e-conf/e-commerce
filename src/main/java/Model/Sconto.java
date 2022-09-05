package Model;

import java.util.Date;

public class Sconto {

    private String codiceSconto;
    private int percentualeSconto;
    private Date dataFineSconto;

    public String getCodiceSconto() {
        return codiceSconto;
    }

    public void setCodiceSconto(String codiceSconto) {
        this.codiceSconto = codiceSconto;
    }

    public int getPercentualeSconto() {
        return percentualeSconto;
    }

    public void setPercentualeSconto(int percentualeSconto) {
        this.percentualeSconto = percentualeSconto;
    }

    public Date getDataFineSconto() {
        return this.dataFineSconto;
    }

    public void setDataFineSconto(Date dataFineSconto) {
        this.dataFineSconto = dataFineSconto;
    }
}
