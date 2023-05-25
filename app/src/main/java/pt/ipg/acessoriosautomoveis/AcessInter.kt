package pt.ipg.acessoriosautomoveis

import android.content.ContentValues

data class AcessInter(
    var nome: String,
    var marca: String,
    var descricao: String,
    var id: Long = -1
) {

    fun toContentValues() : ContentValues {
        val valores = ContentValues()

        valores.put(TabelaAcesInter.CAMPO_NOME, nome)
        valores.put(TabelaAcesInter.CAMPO_MARCA, marca)
        valores.put(TabelaAcesInter.CAMPO_DESCRICAO, descricao)

        return valores
    }

}