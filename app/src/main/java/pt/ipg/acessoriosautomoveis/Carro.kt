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
            val posNomeAcesInter = cursor.getColumnIndex(TabelaAcesInter.CAMPO_NOME)
            val posClasseAcesInter = cursor.getColumnIndex(TabelaAcesInter.CAMPO_CLASSE)
            val posDescricaoAcesInter = cursor.getColumnIndex(TabelaAcesInter.CAMPO_DESCRICAO)

            val posAcessExtFK = cursor.getColumnIndex(TabelaCarro.CAMPO_FK_ACESSEXTER)
            val posNomeAcesExter = cursor.getColumnIndex(TabelaAcesExter.CAMPO_DESIGNACAO)
            val posCategoriaAcessExter = cursor.getColumnIndex(TabelaAcesExter.CAMPO_CATEGORIA)
            val posCorAcesExter = cursor.getColumnIndex(TabelaAcesExter.CAMPO_COR)



            val id = cursor.getLong(posId)
            val marca = cursor.getString(posMarca)
            val cor = cursor.getString(posCor)

            val acessIntId = cursor.getLong(posAcessIntFK)
            val nomeAcesInter = cursor.getString(posNomeAcesInter)
            val classeAcessInter = cursor.getString(posClasseAcesInter)
            val descricaoAcessInter = cursor.getString(posDescricaoAcesInter)
            val acessInter = AcessInter(nomeAcesInter, classeAcessInter.toString(), descricaoAcessInter.toString(), acessIntId)


            val acessExtId = cursor.getLong(posAcessExtFK)
            val nomeAcessExter = cursor.getString(posNomeAcesExter)
            val categoriaAcesExter = cursor.getString(posCategoriaAcessExter)
            val corAcesExter = cursor.getString(posCorAcesExter)
            val acessExter = AcessExter(nomeAcessExter, categoriaAcesExter.toString(), corAcesExter.toString(), acessExtId)

            return Carro(marca, cor, acessInter, acessExter , id)
        }
    }

}
