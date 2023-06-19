package pt.ipg.acessoriosautomoveis

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns
import java.io.Serializable

data class AcessInter(
    var nome: String,
    var classe: String,
    var descricao: String,
    var id: Long = -1
) : Serializable{

    fun toContentValues() : ContentValues {
        val valores = ContentValues()

        valores.put(TabelaAcesInter.CAMPO_NOME, nome)
        valores.put(TabelaAcesInter.CAMPO_CLASSE, classe)
        valores.put(TabelaAcesInter.CAMPO_DESCRICAO, descricao)

        return valores
    }

    companion object {
        fun fromCursor(cursor: Cursor): AcessInter{
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posNome = cursor.getColumnIndex(TabelaAcesInter.CAMPO_NOME)
            val posClasse = cursor.getColumnIndex(TabelaAcesInter.CAMPO_CLASSE)
            val posDescricao = cursor.getColumnIndex(TabelaAcesInter.CAMPO_DESCRICAO)

            val id = cursor.getLong(posId)
            val nome = cursor.getString(posNome)
            val classe = cursor.getString(posClasse)
            val descricao = cursor.getString(posDescricao)

            return AcessInter(nome, classe, descricao, id)

        }
    }

}