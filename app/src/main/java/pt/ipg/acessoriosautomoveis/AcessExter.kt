package pt.ipg.acessoriosautomoveis

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

data class AcessExter(
    var nome: String,
    var categoria: String,
    var cor: String,
    var id: Long = -1
){

    fun toContentValues(): ContentValues{
        val valores = ContentValues()

        valores.put(TabelaAcesExter.CAMPO_DESIGNACAO, nome)
        valores.put(TabelaAcesExter.CAMPO_CATEGORIA, categoria)
        valores.put(TabelaAcesExter.CAMPO_COR, cor)

        return valores

    }


    companion object {
        fun fromCursor(cursor: Cursor): AcessInter{
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posNome = cursor.getColumnIndex(TabelaAcesExter.CAMPO_DESIGNACAO)
            val posCategoria = cursor.getColumnIndex(TabelaAcesExter.CAMPO_CATEGORIA)
            val posCor = cursor.getColumnIndex(TabelaAcesExter.CAMPO_COR)

            val id = cursor.getLong(posId)
            val nome = cursor.getString(posNome)
            val categoria = cursor.getString(posCategoria)
            val cor = cursor.getString(posCor)

            return AcessInter(nome, categoria, cor, id)

        }
    }

}
