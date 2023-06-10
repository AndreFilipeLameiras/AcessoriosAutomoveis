package pt.ipg.acessoriosautomoveis

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

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


    companion object {
        fun fromCursor(cursor: Cursor): AcessInter{
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posNome = cursor.getColumnIndex(TabelaAcesExter.CAMPO_NOME)
            val posMarca = cursor.getColumnIndex(TabelaAcesExter.CAMPO_MARCA)
            val posCor = cursor.getColumnIndex(TabelaAcesExter.CAMPO_COR)

            val id = cursor.getLong(posId)
            val nome = cursor.getString(posNome)
            val marca = cursor.getString(posMarca)
            val cor = cursor.getString(posCor)

            return AcessInter(nome, marca, cor, id)

        }
    }

}
