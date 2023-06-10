package pt.ipg.acessoriosautomoveis

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns


class TabelaAcesExter (db: SQLiteDatabase): TabelaDB(db, NOME_TABELA) {
    override fun cria() {
        db.execSQL("CREATE TABLE $NOME_TABELA ($CHAVE_TABELA, $CAMPO_NOME TEXT NOT NULL, $CAMPO_MARCA TEXT NOT NULL, $CAMPO_COR TEXT )")

    }

    companion object {
        const val NOME_TABELA = "acessoriosExterior"
        const val CAMPO_NOME = "nome"
        const val CAMPO_MARCA = "marca"
        const val CAMPO_COR = "cor"


        val CAMPOS = arrayOf(BaseColumns._ID, CAMPO_NOME, CAMPO_MARCA, CAMPO_COR)
    }

}