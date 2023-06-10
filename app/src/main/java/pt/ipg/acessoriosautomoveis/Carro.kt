package pt.ipg.acessoriosautomoveis

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

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

    companion object{
        fun fromCursor(cursor: Cursor): Carro{
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posMarca = cursor.getColumnIndex(TabelaCarro.CAMPO_MARCA)
            val posCor = cursor.getColumnIndex(TabelaCarro.CAMPO_COR)
            val posAcessIntFK = cursor.getColumnIndex(TabelaCarro.CAMPO_FK_ACESSINTER)
            val posAcessExtFK = cursor.getColumnIndex(TabelaCarro.CAMPO_FK_ACESSEXTER)

            val id = cursor.getLong(posId)
            val marca = cursor.getString(posMarca)
            val cor = cursor.getString(posCor)
            val acessIntId = cursor.getLong(posAcessIntFK)
            val acessExtId = cursor.getLong(posAcessExtFK)

            return Carro(marca, cor, acessIntId, acessExtId, id)
        }
    }

}
