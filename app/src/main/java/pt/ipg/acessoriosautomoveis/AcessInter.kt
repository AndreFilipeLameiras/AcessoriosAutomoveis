package pt.ipg.acessoriosautomoveis

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

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

    companion object {
        fun fromCursor(cursor: Cursor): AcessInter{
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posNome = cursor.getColumnIndex(TabelaAcesInter.CAMPO_NOME)
            val posMarca = cursor.getColumnIndex(TabelaAcesInter.CAMPO_MARCA)
            val posDescricao = cursor.getColumnIndex(TabelaAcesInter.CAMPO_DESCRICAO)

            val id = cursor.getLong(posId)
            val nome = cursor.getString(posNome)
            val marca = cursor.getString(posMarca)
            val descricao = cursor.getString(posDescricao)

            return AcessInter(nome, marca, descricao, id)

        }
    }

}