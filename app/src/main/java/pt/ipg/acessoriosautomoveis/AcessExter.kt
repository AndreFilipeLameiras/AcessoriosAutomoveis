package pt.ipg.acessoriosautomoveis

import android.content.ContentValues

data class AcessExter(
    var nome: String,
    var marca: String,
    var cor: String,
    var id: Long = -1
){

    fun toContentValues(): ContentValues{
        val valores = ContentValues()

        valores.put(TabelaAcesExter.CAMPO_NOME, nome)
        valores.put(TabelaAcesExter.CAMPO_MARCA, marca)
        valores.put(TabelaAcesExter.CAMPO_COR, cor)

        return valores

    }
}
