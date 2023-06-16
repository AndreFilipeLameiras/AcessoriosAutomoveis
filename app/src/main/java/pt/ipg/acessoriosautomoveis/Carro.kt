package pt.ipg.acessoriosautomoveis

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns
import java.io.Serializable

data class Carro(
    var marca: String,
    var cor: String,
    var acessInter: AcessInter,
    var acessExter: AcessExter,
    var id: Long? = -1
) : Serializable{

    fun toContentValues(): ContentValues{
        val valores = ContentValues()

        valores.put(TabelaCarro.CAMPO_MARCA, marca)
        valores.put(TabelaCarro.CAMPO_COR, cor)
        valores.put(TabelaCarro.CAMPO_FK_ACESSINTER, acessInter.id)
        valores.put(TabelaCarro.CAMPO_FK_ACESSEXTER, acessExter.id)


        return valores

    }

    companion object{
        fun fromCursor(cursor: Cursor): Carro{
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posMarca = cursor.getColumnIndex(TabelaCarro.CAMPO_MARCA)
            val posCor = cursor.getColumnIndex(TabelaCarro.CAMPO_COR)

            val posAcessIntFK = cursor.getColumnIndex(TabelaCarro.CAMPO_FK_ACESSINTER)
            val posNomeAcesInter = cursor.getColumnIndex(TabelaCarro.CAMPO_NOME_ACESSINTER)
            //val posMarcaAcesInter = cursor.getColumnIndex(TabelaCarro.CAMPO_)
            //val posDescricaoAcesInter = cursor.getColumnIndex(TabelaCarro.CAMPO_NOME_ACESSINTER)

            val posAcessExtFK = cursor.getColumnIndex(TabelaCarro.CAMPO_FK_ACESSEXTER)
            val posNomeAcesExter = cursor.getColumnIndex(TabelaCarro.CAMPO_NOME_ACESSEXTER)


            val id = cursor.getLong(posId)
            val marca = cursor.getString(posMarca)
            val cor = cursor.getString(posCor)

            val acessIntId = cursor.getLong(posAcessIntFK)
            val nomeAcesInter = cursor.getString(posNomeAcesInter)

            val acessExtId = cursor.getLong(posAcessExtFK)
            val nomeAcessExter = cursor.getString(posNomeAcesExter)

            return Carro(marca, cor, AcessInter(nomeAcesInter, marca = toString(), descricao = toString() ,acessIntId ), AcessExter(nomeAcessExter, marca = toString(), cor = toString() ,acessIntId ), id)
        }
    }

}
