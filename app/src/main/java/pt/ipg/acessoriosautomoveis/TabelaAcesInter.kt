package pt.ipg.acessoriosautomoveis

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns


class TabelaAcesInter(db: SQLiteDatabase): TabelaDB(db, NOME_TABELA) {
    override fun cria() {
        db.execSQL("CREATE TABLE $NOME_TABELA ($CHAVE_TABELA, $CAMPO_NOME TEXT NOT NULL, $CAMPO_CLASSE TEXT NOT NULL, $CAMPO_DESCRICAO TEXT NOT NULL)")

    }

    companion object {
        const val NOME_TABELA = "acessoriosInterior"
        const val CAMPO_ID = "$NOME_TABELA.${BaseColumns._ID}"
        const val CAMPO_NOME = "nome"
        const val CAMPO_CLASSE = "marca"
        const val CAMPO_DESCRICAO = "descricao"

        val CAMPOS = arrayOf(BaseColumns._ID, CAMPO_NOME, CAMPO_CLASSE, CAMPO_DESCRICAO)
    }

}