package pt.ipg.acessoriosautomoveis

import android.content.ContentValues

data class Carro(
    var marca: String,
    var cor: String,
    var idAcessInter: Long,
    var idAcessExter: Long,
    var id: Long = -1
){

    fun toContentValues(): ContentValues{
        val valores = ContentValues()

        valores.put(TabelaCarro.CAMPO_MARCA, marca)
        valores.put(TabelaCarro.CAMPO_COR, cor)
        valores.put(TabelaCarro.CAMPO_FK_ACESSINTER, idAcessInter)
        valores.put(TabelaCarro.CAMPO_FK_ACESSEXTER, idAcessExter)

        return valores

    }

}
